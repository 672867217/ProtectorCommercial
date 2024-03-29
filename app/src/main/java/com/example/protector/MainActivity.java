package com.example.protector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.protector.SQl.Gonwei;
import com.example.protector.SQl.TestData;
import com.example.protector.SQl.XiuGai;
import com.example.protector.util.MyApplication;
import com.example.protector.util.Utils;
import com.example.protector.util.SerialPortUtil;

import org.litepal.crud.DataSupport;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button2;
    private Button button;
    private Button button4;
    private Button button3;
    private Button button6;
    private Button button5;
    public static SerialPortUtil utils = new SerialPortUtil();
    Utils util = new Utils();
    private static BigDecimal b1 = new BigDecimal("0.001");
    private static BigDecimal b3 = new BigDecimal("0.01");
    private static BigDecimal b4 = new BigDecimal("0.1");
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private Timer timer;
    private XiuGai xiuGai;
    private FrameLayout click;
    private Gonwei gonwei;
    private MyApplication app;
    public static int ini;
    public int as = 1;
    private Timer timer1;
    private List list;
    private int cecheng = 0;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static String num2(String s) {
        String ss = String.format("%08d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(s))));
        if (ss.charAt(0) == '0') {
            return "+" + jisuan2(s);
        } else {
            return "-" + jisuan2((Integer.parseInt(Integer.toBinaryString(~Integer.parseInt(s)).substring(24, 32), 2) + 1) + "");
        }
    }

    public static String num(String s) {
        String ss = String.format("%08d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(s))));
        if (ss.charAt(0) == '0') {
            return "+" + jisuan3(s);
        } else {
            return "-" + jisuan3((Integer.parseInt(Integer.toBinaryString(~Integer.parseInt(s)).substring(24, 32), 2) + 1) + "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (MyApplication) getApplication();
        textView = (TextView) findViewById(R.id.textView);
        click = (FrameLayout) findViewById(R.id.click);
        button2 = (Button) findViewById(R.id.button2);
        button = (Button) findViewById(R.id.button);
        button4 = (Button) findViewById(R.id.button4);
        button3 = (Button) findViewById(R.id.button3);
        button6 = (Button) findViewById(R.id.button6);
        button5 = (Button) findViewById(R.id.button5);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    timer.cancel();
                    button2.performClick();
                }
            }
        };
        click.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (as == 2) {
                    if (timer1 != null) {
                        timer1.cancel();
                    }
                    System.exit(0);
                    as = 1;
                } else {
                    as++;
                    if (timer1 == null) {
                        timer1 = new Timer();
                    }
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            as = 1;
                        }
                    };
                    timer1.schedule(timerTask, 3000, 3000);
                }
                return true;
            }
        });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (utils.mReceiveThread == null) {
            utils.openSerialPort();
        }
        utils.onReceive(new SerialPortUtil.Receive() {
            @Override
            public void set(String str, List<String> list) {
                switch (str) {
                    case "61":
                        int[] arr = new int[]{0xAA, 0xFF, 0x00, 0x31, 0x00, 0x64};
                        for (int i = 0; i < arr.length; i++) {
                            utils.sendSerialPort(arr[i]);
                        }
                        if (!preferences.getBoolean("is", false)) {
                            for (int i = 0; i < 2; i++) {
                                int[] arrr = cmd(i + 1);
                                for (int j = 0; j < arrr.length; j++) {
                                    MainActivity.utils.sendSerialPort(arrr[j]);
                                }
                            }
                            editor.putBoolean("is", true).commit();
                        }

                        List<String> strings = util.getDivLines(String.format("%08d", Integer.parseInt(Integer.toBinaryString(new Utils().HexToInt(list.get(5))))), 1);
                        System.out.println(strings.toString());
                        if (DataSupport.findAll(Gonwei.class).size() == 0) {
                            gonwei = new Gonwei();
                        } else {
                            gonwei = DataSupport.find(Gonwei.class, 1);
                        }
                        gonwei.one = is(Integer.parseInt(strings.get(7)));
                        gonwei.two = is(Integer.parseInt(strings.get(6)));
                        gonwei.save();
                        break;
                    case "50":
                        switch (new Utils().HexToInt(list.get(5))) {
                            case 1:
                                xiuGai = DataSupport.find(XiuGai.class, 1);
                                break;
                            case 2:
                                xiuGai = DataSupport.find(XiuGai.class, 2);
                                break;
                            default:
                                return;
                        }
                        xiuGai.setCecheng(new Utils().HexToInt(list.get(6)) + "");
                        xiuGai.setQidong(new Utils().HexToInt(list.get(7)) + "");
                        xiuGai.setDuanxiang(new Utils().HexToInt(list.get(8)) + "");
                        xiuGai.setM13(jisuan3(new Utils().HexToInt(list.get(9)) + ""));
                        xiuGai.setM30(jisuan3(new Utils().HexToInt(list.get(10)) + ""));
                        xiuGai.setChuanlian1(jisuan3(new Utils().HexToInt(list.get(11) + list.get(12)) + ""));
                        xiuGai.setChuanlian2(jisuan3(new Utils().HexToInt(list.get(13) + list.get(14)) + ""));
                        xiuGai.setBinglian1(jisuan3(new Utils().HexToInt(list.get(15) + list.get(16)) + ""));
                        xiuGai.setBinglian2(jisuan3(new Utils().HexToInt(list.get(17) + list.get(18)) + ""));
                        xiuGai.setDuanxiangzhiliu(jisuan2(new Utils().HexToInt(list.get(19)) + ""));
                        xiuGai.setJiaoliu(jisuan2(new Utils().HexToInt(list.get(20) + list.get(21)) + ""));
                        xiuGai.setXiangjian(new Utils().HexToInt(list.get(22) + list.get(23)) + "");
                        xiuGai.setXiangduidi(new Utils().HexToInt(list.get(24) + list.get(25)) + "");
                        xiuGai.setXiangduixianquan(new Utils().HexToInt(list.get(26) + list.get(27)) + "");
                        xiuGai.setXianquan(new Utils().HexToInt(list.get(28) + list.get(29)) + "");
                        xiuGai.save();
                        break;
                    case "10":
                        TestData testData = new TestData();
                        testData.setType(1);
                        testData.setGongwei(new Utils().HexToInt(list.get(5)) + "");
                        testData.setDate2(new Date());
                        testData.setCecheng(new Utils().HexToInt(list.get(6)) + "");
                        testData.setCeshishichang(new Utils().HexToInt(list.get(7) + list.get(8)) + "");
                        testData.setChanpinbianma(new Utils().HexToInt(list.get(9) + list.get(10) + list.get(11) + list.get(12)) + "");
                        testData.setShengchanbianma(new Utils().HexToInt(list.get(9) + list.get(10) + list.get(11) + list.get(12)) + "");
                        testData.setAjiguzhang(Integer.toBinaryString(new Utils().HexToInt(list.get(13))));
                        testData.setBjiguzhang(Integer.toBinaryString(new Utils().HexToInt(list.get(14))));
                        testData.setBaojin(String.format("%08d", Integer.parseInt(Integer.toBinaryString(new Utils().HexToInt(list.get(15))))) + String.format("%08d", Integer.parseInt(Integer.toBinaryString(new Utils().HexToInt(list.get(16))))));
                        testData.setXianquanchuanlian1(jisuan3(new Utils().HexToInt(list.get(17) + list.get(18)) + ""));
                        testData.setXianquanchuanlian2(jisuan3(new Utils().HexToInt(list.get(19) + list.get(20)) + ""));
                        testData.setXianquanchuanlian3(jisuan3(new Utils().HexToInt(list.get(21) + list.get(22)) + ""));
                        testData.setXianquanchuanlian4(jisuan3(new Utils().HexToInt(list.get(23) + list.get(24)) + ""));
                        testData.setXianquanchuanlian5(jisuan3(new Utils().HexToInt(list.get(25) + list.get(26)) + ""));
                        testData.setXianquanbinglian(jisuan3(new Utils().HexToInt(list.get(27) + list.get(28)) + ""));
                        testData.setAjiwucha(num(new Utils().HexToInt(list.get(29)) + ""));
                        testData.setBjiwucha(num(new Utils().HexToInt(list.get(30)) + ""));
                        testData.setAxiangawucha(num2(new Utils().HexToInt(list.get(31)) + ""));
                        testData.setAxiangbwucha(num2(new Utils().HexToInt(list.get(32)) + ""));
                        testData.setAxiangcwucha(num2(new Utils().HexToInt(list.get(33)) + ""));
                        testData.setBxiangawucha(num2(new Utils().HexToInt(list.get(34)) + ""));
                        testData.setBxiangbwucha(num2(new Utils().HexToInt(list.get(35)) + ""));
                        testData.setBxiangcwucha(num2(new Utils().HexToInt(list.get(36)) + ""));
                        testData.setAduanxiangdianya(jisuan2(new Utils().HexToInt(list.get(37)) + ""));
                        testData.setBduanxiangdianya(jisuan2(new Utils().HexToInt(list.get(38)) + ""));
                        testData.setCduanxiangdianya(jisuan2(new Utils().HexToInt(list.get(39)) + ""));
                        testData.setAxiangceyajiang(jisuan(new Utils().HexToInt(list.get(40) + list.get(41)) + ""));
                        testData.setBxiangceyajiang(jisuan(new Utils().HexToInt(list.get(42) + list.get(43)) + ""));
                        testData.setCxiangceyajiang(jisuan(new Utils().HexToInt(list.get(44) + list.get(45)) + ""));
                        testData.setQidongshijian(new Utils().HexToInt(list.get(46) + list.get(47)) + "");
                        testData.setAduanxiangxiangying(new Utils().HexToInt(list.get(48) + list.get(49)) + "");
                        testData.setBduanxiangxiangying(new Utils().HexToInt(list.get(50) + list.get(51)) + "");
                        testData.setCduanxiangxiangying(new Utils().HexToInt(list.get(52) + list.get(53)) + "");
                        testData.setM13xianshishijian(jisuan(new Utils().HexToInt(list.get(54) + list.get(55)) + ""));
                        testData.setM30xianshishijian(jisuan(new Utils().HexToInt(list.get(56) + list.get(57)) + ""));
                        testData.setAbxiangjianjueyuan(new Utils().HexToInt(list.get(58) + list.get(59)) + "");
                        testData.setAcxiangjianjueyuan(new Utils().HexToInt(list.get(60) + list.get(61)) + "");
                        testData.setBcxiangjianjueyuan(new Utils().HexToInt(list.get(62) + list.get(63)) + "");
                        testData.setAxiangduidijueyuan(new Utils().HexToInt(list.get(64) + list.get(65)) + "");
                        testData.setBxiangduidijueyuan(new Utils().HexToInt(list.get(66) + list.get(67)) + "");
                        testData.setCxiangduidijueyuan(new Utils().HexToInt(list.get(68) + list.get(69)) + "");
                        testData.setAxiangduixianquanjueyuan(new Utils().HexToInt(list.get(70) + list.get(71)) + "");
                        testData.setBxiangduixianquanjueyuan(new Utils().HexToInt(list.get(72) + list.get(73)) + "");
                        testData.setCxiangduixianquanjeuyuan(new Utils().HexToInt(list.get(74) + list.get(75)) + "");
                        testData.setXianquanduidijueyuan(new Utils().HexToInt(list.get(76) + list.get(77)) + "");
                        app.map.put(new Utils().HexToInt(list.get(5)) + "", testData);
                        if (testData.getCecheng().equals("4")) {
                            testData.save();
                        }
                        System.out.println(list.toString());
                        break;
                }
            }
        });

        util.hideNavKey(MainActivity.this);
        //设置页
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.cancel();
                }
                View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_setting, null);
                Setting setting = new Setting(MainActivity.this, view1, R.style.dialog);
                setting.show();
            }
        });
        //编号查询
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.cancel();
                }
                View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_number_query, null);
                NumberQuery numberQuery = new NumberQuery(MainActivity.this, view1, R.style.dialog);
                numberQuery.show();
            }
        });
        //误差分析
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timer != null) {
                    timer.cancel();
                }
                startActivity(new Intent(getApplicationContext(), ErrorAnalysis.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                }
                View view = getLayoutInflater().inflate(R.layout.dialog_datequery, null);
                final Dialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).show();
//                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_touming);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                Button btn1 = view.findViewById(R.id.dialog_date_btn);
                Button btn2 = view.findViewById(R.id.dialog_date_btn2);
                final SharedPreferences.Editor editor = getSharedPreferences("cecheng", 0).edit();
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DateQuery.class));
                        editor.putString("what", "4").commit();
                        dialog.dismiss();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DateQuery.class));
                        editor.putString("what", "0").commit();
                        dialog.dismiss();

                    }
                });

            }
        });

        //测试页
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                }
                View view = LayoutInflater.from(getApplication()).inflate(R.layout.dialog_test1, null);
                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).show();
                Button dialog1_btn1 = view.findViewById(R.id.test_dialog1_btn1);
                Button dialog1_btn2 = view.findViewById(R.id.test_dialog1_btn2);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog1_btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ini = 0;
                        dialog.dismiss();
                        View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_test, null);
                        Test test = new Test(MainActivity.this, view1, R.style.dialog);
                        test.show();

                    }
                });
                dialog1_btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view2 = LayoutInflater.from(getApplication()).inflate(R.layout.dialog_test2, null);
                        final AlertDialog dialog2 = new AlertDialog.Builder(MainActivity.this).setView(view2).show();
                        Button dialog2_btn1 = view2.findViewById(R.id.test_dialog2_btn1);
                        Button dialog2_btn2 = view2.findViewById(R.id.test_dialog2_btn2);
                        dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dialog2_btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ini = 1;
                                View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_test, null);
                                Test test = new Test(MainActivity.this, view1, R.style.dialog);
                                test.show();
                                dialog.dismiss();
                                dialog2.dismiss();
                            }
                        });
                        dialog2_btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog2.dismiss();
                            }
                        });
                    }
                });
            }
        });
        //统计页
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                }
                View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_stats, null);
                Stats stats = new Stats(MainActivity.this, view1, R.style.dialog);
                stats.show();
            }
        });

        if (preferences.getBoolean("diyici", false)) {
            return;
        } else {
            for (int i = 0; i < 2; i++) {
                add(i + 1);
            }
            list = DataSupport.findAll(XiuGai.class);
            editor.putBoolean("diyici", true).commit();
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1);
                }
            };
            timer.schedule(timerTask, 5000, 500);
        }
    }

    public int[] cmd(int gongwei) {
        XiuGai xiuGai = (XiuGai) list.get(gongwei - 1);
        String s = "AAFF005119";
        if (Integer.toHexString(gongwei).length() == 2) {
            s += Integer.toHexString(gongwei);
        } else {
            s += "0" + Integer.toHexString(gongwei);
        }
        if (Integer.toHexString(cecheng).length() == 2) {
            s += Integer.toHexString(cecheng);
        } else {
            s += "0" + Integer.toHexString(cecheng);
        }
        if (Integer.toHexString(Integer.parseInt(xiuGai.getQidong())).length() == 2) {
            s += Integer.toHexString(Integer.parseInt(xiuGai.getQidong()));
        } else {
            s += "0" + Integer.toHexString(Integer.parseInt(xiuGai.getQidong()));
        }
        if (Integer.toHexString(Integer.parseInt(xiuGai.getDuanxiang())).length() == 2) {
            s += Integer.toHexString(Integer.parseInt(xiuGai.getDuanxiang()));
        } else {
            s += "0" + Integer.toHexString(Integer.parseInt(xiuGai.getDuanxiang()));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getM13()))).length() == 2) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getM13())));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getM13())));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getM30()))).length() == 2) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getM30())));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getM30())));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian1()) * 10)).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian1()) * 10));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian1()) * 10)).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian1()) * 10));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian1()) * 10));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian2()) * 10)).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian2()) * 10));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian2()) * 10)).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian2()) * 10));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getChuanlian2()) * 10));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian1()) * 10)).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian1()) * 10));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian1()) * 10)).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian1()) * 10));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian1()) * 10));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian2()) * 10)).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian2()) * 10));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian2()) * 10)).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian2()) * 10));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getBinglian2()) * 10));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getDuanxiangzhiliu()) * 100)).length() == 2) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getDuanxiangzhiliu()) * 100));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getDuanxiangzhiliu()) * 100));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getJiaoliu()) * 100)).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getJiaoliu()) * 100));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getJiaoliu()) * 100)).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getJiaoliu()) * 100));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getJiaoliu()) * 100));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangjian()))).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangjian())));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangjian()))).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangjian())));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangjian())));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduidi()))).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduidi())));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduidi()))).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduidi())));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduidi())));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduixianquan()))).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduixianquan())));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduixianquan()))).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduixianquan())));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getXiangduixianquan())));
        }
        if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getXianquan()))).length() == 4) {
            s += Integer.toHexString((int) (Float.parseFloat(xiuGai.getXianquan())));
        } else if (Integer.toHexString((int) (Float.parseFloat(xiuGai.getXianquan()))).length() == 2) {
            s += "00" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getXianquan())));
        } else {
            s += "0" + Integer.toHexString((int) (Float.parseFloat(xiuGai.getXianquan())));
        }
        int[] arr = new int[31];
        List list = new Utils().getDivLines(s, 2);
        System.out.println(list.size() + "///////");
        for (int i = 0; i < list.size(); i++) {
            arr[i] = 0x00 + Integer.parseInt((String) list.get(i), 16);
        }
        arr[list.size()] = new Utils().getXor(new Utils().getDivLines(s, 2));
        return arr;
    }

    public void add(int gonwei) {
        XiuGai xiuGai = new XiuGai();
        xiuGai.setCecheng("1");
        xiuGai.setQidong("150");
        xiuGai.setGonwei(gonwei);
        xiuGai.setDuanxiang("250");
        xiuGai.setM13("1");
        xiuGai.setM30("1");
        xiuGai.setChuanlian1("20.0");
        xiuGai.setChuanlian2("27.5");
        xiuGai.setBinglian1("20.0");
        xiuGai.setBinglian2("27.5");
        xiuGai.setDuanxiangzhiliu("0.2");
        xiuGai.setJiaoliu("3.0");
        xiuGai.setXiangjian("500");
        xiuGai.setXiangduidi("500");
        xiuGai.setXiangduixianquan("500");
        xiuGai.setXianquan("500");
        xiuGai.save();
    }

    private boolean is(int num) {
        if (num == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static String jisuan(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b1));
    }

    public static String jisuan2(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b3));
    }

    public static String jisuan3(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b4));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utils.closeSerialPort();
    }

}
