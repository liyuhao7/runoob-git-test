package yinpinSpider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class qingtingFM {

    public static void main(String[] args) {
        List<String> notUrlList = new ArrayList<>();
        Document document = null;
        String url = "https://www.qingting.fm/categories/1599/0/";
        for (int i = 1; i < 76; i++) {
            try {
                document = Jsoup.connect(url+i).get();
            } catch (IOException e) {
                notUrlList.add(url+i);

            }
            List<Element> imgList = document.getElementsByClass("coverImg");
            for (Element ele:imgList
            ) {
                String img = ele.select("img").first().attr("src");
                String name = ele.select("img").first().attr("alt");
                System.out.println(name+"\t"+"http:"+img);
            }
        }
        System.out.println("=====================");
        for (String str:notUrlList
             ) {
            System.out.println(str);
        }

    }
}
