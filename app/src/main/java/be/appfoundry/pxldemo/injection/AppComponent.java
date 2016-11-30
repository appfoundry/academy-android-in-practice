package be.appfoundry.pxldemo.injection;

import javax.inject.Singleton;

import be.appfoundry.pxldemo.LandscapeDBFlowActivity;
import be.appfoundry.pxldemo.LandscapeDagger2Activity;
import be.appfoundry.pxldemo.LandscapeMVPActivity;
import be.appfoundry.pxldemo.LandscapeRetrofitActivity;
import be.appfoundry.pxldemo.ReactiveActivity;
import be.appfoundry.pxldemo.mvp.LandscapeMVPPresenterDBFlowImpl;
import be.appfoundry.pxldemo.mvp.LandscapeMVPPresenterRetrofitImpl;
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
