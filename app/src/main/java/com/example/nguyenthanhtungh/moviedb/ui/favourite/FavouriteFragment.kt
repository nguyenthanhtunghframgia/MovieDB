package com.example.nguyenthanhtungh.moviedb.ui.favourite

import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nguyenthanhtungh.moviedb.R
import com.example.nguyenthanhtungh.moviedb.base.BaseFragment
import com.example.nguyenthanhtungh.moviedb.base.RecyclerItemDecoration
import com.example.nguyenthanhtungh.moviedb.data.model.Movie
import com.example.nguyenthanhtungh.moviedb.databinding.FragmentFavouriteBinding
import com.example.nguyenthanhtungh.moviedb.ui.detail.DetailFragment
import com.example.nguyenthanhtungh.moviedb.ui.main.MainActivity
import com.example.nguyenthanhtungh.moviedb.util.ITEM_DECORATION
import com.example.nguyenthanhtungh.moviedb.util.SPAN_COUNT
import org.koin.android.viewmodel.ext.android.viewModel

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding, FavouriteViewModel>(),
        SwipeRefreshLayout.OnRefreshListener {

    companion object {
        const val TAG = "FavouriteFragment"
        fun newInstance() = FavouriteFragment()
    }

    override val bindingVariable: Int = BR.favouriteViewModel
    override val viewModel by viewModel<FavouriteViewModel>()
    override val layoutId: Int = R.layout.fragment_favourite

    override fun initComponent(viewDataBinding: FragmentFavouriteBinding) {

        val fragmentFavouriteAdapter = FragmentFavouriteAdapter(
                onItemClick = {
                    goToDetailFragment(it)
                }
        )

        val decoration = RecyclerItemDecoration(ITEM_DECORATION)

        viewDataBinding.apply {
            recyclerFavourite.apply {
                adapter = fragmentFavouriteAdapter
                layoutManager = GridLayoutManager(context, SPAN_COUNT)
                addItemDecoration(decoration)
            }
        }
        viewBinding.favouriteSwipeLayout.setOnRefreshListener(this@FavouriteFragment)

        viewModel.apply {

            listFavouriteMovie.observe(this@FavouriteFragment, Observer {
                when (it.size) {
                    0 -> Toast.makeText(context, getString(R.string.no_favourite_movie), Toast.LENGTH_SHORT).show()
                }
                fragmentFavouriteAdapter.submitList(it)
            })

            getData()

            loadError.observe(this@FavouriteFragment, Observer {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            isRefresh.observe(this@FavouriteFragment, Observer {
                viewBinding.favouriteSwipeLayout.apply { isRefreshing = it == true }
            })
        }

    }

    override fun onRefresh() {
        viewModel.refresh()
    }

    private fun goToDetailFragment(it: Movie) {
        if (activity is MainActivity)
            (activity as MainActivity).apply {
                val movieDetailFragment = DetailFragment.newInstance(it)
                replaceFragment(movieDetailFragment,
                        R.id.frame_layout, DetailFragment.TAG, true)
            }
    }
}
