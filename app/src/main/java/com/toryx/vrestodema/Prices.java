package com.toryx.vrestodema;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Prices extends Fragment {
    ArrayList<String> prices = new ArrayList<>();


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.prices, container, false);
        ListView myListView = rootView.findViewById(R.id.listaprices);


        TextView tx = rootView.findViewById(R.id.infos);
        prices.add("Φάκελος : 3,5€");
        prices.add("Κουτάκι: 4€");

        prices.add("Κουτί(ως 5 κιλά) : 5€");
        prices.add("Κουτί(ως 10 κιλά) : 8€");
        prices.add("Τελάρο : 5€");
        prices.add("Κλούβα : 7€");


        tx.setText("*Επιτρέπεται αποστολή κατοικιδίων μόνο μεσα σε κλουβί και σε συγκεκριμένα δρομολόγια.\n\n" +
                "*Δέματα για αποστολή με το αντιστοιχο λεωφορείο γινονται πριν 5 λεπτα της αναχώρησης του.");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.names, prices);
        myListView.setAdapter(arrayAdapter);

        return rootView;
    }
}