package com.practice.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.board.model.BoardDAO;
import com.practice.board.model.BoardVO;

public class GetContentServiceImpl implements BoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = dao.getContent(bno);
		
		request.setAttribute("vo", vo);
		
	}
	
	
}
