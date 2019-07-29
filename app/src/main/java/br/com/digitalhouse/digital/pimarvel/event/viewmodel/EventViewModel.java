package br.com.digitalhouse.digital.pimarvel.event.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.event.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.event.data.database.dao.EventDAO;
import br.com.digitalhouse.digital.pimarvel.event.model.EventsResponse;
import br.com.digitalhouse.digital.pimarvel.event.model.Result;
import br.com.digitalhouse.digital.pimarvel.event.repository.EventRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.isNetworkConnected;

public class EventViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private EventRepository repository = new EventRepository();

    // Construtor padrão do viewmodel
    public EventViewModel(@NonNull Application application) {
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


    public void searchEvent() {

        if (isNetworkConnected(getApplication())) {

            //Recupera dados da API
            getEvents();

        } else {

            //Recupera dados do banco de Dados
            getLocalEvent();

        }
    }

    private void getLocalEvent() {
        disposable.add(
                repository.getEventLocal(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(false))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(results -> resultLiveData.setValue(results)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private EventsResponse saveItems(EventsResponse eventsResponse) {

        EventDAO eventDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .eventDAO();

        eventDAO.deleteAll();
        eventDAO.insertAll(eventsResponse.getData().getResults());

        return eventsResponse;

    }

    //Recupera dados da API
    public void getEvents() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(
                repository.getEvents()
                        .subscribeOn(Schedulers.newThread())
                        .map(eventsResponse -> saveItems(eventsResponse))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(eventsResponse -> resultLiveData.setValue(eventsResponse.getData().getResults())
                                , throwable -> errorLiveData.setValue(throwable)));
    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
