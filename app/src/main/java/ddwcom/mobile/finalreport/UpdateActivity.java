package ddwcom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    Movie movie;

    EditText etName;
    EditText etActor;
    EditText etDirector;
    EditText etGrade;
    EditText etOpening;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        movie = (Movie) getIntent().getSerializableExtra("movie");

        etName = findViewById(R.id.et_movie_name);
        etActor = findViewById(R.id.et_movie_actor);
        etDirector = findViewById(R.id.et_movie_director);
        etGrade = findViewById(R.id.et_movie_grade);
        etOpening = findViewById(R.id.et_movie_opening);

        etName.setText(movie.getName());
        etActor.setText(movie.getActor());
        etDirector.setText(movie.getDirector());
        etGrade.setText(movie.getGrade());
        etOpening.setText(String.valueOf(movie.getOpening()));

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_update:
                if (etName.getText().toString().length() != 0 && etActor.getText().toString().length() != 0 && etDirector.getText().toString().length() != 0) {
                String name = etName.getText().toString();
                String actor = etActor.getText().toString();
                String director = etDirector.getText().toString();
                String grade = etGrade.getText().toString();
                String opening = etOpening.getText().toString();

                movie.setName(name);
                movie.setActor(actor);
                movie.setDirector(director);
                movie.setGrade(grade);
                movie.setOpening(opening);

                boolean result = movieDBManager.modifyMovie(movie);

                if (result) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("movie", name);
                    setResult(RESULT_OK, resultIntent);
                }
                }else {
                    Toast.makeText(this, "항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
