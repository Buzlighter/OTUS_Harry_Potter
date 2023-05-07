package com.otus.characters_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.otus.characters_screen.databinding.CharactersHolderBinding
import com.otus.core_api.dto.Character

class CharactersAdapter(): RecyclerView.Adapter<CharactersAdapter.CharacterHolder>(){

    private val differCallBack = object: DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
           return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val holderBinding = CharactersHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(holderBinding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val charItem = differ.currentList[position]
        holder.bind(charItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class CharacterHolder(private val binding: CharactersHolderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(charItem: Character) {
            Glide
                .with(binding.root)
                .load(charItem.image)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(binding.avatar)

            binding.name.text = charItem.name
        }
    }
}