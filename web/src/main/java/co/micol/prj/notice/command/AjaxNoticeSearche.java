package co.micol.prj.notice.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;
import co.micol.prj.notice.vo.NoticeVO;

public class AjaxNoticeSearche implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 검색
		NoticeService noticeDao = new NoticeServiceImpl();
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		ObjectMapper mapper = new ObjectMapper();  //jackson 라이브러리 사용(json)
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		System.out.println(key);
		System.out.println(val);
		list = noticeDao.noticeSearchList(key, val);
		String jsonList = null;
		/*
		 * StringBuffer buffer = new StringBuffer(); String line = null; try {
		 * BufferedReader reader = new BufferedReader(request.getReader()); while((line
		 * = reader.readLine()) != null) { System.out.println("================tttt"+
		 * line); buffer.append(line); } jsonList = buffer.toString();
		 * System.out.println(jsonList +"========="); }catch(Exception e) {
		 * e.printStackTrace(); }
		 */

		
		try {
			jsonList = mapper.writeValueAsString(list);  //객체가 json 타입의 스트링으로 변환
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "ajax:"+jsonList;
	}

}
