package com.niket.sample.feature.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.niket.sample.R
import com.niket.sample.base.helper.PaginationListener
import com.niket.sample.base.helper.PaginationListener.PAGE_START
import com.niket.sample.base.helper.ResponseWrapper
import com.niket.sample.base.view.BaseActivity
import com.niket.sample.databinding.ActivityHomeBinding
import com.niket.sample.feature.helper.hide
import com.niket.sample.feature.helper.show
import com.niket.sample.feature.model.Response
import com.niket.sample.feature.view.adapter.DataItem
import com.niket.sample.feature.view.adapter.HomeAdapter
import com.niket.sample.feature.view.adapter.IHomeAdapterItem
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
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initViewModel()
        initObservers()
        initView()
        initSwipeToRefresh()
        loadData(PAGE_START)
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
            loadData(PAGE_START)
        }
    }

    private fun initObservers() {
        viewModel.getResponseLiveData().observe(this, {
            when (it) {
                is ResponseWrapper.Success -> {
                    binding.holderSwipeRefresh.isEnabled = true
                    binding.holderSwipeRefresh.isRefreshing = false
                    binding.layoutError.root.hide()
                    binding.llLoading.hide()
                    binding.rvPosts.show()
                    handleSuccess(it.data)
                }
                is ResponseWrapper.Loading -> {
                    if (currentPage == PAGE_START && !binding.holderSwipeRefresh.isRefreshing) {
                        binding.llLoading.show()
                        binding.layoutError.root.hide()
                        binding.rvPosts.hide()
                    }
                }
                is ResponseWrapper.Error -> {
                    binding.holderSwipeRefresh.isRefreshing = false
                    binding.layoutError.tvError.text =
                        getString(R.string.generic_error_msg)
                    binding.layoutError.btnRetry.setOnClickListener { loadData(0) }
                    binding.layoutError.root.show()
                    binding.llLoading.hide()
                    binding.rvPosts.hide()
                }
            }
        })
    }

    private fun handleSuccess(data: Response?) {
        val items = mutableListOf<IHomeAdapterItem>()
        data?.let {
            if (currentPage == PAGE_START) {
                resetPagination()
                binding.rvPosts.scrollToPosition(0)
                if (data.data.isNullOrEmpty()) {
                    //empty state
                    isLastPagination = true
                    isLoadingPagination = false
                } else {
                    data.data.map { DataItem(it) }.let {
                        items.addAll(it)
                    }
                }
                adapter = HomeAdapter(items)
                adapter.addLoading()
                binding.rvPosts.adapter = adapter
            } else {
                if (data.data.isNullOrEmpty()) {
                    adapter.removeLoading()
                    isLastPagination = true
                } else {
                    if (currentPage != PAGE_START) adapter.removeLoading()
                    adapter.addItems(data.data)
                    adapter.addLoading()
                }
                isLoadingPagination = false
            }
        } ?: run {
            binding.holderSwipeRefresh.isRefreshing = false
            binding.layoutError.tvError.text =
                getString(R.string.generic_error_msg)
            binding.layoutError.root.show()
            binding.llLoading.hide()
            binding.rvPosts.hide()
        }

    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvPosts.layoutManager = layoutManager
        binding.rvPosts.addItemDecoration(
            DividerItemDecoration(
                this,
                layoutManager.orientation
            )
        )
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

    private fun resetPagination() {
        currentPage = PAGE_START
        isLastPagination = false
        isLoadingPagination = false
    }

    private fun loadData(pageNumber: Int = currentPage) {
        if (pageNumber == PAGE_START) currentPage = PAGE_START
        viewModel.changePreference(pageNumber)
    }

    override fun getScreenName() = HomeActivity::class.java.simpleName
}