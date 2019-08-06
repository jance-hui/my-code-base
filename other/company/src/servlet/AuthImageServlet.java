package servlet;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.VerifyCodeUtils;
 
public class AuthImageServlet extends HttpServlet{
	static final long serialVersionUID = 1L;
 
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		//��������ִ�
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		//����Ựsession
		HttpSession session = request.getSession(true);
		session.setAttribute("rand", verifyCode.toLowerCase());
		//����ͼƬ
		int w = 150, h = 50;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
 
	}
}
