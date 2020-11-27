package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class dianshangPriority {
    public static void main(String[] args) throws IOException {
        File file1 = new File("src/天猫.txt");
        File file2 = new File("src/淘宝.txt");
        File file3 = new File("src/京东.txt");

        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));
        BufferedReader bufferedReader3 = new BufferedReader(new FileReader(file3));

        String str1 = null;
        String str2 = null;
        String str3 = null;

        while ((str2 = bufferedReader1.readLine())!=null){
            int pri = str2.split(",")[0].length();
            System.out.println(pri);
        }
    }
}
