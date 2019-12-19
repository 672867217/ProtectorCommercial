package com.example.protector.util;

import android.app.Application;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.example.protector.MainActivity;
import com.example.protector.SQl.TestData;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android_serialport_api.SerialPort;

/**
 * @author by AllenJ on 2018/4/20.
 * <p>
 * 通过串口用于接收或发送数据
 */

public class SerialPortUtil {
    private SerialPort serialPort = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    public ReceiveThread mReceiveThread = null;
    private boolean isStart = false;
    private boolean is = false;
    private Receive receive;
    public String str;
    public StringBuffer str2 = new StringBuffer();
    private Timer timer;

    /**
     * 打开串口，接收数据
     * 通过串口，接收单片机发送来的数据
     */
    public void openSerialPort() {
        try {
            serialPort = new SerialPort(new File("/dev/ttyS1"), 9600, 0);
            //调用对象SerialPort方法，获取串口中"读和写"的数据流
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
            isStart = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        getSerialPort();
    }

    /**
     * 关闭串口
     * 关闭串口中的输入输出流
     */
    public void closeSerialPort() {
        Log.i("test", "关闭串口");
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            isStart = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送数据
     * 通过串口，发送数据到单片机
     *
     * @param data 要发送的数据
     */
    public void sendSerialPort(String data) {
        try {
            byte[] sendData = data.getBytes();
            outputStream.write(sendData);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSerialPort() {
        if (mReceiveThread == null) {

            mReceiveThread = new ReceiveThread();
        }
        mReceiveThread.start();
    }

    /**
     * 接收串口数据的线程
     */

    public interface Receive {
        void set(String str, List<String> list);
    }

    public void onReceive(Receive receive) {
        this.receive = receive;
    }

    private class ReceiveThread extends Thread {
        @Override
        public void run() {
            while (isStart) {
                if (inputStream == null) {
                    return;
                }
                if(timer == null && is == true){
                    timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            String[] s = str2.toString().split("AA00FF");
                            for (int i = 1; i < s.length; i++) {
                                if(timer!= null){
                                    timer.cancel();
                                    timer = null;
                                }
                                Utils utils = new Utils();
                                List<String> strings = utils.getDivLines("AA00FF" + s[i], 2);
                                receive.set(strings.get(3), strings);
                            }str2 = new StringBuffer();
                        }
                    };
                    timer.schedule(timerTask,1000,2000);
                }
                is = true;
                byte[] readData = new byte[1024];
                int size = 0;
                try {
                    size = inputStream.read(readData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (size > 0) {
                    str = new String(readData).trim();
                    str2.append(str);
                }
            }

        }
    }

}