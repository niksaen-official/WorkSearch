package com.niksaen.worksearch.ui.fragments.authorization

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.niksaen.worksearch.R
import com.niksaen.worksearch.databinding.FragmentEnterCodeBinding
import com.niksaen.worksearch.ui.fragments.main.SearchVacancyFragment

private const val EMAIL = "EMAIL"
/**
 * Fragment where user enter code from mail
 * */
class EnterCodeFragment : Fragment() {
    private var email: String? = null
    private lateinit var ui: FragmentEnterCodeBinding

    private var code = ArrayList<String>()
    private var activeBtn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(EMAIL)
        }
        ui = FragmentEnterCodeBinding.inflate(layoutInflater)
        val previewText = ui.previewText.text.toString()+email
        ui.previewText.text = previewText

        ui.digit1.doOnTextChanged { text, start, before, count -> onTextChangeAction(0,text) }
        ui.digit2.doOnTextChanged { text, start, before, count -> onTextChangeAction(1,text) }
        ui.digit3.doOnTextChanged { text, start, before, count -> onTextChangeAction(2,text) }
        ui.digit4.doOnTextChanged { text, start, before, count -> onTextChangeAction(3,text) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = ui.root

    private fun onTextChangeAction(pos:Int,text:CharSequence?){
        if(text != null){
            if(text.length == 1){
                code.add(pos,text.toString())
            }
            else{
                code.removeAt(pos)
            }
            activeBtn = code.size == 4
        }
        else activeBtn = false
        activateBtn()
    }

    private fun activateBtn(){
        if(activeBtn){
            ui.confirm.backgroundTintList = ColorStateList.valueOf(getColor(requireContext(),R.color.blue))
            ui.confirm.setTextColor(getColor(requireContext(),R.color.white))
            ui.confirm.setOnClickListener {
                activity?.supportFragmentManager
                    ?.beginTransaction()?.replace(
                        R.id.fragmentContainerView,
                        SearchVacancyFragment()
                    )
                    ?.commitNow()
            }
        }
        else{
            ui.confirm.backgroundTintList = ColorStateList.valueOf(getColor(requireContext(),R.color.dark_blue))
            ui.confirm.setTextColor(getColor(requireContext(),R.color.gray3))
            ui.confirm.setOnClickListener(null)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param email User email.
         * @return A new instance of fragment EnterCodeFragment.
         */
        @JvmStatic
        fun newInstance(email: String) =
            EnterCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(EMAIL, email)
                }
            }
    }
}