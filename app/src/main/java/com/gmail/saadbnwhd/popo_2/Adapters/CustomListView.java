package com.gmail.saadbnwhd.popo_2.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.saadbnwhd.popo_2.Logos;
import com.gmail.saadbnwhd.popo_2.MainMenu;
import com.gmail.saadbnwhd.popo_2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Musab on 12/11/2016.
 */
public class CustomListView extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> teams;
    private final ArrayList<String> locations;
    private final Integer[] imgid;


    public CustomListView(Activity context, ArrayList<String> teams, ArrayList<String> locations, Integer[] imgid) {
        super(context, R.layout.listview, teams);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.teams=teams;
        this.locations=locations;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {



        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.team1);
        TextView txtlocation = (TextView) rowView.findViewById(R.id.location);

        txtTitle.setText(teams.get(position));
        if(locations.size() >0)
        txtlocation.setText(locations.get(position));

        final ImageView logo;
        logo=(ImageView)  rowView.findViewById(R.id.logo);

      //  if(imgid.length >0)
/*
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://poponfa-8a11a.appspot.com/");
        StorageReference imagesRef = storageRef.child("Team_Logos/" + "Mahsair United");


        final long ONE_MEGABYTE = 1024 * 1024;
        imagesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                logo.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
*/

        byte[] logobyte= Logos.getlogo(teams.get(position));

//        Bitmap bitmap = BitmapFactory.decodeByteArray(logobyte, 0, logobyte.length);
        //logo.setImageBitmap(bitmap);

        return rowView;

    };
}