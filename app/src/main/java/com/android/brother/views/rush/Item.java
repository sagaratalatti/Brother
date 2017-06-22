package com.android.brother.views.rush;

import com.android.brother.entities.RushEvent;

import java.util.List;

/**
 * Created by sagar on 21-06-2017.
 */

public class Item {

    public int type;
    public String header;
    public RushEvent rushEvent;
    public List<Item> invisibleChildren;

    public Item(int type, String header) {
        this.type = type;
        this.header = header;
    }

    public Item(int type, RushEvent rushEvent) {
        this.type = type;
        this.rushEvent = rushEvent;
    }
}
