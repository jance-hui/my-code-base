package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	//ִ�й���
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		if (session.getAttribute("name") == null) {
			((HttpServletResponse)response).sendRedirect("user");
		} else {
			//�������˻�ִ��
			chain.doFilter(request, response);
		}
	}

	//��ʼ��
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	//����
	public void destroy() {
	}
}
