package com.example.nguyenthanhtungh.moviedb.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nguyenthanhtungh.moviedb.R

class DetailFragment() : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }
}