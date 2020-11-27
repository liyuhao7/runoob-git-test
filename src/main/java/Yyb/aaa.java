package Yyb;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class aaa {


    public static void main(String[] args) {
        Document document = null;
        try {
            document = Jsoup.connect("https://appgallery1.huawei.com/#/search/QQ").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(document.html());
    }
}
