package sam.database;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    private Context c;
    ArrayList<Data> dataArrayList;
    SQLiteDatabase db;
    public Myadapter(@NonNull Context context, ArrayList<Data> dataArrayList) {
        c = context;
        this.dataArrayList = dataArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View displayName = layoutInflater.inflate(R.layout.textactivity, parent, false);
        ViewHolder viewHolder = new ViewHolder(displayName);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.user.setText(dataArrayList.get(position).username);
      //  final View pop = LayoutInflater.from(c).inflate(R.layout.activity_options, null);
        //  final PopupWindow popupWindow=new PopupWindow(pop, WindowManager.LayoutParams.MATCH_PARENT);
        holder.user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.popup_example, null, false),100,100, true)
                //View popupview=LayoutInflater.from(c).inflate(R.layout.activity_options,null);

                LayoutInflater layoutInflater=(LayoutInflater) Myadapter.this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView=layoutInflater.inflate(R.layout.activity_options,null);
                final Button updatebt,deletebt,editbt;
                final EditText updatename;
                boolean focusable = true;
                updatebt = popupView.findViewById(R.id.upbt);
                deletebt=popupView.findViewById(R.id.delbt);
                updatename=popupView.findViewById(R.id.upnameet);
                editbt=popupView.findViewById(R.id.edbt);

                PopupWindow  popupWindow=new PopupWindow(popupView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,focusable);
                popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0);
               // popupWindow.setOutsideTouchable(false);

                editbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      updatename.setText(String.valueOf(dataArrayList.get(position).username));
                        updatename.setVisibility(View.VISIBLE);
                        updatebt.setVisibility(View.VISIBLE);
                    }
                });
                updatebt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mydatabase mydatabase ;
                        mydatabase=new Mydatabase(c);
                        db= mydatabase.getWritableDatabase();
                        String newname = updatename.getText().toString();
                        db.execSQL("update " +Mydatabase.tablename+" set Name= '"+newname+"' where Name='"+dataArrayList.get(position).username+"'");
                        Toast.makeText(c,"Successfully Updated",Toast.LENGTH_SHORT).show();
                        //update user set NAME= edname where name= dataarraylist.username
                        db.close();
                    }
                });
                deletebt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteNames(dataArrayList.get(position).username);
                    }
                });
                // popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
                     //  popupWindow.dismiss();
                /*

                final AlertDialog.Builder alertdialog=new AlertDialog.Builder(c);

                alertdialog.setTitle("You have Clicked ");
                alertdialog.setMessage(dataArrayList.get(position).username);
                alertdialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                      //  deleteNames(Myadapter.Myholder.user);
                    }
                });
                alertdialog.setPositiveButton("Rename", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog=alertdialog.create();
                dialog.show();

                 */
            }
        });
    }




    public void deleteNames(String  username) {
        Mydatabase mydatabase ;
        mydatabase=new Mydatabase(c);
        db= mydatabase.getWritableDatabase();
        db.delete(Mydatabase.tablename,   "Name=? ", new String[]{username}) ;
        //  Log.d("//deleted: ",String.valueOf(username));
        //  Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
        db.close();
        Toast.makeText(c,"Successfully Deleted",Toast.LENGTH_SHORT).show();
    }
    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user;

        public ViewHolder(View view) {
            super(view);
            this.user = view.findViewById(R.id.nametv);
        }
    }
}

    /*
    public void doUpdate(EditText name) {
        Mydatabase mydatabase ;
        EditText updataname;
        mydatabase=new Mydatabase(c);
        db= mydatabase.getWritableDatabase();
        String newname = name.getText().toString();
        db.execSQL("update " + Mydatabase.tablename + " set  " + updataname + " = '" + newname + "'");
        db.close();
    }

     */
    /*

    public void deleteItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id=?";
        String whereArgs[] = {item.id.toString()};
        db.delete("Items", whereClause, whereArgs);
    }
         */


/*
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.layout.activity_options, popup.getMenu());
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.show();
    }
}

 */