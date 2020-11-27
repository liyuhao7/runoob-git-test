package TianMallSelenium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TianMall {

    public static boolean isExist(WebDriver webDriver,By by){
        try {
            webDriver.findElement(by);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        HashMap<String,String> goodsIdMap = new HashMap<>();
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.setExperimentalOption("prefs",prefs);
        chromeOptions.addArguments("--headless");
//        Thread.sleep(20000);
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();//最大窗口
        String url1 = "https://uland.taobao.com/sem/tbsearch?spm=a2e15.8261149.07626516003.4.675229b4beveEl&refpid=mm_26632258_3504122_32538762&clk1=84ddbda8bb579bd549074ef582f21dba&keyword=%E6%89%8B%E6%9C%BA&page=";
        String url2 = "&_input_charset=utf-8";
        for (int i = 21; i < 101; i++) {
            System.out.println("第"+i+"页");
            webDriver.navigate().to(url1+i+url2);

            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            List<WebElement> goodsList = webDriver.findElements(By.className("imgContainer"));
            for (WebElement ele:goodsList
            ) {
                ((ChromeDriver) webDriver).executeScript("arguments[0].scrollIntoView();",ele);
                ele.click();
                String handle = webDriver.getWindowHandle();
                String oldhandle = handle;
                Set<String> handle1 = webDriver.getWindowHandles();
                for (String str : handle1) {
                    if (str.equals(handle)) {
                        continue;
                    }
                    webDriver.switchTo().window(str);
                    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

                }
                WebElement video = null;
                WebElement shop = null;
                String videoUrl = null;
                String shopName = null;
//                video = webDriver.findElement(By.cssSelector("video"));

                Thread.sleep(3000);
                String html = webDriver.getPageSource();
                String goodName = webDriver.getTitle();
                try {
                    video = ((ChromeDriver) webDriver).findElementByClassName("lib-video");
                    videoUrl = video.findElement(By.cssSelector("source")).getAttribute("src");


                } catch (Exception e) {

                }

                if (isExist(webDriver,By.className("slogo-shopname"))){
                    shop = ((ChromeDriver) webDriver).findElementByClassName("slogo-shopname");
                    shopName = shop.findElement(By.cssSelector("strong")).getText();
                    goodName = goodName+"\t"+"天猫";
                }else if (isExist(webDriver,By.className("tb-shop-name"))){
                    shop = ((ChromeDriver) webDriver).findElementByClassName("tb-shop-name");
                    shopName = shop.findElement(By.cssSelector("strong")).getText();
                    goodName = goodName+"\t"+"淘宝";
                }else {
                    webDriver.close();
                    webDriver.switchTo().window(oldhandle);
                    continue;
                }
//                    System.out.println(shopName);

                webDriver.close();
                webDriver.switchTo().window(oldhandle);
                Document document = Jsoup.parse(html);
                List<Element> list1 = document.select("script");
                String pattern1 = "itemId:\"\\d+";
                String pattern4 = "itemId=\\d+";
                String pattern2 = "sellerId:\"\\d+";
                String pattern5 = "sellerId=\\d+";
                String pattern3 = "shopId:\"\\d+";
                String pattern6 = "shopId           : '\\d+";
                for (Element e:list1
                ) {
                    if (e.html().contains("(function(w, d)")){
                        Pattern r1 = Pattern.compile(pattern1);
                        Matcher m1 = r1.matcher(e.html());
                        Pattern r2 = Pattern.compile(pattern2);
                        Matcher m2 = r2.matcher(e.html());
                        Pattern r3 = Pattern.compile(pattern3);
                        Matcher m3 = r3.matcher(e.html());
                        if (m1.find()&m2.find()&m3.find()) {

                            System.out.print(goodName+"\t"+shopName+"\t");
                            System.out.println(m1.group()+"\t"+m2.group()+"\t"+m3.group()+"\t"+videoUrl);
                            goodsIdMap.put(goodName,m1.group()+"\t"+m2.group()+"\t"+m3.group()+"\t"+videoUrl);
                        }

                    }
                    if (e.html().contains("var g_config")){
                        Pattern r1 = Pattern.compile(pattern4);
                        Matcher m1 = r1.matcher(e.html());
                        Pattern r2 = Pattern.compile(pattern5);
                        Matcher m2 = r2.matcher(e.html());
                        Pattern r3 = Pattern.compile(pattern6);
                        Matcher m3 = r3.matcher(e.html());
                        if (m1.find()&m2.find()) {
                            System.out.print(goodName+"\t"+shopName+"\t");
                            System.out.println(m1.group()+"\t"+m2.group()+"\t"+""+"\t"+videoUrl);
                            goodsIdMap.put(goodName,m1.group()+"\t"+m2.group()+"\t"+""+"\t"+videoUrl);
                        }
                    }
                }

            }
            Thread.sleep(1000);
        }
        webDriver.quit();
        File file = new File("src/taobaoshouji.txt");
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (String goods:goodsIdMap.keySet()
             ) {
            bufferedWriter.write(goods+"\t"+goodsIdMap.get(goods)+"\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
