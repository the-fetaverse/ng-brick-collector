package models;

public class LegoSet {
    // Class fields
    private int id;
    private String name;
    private int year;
    private String img;
    private int num_parts;

    // Constructors
    // Default
    public LegoSet() {
    }

    // With id
    public LegoSet(int id, String name, int year, String img, int num_parts) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.img = img;
        this.num_parts = num_parts;
    }

    // Without id
    public LegoSet(String name, int year, String img, int num_parts) {
        this.name = name;
        this.year = year;
        this.img = img;
        this.num_parts = num_parts;
    }


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getNum_parts() {
        return num_parts;
    }

    public void setNum_parts(int num_parts) {
        this.num_parts = num_parts;
    }

    //ToString
    @Override
    public String toString() {
        return "LegoSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", img='" + img + '\'' +
                ", num_parts=" + num_parts +
                '}';
    }
}
