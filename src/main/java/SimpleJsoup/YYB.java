package SimpleJsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static Util.Dealtxt.pkgnamedic;

public class YYB {
    static final String yyburl = "https://sj.qq.com/myapp/detail.htm?apkName=";
    static ArrayList<String > pkglist = new ArrayList<>();
    static HashMap<String,String> pkgnamedic = new HashMap<>();

    //读取txt中包名
    public static void readpackagename(){
        File file = new File("src/190+87yidong.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                pkglist.add(line.split("\t")[0]);
//                System.out.println(line);
                pkgnamedic.put(line.split("\t")[0],line.split("\t")[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Jsoup
    public static void spider(String pkgname){
        Elements infos = null;
        Document doc = null;
        String appname = null;
        String update_time =null;
        try {
            doc = Jsoup.connect(yyburl+pkgname).get();
        } catch (IOException e) {
            System.out.println(pkgnamedic.get(pkgname)+"url连接异常");
            return;
        }

        try {//捕获app名字获取异常
            Elements app_name = doc.getElementsByClass("det-name-int");//app名字
            appname = app_name.html();
        } catch (Exception e) {
            System.out.println(pkgnamedic.get(pkgname)+"app名字获取异常");
            return;
        }
        try {//捕获各种应用信息获取异常
            infos = doc.getElementsByClass("det-othinfo-data");//包括版本号，更新时间，公司
        } catch (Exception e) {
            System.out.println(pkgnamedic.get(pkgname)+"各种应用信息获取异常");
            return;
        }
        try {//捕获更新时间获取异常
            Element updatetime = infos.get(1);
            String update_Unix = updatetime.attr("data-apkpublishtime");//Unix时间戳
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//更新时间
            Date date = new Date(Long.parseLong(update_Unix) * 1000);
            update_time = simpleDateFormat.format(date);
            if (update_time.equals("1970-01-01 08:00")){
                return;
            }
        } catch (Exception e) {
            System.out.println(pkgnamedic.get(pkgname)+"更新时间获取异常");
            return ;
        }
        try {//捕获版本号获取异常
            Element version_num = infos.get(0);
            String version = version_num.html();
            Elements types = doc.getElementsByClass("det-type-link");
            Element type = types.first();
            String t = type.text();

        } catch (Exception e) {
            System.out.println(pkgnamedic.get(pkgname)+"版本号获取异常");
            return;
        }
        try {//捕获下载量获取异常
            Elements download_count = doc.getElementsByClass("det-ins-num");//下载量
            String downloadcount = download_count.text();

            System.out.println(pkgnamedic.get(pkgname)+"\t"+pkgname+"\t"+downloadcount+"\t"+update_time);
        } catch (Exception e) {
            System.out.println("下载量获取异常");
        }

    }

    public static void main(String[] args) {
        readpackagename();
        for (String pkgname:pkglist) {
            spider(pkgname);
        }

    }
}
