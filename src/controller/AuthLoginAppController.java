package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.RestFB;

public class AuthLoginAppController extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	  public AuthLoginAppController() {
	    super();
	  }
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    String code = request.getParameter("code");
	    
	    if (code == null || code.isEmpty()) {
	      RequestDispatcher dis = request.getRequestDispatcher("/auth/login.jsp");
	      dis.forward(request, response);
	    } else {
	      String accessToken = RestFB.getToken(code);
	      User user = RestFB.getUserInfo(accessToken);
	      request.setAttribute("id", user.getId());
	      request.setAttribute("name", user.getUsername());
	      RequestDispatcher dis = request.getRequestDispatcher("/auth/index.jsp");
	      dis.forward(request, response);
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
