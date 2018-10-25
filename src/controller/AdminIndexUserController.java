package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.bean.User;
import model.dao.UserDao;
import util.AuthUtil;
import util.DefineUtil;

public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		UserDao userDao = new UserDao();
		
		int numberOfItems = userDao.getCountItems();
		int numberOfPage = (int) Math.ceil((float)numberOfItems/DefineUtil.NUMBER_PER_PAGE);
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(currentPage>numberOfPage||currentPage<0) {
			currentPage=1;
		}
		int offset = (currentPage-1)*DefineUtil.NUMBER_PER_PAGE;
		
		ArrayList<User> listUser = userDao.getItemsPagination(offset);
		
		request.setAttribute("listUser",listUser);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("userDao", userDao);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
