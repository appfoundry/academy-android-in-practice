package be.appfoundry.aipdemo.presentation.cardlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.appfoundry.aipdemo.R
import be.appfoundry.aipdemo.data.model.Card
import be.appfoundry.aipdemo.databinding.ItemCardBinding
import be.appfoundry.aipdemo.presentation.whenClicked
import coil.load
import kotlin.properties.Delegates

class CardAdapter(private val cardClickListener: (card: Card) -> Unit) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var cards: List<Card> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding).whenClicked { position ->
            cardClickListener.invoke(cards[position])
        }
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int = cards.size

    class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.itemCardName.text = card.name
            binding.itemCardType.text = card.type
            if(card.getSecureImageUrl() != null) {
                binding.itemCardImage.load(card.getSecureImageUrl()) {
                    placeholder(R.drawable.card_back)
                    error(R.drawable.card_error)
                    crossfade(true)
                }
            } else {
                binding.itemCardImage.load(R.drawable.card_error)
            }
        }
    }
}
