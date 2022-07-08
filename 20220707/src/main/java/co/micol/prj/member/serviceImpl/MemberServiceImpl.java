package co.micol.prj.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.prj.comm.DataSource;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<MemberVO> memberSelectList() {
		// 전체멤버 목록
		List<MemberVO> list = new ArrayList<MemberVO>(); // 결과를 담을 객체
		MemberVO vo;
		String sql = "SELECT * FROM MEMBER";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); // select구문은 무조건 executeQuery
			while(rs.next()) { // 존재하면 true ??
				vo = new MemberVO(); // 위에서 선언된 vo를 초기화
				vo.setMemberId(rs.getString("member_id" )); // 변수명을 보고 칼럼명을..
	//			vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberAuthor(rs.getString("member_author"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}


	@Override
	public MemberVO memberSelectOne(MemberVO vo) {
		// 한 명 조회
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		try { // 매개변수로 vo가 넘어오니까 vo안만들어줘도 됨??
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if(rs.next()) { 
				vo.setMemberId(rs.getString("member_id" )); // 변수명을 보고 칼럼명을..
	//			vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// 회원정보 등록
		int cnt = 0;
		String sql = "insert into member values (?,?,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPassword());
			psmt.setString(3, vo.getMemberName());
			psmt.setString(4, vo.getMemberAuthor());
			
			cnt = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// 회원정보 변경 (권한, 패스워드)
		int n = 0;
		String sql = "update member set member_password = ?,"
				+ "member_author = ?, where member_id = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// 회원정보 삭제
		int n = 0;
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public boolean isMemberIdCheck(String id) {
		// 회원 아이디 중복체크
		boolean b = false;
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(!rs.next()) { // 중복되는 아이디가 존재하지 않으면 true, 존재하면 false를 돌려줌
				b = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return b;
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		// 회원 로그인
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PASSWORD = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPassword());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}

	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			
		}
	}
}
