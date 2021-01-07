package ddwcom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "Movie.db";
    public final static String TABLE_NAME= "movie_table";

    public final static String COL_ID = "_id";
    public final static String COL_NAME = "name";
    public final static String COL_ACTOR = "actor";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_GRADE = "grade";
    public final static String COL_OPENING = "opening";
    public final static String COL_IMG = "img";

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_IMG + " INTEGER, " + COL_NAME + " TEXT, " + COL_ACTOR + " TEXT, " + COL_DIRECTOR + " TEXT, " + COL_GRADE + " TEXT, " + COL_OPENING + " TEXT)";
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (null, '"+R.mipmap.movie_bohemian+"', '보헤미안랩소디', '라미 말렉', '브라이언 싱어', '9.45', '20181031');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '"+R.mipmap.movie_extreme+"', '극한직업', '류승룡', '이병헌', '9.20', '20190123');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '"+R.mipmap.movie_money+"', '돈', '류준열', '박누리', '8.39', '20190320');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '"+R.mipmap.movie_parasite+"', '기생충', '송강호', '봉준호', '9.07', '20180530');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '"+R.mipmap.movie_exit+"', '엑시트', '조정석', '이상근', '8.99', '20180731');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
