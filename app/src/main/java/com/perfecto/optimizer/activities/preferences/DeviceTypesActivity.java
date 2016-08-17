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

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DeviceTypesActivity extends BaseFilterActivity {

    private static final String DEVICE_KEY = Consts.DEVICE_KEY;
    private Set<String> deviceType = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_types);

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.device_types_layout), iconFont);


        findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceTypesActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        deviceType = preferences.getStringSet(DEVICE_KEY, deviceType);
        for (String filter : deviceType) {
            FilterTypes type = FilterTypes.getTypeByName(filter);
            findViewById(type.getFilterId())
                    .setBackgroundColor(getColor(R.color.selected_icon_background));
        }
    }

    public void toggleDeviceType(View view) throws IOException {
        deviceType = preferences.getStringSet(DEVICE_KEY, deviceType);
        FilterTypes type = FilterTypes.getTypeById(view.getId());
        String value = type.getFilterName();

        boolean contains = deviceType.contains(value);
        if (contains) {
            deviceType.remove(value);
            view.setBackground(getDrawable(R.drawable.filter_btn_bg));
        } else {
            deviceType.add(value);
            view.setBackgroundColor(getColor(R.color.selected_icon_background));
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(DEVICE_KEY, deviceType);
        editor.putLong(Consts.LAST_CHANGE_KEY, System.currentTimeMillis());
        editor.apply();
    }


}
