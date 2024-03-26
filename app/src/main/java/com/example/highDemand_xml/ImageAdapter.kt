package com.example.highDemand_xml

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(private val context: Context, private val imageList: List<Int>, private val itemWidth: Int, private val itemHeight: Int) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(itemWidth, itemHeight)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ImageViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView as ImageView
    }
}
