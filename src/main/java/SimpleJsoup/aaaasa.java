package SimpleJsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class aaaasa {
    public static void main(String[] args) throws IOException {
        ArrayList<String> urlList = new ArrayList<>();
        File file = new File("src/yuanfudaourl2.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            urlList.add(str);
        }
        for (String url : urlList
        ) {
            String id = url.split("/lessons/")[1].split(".html")[0];
            Document document = null;
            try {
                document = Jsoup.connect(url).get();
//                System.out.println(document);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements scripts = document.select("script");
            String lesson = null;
            for (Element script : scripts
            ) {

                if (script.html().contains("T.CommonWebView.setTitle")) {
                    lesson = script.html().split("title:")[1].split("\"")[1];
//                System.out.println(script.html().split("title:")[1].split("\"")[1]);
                }
            }
            Elements grades = document.getElementsByClass("basic-info");
//            Element ps = grades.select("p").get(1);
            Elements teachers = grades.select("ul");
            Elements prices = document.getElementsByClass("price");
            String money = prices.select("em").text();
            try{
                Integer.parseInt(money);
            }catch (Exception e ){
                Elements pri = prices.select("strong");
                money = pri.text();
            }
            Elements teacherList = teachers.select("li");
            String teacher ="";
            for (Element e:teacherList
            ) {
                teacher = teacher+e.text()+"\t";
            }
            System.out.println(id+"\t"+teacher+"\t"+money);
//            String grade = ps.text().split("\\|")[0].split("适合")[1];
//            String subject = lesson.substring(lesson.indexOf("【") + 1, lesson.indexOf("】"));
//            System.out.println(id + teacher);
        }


    }
}
