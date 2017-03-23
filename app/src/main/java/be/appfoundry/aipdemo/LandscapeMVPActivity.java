package be.appfoundry.aipdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import be.appfoundry.aipdemo.database.Post;
import be.appfoundry.aipdemo.mvp.LandscapeMVPPresenter;
import be.appfoundry.aipdemo.mvp.LandscapeMVPPresenterRetrofitImpl;
import be.appfoundry.aipdemo.mvp.LandscapeMVPView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandscapeMVPActivity extends AppCompatActivity implements LandscapeMVPView {

    @BindView(R.id.landscape_scroll) ScrollView landscapeScrollWrapper;
    @BindView(R.id.landscape_container) LinearLayout landscapeContainer;
    @BindView(R.id.landscape_image) ImageView landscapeImage;
    @BindView(R.id.landscape_title) TextView landscapeTitle;
    @BindView(R.id.landscape_do_something) Button landscapeDoSomething;
    @BindView(R.id.landscape_info) TextView landscapeInfo;

    private LandscapeMVPPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landscape);

        ButterKnife.bind(this);

        AIPDemoApplication.getAppComponent().inject(this);

        //presenter = new LandscapeMVPPresenterDBFlowImpl();
        presenter = new LandscapeMVPPresenterRetrofitImpl();
        presenter.attachView(this);
    }

    @OnClick(R.id.landscape_do_something)
    void onSaveClicked(View view) {
        presenter.loadData();
    }

    @Override
    public void showPost(Post post) {
        landscapeTitle.setText(post.getTitle());
        landscapeInfo.setText(post.getBody());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(LandscapeMVPActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}