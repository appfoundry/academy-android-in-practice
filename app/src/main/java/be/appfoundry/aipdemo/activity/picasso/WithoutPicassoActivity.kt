package be.appfoundry.aipdemo.activity.picasso

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import be.appfoundry.aipdemo.R
import kotlinx.android.synthetic.main.activity_common.*
import java.io.IOException
import java.io.InputStream
import java.net.URL

class WithoutPicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_common)

        activityCommonButton.setOnClickListener {
            LoadImageTask(activityCommonImage).execute("https://github.com/appfoundry/academy-android-in-practice/raw/master/app/src/main/res/drawable/art2.jpg")
        }
    }

    inner class LoadImageTask(private val target: ImageView) : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String?): Bitmap {
            return try {
                val inputStream: InputStream = URL(urls[0]).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                BitmapFactory.decodeResource(resources, R.drawable.error)
            }
        }

        override fun onPostExecute(result: Bitmap?) {
            target.setImageBitmap(result)
        }
    }
}