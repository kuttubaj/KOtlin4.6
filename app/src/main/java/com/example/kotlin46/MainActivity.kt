package com.example.kotlin46

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.kotlin46.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        var isHasPermission = PreferenseHelper(this@MainActivity).isHasPermission
        btnGetRequest.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity, Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                val getContent =
                    registerForActivityResult(ActivityResultContracts.GetContent()){
                            uri->
                        binding.referat.setImageURI(uri)
                    }
                }

                Log.e("permision", "Разрешение есть")
            } else {
                if (!isHasPermission) {
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
                    isHasPermission = true
                } else {
                    createDialog()
                }
            }
        }
    }

    private fun createDialog() {
        AlertDialog.Builder(this).setTitle("Разрешение на чтение данных")
            .setMessage("Перейти в Настройки?").setPositiveButton("да перейти") { dialog, k ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)

            }.setNegativeButton("Нет я хочу остаться") { dialog, k ->
            }.show()
    }
}