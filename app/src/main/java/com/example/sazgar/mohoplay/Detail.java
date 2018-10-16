package com.example.sazgar.mohoplay;

/**
 * Created by Sazgar on 8/27/2018.
 */

public class Detail {
    private String mtitle;
   // private String mid;
    private String mcolor;
    private String mSiteUrl;

    public Detail(String mtitle,String mcolor,String mSiteUrl) {
        this.mtitle = mtitle;
        this.mSiteUrl=mSiteUrl;
      //  this.mid = mid;
        this.mcolor = mcolor;
    }

    public String getmSiteUrl() {
        return mSiteUrl;
    }

    public String getMtitle() {
        return mtitle;
    }

//    public String getMid() {
//        return mid;
//    }

    public String getMcolor() {
        return mcolor;
    }
}
