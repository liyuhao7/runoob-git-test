package Jsoup_app;


import Util.Dealtxt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Util.Dealtxt.pkgnamedic;

public class yyb {

    static final String yyburl = "https://sj.qq.com/myapp/detail.htm?apkName=";

    public static APP yybspider(String packagename) throws IOException {

        Document doc = null;
        APP app = new APP();
        Elements infos = null;

        try {//用于捕获url异常
            doc = Jsoup.connect(yyburl + packagename).get();
            //包名和name
            app.setPackagename(packagename);
            System.out.println("包名（应用宝）："+app.getPackagename());
            app.setSpellname(pkgnamedic.get(packagename));

        } catch (Exception e) {
            System.out.println("url打开异常（应用宝）");
            app.setFlag(false);
            return app;
        }
        //jsoup获取页面信息
        try {//捕获下载量获取异常
            Elements download_count = doc.getElementsByClass("det-ins-num");//下载量
            String downloadcount = download_count.text();
            app.setDownloadcounter(downloadcount);
            System.out.println("下载量（应用宝）："+app.getDownloadcounter());
        } catch (Exception e) {
            System.out.println("下载量获取异常（应用宝）");
            app.setFlag(false);
            return app;
        }
        try {//捕获app名字获取异常
            Elements app_name = doc.getElementsByClass("det-name-int");//app名字
            String appname = app_name.text();
            app.setAppname(appname);
            System.out.println("app name（应用宝）："+app.getSpellname());
            System.out.println("app名字（应用宝）："+app.getAppname());
        } catch (Exception e) {
            System.out.println("app名字获取异常（应用宝）");
            app.setFlag(false);
            return app;
        }
        try {//捕获各种应用信息获取异常
            infos = doc.getElementsByClass("det-othinfo-data");//包括版本号，更新时间，公司
        } catch (Exception e) {
            System.out.println("各种应用信息获取异常（应用宝）");
            app.setFlag(false);
            return app;
        }

        try {//捕获版本号获取异常
            Element version_num = infos.get(0);
            String version = version_num.text();
            app.setVersionnumber(version);
            app.setFilename(pkgnamedic.get(packagename) + "^" + version);
            System.out.println("版本号（应用宝）："+app.getVersionnumber());
            System.out.println("文件名（应用宝）："+app.getFilename());
        } catch (Exception e) {
            System.out.println("版本号获取异常（应用宝）");
            app.setFlag(false);
            return app;
        }
        try {//捕获更新时间获取异常
            Element updatetime = infos.get(1);
            String update_Unix = updatetime.attr("data-apkpublishtime");//Unix时间戳
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//更新时间
            Date date = new Date(Long.parseLong(update_Unix) * 1000);
            String update_time = simpleDateFormat.format(date);
            app.setUpdatedata(update_time);
            System.out.println("app更新时间（应用宝）："+app.getUpdatedata());
        } catch (Exception e) {
            System.out.println("app更新时间获取异常（应用宝）");
            app.setFlag(false);
            return app;
        }
        try {//捕获开发公司获取异常
            Element company = infos.get(2);
            String companyname = company.text();
            app.setCompanyname(companyname);
            System.out.println("开发公司（应用宝）："+app.getCompanyname());
        } catch (Exception e) {
            System.out.println("开发公司获取异常（应用宝）");
            app.setFlag(false);
            return app;
        }

        try {//捕获下载链接获取异常
            Elements download_url = doc.getElementsByClass("det-down-btn");//下载链接
            String downloadurl = download_url.attr("data-apkurl");
            app.setDownloadurl(downloadurl);
            System.out.println("下载链接（应用宝）："+app.getDownloadurl());
        } catch (Exception e) {
            System.out.println("下载链接获取异常（应用宝）");
            app.setFlag(false);
            return app;
        }
        try {//捕获应用描述获取异常
            Elements category = doc.getElementsByClass("det-type-link");//应用描述
            String describe = category.text();
            app.setAppdescribe(describe);
            System.out.println("应用描述（应用宝）："+app.getAppdescribe());
        } catch (Exception e) {
            System.out.println("应用描述获取异常（应用宝）");
            app.setFlag(false);
            return app;
        }


        //待处理
        Date date1 = new Date();//获取当前时间（此条数据更新的时间）
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sqlupdatetime = simpleDateFormat1.format(date1);
        app.setSqlupdatetime(sqlupdatetime);
        System.out.println(app.getSqlupdatetime());
        app.setFlag(true);//说明此app数据可以正常入库
        return app;

    }
}
