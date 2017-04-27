package ge.idevelopers.tsamali.fragments;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import ge.idevelopers.tsamali.MainActivity;
import ge.idevelopers.tsamali.R;


public class InformationFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_information, container, false);

        MainActivity.fragmentShown=true;


      //  Typeface typeface= Typeface.createFromAsset(this.getAssets(), "fonts/bpg_glaho.ttf");

        Typeface typeface = Typeface.createFromAsset(((Activity) v.getContext()).getAssets(), "fonts/bpg_glaho.ttf");





        return v;

    }

}
