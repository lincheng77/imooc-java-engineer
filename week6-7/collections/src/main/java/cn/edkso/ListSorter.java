package cn.edkso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSorter {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(70);
        list.add(90);
        list.add(30);
        list.add(40);
        System.out.println(list);

        ListSorterComparator comparator = new ListSorterComparator();
        sort(list, comparator);
        System.out.println(list);
    }

    public static void sort(List<Integer> list, Comparator<Integer> comparator) {
        //下面两种写法都可以
        //区别在于，Collections.sort()方法是静态方法，直接调用即可
        //其实Collections.sort()方法内部也是调用的list.sort()方法
        Collections.sort(list, comparator);
        list.sort(comparator);
    }


    static class ListSorterComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            //后面减前面，降序
            //前面减后面，升序
//            return o2-o1;
            return o1-o2;
        }
    }
}
