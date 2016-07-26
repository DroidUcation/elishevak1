package com.perfecto.optimizer.activities.preferences.targetAudience;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.perfecto.optimizer.R;

public class TargetAudiencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_audiences);
    }

    public void selectCountry(View view) {

        Intent intent = new Intent(getApplicationContext(), GeographiesActivity.class);
        startActivity(intent);

    }
}
