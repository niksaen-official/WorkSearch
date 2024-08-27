package com.niksaen.worksearch.viewModels

import androidx.lifecycle.ViewModel
import com.niksaen.worksearch.localdatamanager.LocalDbManager
import com.niksaen.worksearch.accessToApi.AccessToApi
import com.niksaen.worksearch.accessToApi.models.Offer
import com.niksaen.worksearch.accessToApi.models.Vacancy
import com.niksaen.worksearch.utils.ConvertDbEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {

    var offers:ArrayList<Offer> = ArrayList()
    var vacancies:ArrayList<Vacancy> = ArrayList()
    private val disposable = CompositeDisposable()
    private val accessToApi: AccessToApi by inject(AccessToApi::class.java)
    private val localDbManager: LocalDbManager by inject(LocalDbManager::class.java)
    var favoriteListUpdateAction:()->Unit = {}

    fun loadData(callback:()->Unit){
        if(vacancies.isEmpty()) {
            disposable += accessToApi.mokSingleton.getResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { data ->
                        offers = data.offers
                        vacancies = data.vacancies
                        for(item in vacancies){
                            if(item.isFavorite) localDbManager.addVacancy(ConvertDbEntity.fromNetworkVacancy(item))
                        }
                        synchronizedWithLocalDb()
                        callback()
                    },
                    onError = {}
                )
        }
        else{
            synchronizedWithLocalDb()
            callback()
        }
    }

    fun synchronizedWithLocalDb(){
        val favoriteVacancies = ArrayList<Vacancy>()
        for(item in localDbManager.getAll()){
            if(item.isFavorite) favoriteVacancies.add(ConvertDbEntity.toNetworkVacancy(item))
        }

        for(favoriteVacancy in favoriteVacancies){
            if (favoriteVacancy.isFavorite){
                setIsFavorite(favoriteVacancy)
            }
        }
    }

    fun getAllFavoriteVacancies():ArrayList<Vacancy>{
        val vacancies = ArrayList<Vacancy>()
        val vacancyDbEntities = localDbManager.getAll()
        for(vacancyDbEntity in vacancyDbEntities){
            vacancies.add(ConvertDbEntity.toNetworkVacancy(vacancyDbEntity))
        }
        return vacancies
    }

    fun setIsFavorite(vacancy: Vacancy){
        for(item in vacancies){
            if(item.id == vacancy.id){
                item.isFavorite = vacancy.isFavorite
                if(vacancy.isFavorite)
                    localDbManager.addVacancy(ConvertDbEntity.fromNetworkVacancy(vacancy))
                else
                    localDbManager.deleteVacancy(ConvertDbEntity.fromNetworkVacancy(vacancy))
            }
        }
        favoriteListUpdateAction()
    }

    fun onStop(){
        disposable.dispose()
    }
}