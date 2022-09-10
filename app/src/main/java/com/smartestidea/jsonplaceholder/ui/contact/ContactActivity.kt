package com.smartestidea.jsonplaceholder.ui.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartestidea.jsonplaceholder.databinding.ActivityContactBinding
import dagger.hilt.android.AndroidEntryPoint

import com.smartestidea.jsonplaceholder.core.provider.ContactProvider


@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private val binding:ActivityContactBinding by lazy { ActivityContactBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = ContactAdapter(ContactProvider.getContacts(this))
    }
}

