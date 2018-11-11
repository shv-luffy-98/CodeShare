package com.example.shv.codeshareluffy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CodeWriting extends AppCompatActivity {
    private EditText ET;
    private String code;

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
        ET.setText(code);
    }
    public void Save(View view){
        code = ET.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("code", code);
        setResult(2, intent);
        Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
