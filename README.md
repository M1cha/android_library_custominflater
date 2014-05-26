Custom Inflater
==============

This is a simple library to allow using custom prefixes in XML files.

A short example:
XML:
```xml
  ...
  <android.app.test.DarkSide android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="MAGIC"></android.app.test.DarkSide>
  ...
```
Java:
```java
  setContentView(R.layout.activity_main);
```
  
The fact you have to use the full package nams in XML files is annoying, right?
So, let's use our Custom Inflater library:
```java
  CustomInflater inflater = new CustomInflater(LayoutInflater.from(this), this);
  setContentView(inflater.inflate(R.layout.activity_main, null));
```
or if you have a inflater already:
```java
  inflater = new CustomInflater(inflater, inflater.getContext());
```
  
now you can simplify your XML code like this:
```xml
  <DarkSide android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="MAGIC"></DarkSide>
```

Also you can define custom prefixes:
```java
inflater.addPrefix("my.package.name");
```
        
        
Credits
--------------
I wrote this micro library myself but it's highly inspired by layoutlib's BridgeInflater:
https://android.googlesource.com/platform/frameworks/base/+/android-4.4.2_r2/tools/layoutlib/bridge/src/android/view/BridgeInflater.java
