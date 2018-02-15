package be.appfoundry.aipdemo.injection;

import javax.inject.Singleton;

import be.appfoundry.aipdemo.service.SwapiService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    SwapiService provideSwapiService(Retrofit retrofit) {
        return retrofit.create(SwapiService.class);
    }
}
