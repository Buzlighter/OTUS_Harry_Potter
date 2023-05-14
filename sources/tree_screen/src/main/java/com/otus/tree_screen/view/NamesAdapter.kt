package com.otus.tree_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.otus.tree_screen.databinding.NameHolderBinding

class NamesAdapter(private val nameClickListener: NameClickListener) : RecyclerView.Adapter<NamesAdapter.NameHolder>() {
    private var chosenPosition = 0

    interface NameClickListener {
        fun onNameClick(position: Int)
    }

    private val differCallBack = object: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameHolder {
        val holderBinding = NameHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameHolder(holderBinding)
    }

    override fun onBindViewHolder(holder: NameHolder, position: Int) {
        val nameItem = differ.currentList[position]
        holder.itemView.setOnClickListener {
            nameClickListener.onNameClick(position)
            notifyDataSetChanged()
        }
        if (chosenPosition == position) {
            holder.itemView.setBackgroundResource(com.otus.assets.R.drawable.shape_rounded_green)
        } else {
            holder.itemView.setBackgroundResource(com.otus.assets.R.drawable.shape_rounded_grey)
        }
        holder.bind(nameItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class NameHolder(private val binding: NameHolderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(nameItem: String) {
            binding.name.text = nameItem
        }
    }

    fun setChosenPosition(position: Int) {
        chosenPosition = position
    }

    fun getChosenPosition(): Int = chosenPosition
}