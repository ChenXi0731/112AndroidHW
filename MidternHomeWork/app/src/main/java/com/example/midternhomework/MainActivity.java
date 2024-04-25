package com.example.midternhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, TextWatcher {
    private TextView output;
    private EditText txt;
    private RadioGroup rg, rgType;
    private Button btnSubmit;

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

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult();
            }
        });
    }

    private void showResult() {
        int genderId = rg.getCheckedRadioButtonId();
        int typeId = rgType.getCheckedRadioButtonId();
        String name = txt.getText().toString().trim();

        StringBuilder result = new StringBuilder();

        // 處理性別選項
        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        result.append("性別: ");
        if (genderId == R.id.rdbBoy) {
            result.append(boy.getText()).append("\n");
        } else if (genderId == R.id.rdbGirl) {
            result.append(girl.getText()).append("\n");
        }

        // 處理票種選項
        if (typeId != -1) {
            RadioButton adult = findViewById(R.id.rdbAdult);
            RadioButton child = findViewById(R.id.rdbChild);
            RadioButton student = findViewById(R.id.rdbStudent);
            result.append("票種: ");
            if (typeId == R.id.rdbAdult) {
                result.append(adult.getText()).append("\n");
            } else if (typeId == R.id.rdbChild) {
                result.append(child.getText()).append("\n");
            } else if (typeId == R.id.rdbStudent) {
                result.append(student.getText()).append("\n");
            }
        }

        // 處理票數和金額
        if (!name.isEmpty()) {
            try {
                int num = Integer.parseInt(name);
                int price = getPrice(typeId);
                int sum = price * num;
                result.append("票數: ").append(num).append("張\n");
                result.append("總價: ").append(sum);
            } catch (NumberFormatException e) {
                result.append("票數無效");
            }
        } else {
            result.append("請輸入票數");
        }

        // 啟動新的 Activity 顯示結果
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("result", result.toString());
        startActivity(intent);
    }

    private int getPrice(int typeId) {
        int price = 0;
        if (typeId == R.id.rdbAdult) {
            price = 500;
        } else if (typeId == R.id.rdbChild) {
            price = 250;
        } else if (typeId == R.id.rdbStudent) {
            price = 400;
        }
        return price;
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