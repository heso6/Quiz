package pawel_l.quiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {
    @BindView(R.id.Player_name)
    protected EditText mName;
    @BindView(R.id.difficulty)
    protected Spinner mDifficulty;
    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        mPrefs = getSharedPreferences("user", MODE_PRIVATE);
        mName.setText(mPrefs.getString("username", ""));
    }
    @OnClick(R.id.next)
    protected void onNextClick(){
        // sprawdzanie czy zostalo cos wpisanie w polu imie
        String name = mName.getText().toString();
        if ( name.trim().isEmpty()){
            mName.setError("Brak nazwy gracza !");
                return;
        }
        // TODO zapamietanie nazwy gracza i poziomu trudnosci
        mPrefs.edit().putString("username", name).apply();
        // TODO Losowanie puli pytan
        // TODO otwarcie nowego ekranu

    }

}
