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
import model.dao.catDao;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		catDao catDao = new catDao();
		String catName = request.getParameter("name");
		Category objCat = new Category(0,catName);
		if(catDao.addItem(objCat)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=1");	
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/cat/add?msg=0");
		}
	}

}
