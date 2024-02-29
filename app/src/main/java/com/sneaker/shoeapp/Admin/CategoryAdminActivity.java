package com.sneaker.shoeapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sneaker.shoeapp.Database.DataBaseHelper;
import com.sneaker.shoeapp.MainActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Category;

public class CategoryAdminActivity extends AppCompatActivity {
Button saveCategory,outputCategory;
EditText nameCate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_admin);
        addControls();
        addEvents();
    }

    private void addEvents() {
        saveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category;
                try{
                    category = new Category(nameCate.getText().toString());
                    Toast.makeText(CategoryAdminActivity.this,"Successfully!",Toast.LENGTH_SHORT).show();
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(CategoryAdminActivity.this);
                    dataBaseHelper.addCategory(category);
                }
                catch (Exception e){
                    Toast.makeText(CategoryAdminActivity.this,"ERROR!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        outputCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dbHelper = new DataBaseHelper(CategoryAdminActivity.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM TABLE_CATEGORY", null);

                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        // Lấy dữ liệu từ cột COLUMN_CATE_NAME
                        String data = cursor.getString(cursor.getColumnIndexOrThrow("CATE_NAME"));

                        // Hiển thị dữ liệu
                        Toast.makeText(CategoryAdminActivity.this, data, Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());

                    // Đóng Cursor sau khi sử dụng
                    cursor.close();
                } else {
                    Toast.makeText(CategoryAdminActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void addControls() {
        outputCategory = findViewById(R.id.outputCategory);
        saveCategory = findViewById(R.id.saveCategory);
        nameCate = findViewById(R.id.nameCate);
    }


}