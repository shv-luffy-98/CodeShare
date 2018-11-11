package com.example.shv.codeshareluffy;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUp extends AppCompatActivity {
    private String name, pass, pass2, email;
    private FirebaseAuth mAuth;

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
    }

    private boolean checkempty() {
        if (name != null && pass != null && pass2 != null && email != null)
            return (!name.isEmpty() && !pass.isEmpty() && !pass2.isEmpty() && !email.isEmpty());
        return false;
    }

    public void Register(View view) {
        name = ((EditText) findViewById(R.id.register_name)).getText().toString();
        pass = ((EditText) findViewById(R.id.register_pass)).getText().toString();
        pass2 = ((EditText) findViewById(R.id.register_pass2)).getText().toString();
        email = ((EditText) findViewById(R.id.register_email)).getText().toString();

        if (checkempty())
            if (isValidEmail(email))
                if (pass.length() >= 6)
                    if (pass.equals(pass2)) {
                        mAuth.createUserWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT);
                                            FirebaseUser user = mAuth.getCurrentUser();


                                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(name)
                                                    .build();

                                            user.updateProfile(profileUpdates)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                ;
                                                            }
                                                        }
                                                    });


                                            startActivity(new Intent(getApplicationContext(), Home.class));
                                            finish();
                                        } else
                                            Toast.makeText(getApplicationContext(), "Registration failed.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else
                        Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Password should have 6 characters", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
    }
}
