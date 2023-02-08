package com.example.realtimelocationwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GoMap extends AppCompatActivity {

    WebView webView;
    String latitude, longitude;

    private List<Geolocation> GeolocationList;
    DatabaseReference databaseReference;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_map);

        webView = findViewById(R.id.webView);

        databaseReference = FirebaseDatabase.getInstance().getReference("locations");
        GeolocationList = new ArrayList<>();



    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                GeolocationList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    latitude = dataSnapshot.child("latitude").getValue().toString();
                    longitude = dataSnapshot.child("longitude").getValue().toString();
                }

                webView.loadUrl("https://www.google.com/maps/search/?api=1&query="+latitude+", "+longitude+" ");
                // this will enable the javascript.
                webView.getSettings().setJavaScriptEnabled(true);
                // WebViewClient allows you to handle
                // onPageFinished and override Url loading.
                webView.setWebViewClient(new WebViewClient());

                Log.d("latitudeLongitude", "Latitude and Longitude : "+latitude+" ,  "+longitude);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}