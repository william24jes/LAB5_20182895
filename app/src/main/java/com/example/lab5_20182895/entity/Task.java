package com.example.lab5_20182895.entity;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String title;
    private String description;
    private Date dueDate;

    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    // Getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Título: " + title + ", Descripción: " + description + ", Fecha de vencimiento: " + dueDate.toString();
    }
}
