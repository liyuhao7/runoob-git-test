package liquSpider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class liquSpider {

//    public static ArrayList<String> urlList = new ArrayList<String>();
    public static ArrayList<APP> appList = new ArrayList<APP>();
    //爬取每个app界面的内容
    public static void pageSpider(APP app,String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println(url+"->获取异常");
        }
        try{
            Elements div = doc.getElementsByClass("info_con");//整个框
            //获取app名字
            String h1 = div.select("h1").text();
            System.out.println(h1);
            app.setName(h1);
            //获取分类（大类）
            Element p1 = div.select("p").get(0);
            String describe2 = p1.select("em").get(0).text();
            System.out.println(describe2);
            app.setDescribe2(describe2);
            //获取更新时间
            String updatetime = p1.select("em").get(1).text();
            System.out.println(updatetime);
            app.setUpdatetime(updatetime);
            //获取下载次数
            String downloadcount = p1.select("em").get(2).text();
            System.out.println(downloadcount);
            app.setDownloadcount(downloadcount);
            //获取适用系统
            Element p2 = div.select("p").get(1);
            String system = p2.select("em").get(0).text();
            System.out.println(system);
            app.setSystem(system);
            String company = p2.select("em").get(1).text();
            System.out.println(company);
            app.setCompany(company);
        }catch (Exception e){
            System.out.println("网络或其他异常");
        }





    }
    public static void main(String[] args) {
        Document doc = null;
        //第一页
        try {
            doc = Jsoup.connect("https://www.liqucn.com/rj/").get();
        } catch (IOException e) {
            System.out.println("获取url异常");
        }
        Elements item = doc.getElementsByClass("tip_blist").select("li");
        for (Element e:item) {
            APP app = new APP();
            String describe1 = "";
            Elements child = e.select("a");
            String url = child.attr("href");
            System.out.println(url);
            pageSpider(app,url);
            //获取分类（小类）
            Elements a = e.select("p").select("a");
            for (Element aa:a) {
                describe1 = describe1+aa.text()+"、";
                app.setDescribe1(describe1);
            }
            System.out.println(describe1);
            appList.add(app);
        }

        //第二页及以后
        for (int i = 12871; i >0; i--) {
            try {
                doc = Jsoup.connect("https://www.liqucn.com/rj/?page="+i).get();
            } catch (IOException e) {
                System.out.println("https://www.liqucn.com/rj/?page="+i+"->获取异常");
            }
            Elements item1 = doc.getElementsByClass("tip_blist").select("li");
            for (Element e:item1) {
                APP app = new APP();
                String describe1 = "";
                Elements child = e.select("a");
                String url = child.attr("href");
                System.out.println(url);
                pageSpider(app,url);
                //获取分类（小类）
                Elements a = e.select("p").select("a");
                for (Element aa:a) {
                    describe1 = describe1+aa.text()+"、";
                    app.setDescribe1(describe1);
                }
                System.out.println(describe1);
                appList.add(app);
            }
        }
    File file = new File("src/liqu.txt");
        try {
            file.createNewFile();//直接覆盖掉旧文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write("名字"+"\t"+"描述"+"\t"+"详细描述"+"\t"+"更新时间"+"\t"+"下载量"+"\t"+"适用系统"+"\t"+"公司");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (APP app:appList) {
            try {
                bufferedWriter.write(app.getName()+"\t"+app.getDescribe2()+"\t"+app.getDescribe1()+"\t"+app.getUpdatetime()+"\t"+app.getDownloadcount()+"\t"+app.getSystem()+"\t"+app.getCompany());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
