package PinDuoDuo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 6个推荐商品的图片
 */
public class PddSelenium {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<String> goodIdList = new ArrayList();
        HashMap<String,String> urlGoodsMap = new HashMap<>();
        Document document = Jsoup.connect("https://youhui.pinduoduo.com/search/landing?catId=-12").get();
        List<Element> urlElementList = document.getElementsByClass("goods-detail-card-wrapper");
        for (Element element:urlElementList
             ) {
            String url = element.attr("href");
            if (!url.contains("goodsId=")){
                continue;
            }
            System.out.println(url);
            String goodsid = url.split("goodsId=")[1];
            goodIdList.add(goodsid);
        }


        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");

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
        Cookie cookie4 = new Cookie("PDDAccessToken","FMRT6EPG6TEHTVAOVAILU5KWT3NM2ZNLEBU47MQCLEA5B5CEPX5Q113b63e");
        Cookie cookie5 = new Cookie("pdd_user_id","1752982264758");
        Cookie cookie6 = new Cookie("pdd_user_uin","T5LN7X4LDLUQQAM5MYFLFIPLKQ_GEXDA");
        Cookie cookie7 = new Cookie("webp","1");
        Cookie cookie8 = new Cookie("vds","gaKhVDJuJeZfMrVdWCHwKTVfHxpcvTpTvfpwFDVBkwkDZwvsKsqdkeXwquqs");
        Cookie cookie9 = new Cookie("quick_entrance_click_record","20200811%2C1");
        Cookie cookie10 = new Cookie("chat_list_rec_list","chat_list_rec_list_5KDOzl");
        webDriver.manage().addCookie(cookie1);
        webDriver.manage().addCookie(cookie2);
        webDriver.manage().addCookie(cookie3);
        webDriver.manage().addCookie(cookie4);
        webDriver.manage().addCookie(cookie5);
        webDriver.manage().addCookie(cookie6);
        webDriver.manage().addCookie(cookie7);
        webDriver.manage().addCookie(cookie8);
        webDriver.manage().addCookie(cookie9);
        webDriver.manage().addCookie(cookie10);

//        webDriver.navigate().refresh();

        String url1 = "http://yangkeduo.com/goods.html?goods_id=";
        String url2 = "&refer_page_el_sn=99369&refer_rn=&refer_page_name=search_result&refer_page_id=10015_1597053036249_ba1e03irer&refer_page_sn=10015";
        for (String id:goodIdList
             ) {
            String url = url1+id+url2;
            webDriver.navigate().to(url);
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement elementName = ((ChromeDriver) webDriver).findElementByClassName("enable-select");
            System.out.println(elementName.getText()+"\t"+webDriver.getCurrentUrl());
//            WebElement webElement1 = ((ChromeDriver) webDriver).findElementByClassName("_1n2C_e3H");
//            System.out.println(webElement1.getText());
//            String js3 = "arguments[0].scrollIntoView();";
//            ((JavascriptExecutor) webDriver).executeScript(js3,webElement1);
            //滚到六张图片
            String js1 = "window.scrollTo(0,2500);";
            ((JavascriptExecutor) webDriver).executeScript(js1);
            Thread.sleep(3000);
            WebElement webElement = null;
            String str = null;
            try {
                webElement = webDriver.findElement(By.className("_3eTHz5Hr"));
                List<WebElement> webElementList= webElement.findElements(By.cssSelector("img"));
                for (WebElement element:webElementList
                ) {
                    System.out.println(element.getAttribute("src"));
                    str = str + element.getAttribute("src")+"\t";
                }
                if (urlGoodsMap.containsKey(str)){
                    System.out.println(elementName.getText()+"\t"+webDriver.getCurrentUrl());
                }
                urlGoodsMap.put(str,elementName.getText()+"\t"+webDriver.getCurrentUrl());
            } catch (Exception e) {
                System.out.println("图片链接获取异常");
            }



        }


        webDriver.quit();
    }
}
