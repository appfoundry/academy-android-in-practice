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

public class WithoutButterKnifeActivity extends AppCompatActivity {

    private ScrollView scrollWrapper;
    private LinearLayout container;
    private ImageView image;
    private TextView title;
    private Button doSomething;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);

        scrollWrapper = (ScrollView) findViewById(R.id.activity_common_scroll);
        container = (LinearLayout) findViewById(R.id.activity_common_container);
        image = (ImageView) findViewById(R.id.activity_common_image);
        title = (TextView) findViewById(R.id.activity_common_title);
        doSomething = (Button) findViewById(R.id.activity_common_do_something);
        info = (TextView) findViewById(R.id.activity_common_info);

        doSomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Button clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}