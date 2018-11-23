package com.hnc.dell.gamespeedmath;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends Activity implements View.OnClickListener, Runnable {
    public int TIME;
    public int NUMBER;
    public String CALC;
    public static final String SCORE = "Score";
    public static final String AA = "+-*+-*";

    private TextView tvBest;
    public static TextView tvScore;
    private TextView tvMath;
    private TextView tvTime;
    private ImageView btLeft;
    private ImageView btRight;
    private Random mRandom;
    private int number1;
    private int number2;
    private int number3;
    private int answer;
    private static int time, number;
    private static String calc;
    public static int score;
    private boolean isResult;
    private boolean isCheck;
    private SharedPreferences pref;
    private Handler mHandler;
    private int bestScore;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        Bundle bundle = getIntent().getExtras();
        TIME = bundle.getInt("TIME");
        NUMBER = bundle.getInt("NUMBER");
        CALC = bundle.getString("CALC");
        initView();
        initPreferences();
        //
        editor.putInt("SAVENUMBER", NUMBER);
        editor.putInt("SAVETIME", TIME);
        editor.commit();
        score = 0;
        mRandom = new Random();
        mHandler = new Handler();
        randomQuestion();
        setGameStatus();
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    private void initView() {
        tvBest = (TextView) findViewById(R.id.tv_best_play);
        tvScore = (TextView) findViewById(R.id.tv_score_play);
        tvMath = (TextView) findViewById(R.id.tv_math);
        tvTime = (TextView) findViewById(R.id.tv_time);
        btLeft = (ImageView) findViewById(R.id.bt_left);
        btRight = (ImageView) findViewById(R.id.bt_right);
        btLeft.setOnClickListener(this);
        btRight.setOnClickListener(this);


    }

    private void setGameStatus() {
        String strScore = getString(R.string.score, score);
        tvScore.setText(strScore);
        pref = getSharedPreferences(SCORE, Context.MODE_PRIVATE);
        bestScore = pref.getInt(SCORE, 0);
        String strBest = getString(R.string.best, bestScore);
        tvBest.setText(strBest);
    }

    public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AA.charAt(mRandom.nextInt(AA.length())));
        }
        return sb.toString();
    }

    private void randomQuestion() {
        time = TIME;
        number = NUMBER;
        calc = CALC;
        number1 = mRandom.nextInt(number);
        number2 = mRandom.nextInt(number);
        isResult = mRandom.nextBoolean();
        if (calc.equals("+")) {
            phepCong();
        } else if (calc.equals("-")) {
            phepTru();
        } else if (calc.equals("*")) {
            phepNhan();
        } else if (calc.equals("tonghop")) {
            String random = randomString(1);
           if (random.equalsIgnoreCase("+")){
               phepCong();
           }else if (random.equalsIgnoreCase("-")){
               phepTru();
           }else {
               phepNhan();
           }
        }
        for (int i = 0; i < time + 1; i++) {
            mHandler.postDelayed(this, i * 1000);
        }
    }

    public void phepCong() {
        answer = number1 + number2;
        if (isResult == true) {
            number3 = answer;
            isCheck = true;
        } else {
            if (number1 % 2 == 0) {
                number3 = answer + 1;
                isCheck = false;
            } else {
                number3 = answer - 1;
                isCheck = false;
            }
        }
        String result = number1 + " + " + number2 + " = " + number3;
        tvMath.setText(result);
    }


    public void phepTru() {
        answer = number1 - number2;
        if (isResult == true) {
            number3 = answer;
            isCheck = true;
        } else {
            if (number1 % 2 == 0) {
                number3 = answer + 1;
                isCheck = false;
            } else {
                number3 = answer - 1;
                isCheck = false;
            }
        }
        String result = number1 + " - " + number2 + " = " + number3;
        tvMath.setText(result);
    }


    public void phepNhan() {
        answer = number1 * number2;
        if (isResult == true) {
            number3 = answer;
            isCheck = true;
        } else {
            if (number1 % 2 == 0) {
                number3 = answer + 1;
                isCheck = false;
            } else {
                number3 = answer - 1;
                isCheck = false;
            }
        }
        String result = number1 + " x " + number2 + " = " + number3;
        tvMath.setText(result);
    }

    @Override
    public void onClick(View v) {
        mHandler.removeCallbacksAndMessages(null);
        switch (v.getId()) {
            case R.id.bt_left:
                if (isCheck == false) {
                    score++;
                    setGameStatus();
                    randomQuestion();

                } else {
                    gameOver();
                }
                break;
            case R.id.bt_right:
                if (isCheck == true) {
                    score++;
                    setGameStatus();
                    randomQuestion();
                } else {
                    gameOver();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        tvTime.setText(time + "");
        time--;
        if (time == -1) {
            gameOver();
        }
    }

    private void gameOver() {
        if (score > bestScore) {
            pref.edit().putInt(SCORE, score).commit();
        }
        FragmentManager fm = getFragmentManager();
        DialogFragment myDialog = MyDialog.newInstance(score);
        try {
            myDialog.show(fm, "Dialog");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
