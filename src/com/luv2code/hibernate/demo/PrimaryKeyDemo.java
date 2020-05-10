package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create session factory
				SessionFactory factory = new Configuration()
										 .configure("hibernate.cfg.xml")
										 .addAnnotatedClass(Student.class)
										 .buildSessionFactory();
				
				//create session
				Session session = factory.getCurrentSession();
				
				try {
					//use the session object to save java object
					
					//create 3 student objects
					System.out.println("Creatingg new student object......");
					Student tempStudent1 = new Student("Paul", "Wall", "paul@luv2code.com");
					Student tempStudent2 = new Student("Mary", "Public", "paul@luv2code.com");
					Student tempStudent3 = new Student("Bonita", "Applebum", "paul@luv2code.com");
					
					//start a transaction
					session.beginTransaction();
					
					//save the student object
					System.out.println("Saving the student");
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					//commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}
				finally {
					factory.close();
				}
	}

}