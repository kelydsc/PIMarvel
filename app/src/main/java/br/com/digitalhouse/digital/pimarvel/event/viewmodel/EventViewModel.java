package br.com.digitalhouse.digital.pimarvel.event.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.event.model.EventsResponse;
import br.com.digitalhouse.digital.pimarvel.event.model.Result;
import br.com.digitalhouse.digital.pimarvel.event.repository.EventRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EventViewModel extends AndroidViewModel {// Variáveis que serão usadas para buscar os quadrinhos na API

    private MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();
    //private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private EventRepository repository = new EventRepository();

    // Construtor padrão do viewmodel
    public EventViewModel(@NonNull Application application) {
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

    public void getEvents() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(repository.getEvents()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                //  .doOnSubscribe(disposable1 -> loading.setValue(true)) ???
                //  .doAfterTerminate(() -> loading.setValue(false)) ????
                .subscribe(new Consumer<EventsResponse>() {
                    @Override
                    public void accept(EventsResponse response) throws Exception {
                        // Chegou aqui então alteramos o live data, assim a View que está observando ele pode atualizar a tela
                        resultLiveData.setValue(response.getData().getResults());
                    }
                }, throwable -> {
                    Log.i("LOG", "Error: " + throwable.getMessage());
                }));
    }
}