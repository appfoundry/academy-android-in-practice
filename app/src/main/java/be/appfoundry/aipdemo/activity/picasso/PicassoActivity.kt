package be.appfoundry.aipdemo.activity.picasso

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.appfoundry.aipdemo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_common.*

class PicassoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_common)

    activity_common_do_something.setOnClickListener {
      Picasso.with(this)
          .load("https://github.com/appfoundry/academy-android-in-practice/raw/master/app/src/main/res/drawable/art2.jpg")
          //.rotate(180f)
          //.transform(GrayscaleTransformation())
          //.transform(SketchFilterTransformation(this))
          //.error(R.drawable.error)
          .into(activity_common_image)
    }
  }
}