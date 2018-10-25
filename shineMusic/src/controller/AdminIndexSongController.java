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

public class AdminIndexSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SongDao songDao = new SongDao();
		ArrayList<Song> listSong = songDao.getItems();
		request.setAttribute("listSong", listSong);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
