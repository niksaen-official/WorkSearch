package com.niksaen.worksearch.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.niksaen.worksearch.R
import com.niksaen.worksearch.databinding.FragmentMainPageBinding
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.ButtonListModel
import com.niksaen.worksearch.models.OffersListModel
import com.niksaen.worksearch.models.SearchFieldModel
import com.niksaen.worksearch.models.TextModel
import com.niksaen.worksearch.models.VacancyModel
import com.niksaen.worksearch.ui.adapters.delegates.buttonAdapterDelegate
import com.niksaen.worksearch.ui.adapters.delegates.offerListAdapterDelegate
import com.niksaen.worksearch.ui.adapters.delegates.searchFieldAdapterDelegate
import com.niksaen.worksearch.ui.adapters.delegates.vacanciesListHeaderAdapterDelegate
import com.niksaen.worksearch.ui.adapters.delegates.vacancyAdapterDelegate
import com.niksaen.worksearch.viewModels.MainViewModel
import org.koin.android.ext.android.inject
/**
 * A fragment where the user can search vacancy
 * */
class SearchVacancyFragment : Fragment() {

    private lateinit var ui:FragmentMainPageBinding

    private val viewModel: MainViewModel by inject()
    private val adapterItems = ArrayList<BaseModel>()
    private val adapter = ListDelegationAdapter(
        searchFieldAdapterDelegate,
        offerListAdapterDelegate {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, WebFragment.newInstance(it))
                ?.commitNow()
        },
        buttonAdapterDelegate,
        vacanciesListHeaderAdapterDelegate,
        vacancyAdapterDelegate(
            isFavoriteClick = {
                viewModel.setIsFavorite(it)
            }
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = FragmentMainPageBinding.inflate(layoutInflater)
        viewModel.loadData {
            ui.content.visibility = View.VISIBLE
            ui.progressBar.visibility = View.GONE
            val count = viewModel.vacancies.size.toString()

            adapterItems.add(SearchFieldModel("Должность, ключевые слова"))
            if (viewModel.offers.size > 0) adapterItems.add(OffersListModel(viewModel.offers))
            adapterItems.add(TextModel("Вакансии для вас"))
            adapterItems.add(VacancyModel(viewModel.vacancies[0]))
            adapterItems.add(VacancyModel(viewModel.vacancies[1]))
            adapterItems.add(VacancyModel(viewModel.vacancies[2]))
            adapterItems.add(ButtonListModel(
                when(count.last()){
                    '1' -> "Ещё 1 вакансия"
                    '2','3','4' -> "Ещё $count вакансии"
                    else -> "Ещё $count вакансий"
                }) {
                viewModel.onStop()
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainerView, MoreVacanciesFragment())
                    ?.commitNow()
            })

            adapter.items = adapterItems
            ui.content.adapter = adapter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = ui.root
}