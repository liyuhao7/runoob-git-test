package Selenium_app;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class runSelenium {

    static List<String> pkglist = new ArrayList<>();

    public static void scrolltoPresence(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //移动到元素element对象的“顶端”与当前窗口的“顶部”对齐
//        js.executeScript("arguments[0].scrollIntoView();", element);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        //移动到元素element对象的“底端”与当前窗口的“底部”对齐
        //js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public static void main(String[] args) throws InterruptedException, JSchException, SftpException, IOException {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tt = dateFormat.format(now);

        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver2.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.qimai.cn/rank/marketRank/market/3/category/155/date/" + tt);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String appnameButton = "//*[@id=\"marke-rank-list-index\"]/div[2]/table/tbody/tr[1]/td[2]/div/div/a";


        for (int i = 1; i <= 100; i++) {

            Thread.sleep(5000);
            WebElement buduoduo = ((ChromeDriver) webDriver).findElementByXPath(appnameButton);
//        WebElement buduoduo1 = ((ChromeDriver) webDriver).findElementByXPath("//*[@id=\"marke-rank-list-index\"]/div[2]/table/tbody/tr[2]/td[2]/div/div/a");
            String handle = webDriver.getWindowHandle();
            String oldhandle = handle;

            buduoduo.click();
            Thread.sleep(10000);


            Set<String> handle1 = webDriver.getWindowHandles();
            for (String str : handle1) {
                if (str.equals(handle)) {
                    continue;
                }
                webDriver.switchTo().window(str);
            }
            String title1 = webDriver.getTitle();
            System.out.println(title1);
            WebElement com = ((ChromeDriver) webDriver).findElementByXPath("//*[@id=\"app-container\"]/div[1]/div[1]/div[2]/div[3]/div[2]");
            pkglist.add(com.getText());
            webDriver.close();
            webDriver.switchTo().window(oldhandle);
            scrolltoPresence(webDriver, buduoduo);
            String ii1 = String.valueOf(i + 1);
            String s1 = "tr" + "[" + ii1 + "]";
            appnameButton = "//*[@id=\"marke-rank-list-index\"]/div[2]/table/tbody/" + s1 + "/td[2]/div/div/a";

        }
        webDriver.quit();
        System.out.println(pkglist);

        File file = new File("src/newapp.txt");
        file.createNewFile();//直接覆盖掉旧文件
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(tt + "\n");
        for (int i = 0; i < pkglist.size(); i++) {
            bufferedWriter.write(pkglist.get(i) + "\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
//        connect_216.connectLinux();
    }
}
