package com.example.actorfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.actorfragment.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Options.isFirstInit = false;

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment, new ListFragment(), "list_fragment")
                .addToBackStack("list_fragment_transaction")
                .commit();
    }
}