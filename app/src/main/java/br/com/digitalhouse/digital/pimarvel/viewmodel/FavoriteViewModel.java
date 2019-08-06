package br.com.digitalhouse.digital.pimarvel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.FavoriteDAO;
import br.com.digitalhouse.digital.pimarvel.model.favorite.Favorite;
import br.com.digitalhouse.digital.pimarvel.repository.FavoriteRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.isNetworkConnected;

public class FavoriteViewModel extends AndroidViewModel {

    private MutableLiveData<List<Favorite>> favoriteLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private FavoriteRepository repository = new FavoriteRepository();

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Favorite>> getFavoriteLiveData() {
        return favoriteLiveData;
    }

    public LiveData<Boolean> getFavoriteLoadingLiveData() {
        return loadingLiveData;
    }

    public LiveData<Throwable> getFavoriteErrorLiveData() {
        return errorLiveData;
    }

    public void searchFavorite() {

        if (isNetworkConnected(getApplication())) {

            // getApiFavorite();
            getLocalFavorite();
        } else {
            getLocalFavorite();
        }
    }

    private void getLocalFavorite() {
        disposable.add(
                repository.getFavoriteLocal(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(false))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(favorites -> favoriteLiveData.setValue(favorites)
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    private List<Favorite> saveItems(List<Favorite> favorites) {
        FavoriteDAO favoriteDAO = Database.getDatabase(getApplication()
                .getApplicationContext())
                .favoriteDAO();

        favoriteDAO.insertAll(favorites);
        return favorites;
    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}