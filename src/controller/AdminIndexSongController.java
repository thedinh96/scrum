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
import model.dao.SongDao;
import model.dao.catDao;
import util.AuthUtil;
import util.DefineUtil;

public class AdminIndexSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SongDao songDao = new SongDao();

		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		int numberOfItems = songDao.getCountItems();
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
		
		ArrayList<Song> listSong = songDao.getItemsPagination(offset);
		
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("listSong", listSong);
		SongDao objSong = new SongDao();
		request.setAttribute("objSong", objSong);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
