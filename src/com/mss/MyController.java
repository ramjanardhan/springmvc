package com.mss;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class MyController {
	@Autowired(required=true)
	private loginDAO logindao;  
		public void setLogindao(loginDAO logindao) {
		this.logindao = logindao;
	}
		@Autowired(required=true)
		private StudentDAO studentdao;  
		public void setStudentdao(StudentDAO studentdao) {
			this.studentdao = studentdao;
		}

	@RequestMapping("/")
	public ModelAndView helloWorld1() {
	 String message="hi";
	  
	   return new ModelAndView("index", "welcomeMessage", message);
	}//ModelAndView closed
	
@RequestMapping("/java4s")
public ModelAndView helloWorld() {
	
   String message =  "Welcome to Java4s.com Spring MVC 3.2.x Sessions";
   message += "<br>You Did it....!";
 
   return new ModelAndView("welcomePage", "welcomeMessage", message);
}//ModelAndView closed
 

@RequestMapping("/login")
public ModelAndView login(@RequestParam("username")String username,@RequestParam("password")String password) {
System.out.println("into login");
System.out.println("username is "+username+" and password is "+password);
//Resource r=new ClassPathResource("applicationContext.xml");  
//BeanFactory factory=new XmlBeanFactory(r);  
//login e=new login();  
//e.setUserid(11);
//e.setUsername("xyz");
//e.setPassword("xyz");
//		logindao.saveEmployee(e);  
String message="invalied credentials";
boolean status=logindao.loginCheck(username, password);
System.out.println("Stauts is "+status);	
if(status==true)
 {
  message="welcome "+username;
  return new ModelAndView("student", "message", message);
 }
else
  return new ModelAndView("error", "message", message);
}//ModelAndView closed
@RequestMapping("/student")
public ModelAndView StudentInf(@RequestParam("studentId")String studentId,@RequestParam("firstname")String firstname,@RequestParam("lastname")String lastname,@RequestParam("date")String date,@RequestParam("action")String actionName)
{
	System.out.println("action name is "+actionName);
	if(actionName.equalsIgnoreCase("Add"))
	{
		Student s=new Student();
		s.setFirstname(firstname);
		s.setLastname(lastname);
		s.setStudentid(Integer.parseInt(studentId));
		String date1 = date;
        String[] split=date1.split("/");
        String date2=split[2]+"-"+split[0]+"-"+split[1]+" 00:00:00";
        System.out.println("date is "+date2);

		
		
		Timestamp timestamp = Timestamp.valueOf(date2);
		s.setReg_date(timestamp);
		int rslt=studentdao.saveStudent(s);
	    if(rslt==Integer.parseInt(studentId))
	    {
	    	return new ModelAndView("student", "message", "Successfull Inserted");
	    }
	    else
	    {
	    	return new ModelAndView("student", "message", "Insertion failed!!");
	    }
	}
	if(actionName.equalsIgnoreCase("Edit")){
		Student s=new Student();
		s.setFirstname(firstname);
		s.setLastname(lastname);
		s.setStudentid(Integer.parseInt(studentId));
		String date1 = date;
        String[] split=date1.split("/");
        String date2=split[2]+"-"+split[0]+"-"+split[1]+" 00:00:00";
        System.out.println("date is "+date2);
Timestamp timestamp = Timestamp.valueOf(date2);
		s.setReg_date(timestamp);
		int result=studentdao.updateStudent(s);
		if(result>0)
			return new ModelAndView("student", "message", "Updated successfully");
		else
			return new ModelAndView("student", "message", "Updation failed!!");
			
	}
	if(actionName.equalsIgnoreCase("Delete")){
		Student s=new Student();
		s.setFirstname(firstname);
		s.setLastname(lastname);
		s.setStudentid(Integer.parseInt(studentId));
		String date1 = date;
        String[] split=date1.split("/");
        String date2=split[2]+"-"+split[0]+"-"+split[1]+" 00:00:00";
        System.out.println("date is "+date2);
        Timestamp timestamp = Timestamp.valueOf(date2);
		s.setReg_date(timestamp);
		int result=studentdao.deleteStudent(s);
		if(result>0)
			return new ModelAndView("student", "message", "Deleted successfully");
		else
			return new ModelAndView("student", "message", "Deletion failed!!");
	}
	if(actionName.equalsIgnoreCase("Search")){
		String str=" ";
		//String str1="                 ";
		HashMap m=new HashMap();
		List<Student> list= studentdao.getStudent();
		int n=list.size();
		for(int i=0;i<n;i++)
		{
			Student s=new Student();
			s=list.get(i);
			str=str+"   "+s.getStudentid()+" "+s.getFirstname()+" "+s.getLastname()+" "+s.getReg_date();
		}
		return new ModelAndView("student", "list", str);
	}
	return new ModelAndView("student", "message", "");
	 
}


}