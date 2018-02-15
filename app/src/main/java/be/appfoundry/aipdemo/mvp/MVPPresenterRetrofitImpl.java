package be.appfoundry.aipdemo.mvp;

import javax.inject.Inject;

import be.appfoundry.aipdemo.AIPDemoApplication;
import be.appfoundry.aipdemo.model.StarWarsFilm;
import be.appfoundry.aipdemo.service.SwapiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MVPPresenterRetrofitImpl implements MVPPresenter {

    @Inject
    SwapiService swapiService;

    private MVPView view;

    public MVPPresenterRetrofitImpl() {
        AIPDemoApplication.getAppComponent().inject(this);
    }

    @Override
    public void attachView(MVPView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        swapiService.getFilm(2).enqueue(new Callback<StarWarsFilm>() {
            @Override
            public void onResponse(Call<StarWarsFilm> call, Response<StarWarsFilm> response) {
                view.showStarWarsFilm(response.body());
            }

            @Override
            public void onFailure(Call<StarWarsFilm> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }

}
