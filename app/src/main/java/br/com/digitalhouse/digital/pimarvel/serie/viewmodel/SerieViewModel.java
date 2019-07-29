package br.com.digitalhouse.digital.pimarvel.serie.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.serie.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.serie.data.database.dao.SerieDAO;
import br.com.digitalhouse.digital.pimarvel.serie.model.Result;
import br.com.digitalhouse.digital.pimarvel.serie.model.SeriesResponse;
import br.com.digitalhouse.digital.pimarvel.serie.repository.SerieRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.isNetworkConnected;

public class SerieViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private SerieRepository repository = new SerieRepository();

    // Construtor padrão do viewmodel
    public SerieViewModel(@NonNull Application application) {
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
                        .subscribe(results -> resultLiveData.setValue(results)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private SeriesResponse saveItems(SeriesResponse seriesResponse) {

        SerieDAO serieDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .serieDAO();

        serieDAO.deleteAll();
        serieDAO.insertAll(seriesResponse.getData().getResults());

        return seriesResponse;

    }

    //Recupera dados da API
    public void getSeries() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(
                repository.getSeries()
                        .subscribeOn(Schedulers.newThread())
                        .map(seriesResponse -> saveItems(seriesResponse))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(seriesResponse -> resultLiveData.setValue(seriesResponse.getData().getResults())
                                , throwable -> errorLiveData.setValue(throwable)));
    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}