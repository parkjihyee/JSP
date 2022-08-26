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
import co.micol.prj.notice.command.AjaxNoticeDelte;
import co.micol.prj.notice.command.AjaxNoticeSearche;
import co.micol.prj.notice.command.NoticeForm;
import co.micol.prj.notice.command.NoticeInsert;
import co.micol.prj.notice.command.NoticeList;
import co.micol.prj.notice.command.NoticeSelect;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<>();  

    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// 요청과 수행할 command연결
//		map.put("/main.do", new MainCommand()); //처음접근하는 곳
//		map.put("/memberLoginForm.do", new MemberLoginForm()); //로그인 폼 호출
//		map.put("/memberLogin.do", new MemberLogin());  //로그인 처리
//		map.put("/memberLogout.do", new MemberLogout()); //로그아웃
//		map.put("/memberList.do", new MemberList()); //회원 목록 보기
//		map.put("/memberJoinForm.do", new MemberJoinForm()); //회원가입 화면
//		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck()); //ajax를 이용한 아이디중복체크
//		map.put("/memberJoin.do", new MemberJoin()); //회원가입 처리
		map.put("/noticeList.do", new NoticeList()); //게시글 목록
		map.put("/noticeForm.do", new NoticeForm()); //게시글 입력폼 호출
		map.put("/noticeInsert.do", new NoticeInsert()); //게시글 등록
		map.put("/ajaxNoticeSearche.do", new AjaxNoticeSearche());//게시글 검색
		map.put("/ajaxNoticeDelte.do", new AjaxNoticeDelte()); //게시글 Ajax로 삭제
		map.put("/noticeSelect.do", new NoticeSelect()); //하나의 게시글 조회
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청분석하고 실행하고 결과를 돌려주는 곳
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		if(!viewPage.endsWith(".do")) {
			if(viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			
			//viewPage = viewPage + ".tiles";
			viewPage = "WEB-INF/views/" + viewPage + ".jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);			
		} else {
			response.sendRedirect(viewPage);
		}
	}

}
