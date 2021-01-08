package com.toryx.vrestodema;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Send extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    private ListView dataListView;
    ArrayList<String> listid;
    ArrayList<String>  nameid;
    ArrayList<String>  ggid;
    DatabaseReference dbRef;
    FirebaseAuth fa= FirebaseAuth.getInstance();
    String gg= fa.getUid();
    ListView list;
    Button b;
    ArrayList<String> onomata = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        dataListView = (ListView) findViewById(R.id.listrec);


        EditText srh= findViewById(R.id.searchpara);
        Button backst= findViewById(R.id.backsta);
        dbRef = database.getReference().child("Pelates").child(gg).child("Received");
        // myAdapter= new myAdapter(this,listid,nameid,ggid);
        //   dataListView.setAdapter(myAdapter);
        adapter = new ArrayAdapter<String>(Send.this, R.layout.demata, onomata);

        dataListView.setAdapter(adapter);

        addChildEventListener();
        backst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        srh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (Send.this).adapter.getFilter().filter("Κωδικός : "+s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String data = (String) "Κωδικός : " + dataSnapshot.child("code").getValue() + " \nΚατάσταση : " + dataSnapshot.child("cond").getValue() + "\nΑποστολέας : " + dataSnapshot.child("friend").getValue();
                Log.e("gg",data);
                adapter.add(data);



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbRef.addChildEventListener(childListener);
    }
}
