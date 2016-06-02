package com.idea.l.rxjavaandmvp.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by l on 2016/6/1.
 */
public class UserBean extends BaseBean{

    /**
     * r : 1
     * msg : 成功！
     * total : 0
     * data : []
     * item : {"id":3,"version":"100","isupdate":0,"url":"http://weidaiosstest.oss-cn-hangzhou.aliyuncs.com/weidaiwang-release.apk","macadd":"android","info":"","forceUpdateVersion":"","createTime":"2016-04-08 14:34:29"}
     */


    /**
     * id : 3
     * version : 100
     * isupdate : 0
     * url : http://weidaiosstest.oss-cn-hangzhou.aliyuncs.com/weidaiwang-release.apk
     * macadd : android
     * info :
     * forceUpdateVersion :
     * createTime : 2016-04-08 14:34:29
     */

    private ItemBean item;
    private List<?> data;

    public static UserBean objectFromData(String str) {

        return new Gson().fromJson(str, UserBean.class);
    }



    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public static class ItemBean {
        private int id;
        private String version;
        private int isupdate;
        private String url;
        private String macadd;
        private String info;
        private String forceUpdateVersion;
        private String createTime;

        public static ItemBean objectFromData(String str) {

            return new Gson().fromJson(str, ItemBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getIsupdate() {
            return isupdate;
        }

        public void setIsupdate(int isupdate) {
            this.isupdate = isupdate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMacadd() {
            return macadd;
        }

        public void setMacadd(String macadd) {
            this.macadd = macadd;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getForceUpdateVersion() {
            return forceUpdateVersion;
        }

        public void setForceUpdateVersion(String forceUpdateVersion) {
            this.forceUpdateVersion = forceUpdateVersion;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
