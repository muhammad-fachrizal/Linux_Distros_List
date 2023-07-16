package com.fachrizal.linuxdistroslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.fachrizal.linuxdistroslist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val linuxDistrosList = ArrayList<LinuxDistros>()
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.rvLinuxDistros.setHasFixedSize(true)
        linuxDistrosList.addAll(getListLinuxDistros())
        showRecyclerList()
    }

    private fun getListLinuxDistros(): ArrayList<LinuxDistros> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataIcon = resources.getStringArray(R.array.data_icon)
        val dataPros = resources.getStringArray(R.array.data_pros)
        val dataCons = resources.getStringArray(R.array.data_cons)
        val dataSoftwarePackageManagement = resources.getStringArray(R.array.data_software_package_management)
        val dataAvailableVariants = resources.getStringArray(R.array.data_available_editions)
        val dataSuggestedAlternatives = resources.getStringArray(R.array.data_suggested_alternatives)
        val listLinuxDistros = ArrayList<LinuxDistros>()
        for (i in dataName.indices) {
            val linuxDistros = LinuxDistros(dataName[i], dataDescription[i], dataIcon[i], dataPros[i], dataCons[i], dataSoftwarePackageManagement[i], dataAvailableVariants[i], dataSuggestedAlternatives[i])
            listLinuxDistros.add(linuxDistros)
        }
        return listLinuxDistros
    }

    private fun showRecyclerList() {
        activityMainBinding.rvLinuxDistros.layoutManager = LinearLayoutManager(this)
        val listLinuxDistrosAdapter = ListLinuxDistrosAdapter(linuxDistrosList)
        activityMainBinding.rvLinuxDistros.adapter = listLinuxDistrosAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                val intentAbout = Intent(this, AboutActivity::class.java)
                startActivity(intentAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}