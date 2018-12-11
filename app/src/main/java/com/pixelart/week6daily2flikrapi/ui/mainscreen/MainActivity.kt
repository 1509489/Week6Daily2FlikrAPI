package com.pixelart.week6daily2flikrapi.ui.mainscreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.pixelart.week6daily2flikrapi.adapter.ImageAdapter
import com.pixelart.week6daily2flikrapi.adapter.ImageAdapterHorizontal
import com.pixelart.week6daily2flikrapi.base.BaseActivity
import com.pixelart.week6daily2flikrapi.common.Utils
import com.pixelart.week6daily2flikrapi.databinding.ActivityMainBinding
import com.pixelart.week6daily2flikrapi.di.ApplicationModule
import com.pixelart.week6daily2flikrapi.di.DaggerApplicationComponent
import com.pixelart.week6daily2flikrapi.di.NetworkModule
import com.pixelart.week6daily2flikrapi.model.PhotoResponse
import com.pixelart.week6daily2flikrapi.model.PhotoData
import com.pixelart.week6daily2flikrapi.model.photoinfo.InfoData
import com.pixelart.week6daily2flikrapi.model.photoinfo.InfoResponse
import com.pixelart.week6daily2flikrapi.ui.detailscreen.DetailActivity
import javax.inject.Inject
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper



class MainActivity : BaseActivity<ContractMain.Presenter>(), ContractMain.View, ImageAdapter.OnItemClickedListener {

    private val TAG = "MainActivity"

    @Inject
    lateinit var presenter: ContractMain.Presenter
    @Inject
    lateinit var binding: ActivityMainBinding

    private lateinit var photoList: ArrayList<PhotoData>
    private lateinit var photoInfo : InfoData
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ImageAdapter
    private lateinit var adapterHorizontal: ImageAdapterHorizontal

    private var canChangeView = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photoList = ArrayList()
        //photoInfo = ArrayList(photoList.size)
        layoutManager = LinearLayoutManager(this)
    }

    override fun getViewPresenter(): ContractMain.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build().injectMainScreen(this)
    }

    override fun showImage(photoResponse: PhotoResponse) {
        val photos = photoResponse.photos.photo

        photoList.clear()
        for (i in 0 until photos.size) {
            presenter.getImageInfo(photos[i].id, photos[i].secret)

                photoList.add(PhotoData(photos[i].title, photos[i].owner, Utils.generateImageUrl(
                photos[i].farm, photos[i].server, photos[i].id, photos[i].secret, 'z')))

        }

        adapterHorizontal = ImageAdapterHorizontal(photoList)
        adapter = ImageAdapter(photoList, this)
        binding.recylerView.layoutManager = layoutManager
        binding.recylerView.itemAnimator = DefaultItemAnimator()
        binding.recylerView.adapter = adapter
        binding.recylerView.setHasFixedSize(true)
        canChangeView = true
        val helper = LinearSnapHelper()
        helper.attachToRecyclerView(binding.recylerView)

    }

    override fun showImageInfo(infoResponse: InfoResponse) {
        /*photoInfoList.add(
            InfoData(infoResponse.photo.owner.username,
                infoResponse.photo.description.content, infoResponse.photo.dates.taken))*/
        photoInfo = InfoData(infoResponse.photo.owner.username,
            infoResponse.photo.description.content, infoResponse.photo.dates.taken)

        //Log.d(TAG, photoOwner)
    }

    fun onClicked(view: View){


        when (view) {
            binding.ibtnSearch ->{
                val search = binding.etSearch.text.toString()
                presenter.getImage(search)
            }

            binding.btnToggleView ->{
                if (binding.recylerView.adapter != null){
                    if (canChangeView)
                    {
                        canChangeView = false
                        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        binding.recylerView.layoutManager = layoutManager
                        binding.recylerView.adapter = adapterHorizontal


                    }
                    else{
                        canChangeView = true
                        layoutManager = LinearLayoutManager(this)
                        binding.recylerView.layoutManager = layoutManager
                        binding.recylerView.adapter = adapter
                    }
                }
            }
        }
    }

    override fun onItemClickedListener(position: Int) {
        val photo = PhotoData(
            photoList[position].title, photoList[position].owner, photoList[position].url
        )
        Log.d(TAG, photo.url)
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("photo", photo)
        })

    }

}
