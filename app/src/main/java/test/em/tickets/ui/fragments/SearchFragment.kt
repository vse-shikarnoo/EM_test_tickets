package test.em.tickets.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.ChangeBounds
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import test.em.tickets.R
import test.em.tickets.databinding.FragmentSearchBinding
import test.em.tickets.ui.adapters.TicketsOffersListAdapter
import test.em.tickets.utils.autoCleared
import test.em.tickets.vm.SearchViewModel


class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchViewModel by viewModels()
    private var ticketsOffersListAdapter: TicketsOffersListAdapter by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = ChangeBounds().apply {
            duration = 500
        }

        sharedElementReturnTransition = ChangeBounds().apply {
            duration = 500
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        bindStartData()
        observe()
        listeners()
    }

    private fun initAdapter() {
        ticketsOffersListAdapter = TicketsOffersListAdapter()
        with(binding.ticketsListRv) {
            adapter = ticketsOffersListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observe() {
        viewModel.ticketsOffersList.observe(viewLifecycleOwner) {
            setErrorVisibility(true)
            ticketsOffersListAdapter.submitList(it)

        }
        viewModel.errorState.observe(viewLifecycleOwner) {
            setErrorVisibility(false)
        }
    }

    private fun bindStartData() {
        with(binding) {
            endDestinationEt.requestFocus()
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(binding.endDestinationEt, InputMethodManager.SHOW_IMPLICIT)
            startDestinationEt.setText(args.startDestination ?: "")
        }
    }

    private fun listeners() {
        with(binding) {
            deleteIv.setOnClickListener {
                endDestinationEt.setText("")
            }

            changeIv.setOnClickListener {
                var temp = ""
                temp = startDestinationEt.text.toString()
                startDestinationEt.text = endDestinationEt.text
                endDestinationEt.setText(temp)
            }

            hardRouteCv.setOnClickListener {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToWorkingFragment())
            }

            anywhereCv.setOnClickListener {
                endDestinationEt.setText("Куда угодно")
            }

            weekendsCv.setOnClickListener {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToWorkingFragment())
            }

            hotTicketsCv.setOnClickListener {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToWorkingFragment())
            }

            stambulCl.setOnClickListener {
                endDestinationEt.setText("Стамбул")
            }

            sochiCl.setOnClickListener {
                endDestinationEt.setText("Сочи")
            }

            phuketCl.setOnClickListener {
                endDestinationEt.setText("Пхукет")
            }

            endDestinationEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changeLayout(p0?.isNotBlank() ?: false)
                    viewModel.getTicketsOffers()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            filterCv.setOnClickListener {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToFilterFragment())
            }

            leftArrow.setOnClickListener {
                endDestinationEt.setText("")
            }

            loadingLayout.retryBtn.setOnClickListener {
                viewModel.getTicketsOffers()
                loadingLayout.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun changeLayout(flag: Boolean) {
        with(binding) {
            if (flag) {
                leftArrow.visibility = View.VISIBLE
                loadingFrame.visibility = View.VISIBLE
                filters.visibility = View.VISIBLE
                changeIv.visibility = View.VISIBLE
                notifyCv.visibility = View.VISIBLE
                showAllTicketsBtn.visibility = View.VISIBLE
                ticketsListCv.visibility = View.VISIBLE


                planeIv.visibility = View.GONE
                searchIv.visibility = View.GONE
                fastButtons.visibility = View.GONE
                popularDestinationsCv.visibility = View.GONE
            } else {
                fastButtons.visibility = View.VISIBLE
                popularDestinationsCv.visibility = View.VISIBLE
                planeIv.visibility = View.VISIBLE
                searchIv.visibility = View.VISIBLE

                leftArrow.visibility = View.GONE
                loadingFrame.visibility = View.GONE
                filters.visibility = View.GONE
                changeIv.visibility = View.GONE
                notifyCv.visibility = View.GONE
                showAllTicketsBtn.visibility = View.GONE
                ticketsListCv.visibility = View.GONE
            }
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