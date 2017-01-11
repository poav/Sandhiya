package com.sapient.statestreetscreeningapplication.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginInterceptor.
 */
public class LoginInterceptor extends AbstractInterceptor implements
		StrutsStatics {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3483359092247985893L;
	
	/** The Constant USER_HANDLE. */
	public static final String USER_HANDLE = "user";
	
	/** The Constant LOGIN_ATTEMPT. */
	public static final String LOGIN_ATTEMPT = "loginAttempt";

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		CustomLoggerUtils.INSTANCE.log.info("inside login interceptor");

		final ActionContext actionContext = invocation.getInvocationContext();

		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(HTTP_REQUEST);

		HttpSession session = request.getSession(true);

		try {
			Object user = session.getAttribute(USER_HANDLE);

			if (user == null) {
				CustomLoggerUtils.INSTANCE.log.info("user name is null ");
				String loginAttempt = request.getParameter(LOGIN_ATTEMPT);
				if (!StringUtils.isBlank(loginAttempt)) {
					return invocation.invoke();
				}
				return "login";
			} else {
				CustomLoggerUtils.INSTANCE.log
						.info("user name is not null"
								+ ((PersonNewBean) user).getPersonDetails()
										.getName());
				return invocation.invoke();
			}
		} catch (Exception exception) {
			CustomLoggerUtils.INSTANCE.log.info("Login interceptor exception");
			exception.printStackTrace();
		}
		return "login";
	}
}
