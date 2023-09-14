package com.itfeng.base.codeblock;

/**
 * 局部代码快
 */
public class CodeBlockTest {
    public static void main(String[] args) {
        //局部代码块
        {
            int n = 100;
        }
        // 在方法中出现，可以限定变量生命周期，及早释放，提高内存利用率。
        // 局部代码块中声明的变量在代码块外部访问不到！
        // System.out.println(n);
    }
}
