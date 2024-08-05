import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion{
     String question;
     String[] options;
     int correctAnswerIndex;

     QuizQuestion(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

     String getQuestion() {
        return question;
    }

     String[] getOptions() {
        return options;
    }

    int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

     boolean isCorrectAnswer(int index) {
        return index == correctAnswerIndex;
    }
}
 class Quiz {
 QuizQuestion[] questions;
 int score;
  int currentQuestionIndex;
 Scanner scanner;

     Quiz(QuizQuestion[] questions) {
        this.questions = questions;
        score = 0;
        currentQuestionIndex = 0;
        scanner = new Scanner(System.in);
    }

     void start() {
        while (currentQuestionIndex < questions.length) {
            QuizQuestion question = questions[currentQuestionIndex];
            System.out.println("\n" + question.getQuestion());

            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            boolean answered = askQuestionWithTimer();
            if (!answered) {
                System.out.println("Time is up! Moving to the next question.");
            }

            currentQuestionIndex++;
        }

        displayResult();
    }

 boolean askQuestionWithTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
            }
        };

        timer.schedule(task, 10000); // 10 seconds timer

        System.out.print("Enter your choice (1-" + questions[currentQuestionIndex].getOptions().length + "): ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            timer.cancel();
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }

        if (choice >= 1 && choice <= questions[currentQuestionIndex].getOptions().length) {
            if (questions[currentQuestionIndex].isCorrectAnswer(choice - 1)) {
                score++;
            }
            return true;
        }
        return false;
    }

     void displayResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
    }
}
public class QuizOperator{
    public static void main(String[] args) {
        QuizQuestion[] questions = {
            new QuizQuestion(
                "What is the capital of INDIA?",
                new String[]{"MUMBAI", "CHENNAI", "NEW DELHI", "UTTARAKHAND"},
                2
            ),
            new QuizQuestion(
                "What is the largest state in india?",
                new String[]{"Punjab", "Uttar Pradesh", "Tamil Nadu", "Assam"},
                1
            ),
            new QuizQuestion(
                "Who is Sachin Tendulkar?",
                new String[]{"Cricketer", "President", "Judge", "Minister"},
                0
            )
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
