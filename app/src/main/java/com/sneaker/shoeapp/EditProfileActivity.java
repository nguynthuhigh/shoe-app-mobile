package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sneaker.shoeapp.Dialog.DialogRename;

public class EditProfileActivity extends AppCompatActivity {

    Button btnConfirm,btnRename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        addControls();
        addEvent();
    }

    private void addEvent() {
        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditProfileActivity.this, "ok ok", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void addControls() {
        btnRename= findViewById(R.id.btnRename);
        btnConfirm=findViewById(R.id.btnConfirm);

    }

    private void reName() {
        DialogRename dialogRename = new DialogRename();
        dialogRename.show(getSupportFragmentManager(), "dialog rename");
    }
}