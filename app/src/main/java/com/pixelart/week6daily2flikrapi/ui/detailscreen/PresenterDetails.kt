package com.pixelart.week6daily2flikrapi.ui.detailscreen

import android.content.Intent
import com.pixelart.week6daily2flikrapi.model.PhotoData
import javax.inject.Inject

class PresenterDetails @Inject constructor(private val view: ContractDetail.View): ContractDetail.Presenter{

    override fun getPhotoDetails(intent: Intent): PhotoData {
        return intent.getParcelableExtra("photo")
    }

    override fun onCreate() {

    }

    override fun onResume() {

    }

    override fun onDestroy() {

    }
}