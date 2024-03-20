package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.sneaker.shoeapp.model.User;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Objects;

import io.grpc.Codec;


public class EditProfileActivity extends AppCompatActivity {
    TextView name_userEdit;
    ImageButton closeItem,btnBack;
    Button btnRename,btnChangePassword;
    EditText  edtPassNew, edtConfirm,edtUserName;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseStorage storage= FirebaseStorage.getInstance();


    ProfileActivity profileActivity;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        addControls();

        btnBack.setOnClickListener(new View.OnClickListener() {
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
        closeItem = dialog.findViewById(R.id.closeItem);
        edtUserName= dialog.findViewById(R.id.editUsername);

        closeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUserName.getText().toString();
                    db.collection("User").document(user.getUid()).update("username" , edtUserName.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(profileActivity, "Update Success", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
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
        edtPassNew =dialog.findViewById(R.id.edtPassNew);
        edtConfirm=dialog.findViewById(R.id.edtConfirm);
        btnSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passNew=edtPassNew.getText().toString();
                String confirmPass = edtConfirm.getText().toString();
                if (passNew.equals(confirmPass)) {

                    user.updatePassword(passNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(EditProfileActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
                else {
                    Toast.makeText(profileActivity, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    private void addControls() {
        btnRename= findViewById(R.id.btnRename);
        btnChangePassword=findViewById(R.id.btnChangePassword);
        closeItem = findViewById(R.id.closeItem);
        btnBack = findViewById(R.id.btnBack);
        name_userEdit=findViewById(R.id.name_userEdit);
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