package be.appfoundry.aipdemo.presentation.cardlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.appfoundry.aipdemo.R
import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.data.model.Data
import coil.compose.AsyncImage

@Composable
fun CardListScreen(viewModel: CardListViewModel) {

    val cards by viewModel.cards.observeAsState(Data(emptyList(), Data.Source.UNKNOWN))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "AIP Demo")
                }
            )
        }
    ) {
        CardList(cards = cards.data, viewModel::cardClicked)
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
                Divider(color = Color.LightGray, thickness = 1.dp)
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
            AsyncImage(
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
                model = imageUrl,
                placeholder = painterResource(id = R.drawable.card_back),
                error = painterResource(id = R.drawable.card_error),
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
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(text = type)
        }
    }
}
