// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SessionCheckFilter.java

package com.sulliar.ypq.filter;

import com.fuwa.ypq.model.FWUser;
import com.sulliar.ypq.model.User;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.http.*;

public class SessionCheckFilter
	implements Filter
{

	protected FilterConfig filterConfig;
	private String redirectURL;
	private Set notCheckURLList;
	private Set adminURLList;
	private String sessionKey;

	public SessionCheckFilter()
	{
		filterConfig = null;
		redirectURL = null;
		notCheckURLList = new HashSet();
		adminURLList = new HashSet();
		sessionKey = null;
	}

	public void destroy()
	{
		notCheckURLList.clear();
		adminURLList.clear();
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		HttpSession session = request.getSession();
		if (sessionKey == null)
		{
			filterChain.doFilter(request, response);
			return;
		}
		if (!checkRequestURIIntNotFilterList(request) && session.getAttribute(sessionKey) == null)
		{
			response.sendRedirect((new StringBuilder(String.valueOf(request.getContextPath()))).append(redirectURL).toString());
			return;
		}
		if (!checkRequestURIIntNotFilterList(request) && session.getAttribute(sessionKey) != null)
		{
			FWUser user = (FWUser)session.getAttribute(sessionKey);
			if (!user.getDba() && checkAdminURIIntNotFilterList(request))
			{
				response.sendRedirect((new StringBuilder(String.valueOf(request.getContextPath()))).append("/MainPage/main_user.jsp").toString());
				return;
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private boolean checkRequestURIIntNotFilterList(HttpServletRequest request)
	{
		String uri = (new StringBuilder(String.valueOf(request.getServletPath()))).append(request.getPathInfo() != null ? request.getPathInfo() : "").toString();
		return notCheckURLList.contains(uri);
	}

	private boolean checkAdminURIIntNotFilterList(HttpServletRequest request)
	{
		String uri = (new StringBuilder(String.valueOf(request.getServletPath()))).append(request.getPathInfo() != null ? request.getPathInfo() : "").toString();
		return adminURLList.contains(uri);
	}

	public void init(FilterConfig filterConfig)
		throws ServletException
	{
		this.filterConfig = filterConfig;
		redirectURL = filterConfig.getInitParameter("redirectURL");
		sessionKey = filterConfig.getInitParameter("checkSessionKey");
		String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");
		String adminURLListStr = filterConfig.getInitParameter("adminURLList");
		if (notCheckURLListStr != null)
		{
			System.out.println(notCheckURLListStr);
			String params[] = notCheckURLListStr.split(",");
			for (int i = 0; i < params.length; i++)
				notCheckURLList.add(params[i].trim());

		}
		if (adminURLListStr != null)
		{
			System.out.println(adminURLListStr);
			String params[] = adminURLListStr.split(",");
			for (int i = 0; i < params.length; i++)
				adminURLList.add(params[i].trim());

		}
	}
}
