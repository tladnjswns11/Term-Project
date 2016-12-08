package com.example.b10720.scheduleapplication;


import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class D_Fragment extends Fragment {

    private MyDBHelper helper;
    private ListView d_ListView;
    private ArrayAdapter<String> d_Adapter;

    public D_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.d_layout, container, false);
    }


    @Override
    public void onStart() {//이 메소드가 호출되면 화면의 모든 UI가 만들어진 지고 호출이 된다. UI설정은 이 메소드안에서 해야한다.
        super.onStart();

        // Android에서 제공하는 string 문자열 하나를 출력 가능한 layout으로 어댑터 생성
        d_Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);

        // Xml에서 추가한 ListView 연결
        d_ListView = (ListView) getView().findViewById(R.id.dlistview);

        // ListView에 어댑터 연결
        d_ListView.setAdapter(d_Adapter);

        // ListView 아이템 터치 시 이벤트 추가
        d_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // 이벤트 발생 시 해당 아이템 위치의 텍스트를 출력
                Toast.makeText(getActivity(), d_Adapter.getItem(arg2), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Viewschedule.class);
                intent.putExtra("key", "Day");
                intent.putExtra("id", String.valueOf(arg2+1));
                startActivity(intent);
            }
        });

        // SQLLITE 사용 ListView에 아이템 추가
        helper = new MyDBHelper(getActivity());

        String sql2 = "SELECT * FROM dschedule";
        Cursor cursor = helper.getReadableDatabase().rawQuery(sql2, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append(cursor.getString(2) + "\t\t\t\t");
            buffer.append(cursor.getString(1));
            d_Adapter.add(buffer.toString());
            buffer.setLength(0);
        }
    }

}
