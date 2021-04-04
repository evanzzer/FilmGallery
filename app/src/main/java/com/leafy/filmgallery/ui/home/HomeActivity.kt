package com.leafy.filmgallery.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.leafy.filmgallery.R
import com.leafy.filmgallery.databinding.ActivityHomeBinding
import com.leafy.filmgallery.ui.home.favorite.FavoriteFragment
import com.leafy.filmgallery.ui.home.movies.MovieFragment
import com.leafy.filmgallery.ui.home.tvshows.TvShowFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]

        if (supportActionBar != null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, viewModel.currentFragment)
                    .commit()
            supportActionBar?.title = binding.bottomNav.menu.getItem(0).title
            binding.bottomNav.menu.getItem(0).isChecked = true
        }

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            viewModel.currentFragment = when(item.itemId) {
                R.id.menuMovie -> MovieFragment()
                R.id.menuTvShow -> TvShowFragment()
                R.id.menuFavorite -> FavoriteFragment()
                else -> Fragment()
            }

            try {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, viewModel.currentFragment)
                        .commit()
                supportActionBar?.title = item.title
                return@OnNavigationItemSelectedListener true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@OnNavigationItemSelectedListener false
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fragment, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionName -> return false
            R.id.actionNewest -> return false
            R.id.actionOldest -> return false
        }
        return super.onOptionsItemSelected(item)
    }
}