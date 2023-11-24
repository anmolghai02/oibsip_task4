package com.oasisInfobyte.OnlineExamination;

import java.util.Iterator;
import java.util.Scanner;

public class MCQOnlineExam {
    private String username;
    private String password;
    private boolean isLoggedIn;
    private int timeRemaning;
    private int noOfQuestions;
    private int[] userAnswer;
    private int[] correctAnswer;

    public static void main(String[] args) {
        System.out.println(
                "========Welcome to the Online MCQ Exam ======== \n\nPlease Register Your Username and Password ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Username: ");
        String Username = sc.nextLine();
        System.out.println("Enter Your Password: ");
        String Password = sc.nextLine();
        MCQOnlineExam examSystem = new MCQOnlineExam(Username, Password);
        examSystem.login();
        examSystem.StartExam();
    }

    public MCQOnlineExam(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("You Are Successfully Registered \n");
        this.isLoggedIn = false;
        this.timeRemaning = 1; // time in minutes
        this.noOfQuestions = 10;
        this.userAnswer = new int[noOfQuestions];
        this.correctAnswer = new int[noOfQuestions];
        // Initialize correct answer with random values(0,1)
        for (int i = 0; i < noOfQuestions; i++) {
            correctAnswer[i] = (int) Math.round(Math.random());

        }

    }

    private void login() {
        System.out.println("Logged into Exam Session");
        Scanner sc = new Scanner(System.in);
        System.out.println("Username : ");
        String inputUsername = sc.nextLine();

        System.out.println("Password : ");
        String inputPassword = sc.nextLine();

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            isLoggedIn = true;
            System.out.println("Login Successful \n\tBest of Luck ");
        } else {
            System.out.println("Login Failed. Try Again ");
        }

    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("Logout Successful. ");
    }

    private void StartExam() {
        if (!isLoggedIn) {
            System.out.println("Please Login First !!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("You have " + timeRemaning + " minutes to complete the Exam. ");
        for (int i = 0; i < noOfQuestions; i++) {
            System.out.println("Question" + (i + 1) + ":");
            System.out.println("1.Option A");
            System.out.println("2.Option B");
            System.out.println("Your Answer (1 or 2): ");
            int answer = sc.nextInt();
            userAnswer[i] = answer;
        }
        System.out.println("Would you like to submit ? \n 1: Yes \n 2:No ");
        int n = sc.nextInt();
        if (n == 1) {
            submitExam();
        } else {
            // Auto submit exam when time is up
            try {
                Thread.sleep(timeRemaning * 10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                submitExam();
            }
        }
    }

    private void submitExam() {
        if (!isLoggedIn) {
            System.out.println("Please Login First !!");
            return;
        }
        int score = 0;
        for (int i = 0; i < noOfQuestions; i++) {
            if (userAnswer[i] == correctAnswer[i]) {
                score++;
            }
        }
        System.out.println("Your score is " + score + " out of " + noOfQuestions + ". ");
        System.out.println("Best Of Luck ");
        logout();
    }
}