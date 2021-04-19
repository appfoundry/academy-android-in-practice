package be.appfoundry.aipdemo.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import be.appfoundry.aipdemo.R
import be.appfoundry.aipdemo.data.model.Card
import com.google.accompanist.coil.CoilImage

@Composable
fun CardListScreen(viewModel: CardListViewModel) {
    val cards: List<Card> by viewModel.cards.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "AIP Demo")
                }
            )
        }
    ) {
        CardList(cards = cards)
    }
}

@Composable
fun CardList(cards: List<Card>) {
    LazyColumn {
        cards.forEach { card ->
            item {
                CardItem(card.name, card.type, card.getSecureImageUrl())
            }
        }
    }
}

@Composable
fun CardItem(name: String, type: String, imageUrl: String?) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(imageUrl != null) {
            CoilImage(
                data = imageUrl,
                loading = {
                    Image(
                        painter = painterResource(R.drawable.card_back),
                        contentDescription = "Loading image"
                    )
                },
                contentDescription = "Card image",
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
            )
        }
        Column {
            Text(text = name)
            Text(text = type)
        }
    }
}