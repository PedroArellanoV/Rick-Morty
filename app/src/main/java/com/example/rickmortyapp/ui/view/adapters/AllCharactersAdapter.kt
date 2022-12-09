package com.example.rickmortyapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmortyapp.R
import com.example.rickmortyapp.databinding.ItemAllcharactersBinding
import com.example.rickmortyapp.domain.model.UiCharacterModel

class AllCharactersAdapter(
    private val characters: List<UiCharacterModel>,
    private val isFavourite: (Int) -> Boolean,
    private val onClickListener: (UiCharacterModel) -> Unit,
    private val onCheckedBox: (UiCharacterModel) -> Unit,
    private val onUncheckedBox: (UiCharacterModel) -> Unit
) :
    RecyclerView.Adapter<AllCharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_allcharacters, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characters[position]
        holder.bind(item, isFavourite, onClickListener, onCheckedBox, onUncheckedBox)
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemAllcharactersBinding.bind(view)
        private var favouriteCheckBox = binding.cbFavouriteMain
        fun bind(
            character: UiCharacterModel,
            isFavourite: (Int) -> Boolean,
            onClickListener: (UiCharacterModel) -> Unit,
            onCheckedBox: (UiCharacterModel) -> Unit,
            onUncheckedBox: (UiCharacterModel) -> Unit
        ) {
            val image = character.image
            val name = character.name
            Glide.with(itemView)
                .load(image)
                .into(binding.ivAllCharacters)
            binding.tvAllCharacters.text = name

            itemView.setOnClickListener {
                onClickListener(character)
            }

            val fav = isFavourite(character.id)
            if (fav){
                favouriteCheckBox.isChecked = true
            }

            favouriteCheckBox.setOnCheckedChangeListener { _, _ ->
                if (favouriteCheckBox.isChecked) {
                    onCheckedBox(character)
                } else if (!favouriteCheckBox.isChecked) {
                    onUncheckedBox(character)
                }
            }
        }
    }
}