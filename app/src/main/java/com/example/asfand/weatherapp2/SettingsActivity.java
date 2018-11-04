package com.example.asfand.weatherapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.asfand.weatherapp.R;

public class SettingsActivity extends AppCompatActivity {

    String city= "city name";
    String unit = "unit set";
    EditText cityT;
    String unitT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        cityT=(EditText) findViewById(R.id.cityedit);
        RadioButton rBD=(RadioButton) findViewById(R.id.rBC);
        rBD.setChecked(true);
        unitT="C";
    }

    public void onUnitClick(View view)
    {
        RadioGroup rg= (RadioGroup) findViewById(R.id.rGUnit);
        int rB= rg.getCheckedRadioButtonId();
        switch(rB)
        {
            case R.id.rBC:
                unitT="C";
                break;
            case R.id.rBF:
                unitT="F";
                break;
            default:
                unitT="C";

        }
    }

    public void onSave(View view)
    {
        SharedPreferences list= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = list.edit();
        editor.putString(city, cityT.getText().toString());
        editor.putString(unit, unitT.toString());
        editor.commit();
        Intent b = new Intent(this, MainActivity.class);
        startActivity(b);
        finish();
    }
}
