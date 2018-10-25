package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.Song;
import model.dao.SongDao;
import model.dao.catDao;
import util.AuthUtil;
import util.DefineUtil;
import util.FileUtil;
@MultipartConfig
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEditSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		catDao catD = new catDao();
		SongDao songDao = new SongDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?err=1");
			return;
		}
		Song song = songDao.getItems(id);
		request.setAttribute("song", song);
		
		ArrayList<Category> listCat = catD.getItems();
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int songid = Integer.parseInt(request.getParameter("id"));
		
		SongDao songDao = new SongDao();
		Song song = songDao.getItem(songid);
		
		String name = request.getParameter("name");
		int catId = Integer.parseInt(request.getParameter("cat_id"));
		String description = request.getParameter("preview");
		String detail = request.getParameter("detail");
		
		String dirPath = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD;
		
		File dirFile = new File(dirPath);
		
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		Part filePart = request.getPart("picture");
		String fileName = filePart.getSubmittedFileName();
				
		String filePath = dirPath + File.separator + fileName;
		
		Song objSong = new Song(songid, 0, name, fileName, description, detail, null, new Category(catId,""));

		
		if(songDao.editItem(objSong) > 0) {
			if(!fileName.isEmpty()) {
				String oldFilePath = dirPath + File.separator + song.getPicture();
				File oldFile = new File(oldFilePath);
				if(oldFile.exists()) {
					oldFile.delete();
				}
				filePart.write(filePath);
			}
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=2");
			return;
		}else {
			catDao catDao = new catDao();
			ArrayList<Category> listCat = catDao.getItems();
			request.setAttribute("listCat", listCat);
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/song/index?msg=0");
			rd.forward(request, response);
			return;
		} 
		
	}
}
