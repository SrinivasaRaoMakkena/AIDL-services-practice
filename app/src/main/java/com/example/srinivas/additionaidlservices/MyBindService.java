package com.example.srinivas.additionaidlservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyBindService extends Service {


    private MyImpl myImpl = new MyImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myImpl;
    }

    private  class MyImpl extends IMyAidlInterface.Stub{

        @Override
        public int calculate(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    }
}
