package com.example.foodsavior;

public class Food {

     private String calories;
     private String carbs;
     private String comments;
     private String cooktime;
     private String fat;
     private String fiber;
     private String id;
     private String ingredients;
     private String instructions;
     private String name;
     private String preptime;
     private String protein;
     private String satfat;
     private String servings;
     private String source;
     private String sugar;
     private String tags;
     private String waittime;

    public Food() {
    }

    public Food(String calories, String carbs, String comments, String cooktime,
                String fat, String fiber, String id, String ingredients, String instructions,
                String name, String preptime, String protein, String satfat, String servings,
                String source, String sugar, String tags, String waittime) {
        this.calories = calories;
        this.carbs = carbs;
        this.comments = comments;
        this.cooktime = cooktime;
        this.fat = fat;
        this.fiber = fiber;
        this.id = id;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.name = name;
        this.preptime = preptime;
        this.protein = protein;
        this.satfat = satfat;
        this.servings = servings;
        this.source = source;
        this.sugar = sugar;
        this.tags = tags;
        this.waittime = waittime;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCooktime() {
        return cooktime;
    }

    public void setCooktime(String cooktime) {
        this.cooktime = cooktime;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreptime() {
        return preptime;
    }

    public void setPreptime(String preptime) {
        this.preptime = preptime;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getSatfat() {
        return satfat;
    }

    public void setSatfat(String satfat) {
        this.satfat = satfat;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getWaittime() {
        return waittime;
    }

    public void setWaittime(String waittime) {
        this.waittime = waittime;
    }
}
