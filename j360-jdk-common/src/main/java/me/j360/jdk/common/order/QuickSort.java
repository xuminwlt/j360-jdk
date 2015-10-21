package me.j360.jdk.common.order;

/**
 * Created with j360-jdk -> me.j360.jdk.common.order.
 * User: min_xu
 * Date: 2015/10/21
 * Time: 9:51
 * 说明：快速排序（Quick Sort）
 */
public class QuickSort {

    public static  void main(String[] args){
        int a[] = {3,1,5,7,2,4,9,6,10,8};
        quickSort(a,0,9);
        for(int i:a){
            System.out.println(i);
        }
    }

    static void swap(int a, int b)
    {
        int tmp = a;
        a = b;
        b = tmp;
    }

    static int partition(int a[], int low, int high)
    {
        int privotKey = a[low];                             //基准元素
        while(low < high){                                   //从表的两端交替地向中间扫描
            while(low < high  && a[high] >= privotKey) --high;  //从high 所指位置向前搜索，至多到low+1 位置。将比基准元素小的交换到低端
            swap(a[low],a[high]);
            while(low < high  && a[low] <= privotKey ) ++low;
            swap(a[low],a[high]);
        }
        return low;
    }


    static void quickSort(int a[], int low, int high){
        if(low < high){
            int privotLoc = partition(a,  low,  high);  //将表一分为二
            quickSort(a,  low,  privotLoc -1);          //递归对低子表递归排序
            quickSort(a,   privotLoc + 1, high);        //递归对高子表递归排序
        }
    }
}
