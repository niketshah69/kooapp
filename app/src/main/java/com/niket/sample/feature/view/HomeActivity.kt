package com.niket.sample.feature.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.niket.sample.R
import com.niket.sample.base.helper.PaginationListener
import com.niket.sample.base.helper.PaginationListener.PAGE_START
import com.niket.sample.base.helper.ResponseWrapper
import com.niket.sample.base.view.BaseActivity
import com.niket.sample.databinding.ActivityHomeBinding
import com.niket.sample.feature.helper.hide
import com.niket.sample.feature.helper.show
import com.niket.sample.feature.viewmodel.PostsViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var isLastPagination = false
    private var currentPage: Int = PAGE_START
    private var isLoadingPagination = false
    private var pageSize = 20
    private lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initViewModel()
        initObservers()
        initView()
        initSwipeToRefresh()
        loadData()
    }

    private fun initView() {
        initRecyclerView()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)
    }

    private fun initSwipeToRefresh() {
        binding.holderSwipeRefresh.isEnabled = false
        binding.holderSwipeRefresh.setOnRefreshListener {
            loadData(0)
        }
    }

    private fun initObservers() {
        viewModel.getResponseLiveData().observe(this, {
            when (it) {
                is ResponseWrapper.Success -> {
                    binding.holderSwipeRefresh.isEnabled = true
                    binding.holderSwipeRefresh.isRefreshing = false
                }
                is ResponseWrapper.Loading -> {
                    if (currentPage == 0 && !binding.holderSwipeRefresh.isRefreshing) {
                        binding.llLoading.show()
                        binding.layoutError.hide()
                        binding.rvPosts.hide()
                    }
                }
                is ResponseWrapper.Error -> {
                    binding.holderSwipeRefresh.isRefreshing = false
                    binding.layoutError.text =
                        getString(R.string.generic_error_msg)
                    binding.layoutError.show()
                    binding.llLoading.hide()
                    binding.rvPosts.hide()
                }
            }
        })
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvPosts.layoutManager = layoutManager
        val scrollListener = object : PaginationListener(layoutManager, pageSize) {
            override fun isLastPage(): Boolean {
                return isLastPagination
            }

            override fun loadMoreItems() {
                currentPage += 1
                isLoadingPagination = true
                loadData()
            }

            override fun isLoading(): Boolean {
                return isLoadingPagination
            }
        }
        binding.rvPosts.addOnScrollListener(scrollListener as PaginationListener)
    }

    private fun loadData(hardReload: Int = currentPage) {
        viewModel.changePreference(hardReload)
    }

    override fun getScreenName() = HomeActivity::class.java.simpleName
}