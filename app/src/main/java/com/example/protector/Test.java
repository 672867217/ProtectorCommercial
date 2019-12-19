package com.example.protector;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.protector.SQl.Gonwei;
import com.example.protector.SQl.Operator;
import com.example.protector.SQl.ProductType;
import com.example.protector.SQl.TestData;
import com.example.protector.SQl.XiuGai;
import com.example.protector.util.MyApplication;
import com.example.protector.util.MyDialog;
import com.example.protector.util.SerialPortUtil;
import com.example.protector.util.Utils;

import org.litepal.crud.DataSupport;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Test extends AppCompatActivity implements View.OnClickListener {

    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button test_btn;
    private GridView test_gv1;
    private GridView test_gv2;
    TestGv1ItemAdapter testGv1ItemAdapter;
    TestGv2ItemAdapter testGv2ItemAdapter;
    private TextView stats_tv3;
    private Spinner chanpin_spinner5;
    private TextView stats_tv2;
    private Spinner chanpin_spinner4;
    private Spinner chanpin_spinner2;
    private Spinner chanpin_spinner3;
    private Spinner chanpin_spinner;
    private TextView stats_tv1;
    String[] sp_name = {"其他", "一测", "二测", "三测"};
    String[] sp_save = {"自动", "手动"};
    List sp_ceshi = new ArrayList();
    List sp_chanpin = new ArrayList();
    List sp_shengchan = new ArrayList();
    String[] gv1_name2 = {"一测", "二测", "三测", "其他"};
    List<Bean> list1 = new ArrayList();
    List<TestData> list2 = new ArrayList();
    private List<XiuGai> list;
    private int cecheng = 0, type = 0;
    private Timer timer;
    private MyDialog myDialog;
    TextView testDialog3Tv;
    ImageView testDialog3Img;
    Button testDialog3Btn;
    private Timer timer2;
    BigDecimal b1 = new BigDecimal("0.001");
    BigDecimal b3 = new BigDecimal("0.01");
    BigDecimal b4 = new BigDecimal("0.1");
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    Map<String, Date> map = new HashMap<>();
    private int gonwei;
    private Handler handler;
    private Handler handler2;
    private List anniu;
    private Gonwei gonwei1;
    private String shengchanbianma;
    private Button duqu;
    private boolean is = false;
    private MyApplication app;

    private String num2(String s) {
        String ss = String.format("%08d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(s))));
        if (ss.charAt(0) == '0') {
            return "+" + jisuan2(s);
        } else {
            return "-" + jisuan2((Integer.parseInt(Integer.toBinaryString(~Integer.parseInt(s)).substring(24, 32), 2) + 1) + "");
        }
    }

    private String num(String s) {
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
        setContentView(R.layout.activity_test);
        initView();
        app = (MyApplication) getApplication();
        gonwei1 = DataSupport.find(Gonwei.class, 1);
        View view = LayoutInflater.from(Test.this).inflate(R.layout.dialog_test5, null);
        myDialog = new MyDialog(Test.this, view, R.style.dialog);
        testDialog3Tv = (TextView) view.findViewById(R.id.test_dialog3_tv);
        testDialog3Img = (ImageView) view.findViewById(R.id.test_dialog3_img);
        testDialog3Btn = (Button) view.findViewById(R.id.test_dialog3_btn);
        new Utils().hideNavKey(Test.this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        stats_tv2.setText(dateFormat.format(new Date()));
        anniu = new ArrayList();
        list = DataSupport.findAll(XiuGai.class);
        MainActivity.utils.onReceive(new SerialPortUtil.Receive() {
            @Override
            public void set(String str, List<String> list) {
                switch (str) {
                    case "60":
                        if (timer != null) {
                            timer.cancel();
                            timer = null;
                            handler.sendEmptyMessage(3);
                        }
                        handler2.sendEmptyMessage(2);
                        timer2 = new Timer();
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                timer2.cancel();
                                handler.sendEmptyMessage(2);
                            }
                        };
                        timer2.schedule(timerTask, 240000, 240000);
                        map.put("gonwei" + gonwei, new Date());
                        break;
                    case "61":
                        MainActivity.utils.sendSerialPort("AAFF00310064");
                        chanpin_spinner3.setSelection(0, true);
                        List<String> strings = new Utils().getDivLines(String.format("%08d", Integer.parseInt(Integer.toBinaryString(new Utils().HexToInt(list.get(5))))), 1);
                        System.out.println(strings.toString());
                        if (DataSupport.findAll(Gonwei.class).size() == 0) {
                            gonwei1 = new Gonwei();
                        } else {
                            gonwei1 = DataSupport.find(Gonwei.class, 1);
                        }
                        gonwei1.one = is(Integer.parseInt(strings.get(7)));
                        gonwei1.two = is(Integer.parseInt(strings.get(6)));
                        gonwei1.three = is(Integer.parseInt(strings.get(5)));
                        gonwei1.four = is(Integer.parseInt(strings.get(4)));
                        gonwei1.five = is(Integer.parseInt(strings.get(3)));
                        gonwei1.save();
                        handler.sendEmptyMessage(2);
                        break;
                    case "10":
                        if (timer2 != null) {
                            timer2.cancel();
                        }
                        TestData testData = new TestData();
                        testData.setMode(sp_save[type]);
                        testData.setType(1);
                        testData.setName(chanpin_spinner5.getSelectedItem() + "");
                        testData.setChanpinname(chanpin_spinner.getSelectedItem() + "");
                        testData.setShengchanchang(chanpin_spinner2.getSelectedItem() + "");
                        testData.setXinghao(stats_tv1.getText().toString());
                        testData.setGongwei(new Utils().HexToInt(list.get(5)) + "");
                        testData.setDate(map.get("gonwei" + testData.getGongwei()));
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
                        testData.setM13xianshishijian(jisuan2(new Utils().HexToInt(list.get(54) + list.get(55)) + ""));
                        testData.setM30xianshishijian(jisuan2(new Utils().HexToInt(list.get(56) + list.get(57)) + ""));
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
                        if (chanpin_spinner3.getSelectedItem().equals("其他")) {
                            app.map.put(new Utils().HexToInt(list.get(5)) + "", testData);
                        } else {
                            list2.set(Integer.parseInt(testData.getGongwei()) - 1, testData);
                        }
                        handler.sendEmptyMessage(2);

                        break;
                }
            }
        });

        if (gonwei1 == null) {
            for (int i = 0; i < 5; i++) {
                TestData testData = new TestData();
                testData.setMode(sp_save[type]);
                list2.add(testData);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                switch (i) {
                    case 0:
                        if (gonwei1.one == true) {
                            TestData testData = new TestData();
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        } else {
                            TestData testData = new TestData();
                            testData.setShengchanbianma("");
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        }

                        break;
                    case 1:
                        if (gonwei1.two == true) {
                            TestData testData = new TestData();
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        } else {
                            TestData testData = new TestData();
                            testData.setShengchanbianma("");
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        }
                        break;
                    case 2:
                        if (gonwei1.three == true) {
                            TestData testData = new TestData();
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        } else {
                            TestData testData = new TestData();
                            testData.setShengchanbianma("");
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        }
                        break;
                    case 3:
                        if (gonwei1.four == true) {
                            TestData testData = new TestData();
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        } else {
                            TestData testData = new TestData();
                            testData.setShengchanbianma("");
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        }
                        break;
                    case 4:
                        if (gonwei1.five == true) {
                            TestData testData = new TestData();
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        } else {
                            TestData testData = new TestData();
                            testData.setShengchanbianma("");
                            testData.setMode(sp_save[type]);
                            list2.add(testData);
                        }
                        break;
                }
            }
        }

        testGv2ItemAdapter = new TestGv2ItemAdapter(this, list2);
        test_gv2.setAdapter(testGv2ItemAdapter);
        final List<ProductType> types = DataSupport.findAll(ProductType.class);
        List<Operator> operators = DataSupport.findAll(Operator.class);

        for (int i = 0; i < types.size(); i++) {
            sp_chanpin.add(types.get(i).getName());
            sp_shengchan.add(types.get(i).getChangjia());
        }
        for (int i = 0; i < operators.size(); i++) {
            sp_ceshi.add(operators.get(i).getName());
        }
        ArrayAdapter nameAdapter = new ArrayAdapter(Test.this, R.layout.spinner, sp_name);
        ArrayAdapter ceshiAdapter = new ArrayAdapter(Test.this, R.layout.spinner, sp_ceshi);
        ArrayAdapter chanpinAdapter = new ArrayAdapter(Test.this, R.layout.spinner, sp_chanpin);
        ArrayAdapter saveAdapter = new ArrayAdapter(Test.this, R.layout.spinner, sp_save);

        chanpin_spinner.setAdapter(chanpinAdapter);

        chanpin_spinner3.setAdapter(nameAdapter);
        chanpin_spinner4.setAdapter(saveAdapter);
        chanpin_spinner5.setAdapter(ceshiAdapter);
        chanpin_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (types.size() != 0) {
                    stats_tv1.setText(types.get(i).getXinghao());
                    ArrayAdapter shengchanAdapter = new ArrayAdapter(Test.this, R.layout.spinner, new String[]{sp_shengchan.get(i) + ""});
                    chanpin_spinner2.setAdapter(shengchanAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        chanpin_spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cecheng = position;
                test_gv2.setAdapter(testGv2ItemAdapter);
                //当测程改变  通过串口向测试台主机发送信息
                if (is) {
                    for (int i = 0; i < 5; i++) {
                        MainActivity.utils.sendSerialPort(cmd(i + 1));
                    }
                } else {
                    is = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        chanpin_spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
                for (int i = 0; i < list2.size(); i++) {
                    TestData testData = list2.get(i);
                    if (testData.getSave() == 0) {
                        testData.setMode(sp_save[type]);
                    }

                }
                testGv2ItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //如果是新班次
        int what = getIntent().getIntExtra("what", -1);
        if (what == 1) {
            for (int i = 0; i < 4; i++) {
                TestData testData = new TestData();
                testData.setType(1);
                testData.updateAll("type = ?", "0");
                Bean bean = new Bean();
                bean.ceshi = String.valueOf(0);
                bean.tongguo = String.valueOf(0);
                bean.weitongguo = String.valueOf(0);
                bean.yongshi = "0";
                list1.add(bean);
            }
        } else {
            chanpin_spinner.setSelection(app.scname, true);
            chanpin_spinner3.setSelection(app.cecheng, true);
            chanpin_spinner4.setSelection(app.type, true);
            chanpin_spinner5.setSelection(app.name, true);
            final List<TestData> dataList = DataSupport.findAll(TestData.class);
            for (int i = 1; i < 5; i++) {
                int shuliang = 0, zongshijian = 0;
                String num = String.valueOf(i);
                if (i == 4) {
                    // num = 0 的时候 为其他测程
                    num = 0 + "";
                }
                for (int j = 0; j < dataList.size(); j++) {
                    //遍历数据库数据 如果测程等于1 数量增加;
                    if (dataList.get(j).getType() == 1) {
                        if (dataList.get(j).getCecheng().equals(num)) {
                            shuliang++;
                            zongshijian = Integer.parseInt(dataList.get(j).getCeshishichang());
                        }
                    }
                }
                Bean bean = new Bean();
                bean.ceshi = String.valueOf(shuliang);
                bean.tongguo = String.valueOf(shuliang);
                bean.weitongguo = String.valueOf(shuliang);
                bean.yongshi = zongshijian / 60 + "'" + zongshijian % 60 + "\"";
                list1.add(bean);
            }
        }


        testGv1ItemAdapter = new TestGv1ItemAdapter(this, list1);
        test_gv1.setAdapter(testGv1ItemAdapter);

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        myDialog.show();
                        testDialog3Btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        testDialog3Tv.setText("编码失败！");
                        testDialog3Img.setImageResource(R.drawable.baozhi2);
                        break;
                    case 2:
                        testGv2ItemAdapter = new TestGv2ItemAdapter(getApplicationContext(), list2);
                        test_gv2.setAdapter(testGv2ItemAdapter);
                        break;
                    case 3:
                        myDialog.show();
                        testDialog3Btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        testDialog3Tv.setText("编码成功！");
                        testDialog3Img.setImageResource(R.drawable.gou);
                        break;
                }

            }
        };
        if (gonwei1 == null) {
            MainActivity.utils.sendSerialPort("AAFF00300065");
        }

    }

    private boolean is(int num) {
        if (num == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void initView() {
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        test_btn = (Button) findViewById(R.id.test_btn);
        test_gv1 = (GridView) findViewById(R.id.test_gv1);
        test_gv2 = (GridView) findViewById(R.id.test_gv2);
        stats_tv3 = (TextView) findViewById(R.id.stats_tv3);
        stats_tv2 = (TextView) findViewById(R.id.stats_tv2);
        stats_tv1 = (TextView) findViewById(R.id.stats_tv1);
        chanpin_spinner5 = (Spinner) findViewById(R.id.chanpin_spinner5);
        chanpin_spinner4 = (Spinner) findViewById(R.id.chanpin_spinner4);
        chanpin_spinner2 = (Spinner) findViewById(R.id.chanpin_spinner2);
        chanpin_spinner3 = (Spinner) findViewById(R.id.chanpin_spinner3);
        chanpin_spinner = (Spinner) findViewById(R.id.chanpin_spinner);
        stats_tv1 = (TextView) findViewById(R.id.stats_tv1);
        test_btn.setOnClickListener(this);
        duqu = (Button) findViewById(R.id.duqu);
        duqu.setOnClickListener(this);
    }

    private String jisuan2(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b3));
    }

    private String jisuan3(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b4));
    }

    private String jisuan(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_btn:
                finish();
                app.scname = chanpin_spinner.getSelectedItemPosition();
                app.name = chanpin_spinner5.getSelectedItemPosition();
                app.cecheng = chanpin_spinner3.getSelectedItemPosition();
                app.type = chanpin_spinner4.getSelectedItemPosition();
                break;
            case R.id.duqu:
                MainActivity.utils.sendSerialPort("AAFF00300065");
                break;
        }
    }

    private String cmd(int gongwei) {
        XiuGai xiuGai = list.get(gongwei - 1);
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
        if (new Utils().getXor(new Utils().getDivLines(s, 2)).length() < 2) {
            s += "0" + new Utils().getXor(new Utils().getDivLines(s, 2));
        } else {
            s += new Utils().getXor(new Utils().getDivLines(s, 2));
        }
        return s;
    }

    class Bean {
        String ceshi, tongguo, weitongguo, yongshi;
    }


    public class TestGv1ItemAdapter extends BaseAdapter {

        private List<Bean> objects = new ArrayList<Bean>();

        private Context context;
        private LayoutInflater layoutInflater;

        public TestGv1ItemAdapter(Context context, List<Bean> objects) {
            this.context = context;
            this.objects = objects;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Bean getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.test_gv1_item, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
            return convertView;
        }

        private void initializeViews(Bean bean1, ViewHolder holder, int what) {
            //TODO implement
            holder.testItem1Tv.setText(gv1_name2[what] + "统计");
            holder.testItem1Tv1.setText(bean1.ceshi);
            holder.testItem1Tv2.setText(bean1.tongguo);
            holder.testItem1Tv3.setText(bean1.weitongguo);
            holder.testItem1Tv4.setText(bean1.yongshi);

        }

        protected class ViewHolder {
            private TextView testItem1Tv;
            private TextView testItem1Tv1;
            private TextView testItem1Tv2;
            private TextView testItem1Tv3;
            private TextView testItem1Tv4;

            public ViewHolder(View view) {
                testItem1Tv = (TextView) view.findViewById(R.id.test_item1_tv);
                testItem1Tv1 = (TextView) view.findViewById(R.id.test_item1_tv1);
                testItem1Tv2 = (TextView) view.findViewById(R.id.test_item1_tv2);
                testItem1Tv3 = (TextView) view.findViewById(R.id.test_item1_tv3);
                testItem1Tv4 = (TextView) view.findViewById(R.id.test_item1_tv4);
            }
        }
    }

    public class TestGv2ItemAdapter extends BaseAdapter {

        private List<TestData> objects = new ArrayList();

        private Context context;
        private LayoutInflater layoutInflater;

        public TestGv2ItemAdapter(Context context, List<TestData> objects) {
            this.context = context;
            this.objects = objects;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public TestData getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.test_gv2_item, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
            return convertView;
        }

        private void initializeViews(final TestData testData, final ViewHolder holder, final int what) {
            //TODO implement

            holder.testItem2Tv7.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv8.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv9.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv10.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv11.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv12.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv13.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv15.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv17.setBackgroundResource(R.drawable.dialog_test2_22);
            if (chanpin_spinner3.getSelectedItem().equals("其他")) {
                TestData t;
                if (app.map.get((what + 1) + "") != null) {
                    t = app.map.get((what + 1) + "");
                    setContext(holder, t, what);
                    return;
                }

            }
            holder.testItem2Tv2.setEnabled(true);
            for (int i = 0; i < anniu.size(); i++) {
                if (anniu.get(i).equals(what + 1)) {
                    holder.testBtn2.setEnabled(false);
                    holder.testBtn4.setEnabled(true);
                    holder.testBtn2.setBackgroundResource(R.drawable.queding);
                    holder.testBtn4.setBackgroundResource(R.drawable.dayinweixiuqindan);
                }
            }
            handler2 = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 1:
                            list2.get(what).setShengchanchang("0");
                            notifyDataSetInvalidated();
                            break;
                        case 2:
                            TestData testData1 = new TestData();
                            testData1.setShengchanbianma(shengchanbianma);
                            list2.set(gonwei - 1, testData1);
                            notifyDataSetInvalidated();
                            break;
                    }
                }
            };
            holder.testItem2Tv.setText("工位" + (what + 1));
            holder.testItem2Tv1.setText(testData.getMode());
            holder.testItem2Tv2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        holder.testItem2Tv2.setText("");
                    }
                }
            });
            holder.testItem2Tv2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (holder.testItem2Tv2.getText().toString().equals("")) {
                            Toast.makeText(Test.this, "请输入生产编号", Toast.LENGTH_SHORT).show();
                        } else {
                            if (anniu.size() != 0) {
                                anniu.remove(gonwei + "");
                            }
                            shengchanbianma = holder.testItem2Tv2.getText().toString();
                            holder.testItem2Tv2.clearFocus();
                            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            im.hideSoftInputFromWindow(holder.testItem2Tv2.getWindowToken(), 0);
                            gonwei = 1 + what;
                            String s = Integer.toHexString(Integer.parseInt(holder.testItem2Tv2.getText().toString()));
                            if (s.length() < 8) {
                                s = "0" + s;
                            }
                            MainActivity.utils.sendSerialPort("AAFF0020050" + gonwei + s + new Utils().getXor(new Utils().getDivLines("AAFF0020050" + gonwei + s, 2)));
                            holder.testItem2Tv2.setEnabled(false);
                            timer = new Timer();
                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    timer.cancel();
                                    timer = null;
                                    handler.sendEmptyMessage(1);
                                    handler2.sendEmptyMessage(1);
                                }
                            };
                            timer.schedule(timerTask, 4000, 10000);
                        }
                    }

                    return true;
                }
            });
            holder.testItem2Tv2.setText(testData.getShengchanbianma());
            holder.testItem2Tv3.setText(testData.getChanpinbianma());
            holder.testItem2Tv4.setText(testData.getCecheng());
            if (Integer.parseInt(testData.getCeshishichang()) < 60) {
                holder.testItem2Tv5.setText(testData.getCeshishichang() + "s");
            } else {
                holder.testItem2Tv5.setText(Integer.parseInt(testData.getCeshishichang()) / 60 + "'"
                        + Integer.parseInt(testData.getCeshishichang()) % 60 + "\"");
            }
            //测试结果
            holder.testItem2Tv7.setText(testData.getQidongshijian());
            holder.testItem2Tv9.setText(testData.getM13xianshishijian());
            holder.testItem2Tv10.setText(testData.getM30xianshishijian());
            holder.testItem2Tv11.setText(testData.getXianquanchuanlian5());
            holder.testItem2Tv12.setText(testData.getXianquanbinglian());

            double[] arr = new double[3];
            double[] arr2 = new double[3];
            double[] arr4 = new double[9];
            arr[0] = Double.parseDouble(testData.getAduanxiangxiangying());
            arr[1] = Double.parseDouble(testData.getBduanxiangxiangying());
            arr[2] = Double.parseDouble(testData.getCduanxiangxiangying());

            arr2[0] = Double.parseDouble(testData.getAduanxiangdianya());
            arr2[1] = Double.parseDouble(testData.getBduanxiangdianya());
            arr2[2] = Double.parseDouble(testData.getCduanxiangdianya());

            arr4[0] = Double.parseDouble(testData.getAbxiangjianjueyuan());
            arr4[1] = Double.parseDouble(testData.getAcxiangjianjueyuan());
            arr4[2] = Double.parseDouble(testData.getBcxiangjianjueyuan());
            arr4[3] = Double.parseDouble(testData.getAxiangduidijueyuan());
            arr4[4] = Double.parseDouble(testData.getBxiangduidijueyuan());
            arr4[5] = Double.parseDouble(testData.getCxiangduidijueyuan());
            arr4[6] = Double.parseDouble(testData.getAxiangduixianquanjueyuan());
            arr4[7] = Double.parseDouble(testData.getBxiangduixianquanjueyuan());
            arr4[8] = Double.parseDouble(testData.getCxiangduixianquanjeuyuan());

            Arrays.sort(arr); //断相响应时间
            Arrays.sort(arr2); //断相电压
            Arrays.sort(arr4); //绝缘电阻

            holder.testItem2Tv8.setText(arr[arr.length - 1] + "");
            holder.testItem2Tv13.setText(arr2[arr2.length - 1] + "");
            holder.testItem2Tv15.setText(arr4[0] + "");
            if (cecheng == 0) {
                set2(holder);
            }
            if (gonwei1 != null) {
                switch (what) {
                    case 0:
                        if (!gonwei1.one) {
                            set(holder);
                        }
                        break;
                    case 1:
                        if (!gonwei1.two) {
                            set(holder);
                        }
                        break;
                    case 2:
                        if (!gonwei1.three) {
                            set(holder);
                        }
                        break;
                    case 3:
                        if (!gonwei1.four) {
                            set(holder);
                        }
                        break;
                    case 4:
                        if (!gonwei1.five) {
                            set(holder);
                        }
                        break;
                }
            } else {
                set(holder);
            }

            XiuGai xiuGai = DataSupport.find(XiuGai.class, what + 1);
            boolean is = true;
            if (!testData.getChanpinbianma().equals("0")) {
                if (Double.parseDouble(testData.getQidongshijian()) * 100
                        > Double.parseDouble(xiuGai.getQidong()) * 100) {
                    holder.testItem2Tv7.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv7.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv7.setTextColor(Color.BLACK);
                    holder.testItem2Tv7.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (arr[2] * 100
                        > Double.parseDouble(xiuGai.getDuanxiang()) * 100) {
                    holder.testItem2Tv8.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv8.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv8.setTextColor(Color.BLACK);
                    holder.testItem2Tv8.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (Double.parseDouble(testData.getM13xianshishijian()) * 100
                        < (13 - Double.parseDouble(xiuGai.getM13())) * 100
                        || Double.parseDouble(testData.getM13xianshishijian()) * 100
                        > (13 + Double.parseDouble(xiuGai.getM13())) * 100) {
                    holder.testItem2Tv9.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv9.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv9.setTextColor(Color.BLACK);
                    holder.testItem2Tv9.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (Double.parseDouble(testData.getM30xianshishijian()) * 100
                        < (30 - Double.parseDouble(xiuGai.getM30())) * 100
                        || Double.parseDouble(testData.getM30xianshishijian()) * 100
                        > (30 + Double.parseDouble(xiuGai.getM30())) * 100) {
                    holder.testItem2Tv10.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv10.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv10.setTextColor(Color.BLACK);
                    holder.testItem2Tv10.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (Double.parseDouble(testData.getXianquanchuanlian5()) * 100
                        > Double.parseDouble(xiuGai.getChuanlian2()) * 100
                        || Double.parseDouble(testData.getXianquanchuanlian5()) * 100
                        < Double.parseDouble(xiuGai.getChuanlian1()) * 100) {
                    holder.testItem2Tv11.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv11.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv11.setTextColor(Color.BLACK);
                    holder.testItem2Tv11.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (Double.parseDouble(testData.getXianquanbinglian()) * 100
                        > Double.parseDouble(xiuGai.getBinglian2()) * 100
                        || Double.parseDouble(testData.getXianquanbinglian()) * 100
                        < Double.parseDouble(xiuGai.getBinglian1()) * 100) {
                    holder.testItem2Tv12.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv12.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv12.setTextColor(Color.BLACK);
                    holder.testItem2Tv12.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (arr2[2] * 100
                        > Double.parseDouble(xiuGai.getDuanxiangzhiliu()) * 100) {
                    holder.testItem2Tv13.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv13.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv13.setTextColor(Color.BLACK);
                    holder.testItem2Tv13.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (arr4[0] * 100
                        < Double.parseDouble(xiuGai.getXianquan()) * 100) {
                    holder.testItem2Tv15.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv15.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv15.setTextColor(Color.BLACK);
                    holder.testItem2Tv15.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                is = pandun(is, testData, xiuGai);
                if (is && guzhang(testData.getAjiguzhang(), testData.getBjiguzhang()).equals("无")) {
                    holder.testItem2Tv17.setText("合格");
                    holder.testItem2Tv17.setBackgroundResource(R.drawable.dialog_test2_22);
                    holder.testItem2Tv17.setTextColor(Color.BLACK);
                } else {
                    holder.testItem2Tv17.setText("不合格");
                    holder.testItem2Tv17.setBackgroundResource(R.drawable.dialog_test2_3);
                    holder.testItem2Tv17.setTextColor(Color.WHITE);
                    holder.testBtn4.setEnabled(false);
                    holder.testBtn4.setBackgroundResource(R.drawable.queding);
                }
                //保存模式为自动不可用 手动可用
                testData.setTongguo(holder.testItem2Tv17.getText().toString());
                if (type == 0 && !chanpin_spinner3.getSelectedItem().equals("其他") && testData.getSave() == 0) {
                    if (!anniu.contains(gonwei)) {
                        anniu.add(gonwei);
                    }

                    holder.testBtn2.setEnabled(false);
                    holder.testBtn4.setEnabled(true);
                    holder.testBtn2.setBackgroundResource(R.drawable.queding);
                    holder.testBtn4.setBackgroundResource(R.drawable.dayinweixiuqindan);
                    View view = LayoutInflater.from(Test.this).inflate(R.layout.dialog_test4, null);
                    final MyDialog dialog = new MyDialog(Test.this, view, R.style.dialog);
                    dialog.show();
                    testData.setSave(1);
                    testData.save();
                    final Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            timer.cancel();
                        }
                    };
                    timer.schedule(timerTask, 1000, 200);
                } else {
                    if (testData.getSave() == 1) {
                        holder.testBtn2.setBackgroundResource(R.drawable.queding);
                        holder.testBtn2.setEnabled(false);
                        holder.testBtn4.setEnabled(true);
                        holder.testBtn4.setBackgroundResource(R.drawable.dayinweixiuqindan);
                    } else {
                        holder.testBtn2.setBackgroundResource(R.drawable.baocun);
                        holder.testBtn2.setEnabled(true);
                    }
                }
                //点击保存弹出dialog 1秒后自动关闭
                holder.testBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!anniu.contains(gonwei)) {
                            anniu.add(gonwei);
                        }
                        holder.testBtn2.setEnabled(false);
                        holder.testBtn4.setEnabled(true);
                        holder.testBtn2.setBackgroundResource(R.drawable.queding);
                        holder.testBtn4.setBackgroundResource(R.drawable.dayinweixiuqindan);
                        View view = LayoutInflater.from(Test.this).inflate(R.layout.dialog_test4, null);
                        final MyDialog dialog = new MyDialog(Test.this, view, R.style.dialog);
                        dialog.show();
                        final Timer timer = new Timer();
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                testData.save();
                                dialog.dismiss();
                                timer.cancel();
                            }
                        };
                        timer.schedule(timerTask, 1000, 200);
                    }
                });
            }


        }

        private List gu(String s1) {
            List list = new ArrayList();
            s1 = String.format("%08d", Integer.parseInt(s1));
            System.out.println(s1);
            for (int i = 0; i < s1.length(); i++) {
                switch (i) {
                    case 1:
                        if (s1.charAt(i) == '0') {
                            list.add("通过");
                        } else {
                            list.add("不通过");
                        }
                        break;
                    case 3:
                        if (s1.charAt(i) == '0') {
                            list.add("通过");
                        } else {
                            list.add("不通过");
                        }
                        break;
                    case 5:
                        if (s1.charAt(i) == '0') {
                            list.add("通过");
                        } else {
                            list.add("不通过");
                        }
                        break;
                    case 7:
                        if (s1.charAt(i) == '0') {
                            list.add("通过");
                        } else {
                            list.add("不通过");
                        }
                        break;
                }
            }

            return list;
        }

        private boolean pandun(boolean is, TestData testData, XiuGai xiuGai) {
            List list = gu(testData.getAjiguzhang());
            List list2 = gu(testData.getBjiguzhang());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(0).equals("不通过")) {
                    is = false;
                }
                if (list2.get(0).equals("不通过")) {
                    is = false;
                }
            }
            if (Math.abs(Double.parseDouble(testData.getAxiangawucha())) > 0.15) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getAxiangcwucha())) > 0.15) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getAxiangbwucha())) > 0.15) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getAjiwucha())) > 0.5) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getBxiangawucha())) > 0.15) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getBxiangcwucha())) > 0.15) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getBxiangbwucha())) > 0.15) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getBjiwucha())) > 0.5) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getM13xianshishijian())) - 13 > Double.parseDouble(xiuGai.getM13())) {
                is = false;
            }
            if (Math.abs(Double.parseDouble(testData.getM30xianshishijian())) - 30 > Double.parseDouble(xiuGai.getM30())) {
                is = false;
            }
            if (Double.parseDouble(testData.getQidongshijian()) > Double.parseDouble(xiuGai.getQidong())) {
                is = false;
            }
            if (Double.parseDouble(testData.getAduanxiangxiangying()) > Double.parseDouble(xiuGai.getDuanxiang())) {
                is = false;
            }
            if (Double.parseDouble(testData.getBduanxiangxiangying()) > Double.parseDouble(xiuGai.getDuanxiang())) {
                is = false;
            }
            if (Double.parseDouble(testData.getCduanxiangxiangying()) > Double.parseDouble(xiuGai.getDuanxiang())) {
                is = false;
            }
            if (Double.parseDouble(testData.getAduanxiangdianya()) > Double.parseDouble(xiuGai.getDuanxiangzhiliu())) {
                is = false;
            }
            if (Double.parseDouble(testData.getXianquanbinglian()) > Double.parseDouble(xiuGai.getBinglian2()) ||
                    Double.parseDouble(testData.getXianquanbinglian()) < Double.parseDouble(xiuGai.getBinglian1())) {
                is = false;
            }
            if (Double.parseDouble(testData.getBduanxiangdianya()) > Double.parseDouble(xiuGai.getDuanxiangzhiliu())) {
                is = false;
            }
            if (Double.parseDouble(testData.getXianquanchuanlian5()) > Double.parseDouble(xiuGai.getChuanlian2()) ||
                    Double.parseDouble(testData.getXianquanchuanlian5()) < Double.parseDouble(xiuGai.getChuanlian1())) {
                is = false;
            }
            if (Double.parseDouble(testData.getCduanxiangdianya()) > Double.parseDouble(xiuGai.getDuanxiangzhiliu())) {
                is = false;
            }
            final int[] arr4 = new int[9];
            arr4[0] = Integer.parseInt(testData.getAbxiangjianjueyuan());
            arr4[1] = Integer.parseInt(testData.getAcxiangjianjueyuan());
            arr4[2] = Integer.parseInt(testData.getBcxiangjianjueyuan());
            arr4[3] = Integer.parseInt(testData.getAxiangduidijueyuan());
            arr4[4] = Integer.parseInt(testData.getBxiangduidijueyuan());
            arr4[5] = Integer.parseInt(testData.getCxiangduidijueyuan());
            arr4[6] = Integer.parseInt(testData.getAxiangduixianquanjueyuan());
            arr4[7] = Integer.parseInt(testData.getBxiangduixianquanjueyuan());
            arr4[8] = Integer.parseInt(testData.getCxiangduixianquanjeuyuan());
            Arrays.sort(arr4);
            int dianzu = arr4[0];
            if (Double.parseDouble(String.valueOf(dianzu)) < Double.parseDouble(xiuGai.getXiangduidi())) {
                is = false;
            }
            if (Double.parseDouble(testData.getAxiangceyajiang()) > Double.parseDouble(xiuGai.getJiaoliu())) {
                is = false;
            }
            if (Double.parseDouble(testData.getBxiangceyajiang()) > Double.parseDouble(xiuGai.getJiaoliu())) {
                is = false;
            }
            if (Double.parseDouble(testData.getCxiangceyajiang()) > Double.parseDouble(xiuGai.getJiaoliu())) {
                is = false;
            }
            if (Double.parseDouble(testData.getAbxiangjianjueyuan()) < Double.parseDouble(xiuGai.getXiangjian())) {
                is = false;
            }
            if (Double.parseDouble(testData.getBcxiangjianjueyuan()) < Double.parseDouble(xiuGai.getXiangjian())) {
                is = false;
            }
            if (Double.parseDouble(testData.getAcxiangjianjueyuan()) < Double.parseDouble(xiuGai.getXiangjian())) {
                is = false;
            }
            if (Double.parseDouble(testData.getAxiangduidijueyuan()) < Double.parseDouble(xiuGai.getXiangduidi())) {
                is = false;
            }
            if (Double.parseDouble(testData.getBxiangduidijueyuan()) < Double.parseDouble(xiuGai.getXiangduidi())) {
                is = false;
            }
            if (Double.parseDouble(testData.getCxiangduidijueyuan()) < Double.parseDouble(xiuGai.getXiangduidi())) {
                is = false;
            }
            if (Double.parseDouble(testData.getAxiangduixianquanjueyuan()) < Double.parseDouble(xiuGai.getXiangduixianquan())) {
                is = false;
            }
            if (Double.parseDouble(testData.getBxiangduixianquanjueyuan()) < Double.parseDouble(xiuGai.getXiangduixianquan())) {
                is = false;
            }
            if (Double.parseDouble(testData.getCxiangduixianquanjeuyuan()) < Double.parseDouble(xiuGai.getXiangduixianquan())) {
                is = false;
            }
            if (Double.parseDouble(testData.getXianquanduidijueyuan()) < Double.parseDouble(xiuGai.getXianquan())) {
                is = false;
            }
            if (Double.parseDouble(testData.getXianquanchuanlian1()) > Double.parseDouble(xiuGai.getChuanlian2()) ||
                    Double.parseDouble(testData.getXianquanchuanlian1()) < Double.parseDouble(xiuGai.getChuanlian1())) {
                is = false;
            }
            if (Double.parseDouble(testData.getXianquanchuanlian3()) > Double.parseDouble(xiuGai.getChuanlian2()) ||
                    Double.parseDouble(testData.getXianquanchuanlian3()) < Double.parseDouble(xiuGai.getChuanlian1())) {
                is = false;
            }
            if (Double.parseDouble(testData.getXianquanchuanlian2()) > Double.parseDouble(xiuGai.getChuanlian2()) ||
                    Double.parseDouble(testData.getXianquanchuanlian2()) < Double.parseDouble(xiuGai.getChuanlian1())) {
                is = false;
            }
            if (Double.parseDouble(testData.getXianquanchuanlian4()) > Double.parseDouble(xiuGai.getChuanlian2()) ||
                    Double.parseDouble(testData.getXianquanchuanlian4()) < Double.parseDouble(xiuGai.getChuanlian1())) {
                is = false;
            }
            return is;
        }

        private void setContext(final ViewHolder holder, TestData testData, final int what) {
            holder.testItem2Tv.setText("工位" + (what + 1));
            holder.testItem2Tv1.setText("");
            holder.testItem2Tv2.setText("0");
            holder.testItem2Tv3.setText("0");
            holder.testItem2Tv4.setText("其他");
            if (Integer.parseInt(testData.getCeshishichang()) < 60) {
                holder.testItem2Tv5.setText(testData.getCeshishichang() + "s");
            } else {
                holder.testItem2Tv5.setText(Integer.parseInt(testData.getCeshishichang()) / 60 + "'"
                        + Integer.parseInt(testData.getCeshishichang()) % 60 + "\"");
            }
            //测试结果
            holder.testItem2Tv7.setText(testData.getQidongshijian());
            holder.testItem2Tv9.setText(testData.getM13xianshishijian());
            holder.testItem2Tv10.setText(testData.getM30xianshishijian());
            holder.testItem2Tv11.setText(testData.getXianquanchuanlian5());
            holder.testItem2Tv12.setText(testData.getXianquanbinglian());

            double[] arr = new double[3];
            double[] arr2 = new double[3];
            double[] arr4 = new double[9];
            arr[0] = Double.parseDouble(testData.getAduanxiangxiangying());
            arr[1] = Double.parseDouble(testData.getBduanxiangxiangying());
            arr[2] = Double.parseDouble(testData.getCduanxiangxiangying());

            arr2[0] = Double.parseDouble(testData.getAduanxiangdianya());
            arr2[1] = Double.parseDouble(testData.getBduanxiangdianya());
            arr2[2] = Double.parseDouble(testData.getCduanxiangdianya());

            arr4[0] = Double.parseDouble(testData.getAbxiangjianjueyuan());
            arr4[1] = Double.parseDouble(testData.getAcxiangjianjueyuan());
            arr4[2] = Double.parseDouble(testData.getBcxiangjianjueyuan());
            arr4[3] = Double.parseDouble(testData.getAxiangduidijueyuan());
            arr4[4] = Double.parseDouble(testData.getBxiangduidijueyuan());
            arr4[5] = Double.parseDouble(testData.getCxiangduidijueyuan());
            arr4[6] = Double.parseDouble(testData.getAxiangduixianquanjueyuan());
            arr4[7] = Double.parseDouble(testData.getBxiangduixianquanjueyuan());
            arr4[8] = Double.parseDouble(testData.getCxiangduixianquanjeuyuan());

            Arrays.sort(arr); //断相响应时间
            Arrays.sort(arr2); //断相电压
            Arrays.sort(arr4); //绝缘电阻

            holder.testItem2Tv8.setText(arr[arr.length - 1] + "");
            holder.testItem2Tv13.setText(arr2[arr2.length - 1] + "");
            holder.testItem2Tv15.setText(arr4[0] + "");
            if (cecheng == 0) {
                set2(holder);
            }
            if (gonwei1 != null) {
                switch (what) {
                    case 0:
                        if (!gonwei1.one) {
                            set(holder);
                        }
                        break;
                    case 1:
                        if (!gonwei1.two) {
                            set(holder);
                        }
                        break;
                    case 2:
                        if (!gonwei1.three) {
                            set(holder);
                        }
                        break;
                    case 3:
                        if (!gonwei1.four) {
                            set(holder);
                        }
                        break;
                    case 4:
                        if (!gonwei1.five) {
                            set(holder);
                        }
                        break;
                }
            } else {
                set(holder);
            }

            XiuGai xiuGai = DataSupport.find(XiuGai.class, what + 1);
            boolean is = true;
            if (!testData.getChanpinbianma().equals("0")) {
                if (Double.parseDouble(testData.getQidongshijian()) * 100
                        > Double.parseDouble(xiuGai.getQidong()) * 100) {
                    holder.testItem2Tv7.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv7.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv7.setTextColor(Color.BLACK);
                    holder.testItem2Tv7.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (arr[2] * 100
                        > Double.parseDouble(xiuGai.getDuanxiang()) * 100) {
                    holder.testItem2Tv8.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv8.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv8.setTextColor(Color.BLACK);
                    holder.testItem2Tv8.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (Double.parseDouble(testData.getM13xianshishijian()) * 100
                        < (13 - Double.parseDouble(xiuGai.getM13())) * 100
                        || Double.parseDouble(testData.getM13xianshishijian()) * 100
                        > (13 + Double.parseDouble(xiuGai.getM13())) * 100) {
                    holder.testItem2Tv9.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv9.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv9.setTextColor(Color.BLACK);
                    holder.testItem2Tv9.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (Double.parseDouble(testData.getM30xianshishijian()) * 100
                        < (30 - Double.parseDouble(xiuGai.getM30())) * 100
                        || Double.parseDouble(testData.getM30xianshishijian()) * 100
                        > (30 + Double.parseDouble(xiuGai.getM30())) * 100) {
                    holder.testItem2Tv10.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv10.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv10.setTextColor(Color.BLACK);
                    holder.testItem2Tv10.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (Double.parseDouble(testData.getXianquanchuanlian5()) * 100
                        > Double.parseDouble(xiuGai.getChuanlian2()) * 100
                        || Double.parseDouble(testData.getXianquanchuanlian5()) * 100
                        < Double.parseDouble(xiuGai.getChuanlian1()) * 100) {
                    holder.testItem2Tv11.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv11.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv11.setTextColor(Color.BLACK);
                    holder.testItem2Tv11.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (Double.parseDouble(testData.getXianquanbinglian()) * 100
                        > Double.parseDouble(xiuGai.getBinglian2()) * 100
                        || Double.parseDouble(testData.getXianquanbinglian()) * 100
                        < Double.parseDouble(xiuGai.getBinglian1()) * 100) {
                    holder.testItem2Tv12.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv12.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv12.setTextColor(Color.BLACK);
                    holder.testItem2Tv12.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (arr2[2] * 100
                        > Double.parseDouble(xiuGai.getDuanxiangzhiliu()) * 100) {
                    holder.testItem2Tv13.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv13.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv13.setTextColor(Color.BLACK);
                    holder.testItem2Tv13.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                if (arr4[0] * 100
                        < Double.parseDouble(xiuGai.getXianquan()) * 100) {
                    holder.testItem2Tv15.setBackgroundResource(R.drawable.dialog_test2_3);
                    is = false;
                    holder.testItem2Tv15.setTextColor(Color.WHITE);
                } else {
                    holder.testItem2Tv15.setTextColor(Color.BLACK);
                    holder.testItem2Tv15.setBackgroundResource(R.drawable.dialog_test2_22);
                }
                is = pandun(is, testData, xiuGai);
                if (is && guzhang(testData.getAjiguzhang(), testData.getBjiguzhang()).equals("无")) {
                    holder.testItem2Tv17.setText("合格");
                    holder.testItem2Tv17.setBackgroundResource(R.drawable.dialog_test2_22);
                    holder.testItem2Tv17.setTextColor(Color.BLACK);
                } else {
                    holder.testItem2Tv17.setText("不合格");
                    holder.testItem2Tv17.setBackgroundResource(R.drawable.dialog_test2_3);
                    holder.testItem2Tv17.setTextColor(Color.WHITE);
                    holder.testBtn4.setEnabled(false);
                    holder.testBtn4.setBackgroundResource(R.drawable.queding);
                }
                holder.testItem2Tv17.setBackgroundResource(R.drawable.dialog_test2_3);
                holder.testBtn2.setEnabled(false);
                holder.testBtn4.setEnabled(false);
                holder.testBtn4.setBackgroundResource(R.drawable.queding);
                holder.testBtn2.setBackgroundResource(R.drawable.queding);
            }
        }

        private String guzhang(String s1, String s2) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == '1' || s2.charAt(i) == '1') {
                    return "有";
                }
            }
            return "无";
        }

        private void set2(ViewHolder holder) {
            holder.testBtn2.setBackgroundResource(R.drawable.queding);
            holder.testBtn4.setBackgroundResource(R.drawable.queding);
            holder.testBtn2.setEnabled(false);
            holder.testBtn2.setTextColor(Color.parseColor("#868686"));
            holder.testBtn4.setEnabled(false);
            holder.testBtn4.setTextColor(Color.parseColor("#868686"));
            holder.testItem2Tv2.setEnabled(false);
            holder.testItem2Tv1.setTextColor(Color.parseColor("#868686"));
            holder.testItem2Tv1.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv2.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv3.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv2.setTextColor(Color.parseColor("#868686"));
            holder.testItem2Tv3.setTextColor(Color.parseColor("#868686"));
            holder.t1.setTextColor(Color.parseColor("#868686"));
            holder.t2.setTextColor(Color.parseColor("#868686"));
            holder.t3.setTextColor(Color.parseColor("#868686"));
            holder.testItem2Tv1.setText("");
            holder.testItem2Tv2.setText("0");
            holder.testItem2Tv3.setText("0");
        }

        private void set(ViewHolder holder) {
            holder.testBtn2.setBackgroundResource(R.drawable.queding);
            holder.testBtn4.setBackgroundResource(R.drawable.queding);
            holder.testItem2Tv.setBackgroundResource(R.drawable.huiseshangyuanjiao);
            holder.testItem2Tv2.setEnabled(false);
            holder.testItem2Tv2.setBackgroundResource(R.drawable.dialog_test2_22);
            holder.testItem2Tv2.setTextColor(Color.parseColor("#868686"));
            holder.testItem2Tv.setTextColor(Color.parseColor("#868686"));
            holder.t1.setTextColor(Color.parseColor("#868686"));
            holder.t2.setTextColor(Color.parseColor("#868686"));
            holder.t3.setTextColor(Color.parseColor("#868686"));
            holder.t4.setTextColor(Color.parseColor("#868686"));
            holder.t5.setTextColor(Color.parseColor("#868686"));
            holder.t6.setTextColor(Color.parseColor("#868686"));
            holder.t7.setTextColor(Color.parseColor("#868686"));
            holder.t8.setTextColor(Color.parseColor("#868686"));
            holder.t9.setTextColor(Color.parseColor("#868686"));
            holder.t10.setTextColor(Color.parseColor("#868686"));
            holder.t11.setTextColor(Color.parseColor("#868686"));
            holder.t12.setTextColor(Color.parseColor("#868686"));
            holder.t13.setTextColor(Color.parseColor("#868686"));
            holder.t14.setTextColor(Color.parseColor("#868686"));
            holder.t15.setTextColor(Color.parseColor("#868686"));
            holder.t16.setTextColor(Color.parseColor("#868686"));
            holder.t17.setTextColor(Color.parseColor("#868686"));
            holder.t18.setTextColor(Color.parseColor("#868686"));
            holder.t19.setTextColor(Color.parseColor("#868686"));
            holder.t20.setTextColor(Color.parseColor("#868686"));
            holder.t21.setTextColor(Color.parseColor("#868686"));
            holder.t22.setTextColor(Color.parseColor("#868686"));
            holder.t23.setTextColor(Color.parseColor("#868686"));
            holder.testItem2Tv1.setText("");
            holder.testItem2Tv2.setText("");
            holder.testItem2Tv3.setText("");
            holder.testItem2Tv4.setText("");
            holder.testItem2Tv5.setText("");
            holder.testItem2Tv7.setText("");
            holder.testItem2Tv8.setText("");
            holder.testItem2Tv9.setText("");
            holder.testItem2Tv10.setText("");
            holder.testItem2Tv11.setText("");
            holder.testItem2Tv12.setText("");
            holder.testItem2Tv13.setText("");
            holder.testItem2Tv15.setText("");
            holder.testItem2Tv17.setText("");
            holder.textView27.setTextColor(Color.parseColor("#868686"));
            holder.testBtn2.setEnabled(false);
            holder.testBtn2.setTextColor(Color.parseColor("#868686"));
            holder.testBtn4.setEnabled(false);
            holder.testBtn4.setTextColor(Color.parseColor("#868686"));
        }

        protected class ViewHolder {
            private LinearLayout gv2Layout;
            private TextView testItem2Tv;
            private TextView t1;
            private TextView testItem2Tv1;
            private TextView t2;
            private EditText testItem2Tv2;
            private TextView t3;
            private TextView testItem2Tv3;
            private TextView t4;
            private TextView testItem2Tv4;
            private TextView t5;
            private TextView testItem2Tv5;
            private TextView t6;
            private TextView t7;
            private TextView testItem2Tv7;
            private TextView t8;
            private TextView t9;
            private TextView testItem2Tv8;
            private TextView t10;
            private TextView t11;
            private TextView testItem2Tv9;
            private TextView t12;
            private TextView t13;
            private TextView testItem2Tv10;
            private TextView t14;
            private TextView t15;
            private TextView testItem2Tv11;
            private TextView t16;
            private TextView t17;
            private TextView testItem2Tv12;
            private TextView t18;
            private TextView t19;
            private TextView testItem2Tv13;
            private TextView t20;
            private TextView t21;
            private TextView testItem2Tv15;
            private TextView t22;
            private TextView t23;
            private TextView testItem2Tv17;
            private Button testBtn4;
            private Button testBtn2;
            private TextView textView27;

            public ViewHolder(View view) {
                gv2Layout = (LinearLayout) view.findViewById(R.id.gv2_layout);
                testItem2Tv = (TextView) view.findViewById(R.id.test_item2_tv);
                t1 = (TextView) view.findViewById(R.id.t1);
                testItem2Tv1 = (TextView) view.findViewById(R.id.test_item2_tv1);
                t2 = (TextView) view.findViewById(R.id.t2);
                testItem2Tv2 = (EditText) view.findViewById(R.id.test_item2_tv2);
                t3 = (TextView) view.findViewById(R.id.t3);
                testItem2Tv3 = (TextView) view.findViewById(R.id.test_item2_tv3);
                t4 = (TextView) view.findViewById(R.id.t4);
                testItem2Tv4 = (TextView) view.findViewById(R.id.test_item2_tv4);
                t5 = (TextView) view.findViewById(R.id.t5);
                testItem2Tv5 = (TextView) view.findViewById(R.id.test_item2_tv5);
                t6 = (TextView) view.findViewById(R.id.t6);
                t7 = (TextView) view.findViewById(R.id.t7);
                testItem2Tv7 = (TextView) view.findViewById(R.id.test_item2_tv7);
                t8 = (TextView) view.findViewById(R.id.t8);
                t9 = (TextView) view.findViewById(R.id.t9);
                testItem2Tv8 = (TextView) view.findViewById(R.id.test_item2_tv8);
                t10 = (TextView) view.findViewById(R.id.t10);
                t11 = (TextView) view.findViewById(R.id.t11);
                testItem2Tv9 = (TextView) view.findViewById(R.id.test_item2_tv9);
                t12 = (TextView) view.findViewById(R.id.t12);
                t13 = (TextView) view.findViewById(R.id.t13);
                testItem2Tv10 = (TextView) view.findViewById(R.id.test_item2_tv10);
                t14 = (TextView) view.findViewById(R.id.t14);
                t15 = (TextView) view.findViewById(R.id.t15);
                testItem2Tv11 = (TextView) view.findViewById(R.id.test_item2_tv11);
                t16 = (TextView) view.findViewById(R.id.t16);
                t17 = (TextView) view.findViewById(R.id.t17);
                testItem2Tv12 = (TextView) view.findViewById(R.id.test_item2_tv12);
                t18 = (TextView) view.findViewById(R.id.t18);
                t19 = (TextView) view.findViewById(R.id.t19);
                testItem2Tv13 = (TextView) view.findViewById(R.id.test_item2_tv13);
                t20 = (TextView) view.findViewById(R.id.t20);
                t21 = (TextView) view.findViewById(R.id.t21);
                testItem2Tv15 = (TextView) view.findViewById(R.id.test_item2_tv15);
                t22 = (TextView) view.findViewById(R.id.t22);
                t23 = (TextView) view.findViewById(R.id.t23);
                testItem2Tv17 = (TextView) view.findViewById(R.id.test_item2_tv17);
                testBtn4 = (Button) view.findViewById(R.id.test_btn4);
                testBtn2 = (Button) view.findViewById(R.id.test_btn2);
                textView27 = (TextView) view.findViewById(R.id.textView27);
            }
        }
    }

}
