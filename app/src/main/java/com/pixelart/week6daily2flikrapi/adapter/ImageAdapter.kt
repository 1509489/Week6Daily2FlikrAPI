package com.pixelart.week6daily2flikrapi.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pixelart.week6daily2flikrapi.R
import com.pixelart.week6daily2flikrapi.common.GlideApp
import com.pixelart.week6daily2flikrapi.databinding.ImageAdapterLayoutBinding
import com.pixelart.week6daily2flikrapi.model.PhotoData
import com.pixelart.week6daily2flikrapi.model.photoinfo.InfoData
import kotlinx.android.synthetic.main.image_adapter_layout.view.*

class ImageAdapter(private val photoData: List<PhotoData>/*, private val photoInofList: InfoData*/, private val listener: OnItemClickedListener):
    RecyclerView.Adapter<ImageAdapter.ViewHolder> (){
    private lateinit var context: Context
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        context = viewGroup.context
        val binder : ImageAdapterLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.image_adapter_layout, viewGroup, false)
        return ViewHolder(binder)
    }

    override fun getItemCount(): Int {
        return photoData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val image = photoData[position]
        //val imageInfo = photoInofList
        holder.itemView.apply {
            tvTitle.text = image.title
            tvOwner.text = image.owner

            GlideApp.with(context)
                .load(image.url)
                .override(100,100)
                .into(ivIcon)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClickedListener(position)
        }
    }

    class ViewHolder(binder: ImageAdapterLayoutBinding) : RecyclerView.ViewHolder(binder.root)

    interface OnItemClickedListener{
        fun onItemClickedListener(position: Int)
    }
}