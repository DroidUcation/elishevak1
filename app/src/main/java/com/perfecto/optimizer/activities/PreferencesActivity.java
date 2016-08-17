package com.perfecto.optimizer.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perfecto.optimizer.R;
import com.perfecto.optimizer.activities.preferences.DeviceTypesActivity;
import com.perfecto.optimizer.activities.preferences.OperatingSystemsActivity;
import com.perfecto.optimizer.activities.preferences.targetAudience.TargetAudiencesActivity;
import com.perfecto.optimizer.utils.Consts;
import com.perfecto.optimizer.utils.SQLHelper;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by elishevak on 6/27/2016.
 */
public class PreferencesActivity extends AppCompatActivity {

    private RecyclerView results;
    private TextView tempQuestion;
    private Button seeFullPicture;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        findViewById(R.id.goto_target_audiences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreferencesActivity.this, TargetAudiencesActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.goto_operating_systems).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreferencesActivity.this, OperatingSystemsActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.goto_device_types).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreferencesActivity.this, DeviceTypesActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.see_full_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreferencesActivity.this, ResultsActivity.class);
                startActivity(intent);
            }
        });

        getPartialResults();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPartialResults();
    }

    private void getPartialResults() {
        SharedPreferences preferences = getSharedPreferences(Consts.SHARED_PREF_KEY, MODE_PRIVATE);
        RelativeLayout partialResults = (RelativeLayout) this.findViewById(R.id.partial_results);

        results = (RecyclerView) findViewById(R.id.partial_results_recycler_view);
        tempQuestion = (TextView) findViewById(R.id.temp_question);
        seeFullPicture = (Button) findViewById(R.id.see_full_picture);

        boolean noFilters = checkNoValues(Consts.DEVICE_KEY, preferences) &&
                checkNoValues(Consts.OS_KEY, preferences) &&
                checkNoValues(Consts.COUNTRIES_KEY, preferences);

        if (noFilters) {
            tempQuestion.setVisibility(View.VISIBLE);
            results.setVisibility(View.INVISIBLE);
            seeFullPicture.setVisibility(View.INVISIBLE);

        } else {
            tempQuestion.setVisibility(View.INVISIBLE);
            results.setVisibility(View.VISIBLE);
            seeFullPicture.setVisibility(View.VISIBLE);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            results.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            results.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            try {
                SQLHelper helper = new SQLHelper(getBaseContext());
                mAdapter = new ResultsAdapter(helper, 3);
                results.setAdapter(mAdapter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkNoValues(String key, SharedPreferences preferences) {

        return !preferences.contains(key) ||
                preferences.getStringSet(key, new HashSet<String>()).size() == 0;
    }
}
