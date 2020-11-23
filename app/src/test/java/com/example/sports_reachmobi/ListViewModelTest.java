package com.example.sports_reachmobi;

import com.example.sports_reachmobi.model.SportsService;
import com.example.sports_reachmobi.model.Sports_Item_Model;
import com.example.sports_reachmobi.viewmodel.SportsListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest
{
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    SportsService sportsService;
    @InjectMocks
    SportsListViewModel sportsListViewModel = new SportsListViewModel();

    //private Single<List<Sports_Item_Model>> testSingle;
    private Single<ArrayList<Sports_Item_Model>> testSingle;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSportsSuccess()
    {
        Sports_Item_Model sports_item_model = new Sports_Item_Model("sportName","sportFormat","sportThumb");
        //ArrayList<Sports_Item_Model> sports_item_modelArrayList = new ArrayList<>();
        ArrayList<Sports_Item_Model> sportsList =new ArrayList<>();
        //sports_item_modelArrayList.add(sports_item_model);
        sportsList.add(sports_item_model);

        testSingle = Single.just(sportsList);
        //Mockito.when(sportsService.getSports()).thenReturn(testSingle);

        sportsListViewModel.refresh();
       // Assert.assertEquals(1, sportsListViewModel.sports.getValue().size());
        Assert.assertEquals(false,sportsListViewModel.sportLoadError.getValue());
        Assert.assertEquals(false,sportsListViewModel.loading.getValue());


    }
    @Test
    public void getSportsFail()
    {
        testSingle = Single.error(new Throwable());
       // Mockito.when(sportsService.getSports()).thenReturn(testSingle);
        sportsListViewModel.refresh();

        Assert.assertEquals(true,sportsListViewModel.sportLoadError.getValue());
        Assert.assertEquals(false,sportsListViewModel.loading.getValue());

    }




    @Before
    public void setupRxSchedulers()
    {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> {runnable.run();},true);
            }
        };

        //helps in unit testing without any interruption in case of new thread in the background.
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);

    }

}
