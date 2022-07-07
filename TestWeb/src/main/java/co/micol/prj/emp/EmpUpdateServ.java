package co.micol.prj.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.dept.DeptDAO;
import co.micol.prj.dept.DeptVO;

@WebServlet("/empUpdate")
public class EmpUpdateServ extends HttpServlet{

	//수정 페이지로 이동
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB조회
		//jobs, 부서, 사원리스트
		EmpDAO empDAO = new EmpDAO();
		request.setAttribute("jobs", empDAO.selectJobs());

		DeptDAO deptDAO = new DeptDAO();
		request.setAttribute("depts", deptDAO.selectAll()); 
		
		//수정할 데이터를 가져가야하기 때문에 단건조회 필요
		String employeeId = request.getParameter("employeeId"); // 파라미터를 받아서
		request.setAttribute("emp", empDAO.selectOne(employeeId)); //dao에 담아서?
		request.getRequestDispatcher("WEB-INF/jsp/emp/empUpdate.jsp") // 업데이트페이지로 넘어간다
			   .forward(request, response);
		
	}

	//수정처리 (do post())
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//파라미터를 VO에 담고
		String id = request.getParameter("employeeId");
		String name = request.getParameter("lastName");
		String email = request.getParameter("email");
		String jobId = request.getParameter("jobId");
		String hireDate = request.getParameter("hireDate");
		
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(id);
		vo.setLastName(name);
		vo.setEmail(email);
		vo.setJobId(jobId);
		vo.setHireDate(hireDate);
		
		//DB 처리
		EmpDAO empDAO = new EmpDAO();
		int cnt = empDAO.update(vo);
		
		//결과 출력
		response.getWriter()
				.append(cnt + "건이 등록됨");
	}
}
