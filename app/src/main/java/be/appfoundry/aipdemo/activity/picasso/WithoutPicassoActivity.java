package be.appfoundry.aipdemo.activity.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import be.appfoundry.aipdemo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithoutPicassoActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_common_do_something)
    void onSaveClicked(View view) {
        new DownloadImageTask().execute("http://media1.santabanta.com/full1/Outdoors/Landscapes/landscapes-284a.jpg");
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap image = null;

            try {
                InputStream in = new URL(url).openStream();
                image = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                image = BitmapFactory.decodeResource(getResources(), R.drawable.error);
            }

            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            image.setImageBitmap(bitmap);
        }
    }

}