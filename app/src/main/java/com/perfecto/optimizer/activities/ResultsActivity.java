package com.perfecto.optimizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.perfecto.optimizer.R;
import com.perfecto.optimizer.utils.SQLHelper;

import java.io.IOException;

public class ResultsActivity extends AppCompatActivity {

    private RecyclerView results;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        findViewById(R.id.change_prefs_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getReport();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getReport();
    }

    private void getReport() {
        results = (RecyclerView) findViewById(R.id.results_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        results.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        results.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        try {
            SQLHelper helper = new SQLHelper(getBaseContext());
            mAdapter = new ResultsAdapter(helper, -1);
            results.setAdapter(mAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
