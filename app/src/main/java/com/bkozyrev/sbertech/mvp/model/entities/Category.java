package com.bkozyrev.sbertech.mvp.model.entities;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "category", strict = false)
public class Category {

    @Text
    private String category;

    public Category() {
    }

    public String getCategory() {
        return category;
    }
}
