package org.ecomileage.security;

import org.ecomileage.bean.UserItem;
import org.ecomileage.rest.common.AuthorizationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ecomileage.web.common.Login;
import org.ecomileage.web.common.utils.JsonUtils;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Kenny Phong .
 */
@Service
public class RestAuthenticationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		HttpServletResponse httpResponse = ((HttpServletResponse) response);
		String path = ((HttpServletRequest) request).getRequestURI();
		
		if (httpRequest.isRequestedSessionIdFromURL()) {
			if (session != null) {
				session.invalidate();
			}
		}

		if (path.startsWith("/api") && !path.startsWith("/api/public") ) {
			String token = httpRequest.getHeader("token");
			System.out.println("---- Start API ---");
			System.out.println("Token: " + token);
			UserItem user = AuthorizationToken.convertToObject(token);
			Date date = new Date();
			
			System.out.println(JsonUtils.toString(user));
			System.out.println(date.getTime());

			if ( user == null || user.getId() == null ) {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				httpResponse.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				JSONObject js = new JSONObject();
				js.put("code", HttpStatus.UNAUTHORIZED.value());
				js.put("data", null);
				js.put("mesage", "You have to send token !");
				PrintWriter writer = response.getWriter();
				writer.write(js.toString());
				writer.close();
				return;
			}else if(date.getTime() > user.getExpiration()) {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				httpResponse.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				JSONObject js = new JSONObject();
				js.put("code", HttpStatus.UNAUTHORIZED.value());
				js.put("data", null);
				js.put("mesage", "Token is expired !");
				PrintWriter writer = response.getWriter();
				writer.write(js.toString());
				writer.close();
				return;
			}
		}
		
		if (path.startsWith("/auth")) {
			UserItem user = Login.getUserLogin(session);
			if (user == null) {
				((HttpServletResponse) response).sendRedirect("/login");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
