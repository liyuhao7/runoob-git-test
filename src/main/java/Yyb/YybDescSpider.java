package Yyb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class YybDescSpider {

    public static void sss(String desc){
        Document document = null;
        try {
            document = Jsoup.connect("https://sj.qq.com/myapp/search.htm?kw="+desc).get();
        }catch (Exception e){
            System.out.println(desc+"  搜索界面获取异常");
        }

        try {
            System.out.println(document.html());




        }catch (Exception e){

        }
    }

    public static void main(String[] args) {

        sss("qq");
    }
}
