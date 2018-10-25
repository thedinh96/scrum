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

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEditCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id => getItem (Category) => name set
		catDao CatDao = new catDao();
		int catId = Integer.valueOf(request.getParameter("id"));
		Category objCat = CatDao.getItem(catId);
		request.setAttribute("objCat", objCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		catDao catDao = new catDao();
		String catName = request.getParameter("name");
		int catId = Integer.valueOf(request.getParameter("id"));
		Category objCat = new Category(catId,catName);
		if(catDao.editItem(objCat)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=2");	
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=0");
		}
	}

}
