package com.jrektor.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditActivity extends AppCompatActivity {

    private CircleImageView imageViewCircle;
    private FloatingActionButton fabImage;
    private EditText txname, txbrand, txtype, txprice, txdate;
    Calendar calendar = Calendar.getInstance();
    private String name, brand, type, price, date, image;
    private int id;
    private Bitmap bitmap;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txname = findViewById(R.id.name_edit);
        txbrand = findViewById(R.id.brand_edit);
        txtype = findViewById(R.id.type_edit);
        txprice = findViewById(R.id.price_edit);
        txdate = findViewById(R.id.date_edit);
        fabImage = findViewById(R.id.btn_add_image);

        txdate.setFocusableInTouchMode(false);
        txdate.setFocusable(false);
        txdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditActivity.this, date, calendar
                        .get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        
        fabImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        name = intent.getStringExtra("name");
        brand = intent.getStringExtra("brand");
        type = intent.getStringExtra("type");
        price = intent.getStringExtra("price");
        date = intent.getStringExtra("date");
        image = intent.getStringExtra("image");
        
        setDataFromIntent();
    }

    @SuppressLint("CheckResult")
    private void setDataFromIntent() {
        if (id != 0){
            readMode();
            txname.setText(name);
            txbrand.setText(brand);
            txtype.setText(type);
            txdate.setText(date);
            txprice.setText(price);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_camera);
            requestOptions.error(R.drawable.ic_camera);
        }
    }

    private void readMode() {
    }

    private void chooseFile() {
    }
}