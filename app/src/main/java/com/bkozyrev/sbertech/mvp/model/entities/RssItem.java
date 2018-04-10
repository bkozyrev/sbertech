package com.bkozyrev.sbertech.mvp.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.ArrayList;

@Root(name = "item", strict = false)
public class RssItem {

    @Element(name = "title", required = false)
    private String title;

    @Element(name = "description", required = false)
    private String description;

    @Element(name = "link", required = false)
    private String link;

    @Element(name = "pubDate", required = false)
    private String pubDate;

    @Path("dc:creator")
    @Text
    private String creator;

    @ElementList(name = "category", required = false, inline = true)
    private ArrayList<Category> categories;

    public RssItem() {
    }

    public RssItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getCreator() {
        return creator;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
