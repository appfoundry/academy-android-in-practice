package be.appfoundry.aipdemo.activity.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import be.appfoundry.aipdemo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandscapePicassoActivity extends AppCompatActivity {

    @BindView(R.id.landscape_scroll) ScrollView landscapeScrollWrapper;
    @BindView(R.id.landscape_container) LinearLayout landscapeContainer;
    @BindView(R.id.landscape_image) ImageView landscapeImage;
    @BindView(R.id.landscape_title) TextView landscapeTitle;
    @BindView(R.id.landscape_do_something) Button landscapeDoSomething;
    @BindView(R.id.landscape_info) TextView landscapeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landscape);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.landscape_do_something)
    void onSaveClicked(View view) {
        Picasso.with(getApplicationContext())
                .load("http://media1.santabanta.com/full1/Outdoors/Landscapes/landscapes-284a.jpg")
                //.rotate(180)
                //.transform(new GrayscaleTransformation())
                //.transform(new SketchFilterTransformation(getBaseContext()))
                //.error(R.drawable.error)
                .into(landscapeImage);
    }

}