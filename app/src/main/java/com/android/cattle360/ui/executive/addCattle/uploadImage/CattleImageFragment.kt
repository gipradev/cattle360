package com.android.cattle360.ui.executive.addCattle.uploadImage

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.CattleImageFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.uploadImage.uploadFragment.UploadFragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CattleImageFragment :
    BaseFragment<CattleImageViewModel, CattleImageFragmentBinding, AddCattleRepository>() {
    private val REQUEST_IMAGE_CAPTURE: Int = 300
    private val PERMISSION_REQUEST_CODE: Int = 200
    private val PICK_IMAGE_CAPTURE: Int = 1
    lateinit var imageFilePath: String
    lateinit var c_type: String
    lateinit var imageView: ImageView
    var position: Int? = null
    lateinit var livestock_id:String

    val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )
    companion object {
        fun newInstance() = CattleImageFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CattleImageFragmentBinding {
        return CattleImageFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<CattleImageViewModel> {
        return CattleImageViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        livestock_id = arguments?.getString("livestock_id").toString()
println(livestock_id+"-----------livestk id")
       // launchUploadFragment()
        requestPermission()
        imageViewObserver(binding.cameraButton1.text.toString())
        imageViewObserver(binding.cameraButton2.text.toString())
        imageViewObserver(binding.cameraButton3.text.toString())
        imageViewObserver(binding.cameraButton4.text.toString())
        imageViewObserver(binding.cameraButton5.text.toString())
        imageViewObserver(binding.cameraButton6.text.toString())


        binding.cameraButton1.setOnClickListener {
            onCameraClick(binding.modelImage1)
            imageUploadObserver(binding.cameraButton1.text.toString())
}
        binding.cameraButton2.setOnClickListener {
            onCameraClick(binding.modelImage2)
            imageUploadObserver(binding.cameraButton2.text.toString())
        }
        binding.cameraButton3.setOnClickListener {
            onCameraClick(binding.modelImage3)
            imageUploadObserver(binding.cameraButton3.text.toString())
        }
        binding.cameraButton4.setOnClickListener {
            onCameraClick(binding.modelImage4)
            imageUploadObserver(binding.cameraButton4.text.toString())
        }
        binding.cameraButton5.setOnClickListener {
            onCameraClick(binding.modelImage5)
            imageUploadObserver(binding.cameraButton5.text.toString())
        }
        binding.cameraButton6.setOnClickListener {
            onCameraClick(binding.modelImage6)
            imageUploadObserver(binding.cameraButton6.text.toString())
        }


        binding.imagePreviousButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.imageNextButton.setOnClickListener {

        }


    }



//    private fun launchUploadFragment() {
//        activity?.supportFragmentManager!!.beginTransaction().replace(
//            R.id.uploadFragment,
//            UploadFragment.newInstance(), UploadFragment::class.java.toString()
//        ).commit()
//
//    }

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


    fun onCameraClick(modelImage:ImageView) {

       this.imageView = modelImage
        //this.position = adapterPosition

        if (hasNoPermissions()) requestPermission()
        else takePicture()
    }


    private fun takePicture() {
        try {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
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
        println("image file return................................" + imageFile)
        return imageFile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
//                if (resultCode == Activity.RESULT_OK && data != null) {
//        //            imageView.setImageBitmap(data.extras!!.get("data") as Bitmap)
//                }
                if (resultCode == Activity.RESULT_OK) {

               imageView.setImageBitmap(setScaledBitmap())

                   // position?.let { uploadAdaptor.notifyItemChanged(it) }

                }
            }
            else -> {
                Toast.makeText(requireActivity(), "Unrecognized request code", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
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



    fun imageUploadObserver(type:String){

        val requestBody: RequestBody =
            createImageFile().asRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        viewModel.getimageUploadModel(requestBody, type)
        viewModel.imguploadResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

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

    }


    fun imageViewObserver(text:String){
println("loiiii"+livestock_id)
        viewModel.imageViewModel(livestock_id, text)
        viewModel.imgViewResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    println(it)
                    if (it.value?.status.equals("1")) {


                        val path: String = it.value?.path.toString()
                        Glide.with(this)
                            .load(path)
                            .into(imageView)
                        println("success view")

                    } else {
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }
        })
    }

}