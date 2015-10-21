package me.j360.jdk.common.order;

/**
 * Created with j360-jdk -> me.j360.jdk.common.order.
 * User: min_xu
 * Date: 2015/10/21
 * Time: 9:56
 * 说明：直接插入排序(Straight Insertion Sort)
 */
public class InsertionSort {
    public static void main(String[] args){
        int a[] = {3,1,5,7,2,4,9,6};
        insertSort(a, 8);
        for(int i:a){
            System.out.println(i);
        }
    }

    static void insertSort(int a[], int n)
    {
        for(int i= 1; i<n; i++){
            if(a[i] < a[i-1]){               //若第i个元素大于i-1元素，直接插入。小于的话，移动有序表后插入
                int j= i-1;
                int x = a[i];        //复制为哨兵，即存储待排序元素
                a[i] = a[i-1];           //先后移一个元素
                while(x < a[j]){  //查找在有序表的插入位置
                    a[j+1] = a[j];
                    j--;         //元素后移
                }
                a[j+1] = x;      //插入到正确位置
            }
        }

    }
}
