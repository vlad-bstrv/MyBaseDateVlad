package com.vladbstrv.mybasedatevlad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edName, edSecName, edEmail;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        edName = findViewById(R.id.edName);
        edSecName = findViewById(R.id.edSecondName);
        edEmail = findViewById(R.id.edEmail);

        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    public void onClickSave(View view) {
        String id = mDataBase.getKey();
        String name = edName.getText().toString();
        String secName = edSecName.getText().toString();
        String email = edEmail.getText().toString();
        User newUser = new User(id, name, secName, email);
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(secName) && !TextUtils.isEmpty(email)) {
            mDataBase.push().setValue(newUser);
        } else {
            Toast toast = Toast.makeText(this, "ПУстое поле", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onClickRead(View view) {
        Intent intent = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(intent);
    }
}