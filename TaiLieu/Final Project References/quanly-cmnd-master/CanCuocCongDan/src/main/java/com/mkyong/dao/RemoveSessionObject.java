package com.mkyong.dao;

import java.util.TimerTask;

import javax.servlet.http.HttpSession;

public class RemoveSessionObject extends TimerTask {

	private HttpSession session;
	private String object;
	@Override
	public void run() {
		System.out.println("Vao remove session object " + object);
		session.removeAttribute(object);
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	
}
