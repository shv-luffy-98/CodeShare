package com.example.shv.codeshareluffy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddProject extends AppCompatActivity {

    final int code = 0;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(1, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
    }

    public void addNewProject(View view) {
        Intent intent = new Intent(getApplicationContext(), AddNewProject.class);
        intent.putExtra("value", true);
        startActivityForResult(intent, code);
    }

    public void addOldProject(View view) {
        Intent intent = new Intent(getApplicationContext(), AddNewProject.class);
        intent.putExtra("value", false);
        startActivityForResult(intent, code);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code && resultCode == 2) {
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        }
    }
}
