/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.model;

/**
 *
 * @author Marko
 */
public class Reserve {
    private int id;
    private String date;
    public  User user;
    public Book book;
    public Answer answer;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = new Answer();
        this.answer.setAnswer(answer);
    }
}
