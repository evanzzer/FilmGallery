package com.leafy.filmgallery.ui.home.favorite.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.leafy.filmgallery.R
import com.leafy.filmgallery.data.MovieType
import com.leafy.filmgallery.databinding.FragmentFavoriteTvShowBinding
import com.leafy.filmgallery.ui.detail.DetailActivity
import com.leafy.filmgallery.ui.home.favorite.FavoriteAdapter
import com.leafy.filmgallery.viewmodel.ViewModelFactory

class TvShowFavFragment : Fragment() {
    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TvShowFavViewModel
    private lateinit var tvShowAdapter: FavoriteAdapter

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = tvShowAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.messageUndoAlert, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.messageUndoButton) { _ ->
                    courseEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvFavTvShow)

        if (activity != null) {
            viewModel = ViewModelProvider(
                this,
                ViewModelFactory.getInstance(requireContext())
            )[TvShowFavViewModel::class.java]

            viewModel.fetchFavoriteList()

            tvShowAdapter = FavoriteAdapter()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteList().observe(viewLifecycleOwner, { movies ->
                binding.progressBar.visibility = View.GONE
                tvShowAdapter.submitList(movies)
                tvShowAdapter.notifyDataSetChanged()
            })

            tvShowAdapter.setCallback(object : FavoriteAdapter.OnRequestDetailActivity {
                override fun onItemClicked(id: Int) {
                    val detailIntent = Intent(requireActivity(), DetailActivity::class.java)
                    detailIntent.putExtra(DetailActivity.EXTRA_MOVIE, id)
                    detailIntent.putExtra(DetailActivity.EXTRA_TYPE, MovieType.TV_SHOW_TYPE)
                    startActivity(detailIntent)
                }
            })

            with(binding.rvFavTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}