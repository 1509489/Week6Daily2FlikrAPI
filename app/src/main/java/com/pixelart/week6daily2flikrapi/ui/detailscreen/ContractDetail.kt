package com.pixelart.week6daily2flikrapi.ui.detailscreen

import android.content.Intent
import com.pixelart.week6daily2flikrapi.base.BasePresenter
import com.pixelart.week6daily2flikrapi.base.BaseView
import com.pixelart.week6daily2flikrapi.model.PhotoData

interface ContractDetail {
    interface View: BaseView{
        fun displayPhotoDetail()
    }

    interface Presenter: BasePresenter{
        fun getPhotoDetails(intent: Intent): PhotoData
    }
}