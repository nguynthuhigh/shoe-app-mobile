package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sneaker.shoeapp.Admin.AdminHomeActivity;
import com.sneaker.shoeapp.model.User;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button btnSignUp, btnSignIn;
    EditText inputEmail, confirmPass, inputPass, inputName;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    CollectionReference userCollection = firestore.collection("User");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addbtnSignup();

    }

    private void addbtnSignup() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang chạy nè");
        progressDialog.setMessage("Đợi chút xíu...");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable icERR = getResources().getDrawable(R.drawable.ic_errorlogin);
                icERR.setBounds(0, 0, icERR.getIntrinsicWidth(), icERR.getIntrinsicHeight());
                String email_Register = inputEmail.getText().toString().trim();
                String password_Register = inputPass.getText().toString().trim();
                String name_Register = inputName.getText().toString().trim();
                String confirmPass_Register = confirmPass.getText().toString().trim();
                if (email_Register.isEmpty()) {
                    inputEmail.setCompoundDrawables(null, null, icERR, null);
                    inputEmail.setError("Please, input your email", icERR);
                }
                if (password_Register.isEmpty()) {
                    inputPass.setCompoundDrawables(null, null, icERR, null);
                    inputPass.setError("Please, input your password", icERR);
                }
                if (name_Register.isEmpty()) {
                    inputName.setCompoundDrawables(null, null, icERR, null);
                    inputName.setError("Please, input your name", icERR);
                }
                if (confirmPass_Register.isEmpty()) {
                    confirmPass.setCompoundDrawables(null, null, icERR, null);
                    confirmPass.setError("Please, input your confirm password", icERR);
                }
                if (!email_Register.isEmpty() && !password_Register.isEmpty() && !name_Register.isEmpty() && !confirmPass_Register.isEmpty()) {
                    if (password_Register.equals(confirmPass_Register)) {
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = mAuth.getCurrentUser();
                        progressDialog.show();
                        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        
                        firebaseAuth.createUserWithEmailAndPassword(email_Register, password_Register).addOnCompleteListener((task -> {
                            progressDialog.hide();
                            if (task.isSuccessful()) {
                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                            inputEmail.setText("");
                                            inputPass.setText("");
                                        } else {
                                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }));
                        
                                addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Map<String, Object> userinfo = new HashMap<>();
                                            userinfo.put("id", mAuth.getCurrentUser().getUid());
                                            userinfo.put("username", inputName.getText().toString());

                                            firestore.collection("User").document(mAuth.getCurrentUser().getUid()).set(userinfo);
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            Toast.makeText(RegisterActivity.this,"Success",Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                            finishAffinity();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }

                else {
                    Toast.makeText(RegisterActivity.this, "Please enter information.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        }

    private void addOnCompleteListener(RegisterActivity registerActivity, OnCompleteListener<AuthResult> onCompleteListener) {
    }


    private void addControls() {
        btnSignUp = findViewById(R.id.btnSignUp);
        inputEmail = findViewById(R.id.inputEmail);
        confirmPass = findViewById(R.id.confirmPass);
        inputPass = findViewById(R.id.inputPass);
        inputName = findViewById(R.id.inputName);
        btnSignIn = findViewById(R.id.btnSignIn);
    }
}


