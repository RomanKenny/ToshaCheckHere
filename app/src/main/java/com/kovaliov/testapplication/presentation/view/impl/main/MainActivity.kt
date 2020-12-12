package com.kovaliov.testapplication.presentation.view.impl.main

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ImageReader
import android.os.Bundle
import android.provider.MediaStore.Images.Media.getBitmap
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.resources.MaterialResources.getDrawable
import com.kovaliov.testapplication.App
import com.kovaliov.testapplication.R
import com.kovaliov.testapplication.data.database.Count
import com.kovaliov.testapplication.data.database.Database
import com.kovaliov.testapplication.data.local.ImageApiRequestCounter
import com.kovaliov.testapplication.data.local.ImageApiRequestCounterService
import com.kovaliov.testapplication.data.remote.res.ImageResponse
import com.kovaliov.testapplication.domain.entities.InputImage
import com.kovaliov.testapplication.presentation.presenter.main.MainScreenPresenter
import com.kovaliov.testapplication.presentation.startActivity
import com.kovaliov.testapplication.presentation.view.interfaces.MainScreen
import com.kovaliov.testapplication.presentation.view.impl.about.AboutActivity
import com.kovaliov.testapplication.presentation.view.impl.details.INPUT_IMAGE_KEY
import com.kovaliov.testapplication.presentation.view.impl.details.ImageDetailsActivity
import com.kovaliov.testapplication.presentation.view.impl.support.SupportActivity
import kotlinx.android.synthetic.main.about_activity.*
import kotlinx.android.synthetic.main.about_activity.toolbar
import kotlinx.android.synthetic.main.activity_image_details.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject




class MainActivity : AppCompatActivity(), MainScreen,
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var screenPresenter: MainScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_main, null)
        setContentView(contentView)
        initToolbar()
        initNavigation()
        if (savedInstanceState == null) {
            showScreenImageList()
        }
    }

    override fun onResume() {
        super.onResume()
        screenPresenter.onAttachView(this)
    }

    override fun onPause() {
        super.onPause()
        screenPresenter.onDetachView()
    }

    override fun showScreenImageList() {
        setImageListFragment()
    }

    private fun setImageListFragment() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmeLayout, ImageListFragment.newInstance())
                    .commit()
            }
    }

    override fun showScreenAbout() {
        startActivity<AboutActivity>()
    }

    override fun showScreenSupport() {
        startActivity<SupportActivity>()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_support -> screenPresenter.showScreenAbout()
            R.id.nav_support1 -> screenPresenter.showScreenSupport()
        }
        hideNavigation()
        return false

    }

    override fun onBackPressed() {
        hideNavigation()
        super.onBackPressed()

    }

    private fun initNavigation() {
        nav_view.setNavigationItemSelectedListener(this)

    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            showNavigation()
            //var count = App.getInstance().getDatabase().countDao().getValue()
            tvAmount.text = ImageApiRequestCounterService.getRequestAmount().toString()
        }
    }


    private fun inject(mainActivity: MainActivity) {
        App.getAppComponent().injectMainScreen(mainActivity)
    }


    private fun showNavigation() {
        drawer_layout.open()
    }

    private fun hideNavigation() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)




        }

    }
}


