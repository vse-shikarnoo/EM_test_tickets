package test.em.tickets.ui.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.em.tickets.R
import test.em.tickets.databinding.ItemListSuggestionsBinding
import test.em.tickets.databinding.ItemTicketsOffersBinding
import test.em.tickets.model.Offer
import test.em.tickets.model.TicketsOffer
import test.em.tickets.utils.priceRefactor

class TicketsOffersListAdapter :
    ListAdapter<TicketsOffer, TicketsOffersListAdapter.Holder>(TicketsOfferDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemTicketsOffersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class TicketsOfferDiffUtilCallback : DiffUtil.ItemCallback<TicketsOffer>() {
        override fun areItemsTheSame(oldItem: TicketsOffer, newItem: TicketsOffer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TicketsOffer,
            newItem: TicketsOffer
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemTicketsOffersBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: TicketsOffer, position: Int) {
            with(binding) {
                ImageViewCompat.setImageTintList(
                    circleIv,
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            offer.color
                        )
                    )
                );
                companyTv.text = offer.title
                timeTv.text = offer.timeRange.toString().drop(1).dropLast(1)
                priceTv.text = root.resources.getString(
                    R.string.ticket_price,
                    priceRefactor(offer.price.value)
                )
            }
        }
    }
}