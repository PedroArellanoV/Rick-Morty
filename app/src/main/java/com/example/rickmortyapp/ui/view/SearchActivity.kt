package com.example.rickmortyapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmortyapp.data.model.CharacterModel
import com.example.rickmortyapp.ui.viewmodel.SearchActivityViewModel
import com.example.rickmortyapp.ui.view.adapters.SearchByNameAdapter
import com.example.rickmortyapp.databinding.ActivitySearchBinding
import com.example.rickmortyapp.domain.model.UiCharacterModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), OnQueryTextListener {

    private lateinit var binding: ActivitySearchBinding
    private var allCharactersResults = mutableListOf<CharacterModel>()

    private lateinit var adapter: SearchByNameAdapter

    private val viewModel: SearchActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svSearchActivity.setOnQueryTextListener(this)


        viewModel.charactersRequest.observe(this){
            allCharactersResults.clear()
            allCharactersResults.addAll(it.results)
            adapter.notifyDataSetChanged()
        }

        initRecyclerView()

    }

    private fun initRecyclerView(){
        adapter = SearchByNameAdapter(allCharactersResults){character -> onItemSelected(character) }
        binding.rvSearchByName.layoutManager = LinearLayoutManager(this)
        binding.rvSearchByName.adapter = adapter
    }

    private fun onItemSelected(character: CharacterModel){
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            viewModel.getCharactersByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}