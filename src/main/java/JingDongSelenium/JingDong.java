package JingDongSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class JingDong {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();//最大窗口
        webDriver.get("https://list.jd.com/list.html?cat=737,794,798");
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        for (int i = 0; i < 100; i++) {
            String handle = webDriver.getWindowHandle();
            String oldhandle = handle;
            System.out.println(i);
            ((ChromeDriver) webDriver).executeScript("window.scrollTo(0,1500)");
            Thread.sleep(1000);
            ((ChromeDriver) webDriver).executeScript("window.scrollTo(1500,3000)");
            Thread.sleep(1000);
            ((ChromeDriver) webDriver).executeScript("window.scrollTo(3000,4500)");
            Thread.sleep(1000);
            ((ChromeDriver) webDriver).executeScript("window.scrollTo(4500,6000)");

            List<WebElement> goodsList = ((ChromeDriver) webDriver).findElementsByClassName("gl-item");
//            System.out.println(goodsList.size());
            for (WebElement ele:goodsList
                 ) {
                Thread.sleep(2000);
                try {
                    ele.click();
                } catch (Exception e) {
                    continue;
                }
                Set<String> handle1 = webDriver.getWindowHandles();
                for (String str : handle1) {
                    if (str.equals(handle)) {
                        continue;
                    }
                    webDriver.switchTo().window(str);
                    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                }
                try {
                    System.out.println(webDriver.findElement(By.id("spec-img")).getAttribute("alt")+"\t"+webDriver.findElement(By.id("spec-img")).getAttribute("src"));
                } catch (Exception e) {
                    continue;
                }
                webDriver.close();
                webDriver.switchTo().window(oldhandle);
            }

            ((ChromeDriver) webDriver).findElementByClassName("pn-next").click();
        }


    webDriver.quit();
    }
}
