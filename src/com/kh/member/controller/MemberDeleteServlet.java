package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/delete.me")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userPwd = request.getParameter("pwd");

		HttpSession session = request.getSession();

		String userId = ((Member)session.getAttribute("loginUser")).getId();

		int result = new MemberService().deleteMember(userId, userPwd);

		if(result > 0) {

			session.removeAttribute("loginUser");
			session.setAttribute("msg", "ȸ�� Ż�� �Ϸ�Ǿ����ϴ�.");
			response.sendRedirect(request.getContextPath());

		}else {
			request.setAttribute("msg", "ȸ�� Ż�� �����߽��ϴ�.");

			RequestDispatcher view = request.getRequestDispatcher("views/member/memberDelete.jsp");
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
