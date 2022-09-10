package com.smartestidea.jsonplaceholder.core.provider

import android.content.ContentProviderOperation
import android.content.Context
import android.content.OperationApplicationException
import android.os.RemoteException
import android.provider.ContactsContract
import com.smartestidea.jsonplaceholder.data.model.Contact
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Toast


object ContactProvider {

    private val contentProviderOperations = ArrayList<ContentProviderOperation>()

    fun getContacts(context: Context): MutableList<Contact> {

        val projection = arrayOf(
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.Data.DISPLAY_NAME,
            Phone.NUMBER,
        )

        val cursor = context.contentResolver.query(
            ContactsContract.Data.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.Data.DISPLAY_NAME
        )

        val contacts = mutableListOf<Contact>()

        val ids = mutableListOf<Int>()

        while (cursor!!.moveToNext()) {
                if(cursor.getInt(0) !in ids){
                contacts.add(
                    Contact(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
                    ids.add(cursor.getInt(0))
            }
        }
        return contacts
    }
     fun setNumber(contact_id:Int, new_name:String, context: Context) {
         contentProviderOperations.clear()
        contentProviderOperations.add(
            ContentProviderOperation
                .newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(
                    ContactsContract.Data.CONTACT_ID + " = ? AND " +
                            ContactsContract.Data.MIMETYPE + " = ?", arrayOf(
                        contact_id.toString(),
                        Phone.CONTENT_ITEM_TYPE
                    )
                )
                .withValue(
                    Phone.NUMBER,
                    new_name
                )
                .build()
        )
        try {
            context.contentResolver.applyBatch(ContactsContract.AUTHORITY, contentProviderOperations)
            Toast.makeText(context, "Contact Updated Successfully", Toast.LENGTH_LONG).show()
        } catch (e: OperationApplicationException) {
            e.printStackTrace()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun deleteContact(contact_id: Int,context: Context) {
        contentProviderOperations.clear()
        contentProviderOperations.add(
            ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
                .withSelection(ContactsContract.RawContacts.CONTACT_ID + "=?", arrayOf(contact_id.toString())).build()
        )
        try {
            context.contentResolver.applyBatch(ContactsContract.AUTHORITY, contentProviderOperations)
        } catch (e: RemoteException) {
            e.printStackTrace()
        } catch (e: OperationApplicationException) {
            e.printStackTrace()
        }
    }
}