package com.example.hsu.customize.usableClass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class AbstractActivity extends AppCompatActivity {
    public Bundle savedInstanceState;

    public abstract void create(Bundle savedInstanceState);

    public void restart() { }

    public void destroy() { }

}
