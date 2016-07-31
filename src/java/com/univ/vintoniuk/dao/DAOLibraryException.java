/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.dao;

import java.sql.SQLException;

/**
 *
 * @author Marko
 */
public class DAOLibraryException extends SQLException {

    String message;

    public  DAOLibraryException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}