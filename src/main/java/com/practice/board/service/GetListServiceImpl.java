package com.practice.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.board.model.BoardDAO;
import com.practice.board.model.BoardVO;
import com.practice.util.PageVO;

public class GetListServiceImpl implements BoardService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//받을 값X
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int pageNum = 1;
		int amount = 10;
		
		if(request.getParameter("PageNum") != null || request.getParameter("amount") != null) {
			pageNum = Integer.parseInt( request.getParameter("PageNum") );
			amount = Integer.parseInt( request.getParameter("amount"));
		}
		ArrayList<BoardVO> list = dao.getList();
		
		request.setAttribute("list", list);
		
		int total = dao.getTotal();
		PageVO pagevo = new PageVO(pageNum, amount, total);
		
		request.setAttribute("pageVO", pagevo);
	}
	
}
