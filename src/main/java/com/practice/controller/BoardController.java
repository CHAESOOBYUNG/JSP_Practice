package com.practice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.board.service.BoardService;
import com.practice.board.service.GetContentServiceImpl;
import com.practice.board.service.GetListServiceImpl;
import com.practice.board.service.RegistServiceImpl;


@WebServlet("*.bbs")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardController() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		
		String command = uri.substring( path.length());
	
	    System.out.println(command);
	    
	    BoardService service;
	    
	    if(command.equals("/board/bbs.bbs")) {
	    	
	    	service = new GetListServiceImpl();
	    	service.execute(request, response);
	    	
	    	//포워드 이동
	    	request.getRequestDispatcher("bbs.jsp").forward(request, response); //나가는 경로
	    	
	    } else if(command.equals("/board/board_write.bbs")) { //글쓰기 화면이동
	    	
	    	request.getRequestDispatcher("board_write.jsp");
	    	
	    } else if(command.equals("/board/regist.bbs")) { //글 등록
	    
	       	//등록영역
	        service = new RegistServiceImpl();
	        service.execute(request, response);
	        
	        response.sendRedirect("list.");
	
	    } else if(command.equals("/board/content.bbs")) {
	    	
	    	//상세보기
	    	service = new GetContentServiceImpl();
	    	service.execute(request, response);
	    }
	}
	
}
