package com.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApplication {

	public static void main(String[] args) {

		System.out.println("Welcome to quiz application");

		System.out.println("---- User operations ----");

		System.out.println("1. Student Registration");
		System.out.println("2. Student Login");
		System.out.println("3. Display list of questions");
		System.out.println("4. Attend Quiz");
		System.out.println("5. Display quiz result");
		System.out.println("---- Admin operations ----");
		System.out.println("6. Display all students score as per ascending order");
		System.out.println("7. Fetch student score by using id");
		System.out.println("8. Add question with 4 options into database");

		System.out.println("Please select your option ");

		Scanner sc = new Scanner(System.in);
		int score;
		int userInput = sc.nextInt(), i, id;
		List<Question> questionList = null;

		Service s = new Service();

		switch (userInput) {

		case 1:
			String firstName;
			while (true) {
				System.out.println("Enter your first name : ");
				firstName = sc.next();
				
				if (Validations.isValidName(firstName)) {
					break;
				} else {
					System.out.println("First name can contain only characters");
				}
			}

			String lastName;
			while (true) {
				System.out.println("Enter your last Name : ");
				lastName = sc.next();

				if (Validations.isValidName(lastName)) {
					break;
				} else {
					System.out.println("====Last name can contain only characters====");
				}
			}

			String userName;
			while (true) {
				System.out.println("Enter your user Name : ");
				userName = sc.next();

				if (Validations.isValidUserName(userName)) {
					break;
				} else {
					System.out.println("===User name can contain only characters and userscore===");
				}
			}

			String password;
			while (true) {
				System.out.println("Enter your password : ");
				password = sc.next();

				if (Validations.isValidPassword(password)) {
					break;
				} else {
					System.out.println("==password must contain one uppercase ,one lowercase,"
							+ "one special character,one number and alteast of 5 characters==");
				}
			}

			System.out.println("Enter your city : ");
			String city = sc.next();

			String mail;
			while (true) {
				System.out.println("Enter your mail id : ");
				mail = sc.next();

				if (Validations.isValidEmail(mail)) {
					break;
				} else {
					System.out.println("====Enter valid email====");
				}
			}

			Long mobile;
			while (true) {
				System.out.println("Enter your mobile number : ");
				mobile = sc.nextLong();
				if (Validations.isValidMobileNumber(mobile) == true) {
					break;
				} else {
					System.out.println("=====Enter valid number=====");
				}
			}

			i = s.insertStudentdata(firstName, lastName, userName, password, city, mail, mobile);

			if (i == 1) {
				System.out.println("registration successful");
			} else {
				System.out.println("==== Failed to register, please try again === ");
			}
			break;
		case 2:
			System.out.println("Enter your username : ");
			userName = sc.next();

			System.out.println("Enter your password : ");
			password = sc.next();
			i = s.validateUser(userName, password);

			if (i == 1) {
				System.out.println("Login successful");
			} else {
				System.out.println("Invalid username or pasword");
			}
			break;
		case 3:
			System.out.println("List of questions with the four optins are : ");
			questionList = s.getAllQuestions();

			for (Question question : questionList) {

				System.out.println("question : " + question.getQuestion());
				System.out.println("a. : " + question.getOption1());
				System.out.println("b. : " + question.getOption2());
				System.out.println("c. : " + question.getOption3());
				System.out.println("d. : " + question.getOption4());
			}
			break;
		case 4:
			int scoreCount = 0;
			questionList = s.getAllQuestions();
			String userAnswer = "";
			List<String> userAnswersList = new ArrayList<>();

			System.out.println("Enter your username : ");
			userName = sc.next();
			userAnswersList.add(userName);

			System.out.println("Enter your password : ");
			password = sc.next();
			userAnswersList.add(password);

			i = s.validateUser(userName, password);

			if (i == 1) {
				for (Question question : questionList) {
					System.out.println(question.getQuestion());
					System.out.println("a : " + question.getOption1());
					System.out.println("b : " + question.getOption2());
					System.out.println("c : " + question.getOption3());
					System.out.println("d : " + question.getOption4());

					System.out.println("Enter your answer (a-d): ");
					userAnswer = sc.next();
					sc.nextLine();

					if (userAnswer.equals(question.getCorrectAns())) {
						scoreCount += 10;
					}
				}
				i = s.addUserAnswersToDatabase(userName, scoreCount);
				if (i == 1) {
					System.out.println("Quiz completed sucessfully");
				} else {
					System.out.println("Error , attend after some time");
				}

			} else {
				System.out.println("------- Invalid username or password -----------");
			}

			break;

		case 5:
			System.out.println("Enter username :");
			userName = sc.next();

			System.out.println("Password : ");
			password = sc.next();

			score = s.getScoreByUserNameAndPassword(userName, password);
			System.out.println("score >> " + score);
			break;

		case 6:
			List<User> userList = s.getStudentScoreBasedOnAscendingOrder();

			for (User userScoreList : userList) {

				System.out.print(userScoreList.getFirstName() + " " + userScoreList.getLastName() + " == "
						+ userScoreList.getScore());

				System.out.println();
			}

			break;
		case 7:
			boolean validIdEntered = false;

			while (!validIdEntered) {
				System.out.println("Enter student id : ");
				id = sc.nextInt();
				score = s.getStudentScoreBasedOnId(id);

				if (score == -1) {
					System.out.println("Invalid id, Enter valid id");
				} else {
					System.out.println("score >> " + score);
					validIdEntered = true;
				}
			}

			break;

		case 8:

			System.out.println("Enter the question : ");
			sc.nextLine();
			String question = sc.nextLine();

			System.out.println("option 1 : ");
			String option1 = sc.nextLine();

			System.out.println("option 2 : ");
			String option2 = sc.nextLine();

			System.out.println("option 3 : ");
			String option3 = sc.nextLine();

			System.out.println("option 4 : ");
			String option4 = sc.nextLine();

			System.out.println("Enter correct ans : ");
			String correctAns = sc.nextLine();

			i = s.addQuestiontoDB(question, option1, option2, option3, option4, correctAns);

			if (i == 1) {
				System.out.println("Question added");
			} else {
				System.out.println("failed");
			}
		}

	}

}
