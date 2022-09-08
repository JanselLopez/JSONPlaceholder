package com.smartestidea.jsonplaceholder.ui.contact

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartestidea.jsonplaceholder.data.model.Contact
import com.smartestidea.jsonplaceholder.domain.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
):ViewModel() {
    val contacts = MutableLiveData<MutableList<Contact>>()

    fun onCreate(context: Context){
        viewModelScope.launch {
            contacts.postValue(getContactsUseCase(context)!!)
        }
    }
}