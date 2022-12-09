package com.example.rickmortyapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rickmortyapp.R
import com.example.rickmortyapp.databinding.ItemSearchbynameBinding
import com.example.rickmortyapp.domain.model.UiCharacterModel

class FavouriteCharactersAdapter(
    private val characters: List<UiCharacterModel>,
    private val onClickListener: (UiCharacterModel) -> Unit
) :
    RecyclerView.Adapter<FavouriteCharactersAdapter.FavouriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavouriteViewHolder(
            layoutInflater.inflate(
                R.layout.item_searchbyname,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val item = characters[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = characters.size

    inner class FavouriteViewHolder(view: View) : ViewHolder(view) {
        private val binding = ItemSearchbynameBinding.bind(view)

        fun bind(
            favouriteCharacter: UiCharacterModel,
            onClickListener: (UiCharacterModel) -> Unit
        ) {
            val name = favouriteCharacter.name
            val species = favouriteCharacter.species
            val image = favouriteCharacter.image

            Glide.with(itemView)
                .load(image)
                .circleCrop()
                .into(binding.ivSearchByName)
            binding.tvSearchName.text = name
            binding.tvSearchSpecies.text = species

            itemView.setOnClickListener {
                onClickListener(favouriteCharacter)
            }
        }
    }
}