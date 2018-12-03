package com.hnc.dell.gamespeedmath;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hnc.dell.gamespeedmath.Facebook.ShareActivity;

public class MyActivity extends Activity implements View.OnClickListener {
        private Fragment playFragment;
        private TextView tvBest;
        private TextView tvScore;
        private Button btPlay;
        private Button btnShare;
        private SharedPreferences pref;
        private int bestScore;
        private int score;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            initView();
        }

        private void initView() {
            tvBest = (TextView) findViewById(R.id.tv_best_start);
            btPlay = (Button) findViewById(R.id.bt_play);
            btPlay.setOnClickListener(this);
            btnShare = (Button) findViewById(R.id.btnShare);
            btnShare.setOnClickListener(this);
            playFragment = new PlayFragment();

        }

        @Override
        public void onStart() {
            super.onStart();
            pref = this.getSharedPreferences(PlayFragment.SCORE, Context.MODE_PRIVATE);
            bestScore = pref.getInt(PlayFragment.SCORE, 1);
            String strBest = getString(R.string.best, bestScore);
            tvBest.setText(strBest);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_play:
                    Intent intent = new Intent(MyActivity.this, OptionGame.class);
                    startActivity(intent);
                    break;
                case R.id.btnShare:
                    String ShareText = tvBest.getText().toString().substring(5);
                    Intent intent1 = new Intent(MyActivity.this,ShareActivity.class);
                    intent1.putExtra("SHARETEXT",ShareText);
                    startActivity(intent1);
                    break;
                default:
                    break;
            }
        }

}
