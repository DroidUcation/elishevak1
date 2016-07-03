package com.perfecto.optimizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.perfecto.optimizer.preferences.customizedPreferences.CustomizedPreferencesActivity;
import com.perfecto.optimizer.preferences.DeviceTypesActivity;
import com.perfecto.optimizer.preferences.OperatingSystemsActivity;
import com.perfecto.optimizer.preferences.targetAudience.TargetAudiencesActivity;

/**
 * Created by elishevak on 6/27/2016.
 */
public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }

    public void gotoTargetAudiences(View view) {
        Intent intent = new Intent(getApplicationContext(),TargetAudiencesActivity.class);
        startActivity(intent);
    }

    public void gotoOperatingSystems(View view) {
        Intent intent = new Intent(getApplicationContext(),OperatingSystemsActivity.class);
        startActivity(intent);
    }

    public void gotoDeviceTypes(View view) {
        Intent intent = new Intent(getApplicationContext(),DeviceTypesActivity.class);
        startActivity(intent);
    }

    public void gotoCustomizedPreferences(View view) {
        Intent intent = new Intent(getApplicationContext(),CustomizedPreferencesActivity.class);
        startActivity(intent);
    }
}
