package cn.edkso.inner_class;

//外部类
public class Person {

//    public static int age;
    public  int age;

    public void eat() {
        System.out.println("人会吃东西");
    }

    //成员内部类
    /**
     * 1、内部类在外部使用时，无法直接实例化，需要借助外部类
     */
    public class Heart{
        int age = 13;
        int temp = 22;

         public void eat(){
             System.out.println("在吃饭");
         }
        public String beat() {
            eat();
            Person.this.eat();
//            return age + "岁的心脏在跳动";
            return Person.this.age + "岁的心脏在跳动";
        }
    }

    public Heart getHeart() {
        return new Heart();
    }
}
