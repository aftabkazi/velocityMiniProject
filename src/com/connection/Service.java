package com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Service {

	PreparedStatement prs = null;
	Connection con = null;

	public int insertStudentdata(String firstName, String lastName, String userName, String password, String city,
			String mail, Long mobile) {
		try {
			ConnectionTest connectionTest = new ConnectionTest();

			con = connectionTest.getConnectionDetails();
			prs = con.prepareStatement("insert into user(firstName,lastName,userName,password,"
					+ "city,mail,mobile)values(?,?,?,?,?,?,?)");

			prs.setString(1, firstName);
			prs.setString(2, lastName);
			prs.setString(3, userName);
			prs.setString(4, password);
			prs.setString(5, city);
			prs.setString(6, mail);
			prs.setLong(7, mobile);

			int i = prs.executeUpdate();

			return i;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int validateUser(String userName, String password) {
		try {

			ConnectionTest connectionTest = new ConnectionTest();

			con = connectionTest.getConnectionDetails();
			prs = con.prepareStatement("select * from user where userName=? and password = ?");

			prs.setString(1, userName);
			prs.setString(2, password);
			ResultSet rs = prs.executeQuery();

			if (rs.next()) {
				return 1;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Question> getAllQuestions() {
		List<Question> questionList = new ArrayList<>();

		try {
			ConnectionTest connectionTest = new ConnectionTest();

			con = connectionTest.getConnectionDetails();
			prs = con.prepareStatement("select * from question");

			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				String question = rs.getString("question");
				String option1 = rs.getString("option1");
				String option2 = rs.getString("option2");
				String option3 = rs.getString("option3");
				String option4 = rs.getString("option4");
				String correctAns = rs.getString("correctAns");
				Question q = new Question(question, option1, option2, option3, option4, correctAns);

				questionList.add(q);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	public int getScoreByUserNameAndPassword(String UserName, String password) {

		try {
			ConnectionTest connectionTest = new ConnectionTest();

			con = connectionTest.getConnectionDetails();
			prs = con.prepareStatement("select score from user where userName=? and password = ?");

			prs.setString(1, UserName);
			prs.setString(2, password);
			ResultSet rs = prs.executeQuery();

			if (rs.next()) {
				int score = rs.getInt("score");
				return score;
			} else {
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int addQuestiontoDB(String question, String option1, String option2, String option3, String option4,
			String correctAns) {
		try {
			ConnectionTest connectionTest = new ConnectionTest();

			con = connectionTest.getConnectionDetails();
			prs = con.prepareStatement(
					"insert into question(question,option1,option2,option3,option4,correctAns) values (?,?,?,?,?,?)");

			prs.setString(1, question);
			prs.setString(2, option1);
			prs.setString(3, option2);
			prs.setString(4, option3);
			prs.setString(5, option4);
			prs.setString(6, correctAns);
			int i = prs.executeUpdate();

			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getStudentScoreBasedOnId(int id) {
		try {
			ConnectionTest connectionTest = new ConnectionTest();
			con = connectionTest.getConnectionDetails();
			prs = con.prepareStatement("select * from user where id =?");
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();

			if (rs.next()) {
				int score = rs.getInt("score");
				return score;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public List<User> getStudentScoreBasedOnAscendingOrder() {
		List<User> userList = new ArrayList<>();
		try {
			ConnectionTest connectionTest = new ConnectionTest();
			con = connectionTest.getConnectionDetails();
			prs = con.prepareStatement("select firstName,lastName,score from user order by score ASC");

			ResultSet rs = prs.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				Integer score = rs.getInt("score");

				User user = new User(firstName, lastName, score);
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}

	public int addUserAnswersToDatabase(String userName, Integer score) {
		try {
			ConnectionTest connectionTest = new ConnectionTest();
			con = connectionTest.getConnectionDetails();
			prs = con.prepareStatement("update user set score=? where userName=?");
			prs.setInt(1, score);
			prs.setString(2, userName);

			int i = prs.executeUpdate();

			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
