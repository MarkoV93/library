/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Marko
 */
public class CommandFactory {

    private static Map<String, Command> commandMap = new HashMap<>();
    private static CommandFactory instance = null;

    private CommandFactory() {
    }

    public static synchronized CommandFactory getInstance() {
        if (instance == null) {

            instance = new CommandFactory();

        }
        return instance;
    }

    static {
        commandMap.put("/login", new LoginCommand());
        commandMap.put("/registration", new RegisterCommand());
        commandMap.put("/finding", new FindingCommand());
        commandMap.put("/reserve", new ReserveCommand());
        commandMap.put("/wievReserves", new WievReservesCommand());
         commandMap.put("/changeReserve", new ChangeReserveCommand());
            commandMap.put("/addBook", new AddBookCommand());
            commandMap.put("/wievOldReserves", new WievOldReservesCommand());
        commandMap.put("/myReserves", new MyReserveCommand());
         commandMap.put("/returnBook", new ReturnBookCommand());
          commandMap.put("/myOldReserves", new MyOldReservesCommand());
           commandMap.put("/logOut", new LogOutCommand());
            commandMap.put("/changeLanguage", new ChangeLanguageCommand());
           

    }

    public static Command getCommand(String requestPath) {
     //   String value = request.getServletPath();
        return commandMap.get(requestPath);
    }
}
