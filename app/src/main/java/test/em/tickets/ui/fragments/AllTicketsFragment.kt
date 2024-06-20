package test.em.tickets.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import test.em.tickets.R
import test.em.tickets.databinding.FragmentAllTicketsBinding
import test.em.tickets.databinding.FragmentSearchBinding
import test.em.tickets.ui.adapters.TicketsListAdapter
import test.em.tickets.ui.adapters.TicketsOffersListAdapter
import test.em.tickets.utils.autoCleared
import test.em.tickets.vm.AllTicketsViewModel
import test.em.tickets.vm.SearchViewModel

class AllTicketsFragment : Fragment(R.layout.fragment_all_tickets) {
    private val binding: FragmentAllTicketsBinding by viewBinding(FragmentAllTicketsBinding::bind)
    private val args: AllTicketsFragmentArgs by navArgs()
    private val viewModel: AllTicketsViewModel by viewModels()
    private var ticketsListAdapter: TicketsListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        listeners()
        bindStartData()
        observe()

        viewModel.getTicketsOffers()
    }

    private fun observe() {
        viewModel.ticketsOffersList.observe(viewLifecycleOwner) {
            setErrorVisibility(true)
            ticketsListAdapter.submitList(it)
        }
        viewModel.errorState.observe(viewLifecycleOwner) {
            setErrorVisibility(false)
        }
    }

    private fun bindStartData() {
        with(binding) {
            flightDataTv.text = args.flightData
            flightDateTv.text = args.flightDate
        }
    }

    private fun listeners() {
        with(binding) {
            backArrowIv.setOnClickListener {
                requireActivity().onBackPressed()
            }

            loadingLayout.retryBtn.setOnClickListener {
                viewModel.getTicketsOffers()

                loadingLayout.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun initAdapter() {
        ticketsListAdapter = TicketsListAdapter()
        with(binding.ticketsListRv) {
            adapter = ticketsListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val itemDecorator =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ticket_divider
                )!!
            )
            addItemDecoration(itemDecorator)
        }
    }

    private fun setErrorVisibility(flag: Boolean) {
        if (flag) {
            binding.ticketsListRv.visibility = View.VISIBLE
            binding.loadingLayout.errorTv.visibility = View.GONE
            binding.loadingLayout.retryBtn.visibility = View.GONE

        } else {
            binding.ticketsListRv.visibility = View.GONE
            binding.loadingLayout.errorTv.visibility = View.VISIBLE
            binding.loadingLayout.retryBtn.visibility = View.VISIBLE
        }
        binding.loadingLayout.progressBar.visibility = View.GONE
    }
}