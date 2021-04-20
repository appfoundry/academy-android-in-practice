package be.appfoundry.aipdemo.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
        CardList(cards = cards, viewModel::cardClicked)
    }
}

@Composable
fun CardList(cards: List<Card>, onCardClicked: (card: Card) -> Unit) {
    LazyColumn {
        cards.forEach { card ->
            item {
                CardItem(
                    modifier = Modifier.clickable(onClick = { onCardClicked(card) }),
                    card.name, card.type,
                    card.getSecureImageUrl()
                )
            }
        }
    }
}

@Composable
fun CardItem(modifier: Modifier, name: String, type: String, imageUrl: String?) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (imageUrl != null) {
            CoilImage(
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
                data = imageUrl,
                loading = {
                    Image(
                        painter = painterResource(R.drawable.card_back),
                        contentDescription = "Loading image"
                    )
                },
                contentDescription = "Card image"
            )
        } else {
            Image(
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
                painter = painterResource(R.drawable.card_error),
                contentDescription = "Error image"
            )
        }
        Column {
            Text(text = name)
            Text(text = type)
        }
    }
}