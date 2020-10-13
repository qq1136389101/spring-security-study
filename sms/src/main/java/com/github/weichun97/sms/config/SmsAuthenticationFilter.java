package com.github.weichun97.sms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Sms authentication filter.
 */
@Getter
@Setter
public class SmsAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {

	/**
	 * The constant MOBILE_KEY.
	 */
	public static final String MOBILE_KEY = "mobile";

	private String mobileParameter = MOBILE_KEY;
	private boolean postOnly = true;


	/**
	 * Instantiates a new Sms authentication filter.
	 */
	public SmsAuthenticationFilter() {
		super(new AntPathRequestMatcher("/smsLogin", "POST"));
	}

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);

		if (mobile == null) {
			mobile = "";
		}

		mobile = mobile.trim();

		SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * 获取手机号
	 *
	 * @param request so that request attributes can be retrieved
	 * @return the mobile that will be presented in the <code>Authentication</code> request token to the <code>AuthenticationManager</code>
	 */
	private String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	/**
	 * Provided so that subclasses may configure what is put into the authentication
	 * request's details property.
	 *
	 * @param request     that an authentication request is being created for
	 * @param authRequest the authentication request object that should have its details set
	 */
	protected void setDetails(HttpServletRequest request,
			SmsAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
}
