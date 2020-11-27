package Util;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dealtxt {
    public static HashMap<String, String> pkgnamedic = new HashMap<>();
    public static List<String> pkglist = new ArrayList<>();
    public static HashMap<String ,String> AMap = new HashMap<>();
    public static HashMap<String ,String> BMap = new HashMap<>();
    public static HashMap<String ,String> CMap = new HashMap<>();
    public static void ReadPkgName(String path) {
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                pkglist.add(line.split("\t")[0]);
                pkgnamedic.put(line.split("\t")[0], line.split("\t")[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readTopApp(String path) {
        File file = new File(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String desc = line.split("\t")[0].trim();
                String name = line.split("\t")[1].trim();
                if (i<50){
                    AMap.put(name,desc);
                }else if (i>49&i<500){
                    BMap.put(name,desc);
                }else {
                    CMap.put(name,desc);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
