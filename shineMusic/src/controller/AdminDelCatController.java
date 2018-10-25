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

public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		catDao catDao = new catDao();
		int catID =Integer.parseInt(request.getParameter("id")) ;
		if(catDao.delItem(catID)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg=3");	
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/cat/del?msg=0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
