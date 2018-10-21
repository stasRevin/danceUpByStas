package com.danceUpByStas.controller;

import com.danceUpByStas.entity.Dance;
import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateUserDances",
            urlPatterns = {"/updateUserDances"})
public class UpdateUserDances extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<UserDance> userDances = (List<UserDance>) session.getAttribute("userDances");
        GenericDao<Dance> danceDao = new GenericDao<>(Dance.class);

        List<Dance> danceList = danceDao.getAll();

        filterDanceList(danceList, userDances);

        request.setAttribute("dances", danceList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateUserDances.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }


    private void filterDanceList(List<Dance> danceList, List<UserDance> userDances ) {

        for (UserDance userDance : userDances) {

            danceList.remove(userDance.getDance());

        }

    }
}
