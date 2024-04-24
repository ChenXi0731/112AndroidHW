package com.example.midternhomework;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, TextWatcher {
    private TextView output;
    private EditText txt;
    private RadioGroup rg, rgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得View物件
        output = findViewById(R.id.lblOutput);

        // 註冊傾聽者物件
        rg = findViewById(R.id.rgGender);
        rg.setOnCheckedChangeListener(this);

        rgType = findViewById(R.id.rgType);
        rgType.setOnCheckedChangeListener(this);

        txt = findViewById(R.id.txtName);
        txt.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        show(rg.getCheckedRadioButtonId(), rgType.getCheckedRadioButtonId());
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        show(rg.getCheckedRadioButtonId(), rgType.getCheckedRadioButtonId());
    }

    private int sum, price, num=0;

    public void show(int genderId, int typeId) {
        StringBuilder output = new StringBuilder();
        String nameInput = txt.getText().toString().trim(); // 去除前後空白

        // 處理第一個 RadioGroup
        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        if (genderId == R.id.rdbBoy) {
            output.append(boy.getText());
        } else if (genderId == R.id.rdbGirl) {
            output.append(girl.getText());
        }

        // 處理第二個 RadioGroup
        if (typeId != -1) {
            output.append("\n");
            RadioButton adult = findViewById(R.id.rdbAdult);
            RadioButton child = findViewById(R.id.rdbChild);
            RadioButton student = findViewById(R.id.rdbStudent);
            if (typeId == R.id.rdbAdult) {
                output.append(adult.getText());
                price = 500;
            } else if (typeId == R.id.rdbChild) {
                output.append(child.getText());
                price = 250;
            } else if (typeId == R.id.rdbStudent) {
                output.append(student.getText());
                price = 400;
            }
        }

        // 檢查是否有輸入票數
        if (!nameInput.isEmpty()) {
            try {
                num = Integer.parseInt(nameInput);
                sum = price * num;
                output.append("票\n").append(nameInput).append("張\n金額: ").append(sum);
            } catch (NumberFormatException e) {
                // 無法解析為數字
                output.append("\n請輸入有效的數字");
            }
        } else {
            // 沒有輸入票數
            output.append("\n請輸入票數");
        }

        TextView outputView = findViewById(R.id.lblOutput);
        outputView.setText(output.toString());
    }
}