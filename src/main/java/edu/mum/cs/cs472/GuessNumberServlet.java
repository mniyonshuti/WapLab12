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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        HttpSession session = req.getSession();

        Integer currCount = 0;
        int score = 0;
        if(session.getAttribute(currSession) != null){
            quiz = (Quiz)session.getAttribute(currSession);
            currCount = (Integer) session.getAttribute("count");
            session.setAttribute("count", ++currCount);
        }
        else {
            quiz = new Quiz();
            session.setAttribute(currSession, quiz);
            session.setAttribute("count", currCount);
            session.setAttribute("score", score);
        }
        if(currCount < 5){
            score = (Integer) session.getAttribute("score");
            PrintWriter printWriter = resp.getWriter();

            String output = "";
            output += "<!DOCTYPE html> <html lang=\"en\">  <head> <meta charset=\"UTF-8\"> <title>GuessNextNumber</title> </head>  " +
                    "<body>  <h1>The Number Quiz</h1> " +
                    "<p>Your current score is "+ score +" </p>" +
                    "<p>Guess the next number in the sequence</p>" +
                    "<p>"+quiz.getQuestion(currCount)+"</p>" +
                    "<form action=\"guessNumber\" method=\"post\">" +
                    "<p> Your answer: <input name=\"answer\" type=\"number\" required> </p> " +
                    "<p> <input type=\"submit\" value=\"Submit\"> </p>\n" +
                    "</form> </body> </html>";
            printWriter.println(output);
        }
        else {
            PrintWriter printWriter2 = resp.getWriter();
            int score2 = (Integer) session.getAttribute("score");

            String outPut = "";
            outPut += outPut += "<!DOCTYPE html> <html lang=\"en\">  <head> <meta charset=\"UTF-8\"> <title>GuessNextNumber</title> </head>  " +
                    "<body>  <h1>The Number Quiz</h1> " +
                    "<p>Your current score is " + score2 + " </p>" +
                    "<p>You have completed the quiz with a score of " + score2 + " out of 5.</p>" +
                    "</body> </html>";
            printWriter2.println(outPut);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        quiz = (Quiz)session.getAttribute(currSession);
        if(quiz != null){
            int answerValue = Integer.valueOf(req.getParameter("answer"));
            int score = (Integer) session.getAttribute("score");
            int count = (Integer) session.getAttribute("count");
            if(answerValue == quiz.getAnswer(count)) score ++;
            session.setAttribute("score", score);
            resp.sendRedirect("guessNumber");
        }
    }


}
