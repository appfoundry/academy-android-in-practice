package be.appfoundry.aipdemo.mvp;

import javax.inject.Inject;

import be.appfoundry.aipdemo.AIPDemoApplication;
import be.appfoundry.aipdemo.database.Post;
import be.appfoundry.aipdemo.service.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandscapeMVPPresenterRetrofitImpl implements LandscapeMVPPresenter {

    @Inject PostService postService;

    private LandscapeMVPView view;

    public LandscapeMVPPresenterRetrofitImpl() {
        AIPDemoApplication.getAppComponent().inject(this);
    }

    @Override
    public void attachView(LandscapeMVPView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        postService.getPost(2).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                view.showPost(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }

}
