package br.com.digitalhouse.digital.pimarvel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.EventDAO;
import br.com.digitalhouse.digital.pimarvel.model.event.Event;
import br.com.digitalhouse.digital.pimarvel.model.event.EventResponse;
import br.com.digitalhouse.digital.pimarvel.repository.EventRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.isNetworkConnected;

public class EventViewModel extends AndroidViewModel {

    private MutableLiveData<List<Event>> eventLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private EventRepository repository = new EventRepository();

    //Construtor padrão do viewmodel
    public EventViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Event>> getEventLiveData() {

        return eventLiveData;
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
                        .subscribe(events -> eventLiveData.setValue(events)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private EventResponse saveItems(EventResponse eventResponse) {

        EventDAO eventDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .eventDAO();

        eventDAO.deleteAll();
        eventDAO.insertAll(eventResponse.getData().getEvents());

        return eventResponse;

    }

    //Recupera dados da API
    public void getEvents() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(
                repository.getEvents()
                        .subscribeOn(Schedulers.newThread())
                        .map(eventResponse -> saveItems(eventResponse))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(eventResponse -> eventLiveData.setValue(eventResponse.getData().getEvents())
                                , throwable -> errorLiveData.setValue(throwable)));
    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}

