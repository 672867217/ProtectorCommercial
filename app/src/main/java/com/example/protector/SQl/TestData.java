package com.example.protector.SQl;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Date;

public class TestData extends DataSupport implements Serializable {
    //测试数据表
    //---------------------------


    private int id;
    private int save = 0;//是否保存，1保存，0没保存
    private Date date;//开始时间
    private Date date2;//结束时间
    private int type;//状态值    0---》上一班    1----》新班次
    private String gongwei = "0";//工位
    private String name = "无";//测试人员
    private String xinghao = "无";//型号
    private String shengchanchang = "无";//生产厂
    private String chanpinname = "无";//产品明
    private String mode = "0";//模式
    private String tongguo = "0";//是否通过
    private String cecheng = "0";//测程
    private String ceshishichang = "0";//测试时长
    private String chanpinbianma = "0";//产品编码
    private String shengchanbianma = "0";//生产编码
    private String ajiguzhang = "0";//A机故障测试结果
    private String bjiguzhang = "0";//B机故障测试结果
    private String baojin = "0";//报警状态
    private String xianquanchuanlian1 = "0";//线圈串联电压1
    private String xianquanchuanlian2 = "0";//线圈串联电压2
    private String xianquanchuanlian3 = "0";//线圈串联电压3
    private String xianquanchuanlian4 = "0";//线圈串联电压4
    private String xianquanchuanlian5 = "0";//线圈串联电压5
    private String xianquanbinglian = "0";//线圈并联电压
    private String ajiwucha = "0";//A机驱动电压误差值
    private String bjiwucha = "0";//B机驱动电压误差值
    private String axiangawucha = "0";//A机A相电流误差值
    private String axiangbwucha = "0";//A机B相电流误差值
    private String axiangcwucha = "0";//A机C相电流误差值
    private String bxiangawucha = "0";//B机A相电流误差值
    private String bxiangbwucha = "0";//B机B相电流误差值
    private String bxiangcwucha = "0";//B机C相电流误差值
    private String aduanxiangdianya = "0";//A相断相后驱动电压
    private String bduanxiangdianya = "0";//B相断相后驱动电压
    private String cduanxiangdianya = "0";//C相断相后驱动电压
    private String axiangceyajiang = "0";//A相一次侧压降
    private String bxiangceyajiang = "0";//B相一次侧压降
    private String cxiangceyajiang = "0";//C相一次侧压降
    private String qidongshijian = "0";//启动时间
    private String aduanxiangxiangying = "0";//A相断相响应时间
    private String bduanxiangxiangying = "0";//B相断相响应时间
    private String cduanxiangxiangying = "0";//C相断相响应时间
    private String m13xianshishijian = "0";//13秒限时时间
    private String m30xianshishijian = "0";//30秒限时时间
    private String abxiangjianjueyuan = "0";//AB相间绝缘电阻
    private String acxiangjianjueyuan = "0";//AC相间绝缘电阻
    private String bcxiangjianjueyuan = "0";//BC相间绝缘电阻
    private String axiangduidijueyuan = "0";//A相对地绝缘电阻
    private String bxiangduidijueyuan = "0";//B相对地绝缘电阻
    private String cxiangduidijueyuan = "0";//C相对地绝缘电阻
    private String axiangduixianquanjueyuan = "0";//A相对线圈绝缘电阻
    private String bxiangduixianquanjueyuan = "0";//B相对线圈绝缘电阻
    private String cxiangduixianquanjeuyuan = "0";//C相对线圈绝缘电阻
    private String xianquanduidijueyuan = "0";//线圈对地绝缘电阻

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTongguo() {
        return tongguo;
    }

    public void setTongguo(String tongguo) {
        this.tongguo = tongguo;
    }

    public String getShengchanbianma() {
        return shengchanbianma;
    }

    public void setShengchanbianma(String shengchanbianma) {
        this.shengchanbianma = shengchanbianma;
    }

    public String getChanpinname() {
        return chanpinname;
    }

    public void setChanpinname(String chanpinname) {
        this.chanpinname = chanpinname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public String getShengchanchang() {
        return shengchanchang;
    }

    public void setShengchanchang(String shengchanchang) {
        this.shengchanchang = shengchanchang;
    }

    public Date getDate() {
        return date;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGongwei() {
        return gongwei;
    }

    public void setGongwei(String gongwei) {
        this.gongwei = gongwei;
    }

    public String getCecheng() {
        return cecheng;
    }

    public void setCecheng(String cecheng) {
        this.cecheng = cecheng;
    }

    public String getCeshishichang() {
        return ceshishichang;
    }

    public void setCeshishichang(String ceshishichang) {
        this.ceshishichang = ceshishichang;
    }

    public String getChanpinbianma() {
        return chanpinbianma;
    }

    public void setChanpinbianma(String chanpinbianma) {
        this.chanpinbianma = chanpinbianma;
    }

    public String getAjiguzhang() {
        return ajiguzhang;
    }

    public void setAjiguzhang(String ajiguzhang) {
        this.ajiguzhang = ajiguzhang;
    }

    public String getBjiguzhang() {
        return bjiguzhang;
    }

    public void setBjiguzhang(String bjiguzhang) {
        this.bjiguzhang = bjiguzhang;
    }

    public String getBaojin() {
        return baojin;
    }

    public void setBaojin(String baojin) {
        this.baojin = baojin;
    }

    public String getXianquanchuanlian1() {
        return xianquanchuanlian1;
    }

    public void setXianquanchuanlian1(String xianquanchuanlian1) {
        this.xianquanchuanlian1 = xianquanchuanlian1;
    }

    public String getXianquanchuanlian2() {
        return xianquanchuanlian2;
    }

    public void setXianquanchuanlian2(String xianquanchuanlian2) {
        this.xianquanchuanlian2 = xianquanchuanlian2;
    }

    public String getXianquanchuanlian3() {
        return xianquanchuanlian3;
    }

    public void setXianquanchuanlian3(String xianquanchuanlian3) {
        this.xianquanchuanlian3 = xianquanchuanlian3;
    }

    public String getXianquanchuanlian4() {
        return xianquanchuanlian4;
    }

    public void setXianquanchuanlian4(String xianquanchuanlian4) {
        this.xianquanchuanlian4 = xianquanchuanlian4;
    }

    public String getXianquanchuanlian5() {
        return xianquanchuanlian5;
    }

    public void setXianquanchuanlian5(String xianquanchuanlian5) {
        this.xianquanchuanlian5 = xianquanchuanlian5;
    }

    public String getXianquanbinglian() {
        return xianquanbinglian;
    }

    public void setXianquanbinglian(String xianquanbinglian) {
        this.xianquanbinglian = xianquanbinglian;
    }

    public String getAjiwucha() {
        return ajiwucha;
    }

    public void setAjiwucha(String ajiwucha) {
        this.ajiwucha = ajiwucha;
    }

    public String getBjiwucha() {
        return bjiwucha;
    }

    public void setBjiwucha(String bjiwucha) {
        this.bjiwucha = bjiwucha;
    }

    public String getAxiangawucha() {
        return axiangawucha;
    }

    public void setAxiangawucha(String axiangawucha) {
        this.axiangawucha = axiangawucha;
    }

    public String getAxiangbwucha() {
        return axiangbwucha;
    }

    public void setAxiangbwucha(String axiangbwucha) {
        this.axiangbwucha = axiangbwucha;
    }

    public String getAxiangcwucha() {
        return axiangcwucha;
    }

    public void setAxiangcwucha(String axiangcwucha) {
        this.axiangcwucha = axiangcwucha;
    }

    public String getBxiangawucha() {
        return bxiangawucha;
    }

    public void setBxiangawucha(String bxiangawucha) {
        this.bxiangawucha = bxiangawucha;
    }

    public String getBxiangbwucha() {
        return bxiangbwucha;
    }

    public void setBxiangbwucha(String bxiangbwucha) {
        this.bxiangbwucha = bxiangbwucha;
    }

    public String getBxiangcwucha() {
        return bxiangcwucha;
    }

    public void setBxiangcwucha(String bxiangcwucha) {
        this.bxiangcwucha = bxiangcwucha;
    }

    public String getAduanxiangdianya() {
        return aduanxiangdianya;
    }

    public void setAduanxiangdianya(String aduanxiangdianya) {
        this.aduanxiangdianya = aduanxiangdianya;
    }

    public String getBduanxiangdianya() {
        return bduanxiangdianya;
    }

    public void setBduanxiangdianya(String bduanxiangdianya) {
        this.bduanxiangdianya = bduanxiangdianya;
    }

    public String getCduanxiangdianya() {
        return cduanxiangdianya;
    }

    public void setCduanxiangdianya(String cduanxiangdianya) {
        this.cduanxiangdianya = cduanxiangdianya;
    }

    public String getAxiangceyajiang() {
        return axiangceyajiang;
    }

    public void setAxiangceyajiang(String axiangceyajiang) {
        this.axiangceyajiang = axiangceyajiang;
    }

    public String getBxiangceyajiang() {
        return bxiangceyajiang;
    }

    public void setBxiangceyajiang(String bxiangceyajiang) {
        this.bxiangceyajiang = bxiangceyajiang;
    }

    public String getCxiangceyajiang() {
        return cxiangceyajiang;
    }

    public void setCxiangceyajiang(String cxiangceyajiang) {
        this.cxiangceyajiang = cxiangceyajiang;
    }

    public String getQidongshijian() {
        return qidongshijian;
    }

    public void setQidongshijian(String qidongshijian) {
        this.qidongshijian = qidongshijian;
    }

    public String getAduanxiangxiangying() {
        return aduanxiangxiangying;
    }

    public void setAduanxiangxiangying(String aduanxiangxiangying) {
        this.aduanxiangxiangying = aduanxiangxiangying;
    }

    public String getBduanxiangxiangying() {
        return bduanxiangxiangying;
    }

    public void setBduanxiangxiangying(String bduanxiangxiangying) {
        this.bduanxiangxiangying = bduanxiangxiangying;
    }

    public String getCduanxiangxiangying() {
        return cduanxiangxiangying;
    }

    public void setCduanxiangxiangying(String cduanxiangxiangying) {
        this.cduanxiangxiangying = cduanxiangxiangying;
    }

    public String getM13xianshishijian() {
        return m13xianshishijian;
    }

    public void setM13xianshishijian(String m13xianshishijian) {
        this.m13xianshishijian = m13xianshishijian;
    }

    public String getM30xianshishijian() {
        return m30xianshishijian;
    }

    public void setM30xianshishijian(String m30xianshishijian) {
        this.m30xianshishijian = m30xianshishijian;
    }

    public String getAbxiangjianjueyuan() {
        return abxiangjianjueyuan;
    }

    public void setAbxiangjianjueyuan(String abxiangjianjueyuan) {
        this.abxiangjianjueyuan = abxiangjianjueyuan;
    }

    public String getAcxiangjianjueyuan() {
        return acxiangjianjueyuan;
    }

    public void setAcxiangjianjueyuan(String acxiangjianjueyuan) {
        this.acxiangjianjueyuan = acxiangjianjueyuan;
    }

    public String getBcxiangjianjueyuan() {
        return bcxiangjianjueyuan;
    }

    public void setBcxiangjianjueyuan(String bcxiangjianjueyuan) {
        this.bcxiangjianjueyuan = bcxiangjianjueyuan;
    }

    public String getAxiangduidijueyuan() {
        return axiangduidijueyuan;
    }

    public void setAxiangduidijueyuan(String axiangduidijueyuan) {
        this.axiangduidijueyuan = axiangduidijueyuan;
    }

    public String getBxiangduidijueyuan() {
        return bxiangduidijueyuan;
    }

    public void setBxiangduidijueyuan(String bxiangduidijueyuan) {
        this.bxiangduidijueyuan = bxiangduidijueyuan;
    }

    public String getCxiangduidijueyuan() {
        return cxiangduidijueyuan;
    }

    public void setCxiangduidijueyuan(String cxiangduidijueyuan) {
        this.cxiangduidijueyuan = cxiangduidijueyuan;
    }

    public String getAxiangduixianquanjueyuan() {
        return axiangduixianquanjueyuan;
    }

    public void setAxiangduixianquanjueyuan(String axiangduixianquanjueyuan) {
        this.axiangduixianquanjueyuan = axiangduixianquanjueyuan;
    }

    public String getBxiangduixianquanjueyuan() {
        return bxiangduixianquanjueyuan;
    }

    public void setBxiangduixianquanjueyuan(String bxiangduixianquanjueyuan) {
        this.bxiangduixianquanjueyuan = bxiangduixianquanjueyuan;
    }

    public String getCxiangduixianquanjeuyuan() {
        return cxiangduixianquanjeuyuan;
    }

    public void setCxiangduixianquanjeuyuan(String cxiangduixianquanjeuyuan) {
        this.cxiangduixianquanjeuyuan = cxiangduixianquanjeuyuan;
    }

    public String getXianquanduidijueyuan() {
        return xianquanduidijueyuan;
    }

    public void setXianquanduidijueyuan(String xianquanduidijueyuan) {
        this.xianquanduidijueyuan = xianquanduidijueyuan;
    }
}
