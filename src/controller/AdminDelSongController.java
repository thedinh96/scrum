package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDao;
import util.AuthUtil;
import util.DefineUtil;

public class AdminDelSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if(!AuthUtil.checkLogin(request, response)) {
		response.sendRedirect(request.getContextPath()+"/auth/login");
		return;
	}
	
	SongDao songDao = new SongDao();
	int songId=Integer.valueOf(request.getParameter("id"));
	Song objSong = songDao.getItem(songId);
	String fileNameOld = objSong.getPicture();
	if(!"".equals(fileNameOld)) {
		String dirPath = request.getServletContext().getRealPath("")+DefineUtil.DIR_UPLOAD;
		String filePath = dirPath+File.separator+fileNameOld;
		File file = new File(filePath);
		file.delete();
		System.out.println(dirPath);
	}	
	if(songDao.delItem(objSong.getSongId())>0) {
		response.sendRedirect(request.getContextPath()+"/admin/song/index?msg=3");	
	}else {
		response.sendRedirect(request.getContextPath()+"/admin/song/index?msg=0");
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
