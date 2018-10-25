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
import model.bean.Song;
import model.dao.catDao;
import util.AuthUtil;
import util.DefineUtil;

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		catDao catD = new catDao();
		request.setAttribute("objCat", catD);
		int numberOfItems = catD.getCountItems();
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
		
		ArrayList<Category> listCat = catD.getItemsPagination(offset);
		request.setAttribute("listCat", listCat);

		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
