package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

public abstract class AbsMapView {

    /**
     * Result for IActivityManager.startActivity: an error where the
     * start had to be canceled.
     * @hide
     */

    public abstract void onStart();

    public abstract void onResume();

    public abstract void onDestory();
}
