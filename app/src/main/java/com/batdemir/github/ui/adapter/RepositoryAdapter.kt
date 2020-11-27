package com.batdemir.github.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batdemir.github.R
import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.databinding.ItemRepositoryBinding

class RepositoryAdapter(private val itemListener: ItemListener) :
    ListAdapter<RepositoryModel, RepositoryAdapter.RepositoryViewHolder>(Companion) {

    class RepositoryViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ItemListener {
        fun onClick(model: RepositoryModel)
    }

    companion object : DiffUtil.ItemCallback<RepositoryModel>() {
        override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: RepositoryModel,
            newItem: RepositoryModel
        ): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemRepositoryBinding>(
                layoutInflater,
                R.layout.item_repository,
                parent,
                false
            )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.binding.model = current
        holder.binding.root.setOnClickListener {
            itemListener.onClick(current)
        }
        holder.binding.executePendingBindings()
    }
}