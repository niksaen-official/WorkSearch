package com.niksaen.worksearch.ui.fragments.authorization

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat.getColor
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.niksaen.worksearch.R
import com.niksaen.worksearch.databinding.FragmentAuthorizationMainBinding
import com.niksaen.worksearch.utils.DataValidator

/**
 * Fragment where user enter mail
 * */
class MainAuthorisationFragment : Fragment() {
    private lateinit var ui:FragmentAuthorizationMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = FragmentAuthorizationMainBinding.inflate(layoutInflater)

        val btnOnClick = OnClickListener{
            if(DataValidator.emailVerification(ui.input.text.toString())){
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragmentContainerView,
                        EnterCodeFragment.newInstance(ui.input.text.toString())
                    )
                    .commitNow()
            }
            else{
                ui.input.background = getDrawable(requireContext(),R.drawable.rectangle_8dp_radius_error)
                ui.input.backgroundTintList = null
                ui.errorText.visibility = View.VISIBLE
            }
        }

        ui.input.doOnTextChanged { text, start, before, count ->
            run {
                ui.input.background = getDrawable(requireContext(),R.drawable.rectangle_8dp_radius)
                ui.input.backgroundTintList = ColorStateList.valueOf(getColor(requireContext(),R.color.gray2))
                ui.errorText.visibility = View.GONE
                if (text != null) {
                    if(text.isNotEmpty()) {
                        ui.clearBtn.visibility = View.VISIBLE
                        ui.button.backgroundTintList = ColorStateList.valueOf(getColor(requireContext(),R.color.blue))
                        ui.button.setTextColor(getColor(requireContext(),R.color.white))
                        ui.button.setOnClickListener(btnOnClick)
                    }
                    else{
                        ui.clearBtn.visibility = View.GONE
                        ui.button.backgroundTintList = ColorStateList.valueOf(getColor(requireContext(),R.color.dark_blue))
                        ui.button.setTextColor(getColor(requireContext(),R.color.gray3))
                        ui.button.setOnClickListener(null)
                    }
                }
            }
        }
        ui.clearBtn.setOnClickListener {
            ui.input.text.clear()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = ui.root
}