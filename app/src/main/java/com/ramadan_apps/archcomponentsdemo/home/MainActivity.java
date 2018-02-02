package com.ramadan_apps.archcomponentsdemo.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ramadan_apps.archcomponentsdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState ==null){
            getSupportFragmentManager().beginTransaction().add(R.id.contanier,new ListFragment()).commit();
        }
    }
}
