package JingDongSelenium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
//io
public class a123132 {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();//最大窗口

        String url = "https://item.jd.com/100009177424.html";
        Document document = Jsoup.connect(url).get();
        String str1 = document.html();
        webDriver.get(url);
        String str2 = webDriver.getPageSource();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        File file1 = new File("src/渲染源码.txt");
        File file2 = new File("src/未渲染源码.txt");
        BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(file1));
        BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2));

        file1.createNewFile();
        file2.createNewFile();

        bufferedWriter1.write(str1);
        bufferedWriter2.write(str2);
        webDriver.quit();
    }
}
