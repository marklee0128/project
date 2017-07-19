package com.pro.dao;

import java.sql.*;
import java.util.ArrayList;

import com.pro.vo.MemberVO;

public class MemberDAO {
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.3.151:1521:xe", "scott", "tiger");
		} catch (Exception ex) {
			System.out.println("오류 : " + ex);
		}
		return conn;
	}
		//서버 접속
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("오류 : " + ex);				
			}
		}
		close(conn, ps);
	} // close
		//서버, 퀴리, 결과 종료
	
	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("오류 : " + ex);				
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("오류 : " + ex);				
			}
		}
	} // close
		//서버, 쿼리 종료
	
	//1. 회원 추가 
	public void insertMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			if((member.getType()).equals("student")){
				pstmt = conn.prepareStatement("insert into student values(?,?,?,?,?)");
			}else{
				pstmt = conn.prepareStatement("insert into teacher values(?,?,?,?,?)");
			}
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getMail());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
			}
		}
	
	//2. id 중복 체크(부수적으로 비밀번호도 조건 달기)
	public boolean checkOverlap(String type, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean check = false;
		try {
			conn = connect();
			if(type.equals("student")){
				System.out.println("select sid from"+type+" where sid ="+id);
				pstmt = conn.prepareStatement("select sid from student where sid = ?");
			}else{
				System.out.println("select tid from"+type+" where tid ="+id);
				pstmt = conn.prepareStatement("select tid from teacher where tid = ?");
			}
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			// id가 존재하면 true로 바꾼다.
			if(rs.next()){
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn,pstmt);
		}
		return check;//true면 중복된 ID false면 회원가입 가능!
	}

	//3. 학생 조회
	public MemberVO studentSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO member = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from student where sid=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(3));
				member.setPhone(rs.getString(4));
				member.setMail(rs.getString(5));
			}

		} catch (Exception ex) {
			System.out.println("오류 : " + ex);				
		} finally {
			close(conn, pstmt, rs);
		}

		return member;
	}

	//4. 학생 회원수정
	public void studentUpdate(MemberVO student) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("update student set spw=?,sname=?,sphone=?,smail=? where id=?");
			pstmt.setString(1, student.getPw());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getPhone());
			pstmt.setString(4, student.getMail());
			pstmt.setString(5, student.getId());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("오류 : " + ex);				
		} finally {
			close(conn, pstmt);
		}

	}

	//5. 선생 회원수정
	public void teacherUpdate(MemberVO teacher) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("update teacher set spw=?,sname=?,sphone=?,smail=? where id=?");
			pstmt.setString(1, teacher.getPw());
			pstmt.setString(2, teacher.getName());
			pstmt.setString(3, teacher.getPhone());
			pstmt.setString(4, teacher.getMail());
			pstmt.setString(5, teacher.getId());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("오류 : " + ex);				
		} finally {
			close(conn, pstmt);
		}
		
	}
		//선생 회원수정


	//6. 회원 탈퇴
	public void memberDelete(String type, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from ? where id=?");			
			pstmt.setString(1, type);
			pstmt.setString(2, id);
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("오류 : " + ex);				
		} finally {
			close(conn, pstmt);
		}
	}
		//회원 탈퇴
	

	//7
	public ArrayList<MemberVO> studentList() {

		ArrayList<MemberVO> list = new ArrayList<MemberVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO student = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from student");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new MemberVO();
				student.setId(rs.getString(1));
				student.setName(rs.getString(3));
				student.setPhone(rs.getString(4));
				student.setMail(rs.getString(5));
				list.add(student);
			}

		} catch (Exception ex) {
			System.out.println("오류 : " + ex);				
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}
}