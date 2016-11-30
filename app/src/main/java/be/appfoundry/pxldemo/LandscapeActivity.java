package be.appfoundry.pxldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class LandscapeActivity extends AppCompatActivity {

    private ScrollView landscapeScrollWrapper;
    private LinearLayout landscapeContainer;
    private ImageView landscapeImage;
    private TextView landscapeTitle;
    private Button landscapeDoSomething;
    private TextView landscapeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landscape);

        landscapeScrollWrapper = (ScrollView) findViewById(R.id.landscape_scroll);
        landscapeContainer = (LinearLayout) findViewById(R.id.landscape_container);
        landscapeImage = (ImageView) findViewById(R.id.landscape_image);
        landscapeTitle = (TextView) findViewById(R.id.landscape_title);
        landscapeDoSomething = (Button) findViewById(R.id.landscape_do_something);
        landscapeInfo = (TextView) findViewById(R.id.landscape_info);

        landscapeDoSomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Button clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}