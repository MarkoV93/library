/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import comand.ChangeLanguageCommand;
import comand.Command;
import comand.CommandFactory;
import java.io.IOException;


import java.util.logging.Level;

import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Hendler extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

  Command handler=CommandFactory.getInstance().getCommand(request.getServletPath());
  
        String path= null; 
      
            path = handler.execute(request,response);
              HttpSession hs=request.getSession(true);
            if(!request.getServletPath().equals("/changeLanguage")){
            hs.setAttribute("req", request.getServletPath());
            }
        RequestDispatcher rd=request.getRequestDispatcher(path);
        rd.forward(request, response);

        }
    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
              ex.printStackTrace();
        }
    }



}
