package sam.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Data> dataarraylist =new ArrayList<>();
    SQLiteDatabase db;
    Mydatabase mydatabaseclass;
    Myadapter adapterclassobj ;
    Data dataclassobj;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView=findViewById(R.id.recycleview);
        mydatabaseclass =new Mydatabase(this);
        db= mydatabaseclass.getWritableDatabase();
        String [] column={mydatabaseclass.columnname};
        cursor=db.query(mydatabaseclass.tablename,column,null,null,null,null,null);
        Log.d("cursor_count", String.valueOf(cursor.getCount()));
        if (cursor!=null)
        {
            while (cursor.moveToNext())
            {
                String getname=cursor.getString(0);
                dataclassobj =new Data(getname);
                dataarraylist.add(dataclassobj);
                Log.d("datalistcount", dataclassobj.username);
            }
        }
        db.close();
        adapterclassobj=new Myadapter(this,dataarraylist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterclassobj);

    }
}
