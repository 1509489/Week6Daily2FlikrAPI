package com.pixelart.week6daily2flikrapi.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pixelart.week6daily2flikrapi.R
import com.pixelart.week6daily2flikrapi.common.GlideApp
import com.pixelart.week6daily2flikrapi.databinding.ImageAdapterLayoutHorizontalBinding
import com.pixelart.week6daily2flikrapi.model.PhotoData
import kotlinx.android.synthetic.main.image_adapter_layout_horizontal.view.*

class ImageAdapterHorizontal(private val photoData: List<PhotoData>):
    RecyclerView.Adapter<ImageAdapterHorizontal.ViewHolder> (){

    private lateinit var context: Context
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        context = viewGroup.context
        val binder : ImageAdapterLayoutHorizontalBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.image_adapter_layout_horizontal, viewGroup, false)
        return ViewHolder(binder)
    }

    override fun getItemCount(): Int {
        return photoData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val image = photoData[position]
        holder.itemView.apply {
            tvTitle.text = "Title: ${image.title}"
            tvOwner.text = "Owner NSID: ${image.owner}"

            GlideApp.with(context)
                .load(image.url)
                .override(100,100)
                .into(ivImage)
        }

    }

    class ViewHolder(binder: ImageAdapterLayoutHorizontalBinding) : RecyclerView.ViewHolder(binder.root)
}