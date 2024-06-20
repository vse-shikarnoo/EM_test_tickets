package test.em.tickets.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import test.em.tickets.R
import test.em.tickets.databinding.FragmentTicketsBinding
import test.em.tickets.ui.adapters.OffersListAdapter
import test.em.tickets.utils.autoCleared
import test.em.tickets.vm.TicketsViewModel

class MainFragment : Fragment(R.layout.fragment_tickets) {

    private val binding: FragmentTicketsBinding by viewBinding(FragmentTicketsBinding::bind)
    private val viewModel: TicketsViewModel by viewModels()
    private var offersListAdapter: OffersListAdapter by autoCleared()

    private var setting: SharedPreferences? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observe()
        listeners()
        getSP()

        viewModel.getOffers()
    }

    private fun initAdapter() {
        offersListAdapter = OffersListAdapter()
        with(binding.suggestionsRv) {
            adapter = offersListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val itemDecorator =
                DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
            itemDecorator.setDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.offer_divider
                )!!
            )
            addItemDecoration(itemDecorator)
        }
    }

    private fun listeners() {
        with(binding) {
            loadingLayout.retryBtn.setOnClickListener {
                viewModel.getOffers()
                loadingLayout.progressBar.visibility = View.VISIBLE
            }

            startDestinationEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    setting?.edit()
                        ?.putString(START_DESTINATION, p0.toString())
                        ?.apply()
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })

            endDestinationEt.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    destinations to "secondTransition"
                )
                findNavController().navigate(
                    MainFragmentDirections.actionNavigationTicketsToSearchFragment(
                        setting?.getString(
                            START_DESTINATION,
                            ""
                        )
                    ),
                    extras
                )
            }
        }
    }

    private fun observe() {
        viewModel.offersList.observe(viewLifecycleOwner) {
            setErrorVisibility(true)
            offersListAdapter.submitList(it)
        }
        viewModel.errorState.observe(viewLifecycleOwner) {
            setErrorVisibility(false)
        }
    }

    private fun setErrorVisibility(flag: Boolean) {
        if (flag) {
            binding.suggestionsRv.visibility = View.VISIBLE
            binding.loadingLayout.errorTv.visibility = View.GONE
            binding.loadingLayout.retryBtn.visibility = View.GONE

        } else {
            binding.suggestionsRv.visibility = View.GONE
            binding.loadingLayout.errorTv.visibility = View.VISIBLE
            binding.loadingLayout.retryBtn.visibility = View.VISIBLE
        }
        binding.loadingLayout.progressBar.visibility = View.GONE
    }

    private fun getSP() {
        setting = context?.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        if (setting != null && setting!!.contains(START_DESTINATION)) {
            binding.startDestinationEt.setText(setting?.getString(START_DESTINATION, ""))
        }
    }

    companion object {
        private const val START_DESTINATION = "start_destination"
        private const val SHARED_PREFERENCES = "settings"
    }
}