package com.example.contactfavoris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactfavoris.database.DbHandler;
import com.example.contactfavoris.models.Contact;

public class AddContact extends AppCompatActivity {

    Button btnAdd;
    EditText edtName, edtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        DbHandler db = new DbHandler(this);

        btnAdd = findViewById(R.id.btnAdd);
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString().trim();
                String number = edtNumber.getText().toString().trim();

                Log.i("MyLog",name.isEmpty() + " " + number.length());

                if(!name.isEmpty() && number.length() == 10){

                    Contact tmpContact = new Contact(name, number);

                    db.addContact(tmpContact);
                    Toast.makeText(AddContact.this, "Added", Toast.LENGTH_SHORT).show();

                    setResult(1);
                    finish();

                }else{
                    Toast.makeText(AddContact.this, "Failed to add", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}