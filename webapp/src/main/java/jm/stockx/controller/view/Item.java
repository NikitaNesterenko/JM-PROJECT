package jm.stockx.controller.view;

import java.time.LocalDate;

public class Item {
    private String brand;
    private String category;
    private String name;
    private LocalDate realizeDate;
    private int priceMax;
    private int priceMin;
    private String condition;
    private int lastSale;
    private String style;
    private String color;
    private String description;
    private String urlImage;

    public Item() {

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRealizeDate() {
        return realizeDate;
    }

    public void setRealizeDate(LocalDate realizeDate) {
        this.realizeDate = realizeDate;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(int priceMax) {
        this.priceMax = priceMax;
    }

    public int getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(int priceMin) {
        this.priceMin = priceMin;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getLastSale() {
        return lastSale;
    }

    public void setLastSale(int lastSale) {
        this.lastSale = lastSale;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
