package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button btnRegister,btnLogin;
    EditText emailLogin,pwLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
        addLogin();
    }

    private void addLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable icERR = getResources().getDrawable(R.drawable.ic_errorlogin);
                icERR.setBounds(0,0,icERR.getIntrinsicWidth(),icERR.getIntrinsicHeight());
                String email_user = emailLogin.getText().toString().trim();
                String password_user = pwLogin.getText().toString().trim();
                if (email_user.isEmpty())
                {
                    emailLogin.setCompoundDrawables(null,null,icERR,null);
                    emailLogin.setError("Nhập mẹ mày acc vào",icERR);
                }
                if (password_user.isEmpty()){
                    pwLogin.setCompoundDrawables(null,null,icERR,null);
                    pwLogin.setError("Nhập mật khẩu vào",icERR);
                }
                if (!email_user.isEmpty() && !password_user.isEmpty()){
                    emailLogin.setCompoundDrawables(null,null,null,null);
                    pwLogin.setCompoundDrawables(null,null,null,null);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void addControls() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);
        emailLogin = findViewById(R.id.emailLogin);
        pwLogin = findViewById(R.id.pwLogin);
    }

    private void addEvents() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_user = emailLogin.getText().toString().trim();
                String password_user = pwLogin.getText().toString().trim();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email_user, password_user)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);

                                } else {

                                    Toast.makeText(LoginActivity.this, "fail.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}