package com.example.rickmortyapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmortyapp.R
import com.example.rickmortyapp.data.model.CharacterModel
import com.example.rickmortyapp.databinding.ItemSearchbynameBinding
import com.example.rickmortyapp.domain.model.UiCharacterModel

class SearchByNameAdapter(
    private val characters: List<CharacterModel>,
    private val onClickListener: (CharacterModel) -> Unit
) :
    RecyclerView.Adapter<SearchByNameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_searchbyname, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characters[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSearchbynameBinding.bind(view)

        fun bind(character: CharacterModel, onClickListener: (CharacterModel) -> Unit) {
            val name = character.name
            val species = character.species
            val image = character.image

            Glide.with(itemView)
                .load(image)
                .circleCrop()
                .into(binding.ivSearchByName)
            binding.tvSearchName.text = name
            binding.tvSearchSpecies.text = species

            itemView.setOnClickListener {
                onClickListener(character)
            }
        }
    }
}