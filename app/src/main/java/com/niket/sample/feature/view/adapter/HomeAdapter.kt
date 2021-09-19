package com.niket.sample.feature.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.niket.sample.base.view.BaseViewHolder
import com.niket.sample.databinding.ItemPostBinding
import com.niket.sample.databinding.ItemProgressBinding
import com.niket.sample.feature.helper.ClickListener
import com.niket.sample.feature.model.PostResponseItem

class HomeAdapter(
    private val data: List<IHomeAdapterItem>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {

        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_LOADING -> ProgressViewHolder(
                ItemProgressBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> {
                PostItemViewHolder(
                    ItemPostBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is LoaderItem -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_NORMAL
        }
    }

    private fun getItem(position: Int): IHomeAdapterItem? {
        return data.getOrNull(position)
    }

    inner class ProgressViewHolder(binding: ItemProgressBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {}
    }

    inner class PostItemViewHolder(val binding: ItemPostBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            val postItem = (getItem(position) as? DataItem)?.postItem
            binding.tvTitle.text = postItem?.title
            binding.tvDescription.text =
                postItem?.body
            binding.root.setOnClickListener {
                clickListener.invoke(postItem)
            }
        }
    }

}

interface IHomeAdapterItem

class LoaderItem : IHomeAdapterItem

class DataItem(val postItem: PostResponseItem) : IHomeAdapterItem