package com.kovaliov.testapplication.presentation.view.impl.details

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kovaliov.testapplication.R
import com.kovaliov.testapplication.domain.entities.InputImage
import com.kovaliov.testapplication.presentation.showToast
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_image_details.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.*

const val INPUT_IMAGE_KEY = "INPUT_IMAGE_KEY"

class ImageDetailsActivity : AppCompatActivity() {

    private var inputImage: InputImage? = null

    private val imageLoadTarget = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmap?.let { setImageWallpaper(it) }
            showToast(resources.getString(R.string.image_set_success))
        }

        override fun onBitmapFailed(errorDrawable: Drawable?) {
            showToast(resources.getString(R.string.image_set_error))
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val bundle = intent.extras
        inputImage = (bundle?.getSerializable(INPUT_IMAGE_KEY) as InputImage)
        showData()
        btnSetImage.setOnClickListener {
            setImageToDesktop()
        }
        btnSave.setOnClickListener {
            saveImage()


        }

    }

    override fun onPause() {
        super.onPause()
        Picasso.with(this).cancelRequest(imageLoadTarget)
    }

    private fun showData() {
        inputImage?.let { inputImage ->
            Picasso.with(applicationContext).load(inputImage.imageUri).into(imgFull)
            tvImageDescription.text = inputImage.description
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setImageToDesktop() {
        inputImage?.imageUri?.let { uri ->
            Picasso.with(this).load(uri).into(imageLoadTarget)
        } ?: showToast(resources.getString(R.string.get_image_error))

    }

    private fun setImageWallpaper(bitmap: Bitmap) {
        val wm = WallpaperManager.getInstance(this)
        try {
            wm.setBitmap(bitmap)
        } catch (e: Exception) {
            showToast(resources.getString(R.string.image_set_error))
        }
    }

    private fun saveImage() {
        if (inputImage != null) {
            val extStorageDirectory = applicationContext.getExternalFilesDir(null)?.absolutePath
            val file = File(extStorageDirectory, "${UUID.randomUUID()}.jpg")
            URL(inputImage?.imageUri).openStream().use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                    showToast("файл скачан в ${file.absolutePath}")
                }
            }
        }

//        val uri = Uri.fromFile(File(inputImage?.imageUri))
//        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
//        val extStorageDirectory = applicationContext.getExternalFilesDir(null)?.absolutePath
//        val file = File(extStorageDirectory, "${UUID.randomUUID()}.jpg")
//        val outStream = FileOutputStream(file)
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
//        outStream.flush()
//        outStream.close()

        /*val drawable = BitmapDrawable(resources, bitmap)
        val bitmap = (drawable as BitmapDrawable).bitmap
        val wrapper = ContextWrapper(applicationContext)
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }*/

    }
}