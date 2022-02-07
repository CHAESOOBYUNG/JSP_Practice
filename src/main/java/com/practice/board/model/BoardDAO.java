package com.practice.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.practice.util.JdbcUtil;


public class BoardDAO {
	
	public static BoardDAO dao = new BoardDAO();
	
	private BoardDAO() {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance() {
		return dao;
	}
	
	//데이터베이스 연결주소
	String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	String uid = "jsp";
	String upw = "jsp";
	
	DataSource ds; //연결정보(풀정보) 저장
	
	//글등록 메서드
	public void regist(String writer, String title, String content) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into bbs(bno, writer, title, content) values (bbs_seq.nextval ,?, ?, ?)";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
	}
	
	//목록 조회 메서드
	public ArrayList<BoardVO> getList() {
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select *\r\n"
				+ "from (select rownum as rn,\r\n"
				+ "             a.*\r\n"
				+ "        from(select * \r\n"
				+ "             from bbs\r\n"
				+ "             order by bno desc) a\r\n"
				+ ")\r\n"
				+ "where rn > ? and rn <= ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				BoardVO vo = new BoardVO(bno, title, writer, regdate, content);
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return list;
	}
	
	//전체 게시글 수 
	public int getTotal() {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) as total from bbs";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);	
	} 
		return result;
	}
	
	//상세보기
	public BoardVO getContent(String bno) {
		BoardVO vo = new BoardVO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from bbs where bno = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				vo.setBno( rs.getInt("bno") );
				vo.setWriter( rs.getString("writer") );
				vo.setTitle( rs.getString("title"));
				vo.setContent( rs.getString("content") );
				vo.setRegdate( rs.getTimestamp("regdate") );
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	//글 수정 기능 
	public void update(String bno, String title, String content) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update bbs set title = ?, content = ?, where bno = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	//글 삭제
	public void delete(String bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from bbs where bno = ?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
	}
}
