package com.toryx.vrestodema;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Info extends Fragment {

    public static TextView si;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView =inflater.inflate(R.layout.info, container, false);
        si=rootView.findViewById(R.id.sinolo);

      si.setText("Η εφαρμογή δημιουργήθηκε με τη χρήση του Android Studio στα πλαίσια της πτυχιακής εργασίας.\n \n© 2019-2020 Ηλίας Καραβασιλείου \n");




        return rootView;

    }



}
