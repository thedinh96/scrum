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
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		catDao catDao = new catDao();
		ArrayList<Category> listCat = catDao.getItems();
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SongDao songDao = new SongDao();
		String songName = request.getParameter("name");
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		String songPreview = request.getParameter("preview");
		String songDetail = request.getParameter("detail");
		
		Part filePart = request.getPart("picture");
		String filename = filePart.getSubmittedFileName();
		if(!"".equals(filename)) {
			//upload file
			String dirPath = request.getServletContext().getRealPath("")+DefineUtil.DIR_UPLOAD;
			File dirFile = new File(dirPath);
			if(!dirFile.exists()) {
				dirFile.mkdir();
			}
			filename = FileUtil.rename(filename);
			String filePath = dirPath+File.separator+filename;
			filePart.write(filePath);
		}
		Song objSong = new Song(0, 0, songName, filename, songPreview, songDetail, null, new Category(cat_id,""));
		if(songDao.addItem(objSong)>0) {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg=1");	
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/song/add?msg=0");
		}
	}
}
