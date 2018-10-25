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

public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthLoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		
		User userInfo =userDao.getItemByUsernameAndPassword(username, password);
		if(userInfo!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/auth/login?msg=Error");
			return;
		}
	}

}
