package be.appfoundry.aipdemo.presentation.cardlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardListComposeActivity : ComponentActivity() {

    private val viewModel: CardListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CardListScreen(viewModel = viewModel)
            }
        }
    }
}
