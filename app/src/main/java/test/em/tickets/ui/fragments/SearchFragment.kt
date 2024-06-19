package test.em.tickets.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.ChangeTransform
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import test.em.tickets.R
import test.em.tickets.databinding.FragmentSearchBinding


class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private val args: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = ChangeTransform().apply {
            duration = 500
        }

        sharedElementReturnTransition = ChangeTransform().apply {
            duration = 500
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindStartData()

        listeners()
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
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
        }
    }

    private fun changeLayout(flag: Boolean) {
        with(binding) {
            if (flag) {
                leftArrow.visibility = View.VISIBLE
                loadingFrame.visibility = View.VISIBLE
                filters.visibility = View.VISIBLE
                changeIv.visibility = View.VISIBLE

                planeIv.visibility = View.GONE
                searchIv.visibility = View.GONE
                fastButtons.visibility = View.GONE
                popularDestinationsCv.visibility = View.GONE
            } else {
                fastButtons.visibility = View.VISIBLE
                popularDestinationsCv.visibility = View.VISIBLE
                planeIv.visibility = View.VISIBLE
                searchIv.visibility = View.VISIBLE

                changeIv.visibility = View.GONE
                leftArrow.visibility = View.GONE
                loadingFrame.visibility = View.GONE
                filters.visibility = View.GONE
            }
        }
    }
}