package test.em.tickets.ui.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.em.tickets.R
import test.em.tickets.databinding.ItemListSuggestionsBinding
import test.em.tickets.databinding.ItemListTicketsBinding
import test.em.tickets.databinding.ItemTicketsOffersBinding
import test.em.tickets.model.Offer
import test.em.tickets.model.Ticket
import test.em.tickets.model.TicketsOffer
import test.em.tickets.utils.getFlightTime
import test.em.tickets.utils.priceRefactor

class TicketsListAdapter :
    ListAdapter<Ticket, TicketsListAdapter.Holder>(TicketsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemListTicketsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class TicketsDiffUtilCallback : DiffUtil.ItemCallback<Ticket>() {
        override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Ticket,
            newItem: Ticket
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemListTicketsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ticket: Ticket) {
            with(binding) {

                priceTv.text = root.resources.getString(
                    R.string.ticket_price,
                    priceRefactor(ticket.price.value)
                )

                if (ticket.badge != null) {
                    badgeCv.visibility = View.VISIBLE
                    badgeTv.text = ticket.badge
                }

                bonusInfoTv.visibility = if (!ticket.hasTransfer) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

                val depTime = ticket.departure.date.split("T")[1].dropLast(3)
                val arrTime = ticket.arrival.date.split("T")[1].dropLast(3)
                depAirpTv.text = ticket.departure.airport
                depTimeTv.text = depTime
                arrTimeTv.text = arrTime
                arrAirpTv.text = ticket.arrival.airport
                flightTimeTv.text = root.resources.getString(
                    R.string.flight_time,
                    getFlightTime(depTime, arrTime)
                )
            }
        }
    }
}