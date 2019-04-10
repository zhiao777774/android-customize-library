package com.example.hsu.customize.usableClass;

public interface WorkCallback {

    void onComplete(String message);

    void onProcess(String message);

    void onFail();

    void sendMessage(String message);

}
