package com.mygdx.game;

public class Item {
    private String itemName;
    private int amount;

    public Item() {

        super();
    }

    public Item(String itemName, int amount) {
        this.itemName = itemName;
        this.amount = amount;
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
