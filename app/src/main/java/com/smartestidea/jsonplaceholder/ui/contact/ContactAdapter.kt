package com.smartestidea.jsonplaceholder.ui.contact

import android.content.*
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartestidea.jsonplaceholder.data.model.Contact
import com.smartestidea.jsonplaceholder.databinding.CvContactBinding

import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smartestidea.jsonplaceholder.R
import com.smartestidea.jsonplaceholder.core.provider.ContactProvider
import com.smartestidea.jsonplaceholder.databinding.BsContactMenuBinding


class ContactAdapter(
    private val contacts:MutableList<Contact>
):RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private lateinit var context: Context

    inner class ContactViewHolder(val binding:CvContactBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        context = parent.context
        val binding = CvContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        with(holder){
            with(contacts[position]){
                if(name==null) name = context.resources.getString(R.string.unknown)
                binding.tvInitial.text = name!![0].toString().uppercase()
                binding.tvName.text = name
                binding.tvNumber.text = number?:""
                binding.cvContact.setOnClickListener {
                    showBottomSheet(id,name?:context.resources.getString(R.string.unknown),position)
                }
            }
        }
    }

    override fun getItemCount(): Int = contacts.size

    private fun showBottomSheet(id:Int,name:String,position:Int){
        val bindingBS = BsContactMenuBinding.inflate(LayoutInflater.from(context))
        val bottomSheetDialog = BottomSheetDialog(context,R.style.ThemeOverlay_Material3_BottomSheetDialog)
        bottomSheetDialog.setContentView(bindingBS.root)

        bindingBS.tvName.text = name
        bindingBS.btnEdit.setOnClickListener {
            setNumber(id,position)
            bottomSheetDialog.dismiss()
        }
        bindingBS.btnDelete.setOnClickListener {
            deleteContact(id,position)
            bottomSheetDialog.dismiss()
        }
           bottomSheetDialog.show()
    }

    private fun setNumber(id:Int,position: Int){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(context.resources.getString(R.string.new_number))
        val input = EditText(context)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_NUMBER
        builder.setView(input)
        builder.setPositiveButton(android.R.string.ok
        ) { _, _ ->
            val newNumber = input.text.toString()
            ContactProvider.setNumber(id,newNumber,context)
            contacts[position].number = newNumber
            notifyItemChanged(position)
        }
        builder.setNegativeButton(android.R.string.cancel
        ) { dialog, _ ->
            dialog.cancel() }
        builder.show()
    }

    private fun deleteContact(id: Int,position: Int){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(context.resources.getString(R.string.delete_contact))
        builder.setPositiveButton(android.R.string.ok
        ) { _, _ ->
            ContactProvider.deleteContact(id,context)
            notifyItemRemoved(position)
        }
        builder.setNegativeButton(android.R.string.cancel
        ) { dialog, _ ->
            dialog.cancel() }
        builder.show()
    }

}
