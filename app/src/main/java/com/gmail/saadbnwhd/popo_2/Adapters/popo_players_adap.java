package com.gmail.saadbnwhd.popo_2.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.saadbnwhd.popo_2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Fahaid on 1/10/2017.
 */

public class popo_players_adap extends ArrayAdapter<String> {

    private final Context context;
    ArrayList<String> popo_players=new ArrayList<>();
    ArrayList<String> popo_players_position=new ArrayList<>();
    ArrayList<String> popo_players_number=new ArrayList<>();
    private Holder holder;

    public popo_players_adap(Context context, ArrayList<String> popo_players,ArrayList<String> popo_players_number,ArrayList<String> popo_players_position) {
        super(context, R.layout.popoplayers_listview,popo_players);
        this.context=context;
       this.popo_players=popo_players;
        this.popo_players_number=popo_players_number;
        this.popo_players_position=popo_players_position;
    }

    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.popoplayers_listview, null, true);

        holder=new Holder();

        holder.Name = (TextView) rowView.findViewById(R.id.scorer_name);
        holder.Number = (TextView) rowView.findViewById(R.id.popoplayer_number);
        holder.Position = (TextView) rowView.findViewById(R.id.popoplayer_position);

        holder.img = (ImageView) rowView.findViewById(R.id.popoplayer_img);

        holder.Name.setText(popo_players.get(position));
        holder.Number.setText(popo_players_number.get(position));
        holder.Position.setText(popo_players_position.get(position));
       // txtlocation.setText(locations.get(position));


        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://poponfa-8a11a.appspot.com/");
        StorageReference imagesRef = storageRef.child("Team_Logos/" + "Alliance Jr");

        try {
            final long ONE_MEGABYTE = 1024 * 1024;
            imagesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.img.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }
        catch (Exception e)
        {}
       // holder.img.setImageResource(R.drawable.football_player);

      //  Toast.makeText(context, teams.get(position), Toast.LENGTH_SHORT).show();
        return rowView;

    };

    public class Holder
    {
        TextView Name;
        TextView Number;
        TextView Position;
       ImageView img;
    }

}
