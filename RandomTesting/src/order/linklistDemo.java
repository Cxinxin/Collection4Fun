package order;

import java.util.LinkedList;

//单链表查找中间值
public class linklistDemo {

    public static void main(String args[]){


        LinkedList<Integer> arr=new LinkedList <Integer>();

        for(int i=4;i<20;i+=2){
            arr.add(i);
        }
        arr.add(13);
        arr.add(100);

        for(int i=0;i<arr.size();i++){
            System.out.print(arr.get(i)+" ");
        }
        System.out.println();
        System.out.println(getMiddleElementFromLinkedList(arr));

    }

    public static int getMiddleElementFromLinkedList(LinkedList<Integer> ls){

        int result=0;

        int slow=0;
        int fast=1;

        while (fast < ls.size()) {
            fast += 2;
            slow += 1;
        }

        result=ls.get(slow);
        return result;
    }




}
