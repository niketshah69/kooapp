package com.niket.sample.base.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    private var _binding: ViewBinding? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("%s onViewCreated", getScreenName().orEmpty())
    }

    abstract fun getScreenName(): String?

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}