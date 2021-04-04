package com.leafy.filmgallery.ui.home.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.leafy.filmgallery.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity(), childFragmentManager)
            binding.viewPager.adapter = sectionsPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.getItem(0).isVisible = false
        menu.getItem(1).isVisible = false
        menu.getItem(2).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }
}