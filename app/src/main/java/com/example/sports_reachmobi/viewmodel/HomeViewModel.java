package com.example.sports_reachmobi.viewmodel;

import com.example.sports_reachmobi.DI.DaggerApiComponent;
import com.example.sports_reachmobi.model.HomeNavigator;
import com.example.sports_reachmobi.model.SportsService;
import com.example.sports_reachmobi.model.Sports_Item_Model;
import com.example.sports_reachmobi.model.Sports_List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel
{


    private HomeNavigator navigator;

    public void setNavigator(HomeNavigator navigator)
    {
        this.navigator = navigator;
    }



    //observer design pattern- live data is object that generates data asynchronously :observable
    public MutableLiveData<Sports_List> sports = new MutableLiveData<Sports_List>();
    //public MutableLiveData<Boolean> sportLoadError = new MutableLiveData<Boolean>();
   // public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    //private SportsService sportsService = SportsService.getInstance();
    @Inject
    public SportsService sportsService;
    private CompositeDisposable disposable = new CompositeDisposable();

    public HomeViewModel()
    {
        super();
        //DaggerApiComponent.create().inject(this);
        DaggerApiComponent.create().inject(this);

    }

    //entry point
    public void refresh()
    {
        fetchSports();
    }
    /* public void SearchId(String searchId){
         searchSports(searchId);
     }*/
    private void fetchSports()
    {
        //loading spinner
        //loading.setValue(true);

        //to avoid main thread blocking for response
        disposable.add(sportsService.getSports()
                //String id = "4331";
                //disposable.add(sportsService.getSportsId(id)

                .subscribeOn(Schedulers.newThread())//creates a new thread in the background
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Sports_List>() {

                    @Override
                    public void onSuccess(Sports_List sportsModels)

                    {
                        sports.setValue(sportsModels);

                       // sportLoadError.setValue(false);
                        //loading.setValue(false);

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        //sportLoadError.setValue(true);
                        //loading.setValue(false);
                        e.printStackTrace();

                    }
                }));
//

    }


  public void itemClick(Sports_Item_Model sports_item_model)
  {
      navigator.onItemClick(sports_item_model );

  }



    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}