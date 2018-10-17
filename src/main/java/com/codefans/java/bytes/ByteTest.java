package com.codefans.java.bytes;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-12 21:47
 */

public class ByteTest {

    public static void main(String[] args) {
        ByteTest bt = new ByteTest();
        bt.bitMove();
    }

    public void bitMove() {
//        Index(N) = N/8 = N >> 3;
//        Position(N) = N%8 = N & 0x07;

        int n = 7;
        System.out.println("n/8=" + n/8);
        System.out.println("n>>3=" + (n >> 3));
        System.out.println("n%8=" + n%8);
        System.out.println("n&0x07=" + (n&0x07));


    }


}
