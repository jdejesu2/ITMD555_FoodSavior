package com.example.foodsavior;

public class ItemObject {

    private String name;
    private String instructions;

    public ItemObject(String name, String instructions) {
        //place holder for data, initial
        this.name = name;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String title) {
        this.instructions = instructions;
    }
}
