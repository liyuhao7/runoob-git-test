package SimpleJsoup;

import com.alibaba.fastjson.JSONArray;
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

public class YuanfudaoUrl1 {
    public static void main(String[] args) throws IOException {
        ArrayList<String> urlList = new ArrayList<>();
        File file = new File("src/yuanfudaourl1.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;
        while ((str = bufferedReader.readLine())!=null){
            urlList.add(str);
        }
        for (String url:urlList
             ) {
            Document document = Jsoup.connect(url).get();
//        System.out.println(document);
            Elements scripts = document.select("script");
            for (Element script:scripts
            ) {
//            System.out.println(script.html());
                if (script.html().contains("T.STORE")){
                    String scriptText = script.html().split("T.STORE=")[1];
//                System.out.println(script.html().split("T.STORE=")[1]);
                    JSONObject jsonObject = JSONObject.parseObject(scriptText.substring(0, scriptText.length() - 1));
                    JSONObject groups = jsonObject.getJSONObject("groups");
                    JSONArray listArray = groups.getJSONArray("list");

                    for (int i=0; i<listArray.size(); i++){
                        JSONObject listObj = listArray.getJSONObject(i);


                        JSONArray teachersArray = listObj.getJSONArray("teachers");
                        String nickname = null;
                        for (int j = 0; j < teachersArray.size(); j++) {
                            JSONObject teacherObj = teachersArray.getJSONObject(j);
                            nickname = teacherObj.getString("nickname");
                        }


                        JSONObject subjectObj = listObj.getJSONObject("subject");
                        String subject = subjectObj.getString("name");

                        JSONArray gradesArray = listObj.getJSONArray("grades");
                        String grade = null;
                        for (int j = 0; j < gradesArray.size(); j++) {
                            JSONObject gradesObj = gradesArray.getJSONObject(j);
                            grade = gradesObj.getString("name");
                        }

                        String id = listObj.getString("id");
                        String name = listObj.getString("name");
                        System.out.println(id + "\t" + name + "\t" + grade + "\t" +subject + "\t" + nickname);
                    }
                }
            }
        }



    }
}
