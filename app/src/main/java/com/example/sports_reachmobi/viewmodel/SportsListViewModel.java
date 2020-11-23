package com.example.sports_reachmobi.viewmodel;

import com.example.sports_reachmobi.DI.DaggerApiComponent;
import com.example.sports_reachmobi.model.SportsService;
import com.example.sports_reachmobi.model.Sports_List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SportsListViewModel extends ViewModel
{
    //observer design pattern- live data is object that generates data asynchronously :observable
    public MutableLiveData<Sports_List> sports = new MutableLiveData<Sports_List>();
    public MutableLiveData<Boolean> sportLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    //private SportsService sportsService = SportsService.getInstance();
    @Inject
    public SportsService sportsService;
    private CompositeDisposable disposable = new CompositeDisposable();

    public SportsListViewModel()
    {
        super();
        DaggerApiComponent.create().inject(this);

    }

    //entry point
    public void refresh()
    {
        fetchSports();
    }
    private void fetchSports()
    {
        //loading spinner
        loading.setValue(true);

        //to avoid main thread blocking for response
        disposable.add(sportsService.getSports()
        .subscribeOn(Schedulers.newThread())//creates a new thread in the background
        .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Sports_List>() {

        @Override
        public void onSuccess(Sports_List sportsModels)

        {
            sports.setValue(sportsModels);

            sportLoadError.setValue(false);
                loading.setValue(false);

            }

            @Override
            public void onError(Throwable e)
            {
                sportLoadError.setValue(true);
                loading.setValue(false);
                e.printStackTrace();

            }
        }));
//

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
