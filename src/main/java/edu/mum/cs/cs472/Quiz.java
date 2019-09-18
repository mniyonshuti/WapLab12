package edu.mum.cs.cs472;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {
    private static List<String > questionList = new ArrayList<>();
    private static List<Integer> answerList = new ArrayList<>();

    public Quiz() {
        method();
    }

    public static void method(){
        int count = 0;
        Random rand = new Random();
        int random = rand.nextInt(5);

        while (count < 5){
            for(int i = random; i < 5; i++){
                if(count == 5) break;
                if(i == 4) random = 0;
                questionList.add(questions[i]);
                answerList.add(answers[i]);
                count++;
            }
        }
    }
    private static String[] questions = {
            "3, 1, 4, 1, 5", //pi
            "1, 1, 2, 3, 5", //fibonacci
            "1, 4, 9, 16, 25", //squares
            "2, 3, 5, 7, 11", //primes
            "1, 2, 4, 8, 16" //powers of 2
    };
    private static int[] answers = {9, 8, 36, 13, 32};

    public String getQuestion(int i){
        return questionList.get(i);
    }

    public Integer getAnswer(int i){
        return answerList.get(i);
    }

    public boolean checkAnswer(int answer, int i){
        if(answer == getAnswer(i)) return true;
        return false;
    }
}
