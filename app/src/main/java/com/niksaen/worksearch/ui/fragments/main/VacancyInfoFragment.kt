package com.niksaen.worksearch.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.niksaen.worksearch.R
import com.niksaen.worksearch.databinding.FragmentVacancieInfoBinding
import com.niksaen.worksearch.accessToApi.models.Vacancy
import com.niksaen.worksearch.ui.adapters.QuestionAdapter
import com.niksaen.worksearch.viewModels.MainViewModel
import com.niksaen.worksearch.ui.modalWindows.ResponseModalWindow
import com.niksaen.worksearch.ui.modalWindows.ResponseModalWindowWithInputField
import org.koin.android.ext.android.inject

/**
 * Fragment for displaying detailed information about a vacancy.
 *
 * Use the [WebFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VacancyInfoFragment : Fragment() {

    private lateinit var vacancy:Vacancy
    private lateinit var prevScreen:Fragment
    private lateinit var ui:FragmentVacancieInfoBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = FragmentVacancieInfoBinding.inflate(layoutInflater)
        ui.title.text = vacancy.title
        ui.salary.text = vacancy.salary.full
        ui.experienceView.text = ui.experienceView.text.toString() + vacancy.experience.text
        ui.shedulers.text = vacancy.schedules.toString()
            .replaceFirst(vacancy.schedules?.get(0)!![0], vacancy.schedules?.get(0)!![0].uppercaseChar())
            .replace("[","")
            .replace("]","")
        val appliedNumber = vacancy.appliedNumber.toString()
        val lookingNumber = vacancy.lookingNumber.toString()
        ui.appliedNumber.text =
            when(appliedNumber.last()){
                '2','3','4' -> "$appliedNumber человека \nуже откликнулись"
                else -> "$appliedNumber человек \nуже откликнулись"
            }

        ui.lookingNumber.text =
            when(lookingNumber.last()){
                '2','3','4' -> "$lookingNumber человека \nсейчас смотрят"
                else -> "$lookingNumber человек \nсейчас смотрят"
            }

        ui.company.text = vacancy.company
        ui.isFavorite.isChecked = vacancy.isFavorite
        ui.address.text = "${vacancy.address.town},${vacancy.address.street},${vacancy.address.house}"
        ui.description.text = if(vacancy.description == null) "" else vacancy.description
        ui.responsibilities.text = vacancy.responsibilities
        ui.questionsList.adapter = QuestionAdapter(vacancy.questions!!){
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.modalWindowsContainer,ResponseModalWindowWithInputField.createWithText(it))
                ?.commitNow()
        }

        ui.backBtn.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,prevScreen)
                ?.commitNow()
        }

        ui.isFavorite.setOnClickListener {
            vacancy.isFavorite = ui.isFavorite.isChecked
            viewModel.setIsFavorite(vacancy)
        }
        ui.response.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.modalWindowsContainer, ResponseModalWindow())
                ?.commitNow()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = ui.root

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param vacancy current vacancy
         * @param prevScreen previous screen
         * @return A new instance of fragment EnterCodeFragment.
         */
        @JvmStatic
        fun newInstance(vacancy: Vacancy,prevScreen:Fragment): VacancyInfoFragment {
            val fragment = VacancyInfoFragment()
            fragment.vacancy = vacancy
            fragment.prevScreen = prevScreen
            return fragment
        }
    }
}