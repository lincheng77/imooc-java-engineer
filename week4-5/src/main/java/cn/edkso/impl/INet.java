package cn.edkso.impl;

public interface INet extends Base1INet, Base2INet{


    public static final int TEMP = 20;//解释：接口中的变量默认是 public static final 的，所以可以省略不写

    public static int temp2 = 20;//解释：接口中的变量默认是 public static final 的，所以可以省略不写
    public int temp3 = 20;//解释：接口中的变量默认是 public static final 的，所以可以省略不写
    //所以：上面三个变量是等价的

    default void network() {
        System.out.println("我是接口中的默认方法");
    }

    static void stop() {
        System.out.println("我是接口中的静态方法");
    }

    //接口中的方法如果重名，那么集成父接口的接口必须提供default重写的方法
    //接口中的方法如果重名，那么实现接口的类必须自己实现该方法
    default void test() {
        System.out.println("我是test的默认方法");
    }
}
