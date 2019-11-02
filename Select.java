package com.example.schoolfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Select extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0262R.layout.activity_select);
    }

    public void Student(View view) {
        startActivity(new Intent(this, Student.class));
    }

    public void Parent(View view) {
        startActivity(new Intent(this, Parent.class));
    }

    public void Teacher(View view) {
        startActivity(new Intent(this, Teacher.class));
    }
}
