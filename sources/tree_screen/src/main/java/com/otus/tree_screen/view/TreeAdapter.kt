package com.otus.tree_screen.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.otus.core_api.dto.Character
import com.otus.tree_screen.R
import com.otus.tree_screen.databinding.CharHolderBinding

class TreeAdapter(private val context: Context) : RecyclerView.Adapter<TreeAdapter.TreeHolder>(){

    private val differCallBack = object: DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeHolder {
        val holderBinding = CharHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TreeHolder(holderBinding)
    }

    override fun onBindViewHolder(holder: TreeHolder, position: Int) {
        val charItem = differ.currentList[position]
        holder.bind(charItem, context)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class TreeHolder(private val binding: CharHolderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(charItem: Character, context: Context) {
            Glide
                .with(binding.root)
                .load(charItem.image)
                .error(ContextCompat.getDrawable(context, com.otus.assets.R.drawable.unknown_avatar))
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(binding.charImg)

            binding.charName.text = charItem.name
        }
    }
}