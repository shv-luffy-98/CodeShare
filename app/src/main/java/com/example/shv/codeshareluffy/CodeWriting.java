package com.example.shv.codeshareluffy;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CodeWriting extends AppCompatActivity {
    private EditText ET;
    private String code;
    private String cd;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(1, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_writing);
        code = getIntent().getExtras().getString("code");
        ET = (EditText) findViewById(R.id.code_editor_edit);
        FirebaseDatabase.getInstance().getReference("projects").child(code).child("code")
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                ET.setText(dataSnapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        }
                );
    }
    public void CopyId(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Id", code);
        clipboard.setPrimaryClip(clip);
    }
    public void Save(View view){
        cd = ET.getText().toString();
        FirebaseDatabase.getInstance().getReference("projects").child(code).child("code").setValue(cd);
        Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
