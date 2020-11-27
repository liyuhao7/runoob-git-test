package Wdj;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class wdjDescSpider {

    static ArrayList<String> descList = new ArrayList<>();
    static ArrayList<String> linkList = new ArrayList<>();
    static HashMap<String,String> linkdescMap = new HashMap<>();
    public static void main(String[] args){
        File file = new File("src/liantongdesc.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        while(true){
            try {
                if (!((line = bufferedReader.readLine())!=null)) break;
                descList.add(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Document doc = null;
        System.out.println("列表中的应用名"+"\t"+"豌豆荚中的名字"+"\t"+"更新日期"+"\t"+"包名"+"\t"+"下载量");
        for (String desc:descList
        ) {
            try {
                doc = Jsoup.connect("https://www.wandoujia.com/search?key="+desc+"&source=search").get();

//                System.out.println(desc);
            } catch (IOException e) {
                System.out.println("https://www.wandoujia.com/search?key="+desc+"&source=search"+"->获取异常");
            }
            try{
                Element names = doc.getElementsByClass("name").first();
                String link = names.attr("href");
//                linkList.add(link);
//                linkdescMap.put(link,desc);


                Document doc1 = null;
                try {
                    doc1 = Jsoup.connect(link).get();

                }catch (Exception e ){
                    System.out.println("link"+link+"获取异常");
                }
                String name = null;
                String update = null;
                String apk = null;
                String downloadcount = null;
                try {
                    Elements namess = doc1.getElementsByClass("title");
                    Elements updatetime = doc1.getElementsByClass("update-time");

                    name = namess.text();
                    update = updatetime.attr("datetime");

                }catch (Exception e ){
                    System.out.println(link+"内的信息获取异常");
                }
                try{
                    Element cominfo = doc1.getElementsByClass("download-wp").select("a").get(1);

                    apk = cominfo.attr("data-app-pname");


                }catch (Exception e){
                    System.out.println("包名获取异常");
                }
                try{
                    Elements itemistall = doc1.getElementsByClass("item install");
                    Element download = itemistall.select("i").first();
                    downloadcount = download.text();
                }catch (Exception e){

                }
                System.out.println(desc+"\t"+name+"\t"+update+"\t"+apk+"\t"+downloadcount);
                Thread.sleep(200);




            }catch (Exception e){
                System.out.println(desc+"查询界面获取异常");
            }

        }



    }
}

