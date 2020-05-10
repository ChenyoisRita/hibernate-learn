package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			// get the instructor detail obejct
			int theId = 3;
			InstructorDetail tmpInstructorDetail =
					session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail: " + tmpInstructorDetail);
			
			//print the associated instructor
			System.out.println("the associated instructor: " +
					tmpInstructorDetail.getInstructor());
		
			//now let's delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: "
					+ tmpInstructorDetail);
			
			//remove the asscociated object reference
			//break bi-directional link
			
			tmpInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tmpInstructorDetail);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			//handle connection leak issue
			session.close();
			
			
			factory.close();
		}
	}

}
