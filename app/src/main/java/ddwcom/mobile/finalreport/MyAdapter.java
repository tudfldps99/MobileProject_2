package ddwcom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Movie> myDataList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, int layout, ArrayList<Movie> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {     //전체 원본 데이터의 개수 반환
        return myDataList.size();
    }

    @Override
    public Object getItem(int position) {   //특정 위치의 데이터 항목 반환
        return myDataList.get(position);
    }

    @Override
    public long getItemId(int position) {   //특정 위치의 데이터 항목 아이디 반환
        return myDataList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        //ImageView img = convertView.findViewById(R.id.movie_img);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvActor = convertView.findViewById(R.id.tvActor);
        TextView tvDirector = convertView.findViewById(R.id.tvDirector);

        //img.setId(myDataList.get(position).getImg());
        tvName.setText(myDataList.get(position).getName());
        tvActor.setText(myDataList.get(position).getActor());
        tvDirector.setText(myDataList.get(position).getDirector());


        return convertView;
    }
}
