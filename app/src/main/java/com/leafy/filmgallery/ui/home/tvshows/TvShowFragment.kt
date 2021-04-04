package com.leafy.filmgallery.ui.home.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.leafy.filmgallery.R
import com.leafy.filmgallery.data.MovieType
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity
import com.leafy.filmgallery.databinding.FragmentTvShowBinding
import com.leafy.filmgallery.ui.detail.DetailActivity
import com.leafy.filmgallery.ui.home.HomeViewModel
import com.leafy.filmgallery.utils.SortUtils
import com.leafy.filmgallery.viewmodel.ViewModelFactory
import com.leafy.filmgallery.vo.Resource
import com.leafy.filmgallery.vo.Status

class TvShowFragment : Fragment() {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var viewModel: TvShowViewModel
    private lateinit var mainViewModel: HomeViewModel
    private lateinit var menu: Menu

    private val observer = Observer<Resource<PagedList<TvShowEntity>>> { tvShows ->
        if (tvShows != null) {
            when(tvShows.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    tvShowAdapter.submitList(tvShows.data)
                    tvShowAdapter.notifyDataSetChanged()
                }
                Status.EMPTY, Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), tvShows.message, Toast.LENGTH_SHORT)
                            .show()
                }
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(this,
                ViewModelFactory.getInstance(requireContext()))[TvShowViewModel::class.java]

            mainViewModel = ViewModelProvider(requireActivity(),
                    ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]

            viewModel.fetchTvShowList(SortUtils.tblTvShow, mainViewModel.currentFilter)

            tvShowAdapter = TvShowAdapter()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShowList().observe(viewLifecycleOwner, { tvShows ->
                when(tvShows.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        tvShowAdapter.submitList(tvShows.data)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    Status.EMPTY, Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), tvShows.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                }
            })

            tvShowAdapter.setCallback(object: TvShowAdapter.OnRequestDetailActivity {
                override fun onItemClicked(id: Int) {
                    val detailIntent = Intent(requireActivity(), DetailActivity::class.java)
                    detailIntent.putExtra(DetailActivity.EXTRA_MOVIE, id)
                    detailIntent.putExtra(DetailActivity.EXTRA_TYPE, MovieType.TV_SHOW_TYPE)
                    startActivity(detailIntent)
                }
            })

            with(binding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        when(mainViewModel.currentFilter) {
            SortUtils.NAME -> menu.getItem(0).isChecked = true
            SortUtils.NEWEST -> menu.getItem(1).isChecked = true
            SortUtils.OLDEST -> menu.getItem(2).isChecked = true
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mainViewModel.currentFilter = when(item.itemId) {
            R.id.actionName -> SortUtils.NAME
            R.id.actionNewest -> SortUtils.NEWEST
            R.id.actionOldest -> SortUtils.OLDEST
            else -> throw Throwable("Illegal ID with code ${item.itemId}")
        }
        viewModel.fetchTvShowList(SortUtils.tblTvShow, mainViewModel.currentFilter)
        viewModel.getTvShowList().removeObserver(observer)
        viewModel.getTvShowList().observe(viewLifecycleOwner, observer)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}