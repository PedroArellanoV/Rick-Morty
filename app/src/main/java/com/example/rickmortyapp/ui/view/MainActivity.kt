package com.example.rickmortyapp.ui.view

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.rickmortyapp.ui.view.adapters.AllCharactersAdapter
import com.example.rickmortyapp.ui.viewmodel.MainActivityViewModel
import com.example.rickmortyapp.databinding.ActivityMainBinding
import com.example.rickmortyapp.domain.model.UiCharacterModel
import com.example.rickmortyapp.ui.view.adapters.FavouriteCharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var allCharactersAdapter: AllCharactersAdapter
    private lateinit var favouriteCharactersAdapter: FavouriteCharactersAdapter
    private lateinit var allCharacters: List<UiCharacterModel>
    private var favouriteCharacters: List<UiCharacterModel> = emptyList()

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.charactersList.observe(this) {
            allCharacters = it
            initAllCharactersRecyclerView()
        }


        viewModel.favouriteCharacters.observe(this) {
            if (it.isNotEmpty()) {
                favouriteCharacters = it
                initFavouriteCharactersRecyclerView()
            }
        }

        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
        viewModel.getFavouriteCharacters()
        viewModel.getCharactersByPage(1)

        binding.fabSearch.setOnClickListener { goToSearchView() }
    }


    private fun goToSearchView() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

    private fun initFavouriteCharactersRecyclerView() {
        favouriteCharactersAdapter =
            FavouriteCharactersAdapter(favouriteCharacters) { character -> onItemSelected(character) }
        binding.rvFavourites.layoutManager = LinearLayoutManager(this)
        binding.rvFavourites.adapter = favouriteCharactersAdapter
    }

    private fun initAllCharactersRecyclerView() {
        allCharactersAdapter = AllCharactersAdapter(
            allCharacters,
            { character -> onItemSelected(character) },
            { character -> onCheckedBox(character) },
            { character -> onUncheckedBox(character) })
        val viewPager = binding.rvCarousel

        viewPager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.setPageTransformer(compositePageTransformer)
        binding.rvCarousel.adapter = allCharactersAdapter
    }

    private fun onItemSelected(character: UiCharacterModel) {
        val getImage: String = character.image
        val getName: String = character.name
        val getStatus: String = character.status
        val getSpecies: String = character.species
        val getType: String = character.type

        intent = Intent(this, OnSelectedActivity::class.java)
        intent.putExtra("iImage", getImage)
        intent.putExtra("iName", getName)
        intent.putExtra("iStatus", getStatus)
        intent.putExtra("iSpecies", getSpecies)
        intent.putExtra("iType", getType)
        startActivity(intent)

    }

    private fun onCheckedBox(character: UiCharacterModel) {
        viewModel.insertCharacter(character)
    }

    private fun onUncheckedBox(character: UiCharacterModel) {
        viewModel.deleteCharacter(character)
    }
}