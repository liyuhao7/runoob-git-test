package PinDuoDuo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PddUrlSelenium {

    public static void main(String[] args) throws InterruptedException, IOException {
        ArrayList<String> KindList1 = new ArrayList<>();
        ArrayList<String> GoodsIdList = new ArrayList<>();




        Document document = null;
        try {
            document = Jsoup.connect("https://youhui.pinduoduo.com/search/landing?catId=-1").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element root1 = document.getElementsByClass("goods-list-nav-lv1").first();
        List<Element> aList = root1.select("a");
        for (Element element:aList
             ) {
            KindList1.add(element.attr("href"));
            System.out.println(element.attr("href"));
        }





        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        Cookie cookie1 = new Cookie("api_uid","CiE2DF8xG4c4VgBSneG3Ag==");
        Cookie cookie2 = new Cookie("_nano_fp","XpdbnqganqXanqdyXC_WionUnVVBd_Ue2w3nNqAz");


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        WebDriver webDriver = new ChromeDriver(chromeOptions);
//        webDriver.manage().addCookie(cookie1);
//        webDriver.manage().addCookie(cookie2);
        webDriver.manage().window().maximize();//最大窗口
//        webDriver.get("https://youhui.pinduoduo.com/search/landing?catId=-1");
//        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        ((ChromeDriver) webDriver).findElementByLinkText("百货").click();
        for (String kind :KindList1
             ) {
            webDriver.navigate().to("https://youhui.pinduoduo.com"+kind);
            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            List<WebElement> goodsWebEleList = ((ChromeDriver) webDriver).findElementsByClassName("goods-detail-card-wrapper");
            while (((ChromeDriver) webDriver).executeScript("arguments[0].scrollIntoView();",((ChromeDriver) webDriver).findElementByLinkText("下一页"))!=null){
                for (WebElement webElement:goodsWebEleList
                ) {
                    String url = webElement.getAttribute("href");
                    if (!url.contains("goodsId=")){
                        continue;
                    }
                    String goodsid = url.split("goodsId=")[1];
                    GoodsIdList.add(goodsid);
                    System.out.println(goodsid);
                }
                ((ChromeDriver) webDriver).findElementByLinkText("下一页").click();
                Thread.sleep(3000);
            }

//            ((ChromeDriver) webDriver).executeScript("arguments[0].scrollIntoView();",((ChromeDriver) webDriver).findElementByLinkText("下一页"));

        }
        webDriver.quit();
    }

}
