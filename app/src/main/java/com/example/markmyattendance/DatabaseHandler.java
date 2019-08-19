package com.example.markmyattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager.db";
    private static final String TABLE_CLASS = "class";
    private static final String TABLE_STUDENT = "student";
    private static final String TABLE_RECORD = "record";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ROLL = "roll";
    private static final String KEY_CLASS = "class";
    private static final String KEY_LINE = "line";
    private static final String KEY_TIME = "time";
    private static final String KEY_LECT = "lect";
    private static final String KEY_STATUS = "status";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLASS_TABLE = "CREATE TABLE " + TABLE_CLASS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CLASS + " TEXT," + KEY_LINE + " TEXT" + ")";

        String CREATE_STUDENT_TABLE= "CREATE TABLE " + TABLE_STUDENT + "( "
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ROLL + " TEXT,"
                + KEY_NAME + " TEXT," + KEY_CLASS + " TEXT," + KEY_LINE + " TEXT" + ")";

        String CREATE_RECORD_TABLE= "CREATE TABLE " + TABLE_RECORD + "( "
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ROLL + " TEXT,"
                + KEY_NAME + " TEXT," + KEY_CLASS + " TEXT," + KEY_LINE + " TEXT," + KEY_LECT + " TEXT," + KEY_STATUS + " TEXT," + KEY_TIME + " TEXT" + ")";

        db.execSQL(CREATE_CLASS_TABLE);
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_RECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
        onCreate(db);
    }

    void addClass(StudentClass studentClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLASS, studentClass.getStd());
        values.put(KEY_LINE,studentClass.getLine());
        db.insert(TABLE_CLASS, null, values);
        db.close(); // Closing database connection
    }

    void addRecord(RecordClass recordClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLASS, recordClass.getStd());
        values.put(KEY_LINE,recordClass.getLine());
        values.put(KEY_NAME, recordClass.getName());
        values.put(KEY_ROLL, recordClass.getRoll());
        values.put(KEY_LECT, recordClass.getLect());
        values.put(KEY_STATUS,recordClass.getStatus());
        values.put(KEY_TIME,recordClass.getTime());
        db.insert(TABLE_RECORD, null, values);
        db.close();
    }

    void addStudent(StudentList studentList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, studentList.getName());
        values.put(KEY_ROLL, studentList.getRoll());
        values.put(KEY_CLASS, studentList.getStd());
        values.put(KEY_LINE, studentList.getLine());
        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public List<StudentList> getAllStudentList() {
        List<StudentList> contactList = new ArrayList<StudentList>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentList studentList = new StudentList();
                studentList.setName(cursor.getString(2));
                studentList.setRoll(cursor.getString(1));
                studentList.setStd(cursor.getString(3));
                studentList.setLine(cursor.getString(4));
                studentList.setId(Integer.parseInt(cursor.getString(0)));
                contactList.add(studentList);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<RecordClass> getAllRecord() {
        List<RecordClass> contactList = new ArrayList<RecordClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RECORD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecordClass studentList = new RecordClass();
                studentList.setName(cursor.getString(2));
                studentList.setRoll(cursor.getString(1));
                studentList.setStd(cursor.getString(3));
                studentList.setLine(cursor.getString(4));
                studentList.setId(Integer.parseInt(cursor.getString(0)));
                studentList.setLect(cursor.getString(5));
                studentList.setStatus(cursor.getString(6));
                studentList.setTime(cursor.getString(7));
                contactList.add(studentList);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<StudentClass> getAllClass() {
        List<StudentClass> contactList = new ArrayList<StudentClass>();

        String selectQuery = "SELECT  * FROM " + TABLE_CLASS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                StudentClass studentClass = new StudentClass();
                studentClass.setStd(cursor.getString(1));
                studentClass.setLine(cursor.getString(2));
                studentClass.setId(Integer.parseInt(cursor.getString(0)));
                contactList.add(studentClass);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    void deleteClass(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{""+i};
        db.delete(TABLE_CLASS,KEY_ID+"=?",args);
    }
    void deleteStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{""+i};
        db.delete(TABLE_STUDENT,KEY_ID+"=?",args);
    }

   /* void deleteall(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_CLASS);
        db.close();
    } */

   /* void updatecon(String name,String s1,String s2,String idd) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        int s3 = Integer.valueOf(getContact(Integer.valueOf(idd)).get_total()) - Integer.valueOf(s2) + Integer.valueOf(s1) ;
        String s4 = ""+s3;
        values.put(KEY_NAME, name); // Contact Name
        values.put(KEY_TOTAL, s4); // Contact Phone

        String[] args = new String[]{""+idd};
        db.update(TABLE_CONTACTS,values,KEY_ID+"=?",args);
        db.close();
    } */

    /*Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_TOTAL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }*/

}


