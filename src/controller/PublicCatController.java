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
import model.dao.ContactDao;
import model.dao.SongDao;
import model.dao.catDao;

/**
 * Servlet implementation class PublicCatController
 */
@WebServlet("/PublicCatController")
public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicCatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int cid = 0;
		try {
			cid = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/public/404");
			return;
		}
		SongDao songDao = new SongDao();
		ArrayList<Song> listSongById = songDao.getItemsById(cid);
		request.setAttribute("listSongById", listSongById);
		
		catDao catDao = new catDao();
		Category objCat = catDao.getItem(cid);
		if(objCat == null) {
			response.sendRedirect(request.getContextPath() + "/public/404");
			return;
		}
		request.setAttribute("objCat", objCat);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/cat.jsp");
		rd.forward(request, response);
		
	}

}
