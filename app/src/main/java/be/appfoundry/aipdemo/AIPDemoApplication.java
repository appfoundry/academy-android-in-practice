package be.appfoundry.aipdemo;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import be.appfoundry.aipdemo.injection.AppComponent;
import be.appfoundry.aipdemo.injection.DaggerAppComponent;

public class AIPDemoApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).build());

        appComponent = DaggerAppComponent.builder().build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
