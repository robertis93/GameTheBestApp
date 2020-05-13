package space.ivanoc.gamethebest;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SecondFile extends AppCompatActivity {


    Dialog dialog;
    Dialog dialogend;

    public int numLeft;    // переменная для левой картинки + текст
    public int numRight;
    Array array = new Array();
    Array2 array2 = new Array2();//создали новый объект из класса Аррэй
    Random random = new Random(); // для генерации случайных чисел
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondvieshkal);


        final ImageView img_left = findViewById(R.id.img_left);


        //код сгругляет углы

        img_left.setClipToOutline(true);

        final ImageView img_right = findViewById(R.id.img_right);


        //код сгругляет углы

        img_right.setClipToOutline(true);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Вызов диалогового окна в начале игры

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        //кнопка закрывает окно
        TextView btn_close = dialog.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Вернуться назад к выюору уровню
                    Intent intent = new Intent(SecondFile.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
                dialog.dismiss();
            }
        });


        //Кнопка "продолжить"
        Button btncontinue = dialog.findViewById(R.id.btn_contineu);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        //____________________________________________________________________________________________

        //Вызов диалогового окна в начале игры

        dialogend = new Dialog(this);
        dialogend.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogend.setContentView(R.layout.previewdialogend);
        dialogend.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogend.setCancelable(false);

        //кнопка закрывает окно
        TextView btn_close2 = dialogend.findViewById(R.id.btn_closeend);
        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Вернуться назад к выюору уровню
                    Intent intent = new Intent(SecondFile.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
                dialogend.dismiss();
            }
        });


        //Кнопка "продолжить"
        Button btncontinue2 = dialogend.findViewById(R.id.btn_contineuend);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogend.dismiss();
            }
        });





        //---------------------------------------------------------------------------------------------

//подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(SecondFile.this, R.anim.alpha);

        final int[] progress = {R.id.point1, R.id.point2, R.id.point3, R.id.point4,
        R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10
        };



        numLeft = random.nextInt(10);
        img_left.setImageResource(array2.images2[numLeft]);


       // numRight = random.nextInt(10);
        numRight = getRandomDoubleBetweenRange(10, 20);
        img_right.setImageResource(array.images[numRight]);



        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Условия касания картинки  - начало

                if(event.getAction()==MotionEvent.ACTION_DOWN) {

                    img_right.setEnabled(false);

                    if(numLeft>numRight) {
                        img_left.setImageResource(R.drawable.imgtrue);
                    }else {
                        img_left.setImageResource(R.drawable.imgfalse);
                    }
                    //если коснулся картинки начало

                }else if(event.getAction()==MotionEvent.ACTION_UP) {
//если отпустил палец - конец

//если отпустил палец начало
                    if(numLeft>numRight) {
                        //если левая картинка больше
                        if(count< 10) {
                            count = count +1;
                        }

//Закрашиваем прогресс серым цветом
                        for ( int i =0; i<10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i =0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else {

                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }


                        }
                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {

                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                   if(count ==10) {

                       dialogend.show();
                       //exit//если отпустил палец конец
                   }
                   else {
                       numLeft = random.nextInt(10);
                       img_left.setImageResource(array2.images2[numLeft]);
                       img_left.startAnimation(a);


                       // numRight = random.nextInt(10);
                       numRight = getRandomDoubleBetweenRange(10, 20);
                       img_right.setImageResource(array.images[numRight]);
                       img_right.startAnimation(a);

                       img_right.setEnabled(true);
                   }
                }
                return true;
            }
        });



        //___________________________________________________________________

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Условия касания картинки  - начало

                if(event.getAction()==MotionEvent.ACTION_DOWN) {

                    img_left.setEnabled(false);

                    if(numLeft<numRight) {
                        img_right.setImageResource(R.drawable.imgtrue);
                    }else {
                        img_right.setImageResource(R.drawable.imgfalse);
                    }
                    //если коснулся картинки начало

                }else if(event.getAction()==MotionEvent.ACTION_UP) {
//если отпустил палец - конец

//если отпустил палец начало
                    if(numLeft<numRight) {
                        //если левая картинка больше
                        if(count< 10) {
                            count = count +1;
                        }

//Закрашиваем прогресс серым цветом
                        for ( int i =0; i<9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i =0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else {

                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }


                        }
                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {

                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if(count ==10) {

                        dialogend.show();
                        //exit//если отпустил палец конец
                    }
                    else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array2.images2[numLeft]);
                        img_left.startAnimation(a);


                        // numRight = random.nextInt(10);
                        numRight = getRandomDoubleBetweenRange(10, 20);
                        img_right.setImageResource(array.images[numRight]);
                        img_right.startAnimation(a);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });



        //Кнопка для перехода на первый уровень
        TextView textView1 = findViewById(R.id.btn_back);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(SecondFile.this, MainActivity.class);
                    startActivity(intent); finish();
                } catch (Exception e) {

                }
            }
        });


    }

    private int getRandomDoubleBetweenRange(double min, double max) {
        double x = (Math.random()*((max-min)+1))+min;
        return (int) x;
    }


}
