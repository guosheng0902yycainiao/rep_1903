package com.emp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//��½������
/**
 * 1.preHandle��ҵ��������������֮ǰ������;
 * 2.postHandle��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ��;
 * 3.afterCompletion��DispatcherServlet��ȫ����������󱻵���,������������Դ�� ��
 *    afterCompletion()ִ����ɺ�ʼ��Ⱦҳ��
 *  preHandle-->/emp/conditionList-->postHandle-->����ListEmp.jsp(html)
 *  -->afterCompletion-->��������ش�html
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
		request.setAttribute("msg", "���ȵ�¼3!");
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
		request.setAttribute("msg", "���ȵ�¼2!");
		
	}

	@Override
	/**
	 * return true  ������ ���� ����ҵ������  ����/emp/conditionList
	 * return false ����,������ҵ�����
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handle) throws Exception {
		System.out.println("preHandle");
		Object user=request.getSession().getAttribute("user");
		if(user!=null){
			//�ѵ�¼��,����
			return true;
		}
		//ת����Login.jsp
		//�󶨴�����Ϣ
		request.setAttribute("msg", "���ȵ�¼!");
		String appName=request.getServletContext().getContextPath();
		System.out.println(appName);
		//ת������������(��Ŀ��)
		request.getRequestDispatcher("/user/toLogin").forward(request,response);
		return false;
	}

}
