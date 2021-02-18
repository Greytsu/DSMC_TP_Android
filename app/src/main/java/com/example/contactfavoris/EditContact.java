package com.example.contactfavoris;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactfavoris.database.DbHandler;
import com.example.contactfavoris.models.Contact;

public class EditContact extends AppCompatActivity {

    EditText edtName, edtPhone;
    Button btnSave, btnDelete;
    Contact currentContact;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent previousActivity = getIntent();

        DbHandler db = new DbHandler(EditContact.this);

        edtName     = findViewById(R.id.editName);
        edtPhone    = findViewById(R.id.editNumber);
        btnSave     = findViewById(R.id.btnSave);
        btnDelete     = findViewById(R.id.btnDelete);

        String contactID = previousActivity.getStringExtra("ContactID");

        if(db.getContact(contactID).isPresent())
            currentContact = db.getContact(contactID).get();
        else{
            Toast.makeText(EditContact.this, "Can't find contact", Toast.LENGTH_SHORT).show();
            finish();
        }

        edtName.setText(currentContact.getName());
        edtPhone.setText(currentContact.getNumber());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentContact.setName(edtName.getText().toString().trim());
                currentContact.setNumber(edtPhone.getText().toString().trim());

                db.updateContact(currentContact);
                finish();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteContact(currentContact);
                finish();

            }
        });

    }
}
