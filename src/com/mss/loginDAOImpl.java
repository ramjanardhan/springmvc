package com.mss;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;  
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate3.HibernateTemplate;  
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
public class loginDAOImpl implements loginDAO {
	private SessionFactory sessionFactory;

	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	}  
	

	public boolean loginCheck(String username,String password)
	{
		System.out.println("into login check");
		 
	      
		boolean loginStatus=false;
		try
		{
			System.out.println("----------------"+template);
		 @SuppressWarnings("unchecked")
		List<login> loginobj=	template.find("from login  where username='"+username+"' and password='"+password+"'");
		 if(loginobj.size()!=0){
		System.out.println("in if----- list size not equals to zero");
			 loginStatus=true;
		 }
		}
		catch(Exception e)
		{
			
		}
		System.out.println(" list size equals to zero");
		return loginStatus;
	}
}

  
