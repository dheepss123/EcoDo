package com.bangkit.ecodo.ui.scan_trash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bangkit.ecodo.databinding.ActivityScanTrashBinding
import com.bangkit.ecodo.ui.camera.CameraActivity
import com.bangkit.ecodo.ui.camera.CameraActivity.Companion.CAMERAX_RESULT
import com.bangkit.ecodo.ui.recommendation.RecommendationActivity
import com.bangkit.ecodo.util.Resource
import com.bangkit.ecodo.util.reduceFileImage
import com.bangkit.ecodo.util.showToast
import com.bangkit.ecodo.util.uriToFile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanTrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanTrashBinding
    private var currentImageUri: Uri? = null
    private val viewModel: ScanTrashViewModel by viewModels()

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        setupAction()
    }

    private fun setupAction() {
        binding.btnOpenGallery.setOnClickListener { startGallery() }
        binding.btnOpenCamera.setOnClickListener { startCameraX() }
        binding.btnPredict.setOnClickListener {
            currentImageUri?.let { uri ->
                val imageFile = uriToFile(uri, this).reduceFileImage()


                viewModel.uploadImage(imageFile).observe(this) { result ->
                    when (result) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }

                        is Resource.Error -> {
                            showLoading(false)
                            showToast(result.error)
                        }

                        is Resource.Success -> {
                            showLoading(false)
                            val intent = Intent(this, RecommendationActivity::class.java)
                            intent.putExtra(RecommendationActivity.TRASH_ID, result.data)
                            startActivity(intent)
                        }
                    }
                }

            } ?: showToast("Image not found")
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERAX_RESULT) {
            currentImageUri = it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imagePreview.setImageURI(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}