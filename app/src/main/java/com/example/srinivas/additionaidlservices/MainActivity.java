package com.example.srinivas.additionaidlservices;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface service;
    private boolean isBind = false;

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = IMyAidlInterface.Stub.asInterface(iBinder);
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyBindService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBind) {
                    try {

                        Log.i("Addition:", Integer.toString(service.calculate(100, 200)));

                        Toast.makeText(getApplicationContext(), Integer.toString(service.calculate(100, 500)), Toast.LENGTH_SHORT).show();

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Service not Binded ", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
