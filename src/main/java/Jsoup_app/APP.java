package Jsoup_app;

public class APP {

    private String packagename;
    private String downloadcounter;
    private String spellname;
    private String appname;
    private String versionnumber;
    private String filename;
    private String updatedata;
    private String companyname;
    private String downloadurl;
    private String appdescribe;
    private String sqlupdatetime;
    private Boolean flag = false;//用于标识可否正确爬取此app

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    //    private static app instance = new app();
//    private app(){}
//    public static app getInstance(){
//        return instance;
//    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getDownloadcounter() {
        return downloadcounter;
    }

    public void setDownloadcounter(String downloadcounter) {
        this.downloadcounter = downloadcounter;
    }

    public String getSpellname() {
        return spellname;
    }

    public void setSpellname(String spellname) {
        this.spellname = spellname;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getVersionnumber() {
        return versionnumber;
    }

    public void setVersionnumber(String versionnumber) {
        this.versionnumber = versionnumber;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUpdatedata() {
        return updatedata;
    }

    public void setUpdatedata(String updatedata) {
        this.updatedata = updatedata;
    }


    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getAppdescribe() {
        return appdescribe;
    }

    public void setAppdescribe(String appdescribe) {
        this.appdescribe = appdescribe;
    }

    public String getSqlupdatetime() {
        return sqlupdatetime;
    }

    public void setSqlupdatetime(String sqlupdatetime) {
        this.sqlupdatetime = sqlupdatetime;
    }

}
