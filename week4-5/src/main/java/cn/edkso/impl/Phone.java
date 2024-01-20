package cn.edkso.impl;

public class Phone implements INet{



    public static void main(String[] args) {
        INet phone = new Phone();

        phone.network();//通过接口指向实现实例，调用接口中的默认方法

        INet.stop();//通过接口调用接口中的静态方法

        System.out.println(INet.TEMP);//通过接口调用接口中的静态变量
        System.out.println(INet.temp2);//通过接口调用接口中的静态变量
        System.out.println(INet.temp3);//通过接口调用接口中的静态变量


    }
}
