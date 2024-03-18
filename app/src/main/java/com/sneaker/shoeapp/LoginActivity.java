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
import com.sneaker.shoeapp.Admin.AdminHomeActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnRegister,btnLogin;
    EditText emailLogin,pwLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
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
                Drawable icERR = getResources().getDrawable(R.drawable.ic_errorlogin);
                icERR.setBounds(0,0,icERR.getIntrinsicWidth(),icERR.getIntrinsicHeight());
                String email_user = emailLogin.getText().toString().trim();
                String password_user = pwLogin.getText().toString().trim();
                if (email_user.isEmpty() )
                {
                    emailLogin.setCompoundDrawables(null,null,icERR,null);
                    emailLogin.setError("Please, input your email",icERR);
                }
                if ( password_user.isEmpty())
                {
                    pwLogin.setCompoundDrawables(null,null,icERR,null);
                    pwLogin.setError("Please, input your password",icERR);
                }

                if (!email_user.isEmpty() && !password_user.isEmpty()){
                    email_user = emailLogin.getText().toString().trim();
                    password_user = pwLogin.getText().toString().trim();
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email_user, password_user)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user.isEmailVerified()){
                                            String admin =user.getEmail();
                                            if(!"admin@gmail.com".equals(admin)){
                                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                                finish();
                                                startActivity(intent);
                                            }
                                            else{
                                                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                                finish();
                                                startActivity(intent);
                                            }
                                        }
                                        else{
                                            Toast.makeText(LoginActivity.this, "Please verify your email before login!",
                                                    Toast.LENGTH_SHORT).show();
                                        }


                                    } else {

                                        Toast.makeText(LoginActivity.this, "Account is wrong",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
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