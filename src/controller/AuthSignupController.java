package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDao;
import util.StringUtil;

public class AuthSignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AuthSignupController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/signup.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String accountName = request.getParameter("accountName");
	String password = StringUtil.md5(request.getParameter("password"));
	String fullName = request.getParameter("fullName");
	
	User objUser = new User(0, accountName, password, fullName);
	
	UserDao userDao = new UserDao();
	
	if(userDao.checkExit(accountName)==null) {
		if(userDao.addItem(objUser)>0) {
			response.sendRedirect(request.getContextPath()+"/auth/signup?msg=1");	
			return;
		} else {
			request.setAttribute("accountName", accountName);
			request.setAttribute("password", password);
			request.setAttribute("fullName", fullName);
			RequestDispatcher rd= request.getRequestDispatcher("/auth/signup?err=1");
			rd.forward(request, response);
			return;
		}
	} else {
		response.sendRedirect(request.getContextPath()+"/auth/signup?err=2");
		/*RequestDispatcher rd= request.getRequestDispatcher("/auth/signup?err=2");
		rd.forward(request, response);*/
		return;

	}
	}

}
