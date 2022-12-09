package com.example.rickmortyapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.rickmortyapp.databinding.ActivityOnSelectedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnSelectedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnSelectedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnSelectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val photo = intent.getStringExtra("iImage")
        val name = intent.getStringExtra("iName")
        val status = intent.getStringExtra("iStatus")
        val species = intent.getStringExtra("iSpecies")
        val type = intent.getStringExtra("iType")

        printAll(photo!!, name!!, status!!, species!!, type!!)
    }

    private fun printAll(
        photo: String,
        name: String,
        status: String,
        species: String,
        type: String){

        binding.tvSelectedName.text = name
        binding.tvSelectedSpecies.text = species
        binding.tvSelectedStatus.text = status
        binding.tvSelectedType.text = type

        Glide.with(this)
            .load(photo)
            .centerCrop()
            .into(binding.ivOnSelected)
        }
    }
