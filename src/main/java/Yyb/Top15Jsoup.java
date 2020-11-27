package Yyb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class Top15Jsoup {

    public static HashMap spider40(String url){
        HashMap<String ,Integer> bigmap = new HashMap<>();
        HashMap<String ,Integer> smallmap = new HashMap<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (
                IOException e) {
            System.out.println("url连接异常");
        }
        //爬取分类界面上的名字和下载量
        try {
            Elements name = doc.getElementsByClass("name ofh");
            Elements download_count = doc.getElementsByClass("download");
            String downloadcount = null;
            for (int i = 0; i < name.size(); i++) {
                downloadcount= download_count.get(i).text().replaceAll("下载","");
                System.out.println(name.get(i).text()+"\t"+downloadcount);

            }

        }catch (Exception e){

        }
        return null;
    }

    public static void sorttwolist(HashMap<String,Integer> bigmap,HashMap<String,Integer> smallmap){

    }

    public static void main(String[] args) {
        System.out.println("购物");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=122");
        System.out.println("阅读");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=102");
        System.out.println("新闻");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=110");
        System.out.println("视频");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=103");
        System.out.println("旅游");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=108");
        System.out.println("工具");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=115");
        System.out.println("社交");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=106");
        System.out.println("音乐");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=101");
        System.out.println("美化");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=119");
        System.out.println("摄影");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=104");
        System.out.println("理财");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=114");
        System.out.println("系统");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=117");
        System.out.println("生活");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=107");
        System.out.println("导航");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=112");
        System.out.println("安全");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=118");
        System.out.println("教育");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=111");
        System.out.println("健康");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=109");
        System.out.println("娱乐");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=105");
        System.out.println("儿童");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=100");
        System.out.println("办公");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=113");
        System.out.println("通讯");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=1&categoryId=116");
        System.out.println("休闲益智");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=2&categoryId=147");
        System.out.println("网络游戏");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=2&categoryId=121");
        System.out.println("飞行射击");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=2&categoryId=149");
        System.out.println("动作冒险");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=2&categoryId=144");
        System.out.println("体育竞速");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=2&categoryId=151");
        System.out.println("棋牌中心");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=2&categoryId=148");
        System.out.println("经营策略");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=2&categoryId=153");
        System.out.println("角色扮演");
        spider40("https://sj.qq.com/myapp/category.htm?orgame=2&categoryId=146");

    }
}
