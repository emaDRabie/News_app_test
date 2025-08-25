package com.example.newsy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.newsy.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var viewModel: SharedViewModel

    private val countries = listOf(
        "us" to "United States",
        "gb" to "United Kingdom",
        "ca" to "Canada",
        "au" to "Australia",
        "in" to "India"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Setup ActionBar (Back Button)
        supportActionBar?.apply {
            title = "Settings"
            setDisplayHomeAsUpEnabled(true)
        }

        // Setup Spinner
        val countryNames = countries.map { it.second }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countryNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.countrySpinner.adapter = adapter

        loadSavedCountry()

        binding.countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCountryCode = countries[position].first
                Shared.saveCountry(this@SettingsActivity, selectedCountryCode)
                viewModel.setCountry(selectedCountryCode)

                Toast.makeText(
                    this@SettingsActivity,
                    "News country set to: ${countries[position].second}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.LogoutBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadSavedCountry() {
        val savedCountryCode = Shared.getSavedCountry(this)
        savedCountryCode?.let {
            val position = countries.indexOfFirst { it.first == savedCountryCode }
            if (position >= 0) {
                binding.countrySpinner.setSelection(position)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
