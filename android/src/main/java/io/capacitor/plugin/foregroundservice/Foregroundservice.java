package io.capacitor.plugin.foregroundservice;

import android.util.Log;

public class Foregroundservice {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
