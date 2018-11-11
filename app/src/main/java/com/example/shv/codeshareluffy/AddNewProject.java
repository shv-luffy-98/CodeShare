package com.example.shv.codeshareluffy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddNewProject extends AppCompatActivity {
    private TextView a;
    private EditText Name, pass;
    private String password, name;
    private CheckBox priv;
    private Project pwdcheck;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(1, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_project);
        a = (TextView) findViewById(R.id.new_proj_head);
        Name = (EditText) findViewById(R.id.new_proj_user);
        pass = (EditText) findViewById(R.id.new_proj_pass);
        priv = (CheckBox) findViewById(R.id.new_proj_private);
        if (getIntent().getExtras().getBoolean("value"))
            a.setText("New Project");
        else{
            a.setText("Join Project");
            priv.setVisibility(View.GONE);
            Name.setHint("ProjectID");
        }
    }

    public void submitProject(View view) {
        password = pass.getText().toString();
        name = Name.getText().toString();

        if (password != null && !password.isEmpty() && !name.isEmpty() && name != null) {
            if(a.getText().equals("New Project"))
            {
                Toast.makeText(getApplicationContext(),"Submitting", Toast.LENGTH_SHORT).show();

                DatabaseReference pDb = FirebaseDatabase.getInstance().getReference("projects");
                String key = pDb.push().getKey();
                Project p = new Project(name, password);
                pDb.child(key).setValue(p);

                DatabaseReference uDb = FirebaseDatabase.getInstance().getReference("UserProjects");
                String key2 = uDb.push().getKey();
                String uname = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                UserProject u = new UserProject(uname, key);
                uDb.child(key2).setValue(u);

                Intent intent = new Intent();
                setResult(2, intent);
                finish();
            }
            else
            {
                DatabaseReference uDb = FirebaseDatabase.getInstance().getReference("UserProjects");
                DatabaseReference pDb = FirebaseDatabase.getInstance().getReference("projects");

                String key = name;
                pDb.orderByChild("ProjectID").equalTo(key).addListenerForSingleValueEvent(pwdchecklistener);
                UserProject u = new UserProject(FirebaseAuth.getInstance().getCurrentUser().getEmail(), key);
                if(password.equals(pwdcheck.projectPassword))
                {
                    uDb.child(uDb.push().getKey()).setValue(u);
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong Password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
        }
    }
    ValueEventListener pwdchecklistener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    pwdcheck = sn.getValue(Project.class);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            ;
        }
    };
}
