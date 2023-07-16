package com.fachrizal.linuxdistroslist

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.fachrizal.linuxdistroslist.databinding.ActivityDetailBinding
import uk.co.deanwild.flowtextview.FlowTextView


class DetailActivity : AppCompatActivity() {
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var dataLinuxDistrosGlobal: LinuxDistros
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val dataLinuxDistros = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("key_linux_distros", LinuxDistros::class.java)
        } else {
            intent.getParcelableExtra("key_linux_distros")
        }

        if (dataLinuxDistros != null) {
            dataLinuxDistrosGlobal = dataLinuxDistros
        }

        supportActionBar?.title = getString(R.string.details_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityDetailBinding.tvTitle.text = dataLinuxDistros?.name
        Glide.with(this)
            .load(dataLinuxDistros?.icon) // URL Gambar
            .into(activityDetailBinding.imgIcon)
        val tvDescription: FlowTextView = activityDetailBinding.tvDescription
        tvDescription.text = dataLinuxDistros?.description
        tvDescription.setTextSize(50.0F)
        if(isDarkMode(this)) {
            tvDescription.textColor = resources.getColor(androidx.appcompat.R.color.primary_text_default_material_dark)
        }

        activityDetailBinding.tvPros.text = dataLinuxDistros?.pros
        activityDetailBinding.tvCons.text = dataLinuxDistros?.cons
        activityDetailBinding.tvSoftwarePackageManagement.text = dataLinuxDistros?.softwarePackageManagement
        activityDetailBinding.tvAvailableEditions.text = dataLinuxDistros?.availableEditions

        activityDetailBinding.tvPossibleAlternatives.text = Html.fromHtml(dataLinuxDistros?.possibleAlternatives)

        activityDetailBinding.tvPossibleAlternatives.movementMethod = LinkMovementMethod.getInstance()
    }

    fun isDarkMode(context: Context): Boolean {
        val darkModeFlag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return darkModeFlag == Configuration.UI_MODE_NIGHT_YES
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_share -> {
                val intentShare = Intent(Intent.ACTION_SEND)
                intentShare.type = "text/plain"
                intentShare.putExtra(Intent.EXTRA_TEXT, dataLinuxDistrosGlobal.name)
                startActivity(Intent.createChooser(intentShare, "Share using"))
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}