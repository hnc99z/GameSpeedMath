package com.hnc.dell.gamespeedmath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OptionGame extends AppCompatActivity implements View.OnClickListener {
    private EditText edInputTime;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnPhepCong;
    private Button btnPhepTru;
    private Button btnPhepNhan;
    private Button btnTongHop;
    Intent intent;
    int inputTime;
    int number;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_game);
        initView();
    }

    private void initView() {
        edInputTime = (EditText) findViewById(R.id.edInputTime);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnPhepCong = (Button) findViewById(R.id.btnPhepCong);
        btnPhepTru = (Button) findViewById(R.id.btnPhepTru);
        btnPhepNhan = (Button) findViewById(R.id.btnPhepNhan);
        btnTongHop = (Button) findViewById(R.id.btnTongHop);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnPhepCong.setOnClickListener(this);
        btnPhepTru.setOnClickListener(this);
        btnPhepNhan.setOnClickListener(this);
        btnTongHop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        intent = new Intent(getApplicationContext(), PlayActivity.class);
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.btnOne:
                check = true;
                number = 10;
                Toast.makeText(this, "Bạn đã chọn số 1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnTwo:
                check = true;
                number = 100;
                Toast.makeText(this, "Bạn đã chọn số 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnThree:
                check = true;
                number = 1000;
                Toast.makeText(this, "Bạn đã chọn số 3", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnPhepCong:
                if (check == true) {
                    inputTime = Integer.parseInt(edInputTime.getText().toString().trim());
                    bundle.putInt("NUMBER", number);
                    bundle.putInt("TIME", inputTime);
                    bundle.putString("CALC","+");
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Vui lòng chọn số để chơi!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnPhepTru:
                if (check == true) {
                    inputTime = Integer.parseInt(edInputTime.getText().toString().trim());
                    bundle.putInt("NUMBER", number);
                    bundle.putInt("TIME", inputTime);
                    bundle.putString("CALC","-");
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Vui lòng chọn số để chơi!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnPhepNhan:
                if (check == true) {
                    inputTime = Integer.parseInt(edInputTime.getText().toString().trim());
                    bundle.putInt("NUMBER", number);
                    bundle.putInt("TIME", inputTime);
                    bundle.putString("CALC","*");
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Vui lòng chọn số để chơi!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnTongHop:
                if (check == true) {
                    inputTime = Integer.parseInt(edInputTime.getText().toString().trim());
                    bundle.putInt("NUMBER", number);
                    bundle.putInt("TIME", inputTime);
                    bundle.putString("CALC","tonghop");
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Vui lòng chọn số để chơi!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
