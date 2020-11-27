package SimpleJsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class YuanfudaoSpider {
    public static void main(String[] args) {
        //获取首页的所有年级和课程的组合url
        ArrayList<String > urlList = new ArrayList<>();
        Document document1 = null;
        try {

//            for (int i = 1; i < 4; i++) {//大班
//                String url = "https://www.yuanfudao.com/lessons/list?grade=100&channelId=" + i +"&studyPhase=xiaoxue&count=50";
//                urlList.add(url);
//            }
//            urlList.add("https://www.yuanfudao.com/lessons/list?grade=" + 100 +"&channelId=" + 201 +"&studyPhase=xiaoxue&count=50");//大班猿编程
            for (int i = 1; i < 13; i++) {//i : grade , j : channelId
                if (i<7){
//                    System.out.println("https://www.yuanfudao.com/lessons/list?grade=" + i +"&channelId=" + 201 +"&studyPhase=xiaoxue&count=50");
                }
                for (int j = 1; j < 10; j++) {
                    String url = "https://www.yuanfudao.com/lessons/list?grade=" + i +"&channelId=" + j +"&studyPhase=xiaoxue&count=50";
                    document1 = Jsoup.connect(url).get();
                    urlList.add(url);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




        //获取每个课程列表界面的url

        Document document = null;

        try {
            for (String str:urlList
                 ) {
                document = Jsoup.connect(str).get();
                Elements scripts = document.select("script");
                for (Element script:scripts
                ) {
//            System.out.println(script.html());
                    if (script.html().contains("T.STORE")){
                        String scriptText = script.html().split("T.STORE=")[1];
//                System.out.println(script.html().split("T.STORE=")[1]);
                        JSONObject jsonObject = JSONObject.parseObject(scriptText.substring(0, scriptText.length() - 1));
                        JSONObject lessonsObj = jsonObject.getJSONObject("lessons");
                        JSONArray listArray = lessonsObj.getJSONArray("list");
                        for (int i=0; i<listArray.size(); i++){
                            JSONObject listObj = listArray.getJSONObject(i);
                            String id = listObj.getString("id");
                            String name = listObj.getString("name");
                            String parse = listObj.getString("phase");
                            String url = "https://www.yuanfudao.com/lessons/groups/" + id + "?groupName=" + name + "&studyPhase=" + parse;
                            if (parse!=null){
                                System.out.println(url);

                            }else {//只有一节课  直接跳转到终极页面
//                                System.out.println("https://www.yuanfudao.com/lessons/" + id +".html");
                            }

                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(document);

    }
}
