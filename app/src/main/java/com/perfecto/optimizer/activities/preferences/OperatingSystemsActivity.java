package com.perfecto.optimizer.activities.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.perfecto.optimizer.R;
import com.perfecto.optimizer.activities.PreferencesActivity;
import com.perfecto.optimizer.utils.Consts;
import com.perfecto.optimizer.utils.FilterTypes;
import com.perfecto.optimizer.utils.FontManager;

import java.util.HashSet;
import java.util.Set;

public class OperatingSystemsActivity extends BaseFilterActivity {

    private static final String OS_KEY = Consts.OS_KEY;
    private Set<String> os = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_systems);

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.os_layout), iconFont);

        findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OperatingSystemsActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        os = preferences.getStringSet(OS_KEY, new HashSet<String>());
        for (String filter : os) {
            FilterTypes type = FilterTypes.getTypeByName(filter);
            findViewById(type.getFilterId())
                    .setBackgroundColor(getColor(R.color.selected_icon_background));
        }
    }

    public void toggleOS(View view) {
        os = preferences.getStringSet(OS_KEY, new HashSet<String>());
        FilterTypes type = FilterTypes.getTypeById(view.getId());
        String value = type.getFilterName();

        boolean contains = os.contains(value);
        if (contains) {
            os.remove(value);
            view.setBackground(getDrawable(R.drawable.filter_btn_bg));
        } else {
            os.add(value);
            view.setBackgroundColor(getColor(R.color.selected_icon_background));
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(OS_KEY, os);
        editor.apply();
    }
}

