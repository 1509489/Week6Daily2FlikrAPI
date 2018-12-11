package com.pixelart.week6daily2flikrapi.ui.detailscreen

import android.os.Bundle
import com.pixelart.week6daily2flikrapi.R
import com.pixelart.week6daily2flikrapi.base.BaseActivity
import com.pixelart.week6daily2flikrapi.common.GlideApp
import com.pixelart.week6daily2flikrapi.databinding.ActivityDetailBinding
import com.pixelart.week6daily2flikrapi.di.ApplicationModule
import com.pixelart.week6daily2flikrapi.di.DaggerApplicationComponent
import com.pixelart.week6daily2flikrapi.di.NetworkModule
import javax.inject.Inject

class DetailActivity : BaseActivity<ContractDetail.Presenter>(), ContractDetail.View {

    @Inject
    lateinit var presenter: ContractDetail.Presenter
    @Inject
    lateinit var binder: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayPhotoDetail()
    }

    override fun getViewPresenter(): ContractDetail.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build().injectDetailScreen(this)
    }

    override fun displayPhotoDetail() {
        val photoDetail = presenter.getPhotoDetails(intent)

        binder.tvTitle.text = "Title: ${photoDetail.title}"
        binder.tvOwner.text = "Owner NSID: ${photoDetail.owner}"

        GlideApp.with(this)
            .load(photoDetail.url)
            .override(385,250)
            .fitCenter()
            .into(binder.ivImage)
    }
}
