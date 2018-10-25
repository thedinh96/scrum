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
import model.dao.ContactDao;
import model.dao.SongDao;
import model.dao.UserDao;
import model.dao.catDao;
import util.AuthUtil;

public class AdminSearchSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSearchSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		String search = (String) request.getParameter("search");
		
		SongDao songDao = new SongDao();
		ArrayList<Song> listSong = songDao.getItems(search);
		request.setAttribute("listSong", listSong);
			
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
