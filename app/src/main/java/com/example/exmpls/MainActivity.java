package com.example.exmpls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        button = findViewById(R.id.button);
        context = getApplicationContext();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* final EditText editText = findViewById(R.id.editText1);
                editText.setOnKeyListener(new View.OnKeyListener()
                                          {
                                              public boolean onKey(View v, int keyCode, KeyEvent event)
                                              {
                                                  if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                                          (keyCode == KeyEvent.KEYCODE_ENTER))
                                                  {
                                                      // сохраняем текст, введённый до нажатия Enter в переменную
                                                      String strCatName = editText.getText().toString();
                                                      return true;
                                                  }
                                                  return false;
                                              }
                                          }
                );*/

                //переход на другую активити через класс Intent

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                //Uri address = Uri.parse("https://www.google.ru/");
                // Intent intent = new Intent(Intent.ACTION_VIEW,address);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("Intent", "Не получается обработать интент");
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
}
