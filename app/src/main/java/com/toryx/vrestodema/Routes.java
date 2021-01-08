package com.toryx.vrestodema;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class Routes extends Fragment {
   public static TextView data;
    public static TextView si;
    public static TextView si2;
    public static TextView si3;
    public static Button but;
    public static Button but2;
   public static ArrayList<String> images;
   private RequestQueue mQ;
    private ProgressBar progressBar;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView =inflater.inflate(R.layout.routes, container, false);
        si= (TextView) rootView.findViewById(R.id.dromologia);
        si2= (TextView) rootView.findViewById(R.id.dromologia2);
        si3= (TextView) rootView.findViewById(R.id.dromologia3);

        Spinner mySpinner = rootView.findViewById(R.id.testaki);

         si.setMovementMethod(new ScrollingMovementMethod());

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.customspinner, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    si.setText(
                            "Καθημερινές\n" +
                            "5:35\n" +
                            "6:15 T\n" +
                            "6:45\n" +
                            "7:30\n" +
                            "8:15 T\n" +
                            "8:15\n" +
                            "9:15\n" +
                            "9:15 T\n" +
                            "10:15 T\n" +
                            "10:45\n" +
                            "11:15 T\n" +
                            "12:15 T\n" +
                            "12:30\n" +
                            "13:15 T\n" +
                            "13:45\n" +
                            "14:15 T\n" +
                            "14:45\n" +
                            "15:15 T\n" +
                            "15:45\n" +
                            "16:15 T\n" +
                            "16:45\n" +
                            "17:15 T\n" +
                            "17:45\n" +
                            "18:15 T\n" +
                            "18:45\n" +
                            "19:15 T\n" +
                            "20:15\n" +
                            "21:00 T\n" +
                            "21:30\n");

                            si2.setText("Σάββατο\n" +
                            "7:00\n" +
                            "8:30\n" +
                            "9:15 T\n" +
                            "10:00\n" +
                            "10:15 T\n" +
                            "11:00\n" +
                            "11:15 T\n" +
                            "12:00\n" +
                            "13:00\n" +
                            "13:15 T\n" +
                            "14:00\n" +
                            "15:00\n" +
                            "15:15 T\n" +
                            "16:00\n" +
                            "16:15 T\n" +
                            "17:00\n" +
                            "18:00\n" +
                            "19:00\n" +
                            "19:15 T\n" +
                            "20:15\n" +
                            "21:00 T\n" +
                            "21:30\n");
                            si3.setText("Κυριακή\n" +
                            "7:00\n" +
                            "8:00\n" +
                            "9:30\n" +
                            "11:00\n" +
                            "12:00\n" +
                            "13:00\n" +
                            "14:00\n" +
                            "15:00\n" +
                            "16:00\n" +
                            "17:00 T\n" +
                            "18:00\n" +
                            "19:00 T\n" +
                            "20:15\n" +
                            "21:00 T\n" +
                            "21:30");
                } else if (i == 2) {
                    si.setText(
                            "Καθημερινές\n" +
                            "5:30\n" +
                            "6:20\n" +
                            "6:30 T\n" +
                            "7:00 T\n" +
                            "7:15\n" +
                            "7:45 T\n" +
                            "8:00\n" +
                            "8:45 T\n" +
                            "9:00\n" +
                            "9:45 T\n" +
                            "10:30\n" +
                            "10:45 T\n" +
                            "11:45 T\n" +
                            "12:00\n" +
                            "12:45 T\n" +
                            "13:10\n" +
                            "13:45 T\n" +
                            "14:00\n" +
                            "14:45 T\n" +
                            "15:15\n" +
                            "15:45 T\n" +
                            "16:00\n" +
                            "16:45 T\n" +
                            "17:00\n" +
                            "17:45 T\n" +
                            "18:15\n" +
                            "19:30 T\n" +
                            "19:45\n" +
                            "20:45\n" +
                            "21:30\n");
                    si2.setText(

                            "Σάββατο\n" +
                            "6:45\n" +
                            "7:45 T\n" +
                            "8:00\n" +
                            "8:45 T\n" +
                            "9:30\n" +
                            "9:45 T\n" +
                            "10:30\n" +
                            "11:30\n" +
                            "11:45 T\n" +
                            "12:30\n" +
                            "13:30\n" +
                            "13:45 T\n" +
                            "14:30\n" +
                            "14:45 T\n" +
                            "15:30\n" +
                            "16:30\n" +
                            "17:30\n" +
                            "17:45 T\n" +
                            "18:30\n" +
                            "19:30 T\n" +
                            "20:00\n" +
                            "21:30\n");

                            si3.setText("Κυριακή\n" +
                            "6:30\n" +
                            "8:00\n" +
                            "9:30\n" +
                            "10:30\n" +
                            "11:30\n" +
                            "12:30\n" +
                            "13:30\n" +
                            "14:30\n" +
                            "15:30\n" +
                            "16:30\n" +
                            "17:30\n" +
                            "18:30\n" +
                            "19:30\n" +
                            "20:30\n" +
                            "21:30");
                }else if (i == 0) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });








        return rootView;

    }



}
