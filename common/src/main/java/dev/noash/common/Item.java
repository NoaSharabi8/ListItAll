package dev.noash.common;

public class Item {
    private String name;
    private String category;
    private boolean isMarked;
    private String info;

    public Item(String name, String category, boolean isMarked, String info) {
        this.name = name;
        this.category = category;
        this.isMarked = isMarked;
        this.info = info;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isMarked() { return isMarked; }
    public void setMarked(boolean marked) { this.isMarked = marked; }
    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

}
