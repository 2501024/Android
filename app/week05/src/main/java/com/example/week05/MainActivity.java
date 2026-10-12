package com.example.week05;

import static android.widget.Toast.LENGTH_SHORT;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("앱을 종료하시겠습니까?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("일반 Dialog");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "예 알겠습니다", LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "취소 버튼을 눌렀습니다", LENGTH_SHORT).show();
                    }
                });

                builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "확인을 했습니다", LENGTH_SHORT).show();

                    }
                });
                builder.setCancelable(false);
                builder.create();
                builder.show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String temp = year + "년 ";
                        temp += (month + 1) + "월 ";
                        temp += (dayOfMonth) + "일";
                        Toast.makeText(getBaseContext(), temp, LENGTH_SHORT).show();
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, dateSetListener, year, month, day);
                dialog.create();
                dialog.show();


            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getBaseContext(), hourOfDay + ":" + minute, LENGTH_SHORT).show();
                    }
                };

                TimePickerDialog dialog = new TimePickerDialog(MainActivity.this, timeSetListener, hour, minute, false);
                dialog.create();
                dialog.show();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_birthday);
                Button ok = dialog.findViewById(R.id.button52);
                Button cancel = dialog.findViewById(R.id.button51);
                NumberPicker numberPicker = dialog.findViewById(R.id.numberPicker);
                numberPicker.setMaxValue(year - 15);
                numberPicker.setMinValue(year - 80);
                numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
                numberPicker.setWrapSelectorWheel(false);
                numberPicker.setValue(year - 20);
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.create();
                dialog.show();
            }

        });

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String options = "035167924ACBEFD";
                CharacterPickerDialog dialog =
                        new CharacterPickerDialog(MainActivity.this, new View(
                                MainActivity.this), null, options, false) {
                            @Override
                            public void onClick(View v) {
                                super.onClick(v);
                                Toast.makeText(getBaseContext(), "Selected " + ((Button) v).getText().toString(), LENGTH_SHORT ).show();
                                dismiss();
                            }
                        };

                dialog.create();
                dialog.show();
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("공지");
                progressDialog.setMessage("잠시만 기다려주세요!\n 프로그램이 로딩중입니다\n중지하시겠습니까?");
                progressDialog.setButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog.dismiss();
                        Toast.makeText(getBaseContext(), "중지하였습니다", LENGTH_SHORT).show();
                    }
                });

                progressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                progressDialog.create();
                progressDialog.show();
            }
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("Loading....");
                progressDialog.setMax(200);
                progressDialog.getWindow().setDimAmount(0);
                progressDialog.create();
                progressDialog.show();
                for (int i = 1; i < 10; i++)
                    progressDialog.setProgress(i * 10);
            }
        });

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog(MainActivity.this);
                dialog.setCancelable(false);
                dialog.create();
                dialog.show();
            }
        });

    }
}