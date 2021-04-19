package be.appfoundry.aipdemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.appfoundry.aipdemo.data.model.Card
import com.google.accompanist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class CardListActivity : ComponentActivity() {

    private val viewModel: CardListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CardListScreen(viewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadCards()
    }
}
