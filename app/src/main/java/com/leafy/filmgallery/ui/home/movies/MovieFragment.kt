package com.leafy.filmgallery.ui.home.movies

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
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.databinding.FragmentMoviesBinding
import com.leafy.filmgallery.ui.detail.DetailActivity
import com.leafy.filmgallery.ui.home.HomeViewModel
import com.leafy.filmgallery.utils.SortUtils
import com.leafy.filmgallery.viewmodel.ViewModelFactory
import com.leafy.filmgallery.vo.Resource
import com.leafy.filmgallery.vo.Status

class MovieFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel
    private lateinit var mainViewModel: HomeViewModel

    private val observer = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    movieAdapter.submitList(movies.data)
                    movieAdapter.notifyDataSetChanged()
                }
                Status.EMPTY, Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), movies.message, Toast.LENGTH_SHORT)
                            .show()
                }
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(this,
                ViewModelFactory.getInstance(requireContext()))[MovieViewModel::class.java]

            mainViewModel = ViewModelProvider(requireActivity(),
                    ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]

            viewModel.fetchMovieList(SortUtils.tblMovie, mainViewModel.currentFilter)

            movieAdapter = MovieAdapter()
            viewModel.getMovieList().observe(viewLifecycleOwner, observer)

            movieAdapter.setCallback(object: MovieAdapter.OnRequestDetailActivity {
                override fun onItemClicked(id: Int) {
                    val detailIntent = Intent(requireActivity(), DetailActivity::class.java)
                    detailIntent.putExtra(DetailActivity.EXTRA_MOVIE, id)
                    detailIntent.putExtra(DetailActivity.EXTRA_TYPE, MovieType.MOVIE_TYPE)
                    startActivity(detailIntent)
                }
            })

            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
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
        viewModel.fetchMovieList(SortUtils.tblMovie, mainViewModel.currentFilter)
        viewModel.getMovieList().removeObserver(observer)
        viewModel.getMovieList().observe(viewLifecycleOwner, observer)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}