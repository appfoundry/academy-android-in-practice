package be.appfoundry.aipdemo.activity.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.appfoundry.aipdemo.R
import kotlinx.android.synthetic.main.activity_reactive.*

class RandomNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reactive)

        val viewModel = ViewModelProvider(this).get(RandomNumberViewModel::class.java)
        viewModel.number.observe(this, Observer { number ->
            activityLogText.text = "The number is $number"
        })

        activityLogButton.setOnClickListener {
            viewModel.generateRandom()
        }
    }
}