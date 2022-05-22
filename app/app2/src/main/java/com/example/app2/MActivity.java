package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MActivity extends AppCompatActivity {

    private Button button;
    private TextView enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mactivity);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();


        button = findViewById(R.id.btn);
        enter = findViewById(R.id.textView);


    }

       /*button.setOnClickListener(new View.OnClickListener() {*/


            public void onClick(View v) {


                switch (v.getId()) {
                    case R.id.btn:
                        Toast.makeText(this, "НАЖАЛ!!!", Toast.LENGTH_SHORT).show();
                        EditText editText = findViewById(R.id.enter);
                        TextView textView = findViewById(R.id.textView);
                        String txtName = editText.getText().toString();
                        textView.setText(txtName);
//                        v.setClickable(false);

                        break;
                    default:
                        break;

                }
            }
        /*});*/




    }

