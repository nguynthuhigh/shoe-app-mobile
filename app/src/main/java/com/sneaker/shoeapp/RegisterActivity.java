package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sneaker.shoeapp.model.User;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
Button btnSignUp,btnSignIn;
EditText inputEmail,confirmPass,inputPass,inputName;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    CollectionReference userCollection = firestore.collection("User");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String email_user = inputEmail.getText().toString().trim();
                String pass_user = inputPass.getText().toString().trim();
                String confirmPass_user = confirmPass.getText().toString().trim();
                if (pass_user.equals(confirmPass_user)) {
                    mAuth.createUserWithEmailAndPassword(email_user, pass_user)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Map<String, Object> userinfo = new HashMap<>();
                                        userinfo.put("id", mAuth.getCurrentUser().getUid());
                                        userinfo.put("username", inputName.getText().toString());

                                        firestore.collection("User").document(mAuth.getCurrentUser().getUid()).set(userinfo);
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                else {
                    Toast.makeText(RegisterActivity.this, "Incorrect password.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void addControls() {
        btnSignUp=findViewById(R.id.btnSignUp);
        inputEmail = findViewById(R.id.inputEmail);
        confirmPass = findViewById(R.id.confirmPass);
        inputPass = findViewById(R.id.inputPass);
        inputName = findViewById(R.id.inputName);
        btnSignIn=findViewById(R.id.btnSignIn);
    }
}