package be.appfoundry.aipdemo.activity.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import be.appfoundry.aipdemo.AIPDemoApplication;
import be.appfoundry.aipdemo.R;
import be.appfoundry.aipdemo.model.StarWarsFilm;
import be.appfoundry.aipdemo.service.SwapiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    @BindView(R.id.activity_common_scroll) ScrollView scrollWrapper;
    @BindView(R.id.activity_common_container) LinearLayout container;
    @BindView(R.id.activity_common_image) ImageView image;
    @BindView(R.id.activity_common_title) TextView title;
    @BindView(R.id.activity_common_do_something) Button doSomething;
    @BindView(R.id.activity_common_info) TextView info;

    @Inject SwapiService swapiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);

        ButterKnife.bind(this);

        AIPDemoApplication.getAppComponent().inject(this);
    }

    @OnClick(R.id.activity_common_do_something)
    void onSaveClicked(View view) {
        swapiService.getFilm(1).enqueue(new Callback<StarWarsFilm>() {
            @Override
            public void onResponse(Call<StarWarsFilm> call, Response<StarWarsFilm> response) {
                StarWarsFilm starWarsFilm = response.body();
                title.setText(starWarsFilm.getTitle());
                info.setText(starWarsFilm.getOpeningCrawl());
            }

            @Override
            public void onFailure(Call<StarWarsFilm> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "Failed to get a film.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}