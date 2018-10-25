package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.bean.Contact;
import model.dao.ContactDao;
import util.AuthUtil;

public class AdminDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelContactController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		ContactDao contactDao = new ContactDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e){
			response.sendRedirect(request.getContextPath()+"/admin/contacts?err=1");
			return;
		}
		if(contactDao.delItem(id)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/contacts?msg=1");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/contacts?err=2");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
