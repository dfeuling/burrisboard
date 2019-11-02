package com.example.schoolfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0262R.layout.activity_main);
    }

    public void login(View view) {
        startActivity(new Intent(this, Select.class));
    }

    public void signup(View view) {
        startActivity(new Intent(this, registerUser.class));
    }
}
