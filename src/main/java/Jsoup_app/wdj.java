package Jsoup_app;

import Util.Dealtxt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static Util.Dealtxt.pkgnamedic;

public class wdj {
    static final String wdjurl = "https://www.wandoujia.com/apps/";

    public static APP wdjspider(String packagename) throws IOException {
        Dealtxt.ReadPkgName("src/PkgName.txt");
        Document doc = null;
        APP app = new APP();
        Elements infos = null;
        app.setPackagename(packagename);
        app.setSpellname(pkgnamedic.get(packagename));
        System.out.println(app.getPackagename());
        System.out.println(app.getSpellname());

        try {//用于捕获url异常
            doc = Jsoup.connect(wdjurl + packagename).get();
        } catch (Exception e) {
            System.out.println("url打开异常（豌豆荚）");
            app.setFlag(false);
            return app;
        }

        //jsoup获取页面信息
        try {//捕获下载量获取异常
            Elements download_count = doc.getElementsByAttributeValue("itemprop","interactionCount");//下载量
            String downloadcount = download_count.html();
            app.setDownloadcounter(downloadcount);
            System.out.println(app.getDownloadcounter());
        } catch (Exception e) {
            System.out.println("下载量异常（豌豆荚）");
            app.setFlag(false);
            return app;
        }

        try {//捕获app名字获取异常
            Elements app_name = doc.getElementsByClass("title");//app名字
            String appname = app_name.html();
            app.setAppname(appname);
            System.out.println(app.getSpellname());
            System.out.println(app.getAppname());
        } catch (Exception e) {
            System.out.println("app名字异常（豌豆荚）");
            app.setFlag(false);
            return app;
        }

        try {//捕获版本获取异常
            infos = doc.select("dd");
            Element version_num = infos.get(3);
            System.out.println(version_num.text());
        } catch (Exception e) {
            System.out.println("版本信息获取异常（豌豆荚）");
            app.setFlag(false);
            return app;
        }

        try {//捕获更新时间获取异常
            Elements update_time = doc.getElementsByClass("update-time");
            String updatetime = update_time.attr("datetime");
            app.setUpdatedata(updatetime);
            System.out.println(app.getUpdatedata());
        } catch (Exception e) {
            System.out.println("更新时间获取异常（豌豆荚）");
            app.setFlag(false);
            return app;
        }
        try {//捕获开发公司获取异常
            Elements company_name = doc.getElementsByClass("dev-sites");
            String company = company_name.text();
            app.setCompanyname(company);
            System.out.println(app.getCompanyname());
        } catch (Exception e) {
            System.out.println("开发公司信息获取异常（豌豆荚）");
            app.setFlag(false);
            return app;
        }

        try {//捕获应用描述获取异常
            Elements category = doc.getElementsByAttributeValue("itemprop","SoftwareApplicationCategory");//应用描述
            String describe = category.text();
            app.setAppdescribe(describe);
            System.out.println(app.getAppdescribe());
        } catch (Exception e) {
            System.out.println("应用描述获取异常（豌豆荚）");
            app.setFlag(false);
            return app;
        }

        try {//捕获下载链接获取异常
            Elements download_url = doc.getElementsByClass("normal-dl-btn");//下载链接
            String downloadurl = download_url.attr("href");
            app.setDownloadurl(downloadurl);
            System.out.println(app.getDownloadurl());
        } catch (Exception e) {
            System.out.println("下载链接异常（豌豆荚）");
            app.setFlag(false);
            return app;
        }


        Date date1 = new Date();//获取当前时间（此条数据更新的时间）
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sqlupdatetime = simpleDateFormat1.format(date1);
        app.setSqlupdatetime(sqlupdatetime);
        System.out.println(app.getSqlupdatetime());
        app.setFlag(true);//说明此app数据可以正常入库
        System.out.println(app.getAppname());
        return app;
    }

    public static void main(String[] args) throws IOException {

        File file = new File("src/PkgName.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str = null;
        while ((str = bufferedReader.readLine())!=null){
            String pkgName = str.split("\t")[0];

            wdjspider(pkgName);
            System.out.println("========================");
        }
    }
}
