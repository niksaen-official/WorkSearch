package com.niksaen.worksearch.ui.modalWindows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.niksaen.worksearch.databinding.FragmentResponseModalWindowWithInputBinding

private const val TEXT = "text"

/**
 * Modal window with input field.
 *
 * Use the [ResponseModalWindowWithInputField.createWithText] factory method to
 * create with text in input field.
 * */
class ResponseModalWindowWithInputField : Fragment() {
    private var text: String? = null
    lateinit var ui: FragmentResponseModalWindowWithInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = FragmentResponseModalWindowWithInputBinding.inflate(layoutInflater)
        arguments?.let {
            text = it.getString(TEXT)
        }
        if(text != null) ui.inputField.setText(this.text)
        ui.root.setOnClickListener { closeWindow() }
        ui.window.setOnClickListener {  }
        ui.response.setOnClickListener { closeWindow() }
    }

    private fun closeWindow() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commitNow()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ui.root

    companion object {
        /**
         * Use this factory method to create a new modal window with text.
         *
         * @param text for add text in input field
         * @return A new instance of fragment WebFragment.
         */
        @JvmStatic
        fun createWithText(text: String) =
            ResponseModalWindowWithInputField().apply {
                arguments = Bundle().apply {
                    putString(TEXT, text)
                }
            }
    }
}