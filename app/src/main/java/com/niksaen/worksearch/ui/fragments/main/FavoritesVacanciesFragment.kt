package com.niksaen.worksearch.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.niksaen.worksearch.R
import com.niksaen.worksearch.databinding.FragmentFavoritesVacanciesBinding
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.VacanciesCountListHeaderModel
import com.niksaen.worksearch.ui.adapters.delegates.favoriteVacanciesHeaderAdapterDelegate
import com.niksaen.worksearch.ui.adapters.delegates.vacancyAdapterDelegate
import com.niksaen.worksearch.ui.modalWindows.ResponseModalWindow
import com.niksaen.worksearch.utils.ConvertToListVacancyModel
import com.niksaen.worksearch.viewModels.MainViewModel
import org.koin.android.ext.android.inject

/**
 * Fragment for displaying a favorite of vacancies
 * */

class FavoritesVacanciesFragment : Fragment() {

    private lateinit var ui:FragmentFavoritesVacanciesBinding

    private val viewModel: MainViewModel by inject()
    private val adapterItems = ArrayList<BaseModel>()
    private val adapter = ListDelegationAdapter(
        favoriteVacanciesHeaderAdapterDelegate,
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
                ?.replace(R.id.modalWindowsContainer, ResponseModalWindow())
                ?.commitNow()
        },
        {
            viewModel.setIsFavorite(it)
            updateList()
        }
    ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = FragmentFavoritesVacanciesBinding.inflate(layoutInflater)
        updateList()
    }

    private fun updateList(){
        adapterItems.clear()
        adapterItems.add(VacanciesCountListHeaderModel(viewModel.getAllFavoriteVacancies().size))
        adapterItems.addAll(ConvertToListVacancyModel.convert(viewModel.getAllFavoriteVacancies()))
        adapter.items = adapterItems
        ui.listVacancies.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = ui.root
}