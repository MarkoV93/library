/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.properties.classesForTags;

import com.univ.vintoniuk.dao.AbstractDao;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.ReserveDao;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.log4j.LogManager;

/**
 *
 * @author Marko Clacc for tag, print message abaut active reserves of user
 */
public class MessageAboutReserve extends SimpleTagSupport {

    String login;
    int activeReserves;
    ResourceBundle labels;
    private static final org.apache.log4j.Logger logger = LogManager.getLogger(MessageAboutReserve.class);

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void doTag() {
        Locale locale = Locale.getDefault();
        labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", locale);
        DaoFactory factory = DaoFactory.getInstance();
        ReserveDao reserves = factory.getReserveDao();
        try {
            activeReserves = reserves.getReservesByLogin(login).size();
        } catch (DAOLibraryException ex) {
            logger.warn("do not get list for Tag messageAboutReserve", ex);
        }
        JspWriter out = getJspContext().getOut();
        try {
            out.println(labels.getString("youHave") + " " + activeReserves + " " + labels.getString("activeReserves"));
        } catch (IOException ex) {
            logger.error("dount dispay by JspWriter", ex);
        }

    }
}
