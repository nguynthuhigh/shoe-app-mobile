package com.sneaker.shoeapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import com.sneaker.shoeapp.model.Category;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_CATE = "TABLE_CATE";
    public static final String TABLE_CUSTOMER = "TABLE_CUSTOMER";
    public static final String TABLE_PRO_DETAILS = "TABLE_PRO_DETAILS";
    public static final String TABLE_ORDER_DETAILS = "TABLE_ORDER_DETAILS";
    //PRODUCT
    private static final String COLUMN_PRO_ID = "PRO_ID";
    private static final String COLUMN_PRO_NAME = "PRO_NAME";

    private static final String COLUMN_PRO_CATE = "PRO_CATE";

    //ADMIN
    private static final String TABLE_ADMIN = "TABLE_ADMIN";
    private static final String COLUMN_USER_ID = "USER_ID";
    private static final String COLUMN_USER_NAME = "USER_NAME";
    private static final String COLUMN_PASSWORD = "PASSWORD";

    //CUSTOMER
    private static final String COLUMN_CUS_ID ="CUS_ID";
    private static final String COLUMN_CUS_EMAIL ="CUS_EMAIL";
    private static final String COLUMN_CUS_PASS ="CUS_PASS";
    private static final String COLUMN_CUS_NAME ="CUS_NAME";
    //CATEGORY
    private static final String COLUMN_CATE_ID = "CATE_ID";
    public static final String COLUMN_CATE_NAME = "CATE_NAME";
    //FAVORITE
    private static final String COLUMN_FAV_ID ="FAV_ID";
    private static final String COLUMN_CUS_ID_FAV ="CUS_ID_FAV";
    private static final String COLUMN_PRO_ID_FAV ="PRO_ID";
    //PRO DETAILS
    private static final String COLUMN_ID_PRO_DETAILS ="ID_PRO_DETAILS";
    private static final String COLUMN_QUANTITY ="QUANTITY";
    private static final String COLUMN_QUANTITY_SOLD ="QUANTITY_SOLD ";
    private static final String COLUMN_PRICE ="PRICE";
    private static final String COLUMN_DISCOUNT ="DISCOUNT";
    private static final String COLUMN_SIZE ="SIZE";
    private static final String COLUMN_COLOR ="COLOR";
    private static final String COLUMN_PRO_ID_DETAIL ="PRO_ID_DETAIL";
    //CART
    private static final String COLUMN_CART_ID ="CART_ID";
    private static final String COLUMN_TOTAL_VALUE ="TOTAL_VALUE";
    private static final String COLUMN_TEMP_VALUE ="TEMP_VALUE";
    private static final String COLUMN_CUS_ID_CART ="CUS_ID_CART";
    //CART DETAILS
    private static final String COLUMN_CART_ID_DETAILS ="CART_ID_DETAILS";
    private static final String COLUMN_TOTAL_VALUE_CART_DETAILS ="TOTAL_VALUE_CART_DETAILS";
    private static final String COLUMN_QUANTITY_CART_DETAILS ="QUANTITY_CART_DETAILS";
    private static final String COLUMN_CART_ID_CART_DETAILS ="ID_CART_DETAILS";
    private static final String COLUMN_PRO_ID_CART_DETAILS ="RO_ID_CART_DETAILS";
    //ORDER
    private static final String COLUMN_ORDER_ID ="ORDER_ID";
    private static final String COLUMN_ORDER_DATE ="ORDER_DATE";
    private static final String COLUMN_ORDER_STATUS ="ORDER_STATUS";
    private static final String COLUMN_QUANTITY_ORDER ="QUANTITY_ORDER";
    private static final String COLUMN_CUS_ADDRESS_ORDER ="CUS_ADDRESS_ORDER";
    private static final String COLUMN_CUS_ID_ORDER ="CUS_ID_ORDER";
    private static final String COLUMN_METHOD_PAYMENT_ORDER ="METHOD_PAYMENT_ORDER";
    private static final String COLUMN_ID_CART_ORDER ="ID_CART_ORDER";
    //ORDER DETAILS
    private static final String COLUMN_ORDER_ID_DETAILS ="ORDER_ID_DETAILS";
    private static final String COLUMN_ORDER_DETAILS_QUANTITY ="ORDER_DETAILS_QUANTITY";
    private static final String COLUMN_ORDER_DETAILS_PRO_ID ="ORDER_DETAILS_PRO_ID";
    private static final String COLUMN_ORDER_DETAILS_ORDER_ID ="ORDER_DETAILS_ORDER_ID";

    public static final String TABLE_PRODUCT = "TABLE_PRODUCT";
    // SIZE
    private static final String COLUMN_SIZE_ID ="SIZE_ID";
    private static final String COLUMN_SIZE_NUMBER ="SIZE_NUMBER";
    //COLOR
    private static final String COLUMN_COLOR_ID ="COLOR_ID";
    private static final String COLUMN_COLOR_CODE ="COLOR_CODE";
    private static final String COLUMN_COLOR_NAME ="COLOR_NAME";
    public static final String TABLE_SIZE = "TABLE_SIZE";
    public static final String TABLE_COLOR = "TABLE_COLOR";
    public static final String TABLE_ORDER = "TABLE_ORDER";
    public static final String TABLE_PAYMENT = "TABLE_PAYMENT";
    //PAYMENT
    private static final String COLUMN_PAYMENT_ID ="PAYMENT_ID";
    private static final String COLUMN_PAYMENT_METHOD ="PAYMENT_METHOD";

    public static final String TABLE_CART = "TABLE_CART";
    public static final String TABLE_LIST_IMAGE = "TABLE_LIST_IMAGE";
    //LIST IMAGE
    private static final String COLUMN_LIST_IMG_ID ="LIST_IMG_ID";
    private static final String COLUMN_IMG_PATH ="IMG_PATH";
    private static final String COLUMN_PRO_ID_LIST_IMG ="PRO_ID_LIST_IMG";

    public static final String TABLE_FAV = "TABLE_FAV";
    public static final String TABLE_CART_DETAILS = "TABLE_CART_DETAILS";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "shoeApp.db", null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_AdminUser = "CREATE TABLE " + TABLE_ADMIN + " ("
                + COLUMN_USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT); ";
        String CREATE_TABLE_CUSTOMER = "CREATE TABLE " + TABLE_CUSTOMER + " ("
                + COLUMN_CUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CUS_EMAIL + " TEXT, "
                + COLUMN_CUS_NAME + " TEXT, "
                + COLUMN_CUS_PASS + " TEXT);";
        String CREATE_TABLE_Category = "CREATE TABLE " + TABLE_CATE + " ("
                + COLUMN_CATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CATE_NAME + " TEXT);";
        String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + " ("
                + COLUMN_PRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRO_NAME + " TEXT, "

                + COLUMN_PRO_CATE + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_PRO_CATE + ") REFERENCES " + TABLE_CATE + "(" + COLUMN_CATE_ID + "));";
        String CREATE_TABLE_SIZE = "CREATE TABLE " + TABLE_SIZE + " ("
                + COLUMN_SIZE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SIZE_NUMBER + " INTEGER);";
        String CREATE_TABLE_COLOR = "CREATE TABLE " + TABLE_COLOR + " ("
                + COLUMN_COLOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COLOR_CODE + " TEXT, "
                + COLUMN_COLOR_NAME + " TEXT);";
        String CREATE_TABLE_PRO_DETAILS = "CREATE TABLE " + TABLE_PRO_DETAILS + " ("
                + COLUMN_ID_PRO_DETAILS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_QUANTITY + " INTEGER, "
                + COLUMN_QUANTITY_SOLD + " INTEGER, "
                + COLUMN_PRICE + " INTEGER, "
                + COLUMN_DISCOUNT + " INTEGER, "
                + COLUMN_COLOR + " INTEGER, "
                + COLUMN_SIZE + " INTEGER, "
                + COLUMN_PRO_ID_DETAIL + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_SIZE + ") REFERENCES " + TABLE_SIZE + "("+COLUMN_SIZE_ID+"),"
                + "FOREIGN KEY (" + COLUMN_COLOR + ") REFERENCES " + TABLE_COLOR + "("+COLUMN_COLOR_ID+"),"
                + "FOREIGN KEY (" + COLUMN_PRO_ID_DETAIL + ") REFERENCES " + TABLE_PRODUCT + "("+COLUMN_PRO_ID+"));";
        String CREATE_TABLE_PAYMENT = "CREATE TABLE " + TABLE_PAYMENT + " ("
                + COLUMN_PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PAYMENT_METHOD + " TEXT);";


        String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_CART + " ("
                + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TOTAL_VALUE + " INTEGER,"
                + COLUMN_TEMP_VALUE + " INTEGER, "
                + COLUMN_CUS_ID_CART + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_CUS_ID_CART + ") REFERENCES "+ TABLE_CUSTOMER + "(" + COLUMN_CUS_ID + "));";
        String CREATE_TABLE_CART_DETAILS = "CREATE TABLE " + TABLE_CART_DETAILS + " ("
                + COLUMN_CART_ID_DETAILS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TOTAL_VALUE_CART_DETAILS + " INTEGER,"
                + COLUMN_QUANTITY_CART_DETAILS + " INTEGER, "
                + COLUMN_CART_ID_CART_DETAILS + " INTEGER, "
                + COLUMN_PRO_ID_CART_DETAILS + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_CART_ID_CART_DETAILS + ") REFERENCES "+ TABLE_CART + "(" + COLUMN_CART_ID + "),"
                + "FOREIGN KEY (" + COLUMN_PRO_ID_CART_DETAILS + ") REFERENCES "+ TABLE_PRO_DETAILS + "(" + COLUMN_ID_PRO_DETAILS + "));";
        String CREATE_TABLE_LIST_IMAGE = "CREATE TABLE " + TABLE_LIST_IMAGE + " ("
                + COLUMN_LIST_IMG_ID + " INTEGER, "
                + COLUMN_IMG_PATH + " INTEGER, "
                + COLUMN_PRO_ID_LIST_IMG + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_PRO_ID_LIST_IMG + ") REFERENCES "+ TABLE_PRO_DETAILS + "(" + COLUMN_ID_PRO_DETAILS + "));";
        String CREATE_TABLE_FAVORITE = "CREATE TABLE " + TABLE_FAV + " ("
                + COLUMN_FAV_ID + " INTEGER, "
                + COLUMN_CUS_ID_FAV + " INTEGER, "
                + COLUMN_PRO_ID_FAV + " INTEGER, "

                + "FOREIGN KEY (" + COLUMN_PRO_ID_FAV + ") REFERENCES "+ TABLE_PRO_DETAILS + "(" + COLUMN_ID_PRO_DETAILS + "),"
                + "FOREIGN KEY (" + COLUMN_CUS_ID_FAV + ") REFERENCES "+ TABLE_CUSTOMER + "(" + COLUMN_CUS_ID + "));";
        String CREATE_TABLE_ORDER = "CREATE TABLE "+ TABLE_ORDER + " ("
                + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ORDER_DATE + " DATE, "
                + COLUMN_ORDER_STATUS + " INTEGER, "
                + COLUMN_QUANTITY_ORDER + " INTEGER, "
                + COLUMN_CUS_ADDRESS_ORDER + " TEXT, "
                + COLUMN_CUS_ID_ORDER + " INTEGER, "
                + COLUMN_METHOD_PAYMENT_ORDER + " INTEGER, "
                + COLUMN_ID_CART_ORDER + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_CUS_ID_ORDER + ") REFERENCES " + TABLE_CUSTOMER + "(" + COLUMN_CUS_ID + "), "
                + "FOREIGN KEY (" + COLUMN_ID_CART_ORDER + ") REFERENCES " + TABLE_CART + "(" + COLUMN_CART_ID + "), "
                + "FOREIGN KEY (" + COLUMN_METHOD_PAYMENT_ORDER + ") REFERENCES " + TABLE_PAYMENT + "(" + COLUMN_PAYMENT_ID + ")); ";
        String CREATE_TABLE_ORDER_DETAILS = "CREATE TABLE "+ TABLE_ORDER_DETAILS +" ("
                + COLUMN_ORDER_ID_DETAILS +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ORDER_DETAILS_QUANTITY + " INTEGER, "
                + COLUMN_ORDER_DETAILS_PRO_ID + " INTEGER, "
                + COLUMN_ORDER_DETAILS_ORDER_ID + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_ORDER_DETAILS_PRO_ID + ") REFERENCES "+ TABLE_PRO_DETAILS + "(" + COLUMN_ID_PRO_DETAILS + "),"
                + "FOREIGN KEY (" + COLUMN_ORDER_DETAILS_ORDER_ID + ") REFERENCES "+ TABLE_ORDER + "(" + COLUMN_ORDER_ID + "));";
        db.execSQL(CREATE_TABLE_Category);
        db.execSQL(CREATE_TABLE_AdminUser);
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_SIZE);
        db.execSQL(CREATE_TABLE_COLOR);
        db.execSQL(CREATE_TABLE_PRO_DETAILS);
        db.execSQL(CREATE_TABLE_PAYMENT);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_CART_DETAILS);
        db.execSQL(CREATE_TABLE_LIST_IMAGE);
        db.execSQL(CREATE_TABLE_FAVORITE);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_ORDER_DETAILS);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATE_NAME, category.getName());

        long insert = db.insert(TABLE_CATE, null, contentValues);

        return insert != -1;
    }

    public List<Category> getCategory(){
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CATE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                String nameCate = cursor.getString(1);
                Category category = new Category(nameCate);
                list.add(category);
            }
            while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return list;
    }
}
