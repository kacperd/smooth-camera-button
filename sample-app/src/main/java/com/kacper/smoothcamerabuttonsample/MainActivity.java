package com.kacper.smoothcamerabuttonsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kacper.smoothcamerabutton.SmoothCameraButton;

public class MainActivity extends AppCompatActivity {

    SmoothCameraButton smoothCameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smoothCameraButton  = (SmoothCameraButton) findViewById(R.id.smoothCameraButton);

        smoothCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smoothCameraButton.setSelected(!smoothCameraButton.isSelected());
            }
        });
    }
}
