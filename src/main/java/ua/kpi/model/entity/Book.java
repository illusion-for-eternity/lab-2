package ua.kpi.model.entity;

import java.util.Objects;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String genre;
private Reader takenByreader;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", takenByreader='" + takenByreader + '\'' +
                '}';
    }

    public Reader getTakenByreader() {
        return takenByreader;
    }

    public void setTakenByreader(Reader takenByreader) {
        this.takenByreader = takenByreader;
    }

    public static class Builder {
        private final Book instance = new Book();

        public Builder setId(Integer id) {
            instance.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            instance.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            instance.author = author;
            return this;
        }

        public Builder setGenre(String genre) {
            instance.genre = genre;
            return this;
        }

        public Builder setTakenByreader(Reader reader) {
            instance.takenByreader = reader;
            return this;
        }

        public Book build() {
            return instance;
        }
    }
}

