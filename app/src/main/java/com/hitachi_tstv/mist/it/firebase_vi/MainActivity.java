package com.hitachi_tstv.mist.it.firebase_vi;

import android.media.Ringtone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hitachi_tstv.mist.it.firebase_vi.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Add Fragment
        if (savedInstanceState == null) {

//            Add Fragment to Activity
            getSupportFragmentManager().beginTransaction().add(R.id.contentMainFragment, new MainFragment()).commit();
        }

    }//Main Method
}// Main Class
