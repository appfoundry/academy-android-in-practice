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

public class Dagger2Activity extends AppCompatActivity {

    @BindView(R.id.activity_common_scroll)
    ScrollView scrollWrapper;
    @BindView(R.id.activity_common_container)
    LinearLayout container;
    @BindView(R.id.activity_common_image)
    ImageView image;
    @BindView(R.id.activity_common_title)
    TextView title;
    @BindView(R.id.activity_common_do_something)
    Button doSomething;
    @BindView(R.id.activity_common_info)
    TextView info;

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);

        ButterKnife.bind(this);

        AIPDemoApplication.getAppComponent().inject(this);
    }

    @OnClick(R.id.activity_common_do_something)
    void onSaveClicked(View view) {
        Toast.makeText(getApplicationContext(), "The user's name is: " + user.getName(), Toast.LENGTH_SHORT).show();
    }

}