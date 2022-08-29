package com.dw.newkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frameLayout=findViewById<FrameLayout>(R.id.frame)
        val drawerLayout=findViewById<DrawerLayout>(R.id.drawer)
        val navigationView=findViewById<NavigationView>(R.id.navigation)
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bootom)

        toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        replaceFragment(HomeFragment())

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.firstItem-> replaceFragment(HomeFragment())
                R.id.secondItem->    replaceFragment(PersonFragment())
                R.id.thirdItem->    replaceFragment(SettingFragment())

                else->{
                }
            }
            true
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.firstItem -> {

                    replaceFragment(HomeFragment())
                }
                R.id.secondItem -> {

                    replaceFragment(PersonFragment())

                }
                R.id.thirdItem -> {
                    replaceFragment(SettingFragment())
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment:Fragment){

        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}