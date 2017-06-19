package com.witts.mdbox.model;

import java.util.List;

/**
 * Created by wm02 on 6/13/2017.
 */

public class SouvenirImageandDetail {

    private String imageUrl;
    private List<SouvenirDetail> souvenirDetailList;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<SouvenirDetail> getSouvenirDetailList() {
        return souvenirDetailList;
    }

    public void setSouvenirDetailList(List<SouvenirDetail> souvenirDetailList) {
        this.souvenirDetailList = souvenirDetailList;
    }
}
