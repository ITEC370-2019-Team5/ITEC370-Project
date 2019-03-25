package com.mygdx.game;

public class Item {
    private String itemName;
    private int amount;

    public Item() {
        super();
    }

    //getters and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
