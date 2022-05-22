package com.example.fproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    Button btnAdd,btnRead,btnClear;
    EditText /*etName,*/etText;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        /*etName = findViewById(R.id.etName);*/
        etText = findViewById(R.id.etText);
        //объект для создания и управоения версиями бд
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        //object dlya dannyx
        ContentValues cv = new ContentValues();
        //poluchaem dannie iz polei vvoda
       /* String name = etName.getText().toString();*/
        String text = etText.getText().toString();
        //connect to Db
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "INSERT");
                //podgotovim dannie dlya vstavki d vide par: stolbec-znachenie
               /* cv.put("name", name);*/
                cv.put("text", text);
                //vstavlyaem zapis i poluchaem ee id
                long rowId = db.insert("mytable",null,cv);
                Log.d(LOG_TAG,"Added row" + rowId);
                etText.setText(null);
                break;
            case R.id.btnRead:
                //zapros vcex dannyx iz table i poluchaem Cursor
                Cursor c = db.query("mytable", null,null,null,null,null,null);
                //stavim Cursor na pervoe mesto, esli strok net - false
                if (c.moveToFirst()) {
                    //opredelyaem imena stolbcov po imeni v vyborke
                    int idColIndex = c.getColumnIndex("id");
                    /*int nameColIndex = c.getColumnIndex("name");*/
                    int textColIndex = c.getColumnIndex("text");
                    do {
                        Log.d(LOG_TAG,"id= " + c.getInt(idColIndex)+
                                /*",  name = " + c.getString(nameColIndex)+*/
                                ", text= " + c.getString(textColIndex));
                        // perexod na sled stroky, esli net sled to false- vixod iz cikla
                    } while(c.moveToNext());
                } else
                    Log.d(LOG_TAG,"0 rows");
                c.close();
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG,"CLEAR ALL");
                int clearCount = db.delete("mytable", null, null);
                Log.d(LOG_TAG, "Deleted number of colums " + clearCount);
                break;
        }
        dbHelper.close();


    }
}