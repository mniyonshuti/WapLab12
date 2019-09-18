package edu.mum.cs.cs472;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/guessNumber")
public class GuessNumberServlet extends HttpServlet {

    public static  String currSession = "currSession";
    private Quiz quiz;
    private int count = 0;
    int score = 0;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        HttpSession session = req.getSession();


        if(session.getAttribute(currSession) != null){
            quiz = (Quiz)session.getAttribute(currSession);
            System.out.println("Session Exists");
        }
        else {
            System.out.println("Setting a new session");
            quiz = new Quiz();
            session.setAttribute(currSession, quiz);
            session.setAttribute("count", count);
            session.setAttribute("score", score);
        }
        if(count <= 5){
            System.out.println("Count Less than 5");
            score = (Integer) session.getAttribute("score");
            count = (Integer) session.getAttribute("count");
            PrintWriter printWriter = resp.getWriter();
            String output = "";
            output += "<!DOCTYPE html> <html lang=\"en\">  <head> <meta charset=\"UTF-8\"> <title>GuessNextNumber</title> </head>  " +
                    "<body>  <h1>The Number Quiz</h1> " +
                    "<p>Your current score is "+ score +" </p>" +
                    "<p>Guess the next number in the sequence</p>" +
                    "<p>"+quiz.getQuestion(count)+"</p>" +
                    "<form action=\"guessNumber\" method=\"post\">" +
                    "<p> Your answer: <input name=\"answer\" type=\"number\" required> </p> " +
                    "<p> <input type=\"submit\" value=\"Submit\"> </p>\n" +
                    "</form> </body> </html>";
            printWriter.println(output);
        }
        else {
            System.out.println("Count greater than 5");
            PrintWriter printWriter2 = resp.getWriter();
            String outPut = "";
            outPut += outPut += "<!DOCTYPE html> <html lang=\"en\">  <head> <meta charset=\"UTF-8\"> <title>GuessNextNumber</title> </head>  " +
                    "<body>  <h1>The Number Quiz</h1> " +
                    "<p>Your current score is " + score + " </p>" +
                    "<p>You have completed the quiz with a score of " + score + " out of 5.</p>" +
                    "</body> </html>";
            printWriter2.println(outPut);
        }
        count++;
        System.out.println("Count " + count);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("Do post method");
        HttpSession session = req.getSession();
        quiz = (Quiz)session.getAttribute(currSession);
        if(quiz != null){
            int answerValue = Integer.valueOf(req.getParameter("answer"));
            System.out.println("Answer " + answerValue);
            if(answerValue == quiz.getAnswer(count)) score ++;
            System.out.println("Score " + score);
//            RequestDispatcher dispatcher = req.getRequestDispatcher("guessNumber");
//            dispatcher.forward(req, resp);
            resp.sendRedirect("guessNumber");
        }
    }


}
