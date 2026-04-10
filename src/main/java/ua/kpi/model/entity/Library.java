package ua.kpi.model.entity;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    private ArrayList<Reader> readers;

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }

    public void setReaders(ArrayList<Reader> readers) {
        this.readers = readers;
    }
}
