package com.example.upipaymentgateway.DBHepler;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelperSqlite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteUpiPayment.db";

    private static final int DATABASE_VERSION = 7;

    public static final String ITEM_TABLE_NAME = "upipayment";  // table 1
    public static final String ITEM_TABLE_NAME2 = "upipaymenthistory"; // table 2

    public static final String ITEM_COLUMN_ID = "_id";//0
    public static final String UPI_PHONE_NO = "phoneno";//1
    public static final String UPI_PHONE_OTP = "phoneotp";//2
    public static final String UPI_PIN = "upipin";//3
    public static final String UPI_NUMBER = "upino";//4
    public static final String UPI_NAME = "name";//5




    public static final String ITEM_COLUMN_ID1 = "_id";//0

    public static final String UPI_PHONE_NO1 = "phoneno";//1
    public static final String UPI_PAID_AMOUNT = "paidamount";//1
    public static final String UPI_NUMBER_HISTORY = "upid";//2
    public static final String UPI_NAME_HISTORY = "upiname";//3
    public static final String UPI_DESCRIPTION = "upidescription";//4
    public static final String UPI_DATE_TIME = "upidatatime";//5
    public static final String UPI_TRANSCTION_STATUS = "transctionstatus";//6
    public static final String UPI_CURRENT_DATE = "upicurrentdate";//7


    public DBHelperSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + ITEM_TABLE_NAME +
                        "(" + ITEM_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        UPI_PHONE_NO + " TEXT, " +
                        UPI_PHONE_OTP + " TEXT, " +
                        UPI_PIN + " TEXT,"+
                        UPI_NAME + " TEXT,"+
                        UPI_NUMBER + " TEXT)"

        );

        db.execSQL(
                "CREATE TABLE " + ITEM_TABLE_NAME2 +
                        "(" + ITEM_COLUMN_ID1 + " INTEGER PRIMARY KEY, " +
                        UPI_PHONE_NO1 + " TEXT, " +
                        UPI_PAID_AMOUNT + " TEXT, " +
                        UPI_NUMBER_HISTORY + " TEXT,"+
                        UPI_NAME_HISTORY + " TEXT,"+
                        UPI_DESCRIPTION + " TEXT,"+
                        UPI_TRANSCTION_STATUS + " TEXT,"+
                        UPI_CURRENT_DATE + " TEXT,"+
                        UPI_DATE_TIME + " TEXT)"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME2);
        onCreate(db);
    }

    public boolean insertUPI(String itemname, String itemid) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UPI_PHONE_NO, itemname);
        contentValues.put(UPI_PHONE_OTP, itemid);

        db.insert(ITEM_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updatePinId(String phoneNo, String pinno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UPI_PIN, pinno);

        db.update(ITEM_TABLE_NAME, contentValues, UPI_PHONE_NO + " = ? ", new String[]{phoneNo});
        return true;
    }

    public boolean updateUpiId(String phoneNo, String upino,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UPI_NUMBER, upino);
        contentValues.put(UPI_NAME, name);

        db.update(ITEM_TABLE_NAME, contentValues, UPI_PHONE_NO + " = ? ", new String[]{phoneNo});
        return true;
    }

    public int getPhoneById(String phoneno) {

        String countQuery = "SELECT  * FROM " + ITEM_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ITEM_TABLE_NAME, new String[]{UPI_PHONE_NO}, UPI_PHONE_NO + "=?", new String[]{phoneno}, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getUpitransition(String phoneNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM " + ITEM_TABLE_NAME2;
        Cursor cursor = db.query(ITEM_TABLE_NAME2, new String[]{
                ITEM_COLUMN_ID1, UPI_PHONE_NO1, UPI_NUMBER_HISTORY, UPI_NAME_HISTORY,
                UPI_DESCRIPTION,UPI_PAID_AMOUNT,UPI_DATE_TIME,UPI_TRANSCTION_STATUS,UPI_CURRENT_DATE
        }, UPI_PHONE_NO1 + "=?", new String[]{String.valueOf(phoneNo)}, null, null, null, null);

        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("ITEM_COLUMN_ID1", cursor.getString(cursor.getColumnIndex(ITEM_COLUMN_ID1)));
            user.put("UPI_PHONE_NO1", cursor.getString(cursor.getColumnIndex(UPI_PHONE_NO1)));
            user.put("UPI_NUMBER_HISTORY", cursor.getString(cursor.getColumnIndex(UPI_NUMBER_HISTORY)));
            user.put("UPI_NAME_HISTORY", cursor.getString(cursor.getColumnIndex(UPI_NAME_HISTORY)));
            user.put("UPI_DESCRIPTION", cursor.getString(cursor.getColumnIndex(UPI_DESCRIPTION)));
            user.put("UPI_PAID_AMOUNT", cursor.getString(cursor.getColumnIndex(UPI_PAID_AMOUNT)));
            user.put("UPI_DATE_TIME", cursor.getString(cursor.getColumnIndex(UPI_DATE_TIME)));
            user.put("UPI_TRANSCTION_STATUS", cursor.getString(cursor.getColumnIndex(UPI_TRANSCTION_STATUS)));
            user.put("UPI_CURRENT_DATE", cursor.getString(cursor.getColumnIndex(UPI_CURRENT_DATE)));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }




    public boolean insertUPITransitionHistory(String number, String paidAmount, String UPIID,
                                              String customerName, String description ,String datatime,String transStatus,String crtdata) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UPI_PHONE_NO1, number);
        contentValues.put(UPI_PAID_AMOUNT, paidAmount);
        contentValues.put(UPI_NUMBER_HISTORY, UPIID);
        contentValues.put(UPI_NAME_HISTORY, customerName);
        contentValues.put(UPI_DESCRIPTION, description);
        contentValues.put(UPI_DATE_TIME, datatime);
        contentValues.put(UPI_TRANSCTION_STATUS, transStatus);
        contentValues.put(UPI_CURRENT_DATE, crtdata);

        db.insert(ITEM_TABLE_NAME2, null, contentValues);
        return true;
    }

    @SuppressLint("Range")
    public double getTotalAmountForDate(String phoneNumber, String currentDate) {
        double totalAmount = 0.0;
        String[] columns = {"SUM(" + UPI_PAID_AMOUNT + ") AS " + UPI_PAID_AMOUNT}; // Alias the summed column
        String selection = UPI_PHONE_NO1 + " = ? AND " + UPI_CURRENT_DATE + " = ?";
        String[] selectionArgs = {phoneNumber, currentDate};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(ITEM_TABLE_NAME2, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(cursor.getColumnIndex(UPI_PAID_AMOUNT)); // Access by column name
            cursor.close();
        }

        return totalAmount;
    }



    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getPhoneByName(String phoneNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM " + ITEM_TABLE_NAME;
        Cursor cursor = db.query(ITEM_TABLE_NAME, new String[]{
                UPI_PIN, UPI_NAME, UPI_NUMBER
        }, UPI_PHONE_NO + "=?", new String[]{String.valueOf(phoneNo)}, null, null, null, null);

        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("UPI_PIN", cursor.getString(cursor.getColumnIndex(UPI_PIN)));
            user.put("UPI_NAME", cursor.getString(cursor.getColumnIndex(UPI_NAME)));
            user.put("UPI_NUMBER", cursor.getString(cursor.getColumnIndex(UPI_NUMBER)));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }


}
