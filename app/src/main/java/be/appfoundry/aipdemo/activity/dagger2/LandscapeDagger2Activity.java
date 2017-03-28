package be.appfoundry.aipdemo.activity.dagger2;

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
import be.appfoundry.aipdemo.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandscapeDagger2Activity extends AppCompatActivity {

    @BindView(R.id.landscape_scroll) ScrollView landscapeScrollWrapper;
    @BindView(R.id.landscape_container) LinearLayout landscapeContainer;
    @BindView(R.id.landscape_image) ImageView landscapeImage;
    @BindView(R.id.landscape_title) TextView landscapeTitle;
    @BindView(R.id.landscape_do_something) Button landscapeDoSomething;
    @BindView(R.id.landscape_info) TextView landscapeInfo;

    @Inject User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landscape);

        ButterKnife.bind(this);

        AIPDemoApplication.getAppComponent().inject(this);
    }

    @OnClick(R.id.landscape_do_something)
    void onSaveClicked(View view) {
        Toast.makeText(getApplicationContext(), "The user's name is: " + user.getName(), Toast.LENGTH_SHORT).show();
    }

}