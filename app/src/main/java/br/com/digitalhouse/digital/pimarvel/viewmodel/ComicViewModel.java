package br.com.digitalhouse.digital.pimarvel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.ComicDAO;
import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.model.comic.ComicResponse;
import br.com.digitalhouse.digital.pimarvel.repository.ComicRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.isNetworkConnected;

public class ComicViewModel extends AndroidViewModel {

    private MutableLiveData<List<Comic>> eventLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private ComicRepository repository = new ComicRepository();

    //Construtor padrão do viewmodel
    public ComicViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Comic>> getComicLiveData() {

        return eventLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {

        return loadingLiveData;

    }

    public LiveData<Throwable> getErrorLiveData() {

        return errorLiveData;

    }


    public void searchComic() {

        if (isNetworkConnected(getApplication())) {

            //Recupera dados da API
            getComics();

        } else {

            //Recupera dados do banco de Dados
            getLocalComic();

        }
    }

    private void getLocalComic() {
        disposable.add(
                repository.getComicLocal(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(false))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(comics -> eventLiveData.setValue(comics)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private ComicResponse saveItems(ComicResponse comicResponse) {

        ComicDAO comicDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .comicDAO();

        comicDAO.deleteAll();
        comicDAO.insertAll(comicResponse.getData().getComics());

        return comicResponse;

    }

    //Recupera dados da API
    public void getComics() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(
                repository.getComics()
                        .subscribeOn(Schedulers.newThread())
                        .map(comicResponse -> saveItems(comicResponse))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(comicResponse -> eventLiveData.setValue(comicResponse.getData().getComics())
                                , throwable -> errorLiveData.setValue(throwable)));
    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}

