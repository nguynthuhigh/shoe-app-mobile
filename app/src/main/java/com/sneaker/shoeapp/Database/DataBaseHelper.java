package com.sneaker.shoeapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_CATE = "TABLE_CATE";
    public static final String TABLE_CUSTOMER = "TABLE_CUSTOMER";
    public static final String TABLE_PRO_DETAILS = "TABLE_PRO_DETAILS";
    //PRODUCT
    private static final String COLUMN_PRO_ID = "PRO_ID";
    private static final String COLUMN_PRO_NAME = "PRO_NAME";
    private static final String COLUMN_PRO_PRICE = "PRO_PRICE";
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
    private static final String COLUMN_CATE_NAME = "CATE_NAME";
    //FAVORITE
    private static final String COLUMN_FAV_ID ="FAV_ID";
    private static final String COLUMN_CUS_ID_FAV ="CUS_ID_FAV";
    private static final String COLUMN_PRO_ID_FAV ="PRO_ID";
    //PRO DETAILS
    private static final String COLUMN_ID_PRO_DETAILS ="ID_PRO_DETAILS";
    private static final String COLUMN_QUANTITY ="QUANTITY";
    private static final String COLUMN_QUANTITYSOLD ="QUANTITYSOLD ";
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
    private static final String COLUMN_QUANTITY_CART_DETAILS ="";
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
    //ORDER DETAILS
    private static final String COLUMN_ORDER_ID_DETAILS ="ORDER_ID_DETAILS";
    private static final String COLUMN_ORDER_DETAILS_QUANTITY ="ORDER_DETAILS_QUANTITY";
    private static final String COLUMN_ORDER_DETAILS_PRO_ID ="ORDER_DETAILS_PRO_ID";
    private static final String COLUMN_ORDER_DETAILS_ORDER_ID ="";
    private static final String COLUMN_ ="";
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


    public DataBaseHelper(@Nullable Context context) {
        super(context, "shoeApp.db", null, 1);

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
                + COLUMN_QUANTITYSOLD + " INTEGER, "
                + COLUMN_PRICE + " INTEGER, "
                + COLUMN_DISCOUNT + " INTEGER, "
                + COLUMN_COLOR + " INTEGER, "
                + COLUMN_SIZE + " INTEGER, "
                + COLUMN_PRO_ID_DETAIL + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_SIZE + ") REFERENCES " + TABLE_SIZE + "("+COLUMN_SIZE_ID+"),"
                + "FOREIGN KEY (" + COLUMN_COLOR + ") REFERENCES " + TABLE_COLOR + "("+COLUMN_COLOR_ID+"),"
                + "FOREIGN KEY (" + COLUMN_PRO_ID_DETAIL + ") REFERENCES " + TABLE_PRODUCT + "("+COLUMN_PRO_ID+"));";


        db.execSQL(CREATE_TABLE_AdminUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
