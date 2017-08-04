# Smooth Camera Button
Smooth Camera Button is a graphic library that ships camera button view similar to the default iOS camera app.

![alt text](https://image.ibb.co/nsRsPv/ezgif_com_video_to_gif.gif "")

# Installation
Via Gradle:

Make sure to have jcenter() in your repositories:
```
repositories {
    jcenter()    
}
```
And then add as dependency: 
```gradle
  compile 'com.kacper.smooth-camera-button:smooth-camera-button:1.0.0-beta'
```

# How to use
Simply put in your view xml file:

```
  <com.kacper.smoothcamerabutton.SmoothCameraButton      
      android:layout_width="80dp"
      android:layout_height="80dp"
```

To manipulate colors and stroke width use following methods:
```
  smoothCameraButton.setStrokeColor(Color.WHITE);
  smoothCameraButton.setInsideActiveColor(Color.DKGRAY);
  smoothCameraButton.setInsideColor(Color.RED);
  smoothCameraButton.setStrokeWidth(10);

```
To change inside view to rounded rectangle shape change selected state:

```
  smoothCameraButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          smoothCameraButton.setSelected(!smoothCameraButton.isSelected());
      }
  });
```

# License
Copyright (C) 2017 Kacper Dziubek (http://kacperdziubek.pl)

Smooth Camera Button binaries and source code can be used according to the [Apache License, Version 2.0](LICENSE).
