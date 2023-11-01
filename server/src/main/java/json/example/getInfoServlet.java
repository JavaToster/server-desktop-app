package json.example;

import json.example.Database.info.getInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class getInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        PrintWriter pr = resp.getWriter();
        if(username == null){
            long phone = Long.parseLong(req.getParameter("phone"));
            pr.println(getInfo.getInfoAboutUser(phone));
            return;
        }

        pr.println(getInfo.getInfoAboutUser(username));
    }
}
