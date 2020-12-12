package com.kovaliov.testapplication.presentation.view.impl.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.kovaliov.testapplication.R
import com.kovaliov.testapplication.domain.entities.InputImage
import com.kovaliov.testapplication.presentation.view.interfaces.ImageListScreen
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import java.io.File
import java.util.*

class ImageListAdapter(
    private var inputImageChoiceListener: ImageListScreen? = null,
    private val list: LinkedList<InputImage> = LinkedList()
) : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int ) {
        var data = list[position]
        holder.setData(data)
        holder.itemView.setOnClickListener { inputImageChoiceListener?.onChoice(data) }
    }

    override fun getItemCount() = list.size

    fun updateData(inputImageList: List<InputImage>) {
        list.clear()
        list.addAll(inputImageList)
        notifyDataSetChanged()
    }

    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val contraintLayout = view.findViewById<ConstraintLayout>(R.id.contraintLayout)
        private val constraintSet = ConstraintSet().apply { clone(contraintLayout) }

        fun setData(inputImage: InputImage) {
            constraintSet.setDimensionRatio(
                R.id.imgSmall,
                "H,${inputImage.imageWidth}:${inputImage.imageHeight}"
            )
            constraintSet.applyTo(contraintLayout)
            Picasso.with(view.context.applicationContext).load(inputImage.imageUri)
                .into(view.imgSmall)

        }

    }
}
