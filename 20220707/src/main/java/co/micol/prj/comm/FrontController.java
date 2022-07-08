package co.micol.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.MainCommand;
import co.micol.prj.member.command.AjaxMemberIdCheck;
import co.micol.prj.member.command.MemberJoin;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberList;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
import co.micol.prj.member.command.MemberLogout;

@WebServlet("*.do") // *.do로 들어오면 무조건 이 서블릿 동작
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<>(); // 요청과 실행문을 매핑하기 위해
    
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// 초기화 하는 메소드(Mapping 하는 부분을 작성)
		map.put("/main.do", new MainCommand()); // 처음 접속하는 페이지 / main.do를 넘겼더니 new MainCommand가 옴
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); // 로그인 처리
		map.put("/memberLogout.do", new MemberLogout()); // 로그아웃
		map.put("/memberList.do", new MemberList()); // 회원 목록 보기
		map.put("/memberJoinForm.do", new MemberJoinForm());
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck()); //ajax를 이용한 아이디 중복 체크
		map.put("/memberJoin.do", new MemberJoin()); // 회원가입처리
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실행(서비스)하는 메소드
		request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지
		String uri = request.getRequestURI(); // 요청된 URI를 확인한다
		String contextPath = request.getContextPath(); //요청 URL로부터 contextPath를 가져와 담는다?
		String page = uri.substring(contextPath.length()); // 실제 요청한 서블릿 페이지를 찾는다
		
		Command command = map.get(page); // 실제 수행할 Command를 찾음/요청에따른 일대일 맵핑을 함 / new MainCommand();
		//Command command = new MainCommand();
		//인터페이스는 구현체를 통해서만 초기화 가능
		
		String viewPage = command.exec(request, response); //요청 Command를 수행하고 결과를 받음
		
		// viewResolve / 보여줄page(돌려줄page)선택
		if(!viewPage.endsWith(".do") && !viewPage.equals(null)) { //문자열끝에 .do가 붙어있지않고 null이 아니면 아래 실행
			if(viewPage.startsWith("ajax:")) { // 시작하는 문구에 ajax: 가 있으면
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5)); // ajax:가 다섯글자!
				return;
			}
			viewPage = "WEB-INF/views/" + viewPage + ".jsp"; //시스템에서 접근 가능한 폴더를 더해주고
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response); // 원하는 페이지를 호출해서 전달함
		}else {
			response.sendRedirect(viewPage); // .do로 권한 위임 처리
		}
	}

}
