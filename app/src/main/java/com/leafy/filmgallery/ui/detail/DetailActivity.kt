package com.leafy.filmgallery.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leafy.filmgallery.R
import com.leafy.filmgallery.databinding.ActivityDetailBinding
import com.leafy.filmgallery.viewmodel.ViewModelFactory
import com.leafy.filmgallery.vo.Status

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    internal lateinit var viewModel: DetailViewModel

    private var id = 0
    private var type = 0

    private var menu: Menu? = null

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            ViewModelFactory.getInstance(this)).get(DetailViewModel::class.java)

        supportActionBar?.title = resources.getString(R.string.description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extra = intent?.extras
        if (extra != null) {
            id = extra.getInt(EXTRA_MOVIE, 0)
            type = extra.getInt(EXTRA_TYPE, 0)
            if (id != 0) {
                if (viewModel.id == 0 && viewModel.type == 0) {
                    viewModel.id = id
                    viewModel.type = type
                    viewModel.fetchDetail()
                }

                viewModel.getDetail().observe(this, { detail ->
                    when (detail.status) {
                        Status.SUCCESS -> {
                            getEconomy(true)
                            val data = detail.data
                            if (data != null) {
                                binding.tvTitle.text = data.title
                                binding.tvDate.text = resources.getString(R.string.dateRelease, data.date)
                                binding.tvCategory.text = data.category
                                binding.tvDescription.text = data.description

                                Glide.with(this)
                                    .load(data.imageUrl)
                                    .apply(RequestOptions.overrideOf(160, 240))
                                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                                    .error(R.drawable.ic_baseline_broken_image_24)
                                    .into(binding.imgPoster)
                            }
                        }
                        Status.EMPTY, Status.ERROR -> {
                            getEconomy(true)
                            Toast.makeText(this, detail.message, Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }
                        Status.LOADING -> getEconomy(false)
                    }
                })
            }
        }
    }

    // When executing, the back animation in android manifest doesn't show properly on the phone
    // So this is the replacement for the back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu
        viewModel.getDetail().observe(this, { detail ->
            when (detail.status) {
                Status.SUCCESS -> {
                    getEconomy(true)
                    menu?.getItem(0)?.isVisible = true
                    menu?.getItem(1)?.isVisible = true
                    val data = detail.data
                    if (data != null)
                        setFavorite(data.favorite)
                }
                Status.EMPTY, Status.ERROR -> {
                    getEconomy(true)
                    Toast.makeText(this, detail.message, Toast.LENGTH_SHORT)
                            .show()
                }
                Status.LOADING -> getEconomy(false)
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionFavorite -> {
                viewModel.setFavorite()
                return true
            }
            R.id.actionShare -> {
                val mimeType = "text/plain"
                val category = when(type) {
                    0 -> "movie"
                    1 -> "tv"
                    else -> throw Throwable("Unknown $type")
                }
                ShareCompat.IntentBuilder.from(this).apply {
                    setType(mimeType)
                    setChooserTitle(resources.getString(R.string.shareTitle))
                    setText(resources.getString(R.string.shareDescription, category, id))
                    startChooser()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getEconomy(state: Boolean) {
        binding.tvTitle.visibility = if (state) View.VISIBLE else View.GONE
        binding.contentLayout.visibility = if (state) View.VISIBLE else View.GONE
        binding.tvDescTitle.visibility = if (state) View.VISIBLE else View.GONE
        binding.tvDescription.visibility = if (state) View.VISIBLE else View.GONE
        binding.progressBar.visibility = if (state) View.GONE else View.VISIBLE
    }

    private fun setFavorite(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.actionFavorite)
        menuItem?.icon =
            if (state) ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24)
            else ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_border_24)
    }
}