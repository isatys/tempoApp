package com.example.tempoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.util.Calendar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupérer le DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Récupérer le bouton menu
        val menuButton = findViewById<ImageView>(R.id.menu_button)

        // Ajouter un listener au bouton menu pour ouvrir le menu burger
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Ajouter un listener à la NavigationView pour gérer la navigation dans le menu burger
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)

        val textView = findViewById<TextView>(R.id.dateTextView)

        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val dayOfWeekString = when (dayOfWeek) {
            Calendar.SUNDAY -> "Dimanche"
            Calendar.MONDAY -> "Lundi"
            Calendar.TUESDAY -> "Mardi"
            Calendar.WEDNESDAY -> "Mercredi"
            Calendar.THURSDAY -> "Jeudi"
            Calendar.FRIDAY -> "Vendredi"
            Calendar.SATURDAY -> "Samedi"
            else -> ""
        }

        textView.text = "Aujourd'hui, nous sommes $dayOfWeekString."
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //Navigation dans le menu
        when (item.itemId) {
            R.id.historique -> {
                val intent = Intent(this@MainActivity, Reglage::class.java)
                startActivity(intent)
            }
            R.id.reglages -> {
                val intent = Intent(this@MainActivity, Historique::class.java)
                startActivity(intent)
            }
        }

        // Fermer le DrawerLayout
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
    }


