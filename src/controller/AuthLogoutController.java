package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.User;
import model.dao.UserDao;
import model.dao.catDao;
import util.StringUtil;

public class AuthLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthLogoutController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
		}
		response.sendRedirect(request.getContextPath() + "/auth/login");
		return ;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
