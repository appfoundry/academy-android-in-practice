package be.appfoundry.aipdemo.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.appfoundry.aipdemo.databinding.ActivityCardListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardListActivity : AppCompatActivity() {

    private val viewModel: CardListViewModel by viewModels()

    private lateinit var binding: ActivityCardListBinding

    private lateinit var cardAdapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCardListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardAdapter = CardAdapter(viewModel::cardClicked)

        binding.cardList.apply {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        viewModel.cards.observe(this) { cards ->
            cardAdapter.cards = cards.data
            Toast.makeText(
                this@CardListActivity,
                "Data from: ${cards.source}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadCards()
    }
}