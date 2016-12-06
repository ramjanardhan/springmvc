package com.mss;

import org.springframework.web.servlet.ModelAndView;

public interface loginDAO {
	public boolean loginCheck(String username,String password);
	}
