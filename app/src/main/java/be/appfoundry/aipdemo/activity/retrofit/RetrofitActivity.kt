package be.appfoundry.aipdemo.activity.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import be.appfoundry.aipdemo.R
import be.appfoundry.aipdemo.extension.app
import be.appfoundry.aipdemo.model.StarWarsFilm
import be.appfoundry.aipdemo.service.SwapiService
import kotlinx.android.synthetic.main.activity_common.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitActivity : AppCompatActivity() {

  @Inject lateinit var swapiService: SwapiService

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    app.appComponent.inject(this)

    setContentView(R.layout.activity_common)

    activityCommonButton.setOnClickListener {
      swapiService.getFilm(7).enqueue(object : Callback<StarWarsFilm> {
        override fun onResponse(call: Call<StarWarsFilm>, response: Response<StarWarsFilm>) {
          val starWarsFilm = response.body()
          if (starWarsFilm != null) {
            activityCommonTitle.text = starWarsFilm.title
            activityCommonInfo.text = starWarsFilm.openingCrawl
          }
        }

        override fun onFailure(call: Call<StarWarsFilm>, t: Throwable) {
          Toast.makeText(this@RetrofitActivity, "Failed to get a film.", Toast.LENGTH_SHORT).show()
        }
      })
    }
  }
}