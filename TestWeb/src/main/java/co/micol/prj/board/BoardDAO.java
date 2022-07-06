package co.micol.prj.board;

import java.sql.ResultSet;
import java.util.ArrayList;

import co.micol.prj.comm.DAO;

public class BoardDAO extends DAO {

	public ArrayList<BoardVO> selectAll(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnect();
			String sql = "select * from board order by hit desc"; 
			psmt = conn.prepareStatement(sql);		
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO(); // vo에서 생성자 안 만들어도 됨!
				vo.setId(rs.getString("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRdt(rs.getString("rdt"));
				vo.setHit(rs.getString("hit"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
}
