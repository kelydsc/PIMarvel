package br.com.digitalhouse.digital.pimarvel.comic.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.comic.dao.ComicDAO;
import br.com.digitalhouse.digital.pimarvel.comic.model.ComicsResponse;
import br.com.digitalhouse.digital.pimarvel.comic.model.Result;
import br.com.digitalhouse.digital.pimarvel.comic.repository.ComicRepository;
import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ComicViewModel extends AndroidViewModel {// Variáveis que serão usadas para buscar os quadrinhos na API

    private MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();
    private ComicRepository repository = new ComicRepository();

    // Construtor padrão do viewmodel
    public ComicViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getResults() {
        return resultLiveData;
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
                        .subscribe(results -> resultLiveData.setValue(results)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private ComicsResponse saveItems(Result result) {

        ComicDAO comicDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .comicDAO();

        comicDAO.deleteAll();
        comicDAO.insertAll(result.getData().getResults());

        return result;

    }

    //Recupera dados da API
    public void getComics() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(repository.getComics()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                .doAfterTerminate(() -> loadingLiveData.setValue(false))
                .subscribe(new Consumer<ComicsResponse>() {
                    @Override
                    public void accept(ComicsResponse response) throws Exception {
                        // Chegou aqui então alteramos o live data, assim a View que está observando ele pode atualizar a tela
                        resultLiveData.setValue(response.getData().getResults());
                    }
                }, throwable -> errorLiveData.setValue(throwable)));
    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {

        super.onCleared();
        disposable.clear();
    }


}
