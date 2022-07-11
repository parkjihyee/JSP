package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.member.command.AjaxMemberIdCheck;
import co.micol.prj.member.command.MemberJoin;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberList;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
import co.micol.prj.member.command.MemberLogout;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<>(); // 자료구조

	public void init(ServletConfig config) throws ServletException {
		// 요청과 수행할 command 연결
		map.put("/main.do", new MainCommand()); // 처음 접하는 곳
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); // 로그인 처리
		map.put("/memberLogout.do", new MemberLogout()); // 로그아웃
		map.put("/memberList.do", new MemberList()); // 회원 목록 보기
		map.put("/memberJoinForm.do", new MemberJoinForm());
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck()); //ajax를 이용한 아이디 중복 체크
		map.put("/memberJoin.do", new MemberJoin()); // 회원가입처리
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 분석하고 실행하고 결과를 돌려주는 곳
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		if(!viewPage.endsWith(".do")) {
			if(viewPage.startsWith("ajax:")) {
				response.setCharacterEncoding("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			viewPage = viewPage + ".tiles";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(viewPage);
		}
	}

}
