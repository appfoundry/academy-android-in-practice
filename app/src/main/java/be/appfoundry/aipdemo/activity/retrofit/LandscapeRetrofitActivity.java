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
import be.appfoundry.aipdemo.database.Post;
import be.appfoundry.aipdemo.service.PostService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandscapeRetrofitActivity extends AppCompatActivity {

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

        AIPDemoApplication.getAppComponent().inject(this);
    }

    @OnClick(R.id.landscape_do_something)
    void onSaveClicked(View view) {
        postService.getPost(2).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
                landscapeTitle.setText(post.getTitle());
                landscapeInfo.setText(post.getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(LandscapeRetrofitActivity.this, "Failed to get a post.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}