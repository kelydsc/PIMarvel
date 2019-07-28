package br.com.digitalhouse.digital.pimarvel.serie.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.serie.model.Result;
import br.com.digitalhouse.digital.pimarvel.serie.model.SeriesResponse;
import br.com.digitalhouse.digital.pimarvel.serie.repository.SerieRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SerieViewModel extends AndroidViewModel {// Variáveis que serão usadas para buscar os quadrinhos na API

    private MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();
    //private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private SerieRepository repository = new SerieRepository();

    // Construtor padrão do viewmodel
    public SerieViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getResults() {
        return resultLiveData;
    }

    /*precisa???????
    public LiveData<Boolean> isLoading() {
        return loading;
    }
    */

    public void getSeries() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(repository.getSeries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                //  .doOnSubscribe(disposable1 -> loading.setValue(true)) ???
                //  .doAfterTerminate(() -> loading.setValue(false)) ????
                .subscribe(new Consumer<SeriesResponse>() {
                    @Override
                    public void accept(SeriesResponse response) throws Exception {
                        // Chegou aqui então alteramos o live data, assim a View que está observando ele pode atualizar a tela
                        resultLiveData.setValue(response.getData().getResults());
                    }
                }, throwable -> {
                    Log.i("LOG", "Error: " + throwable.getMessage());
                }));
    }
}
