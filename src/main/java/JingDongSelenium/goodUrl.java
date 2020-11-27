package JingDongSelenium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class goodUrl {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();//最大窗口
        webDriver.get("https://list.jd.com/list.html?cat=737,794,878");
//        webDriver.get("https://list.jd.com/list.html?cat=9987%2C653%2C655&page=43&s=1261&click=0");
//        Thread.sleep(30000);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        for (int i = 0; i < 100; i++) {
            ((ChromeDriver) webDriver).executeScript("window.scrollTo(0,1500)");
            Thread.sleep(1000);
            ((ChromeDriver) webDriver).executeScript("window.scrollTo(1500,3000)");
            Thread.sleep(1000);
            ((ChromeDriver) webDriver).executeScript("window.scrollTo(3000,4500)");
            Thread.sleep(1000);
            ((ChromeDriver) webDriver).executeScript("window.scrollTo(4500,6000)");
            List<WebElement> goodsList = ((ChromeDriver) webDriver).findElementsByClassName("gl-item");
            for (WebElement ele:goodsList
                 ) {
                String url = ele.findElement(By.cssSelector("a")).getAttribute("href");
                System.out.println(url);
                Document document = null;
                try {
                    document = Jsoup.connect(url).timeout(5000).get();
//                    System.out.println(document.html());
                } catch (IOException e) {
                    continue;
                }
                Element element = null;
                String name = null;
                String img = null;
                try {
                    element = document.getElementById("spec-img");
                    img = element.attr("data-origin");
                    name = element.attr("alt");
                } catch (Exception e) {
                    continue;
                }
//                System.out.println(name+"\t"+img);

            }

            try {
                WebElement nextPage = ((ChromeDriver) webDriver).findElementByXPath("//*[@id=\"J_bottomPage\"]/span[1]/a[9]");
                nextPage.click();
            } catch (Exception e) {
                System.out.println("第" + (i+1)+"页");
                break;
            }
        }
        webDriver.quit();

    }
}
