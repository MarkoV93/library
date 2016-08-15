
package com.univ.vintoniuk.model;


public class Book {
    private int id;
   private  String title;
   private  String author;
  private   int qty;
   private Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = new Genre();
        this.genre.setGenre(genre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


}
