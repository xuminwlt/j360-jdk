package me.j360.jdk.common.order;

/**
 * Created with j360-jdk -> me.j360.jdk.common.order.
 * User: min_xu
 * Date: 2015/10/21
 * Time: 10:00
 * 说明：选择排序—简单选择排序（Simple Selection Sort）
 */
public class SimpleSelectSort {

    public static void main(String[] args){
        int a[] = {3,1,5,7,2,4,9,6};
        selectSort(a, 8);
        for(int i:a){
            System.out.println(i);
        }
    }

    /**
     * 在要排序的一组数中，选出最小（或者最大）的一个数与第1个位置的数交换；
     * 然后在剩下的数当中再找最小（或者最大）的与第2个位置的数交换，依次类推，直到第n-1个元素（倒数第二个数）和第n个元素（最后一个数）比较为止。
     * 操作方法：
     第一趟，从n 个记录中找出关键码最小的记录与第一个记录交换；
     第二趟，从第二个记录开始的n-1 个记录中再选出关键码最小的记录与第二个记录交换；
     以此类推.....
     第i 趟，则从第i 个记录开始的n-i+1 个记录中选出关键码最小的记录与第i 个记录交换，
     直到整个序列按关键码有序。
     * */
    /**
     * 数组的最小值
     *
     * @return int 数组的键值
     */
    static int selectMinKey(int a[], int n, int i)
    {
        int k = i;
        for(int j=i+1 ;j< n; ++j) {
            if(a[k] > a[j]) k = j;
        }
        return k;
    }

    /**
     * 选择排序
     *
     */
    static void selectSort(int a[], int n){
        int key, tmp;
        for(int i = 0; i< n; ++i) {
            key = selectMinKey(a, n,i);           //选择最小的元素
            if(key != i){
                tmp = a[i];
                a[i] = a[key];
                a[key] = tmp; //最小元素与第i位置元素互换
            }
        }
    }

}

