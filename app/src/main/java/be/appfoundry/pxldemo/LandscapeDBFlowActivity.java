package be.appfoundry.pxldemo;

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

import be.appfoundry.pxldemo.database.Post;
import be.appfoundry.pxldemo.database.Post_Table;
import be.appfoundry.pxldemo.service.PostService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandscapeDBFlowActivity extends AppCompatActivity {

    @BindView(R.id.landscape_scroll) ScrollView landscapeScrollWrapper;
    @BindView(R.id.landscape_container) LinearLayout landscapeContainer;
    @BindView(R.id.landscape_image) ImageView landscapeImage;
    @BindView(R.id.landscape_title) TextView landscapeTitle;
    @BindView(R.id.landscape_do_something) Button landscapeDoSomething;
    @BindView(R.id.landscape_info) TextView landscapeInfo;

    @Inject PostService postService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landscape);

        ButterKnife.bind(this);

        PXLDemoApplication.getAppComponent().inject(this);
    }

    @OnClick(R.id.landscape_do_something)
    void onSaveClicked(View view) {
        postService.getPost(2).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post servicePost = response.body();
                FlowManager.getModelAdapter(Post.class).save(servicePost);

                SQLite.select()
                        .from(Post.class)
                        .where(Post_Table.id.eq(2))
                        .async()
                        .queryResultCallback(new QueryTransaction.QueryResultCallback<Post>() {
                            @Override
                            public void onQueryResult(QueryTransaction<Post> transaction, @NonNull CursorResult<Post> tResult) {
                                Post databasePost = tResult.toModel();
                                landscapeTitle.setText(databasePost.getTitle());
                                landscapeInfo.setText(databasePost.getBody());
                            }
                        }).execute();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(LandscapeDBFlowActivity.this, "Failed to get a post.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}