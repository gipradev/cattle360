package com.android.cattle360.ui.executive.addCattle.uploadImage.uploadFragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.getIntent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.UploadFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class  UploadFragment : BaseFragment<UploadViewModel, UploadFragmentBinding, AddCattleRepository>(),
    UploadAdaptor.OnUploadClickEvent {

    private val REQUEST_IMAGE_CAPTURE: Int = 300
    private val PERMISSION_REQUEST_CODE: Int = 200
    lateinit var imageFilePath: String

    lateinit var imageView: ImageView
    var position: Int? = null

    private val uploadAdaptor: UploadAdaptor by lazy { UploadAdaptor(this) }

    val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    companion object {
        fun newInstance() = UploadFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): UploadFragmentBinding {
        return UploadFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<UploadViewModel> {
        return UploadViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.imageUploadRecycler.adapter = uploadAdaptor
//list image
     //  val file: File = File(imageFilePath)
      val  requestBody : RequestBody =createImageFile().asRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        viewModel.getimageUploadModel(requestBody, "head")
        viewModel.imguploadResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    println(it)
                    if (it.value?.status.equals("1")) {
                        Toast.makeText(requireContext(), "file uploaded ", Toast.LENGTH_SHORT)
                            .show()

                    } else {
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }
        })

//upload image

        viewModel.getUploadModel()
        viewModel.uploadResponse.observe(viewLifecycleOwner, Observer {
            uploadAdaptor.list = it
        })

        requestPermission()

    }

    private fun hasNoPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), permissions, 0)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {

                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {

                    Toast.makeText(requireActivity(), "Permission Granted", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_SHORT)
                        .show()
                }
                return
            }

            else -> {

            }
        }
    }


    override fun onCameraClick(modelImage: ImageView, adapterPosition: Int) {

        this.imageView = modelImage
        this.position = adapterPosition

        if (hasNoPermissions()) requestPermission()
        else takePicture()
    }


    private fun takePicture() {
        try {


            val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val file: File = createImageFile()

            val uri: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.android.cattle360",
                file
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

        } catch (e: IOException) {
            Toast.makeText(requireActivity(), "Could not create file!", Toast.LENGTH_SHORT).show()
            println("Exception $e")
        }
    }


    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName: String = "JPEG_" + timeStamp + "_"
        val storageDir: File =
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        if (!storageDir.exists()) storageDir.mkdirs()
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = imageFile.absolutePath
        println("image file path................................." + imageFilePath)
        return imageFile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    imageView.setImageBitmap(data.extras?.get("data") as Bitmap)
                }
                if (resultCode == Activity.RESULT_OK) {

                    imageView.setImageBitmap(setScaledBitmap())
                    position?.let { uploadAdaptor.notifyItemChanged(it) }

                }
            }
            else -> {
                Toast.makeText(requireActivity(), "Unrecognized request code", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun setScaledBitmap(): Bitmap {
        val imageViewWidth = imageView.width
        val imageViewHeight = imageView.height

        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imageFilePath, bmOptions)
        val bitmapWidth = bmOptions.outWidth
        val bitmapHeight = bmOptions.outHeight

        val scaleFactor = Math.min(bitmapWidth / imageViewWidth, bitmapHeight / imageViewHeight)

        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor

        return BitmapFactory.decodeFile(imageFilePath, bmOptions)

    }


}