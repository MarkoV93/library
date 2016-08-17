/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.dao.AbstractDao;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;

/**
 *
 * @author Marko class for geting relevant command
 */
public class CommandFactory {

    private static final org.apache.log4j.Logger logger = LogManager.getLogger(AbstractDao.class);
    private Map<String, Command> commandMap = null;
    private static CommandFactory instance = null;
    ResourceBundle labels;

    private CommandFactory() {
        commandMap = new HashMap<>();
    }

    //method which crate command by reflactions and put it into Map commandMap
    private Command createNewCommand(String requestPath) {
        try {
         //   System.out.println(requestPath);
            labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", Locale.getDefault());
            Class commandClass = Class.forName(labels.getString(requestPath));
            Command command = (Command) commandClass.newInstance();
            commandMap.put(requestPath, command);
        } catch (InstantiationException ex) {
            logger.error("something wrong with createNewCommand method", ex);
        } catch (IllegalAccessException ex) {
            logger.error("something wrong with createNewCommand method", ex);
        } catch (ClassNotFoundException ex) {
            logger.error("something wrong with createNewCommand method", ex);
        }
        return commandMap.get(requestPath);
    }

//lazy initialization for CommandFactory 
    public static synchronized CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

//lazy initialization for Commands 
    public Command getCommand(String requestPath) {
        if (commandMap.containsKey(requestPath)) {
            return commandMap.get(requestPath);//return the appropriate command from the map if it contains there
        } else {
            return createNewCommand(requestPath);//method which crate command by reflections and put it into Map commandMap
        }

    }
}
