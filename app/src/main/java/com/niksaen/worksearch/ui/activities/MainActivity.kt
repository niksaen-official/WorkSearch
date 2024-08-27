package com.niksaen.worksearch.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.niksaen.worksearch.R
import com.niksaen.worksearch.ui.fragments.authorization.EnterCodeFragment
import com.niksaen.worksearch.ui.fragments.authorization.MainAuthorisationFragment
import com.niksaen.worksearch.ui.fragments.main.FavoritesVacanciesFragment
import com.niksaen.worksearch.ui.fragments.main.SearchVacancyFragment
import com.niksaen.worksearch.ui.fragments.stub.StubFragment
import com.niksaen.worksearch.viewModels.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuView = findViewById<BottomNavigationView>(R.id.menuView)
        val badgeDrawable = menuView.getOrCreateBadge(R.id.favorite)
        badgeDrawable.badgeTextColor = getColor(R.color.white)
        badgeDrawable.backgroundColor = getColor(R.color.red)
        badgeDrawable.isVisible = false

        menuView.setOnItemSelectedListener {
            if(supportFragmentManager.fragments.last() is MainAuthorisationFragment || supportFragmentManager.fragments.last() is EnterCodeFragment) {
                false
            }
            else {
                when (it.itemId) {
                    R.id.search -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, SearchVacancyFragment())
                            .commitNow()
                        true
                    }
                    R.id.favorite -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, FavoritesVacanciesFragment())
                            .commitNow()
                        true
                    }
                    R.id.response -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, StubFragment.newInstance("Отклики"))
                            .commitNow()
                        true
                    }
                    R.id.messages -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, StubFragment.newInstance("Сообщения"))
                            .commitNow()
                        true
                    }
                    R.id.profile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, StubFragment.newInstance("Профиль"))
                            .commitNow()
                        true
                    }

                    else -> false
                }
            }
        }
        viewModel.favoriteListUpdateAction = {
            val count = viewModel.getAllFavoriteVacancies().size
            if(count > 0) {
                badgeDrawable.isVisible = true
                badgeDrawable.number = count
            }
            else{
                badgeDrawable.isVisible = false
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}