package com.fachrizal.linuxdistroslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.fachrizal.linuxdistroslist.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var activityAboutBinding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAboutBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(activityAboutBinding.root)

        supportActionBar?.title = getString(R.string.about_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this)
            .load(AppCompatResources.getDrawable(this, R.drawable.profile_photo))
            .circleCrop()
            .into(activityAboutBinding.imgAboutPhoto)

        activityAboutBinding.tvSource.text = Html.fromHtml(getString(R.string.about_source))
        activityAboutBinding.tvSource.movementMethod = LinkMovementMethod.getInstance()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}