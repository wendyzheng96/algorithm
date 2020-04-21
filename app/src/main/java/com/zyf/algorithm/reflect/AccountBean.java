package com.zyf.algorithm.reflect;

/**
 * 帐号信息
 */
public class AccountBean {

    // 帐号ID
    public String id;
    //警务编号
    protected String staffCard;

    String officeCode;

    private String userLevel;


    public AccountBean() {
        System.out.println("无参构造函数");
    }

    private AccountBean(String id) {
        this.id = id;
        System.out.println("带有String参数的构造函数，id:" + id);
    }

    public void show(){
        System.out.println("默认函数方法show(), id == " + id);
    }

    void show2(String s){
        System.out.println("默认函数方法show2(String s), s : " + s);
    }
}
