package com.example.loginui_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login_click(View view){
        EditText AInput = (EditText) findViewById(R.id.AInput);
        EditText PInput = (EditText) findViewById(R.id.PInput);
        TextView Result = (TextView) findViewById(R.id.Result);

        String account = AInput.getText().toString();
        String password = PInput.getText().toString();

        if (account.equals("a111070036") && password.equals("shu123456")){
            Result.setText("登入成功");
        }
        else {
            Result.setText("帳號或密碼錯誤");
        }
    }

}