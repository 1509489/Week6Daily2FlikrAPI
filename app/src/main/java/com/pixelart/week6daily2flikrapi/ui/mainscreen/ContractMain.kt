package com.pixelart.week6daily2flikrapi.ui.mainscreen

import com.pixelart.week6daily2flikrapi.base.BasePresenter
import com.pixelart.week6daily2flikrapi.base.BaseView
import com.pixelart.week6daily2flikrapi.model.PhotoResponse
import com.pixelart.week6daily2flikrapi.model.photoinfo.InfoResponse

interface ContractMain {
    interface View: BaseView{
        fun showImage(photoResponse: PhotoResponse)
        fun showImageInfo(infoResponse: InfoResponse)
    }
    interface Presenter: BasePresenter{
        fun getImage(searchTag: String)
        fun getImageInfo(photoId: String, secret: String)
        fun getString(string: String):String
    }
}