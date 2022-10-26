//package com.technoelevate.employeemanagementsystem.customidgenerator;
//
//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SessionImplementor;
//import org.hibernate.id.IdentifierGenerator;
//import org.springframework.stereotype.Component;
//
//@Component
//
//public class CustomIdGenerator implements IdentifierGenerator {
//
//	@Override
//	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
//		String prefix = "TYSS";
//		String suffix = "";
//		try {
//			Connection connection = session.connection();
//			Statement createStatement = connection.createStatement();
//			String sql = "select order_id_seq.nextval from dual";
//			ResultSet result = createStatement.executeQuery(sql);
//			if (result.next()) {
//				int value = result.getInt(1);
//				suffix = String.valueOf(value);
//
//			}
//
//		} catch (Exception e) {
//			e.getMessage();
//		}
//
//		return prefix + suffix;
//	}
//
//}
