package co.micol.prj.notice.service;

import java.util.List;

import co.micol.prj.notice.vo.NoticeVO;

public interface NoticeService {
	List<NoticeVO> noticeSelectList();  //전체조회
	NoticeVO noticeSelect(NoticeVO vo);  //한글조회
	int noticeInsert(NoticeVO vo);  //글 입력
	int noticeDelete(NoticeVO vo);  //글 수정
	int noticeUpdate(NoticeVO vo);  //글 삭제
	
	List<NoticeVO> noticeSearchList(String key, String val);  //글 목록에서 검색하기
}
