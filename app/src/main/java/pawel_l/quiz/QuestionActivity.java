package pawel_l.quiz;

import android.content.DialogInterface;
import android.os.Handler;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends AppCompatActivity {

    @BindView(R.id.question_text)
    protected TextView mTitle;
    @BindView(R.id.answers)
    protected RadioGroup mAnswers;
    @BindViews({R.id.answer_a, R.id.answer_b, R.id.answer_c})
    protected List<RadioButton> mAnswersButtons;


    private int mCurrentQuestion = 0;
    private List<Question> mQuestions;
    private int[] mAnswersArray;

    private boolean mFirstBackClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        mQuestions = (List<Question>) getIntent().getSerializableExtra("questions");
        mAnswersArray = new int[mQuestions.size()];
        refreshQuestionView();
    }

    @Override
    public void onBackPressed() {
        onBackTapped();
    }

    private void onBackTapped() {
        // pierwsze klikniecie
        if (!mFirstBackClicked) {
            //Ustawic flage na true
            mFirstBackClicked = true;
            // pokazac Toast
            Toast.makeText(this, "Kliknij ponownie, aby wyjsc!!", Toast.LENGTH_SHORT).show();
            // uruchomi odliczanie (1-2sek) i po tym czasie ustawic flage ponownie na false
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mFirstBackClicked = false;
                }
            }, 2000);
        } else { //drugie klikniecie ( w ciagu 1-2sek)
            // zamknac okno Activity
            finish();
        }

    }

    private void refreshQuestionView() {
        mAnswers.clearCheck();
        Question q1 = mQuestions.get(mCurrentQuestion);
        mTitle.setText(q1.getContent());
        for (int i = 0; i < 3; i++) {
            mAnswersButtons.get(i).setText(q1.getAnswers().get(i));
        }
        // sprawdz czy dla danego pytania zostala udzielona odpowiedz
        if (mAnswersArray[mCurrentQuestion] > 0) {
            // jezeli tak to zaznacz wybrana !
            mAnswers.check(mAnswersArray[mCurrentQuestion]);
        }

    }

    @OnClick(R.id.btn_back)
    protected void onBackClick() {
        if (mCurrentQuestion == 0) {
            // Ustawienie podwojnego klikniecia aby wyjsc na buttonie "wroc"
            // jesli chcielibysmy aby button "wroc" odrazu wychodzil musimy
            // zamienic onBackTapped na finish();
            onBackTapped();
            return;
        }// zapisanie udzielonej odpowiedzi na aktualne pytanie
        mAnswersArray[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        mCurrentQuestion--;
        refreshQuestionView();
    }

    @OnClick(R.id.btn_next)
    protected void onNextClick() {
        // Zapisanie udzielonej odpowiedzi na aktualne  pytanie
        mAnswersArray[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        if (mCurrentQuestion == mQuestions.size() - 1) {
            int correctAnswers = countCorrectAnswer();
            int totalAnswers = mAnswersArray.length;
            displayResults(correctAnswers, totalAnswers);
            return;
        }
        //Sprawdzamy czy uzytkownik wybral cokolwiek (getCheckedButtonId zwroci cos innego niz -1)
        if (mAnswersArray[mCurrentQuestion] == -1) {
            // jezeli nie to wyswietlamy komunikat i zatrzymujemy przejscie dalej (return)
            Toast.makeText(this, "Odpowiedz na pytanie!!", Toast.LENGTH_SHORT).show();
            return;
        }
        mCurrentQuestion++;
        refreshQuestionView();
    }

    private void displayResults(int correctAnswers, int totalAnswers) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("wynik quizu")
                .setCancelable(false)
                .setMessage("odpowiedzialem poprawnie na " + correctAnswers + "pytan z " + totalAnswers)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create();
        dialog.show();

    }

    private int countCorrectAnswer() {
        int sum = 0;

        for (int i = 0; i < mQuestions.size(); i++) {
            Question question = mQuestions.get(i);
            int userAnswerId = mAnswersArray[i];
            int correctAnswerId = mAnswersButtons.get(question.getCorrectAnswer()).getId();
            if (userAnswerId == correctAnswerId) {
                sum++;
            }
        }

        return sum;
    }

}