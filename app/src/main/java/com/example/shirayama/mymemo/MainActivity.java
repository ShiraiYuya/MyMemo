package com.example.shirayama.mymemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "select _id, title, content from memo;";
        Cursor c = db.rawQuery(sql, null);
        boolean mov = c.moveToFirst();

        ArrayList<MemoBean> list = new ArrayList<MemoBean>();
        while (mov) {
            MemoBean memo = new MemoBean();
            memo.setId(c.getInt(0));
            memo.setTitle(c.getString(1));
            memo.setContent(c.getString(2));
            list.add(memo);
            mov = c.moveToNext();
        }
        c.close();
        db.close();
        helper.close();
        ListView listview = (ListView)findViewById(R.id.listview);
        ListAdapter adapter = new ListAdapter(this, 0, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // リストビューの項目を取得
                ListView listview = (ListView) parent;
                MemoBean memo = (MemoBean) listview.getItemAtPosition(position);
                editMemo(memo);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNew(View v) {
        Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra("status",0);
        startActivity(intent);
    }

    public void editMemo(MemoBean memo) {
        Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra("status",1);
        intent.putExtra("id",memo.getId());
        intent.putExtra("title",memo.getTitle());
        intent.putExtra("content",memo.getContent());
        startActivity(intent);
    }
}
