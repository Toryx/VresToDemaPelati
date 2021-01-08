package com.toryx.vrestodema;

import android.app.Notification;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.toryx.vrestodema.App.CHANNEL_1_ID;


public class Tracking extends Fragment {
    private ListView dataListView;
    ArrayAdapter<String> adapter;
    private TextView profname,profemail,retupr,proftel;
    public static TextView si;
    public static TextView si2;
    TextView infotext;
    private NotificationManagerCompat notificationManager;
    DatabaseReference dbRef;
    ArrayList<String> onomata = new ArrayList<>();
    private FirebaseAuth firebaseAuth=  firebaseAuth= FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView =inflater.inflate(R.layout.tracking, container, false);
        infotext= rootView.findViewById(R.id.sendinfo);
        EditText srh2= rootView.findViewById(R.id.searchpara2);
        dataListView = (ListView) rootView.findViewById(R.id.sendlist);

        dbRef = firebaseDatabase.getReference().child("Pelates").child(firebaseAuth.getUid()).child("Send");
        Log.e("ggwp",firebaseAuth.getUid());
        adapter = new ArrayAdapter<String>(getContext(),R.layout.demata, onomata);
        dataListView.setAdapter(adapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addChildEventListener();
        srh2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (Tracking.this).adapter.getFilter().filter("Κωδικός : "+s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                info(position);

            }
        });

        notificationManager = NotificationManagerCompat.from(getContext());

        return rootView;

    }

    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String data= (String) "Κωδικός : "+ dataSnapshot.child("code").getValue()+" \nΚατάσταση : "+dataSnapshot.child("cond").getValue()+"\nΠαραλήπτης : "+dataSnapshot.child("friend").getValue();
                Log.e("ggwp",data);
                adapter.insert(data,0);





            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String code =dataSnapshot.child("code").getValue().toString();
                String cond =dataSnapshot.child("cond").getValue().toString();
                sendOnChannel1(getView(),code,cond);
                Log.e("papa",code);
                Log.e("papa",cond);
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

    private void info(int x){
        String subString = "";
        String subString2 = "";
        String subString3 = "";
        String antikat = "";
        String katoiko = "";

        int end = adapter.getItem(x).indexOf(":");
        int end2 = adapter.getItem(x).indexOf("Κατά");
        subString = adapter.getItem(x).substring(end+1, end2);

        int leavinghour=0;
        int leavingminute=0;
        int arrivinghour=0;
        int arrivingminute=0;

        String cityname = "";
        String filename =subString;
        subString = filename.substring(0, 3); //this will give ωρα
        subString2 = filename.substring(3, 5); //this will give λεπτο
        subString3 = filename.substring(5,9);//city
        antikat = filename.substring(11,12);//anbtikatavoli
        katoiko = filename.substring(12,13);//katoikia
        String hour = subString3.substring(0,2);
        String minute = subString3.substring(2);
        String citysend=filename.substring(9,10);
        String daysend="";
        String cityrec=filename.substring(10,11);
        Integer hourint = Integer.parseInt(hour);
        boolean nextday=false;
        Integer minuteint = Integer.parseInt(minute);
        if(hourint>18 || hourint<7){
            if(hourint>18){nextday=true;}
            hourint=7;

        }
        if(nextday){
            int day = Integer.parseInt(subString2);
            day++;
            daysend= Integer.toString(day);
        }
        if(citysend.equals("t")){citysend="Θεσσαλονίκη";

        }
        if(citysend.equals("a")){citysend="Αλεξάνδεια";
        }
        if(citysend.equals("b")){citysend="Βέροια";

        }
        if(citysend.equals("n")){citysend="Νάουσα";
        }
        if(cityrec.equals("t")){cityrec="Θεσσαλονίκη";}
        if(cityrec.equals("a")){cityrec="Αλεξάνδεια";}
        if(cityrec.equals("b")){cityrec="Βέροια";

            if (minuteint < 40) {
                leavinghour = hourint;
                leavingminute = 45;
            } else {
                leavinghour = hourint + 1;
                leavingminute = 45;
            }}
        if(cityrec.equals("n")){cityrec="Νάουσα";}

        if(antikat.equals("Y")){antikat="Με Αντικαταβολή";}else{antikat="Χωρίς Αντικαταβολή";}
        if(katoiko.equals("Y")){katoiko="Με Παράδωση Κατ΄οίκον";}else{katoiko="Χωρίς Παράδωση Κατ΄οίκον";}


        infotext.setText("Ώρα παράδωσης : "+ String.valueOf(hour) + ":"+String.valueOf(minute) +" "+ String.valueOf(subString2) + " /" +
         String.valueOf(subString)+"\n\nΩρα Αποστολής : " + String.valueOf(leavinghour) + ":" + String.valueOf(leavingminute) +" "+ String.valueOf(daysend) + " /" +
                String.valueOf(subString)+
        "\n\nΑφετηρία : "+ String.valueOf(citysend)+"\n\n Προορισμός : " + String.valueOf(cityrec)+"\n\n"+antikat+"\n\n"+katoiko



);



    }
    public void sendOnChannel1(View v,String cod,String con) {
        String title = cod;
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String message = con;
        Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_1_ID)
                .setSmallIcon(R.drawable.eikonidiosender)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(alarmSound)
                .setVibrate(new long[]{0,250,100,250})
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }

}







