package com.application.uberApp.controllers;

import java.util.Arrays;

public class Matrix {
    //[1,1 ]     [2,2]
     //[2,2]     [1,1]
    public static void main(String[] args) {
        int[][] a={{1,1},{2,2}};
        int[][] b={{2,2},{3,3}};
        int row1=a.length;
        int col1=a[0].length;
        int row2=b.length;
        int col2=b[0].length;
        int[][] c=new int[row1][col2];


        for (int i = 0; i < a.length ; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b[0].length; k++) {
                    c[i][j]+=a[i][k]*b[k][j];
                }

            }

        }
//        for (int i = 0; i < c.length; i++) {
//            for (int j = 0; j < c[0].length; j++) {
//                System.out.println(c[i][j]);
//
//            }
//
//        }
        System.out.println(Arrays.deepToString(c));
    }
}
