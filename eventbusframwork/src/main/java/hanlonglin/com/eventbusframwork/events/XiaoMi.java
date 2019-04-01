/*
 * Copyright (c) 2019/4/1
 * Create at 1:48:21
 * Wriitten by hanlonglin
 */

package hanlonglin.com.eventbusframwork.events;

public class XiaoMi {
    String name;
    int price;

    public XiaoMi(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
