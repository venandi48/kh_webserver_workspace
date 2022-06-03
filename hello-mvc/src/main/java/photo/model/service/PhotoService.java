package photo.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import photo.model.dao.PhotoDao;
import photo.model.dto.Photo;

public class PhotoService {
	public static final int NUM_PER_PAGE = 5;
	private PhotoDao photoDao = new PhotoDao();

	public int getTotalContent() {
		Connection conn = getConnection();
		int totalContent = photoDao.getTotalContent(conn);
		close(conn);
		return totalContent;
	}

	public List<Photo> findMorePage(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Photo> list = photoDao.findMorePage(conn, param);
		close(conn);
		return list;
	}
}
