package com.toryx.vrestodema;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileAct extends AppCompatActivity {
    private TextView profname,profemail,retupr,proftel,apost,paral;
    private ImageView profpic;
    private Button editpr,editps;
    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private int apo=0;
    private int par=0;
   private DatabaseReference dbRef2 = database.getReference().child("Pelates").child(firebaseAuth.getUid()).child("Send");
    private DatabaseReference dbRef3 = database.getReference().child("Pelates").child(firebaseAuth.getUid()).child("Received");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        profname= findViewById(R.id.nameli);
        profemail=findViewById(R.id.emailli);
        apost=findViewById(R.id.arithmosapostolwn);
        paral=findViewById(R.id.arithmosparal);
        editpr = findViewById(R.id.editli);

        retupr= findViewById(R.id.returnli);
        proftel = findViewById(R.id.teli);


        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();



        //DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference dbRef = firebaseDatabase.getReference().child("Pelates").child(firebaseAuth.getUid());

        addChildEventListener();
        addChildEventListener2();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                profname.setText("Όνομα : "+userProfile.getUserN());
                profemail.setText("E-mail : "+userProfile.getUserE());
                proftel.setText("Τηλέφωνο : "+userProfile.getUserT());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileAct.this,databaseError.getCode(),Toast.LENGTH_LONG).show();
            }
        });
editpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileAct.this,Update_P.class));

            }

        });
        retupr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });

    }
    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            apo++;
            apost.setText(String.valueOf(apo));
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
        dbRef2.addChildEventListener(childListener);
    }
    private void addChildEventListener2() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                par++;
                paral.setText(String.valueOf(par));
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
        dbRef3.addChildEventListener(childListener);
    }
}
