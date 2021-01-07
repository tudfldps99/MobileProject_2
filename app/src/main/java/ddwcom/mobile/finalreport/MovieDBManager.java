package ddwcom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieDBManager {

    MovieDBHelper movieDBHelper;

    public MovieDBManager(Context context) {
        movieDBHelper = new MovieDBHelper(context);
    }

    //DB의 모든 movie를 반환
    public ArrayList<Movie> getAllMovie() {
        ArrayList movieList = new ArrayList();

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID));
            int img = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_IMG));
            String name = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_NAME));
            String actor = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTOR));
            String director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String grade = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_GRADE));
            String opening = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_OPENING));

            movieList.add ( new Movie (id, img, name, actor, director, grade, opening));
        }
        cursor.close();
        movieDBHelper.close();
        return movieList;
    }

    //DB에 새로운 movie 추가
    public boolean addNewMovie (Movie newMovie) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(MovieDBHelper.COL_NAME, newMovie.getName());
        value.put(MovieDBHelper.COL_ACTOR, newMovie.getActor());
        value.put(MovieDBHelper.COL_DIRECTOR, newMovie.getDirector());
        value.put(MovieDBHelper.COL_GRADE, newMovie.getGrade());
        value.put(MovieDBHelper.COL_OPENING, newMovie.getOpening());

        long count = db.insert(MovieDBHelper.TABLE_NAME, null, value);

        movieDBHelper.close();
        if (count > 0) return true;

        return false;
    }

    //_id를 기준으로 movie 수정
    public boolean modifyMovie(Movie movie) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(MovieDBHelper.COL_NAME, movie.getName());
        row.put(MovieDBHelper.COL_ACTOR, movie.getActor());
        row.put(MovieDBHelper.COL_DIRECTOR, movie.getDirector());
        row.put(MovieDBHelper.COL_GRADE, movie.getGrade());
        row.put(MovieDBHelper.COL_OPENING, movie.getOpening());

        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(movie.get_id())};

        int result = sqLiteDatabase.update(MovieDBHelper.TABLE_NAME, row, whereClause, whereArgs);

        movieDBHelper.close();
        if (result > 0) return true;

        return false;
    }

    //_id를 기준으로 movie 삭제
    public boolean removeMovie(String name) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String whereClause = MovieDBHelper.COL_NAME + "=?";
        String[] whereArgs = new String[] {name};
        int result = sqLiteDatabase.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);

        movieDBHelper.close();
        if (result > 0) return true;

        return false;
    }
}
