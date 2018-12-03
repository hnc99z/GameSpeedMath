package com.hnc.dell.gamespeedmath.Facebook;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.share.Share;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.hnc.dell.gamespeedmath.PlayFragment;
import com.hnc.dell.gamespeedmath.R;

public class ShareActivity extends AppCompatActivity {
     ShareDialog shareDialog;
    private  ShareLinkContent content;
    private SharedPreferences pref;
    private  String bestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        shareDialog = new ShareDialog(ShareActivity.this);

        Intent intent3 = getIntent();
        bestScore = intent3.getStringExtra("SHARETEXT");

        runTimePermission();
        String title ="Speed Math";
        String linkShare = "http://bit.ly/mathspeedversion1";
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            shareLinkFB(title,linkShare);
        }
        finish();
    }

    public  void shareLinkFB(String title, String linkShare) {
        content = new ShareLinkContent.Builder()
                .setContentTitle(title).setQuote("Tôi đã chơi được "+bestScore+" điểm. Hãy thử xem bạn được bao nhiêu điểm?")
                .setContentUrl(Uri.parse(linkShare))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#SpeedMath \n#ByCongHieu")
                        .build())
                .build();
        shareDialog.show(content);
    }

    public void runTimePermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }

}
