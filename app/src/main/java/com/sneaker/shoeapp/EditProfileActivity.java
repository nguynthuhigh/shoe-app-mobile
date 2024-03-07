package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sneaker.shoeapp.model.User;

import java.net.PasswordAuthentication;


public class EditProfileActivity extends AppCompatActivity {
    TextView name_userEdit;

    Button btnRename,btnConfirm,btnChangeAvt,btnChangeEmail,btnChangePassword;
    EditText edtPassOld, edtPassNew, edtConfirm,edtUserName,edtSurName;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    ProfileActivity profileActivity;

    AuthCredential credential = EmailAuthProvider
            .getCredential("user@example.com", "password1234");


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
                openDialogRename(Gravity.CENTER);
            }
        });
        btnChangeAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAvt(Gravity.CENTER);
            }
        });

        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogEmail(Gravity.CENTER);
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogPassword(Gravity.CENTER);
            }
        });
    }

    private void openDialogRename(int center) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_rename);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = center;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == center ) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        LinearLayout btnSaveName = dialog.findViewById(R.id.btnSaveName);

        btnSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openDialogAvt(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_changeavt);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity ) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        LinearLayout btnsaveAvt = dialog.findViewById(R.id.btnsaveAvt);

        btnsaveAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void openDialogEmail(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_changeemail);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity ) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        LinearLayout btnSaveEmail = dialog.findViewById(R.id.btnSaveEmail);

        btnSaveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openDialogPassword(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_changepass);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity ) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        LinearLayout btnSavePass = dialog.findViewById(R.id.btnSavePass);

        btnSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                onClickChangePassWord();
            }


        });
        dialog.show();
    }

    private void onClickChangePassWord() {
        String strConFirmPass= edtConfirm.getText().toString().trim();
        String strNewPass=edtPassNew.getText().toString().trim();
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()&&strConFirmPass.equals(strNewPass)){

                    user.updatePassword(strNewPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EditProfileActivity.this, "User Password update.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });





    }

    private  void ChangeName(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
      String name=edtUserName.getText().toString().trim();
        UserProfileChangeRequest profileChangeRequest=new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build();
        user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(EditProfileActivity.this, "Update Profile Success", Toast.LENGTH_SHORT).show();
                    profileActivity.showUserInformation();
                }
            }
        });

    }
    private void addControls() {
        btnRename= findViewById(R.id.btnRename);
        btnConfirm=findViewById(R.id.btnConfirm);
        btnChangeAvt=findViewById(R.id.btnChangeAvt);
        btnChangeEmail=findViewById(R.id.btnChangeEmail);
        btnChangePassword=findViewById(R.id.btnChangePassword);
        edtPassOld=findViewById(R.id.edtPassOld);
        edtPassNew=findViewById(R.id.edtPassNew);
        edtConfirm=findViewById(R.id.edtConfirm);
        name_userEdit=findViewById(R.id.name_userEdit);
        edtUserName=findViewById(R.id.editUsername);
        edtSurName=findViewById(R.id.editSurname);
        if(user !=null){
            DocumentReference documentReference =db.collection("User").document(user.getUid());
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    name_userEdit.setText(documentSnapshot.getString("username"));
                }
            });
        }
    }


}