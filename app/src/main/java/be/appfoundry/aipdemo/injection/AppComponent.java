package be.appfoundry.aipdemo.injection;

import javax.inject.Singleton;

import be.appfoundry.aipdemo.LandscapeDBFlowActivity;
import be.appfoundry.aipdemo.LandscapeDagger2Activity;
import be.appfoundry.aipdemo.LandscapeMVPActivity;
import be.appfoundry.aipdemo.LandscapeRetrofitActivity;
import be.appfoundry.aipdemo.ReactiveActivity;
import be.appfoundry.aipdemo.mvp.LandscapeMVPPresenterDBFlowImpl;
import be.appfoundry.aipdemo.mvp.LandscapeMVPPresenterRetrofitImpl;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ServiceModule.class})
public interface AppComponent {

    void inject(LandscapeDagger2Activity activity);
    void inject(LandscapeRetrofitActivity activity);
    void inject(LandscapeDBFlowActivity activity);
    void inject(LandscapeMVPActivity activity);
    void inject(ReactiveActivity activity);

    void inject(LandscapeMVPPresenterRetrofitImpl presenter);
    void inject(LandscapeMVPPresenterDBFlowImpl presenter);

}
