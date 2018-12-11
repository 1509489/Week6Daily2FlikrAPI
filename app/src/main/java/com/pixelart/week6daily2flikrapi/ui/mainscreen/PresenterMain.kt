package com.pixelart.week6daily2flikrapi.ui.mainscreen

import com.pixelart.week6daily2flikrapi.common.*
import com.pixelart.week6daily2flikrapi.model.PhotoResponse
import com.pixelart.week6daily2flikrapi.model.photoinfo.InfoResponse
import com.pixelart.week6daily2flikrapi.remote.APIService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PresenterMain @Inject constructor(private val view: ContractMain.View, private val apiService: APIService):
    ContractMain.Presenter{


    override fun getImage(searchTag: String) {
        apiService.flikrSearch(API_KEY, searchTag, FORMAT, NO_JSON_CALLBACK)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<PhotoResponse>{
                override fun onSuccess(t: PhotoResponse) {
                    view.showImage(t)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view.showError("Failed to fetch Photo data")
                }

            })
    }

    override fun getImageInfo(photoId: String, secret: String) {
        apiService.getPhotoInof(API_KEY, photoId, secret, FORMAT, NO_JSON_CALLBACK)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<InfoResponse>{
                override fun onSuccess(t: InfoResponse) {
                    view.showImageInfo(t)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view.showError("Failed to fetch Info data")
                }

            })
    }

    override fun getString(string: String): String{
        return string
    }

    override fun onCreate() {

    }

    override fun onResume() {

    }

    override fun onDestroy() {

    }
}