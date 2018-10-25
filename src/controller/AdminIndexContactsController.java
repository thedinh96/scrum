package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Contact;
import model.dao.ContactDao;
import util.AuthUtil;
import util.DefineUtil;

public class AdminIndexContactsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminIndexContactsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		ContactDao contactDao = new ContactDao();
		request.setAttribute("objContact", contactDao);
		
		int numberOfItems = contactDao.getCountItems();
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
		
		ArrayList<Contact> list = contactDao.getItemsPagination(offset);
		request.setAttribute("list", list);
		
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/contact/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
