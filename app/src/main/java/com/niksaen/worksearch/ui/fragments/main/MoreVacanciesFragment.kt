package com.niksaen.worksearch.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.niksaen.worksearch.R
import com.niksaen.worksearch.databinding.FragmentMoreVacanciesBinding
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.VacanciesCountListHeaderModel
import com.niksaen.worksearch.ui.adapters.delegates.moreVacanciesHeaderAdapterDelegate
import com.niksaen.worksearch.ui.adapters.delegates.vacancyAdapterDelegate
import com.niksaen.worksearch.ui.modalWindows.ResponseModalWindow
import com.niksaen.worksearch.utils.ConvertToListVacancyModel
import com.niksaen.worksearch.viewModels.MainViewModel
import org.koin.android.ext.android.inject
/**
 * Fragment for displaying a large number of vacancies
 * */
class MoreVacanciesFragment : Fragment() {
    private lateinit var ui:FragmentMoreVacanciesBinding
    private val viewModel: MainViewModel by inject()
    val adapterItems = ArrayList<BaseModel>()
    val adapter = ListDelegationAdapter(
        moreVacanciesHeaderAdapterDelegate,
        vacancyAdapterDelegate(
            {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainerView, VacancyInfoFragment.newInstance(it, this))
                    ?.commitNow()
            },
            {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.modalWindowsContainer,ResponseModalWindow())
                    ?.commitNow()
            },
            {
                viewModel.setIsFavorite(it)
            }
        ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = FragmentMoreVacanciesBinding.inflate(layoutInflater)
        viewModel.synchronizedWithLocalDb()

        adapterItems.add(VacanciesCountListHeaderModel(viewModel.vacancies.size))
        adapterItems.addAll(ConvertToListVacancyModel.convert(viewModel.vacancies))

        adapter.items = adapterItems
        ui.list.adapter = adapter
        ui.backBtn.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, SearchVacancyFragment())
                ?.commitNow()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View = ui.root
}