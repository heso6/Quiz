package pawel_l.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {
    @BindView(R.id.Player_name)
    protected EditText mName;
    @BindView(R.id.difficulty)
    protected Spinner mDifficulty;

    private UserPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        mPrefs = new UserPreferences(this);
        mName.setText(mPrefs.getUsername());
        mDifficulty.setSelection(mPrefs.getLevel());

    }

    @OnClick(R.id.next)
    protected void onNextClick() {
        // sprawdzanie czy zostalo cos wpisanie w polu imie
        String name = mName.getText().toString();
        if (name.trim().isEmpty()) {
            mName.setError("Brak nazwy gracza !");
            return;
        }

        int selectedLevel = mDifficulty.getSelectedItemPosition();
        if (selectedLevel == 0) {
            Toast.makeText(this, "Wybierz poziom trudnosci!", Toast.LENGTH_LONG).show();
            return;
        }

        //zapamietanie nazwy gracza i poziomu trudnosci
        mPrefs.setUsername(name);
        mPrefs.setLevel(selectedLevel);

        // TODO Losowanie puli pytan
        QuestionsDatabase db = null;
        List<Question> questions = db.getQuestions(selectedLevel);
        Random random = new Random();
        while (questions.size() > 5 ){
            // Usuwamy losowe pytania az zostanie nam ich tylko 5
            questions.remove(random.nextInt(questions.size()));
        }
        // przetasowujemy kolekcje pozostalych pytan aby kolejnosc rowniez byla losowa
        Collections.shuffle(questions);

        //TODO przekazanie lub zapisanie wylosowanych pytan na potrzeby nastepnego  ekranu

        // otwarcie nowego ekranu
        Intent questionIntent = new Intent(this, QuestionActivity.class);
        startActivity(questionIntent);
    }

}
