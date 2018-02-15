package be.appfoundry.aipdemo.activity.dbflow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import javax.inject.Inject;

import be.appfoundry.aipdemo.AIPDemoApplication;
import be.appfoundry.aipdemo.R;
import be.appfoundry.aipdemo.model.StarWarsFilm;
import be.appfoundry.aipdemo.model.StarWarsFilm_Table;
import be.appfoundry.aipdemo.service.SwapiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DBFlowActivity extends AppCompatActivity {

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
        swapiService.getFilm(2).enqueue(new Callback<StarWarsFilm>() {
            @Override
            public void onResponse(Call<StarWarsFilm> call, Response<StarWarsFilm> response) {


                StarWarsFilm serviceStarWarsFilm = response.body();
                FlowManager.getModelAdapter(StarWarsFilm.class).save(serviceStarWarsFilm);

                SQLite.select()
                        .from(StarWarsFilm.class)
                        .where(StarWarsFilm_Table.episodeId.eq(5))
                        .async()
                        .queryResultCallback(new QueryTransaction.QueryResultCallback<StarWarsFilm>() {
                            @Override
                            public void onQueryResult(QueryTransaction<StarWarsFilm> transaction, @NonNull CursorResult<StarWarsFilm> tResult) {
                                StarWarsFilm databaseStarWarsFilm = tResult.toModel();
                                if (databaseStarWarsFilm != null) {
                                    title.setText(databaseStarWarsFilm.getTitle());
                                    info.setText(databaseStarWarsFilm.getOpeningCrawl());
                                }
                            }
                        }).execute();


            }

            @Override
            public void onFailure(Call<StarWarsFilm> call, Throwable t) {
                Toast.makeText(DBFlowActivity.this, "Failed to get a film.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}