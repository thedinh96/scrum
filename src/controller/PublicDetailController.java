package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.Song;
import model.dao.SongDao;


public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PublicDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int sid = 0;
		try {
			sid = Integer.parseInt(request.getParameter("sid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/public/404");
			return;
		}
		
		SongDao songDao = new SongDao();
		Song objSong = songDao.getItemsById1(sid);
		if(objSong == null) {
			response.sendRedirect(request.getContextPath() + "/public/404");
			return;
		}
		
		HttpSession session = request.getSession();
		String hasVisited = (String)session.getAttribute("hasVisited: " + sid);
		if(hasVisited == null) {
			session.setAttribute("hasVisited: " + sid, "yes");
			session.setMaxInactiveInterval(3600);
			//tăng view
			songDao.increaseView(sid);
		}
		request.setAttribute("objSong", objSong);
		
		ArrayList<Song> relatedSongs = songDao.getRelatedItems(objSong, 2);
		request.setAttribute("relatedSongs", relatedSongs);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
		rd.forward(request, response);
	}
}
