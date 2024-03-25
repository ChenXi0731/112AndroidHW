package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button1_click(View view) {
        int num;
        TextView value = (TextView) findViewById(R.id.label_layout);
        num = Integer.parseInt(value.getText().toString());
        num++;
        value.setText(Integer.toString(num));
    }
    public void button2_click(View view) {
        int num;
        TextView value = (TextView) findViewById(R.id.label_layout);
        num = Integer.parseInt(value.getText().toString());
        num = 0;
        value.setText(Integer.toString(num));
    }

    public void button3_click(View view) {
        int num;
        TextView value = (TextView) findViewById(R.id.label_layout);
        num = Integer.parseInt(value.getText().toString());
        if(num==0)
            num=0;
        else
            num--;
        value.setText(Integer.toString(num));
    }
}