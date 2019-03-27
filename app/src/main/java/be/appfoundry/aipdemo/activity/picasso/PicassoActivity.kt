package be.appfoundry.aipdemo.activity.picasso

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.appfoundry.aipdemo.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation
import jp.wasabeef.picasso.transformations.gpu.SketchFilterTransformation
import kotlinx.android.synthetic.main.activity_common.*

class PicassoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_common)

    activityCommonButton.setOnClickListener {
      Picasso.with(this)
          .load("https://github.com/appfoundry/academy-android-in-practice/raw/master/app/src/main/res/drawable/art3.jpg")
          .rotate(180f)
          .transform(GrayscaleTransformation())
          .transform(SketchFilterTransformation(this))
          .error(R.drawable.error)
          .into(activityCommonImage)
    }
  }
}