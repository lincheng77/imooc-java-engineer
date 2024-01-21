package cn.edkso;

import java.util.ArrayList;
import java.util.List;

public class GenericMethod {

    //public 后面的<T> 表示这是一个泛型方法
    public <T> List<T> transferToList(T[] array){
        List<T> list = new ArrayList<>();
        for (T item : array) {
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        String[] array = {"张三","李四","王五"};

        //调用泛型方法，方法传入的类型就是泛型方法的类型
        List<String> list = genericMethod.transferToList(array);
        System.out.println(list);
    }
}
