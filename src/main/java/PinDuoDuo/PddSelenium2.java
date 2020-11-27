package PinDuoDuo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * 店铺商标图片
 */
public class PddSelenium2 {

    public static void main(String[] args) throws InterruptedException, IOException {
        ArrayList<String> goodIdList = new ArrayList();
        HashMap<String,String> urlGoodsMap = new HashMap<>();
        File file = new File("src/pddId.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = bufferedReader.readLine())!=null){
            goodIdList.add(line);
        }
//        Document document = null;
//        try {
//            document = Jsoup.connect("https://youhui.pinduoduo.com/search/landing?catId=-11").get();
//        } catch (IOException e) {
//            System.out.println("拼多多商城连接异常");
//        }
//        List<Element> urlElementList = document.getElementsByClass("goods-detail-card-wrapper");
//        for (Element element:urlElementList
//        ) {
//            String url = element.attr("href");
//            if (!url.contains("goodsId=")){
//                continue;
//            }
//            System.out.println(url);
//            String goodsid = url.split("goodsId=")[1];
//            goodIdList.add(goodsid);
//        }


        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();//最大窗口



        webDriver.get("http://yangkeduo.com/?page_id=10002_1597135777023_38digipziu");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriver.Options manage = webDriver.manage();
//        Set<Cookie> cookies = manage.getCookies();
////        for(Cookie c : cookies){
////            System.out.println(c.getName()+ " = " + c.getValue());
////        }


        manage.deleteAllCookies();

        Cookie cookie1 = new Cookie("api_uid","CiWB918xG4UWhwBgaNwUAg==");
        Cookie cookie2 = new Cookie("ua","Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F84.0.4147.125%20Safari%2F537.36");
        Cookie cookie3 = new Cookie("_nano_fp","Xpdbnq9yXqCxlpTbnC_zxCWViPkgfTzl8GOT3VFR");
        Cookie cookie4 = new Cookie("PDDAccessToken","4K63NJUNCKQIN77UADGMFI5BC7TFZSCEM3WZMALXNCRH7BIFOFRA113b63e");
        Cookie cookie5 = new Cookie("pdd_user_id","1752982264758");
        Cookie cookie6 = new Cookie("pdd_user_uin","T5LN7X4LDLUQQAM5MYFLFIPLKQ_GEXDA");
        Cookie cookie7 = new Cookie("webp","1");
        Cookie cookie8 = new Cookie("vds","gaLsNnmtiQyaGybIyOnEmGENNbQtLEPwiEIOnGQIioGQaOtGEEommtGQbEPE");
//        Cookie cookie9 = new Cookie("quick_entrance_click_record","20200811%2C1");
//        Cookie cookie10 = new Cookie("chat_list_rec_list","chat_list_rec_list_5KDOzl");
        webDriver.manage().addCookie(cookie1);
        webDriver.manage().addCookie(cookie2);
        webDriver.manage().addCookie(cookie3);
        webDriver.manage().addCookie(cookie4);
        webDriver.manage().addCookie(cookie5);
        webDriver.manage().addCookie(cookie6);
        webDriver.manage().addCookie(cookie7);
        webDriver.manage().addCookie(cookie8);
//        webDriver.manage().addCookie(cookie9);
//        webDriver.manage().addCookie(cookie10);



        String url1 = "http://yangkeduo.com/goods.html?goods_id=";
        String url2 = "&refer_page_el_sn=99369&refer_rn=&refer_page_name=search_result&refer_page_id=10015_1597053036249_ba1e03irer&refer_page_sn=10015";
        for (String id:goodIdList
        ) {
            String url = url1+id+url2;
            webDriver.navigate().to(url);
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement elementName = ((ChromeDriver) webDriver).findElementByClassName("enable-select");
            System.out.println(elementName.getText()+"\t"+webDriver.getCurrentUrl());
//            String js1 = "window.scrollTo(0,500);";
//            ((JavascriptExecutor) webDriver).executeScript(js1);
            //滚到店铺图片位置
            ((ChromeDriver) webDriver).executeScript("arguments[0].scrollIntoView();", ((ChromeDriver) webDriver).findElementByClassName("nyCdOUzm"));
            WebElement shopPic = null;
            try {
                shopPic = ((ChromeDriver) webDriver).findElementByClassName("nyCdOUzm");
                Thread.sleep(3000);
                String shopUrl = shopPic.findElement(By.cssSelector("img")).getAttribute("src");
                System.out.println(shopUrl);
            } catch (Exception e) {
                webDriver.quit();
            }
            Thread.sleep(3000);
        }

        webDriver.quit();

    }
}
