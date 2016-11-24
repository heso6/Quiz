package pawel_l.quiz;

import java.util.List;

/**
 * Created by gosc on 23.11.2016.
 */

public interface QuestionsDatabase {

    List<Question> getQuestions(int difficulty);


}
