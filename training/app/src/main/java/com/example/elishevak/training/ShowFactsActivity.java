package com.example.elishevak.training;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class ShowFactsActivity extends AppCompatActivity {

    ViewPager pager;
    FactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_facts);

        pager = (ViewPager) findViewById(R.id.show_facts);
        adapter = new FactAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

    }
}
