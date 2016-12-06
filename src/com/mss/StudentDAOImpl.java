package com.mss;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class StudentDAOImpl implements StudentDAO {
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	}  
	public int saveStudent(Student s){  
	  int status=(int) template.save(s);
	  System.out.println("satus is "+status);
	  return status;
	}  
	public int updateStudent(Student s)
		{
		int res = template.bulkUpdate("update Student s set s.firstname='"+s.getFirstname()+"', s.lastname='"+s.getLastname()+"',s.reg_date='"+s.getReg_date()+"'  where s.studentid=?",s.getStudentid());
		return res;
		//	template.update(s);
	}
	public int deleteStudent(Student s)
	{
		int res = template.bulkUpdate("delete from Student s  where s.studentid=?",s.getStudentid());
		return res;
		
	//	template.delete(s);
	}
	
	public List<Student> getStudent()
	{
	    List<Student> q = template.find("from Student");
	   System.out.println(q.size());
	    return (List<Student>) q;
	}
}
