package JingDongSelenium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class getInformation {
    public static void main(String[] args) throws IOException, InterruptedException {

        List<String> list = new ArrayList<>();

        File file = new File("src/京东商品链接.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str = null;
        while ((str = bufferedReader.readLine())!=null){
//            System.out.println(str);
            Document document = null;
            try {
                document = Jsoup.connect(str).get();
            } catch (IOException e) {
                list.add(str);
                continue;
            }
            Thread.sleep(2000);
            Element element = null;
            String img = null;
            String name = null;
            try {
                element = document.getElementById("spec-img");
                img = element.attr("data-origin");
                name = element.attr("alt");
            } catch (Exception e) {
                list.add(str);
                continue;
            }
            String str1[] = img.split("/");
            int k = str1.length;
            img = str1[k-2]+"/"+str1[k-1];
            System.out.println(name+"\t"+img);

        }
        System.out.println("======================================================");
        for (String string:list
             ) {
            System.out.println(string);
        }
    }
}
