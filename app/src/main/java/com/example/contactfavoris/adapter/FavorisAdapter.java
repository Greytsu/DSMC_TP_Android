package com.example.contactfavoris.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.contactfavoris.EditContact;
import com.example.contactfavoris.MainActivity;
import com.example.contactfavoris.R;
import com.example.contactfavoris.models.Contact;

import java.util.List;

public class FavorisAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> favorisList;
    private LayoutInflater inflater;

    public FavorisAdapter(Context context, List<Contact> favorisList){

        this.context = context;
        this.favorisList = favorisList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return favorisList.size();
    }

    @Override
    public Contact getItem(int pos) {
        return favorisList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.adapter_favoris, null);

        //get info from current item
        Contact currentItem = getItem(i);

        //Set value to adapter
        TextView txtName = view.findViewById(R.id.name);
        Button btnCall = view.findViewById(R.id.call);
        LinearLayout contactLayout = view.findViewById(R.id.contact);

        //Set name
        txtName.setText(currentItem.getName());

        //Click on contact to edit
        contactLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EditContact.class);
                intent.putExtra("ContactID", String.valueOf(currentItem.getID()));    // send text
                context.startActivity(intent);

            }
        });

        //Click on button to call
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = currentItem.getNumber();// Votre numéro de téléphone
                // Call
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + tel));
                context.startActivity(callIntent);
            }
        });



        return view;
    }

    public void reloadView(List<Contact> favorisList){

        this.favorisList = favorisList;


    }
}
