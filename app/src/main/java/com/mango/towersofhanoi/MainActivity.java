package com.mango.towersofhanoi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    ImageView form1to3, form3to1, form2to3, form2to1, form1to2, form3to2;
    LinearLayout layout11, layout12, layout13;
    Stack<View> Tower1discs = new Stack<>();
    Stack<View> Tower2discs = new Stack<>();
    Stack<View> Tower3discs = new Stack<>();
    View tower1, tower2, tower3, temp;
    Boolean IsMoving = Boolean.FALSE;
    Button startSim,resetBtn;
    TextView diskMoveCountText;
    int ArrowStart = 0;
    int countMove=0;
    int timee= 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diskMoveCountText=findViewById(R.id.textmove);

        layout11 = findViewById(R.id.layout1);
        layout12 = findViewById(R.id.layout2);
        layout13 = findViewById(R.id.layout3);

        tower1 = findViewById(R.id.Tower1);
        tower2 = findViewById(R.id.Tower2);
        tower3 = findViewById(R.id.Tower3);

        startSim = findViewById(R.id.btnStart);
        resetBtn = findViewById(R.id.btnReset);

        form1to3 = findViewById(R.id.imgfrom1to3);
        form3to1 = findViewById(R.id.imgfrom3to1);
        form2to3 = findViewById(R.id.imgfrom2to3);
        form2to1 = findViewById(R.id.imgfrom2to1);
        form1to2 = findViewById(R.id.imgfrom1to2);
        form3to2 = findViewById(R.id.imgfrom3to2);


        Tower1discs.push((View) findViewById(R.id.dis4));
        Tower1discs.push((View) findViewById(R.id.dis3));
        Tower1discs.push((View) findViewById(R.id.dis2));
        Tower1discs.push((View) findViewById(R.id.dis1));
        Tower1discs.push((View) findViewById(R.id.dis0));

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finishAffinity();
            }
        });

        startSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hanoi(5, 1, 3, 2);




            }
        });



        layout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tower1Clicked();
            }
        });

        layout12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tower2Clicked();
            }
        });
        layout13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tower3Clicked();
            }
        });
    }

    void makeNormalTower() {
        tower3.setBackgroundColor(Color.parseColor("#000000"));
        tower2.setBackgroundColor(Color.parseColor("#000000"));
        tower1.setBackgroundColor(Color.parseColor("#000000"));
    }

    void allArrowsGone() {

        form1to3.setVisibility(View.GONE);
        form3to1.setVisibility(View.GONE);
        form2to3.setVisibility(View.GONE);
        form2to1.setVisibility(View.GONE);
        form1to2.setVisibility(View.GONE);
        form3to2.setVisibility(View.GONE);
    }

    void arrowVisible(int a, int b) {
        if (a == 1 && b == 3) form1to3.setVisibility(View.VISIBLE);
        if (a == 3 && b == 1) form3to1.setVisibility(View.VISIBLE);
        if (a == 2 && b == 3) form2to3.setVisibility(View.VISIBLE);
        if (a == 2 && b == 1) form2to1.setVisibility(View.VISIBLE);
        if (a == 1 && b == 2) form1to2.setVisibility(View.VISIBLE);
        if (a == 3 && b == 2) form3to2.setVisibility(View.VISIBLE);
    }

    void hanoi(int disk, int source, int dest, int aux) {

        if(disk ==1){
            autoClick(source,dest);
        }
        else {
            hanoi(disk - 1, source, aux, dest);
            autoClick(source,dest);
            hanoi(disk - 1, aux, dest, source);
        }

    }

    void tower1Clicked() {
        if (!IsMoving) {
            allArrowsGone();
            tower1.setBackgroundColor(Color.parseColor("#FFFF00"));
            tower2.setBackgroundColor(Color.parseColor("#000000"));
            tower3.setBackgroundColor(Color.parseColor("#000000"));
            if (Tower1discs.empty()) return;
            temp = Tower1discs.pop();
            layout11.removeView(temp);
            IsMoving = Boolean.TRUE;
            ArrowStart = 1;
        } else {
            arrowVisible(ArrowStart, 1);
            layout11.addView(temp, 0);
            Tower1discs.push(temp);
            makeNormalTower();
            IsMoving = Boolean.FALSE;
        }
    }


    void tower2Clicked() {
        if (!IsMoving) {
            allArrowsGone();
            tower2.setBackgroundColor(Color.parseColor("#FFFF00"));
            tower1.setBackgroundColor(Color.parseColor("#000000"));
            tower3.setBackgroundColor(Color.parseColor("#000000"));
            if (Tower2discs.empty()) return;
            temp = Tower2discs.pop();
            layout12.removeView(temp);
            IsMoving = Boolean.TRUE;
            ArrowStart = 2;
        } else {
            arrowVisible(ArrowStart, 2);
            layout12.addView(temp, 0);
            Tower2discs.push(temp);
            makeNormalTower();
            IsMoving = Boolean.FALSE;

        }
    }


    void tower3Clicked() {
        if (!IsMoving) {
            allArrowsGone();
            tower3.setBackgroundColor(Color.parseColor("#FFFF00"));
            tower2.setBackgroundColor(Color.parseColor("#000000"));
            tower1.setBackgroundColor(Color.parseColor("#000000"));
            if (Tower3discs.empty()) return;
            temp = Tower3discs.pop();
            layout13.removeView(temp);
            IsMoving = Boolean.TRUE;
            ArrowStart = 3;
        } else {
            arrowVisible(ArrowStart, 3);
            layout13.addView(temp, 0);
            Tower3discs.push(temp);
            makeNormalTower();
            IsMoving = Boolean.FALSE;
        }
    }

    void autoClick(int a, int b) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //add your code here
                countMove++;
                diskMoveCountText.setText("Total Moves:"+ (countMove));
        switch (a) {
            case 1:
                tower1Clicked();
                break;
            case 2:
                tower2Clicked();

                break;
            case 3:
                tower3Clicked();

                break;
            default:
                break;
        }
        switch (b) {
            case 1:
                tower1Clicked();
                break;
            case 2:
                tower2Clicked();

                break;
            case 3:
                tower3Clicked();

                break;
            default:
                break;
        }

            }


        }, timee);
        timee=timee+500;
    }
    void move3Disk() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                        autoClick(1,3);

                    }
                }, 1000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                        autoClick(1,2);

                    }
                }, 1500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                        autoClick(3,2);

                    }
                }, 2000);
//
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                        autoClick(1,3);

                    }
                }, 2500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                        autoClick(2,1);

                    }
                }, 3000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                        autoClick(2,3);

                    }
                }, 3500);

               new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                        autoClick(1,3);

                    }
                }, 4000);

    }
}