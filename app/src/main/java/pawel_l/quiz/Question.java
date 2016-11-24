package pawel_l.quiz;

import java.security.PrivateKey;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

/**
 * Created by gosc on 23.11.2016.
 */

public class Question {
    
    private String content;
    private int difficulty;
    private List<String> answers;
    private int correctAnswer;

    public Question(String content, int correctAnswer, List<String> answers, int difficulty) {
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.difficulty = difficulty;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getanswers() {
        return answers;
    }

    public void setanswers(List<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
