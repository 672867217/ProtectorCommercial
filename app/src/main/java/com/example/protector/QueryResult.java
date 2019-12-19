package com.example.protector;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.protector.SQl.TestData;
import com.example.protector.SQl.XiuGai;
import com.example.protector.util.MyApplication;
import com.example.protector.util.SerialPortUtil;
import com.example.protector.util.Utils;

import org.litepal.crud.DataSupport;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QueryResult extends AppCompatActivity implements View.OnClickListener {

    private TextView test_item2_tv;
    private TextView textView13;
    private TextView result_tv8;
    private TextView result_tv9;
    private TextView result_tv10;
    private TextView result_tv11;
    private TextView textView11;
    private TextView result_tv12;
    private TextView result_tv13;
    private TextView result_tv14;
    private TextView result_tv15;
    private TextView result_tv16;
    private TextView result_tv17;
    private TextView result_tv18;
    private TextView result_tv19;
    private TextView result_tv20;
    private TextView result_tv21;
    private TextView result_tv22;
    private TextView result_tv23;
    private TextView test_item2_tv6;
    private TextView header_tv;
    private TextView header_tv2;
    private TextView header_tv3;
    private TextView header_tv4;
    private Button btn_dayin;
    private Button btn_cancel;
    private Spinner stats_spinner;
    private TextView stats_tv1;
    private TextView textView5;
    private TextView textView6;
    private TextView result_tv1;
    private TextView result_tv2;
    private TextView result_tv3;
    private TextView result_tv4;
    private TextView result_tv5;
    private TextView result_tv6;
    private TextView result_tv7;
    private ImageView image1;
    private ImageView image2;
    private TextView result2_tv7;
    private TextView result2_tv8;
    private TextView result2_tv9;
    private TextView result2_tv10;
    private TextView result2_tv11;
    private TextView result2_tv12;
    private TextView result2_tv13;
    private TextView result2_tv14;
    private TextView result2_tv15;
    private TextView result2_tv1;
    private TextView result2_tv2;
    private TextView result2_tv3;
    private TextView result2_tv4;
    private TextView result2_tv5;
    private TextView result2_tv6;
    private TextView result2_tv16;
    private TextView result2_tv17;
    private TextView result2_tv18;
    private TextView result2_tv19;
    private TextView result2_tv20;
    private TextView result2_tv21;
    private TextView result2_tv22;
    private TextView result2_tv23;
    private TextView result2_tv24;
    private TextView result2_tv25;
    private TextView result_tv26;
    private TextView jielun;
    private TextView result_tv27;
    private TextView result_tv28;
    private TextView result_tv29;
    private TextView result_tv30;
    private int dianzu;
    private TestData testData;
    private MyApplication app;
    BigDecimal b4 = new BigDecimal("0.1");
    BigDecimal b3 = new BigDecimal("0.01");
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private LinearLayout q13;
    private TextView q1;
    private TextView q3;
    private TextView q2;
    private TextView q4;
    private TextView q5;
    private TextView q9;
    private TextView q7;
    private TextView q11;
    private TextView q6;
    private TextView q10;
    private TextView q8;
    private TextView q12;
    private TextView q29;
    private LinearLayout q28;
    private TextView q14;
    private TextView q15;
    private TextView q17;
    private TextView q16;
    private TextView q18;
    private TextView q19;
    private TextView q20;
    private TextView q24;
    private TextView q22;
    private TextView q26;
    private TextView q21;
    private TextView q25;
    private TextView q23;
    private TextView q27;
    private XiuGai xiuGai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        initView();
        app = (MyApplication) getApplication();
        MainActivity.utils.onReceive(new SerialPortUtil.Receive() {
            @Override
            public void set(String str, List<String> list) {
                if(str.equals("10")){
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
                    testData.setXianquanchuanlian1(MainActivity.jisuan3(new Utils().HexToInt(list.get(17) + list.get(18)) + ""));
                    testData.setXianquanchuanlian2(MainActivity.jisuan3(new Utils().HexToInt(list.get(19) + list.get(20)) + ""));
                    testData.setXianquanchuanlian3(MainActivity.jisuan3(new Utils().HexToInt(list.get(21) + list.get(22)) + ""));
                    testData.setXianquanchuanlian4(MainActivity.jisuan3(new Utils().HexToInt(list.get(23) + list.get(24)) + ""));
                    testData.setXianquanchuanlian5(MainActivity.jisuan3(new Utils().HexToInt(list.get(25) + list.get(26)) + ""));
                    testData.setXianquanbinglian(MainActivity.jisuan3(new Utils().HexToInt(list.get(27) + list.get(28)) + ""));
                    testData.setAjiwucha(MainActivity.num(new Utils().HexToInt(list.get(29)) + ""));
                    testData.setBjiwucha(MainActivity.num(new Utils().HexToInt(list.get(30)) + ""));
                    testData.setAxiangawucha(MainActivity.num2(new Utils().HexToInt(list.get(31)) + ""));
                    testData.setAxiangbwucha(MainActivity.num2(new Utils().HexToInt(list.get(32)) + ""));
                    testData.setAxiangcwucha(MainActivity.num2(new Utils().HexToInt(list.get(33)) + ""));
                    testData.setBxiangawucha(MainActivity.num2(new Utils().HexToInt(list.get(34)) + ""));
                    testData.setBxiangbwucha(MainActivity.num2(new Utils().HexToInt(list.get(35)) + ""));
                    testData.setBxiangcwucha(MainActivity.num2(new Utils().HexToInt(list.get(36)) + ""));
                    testData.setAduanxiangdianya(MainActivity.jisuan2(new Utils().HexToInt(list.get(37)) + ""));
                    testData.setBduanxiangdianya(MainActivity.jisuan2(new Utils().HexToInt(list.get(38)) + ""));
                    testData.setCduanxiangdianya(MainActivity.jisuan2(new Utils().HexToInt(list.get(39)) + ""));
                    testData.setAxiangceyajiang(MainActivity.jisuan(new Utils().HexToInt(list.get(40) + list.get(41)) + ""));
                    testData.setBxiangceyajiang(MainActivity.jisuan(new Utils().HexToInt(list.get(42) + list.get(43)) + ""));
                    testData.setCxiangceyajiang(MainActivity.jisuan(new Utils().HexToInt(list.get(44) + list.get(45)) + ""));
                    testData.setQidongshijian(new Utils().HexToInt(list.get(46) + list.get(47)) + "");
                    testData.setAduanxiangxiangying(new Utils().HexToInt(list.get(48) + list.get(49)) + "");
                    testData.setBduanxiangxiangying(new Utils().HexToInt(list.get(50) + list.get(51)) + "");
                    testData.setCduanxiangxiangying(new Utils().HexToInt(list.get(52) + list.get(53)) + "");
                    testData.setM13xianshishijian(MainActivity.jisuan2(new Utils().HexToInt(list.get(54) + list.get(55)) + ""));
                    testData.setM30xianshishijian(MainActivity.jisuan2(new Utils().HexToInt(list.get(56) + list.get(57)) + ""));
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
                    app.map.put(new Utils().HexToInt(list.get(5))+"",testData);
                }
            }
        });
        new Utils().hideNavKey(QueryResult.this);
        if (getIntent().getBundleExtra("s").getInt("flag") == 1) {
            testData = (TestData) getIntent().getBundleExtra("s").getSerializable("data");
            dianzu = getIntent().getBundleExtra("s").getInt("dianzu");
        } else {
            int id = (int) getIntent().getBundleExtra("s").getSerializable("data");
            testData = DataSupport.find(TestData.class, id);
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
            dianzu = arr4[0];
        }
        xiuGai = DataSupport.find(XiuGai.class, Long.parseLong(testData.getGongwei()));
        ArrayAdapter arrayAdapter = new ArrayAdapter(QueryResult.this, R.layout.spinner, new String[]{testData.getChanpinname() + ""});
        stats_spinner.setAdapter(arrayAdapter);
        stats_tv1.setText(testData.getXinghao());
        textView6.setText(testData.getChanpinbianma());
        result_tv2.setText(testData.getCecheng());
        result_tv3.setText(testData.getGongwei());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
        result_tv4.setText(simpleDateFormat.format(testData.getDate()));
        result_tv5.setText(simpleDateFormat2.format(testData.getDate()));
        result_tv6.setText(simpleDateFormat2.format(testData.getDate2()));
        result_tv7.setText(testData.getName());
        List list = guzhang(testData.getAjiguzhang());
        result_tv8.setText(list.get(0) + "");
        result_tv9.setText(list.get(2) + "");
        result_tv10.setText(list.get(1) + "");
        result_tv11.setText(list.get(3) + "");

        result_tv12.setText(testData.getAxiangawucha());
        result_tv13.setText(testData.getAxiangcwucha());
        result_tv14.setText(testData.getAxiangbwucha());
        result_tv15.setText(testData.getAjiwucha());

        List list2 = guzhang(testData.getBjiguzhang());
        result_tv16.setText(list2.get(0) + "");
        result_tv17.setText(list2.get(2) + "");
        result_tv18.setText(list2.get(1) + "");
        result_tv19.setText(list2.get(3) + "");

        result_tv20.setText(testData.getBxiangawucha());
        result_tv21.setText(testData.getBxiangcwucha());
        result_tv22.setText(testData.getBxiangbwucha());
        result_tv23.setText(testData.getBjiwucha());

        result2_tv1.setText(testData.getM13xianshishijian());
        result2_tv2.setText(testData.getM30xianshishijian());
        result2_tv3.setText(testData.getQidongshijian());
        result2_tv4.setText(testData.getAduanxiangxiangying());
        result2_tv5.setText(testData.getBduanxiangxiangying());
        result2_tv6.setText(testData.getCduanxiangxiangying());
        result2_tv7.setText(testData.getAduanxiangdianya());
        result2_tv8.setText(testData.getXianquanbinglian());
        result2_tv9.setText(testData.getBduanxiangdianya());
        result2_tv10.setText(testData.getXianquanchuanlian5() + "");
        result2_tv11.setText(testData.getCduanxiangdianya());
        result2_tv12.setText(dianzu + "");
        if(testData.getBaojin().charAt(11) == '0'){
            image1.setImageResource(R.drawable.weijietong);
        }else
        {
            image1.setImageResource(R.drawable.jietong);
        }
        if(testData.getBaojin().charAt(12) == '0'){
            image2.setImageResource(R.drawable.weijietong);
        }else
        {
            image2.setImageResource(R.drawable.jietong);
        }
        result2_tv13.setText(testData.getAxiangceyajiang());
        result2_tv14.setText(testData.getBxiangceyajiang());
        result2_tv15.setText(testData.getCxiangceyajiang());
        if(testData.getTongguo().equals("合格")){
            result_tv26.setText("检测到产品符合要求");
            jielun.setBackgroundResource(R.drawable.bg_lvseyuanjiao);
        }else{
            result_tv26.setText("检测到产品不符合要求");
            jielun.setBackgroundResource(R.drawable.bg_hongseyuanjiao);
        }

        result_tv27.setText(testData.getXianquanchuanlian1());
        result_tv28.setText(testData.getXianquanchuanlian3());
        result_tv29.setText(testData.getXianquanchuanlian2());
        result_tv30.setText(testData.getXianquanchuanlian4());
        result2_tv16.setText(testData.getAbxiangjianjueyuan());
        result2_tv17.setText(testData.getBcxiangjianjueyuan());
        result2_tv18.setText(testData.getAcxiangjianjueyuan());
        result2_tv19.setText(testData.getAxiangduidijueyuan());
        result2_tv20.setText(testData.getBxiangduidijueyuan());
        result2_tv21.setText(testData.getCxiangduidijueyuan());
        result2_tv22.setText(testData.getAxiangduixianquanjueyuan());
        result2_tv23.setText(testData.getBxiangduixianquanjueyuan());
        result2_tv24.setText(testData.getCxiangduixianquanjeuyuan());
        result2_tv25.setText(testData.getXianquanduidijueyuan());
        if(result_tv8.getText().toString().equals("不通过")){
            result_tv8.setTextColor(Color.WHITE);
            result_tv8.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(result_tv9.getText().toString().equals("不通过")){
            result_tv9.setTextColor(Color.WHITE);
            result_tv9.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(result_tv10.getText().toString().equals("不通过")){
            result_tv10.setTextColor(Color.WHITE);
            result_tv10.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(result_tv11.getText().toString().equals("不通过")){
            result_tv11.setTextColor(Color.WHITE);
            result_tv11.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getAxiangawucha()))>0.15){
            result_tv12.setTextColor(Color.WHITE);
            result_tv12.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getAxiangcwucha()))>0.15){
            result_tv13.setTextColor(Color.WHITE);
            result_tv13.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getAxiangbwucha()))>0.15){
            result_tv14.setTextColor(Color.WHITE);
            result_tv14.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getAjiwucha()))>0.5){
            result_tv15.setTextColor(Color.WHITE);
            result_tv15.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(result_tv16.getText().toString().equals("不通过")){
            result_tv16.setTextColor(Color.WHITE);
            result_tv16.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(result_tv17.getText().toString().equals("不通过")){
            result_tv17.setTextColor(Color.WHITE);
            result_tv17.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(result_tv18.getText().toString().equals("不通过")){
            result_tv18.setTextColor(Color.WHITE);
            result_tv18.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(result_tv19.getText().toString().equals("不通过")){
            result_tv19.setTextColor(Color.WHITE);
            result_tv19.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getBxiangawucha()))>0.15){
            result_tv20.setTextColor(Color.WHITE);
            result_tv20.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getBxiangcwucha()))>0.15){
            result_tv21.setTextColor(Color.WHITE);
            result_tv21.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getBxiangbwucha()))>0.15){
            result_tv22.setTextColor(Color.WHITE);
            result_tv22.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getBjiwucha()))>0.5){
            result_tv23.setTextColor(Color.WHITE);
            result_tv23.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getM13xianshishijian()))-13>Double.parseDouble(xiuGai.getM13())){
            result2_tv1.setTextColor(Color.WHITE);
            result2_tv1.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Math.abs(Double.parseDouble(testData.getM30xianshishijian()))-30>Double.parseDouble(xiuGai.getM30())){
            result2_tv2.setTextColor(Color.WHITE);
            result2_tv2.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getQidongshijian())>Double.parseDouble(xiuGai.getQidong())){
            result2_tv3.setTextColor(Color.WHITE);
            result2_tv3.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getAduanxiangxiangying())>Double.parseDouble(xiuGai.getDuanxiang())){
            result2_tv4.setTextColor(Color.WHITE);
            result2_tv4.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getBduanxiangxiangying())>Double.parseDouble(xiuGai.getDuanxiang())){
            result2_tv5.setTextColor(Color.WHITE);
            result2_tv5.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getCduanxiangxiangying())>Double.parseDouble(xiuGai.getDuanxiang())){
            result2_tv6.setTextColor(Color.WHITE);
            result2_tv6.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getAduanxiangdianya())>Double.parseDouble(xiuGai.getDuanxiangzhiliu())){
            result2_tv7.setTextColor(Color.WHITE);
            result2_tv7.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getXianquanbinglian())>Double.parseDouble(xiuGai.getBinglian2()) ||
                Double.parseDouble(testData.getXianquanbinglian())<Double.parseDouble(xiuGai.getBinglian1())  ){
            result2_tv8.setTextColor(Color.WHITE);
            result2_tv8.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getBduanxiangdianya())>Double.parseDouble(xiuGai.getDuanxiangzhiliu())){
            result2_tv9.setTextColor(Color.WHITE);
            result2_tv9.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getXianquanchuanlian5())>Double.parseDouble(xiuGai.getChuanlian2()) ||
                Double.parseDouble(testData.getXianquanchuanlian5())<Double.parseDouble(xiuGai.getChuanlian1())){
            result2_tv10.setTextColor(Color.WHITE);
            result2_tv10.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getCduanxiangdianya())>Double.parseDouble(xiuGai.getDuanxiangzhiliu())){
            result2_tv11.setTextColor(Color.WHITE);
            result2_tv11.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(String.valueOf(dianzu))<Double.parseDouble(xiuGai.getXiangduidi())){
            result2_tv12.setTextColor(Color.WHITE);
            result2_tv12.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getAxiangceyajiang())>Double.parseDouble(xiuGai.getJiaoliu())){
            result2_tv13.setTextColor(Color.WHITE);
            result2_tv13.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getBxiangceyajiang())>Double.parseDouble(xiuGai.getJiaoliu())){
            result2_tv14.setTextColor(Color.WHITE);
            result2_tv14.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getCxiangceyajiang())>Double.parseDouble(xiuGai.getJiaoliu())){
            result2_tv15.setTextColor(Color.WHITE);
            result2_tv15.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getAbxiangjianjueyuan())<Double.parseDouble(xiuGai.getXiangjian())){
            result2_tv16.setTextColor(Color.WHITE);
            result2_tv16.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getBcxiangjianjueyuan())<Double.parseDouble(xiuGai.getXiangjian())){
            result2_tv17.setTextColor(Color.WHITE);
            result2_tv17.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getAcxiangjianjueyuan())<Double.parseDouble(xiuGai.getXiangjian())){
            result2_tv18.setTextColor(Color.WHITE);
            result2_tv18.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getAxiangduidijueyuan())<Double.parseDouble(xiuGai.getXiangduidi())){
            result2_tv19.setTextColor(Color.WHITE);
            result2_tv19.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getBxiangduidijueyuan())<Double.parseDouble(xiuGai.getXiangduidi())){
            result2_tv20.setTextColor(Color.WHITE);
            result2_tv20.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getCxiangduidijueyuan())<Double.parseDouble(xiuGai.getXiangduidi())){
            result2_tv21.setTextColor(Color.WHITE);
            result2_tv21.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getAxiangduixianquanjueyuan())<Double.parseDouble(xiuGai.getXiangduixianquan())){
            result2_tv22.setTextColor(Color.WHITE);
            result2_tv22.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getBxiangduixianquanjueyuan())<Double.parseDouble(xiuGai.getXiangduixianquan())){
            result2_tv23.setTextColor(Color.WHITE);
            result2_tv23.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getCxiangduixianquanjeuyuan())<Double.parseDouble(xiuGai.getXiangduixianquan())){
            result2_tv24.setTextColor(Color.WHITE);
            result2_tv24.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getXianquanduidijueyuan())<Double.parseDouble(xiuGai.getXianquan())){
            result2_tv25.setTextColor(Color.WHITE);
            result2_tv25.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getXianquanchuanlian1())>Double.parseDouble(xiuGai.getChuanlian2()) ||
                Double.parseDouble(testData.getXianquanchuanlian1())<Double.parseDouble(xiuGai.getChuanlian1())){
            result_tv27.setTextColor(Color.WHITE);
            result_tv27.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getXianquanchuanlian3())>Double.parseDouble(xiuGai.getChuanlian2()) ||
                Double.parseDouble(testData.getXianquanchuanlian3())<Double.parseDouble(xiuGai.getChuanlian1())){
            result_tv28.setTextColor(Color.WHITE);
            result_tv28.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getXianquanchuanlian2())>Double.parseDouble(xiuGai.getChuanlian2()) ||
                Double.parseDouble(testData.getXianquanchuanlian2())<Double.parseDouble(xiuGai.getChuanlian1())){
            result_tv29.setTextColor(Color.WHITE);
            result_tv29.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(Double.parseDouble(testData.getXianquanchuanlian4())>Double.parseDouble(xiuGai.getChuanlian2()) ||
                Double.parseDouble(testData.getXianquanchuanlian4())<Double.parseDouble(xiuGai.getChuanlian1())){
            result_tv30.setTextColor(Color.WHITE);
            result_tv30.setBackgroundResource(R.drawable.dialog_test2_3);
        }
        if(testData.getBaojin().charAt(13) == '0'){
            test_item2_tv.setTextColor(Color.parseColor("#8a8a8a"));
            test_item2_tv.setBackgroundResource(R.drawable.huiseshangyuanjiao);
            textView13.setTextColor(Color.parseColor("#8a8a8a"));
            textView11.setTextColor(Color.parseColor("#8a8a8a"));
            q1.setTextColor(Color.parseColor("#8a8a8a"));
            q2.setTextColor(Color.parseColor("#8a8a8a"));
            q3.setTextColor(Color.parseColor("#8a8a8a"));
            q4.setTextColor(Color.parseColor("#8a8a8a"));
            q5.setTextColor(Color.parseColor("#8a8a8a"));
            q6.setTextColor(Color.parseColor("#8a8a8a"));
            q7.setTextColor(Color.parseColor("#8a8a8a"));
            q8.setTextColor(Color.parseColor("#8a8a8a"));
            q9.setTextColor(Color.parseColor("#8a8a8a"));
            q10.setTextColor(Color.parseColor("#8a8a8a"));
            q11.setTextColor(Color.parseColor("#8a8a8a"));
            q12.setTextColor(Color.parseColor("#8a8a8a"));
            q13.setBackgroundResource(R.drawable.bg_huiyuanjiao2);
            q14.setTextColor(Color.parseColor("#8a8a8a"));
            q15.setTextColor(Color.parseColor("#8a8a8a"));
            q16.setTextColor(Color.parseColor("#8a8a8a"));
            q17.setTextColor(Color.parseColor("#8a8a8a"));
            q18.setTextColor(Color.parseColor("#8a8a8a"));
            q19.setTextColor(Color.parseColor("#8a8a8a"));
            q20.setTextColor(Color.parseColor("#8a8a8a"));
            q21.setTextColor(Color.parseColor("#8a8a8a"));
            q22.setTextColor(Color.parseColor("#8a8a8a"));
            q23.setTextColor(Color.parseColor("#8a8a8a"));
            q24.setTextColor(Color.parseColor("#8a8a8a"));
            q25.setTextColor(Color.parseColor("#8a8a8a"));
            q26.setTextColor(Color.parseColor("#8a8a8a"));
            q27.setTextColor(Color.parseColor("#8a8a8a"));
            q28.setBackgroundResource(R.drawable.bg_huiyuanjiao2);
            q29.setBackgroundResource(R.drawable.huiseshangyuanjiao);
            q29.setTextColor(Color.parseColor("#8a8a8a"));
            result_tv8.setText("");
            result_tv9.setText("");
            result_tv10.setText("");
            result_tv11.setText("");
            result_tv12.setText("");
            result_tv13.setText("");
            result_tv14.setText("");
            result_tv15.setText("");
            result_tv16.setText("");
            result_tv17.setText("");
            result_tv18.setText("");
            result_tv19.setText("");
            result_tv20.setText("");
            result_tv21.setText("");
            result_tv22.setText("");
            result_tv23.setText("");
            result_tv8.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv9.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv10.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv11.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv12.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv13.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv14.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv15.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv16.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv17.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv18.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv19.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv20.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv21.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv22.setBackgroundResource(R.drawable.dialog_test2_22);
            result_tv23.setBackgroundResource(R.drawable.dialog_test2_22);
        }
    }

    private String jisuan3(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b4));
    }

    private String jisuan2(String s) {
        BigDecimal b2 = new BigDecimal(s);
        return decimalFormat.format(b2.multiply(b3));
    }

    private List guzhang(String s1) {
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

    private void initView() {
        //xsacsdc
        test_item2_tv = (TextView) findViewById(R.id.test_item2_tv);
        textView13 = (TextView) findViewById(R.id.textView13);
        result_tv8 = (TextView) findViewById(R.id.result_tv8);
        result_tv9 = (TextView) findViewById(R.id.result_tv9);
        result_tv10 = (TextView) findViewById(R.id.result_tv10);
        result_tv11 = (TextView) findViewById(R.id.result_tv11);
        textView11 = (TextView) findViewById(R.id.textView11);
        result_tv12 = (TextView) findViewById(R.id.result_tv12);
        result_tv13 = (TextView) findViewById(R.id.result_tv13);
        result_tv14 = (TextView) findViewById(R.id.result_tv14);
        result_tv15 = (TextView) findViewById(R.id.result_tv15);
        result_tv16 = (TextView) findViewById(R.id.result_tv16);
        result_tv17 = (TextView) findViewById(R.id.result_tv17);
        result_tv18 = (TextView) findViewById(R.id.result_tv18);
        result_tv19 = (TextView) findViewById(R.id.result_tv19);
        result_tv20 = (TextView) findViewById(R.id.result_tv20);
        result_tv21 = (TextView) findViewById(R.id.result_tv21);
        result_tv22 = (TextView) findViewById(R.id.result_tv22);
        result_tv23 = (TextView) findViewById(R.id.result_tv23);
        test_item2_tv6 = (TextView) findViewById(R.id.test_item2_tv6);
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv2 = (TextView) findViewById(R.id.header_tv2);
        header_tv3 = (TextView) findViewById(R.id.header_tv3);
        header_tv4 = (TextView) findViewById(R.id.header_tv4);
        btn_dayin = (Button) findViewById(R.id.btn_dayin);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        stats_spinner = (Spinner) findViewById(R.id.stats_spinner);
        stats_tv1 = (TextView) findViewById(R.id.stats_tv1);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        result_tv1 = (TextView) findViewById(R.id.result_tv1);
        result_tv2 = (TextView) findViewById(R.id.result_tv2);
        result_tv3 = (TextView) findViewById(R.id.result_tv3);
        result_tv4 = (TextView) findViewById(R.id.result_tv4);
        result_tv5 = (TextView) findViewById(R.id.result_tv5);
        result_tv6 = (TextView) findViewById(R.id.result_tv6);
        result_tv7 = (TextView) findViewById(R.id.result_tv7);
        jielun = (TextView) findViewById(R.id.jielun);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        result2_tv7 = (TextView) findViewById(R.id.result2_tv7);
        result2_tv8 = (TextView) findViewById(R.id.result2_tv8);
        result2_tv9 = (TextView) findViewById(R.id.result2_tv9);
        result2_tv10 = (TextView) findViewById(R.id.result2_tv10);
        result2_tv11 = (TextView) findViewById(R.id.result2_tv11);
        result2_tv12 = (TextView) findViewById(R.id.result2_tv12);
        result2_tv13 = (TextView) findViewById(R.id.result2_tv13);
        result2_tv14 = (TextView) findViewById(R.id.result2_tv14);
        result2_tv15 = (TextView) findViewById(R.id.result2_tv15);
        result2_tv1 = (TextView) findViewById(R.id.result2_tv1);
        result2_tv2 = (TextView) findViewById(R.id.result2_tv2);
        result2_tv3 = (TextView) findViewById(R.id.result2_tv3);
        result2_tv4 = (TextView) findViewById(R.id.result2_tv4);
        result2_tv5 = (TextView) findViewById(R.id.result2_tv5);
        result2_tv6 = (TextView) findViewById(R.id.result2_tv6);
        result2_tv16 = (TextView) findViewById(R.id.result2_tv16);
        result2_tv17 = (TextView) findViewById(R.id.result2_tv17);
        result2_tv18 = (TextView) findViewById(R.id.result2_tv18);
        result2_tv19 = (TextView) findViewById(R.id.result2_tv19);
        result2_tv20 = (TextView) findViewById(R.id.result2_tv20);
        result2_tv21 = (TextView) findViewById(R.id.result2_tv21);
        result2_tv22 = (TextView) findViewById(R.id.result2_tv22);
        result2_tv23 = (TextView) findViewById(R.id.result2_tv23);
        result2_tv24 = (TextView) findViewById(R.id.result2_tv24);
        result2_tv25 = (TextView) findViewById(R.id.result2_tv25);
        result_tv26 = (TextView) findViewById(R.id.result_tv26);
        result_tv27 = (TextView) findViewById(R.id.result_tv27);
        result_tv28 = (TextView) findViewById(R.id.result_tv28);
        result_tv29 = (TextView) findViewById(R.id.result_tv29);
        result_tv30 = (TextView) findViewById(R.id.result_tv30);

        q13 = (LinearLayout) findViewById(R.id.q13);
        q1 = (TextView) findViewById(R.id.q1);
        q3 = (TextView) findViewById(R.id.q3);
        q2 = (TextView) findViewById(R.id.q2);
        q4 = (TextView) findViewById(R.id.q4);
        q5 = (TextView) findViewById(R.id.q5);
        q9 = (TextView) findViewById(R.id.q9);
        q7 = (TextView) findViewById(R.id.q7);
        q11 = (TextView) findViewById(R.id.q11);
        q6 = (TextView) findViewById(R.id.q6);
        q10 = (TextView) findViewById(R.id.q10);
        q8 = (TextView) findViewById(R.id.q8);
        q12 = (TextView) findViewById(R.id.q12);
        q29 = (TextView) findViewById(R.id.q29);
        q28 = (LinearLayout) findViewById(R.id.q28);
        q14 = (TextView) findViewById(R.id.q14);
        q15 = (TextView) findViewById(R.id.q15);
        q17 = (TextView) findViewById(R.id.q17);
        q16 = (TextView) findViewById(R.id.q16);
        q18 = (TextView) findViewById(R.id.q18);
        q19 = (TextView) findViewById(R.id.q19);
        q20 = (TextView) findViewById(R.id.q20);
        q24 = (TextView) findViewById(R.id.q24);
        q22 = (TextView) findViewById(R.id.q22);
        q26 = (TextView) findViewById(R.id.q26);
        q21 = (TextView) findViewById(R.id.q21);
        q25 = (TextView) findViewById(R.id.q25);
        q23 = (TextView) findViewById(R.id.q23);
        q27 = (TextView) findViewById(R.id.q27);
        btn_dayin.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dayin:

                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
