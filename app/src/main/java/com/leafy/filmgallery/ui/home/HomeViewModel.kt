package com.leafy.filmgallery.ui.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.leafy.filmgallery.ui.home.movies.MovieFragment
import com.leafy.filmgallery.utils.SortUtils

class HomeViewModel : ViewModel() {
    var currentFragment: Fragment = MovieFragment()
    var currentFilter: String = SortUtils.NAME
}