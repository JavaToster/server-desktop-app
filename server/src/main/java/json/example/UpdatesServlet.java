package json.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.example.Database.updates.setUpdates;
import json.example.Database.info.getInfo;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdatesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation_type = req.getParameter("type");

        if(operation_type == null){
            return;
        }

        try{
            if(operation_type.equals("new-user")){
                String username = req.getParameter("username");
                String firstname = req.getParameter("firstname");
                String surname = req.getParameter("surname");
                String phone = req.getParameter("phone");
                String password = req.getParameter("password");
                if(username == null || firstname == null || surname == null || phone == null || password == null){
                    return;
                }
                setUpdates.setSingUp(username, firstname, surname, phone, password);
            } else if(operation_type.equals("delete-user")) {
                String username = req.getParameter("username");
                if(username == null){
                    String phone = req.getParameter("phone");
                    if(phone == null){
                        return;
                    }
                    setUpdates.deleteStringFromUsers(getInfo.getInfoAboutUserFromPhone(Long.parseLong(phone)).get("username"));
                }else {
                    setUpdates.deleteStringFromUsers(username);
                }
            } else if(operation_type.equals("update-score")){
                String username = req.getParameter("username");
                String type = req.getParameter("update-type");
                if(username == null || type == null){
                    return;
                }
                long sum = 0;
                try{
                    sum = Long.parseLong(req.getParameter("summa"));
                }catch (NumberFormatException e){}
                if(type.equals("plus")){
                    setUpdates.addHistoryPlus(username, sum);
                    setUpdates.updateScorePlus(username, sum);
                }else if(type.equals("minus")){
                    setUpdates.addHistoryMinus(username, sum);
                    setUpdates.updateScoreMinus(username, sum);
                }else if(type.equals("zero-score")){
                    setUpdates.toZeroScore(username);
                }
            } else if(operation_type.equals("update-user")){
                String username = req.getParameter("username");
                if(username == null){
                    return;
                }
                String rename = req.getParameter("new-name");
                if(rename == null){
                    String resurname = req.getParameter("new-surname");
                    if(resurname == null){
                        return;
                    }
                    setUpdates.toReSurname(resurname, username);
                    return;
                }
                setUpdates.toReName(rename, username);
            }else if(operation_type.equals("update-history")){
                String username = req.getParameter("username");
                String type = req.getParameter("history-type");
                if(username == null || type == null){
                    return;
                }
                if(type.equals("clear-history")){
                    setUpdates.clearHistory(username);
                }else if(type.equals("delete-last")){
                    setUpdates.deleteLastOperation(username);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pr = resp.getWriter();

        pr.println("Welcome!");
        pr.println("You can't set updates!");
    }
}
