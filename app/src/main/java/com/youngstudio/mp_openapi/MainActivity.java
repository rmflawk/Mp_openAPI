package com.youngstudio.mp_openapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

//    public static int i=0;
    int n=2;

    String apiKey="";
    EditText et,et2;
    ListView listView;
    ArrayAdapter adapter;
    TextView tv;

    double lat1,lng1;

    ArrayList<String> items= new ArrayList<>();
    String[] s = new String[4];


//    public void plus(){
//        i++;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(i==0){
//            Intent intent= new Intent(this,Main2Activity.class);
//            //intent.putExtra("av", Context);
//            startActivity(intent);
//            finish();
//            i++;
//        }

        listView= findViewById(R.id.listview);
        adapter= new ArrayAdapter(this, R.layout.listview_item, items);
        listView.setAdapter(adapter);
        et= findViewById(R.id.et);
        et2= findViewById(R.id.et2);
        tv= findViewById(R.id.tv);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String s = items.get(i);

                String ss = s.substring(s.lastIndexOf("x")+3);

                String s2= ss.substring(0, 8);//y

                String sss = s.substring(s.lastIndexOf("표")+1);

                String s3 =sss.substring(0, 7);//x

                Toast.makeText(MainActivity.this,s2 + " \n" + s3,Toast.LENGTH_SHORT).show();

//                lat1= Double.parseDouble(s2);
//                lng1= Double.parseDouble(s3);
                lat1= Double.parseDouble(s3);
                lng1= Double.parseDouble(s2);

                Intent intent= new Intent();
                intent.setAction(Intent.ACTION_VIEW);

                //지도좌표값 데이터 정보
                Uri uri= Uri.parse("geo:"+ lat1 + ","+lng1+"?z=16"+"&q="+lat1+","+lng1);// 끝에 (aa)라고 하면 마커에 이름찍힘
                intent.setData(uri);

                startActivity(intent);


            }
        });


    }


    public void clickBtn(View view) {
        //네트워크를 통해서 xml문서를 읽어오기..
        new Thread(){
            @Override
            public void run() {

                items.clear();

                String name= et.getText().toString();
                String sido =11+"";

                if(name.equals("서울"))sido=11+"";//
                if(name.equals("부산"))sido=21+"";//
                if(name.equals("대구"))sido=23+"";//
                if(name.equals("인천"))sido=22+"";//
                if(name.equals("광주"))sido=24+"";//
                if(name.equals("대전"))sido=25+"";//
                if(name.equals("울산"))sido=26+"";//
                if(name.equals("경기"))sido=31+"";//
                if(name.equals("강원"))sido=32+"";//
                if(name.equals("충북"))sido=33+"";//
                if(name.equals("충남"))sido=34+"";//
                if(name.equals("전북"))sido=35+"";//
                if(name.equals("전남"))sido=36+"";//
                if(name.equals("경북"))sido=37+"";//
                if(name.equals("경남"))sido=38+"";//
                if(name.equals("제주"))sido=39+"";//
                if(name.equals("세종"))sido=41+"";//


                String s="수정";

                if(et2.getText().toString()==null){
                    s = "수정";
                }else{
                    s= et2.getText().toString();
                }

                Date date = new Date();
                date.setTime(date.getTime()- (1000*60*60*24));
                SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
                String dateStr=sdf.format(date);

                String address="http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList"
                        +"?serviceKey=c7nO3weEBxC%2FwHOcCZC2mHzxigp4xQ4jAHdYTZasP7Za4Ld85Z22pshueeopKpVLEfvNJlw1SNzsz2CCjViGWA%3D%3D"
                        +"&pageNo=1"
                        +"&numOfRows=40"
                        +"&sidoCd="
                        //+"21"
                        +sido
                        +"0000"
                        +"&yadmNm="
                        +s;
                        //+"&yadmNm=%EC%88%98%EC%A0%95";
                try {
                    //해임달객체 생성
                    URL url= new URL(address);

                    //무지개로드 열기
                    InputStream is= url.openStream(); //바이트스트림
                    //문자스트림으로 변환
                    InputStreamReader isr= new InputStreamReader(is);

                    //읽어들인 XML문서를 분석(parse)해주는 객체 생성
                    XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                    XmlPullParser xpp= factory.newPullParser();
                    xpp.setInput(isr);

                    //xpp를 이용해서 xml문서를 분석
                    int eventType= xpp.getEventType();

                    String tagName;

                    StringBuffer buffer= null;

                    String[] ss = new String[30];

                    while(eventType!=XmlPullParser.END_DOCUMENT){
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                xpp.getName();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "파싱시작!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case XmlPullParser.START_TAG:
                                tagName= xpp.getName();

                                if(tagName.equals("item")){

                                    buffer= new StringBuffer();

                                }else if(tagName.equals("yadmNm")){
                                    //buffer.append(xpp.getText()+"\n");
                                    xpp.next();
                                    buffer.insert(0,xpp.getText()+"\n");

                                }else if(tagName.equals("telno")){
                                    xpp.next();
                                    buffer.append("전화번호 : "  + xpp.getText()+"\n");

//                                }else if(tagName.equals("estbDd")){
////                                    xpp.next();
////                                    buffer.append("창립년도 : " + xpp.getText().toString().substring(0,4)+"년 \n");
////                                    //String z= "창립년도 : " + xpp.getText().toString().substring(0,4)+"년 \n";
////                                    //buffer.append(z);
////                                    //buffer.insert(4,z);

                                }else if(tagName.equals("addr")){
                                    xpp.next();
                                    buffer.append("주소 : " + xpp.getText()+"\n");
                                }else if(tagName.equals("XPos")) {
                                    xpp.next();
                                    lat1= Double.parseDouble(xpp.getText());
                                    //buffer.append("x좌표" + lat1+"\n");
                                }else if(tagName.equals("YPos")){
                                    xpp.next();
                                    lng1= Double.parseDouble(xpp.getText());
                                    //buffer.append("y좌표" + lng1+"\n");
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                tagName= xpp.getName();
                                if( tagName.equals("item")){

                                        items.add(buffer.toString());

                                        //리스트뷰 갱신
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                adapter.notifyDataSetChanged();
                                            }
                                        });
                                }
                                break;
                        }//switch
                        eventType= xpp.next();
                    }//while
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "파싱종료!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (MalformedURLException e) {e.printStackTrace();
                } catch (IOException e) {e.printStackTrace();
                } catch (XmlPullParserException e) {e.printStackTrace();}
            }//run method
        }.start();
    }//clackBtn

    public void clickBtn2(View view) {

        // 주소 --> 좌표 (지오코딩)
        String addr= et2.getText().toString();

        //지오코딩 작업을 수행하는 객체 생성
        Geocoder geocoder= new Geocoder(this, Locale.KOREA);

        //지오코더에게 지오코딩작업 요청
        try {
            List<Address> addresses= geocoder.getFromLocationName(addr, 3);

            StringBuffer buffer= new StringBuffer();
            for(Address t : addresses){
                buffer.append( t.getLatitude()+", "+ t.getLongitude()+"\n");
            }

            //대표 좌표값(첫번째 결과)의 위도와 경도
            lat1= addresses.get(0).getLatitude();
            lng1= addresses.get(0).getLongitude();


            //다이얼로그로 좌표들 보여주기
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage(buffer.toString()).setPositiveButton("OK", null).create().show();


        } catch (IOException e) {
            Toast.makeText(this, "검색 실패", Toast.LENGTH_SHORT).show();
        }
    }


    public void clickBtn3(View view) {
//        startActivity(new Intent(this, SecondActivity.class));
//        Intent intent=new Intent(this,Main2Activity.class);
//        startActivity(intent);

        //지도 앱 실행
        Intent intent= new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        //지도좌표값 데이터 정보
        Uri uri= Uri.parse("geo:"+ lat1 + ","+lng1+"?z=16"+"&q="+lat1+","+lng1);// 끝에 (aa)라고 하면 마커에 이름찍힘
        intent.setData(uri);

        startActivity(intent);
    }


}//Mainactivity
