package com.kwave.android.firebaseprojectexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.kwave.android.firebaseprojectexercise.Group.GroupReadActivity;
import com.kwave.android.firebaseprojectexercise.Information.InformationActivity;
import com.kwave.android.firebaseprojectexercise.Notify.NotifyReadActivity;
import com.kwave.android.firebaseprojectexercise.Payment.PaymentReadActivity;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageInformation, imagePay, imageGruop, imageNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        imageInformation = (ImageView) findViewById(R.id.imageInfomation);
        imagePay = (ImageView) findViewById(R.id.imagePay);
        imageGruop = (ImageView) findViewById(R.id.imageGruop);
        imageNotify = (ImageView) findViewById(R.id.imageNotify);
        imageInformation.setOnClickListener(this);
        imagePay.setOnClickListener(this);
        imageGruop.setOnClickListener(this);
        imageNotify.setOnClickListener(this);

        }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.imageInfomation :
                intent = new Intent(SelectActivity.this,InformationActivity.class);
                startActivity(intent);
                break;
            case R.id.imagePay :
                intent = new Intent(SelectActivity.this,PaymentReadActivity.class);
                startActivity(intent);
                break;
            case R.id.imageGruop :
                intent = new Intent(SelectActivity.this,GroupReadActivity.class);
                startActivity(intent);
                break;
            case R.id.imageNotify :
                intent = new Intent(SelectActivity.this,NotifyReadActivity.class);
                startActivity(intent);
                break;
        }

    }

}
