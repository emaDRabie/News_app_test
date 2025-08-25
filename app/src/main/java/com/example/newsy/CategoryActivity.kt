package com.example.newsy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnSports).setOnClickListener { returnCategory("sports") }
        findViewById<Button>(R.id.btnTech).setOnClickListener { returnCategory("technology") }
        findViewById<Button>(R.id.btnHealth).setOnClickListener { returnCategory("health") }
        findViewById<Button>(R.id.btnScience).setOnClickListener { returnCategory("science") }
        findViewById<Button>(R.id.btnBusiness).setOnClickListener { returnCategory("business") }

        // toolbar code
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    private fun returnCategory(category: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("selectedCategory", category)
        startActivity(intent)
    }

    // ربط مع menu file
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    // do what when icon clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.favorite -> {
                Toast.makeText(this, "favorites", Toast.LENGTH_SHORT).show()

                true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}

