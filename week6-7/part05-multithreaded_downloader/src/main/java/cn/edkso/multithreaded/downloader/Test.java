package cn.edkso.multithreaded.downloader;

public class Test {
    public static void main(String[] args) {
        System.out.println("测试远程开发");
        System.out.println("竟然可以两个人都操作这个代码，一个人写，另外一个人也行看到");
        System.out.println("真的太神奇了");
        System.out.println("牛逼！！！");

        for (int i = 0; i < 1000000000; i++) {
            int a = i * 1000 + 2;
            if (i % 1000 == 0) System.out.println(i);
            for (int j = 0; j < 10000000; j++) {
                int b = i + j;
                for (int k = 0; k < 1000000; k++) {
                    int c = i - j + k * 3;
                }
            }
        }
    }
}
