package com.niksaen.worksearch.ui.modalWindows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.replace
import com.niksaen.worksearch.R
import com.niksaen.worksearch.databinding.FragmentResponseModalWindowBinding

/**
 * Bottom modal window without input field*/

class ResponseModalWindow : Fragment() {

    lateinit var ui:FragmentResponseModalWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = FragmentResponseModalWindowBinding.inflate(layoutInflater)
        ui.root.setOnClickListener { closeWindow() }
        ui.window.setOnClickListener {  }
        ui.response.setOnClickListener { closeWindow() }
        ui.addCoverLetter.setOnClickListener { openWindowWithInputField() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ui.root

    private fun closeWindow() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commitNow()
    }
    private fun openWindowWithInputField() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.modalWindowsContainer,ResponseModalWindowWithInputField())
            ?.commitNow()
    }
}