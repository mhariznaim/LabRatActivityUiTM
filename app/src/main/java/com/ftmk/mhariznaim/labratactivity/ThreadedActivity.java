package com.ftmk.mhariznaim.labratactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreadedActivity extends AppCompatActivity {

    ImageView imgVwBean;
    TextView tvHello;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        imgVwBean = findViewById(R.id.imgVwProfile);
        tvHello = findViewById(R.id.txtVwHello);

        //get data from previous activity
        Intent intentGetData = getIntent();
        String strMsg = intentGetData.getStringExtra("varStr1");
        tvHello.setText(strMsg);



    }

    public void fnTakePic(View view)
    {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                // logical process execute here
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvHello.setText("This is picture taken from camera");
                    }
                });
            }
        };
        Thread thread = new Thread(run);
        thread.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imgVwBean.setImageBitmap(bitmap);
    }
}
