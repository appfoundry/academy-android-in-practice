package be.appfoundry.aipdemo;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandscapeWithoutPicassoActivity extends AppCompatActivity {

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
            landscapeImage.setImageBitmap(bitmap);
        }
    }

}