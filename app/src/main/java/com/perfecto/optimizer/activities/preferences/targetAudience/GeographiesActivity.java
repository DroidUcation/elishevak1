package com.perfecto.optimizer.activities.preferences.targetAudience;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.perfecto.optimizer.R;
import com.perfecto.optimizer.activities.PreferencesActivity;
import com.perfecto.optimizer.activities.preferences.BaseFilterActivity;
import com.perfecto.optimizer.utils.Consts;
import com.perfecto.optimizer.utils.FilterTypes;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GeographiesActivity extends BaseFilterActivity {
    private static final String COUNTRIES_KEY = Consts.COUNTRIES_KEY;
    private Set<String> countries = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographies);
        findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeographiesActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void onStart() {
        super.onStart();
        countries = preferences.getStringSet(COUNTRIES_KEY, countries);
        for (String filter : countries) {
            FilterTypes type = FilterTypes.getTypeByName(filter);

            CheckBox checkBox = (CheckBox)findViewById(type.getFilterId());
            checkBox.setChecked(true);
        }
    }

    public void toggleGeography(View view) throws IOException {
        countries = preferences.getStringSet(COUNTRIES_KEY, countries);
        FilterTypes type = FilterTypes.getTypeById(view.getId());
        String value = type.getFilterName();

        boolean contains = countries.contains(value);
        if (contains) {
            countries.remove(value);
        } else {
            countries.add(value);
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(COUNTRIES_KEY, countries);
        editor.putLong(Consts.LAST_CHANGE_KEY, System.currentTimeMillis());
        editor.apply();
    }

}
