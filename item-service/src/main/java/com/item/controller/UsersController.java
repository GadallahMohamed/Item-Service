package com.item.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.item.model.Users;
import com.item.service.UsersService;
import com.item.service.impl.UsersServiceImpl;

@WebServlet("/UsersController")
public class UsersController extends HttpServlet {

    @Resource(name = "jdbc/connection")
    private DataSource dataSource;

    private UsersService usersService;

    @Override
    public void init() {
        usersService = new UsersServiceImpl(dataSource);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "add-user"    -> addUser(req, resp);
            case "update-user" -> updateUser(req, resp);
            case "remove-user" -> removeUser(req, resp);
            case "login"       -> login(req, resp);
            default            -> resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    /* ---------------- helpers --------------- */

    private void addUser(HttpServletRequest r, HttpServletResponse p) throws IOException {
        Users u = new Users(r.getParameter("username"), r.getParameter("email"), r.getParameter("password"));
        if (usersService.saveUser(u)) p.getWriter().println("User added.");
        else                           p.getWriter().println("Add failed.");
    }

    private void updateUser(HttpServletRequest r, HttpServletResponse p) throws IOException {
        Users u = new Users(
                Integer.parseInt(r.getParameter("id")),
                r.getParameter("username"),
                r.getParameter("email"),
                r.getParameter("password")
        );
        if (usersService.updateUser(u)) p.getWriter().println("User updated.");
        else                            p.getWriter().println("Update failed.");
    }

    private void removeUser(HttpServletRequest r, HttpServletResponse p) throws IOException {
        int id = Integer.parseInt(r.getParameter("id"));
        if (usersService.removeUser(id)) p.getWriter().println("User deleted.");
        else                             p.getWriter().println("Delete failed.");
    }

    private void login(HttpServletRequest r, HttpServletResponse p) throws IOException {
        Users u = usersService.authenticate(r.getParameter("email"), r.getParameter("password"));
        if (u != null) {
            HttpSession session = r.getSession();
            session.setAttribute("user", u);
            p.sendRedirect("itemController?action=load-items"); // Redirect after login
        } else {
            p.getWriter().println("Invalid credentials.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect("itemController?action=load-items"); // Already logged in
        } else {
            resp.sendRedirect("login.jsp"); // Change to your login page filename
        }
    }
}
