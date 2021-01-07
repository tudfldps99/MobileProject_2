//과제명: 영화 정보 관련 앱
//분반: 02분반
//학번: 20180970 성명: 박승연
//제출일: 2020년 07월 03일

package ddwcom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int UPDATE_CODE = 100;
    final int REQ_CODE = 200;

    MovieDBManager movieDBManager;
    private ArrayList<Movie> movieList;
    private ListView listView;
    private MyAdapter myAdapter;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDBManager = new MovieDBManager(this);
        movieList = movieDBManager.getAllMovie();

        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, movieList);
        listView = findViewById(R.id.customlistView);

        listView.setAdapter(myAdapter);

        //수정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movieList.get(position);
                intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("movie", movie);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        //삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("영화 삭제")
                        .setMessage(movieList.get(pos).getName()+ " 영화를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean result = movieDBManager.removeMovie(movieList.get(pos).getName());

                                if (result) {
                                    movieList.clear();
                                    movieList.addAll(movieDBManager.getAllMovie());
                                    myAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieList.clear();
        movieList.addAll(movieDBManager.getAllMovie());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                break;
            case R.id.menu_developer:
                setContentView(R.layout.activity_developer);
                break;
            case R.id.menu_finish:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    String movie = data.getStringExtra("movie");
                    Toast.makeText(this, movie + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {
            switch(resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "영화 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}