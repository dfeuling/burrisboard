package com.example.schoolfrontend;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class registerUser extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0262R.layout.activity_register_user);
        Spinner spinner = (Spinner) findViewById(C0262R.C0264id.spAre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, C0262R.array.planets_array, 17367048);
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter(adapter);
    }
}
