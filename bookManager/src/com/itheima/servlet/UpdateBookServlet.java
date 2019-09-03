package com.itheima.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.Book;
import com.itheima.service.BookService;

public class UpdateBookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//��ȡ������
		Book book = new Book();
		try {
			BeanUtils.populate(book, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//����ҵ���߼�
		BookService bs = new BookService();
		bs.updateBook(book);
		//�ַ�ת��
		request.getRequestDispatcher("BookListServlet").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
