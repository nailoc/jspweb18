package com.hk.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hk.jsp.vo.GalleryVo;

public class GalleryDao {
	
	// 접속정보
	private static String driveName = "com.mysql.jdbc.Driver";
	private static 	String url = "jdbc:mysql://localhost:3306/jspweb";
	private static String user = "jsp";
	private static String password = "1234";
	// sql정보
	private static Connection conn = null;
	private static Statement stmt = null; // 복잡한 경우 PreparedStatement 전환 가능성
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	// 생성자
	public static GalleryDao getInstance() {
		if(instance==null) {
			instance = new GalleryDao();
		}
		return instance;
	}
	// 인스턴스
	private static GalleryDao instance = null;
	// 초기화 블럭
	{
		try{
			Class.forName(driveName);
			System.out.println("드라이버로딩성공");
		}catch(Exception e) {
			System.out.println("드라이버로딩실패");
		}
		
	}
		
	//mysql 접속메소드 (공통)
	private void connectDB() throws Exception {
		if(conn==null) { // 접속이 안되었으면
			conn=DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			System.out.println("데이터베이스접속성공");
		}
	}
	// 접속을 종료하는 메소드
	private void closeDB() {
		try {
			if(conn!=null) { conn.close(); conn=null; }
			if(stmt!=null) { stmt.close(); stmt=null; }
			if(pstmt!=null) { pstmt.close(); pstmt=null; }
			if(rs!=null) { rs.close(); rs=null; }
			System.out.println("데이터베이스접속종료완료");
		}catch(SQLException e) {
			System.out.println("데이터베이스 쿼리오류:"+e.getMessage());
		}catch(Exception e2) {
			System.out.println("데이터베이스 접속오류:"+e2.getMessage());
		}
	}
	
	// gallerylist.jsp 갤러리목록가져오기
	public int getGalleryTotal() throws Exception {
		int rst=0;
		connectDB();
		String sql = "select count(*) as total from gallery";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			rst = rs.getInt("total");
		}
		closeDB();
		return rst;
	}
	
	public List<GalleryVo> getGalleryList() throws Exception {
		List<GalleryVo>  rst= new ArrayList<GalleryVo>();
		connectDB();
		String sql = "select * from gallery";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			GalleryVo galvo = new GalleryVo();
			galvo.setNo(rs.getInt("no"));
			galvo.setFilename1(rs.getString("filename1"));
			galvo.setSubject(rs.getString("subject"));
			rst.add(galvo);
		}
		closeDB();
		return rst;
	}
	
	// galleryshow.jsp 이미지갤러리 가져오기
	public GalleryVo getGalleryByNo(String no) throws Exception {
		GalleryVo rst = new GalleryVo();
		connectDB();
		StringBuffer sb = new StringBuffer("");
		sb.append("select no, subject, content, filename1, filename2, ");
		sb.append("\n date_format(regdate,'%Y-%m-%d %H:%i:%s') as regdate ");
		sb.append("\n from gallery where no = ?");
		String sql = sb.toString();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(no));
		rs = pstmt.executeQuery();
		while(rs.next()){
			rst.setNo(rs.getInt("no"));
			rst.setSubject(rs.getString("subject"));
			rst.setContent(rs.getString("content"));
			rst.setFilename1(rs.getString("filename1"));
			rst.setFilename2(rs.getString("filename2"));
			rst.setRegdate(rs.getString("regdate"));
		}
		return rst;
	}

}
