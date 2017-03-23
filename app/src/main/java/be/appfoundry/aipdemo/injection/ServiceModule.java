package be.appfoundry.aipdemo.injection;

import javax.inject.Singleton;

import be.appfoundry.aipdemo.service.PostService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class ServiceModule {

    @Provides @Singleton Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides @Singleton PostService provideUserService(Retrofit retrofit) {
        return retrofit.create(PostService.class);
    }

}
