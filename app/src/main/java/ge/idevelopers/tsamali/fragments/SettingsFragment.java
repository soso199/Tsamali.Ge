package ge.idevelopers.tsamali.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import ge.idevelopers.tsamali.MainActivity;
import ge.idevelopers.tsamali.R;




public class SettingsFragment extends Fragment {
    private TextView text;
    private Switch mSwitch;
    private Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        text=(TextView) v.findViewById(R.id.switch_text);
        mSwitch=(Switch) v.findViewById(R.id.mSwitch);
        save=(Button) v.findViewById(R.id.save_settings);


        Typeface typeface= Typeface.createFromAsset(getContext().getAssets(), "fonts/bpg_glaho.ttf");
        text.setTypeface(typeface);
        save.setTypeface(typeface);

        if(MainActivity.showNotifications)
            mSwitch.setChecked(true);



        return v;

    }

}
