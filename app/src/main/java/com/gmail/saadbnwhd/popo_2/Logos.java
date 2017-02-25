package com.gmail.saadbnwhd.popo_2;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gmail.saadbnwhd.popo_2.Adapters.CustomListView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Saad on 24/02/2017.
 */

public class Logos extends Application {
    private static Map<String, byte[]> logos ;


    @Override
    public void onCreate() {

        super.onCreate();
        //  instance = this;

        logos = new HashMap<>();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://poponfa-8a11a.appspot.com/");
        final StorageReference imagesRef = storageRef.child("Team_Logos/" + "Mahsair United");



        Firebase.setAndroidContext(getApplicationContext());  //Setting up Firebase
        Firebase ref=new Firebase("https://poponfa-8a11a.firebaseio.com/");

        Firebase teamRef; //Reference to Teams node
        teamRef=ref.child("League").child("Teams");  //Traversing to Teams

        teamRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                // Map<String,String> map=dataSnapshot.getValue(Map.class);

                final long ONE_MEGABYTE = 1024 * 1024;
                imagesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        logos.put(dataSnapshot.getKey(),bytes);

                        Log.i("Team:",dataSnapshot.getKey() );
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public static byte[] getlogo(String Name) {

        return logos.get(Name);
    }

    public void initialLoad() {


    }

    public void addlogo(String str) {

        //mGlobalVarValue = str;
    }
}
