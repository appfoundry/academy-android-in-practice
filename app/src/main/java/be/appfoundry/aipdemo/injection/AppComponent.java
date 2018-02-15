package be.appfoundry.aipdemo.injection;

import javax.inject.Singleton;

import be.appfoundry.aipdemo.activity.dagger2.Dagger2Activity;
import be.appfoundry.aipdemo.activity.dbflow.DBFlowActivity;
import be.appfoundry.aipdemo.activity.mvp.MVPActivity;
import be.appfoundry.aipdemo.activity.retrofit.RetrofitActivity;
import be.appfoundry.aipdemo.activity.rxjava.ReactiveActivity;
import be.appfoundry.aipdemo.mvp.MVPPresenterDBFlowImpl;
import be.appfoundry.aipdemo.mvp.MVPPresenterRetrofitImpl;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ServiceModule.class})
public interface AppComponent {

    void inject(Dagger2Activity activity);
    void inject(RetrofitActivity activity);
    void inject(DBFlowActivity activity);
    void inject(MVPActivity activity);
    void inject(ReactiveActivity activity);
    void inject(MVPPresenterRetrofitImpl presenter);
    void inject(MVPPresenterDBFlowImpl presenter);
}
