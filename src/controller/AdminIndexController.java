package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContactDao;
import model.dao.SongDao;
import model.dao.UserDao;
import model.dao.catDao;
import util.AuthUtil;
import util.StringUtil;

public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		catDao objCat = new catDao();
		request.setAttribute("objCat", objCat);
		
		ContactDao objContact = new ContactDao();
		request.setAttribute("objContact", objContact);
		
		SongDao objSong = new SongDao();
		request.setAttribute("objSong", objSong);
		
		UserDao objUser = new UserDao();
		request.setAttribute("objUser", objUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/index/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
