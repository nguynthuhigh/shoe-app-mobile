package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class EditProfileActivity extends AppCompatActivity {

    Button btnRename,btnConfirm,btnChangeAvt,btnChangeEmail,btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        addControls();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, DialogEditUsernameActivity.class);
                startActivity(intent);
            }
        });
        btnChangeAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, DialogChangeAvatarActivity.class);
                startActivity(intent);
            }
        });

        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, DialogChangeEmailActivity.class);
                startActivity(intent);
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, DialogChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }




    private void addControls() {
        btnRename= findViewById(R.id.btnRename);
        btnConfirm=findViewById(R.id.btnConfirm);
        btnChangeAvt=findViewById(R.id.btnChangeAvt);
        btnChangeEmail=findViewById(R.id.btnChangeEmail);
        btnChangePassword=findViewById(R.id.btnChangePassword);

    }


}