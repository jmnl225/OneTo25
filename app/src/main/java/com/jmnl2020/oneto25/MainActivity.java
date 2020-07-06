package com.jmnl2020.oneto25;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btnRetry;

    Button[] btns= new Button[25]; // Button참조변수 25개짜리 배열 객체

    //현재 눌러야할 번호를 갖고있는 int형 변수
    int cnt=1;

    //버튼의 배경그림을 참조하는 참조변수
    Drawable btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id를 이용해서 xml에서 만든 view객체들 참조변수에 대입하기.
        tv= findViewById(R.id.tv);
        btnRetry= findViewById(R.id.btn_retry); //변수 안에있는 숫자값을 줌

        for(int i=0; i<btns.length; i++){
            btns[i]= findViewById(R.id.btn01+i);
        }


        //버튼의 배경그림 가져오기
        btnBack = btns[0].getBackground();

        //우선, 버튼들에서 보여줄 글씨 설정.
//        //각 버튼에 1~25중 하나의 숫자가 랜덤하도록 설정(중복XXXXXX)
//
// //       Random rnd= new Random(); 랜덤 없어도 댐
//
//        //1~25의 숫자를 순서대로 가진 arraylist 만들기
//        ArrayList<Integer> arrayList= new ArrayList<>();
//        for(int i=1; i<=25; i++){
//            arrayList.add(i);
//        }
//
//        //arrayList의 요소들을 뒤섞기!
//        Collections.shuffle(arrayList);
//
//        for(int i=0; i<btns.length; i++){
////            int n= rnd.nextInt(25)+1;
////            btns[i].setText(n+"");
//            btns[i].setText( arrayList.get(i) +"" );
//        }
        initial();

        //버튼을 눌렀을 때 반응하기. -> 리스너 추가
        //해야하는데..!! 그거 말고~
        //xml에서 button을 눌렀을 때 Activity의 특별한 method를 자동으로 발동하도록 하는
        //*속*성* 추가!



    }//onCreatMethod end.

    //Retry 버튼 눌렀을 때 반응하는 메소드
    public void clickRetry(View v){
        //초기화하는 기능 메소드 호출
        cnt = 1;
        initial();
    }




    //onClick 속성이 부여된 버튼이 클릭되면 자동으로 실행되는 메소드 (생성자?)
    // ***규칙 ****
    //1. return 타입이 반드시 void여야 함.
    //2. 파라미터로 View 객체 하나는 받아야함.
    //3. 접근제한자 == public
    public void clickBtn(View v){



        //글씨가 아닐 때 해결하는 방법
        //버튼에 저장되어 있는 값(Tag)를 읽어와서,
        String s= v.getTag().toString();
        int num= Integer.parseInt(s);



 //       tv.setText("Click!");
        //눌러진 버튼v 에 써있는 글씨를 얻어오기
        Button bt= (Button)v; //view v는 자신이 버튼인 지 모름 따라서 v. 으로 했을 때 getText 가동이 안 됨.
        //따라서, Button을 참조하는 변수를 만들자! Button bt= v;
        //그런데 v가 자신이 버튼이 아니라고 믿음!! 따라서 (Button) 형변환 시켜줌

        //====================
 //       String s =bt.getText().toString();
        //위에서 만든 버튼 참조변수에게 받은 값getText를 문자열로 바꿔줌.
        //그것을 스트링에 대입.

        //얻어온 글씨의 숫자가 현재 눌러야 할 번호(cnt)와 같은지 비교!
        //얻어온 글씨를 숫자로 변경.

 //       int num= Integer.parseInt(s); //얻어온 글자의 숫자 변수!
        //=========================

        if(num== cnt){
            //현재 눌러야 할 번호를 정확히 누른 상황!

 //           bt.setText("OK");
 //           bt.setTextColor(Color.RED);
            bt.setVisibility(View.INVISIBLE);
 //           bt.setBackgroundColor(Color.TRANSPARENT);

            //눌러야할 번호를 증가
            cnt++;

            //안내 글씨 TextView에 현재 눌러야 할 번호 설정하기
            tv.setText(cnt+"");
            //cnt 는 int형 이기때문에 TextView에 들어갈땐 꼭!! +"" 해주기!!

            //모든 번호가 끝났을 경우!
            if(cnt>25){
                tv.setText("Stage Clear!");
                tv.setTextColor(Color.GREEN);

                //재시작버튼을 활성화
                btnRetry.setEnabled(true);

            }

        }

    }

    //게임의 초기설정 기능 메소드
    void initial(){
        // retry 눌렀을 때 !!! 현재 눌러야할 번호 초기화


        //각 버튼에 1~25중 하나의 숫자가 랜덤하도록 설정(중복XXXXXX)

        //       Random rnd= new Random(); 랜덤 없어도 댐

        //1~25의 숫자를 순서대로 가진 arraylist 만들기
        ArrayList<Integer> arrayList= new ArrayList<>();
        for(int i=1; i<=25; i++){
            arrayList.add(i);
        }

        //arrayList의 요소들을 뒤섞기!
        Collections.shuffle(arrayList);

        for(int i=0; i<btns.length; i++){
//            int n= rnd.nextInt(25)+1;
//            btns[i].setText(n+"");
            btns[i].setText( arrayList.get(i) +"" );
            btns[i].setVisibility(View.VISIBLE);
            btns[i].setBackground(btnBack);

            //버튼 객체에 버튼이 보여주고 있는 글씨값을 Tag로 저장
            btns[i].setTag(arrayList.get(i));

        }



    }

}//Main Activity class end.
