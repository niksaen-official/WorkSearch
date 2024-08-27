package com.niksaen.worksearch.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.niksaen.worksearch.databinding.FragmentWebBinding

/**
 * Fragment for open links
 *
 * Use the [WebFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val URL = "url"

class WebFragment : Fragment() {
    private var url: String? = null
    private lateinit var ui: FragmentWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL)
        }
        ui = FragmentWebBinding.inflate(layoutInflater)

        ui.webView.loadUrl(url.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = ui.root

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param url Url for opening
         * @return A new instance of fragment WebFragment.
         */
        @JvmStatic
        fun newInstance(url: String) =
            WebFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                }
            }
    }
}