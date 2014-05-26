Custom Inflater
==============

This is a simple library to allow using custom prefixes in XML files.

A short example:
XML:
  ...
  <android.app.test.DarkSide android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="MAGIC"></android.app.test.DarkSide>
  ...
Java:
  setContentView(R.layout.activity_main);
  
The fact you have to use the full package nam in XML files is annoying, right?
So, let's use our Custom Inflater library:
  CustomInflater inflater = new CustomInflater(LayoutInflater.from(this), this);
	setContentView(inflater.inflate(R.layout.activity_main, null));
or if you have a inflater already:
  inflater = new CustomInflater(inflater, inflater.getContext());
  
now you can simplify your XML code like this:
  <DarkSide android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="MAGIC"></DarkSide>
        
        
Credits
--------------
I wrote this micro library myself but it's highly inspired by layoutlib's BridgeInflater:
https://android.googlesource.com/platform/frameworks/base/+/android-4.4.2_r2/tools/layoutlib/bridge/src/android/view/BridgeInflater.java