package com.toryx.vrestodema;

import android.app.Notification;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static com.toryx.vrestodema.App.CHANNEL_1_ID;
import static com.toryx.vrestodema.App.CHANNEL_2_ID;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private static final String TAG = "MainPhase";
    private String linkshare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //ΑΝΑΦΟΡΑ ΣΤΗ ΒΑΣΗ ΔΕΔΟΜΈΝΩΝ
        firebaseAuth =FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Pelates").child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                setTitle("Καλωσήρθες " + userProfile.getUserN());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this,databaseError.getCode(),Toast.LENGTH_LONG).show();
            }
        });
      androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ΔΗΜΙΟΥΡΓΙΑ ΤΟΥ DRAWER-MENU
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_co,new Tracking()).commit();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == R.id.UpdateSettings) {

         startActivity(new Intent(Main2Activity.this, ProfileAct.class));

      }

        if (id == R.id.exitus) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(Main2Activity.this, MainActivity.class));


        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_co,new Routes()).commit();
            setTitle("Δρομολόγια");
        }  else if (id == R.id.about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_co,new Info()).commit();
            setTitle("Νέα");
        } else if (id == R.id.nav_tools) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_co,new Tracking()).commit();
            setTitle("Αποστολές Δεμάτων");
        } else if (id == R.id.received) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_co,new Received()).commit();
            setTitle("Παραλαβές Δεμάτων");
        }
        else if (id == R.id.nav_prices) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_co,new Prices()).commit();
            setTitle("Χρεώσεις και Λοιπά");

        } else if (id == R.id.nav_share) {

            startActivity(new Intent(Main2Activity.this, news.class));
        } else if (id == R.id.nav_send) {
            Intent Share =new Intent(Intent.ACTION_SEND);
            Share.setType("text/plain");
            Share.putExtra(Intent.EXTRA_SUBJECT,"Κοινή Χρήση Εφαρμογής");
            Share.putExtra(Intent.EXTRA_TEXT,"gg");
            startActivity(Intent.createChooser(Share,"Κοινή χρήση μέσω:"));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
