package com.smartestidea.jsonplaceholder.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartestidea.jsonplaceholder.core.CheckInternet
import com.smartestidea.jsonplaceholder.databinding.ActivityMainBinding
import com.smartestidea.jsonplaceholder.ui.contact.ContactActivity
import dagger.hilt.android.AndroidEntryPoint

import com.google.android.material.snackbar.Snackbar
import com.smartestidea.jsonplaceholder.R


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvPosts.layoutManager = LinearLayoutManager(this)

        mainViewModel.onCreate(CheckInternet(this))

        mainViewModel.posts.observe(this,{
           binding.rvPosts.adapter = PostAdapter(it?: emptyList())
        })

        mainViewModel.loading.observe(this,{
            binding.shimmer.isVisible = it
            binding.rvPosts.isVisible = !it
        })

        binding.fabContacts.setOnClickListener {
            requestPermission()
        }


    }

   private fun requestPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS ) -> {
                    requestWrite()
                }
                else ->requestPermissionContactsRead.launch(Manifest.permission.READ_CONTACTS)
            }
        }else
            startActivity(Intent(this,ContactActivity::class.java))
    }

    private fun requestWrite() {
        when(PackageManager.PERMISSION_GRANTED){
            ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_CONTACTS) ->{
                startActivity(Intent(this,ContactActivity::class.java))
            }
            else -> requestPermissionContactsWrite.launch(Manifest.permission.WRITE_CONTACTS)
        }
    }

    private val requestPermissionContactsWrite = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){
        if(it)
            startActivity(Intent(this,ContactActivity::class.java))
        else
            Snackbar.make(binding.coordinator,resources.getString(R.string.grant_permissions),Snackbar.LENGTH_SHORT).show()
    }
    private val requestPermissionContactsRead = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){
        if(it)
            requestWrite()
        else
            Snackbar.make(binding.coordinator,resources.getString(R.string.grant_permissions),Snackbar.LENGTH_SHORT).show()
    }




}