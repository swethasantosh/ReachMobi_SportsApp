package com.example.sports_reachmobi.viewmodel;


import com.example.sports_reachmobi.DI.DaggerApiComponent;
import com.example.sports_reachmobi.model.EventsService;
import com.example.sports_reachmobi.model.Events_List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EventsViewModel extends ViewModel {
    //observer design pattern- live data is object that generates data asynchronously :observable
    public MutableLiveData<Events_List> events = new MutableLiveData<Events_List>();


     public MutableLiveData<Boolean> sportLoadError = new MutableLiveData<Boolean>();
     public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    //private EventsService eventsService = EventsService.getInstance();
    @Inject
    public EventsService eventsService;
    private CompositeDisposable disposable = new CompositeDisposable();

    public EventsViewModel()
    {
        super();
        //DaggerApiComponent.create().inject(this);
        DaggerApiComponent.create().inject(this);

    }

    //entry point
    public void refresh()
    {
        fetchEvents();
    }
    /*public void SearchId(String searchId){
        searchSports(searchId);
    }*/
    private void fetchEvents()
    {
        //loading spinner
        // loading.setValue(true);

        //to avoid main thread blocking for response
        disposable.add(eventsService.getEvents()
                //String id = "4331";
                //disposable.add(sportsService.getSportsId(id)

                .subscribeOn(Schedulers.newThread())//creates a new thread in the background
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Events_List>() {


                    @Override
                    public void onSuccess(Events_List eventsModels)

                    {
                        events.setValue(eventsModels);

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



  /*  private void searchSports(String serachId)
    {
        //loading spinner
        loading.setValue(true);

        //to avoid main thread blocking for response
        //disposable.add(sportsService.getSports()
        //String id = "4331";
        disposable.add(eventsService.getSportsId(serachId)

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
*/



    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}