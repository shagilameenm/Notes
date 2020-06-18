package sam.database;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button submit;
    Mydatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.inputtext);
        submit=findViewById(R.id.clickbutton);
        mydatabase=new Mydatabase(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              checkCondition();
                Intent recyclerintent=new Intent(MainActivity.this,RecycleActivity.class);
                startActivity(recyclerintent);
            }
        });
    }
    public boolean isEmpty(EditText name)
    {
        CharSequence charSequence=name.getText().toString();
        return TextUtils.isEmpty(charSequence);
    }
    private void checkCondition() {
        if(isEmpty(name))
        {
            Toast.makeText(this,"Please Enter your Notes",Toast.LENGTH_SHORT).show();
        }
        else
            {
                savetoDB(name);
                Toast.makeText(this,"Notes Saved",Toast.LENGTH_SHORT).show();
            }
    }
    private void savetoDB(EditText name) {
        String uname = name.getText().toString();
        mydatabase.addNames(uname);
    }
}