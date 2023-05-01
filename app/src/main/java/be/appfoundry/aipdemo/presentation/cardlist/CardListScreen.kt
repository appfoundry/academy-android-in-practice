package be.appfoundry.aipdemo.presentation.cardlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.appfoundry.aipdemo.R
import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.data.model.Data
import be.appfoundry.aipdemo.presentation.destinations.CardDetailScreenDestination
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun CardListScreen(
    navigator: DestinationsNavigator,
    viewModel: CardListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CardListScreen(
        uiState = uiState,
        onCardClick = { card -> navigator.navigate(CardDetailScreenDestination(card)) },
        onRefreshClick = { viewModel.loadCards() }
    )
}

@Composable
private fun CardListScreen(
    uiState: CardListState,
    onRefreshClick: () -> Unit,
    onCardClick: (Card) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "AIP Demo") },
                actions = {
                    IconButton(onClick = onRefreshClick) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                if (uiState.hasError) {
                    InfoText(text = stringResource(id = R.string.error_loading_cards), Color.Red)
                }
                uiState.source?.let { source ->
                    InfoText(
                        text = stringResource(id = R.string.info_cards_source, source),
                        Color.Gray
                    )
                }
                uiState.cards?.let { cards ->
                    CardList(
                        cards = cards,
                        onCardClicked = onCardClick
                    )
                }
            }
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x33000000)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun CardList(
    cards: List<Card>,
    onCardClicked: (card: Card) -> Unit
) {
    LazyColumn {
        cards.forEach { card ->
            item {
                CardItem(
                    modifier = Modifier.clickable(onClick = { onCardClicked(card) }),
                    name = card.name,
                    type = card.type,
                    imageUrl = card.getSecureImageUrl()
                )
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun CardItem(name: String, type: String, imageUrl: String?, modifier: Modifier = Modifier) {
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

@Composable
private fun InfoText(
    text: String,
    backgroundColor: Color,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(8.dp),
        text = text,
        color = Color.White
    )
}

@Preview
@Composable
private fun CardListScreenPreview() {
    CardListScreen(
        uiState = CardListState(
            isLoading = true,
            cards = (0..10).map {
                Card(multiverseId = 0, name = "Name $it", type = "Type $it", imageUrl = null)
            },
            source = Data.Source.NETWORK,
            hasError = true
        ),
        onRefreshClick = { },
        onCardClick = { }
    )
}