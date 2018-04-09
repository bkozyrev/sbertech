package com.bkozyrev.sbertech.mvp.model.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "channel", strict = false)
public class Channel {

    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    @ElementList(name = "item", required = true, inline = true)
    private ArrayList<RssItem> rssItems;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<RssItem> getRssItems() {
        return rssItems;
    }

    public void setRssItems(ArrayList<RssItem> rssItems) {
        this.rssItems = rssItems;
    }
}
