package com.example.contactfavoris;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contactfavoris.adapter.FavorisAdapter;
import com.example.contactfavoris.database.DbHandler;
import com.example.contactfavoris.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listFavoris = new ArrayList<>();
    private ArrayAdapter<String> listAdapter ;

    ListView favoristListView;
    Button btnAdd;
    FavorisAdapter adapter;
    List<Contact> contactFavoris;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favoristListView = findViewById(R.id.FavorisListView);
        btnAdd = findViewById(R.id.btnAdd);
        contactFavoris = new ArrayList<>();

        //Recupere contact depuis bdd
        db = new DbHandler(this);
        contactFavoris = db.getAllContacts();

        //Affiche contacts
        adapter = new FavorisAdapter(MainActivity.this, contactFavoris);
        favoristListView.setAdapter(adapter);


        //Ajouter contact
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Click on AddContact", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);

                //TODO:Reload listView when add a contact and edit a contact

            }
        });

    }
}