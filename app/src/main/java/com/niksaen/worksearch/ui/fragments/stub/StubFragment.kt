package com.niksaen.worksearch.ui.fragments.stub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.niksaen.worksearch.R
import com.niksaen.worksearch.databinding.FragmentStubBinding

private const val TITLE = "title"

/**
 * A stub fragment.
 * Use the [StubFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StubFragment : Fragment() {
    private var title: String? = null
    private lateinit var ui:FragmentStubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(TITLE)
        }
        ui = FragmentStubBinding.inflate(layoutInflater)
        ui.title.text = title
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = ui.root

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param title Stub fragment title.
         * @return A new instance of fragment StubFragment.
         */
        @JvmStatic
        fun newInstance(title: String) =
            StubFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                }
            }
    }
}