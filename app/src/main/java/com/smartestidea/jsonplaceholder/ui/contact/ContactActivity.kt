package com.smartestidea.jsonplaceholder.ui.contact

import android.Manifest
import android.content.ContentProviderOperation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartestidea.jsonplaceholder.databinding.ActivityContactBinding
import dagger.hilt.android.AndroidEntryPoint
import android.provider.ContactsContract.PhoneLookup

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import java.lang.Exception
import android.content.OperationApplicationException
import android.content.pm.PackageManager
import android.os.Build
import android.os.RemoteException
import android.view.MotionEvent
import android.view.View

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private val binding:ActivityContactBinding by lazy { ActivityContactBinding.inflate(layoutInflater) }

    private val contactViewModel:ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        contactViewModel.onCreate(this)

        binding.rvContacts.layoutManager = LinearLayoutManager(this)

        contactViewModel.contacts.observe(this,{
            binding.rvContacts.adapter = ContactAdapter(it)
        })
    }
}

