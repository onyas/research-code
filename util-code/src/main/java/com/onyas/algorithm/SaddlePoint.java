package com.onyas.algorithm;

public class SaddlePoint {

    // 找鞍点(一个数字在该行最大，在该列最小)
    public static void main(String[] args) {
        int arr[][] = {{9, 80, 205, 40}, {90, -60, 96, 1}, {210, -3, 101, 89}};
        for (int i = 0; i < arr.length; i++) {//一行一行遍历
            int max = Integer.MIN_VALUE;
            int column = -1;
            for (int j = 0; j < arr[i].length; j++) {//该循环找到该行最大的值
                if (arr[i][j] > max) {
                    max = arr[i][j];
                    column = j;
                }
            }

            System.out.println("max = " + max + ",column = " + column);
            boolean isTarget = true;
            for (int k = 0; k < arr.length; k++) {//该循环对比该值是否为当前列中最小的，注意column没有变，只增加行
                if (arr[k][column] < max) {  //如果有比该数小的，马上结束，开始下一循环
                    isTarget = false;
                    break;
                }
            }

            if (isTarget) {//走到这里，说明有鞍点，并打印出来
                System.out.println(max);
            }

        }
    }

}
