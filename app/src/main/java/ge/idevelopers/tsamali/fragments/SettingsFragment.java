package ge.idevelopers.tsamali.fragments;


import android.app.Activity;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

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

        MainActivity.fragmentShown=true;

        text=(TextView) v.findViewById(R.id.switch_text);
        mSwitch=(Switch) v.findViewById(R.id.mSwitch);
        save=(Button) v.findViewById(R.id.save_settings);


      //  Typeface typeface= Typeface.createFromAsset(this.getAssets(), "fonts/bpg_glaho.ttf");



        if(MainActivity.showNotifications)
            mSwitch.setChecked(true);

        else
            mSwitch.setChecked(false);


        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!mSwitch.isChecked()){
                    MainActivity.showNotifications=false;



                }
                else {
                    MainActivity.showNotifications = true;
                }



            }


        });



        return v;

    }
    public boolean onBackPressed() {

        return false;
    }

}
