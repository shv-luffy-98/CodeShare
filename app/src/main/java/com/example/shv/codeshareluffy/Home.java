package com.example.shv.codeshareluffy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    final int code = 0, code2 = 1;
    private String codes[] = new String[10];
    private int id;
    ListView lv;
    ArrayList<String> projs = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*myLayout = (LinearLayout) findViewById(R.id.home_projects);*/

        lv = (ListView) findViewById(R.id.home_projects);
        adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, projs);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getApplicationContext(), CodeWriting.class);
                intent.putExtra("code", codes[arg2]);
                id = arg2;
                startActivity(intent);
            }
        });
    }

    public void addProject(View view) {
        startActivity(new Intent(getApplicationContext(), AddProject.class));
    }

    public void clickedProfile(View view) {
        startActivity(new Intent(getApplicationContext(), Profile.class));
    }
}
