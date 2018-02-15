package be.appfoundry.aipdemo.activity.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import be.appfoundry.aipdemo.R;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.activity_common_scroll) ScrollView scrollWrapper;
    @BindView(R.id.activity_common_container) LinearLayout container;
    @BindView(R.id.activity_common_image) ImageView image;
    @BindView(R.id.activity_common_title) TextView title;
    @BindView(R.id.activity_common_do_something) Button doSomething;
    @BindView(R.id.activity_common_info) TextView info;

    @BindString(R.string.button_clicked) String buttonClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_common_do_something)
    void onSaveClicked(View view) {
        Toast.makeText(getApplicationContext(), buttonClicked, Toast.LENGTH_SHORT).show();
    }

}