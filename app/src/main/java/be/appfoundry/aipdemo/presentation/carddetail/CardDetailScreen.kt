package be.appfoundry.aipdemo.presentation.carddetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import be.appfoundry.aipdemo.R
import be.appfoundry.aipdemo.data.model.Card
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CardDetailScreen(
    navigator: DestinationsNavigator,
    card: Card
) {
    CardDetailScreen(
        card = card,
        onBackClick = navigator::popBackStack
    )
}

@Composable
private fun CardDetailScreen(
    card: Card,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = card.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            model = card.getSecureImageUrl(),
            placeholder = painterResource(id = R.drawable.card_back),
            error = painterResource(id = R.drawable.card_error),
            contentDescription = "Card image"
        )
    }
}

@Preview
@Composable
private fun CardDetailScreenPreview() {
    CardDetailScreen(
        card = Card(multiverseId = 0, name = "Name", type = "Type", imageUrl = null),
        onBackClick = { }
    )
}