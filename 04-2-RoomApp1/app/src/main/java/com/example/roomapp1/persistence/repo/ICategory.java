package com.example.roomapp1.persistence.repo;

public class ICategory {
    private long id;
    private String name;

    public ICategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ICategory(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ICategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
