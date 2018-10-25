package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDao;
import util.AuthUtil;

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		UserDao userDao = new UserDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e){
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=3");
			return;
		}
		
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("userInfo");
		User user = userDao.getItem(id);
		
		if("admin".equals(user.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=6");
			return;
		} else {
			if("admin".equals(loginUser.getUsername())) {
				if(userDao.delItem(id)>0) {
					response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=3");
					return;
				} else {
					response.sendRedirect(request.getContextPath()+"/admin/user/index?err=2");
					return;
				}
			} else {
				response.sendRedirect(request.getContextPath()+"/admin/user/index?err=6");
				return;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
