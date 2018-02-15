package be.appfoundry.aipdemo.activity.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import be.appfoundry.aipdemo.AIPDemoApplication;
import be.appfoundry.aipdemo.R;
import be.appfoundry.aipdemo.model.StarWarsFilm;
import be.appfoundry.aipdemo.mvp.MVPPresenter;
import be.appfoundry.aipdemo.mvp.MVPPresenterRetrofitImpl;
import be.appfoundry.aipdemo.mvp.MVPView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MVPActivity extends AppCompatActivity implements MVPView {

    @BindView(R.id.activity_common_scroll) ScrollView scrollWrapper;
    @BindView(R.id.activity_common_container) LinearLayout container;
    @BindView(R.id.activity_common_image) ImageView image;
    @BindView(R.id.activity_common_title) TextView title;
    @BindView(R.id.activity_common_do_something) Button doSomething;
    @BindView(R.id.activity_common_info) TextView info;

    private MVPPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);

        ButterKnife.bind(this);

        AIPDemoApplication.getAppComponent().inject(this);

        presenter = new MVPPresenterRetrofitImpl();
        //presenter = new MVPPresenterDBFlowImpl();
        presenter.attachView(this);
    }

    @OnClick(R.id.activity_common_do_something)
    void onSaveClicked(View view) {
        presenter.loadData();
    }

    @Override
    public void showStarWarsFilm(StarWarsFilm starWarsFilm) {
        title.setText(starWarsFilm.getTitle());
        info.setText(starWarsFilm.getOpeningCrawl());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(MVPActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}