package ddwcom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    ImageView addImg;
    EditText etName;
    EditText etActor;
    EditText etDirector;
    EditText etGrade;
    EditText etOpening;

    Button btn;
    CalendarView calView;
    TextView tvYear, tvMonth, tvDay;
    int selectYear, selectMonth, selectDay;
    String text;

    RatingBar ratingBar;
    TextView textV;
    String star;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addImg = findViewById(R.id.add_img);
        etName = findViewById(R.id.et_movie_name);
        etActor = findViewById(R.id.et_movie_actor);
        etDirector = findViewById(R.id.et_movie_director);
        //etGrade = findViewById(R.id.et_movie_grade);
        //etOpening = findViewById(R.id.et_movie_opening);

        btn = findViewById(R.id.button);
        calView = findViewById(R.id.calendarView);
        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDay = findViewById(R.id.tvDay);

        ratingBar = findViewById(R.id.ratingBar);
        textV = findViewById(R.id.textV);

        movieDBManager = new MovieDBManager(this);

        //평점
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textV.setText(""+rating);
                star = String.valueOf(rating);
            }
        });


        //calendar 위젯 사용.
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month + 1;
                selectDay = dayOfMonth;

                String y = String.valueOf(selectYear);
                String m = String.valueOf(selectMonth);
                String d = String.valueOf(selectDay);

                if (selectMonth <= 9) {
                    m = "0" + m;
                }
                if (selectDay <= 9) {
                    d = "0" + d;
                }

                text = y + m + d;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvYear.setText(Integer.toString(selectYear));
                tvMonth.setText(Integer.toString(selectMonth));
                tvDay.setText(Integer.toString(selectDay));
            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                if (etName.getText().toString().length() != 0 && etActor.getText().toString().length() != 0 && etDirector.getText().toString().length() != 0) {
                    boolean result = movieDBManager.addNewMovie(
                            new Movie(addImg.getId(), etName.getText().toString(), etActor.getText().toString(), etDirector.getText().toString(), star, text));

                    if (result) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("movie", etName.getText().toString());
                        setResult(RESULT_OK, resultIntent);
                    }
                    finish();
                } else {
                        Toast.makeText(this, "항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
