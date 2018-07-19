package com.example.shirayama.mymemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;


public class SubActivity extends AppCompatActivity {
    Integer id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        id = intent.getIntExtra("id",-1);
        String content = intent.getStringExtra("content");
        Integer status = intent.getIntExtra("status",-1);
        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        Button reg_btn = (Button) findViewById(R.id.button);
        EditText editContent = (EditText) findViewById(R.id.editContent);
        editTitle.setText(title);
        editContent.setText(content);

        if(status==0){
            reg_btn.setText("登録");
            reg_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    register();
                }
            });
        }else{
            reg_btn.setText("更新");
            reg_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update(id);
                }
            });

            LinearLayout linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
            View convertView = getLayoutInflater().inflate(R.layout.button,null);
            Button delete_button = (Button)convertView.findViewById(R.id.delete_button);
            linearlayout.addView(delete_button);
            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(id);
                }
            });
        }


    }

    public void register() {

        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        String title = editTitle.getText().toString();
        EditText editContent = (EditText) findViewById(R.id.editContent);
        String content = editContent.getText().toString();
        if (editTitle.equals("")) {
            Toast.makeText(SubActivity.this, "名前を入力してください。",
                    Toast.LENGTH_SHORT).show();
        } else {
            MyOpenHelper helper = new MyOpenHelper(this);
            final SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues insertValues = new ContentValues();
            insertValues.put("title", title);
            insertValues.put("content", content);
            long id = db.insert("memo", null, insertValues);
            db.close();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void update(Integer id) {

        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        String title = editTitle.getText().toString();
        EditText editContent = (EditText) findViewById(R.id.editContent);
        String content = editContent.getText().toString();

        if (editTitle.equals("")) {
            Toast.makeText(SubActivity.this, "名前を入力してください。",
                    Toast.LENGTH_SHORT).show();
        } else {
            MyOpenHelper helper = new MyOpenHelper(this);
            final SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues updateValues = new ContentValues();
            updateValues.put("title", title);
            updateValues.put("content", content);
            db.update("memo", updateValues, "_id="+String.valueOf(id), null);
            db.close();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void delete(Integer id) {

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("memo", "_id="+String.valueOf(id), null);
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
