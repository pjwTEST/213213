package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MyInfoDelProAction;
import action.MyInfoModAction;
import action.MyInfoModProAction;
import action.UserCheckDuplicateIdAction;
import action.UserInfoAction;
import action.UserJoinProAction;
import action.UserLoginProAction;
import action.UserLogoutProAction;
import action.UserProductListProAction;
import action.userProductDetailAction;
import vo.ActionForward;

@WebServlet("*.us")
public class UserFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserFrontController123");
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command);

		Action action = null;
		ActionForward forward = null;	
		
		if(command.equals("/UserJoinForm.us")) {
			forward = new ActionForward();
			forward.setPath("user/user_join.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/UserJoinPro.us")) {
			action = new UserJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/UserLoginPro.us")) {
			action = new UserLoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/UserLogoutPro.us")) {
			action = new UserLogoutProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CheckDuplicateId.us")) {
			action = new UserCheckDuplicateIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/UserCheckIdForm.us")) {
			forward = new ActionForward();
			forward.setPath("user/user_check_id.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/UserLogin.us")) {
			forward = new ActionForward();
			forward.setPath("user/user_login.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/UserList.us")) {
			forward = new ActionForward();
			forward.setPath("user/user_list.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MyPage.us")) {
			action = new UserInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("forward ?????? ??????"+e.getMessage());
				e.printStackTrace();
			}
		}
		else if(command.equals("/UserAuthen.us")) {
			forward = new ActionForward();
			forward.setPath("user/user_authen.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MyInfoMod.us")){
			try {
				action = new MyInfoModAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("forward ?????? ??????"+e.getMessage());
				e.printStackTrace();
			}
		}else if(command.equals("/MyInfoModPro.us")){
				try {
					action = new MyInfoModProAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					System.out.println("forward ?????? ??????"+e.getMessage());
					e.printStackTrace();
				}
		}else if(command.equals("/MyInfoDel.us")) {
				forward = new ActionForward();
	            forward.setPath("user/user_del_terms.jsp");
	            forward.setRedirect(false);
		}else if(command.equals("/MyInfoDelPro.us")) {
			try {
				action = new MyInfoDelProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("forward ?????? ??????"+e.getMessage());
				e.printStackTrace();
		}
		}else if(command.equals("/UserTerms.us")) {
            forward = new ActionForward();
            forward.setPath("user/user_terms.jsp");
            forward.setRedirect(false);
		}else if(command.equals("/UserProductList.us")){
			try {
				action = new UserProductListProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/UserProductDetail.us")) {
			action = new userProductDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		
		
		
		// --------------------------------------------------------------------------------------
		// ActionForward ????????? ????????? ????????? ????????? ?????? ????????? ?????? ???????????? ?????? ???????????? ??????
		if(forward != null) { // ActionForward ????????? null ??? ?????? ???????????? ????????? ?????? ??????
			// Redirect ?????? vs Dispatcher ?????? ???????????? ??? ????????? ?????? ????????? ?????? ??????
			if(forward.isRedirect()) { // Redirect ??????
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher ??????
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}







