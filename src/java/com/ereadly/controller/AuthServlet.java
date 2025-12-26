/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ereadly.controller;
import com.ereadly.dao.UserDAO;
import com.ereadly.model.Admin;
import com.ereadly.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.authenticate(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            if (user instanceof Admin) {
                response.sendRedirect(request.getContextPath() + "/view/admin/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/view/member/dashboard.jsp");
            }
        } else {
            request.setAttribute("error", "Email atau password salah");
            request.getRequestDispatcher("/view/auth/login.jsp").forward(request, response);
        }
    }
}
