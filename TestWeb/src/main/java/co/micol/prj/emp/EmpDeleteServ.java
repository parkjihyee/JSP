package co.micol.prj.emp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empDelete")
public class EmpDeleteServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String employeeId = request.getParameter("employeeId");
		
		EmpDAO empDAO = new EmpDAO();
		int cnt = empDAO.delete(employeeId);

		response.getWriter()
				.append("<script>")
				.append("alert('"+cnt+"건이 삭제완료')")
				.append("location.href='empList';")
				.append("</script>")
			    .append(cnt + "삭제됨");
		//request.setAttribute("msg", cnt + "건이 삭제됨");
		//request.getRequestDispatcher("/WEB-INF/jsp/message.jsp")
		//       .forward(request, response);
	}
}