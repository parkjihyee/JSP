package co.micol.prj.emp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import co.micol.prj.comm.DAO;

public class EmpDAO extends DAO {
	
	//JOBs 전체 조회
	public ArrayList<JobsVO> selectJobs() {
		ArrayList<JobsVO> list = new ArrayList<JobsVO>();
		try {
			getConnect();
			String sql = "select * from jobs order by job_id";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			while(rs.next()) {
				JobsVO vo = new JobsVO();
				vo.setJobId(rs.getString("job_id"));
				vo.setJobTitle(rs.getString("job_title"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	// 전체 조회
	public ArrayList<EmpVO> selectAll(String departmentId){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			//1.연결
			getConnect();
			
			//2.sql구문 준비
			String sql = "select * from employees";
			if(departmentId != null && ! departmentId.isEmpty()) {
				sql += " where department_id = ?";
			}
			sql += " order by employee_id ";
			
			psmt = conn.prepareStatement(sql);
			if(departmentId != null && ! departmentId.isEmpty()) {
				psmt.setString(1, departmentId);
				
				
			}
			//실행
			ResultSet rs = psmt.executeQuery();
			//조회결과를 list에 담기
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmployeeId(rs.getString("employee_id"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setJobId(rs.getString("job_id"));
				vo.setHireDate(rs.getString("hire_date"));
				list.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4.연결해제
		}
		
		return list;
	}
	
	// 단건 조회
	public EmpVO selectOne(String id) {
		EmpVO vo = new EmpVO();
		return vo;
	}
	
	// 등록 (n건이 처리되었습니다)
	public int insert(EmpVO vo) {
		int cnt=0;
		try {
			getConnect();
			String sql = "insert into"
						+ " employees ( employee_id, last_name, email, job_id, hire_date)"
						+ "values(?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getEmployeeId());
			psmt.setString(2, vo.getLastName());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getJobId());
			psmt.setString(5, vo.getHireDate());
			cnt = psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}
	
	
	// 수정
	
	// 삭제

}
