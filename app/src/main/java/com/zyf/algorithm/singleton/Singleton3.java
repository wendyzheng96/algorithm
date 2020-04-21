package com.zyf.algorithm.singleton;

/**
 * 单例：静态内部类
 */
public class Singleton3 {

    private Singleton3(){

    }

    public static Singleton3 getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {

        private static Singleton3 INSTANCE = new Singleton3();
    }
}
