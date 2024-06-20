package test.em.tickets.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.em.tickets.R
import test.em.tickets.databinding.ItemListSuggestionsBinding
import test.em.tickets.model.Offer
import test.em.tickets.utils.priceRefactor

class OffersListAdapter : ListAdapter<Offer, OffersListAdapter.Holder>(OfferDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemListSuggestionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class OfferDiffUtilCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Offer,
            newItem: Offer
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemListSuggestionsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: Offer) {
            with(binding) {
                imageIv.setImageResource(
                    when (offer.id) {
                        1 -> R.drawable.offer_1
                        2 -> R.drawable.offer_2
                        3 -> R.drawable.offer_3
                        else -> R.drawable.close
                    }
                )
                titleTv.text = offer.title
                cityTv.text = offer.town
                priceTv.text = root.resources.getString(
                    R.string.offer_price,
                    priceRefactor(offer.price.value)
                )
            }
        }
    }
}