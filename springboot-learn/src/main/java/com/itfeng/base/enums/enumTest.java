package com.itfeng.base.enums;
enum Color
{
    RED, GREEN, BLUE;
}
public class enumTest {
    public static void main(String[] args) {
        Color myVar = Color.BLUE;

        switch (myVar) {
            case RED:
                System.out.println("红色");
                break;
            case GREEN:
                System.out.println("绿色");
                break;
            case BLUE:
                System.out.println("蓝色");
                break;
        }
    }
}
