package co.micol.prj.dept;

import java.util.ArrayList;

import co.micol.prj.comm.DAO;
import co.micol.prj.emp.EmpVO;

public class DeptDAO extends DAO {
	
	//전체 조회
	public ArrayList<DeptVO> selectAll(){
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();
		try {
			getConnect();
			String sql = "select * from departments order by Department_id";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) { // 다음 레코드로 이동
				DeptVO vo = new DeptVO();
				vo.setDepartmentName(rs.getString("Department_Name"));
				vo.setDepartmentId(rs.getString("Department_id"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	//단건 조회
	public DeptVO selectOne(String department_id){ // 조회할 부서 번호를( )에 받음
		DeptVO vo = new DeptVO(); // 결과를 담을 vo 객체 생성
		try {
			getConnect();
			String sql = "select * from departments where department_id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, department_id);
			rs = psmt.executeQuery();
			
			if(rs.next()) { // 다음 레코드로 이동
				vo.setDepartmentName(rs.getString("Department_Name"));
				vo.setDepartmentId(rs.getString("Department_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return vo;
	}
	
	//등록
	public int deptInsert(DeptVO vo) {
		int cnt=0;
		try {
			getConnect();
			String sql = "insert into" 
						+ " departments(department_id, department_name)"
						+ "values(?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getDepartmentId());
			psmt.setString(2, vo.getDepartmentName());
			cnt = psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}
	
	
	//수정
	public int update(DeptVO vo) {
		int r=0;
		
		return r;
	}
	
	
	//삭제
	

}
