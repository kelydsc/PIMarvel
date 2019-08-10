package br.com.digitalhouse.digital.pimarvel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.SerieDAO;
import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;
import br.com.digitalhouse.digital.pimarvel.model.serie.SerieResponse;
import br.com.digitalhouse.digital.pimarvel.repository.SerieRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.isNetworkConnected;

public class SerieViewModel extends AndroidViewModel {

    private MutableLiveData<List<Serie>> serieLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private SerieRepository repository = new SerieRepository();

    //Construtor padrão do viewmodel
    public SerieViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Serie>> getSerieLiveData() {

        return serieLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {

        return loadingLiveData;

    }

    public LiveData<Throwable> getErrorLiveData() {

        return errorLiveData;

    }


    public void searchSerie() {

        if (isNetworkConnected(getApplication())) {

            //Recupera dados da API
            getSeries();

        } else {

            //Recupera dados do banco de Dados
            getLocalSerie();

        }
    }

    private void getLocalSerie() {
        disposable.add(
                repository.getSerieLocal(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(false))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(series -> serieLiveData.setValue(series)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private SerieResponse saveItems(SerieResponse serieResponse) {

        SerieDAO serieDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .serieDAO();

        serieDAO.deleteAll();
        serieDAO.insertAll(serieResponse.getData().getSeries());

        return serieResponse;

    }

    //Recupera dados da API
    public void getSeries() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(
                repository.getSeries()
                        .subscribeOn(Schedulers.newThread())
                        .map(serieResponse -> saveItems(serieResponse))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(serieResponse -> serieLiveData.setValue(serieResponse.getData().getSeries())
                                , throwable -> errorLiveData.setValue(throwable)));
    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}

