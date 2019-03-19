package com.example.boyraztalha.quizapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private  int presscounter =0;
    private  int maxpresscounter =4;
    private String[] keys = {"R","D","B","E","I"};
    private String textAnswer = "BIRD";
    TextView textmaintitle, textsubtitle, textquestion;
    LinearLayout linearLayout;
    EditText editText;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearlayout);
        editText = findViewById(R.id.textedit);
        animation = AnimationUtils.loadAnimation(this,R.anim.bigtosmall);

        keys = shuffleArray(keys);
        for (String key : keys){
            addview(linearLayout,key, editText);
        }
    }

    private void addview(LinearLayout linearLayout, final String key, final EditText editText) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.rightMargin = 30;

        final TextView textView = new TextView(this);
        textView.setLayoutParams(layoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorpurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(key);
        textView.setTextSize(32);
        textView.setClickable(true);
        textView.setFocusable(true);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOneRegular.ttf");

        textmaintitle = findViewById(R.id.textmaintitle);
        textsubtitle = findViewById(R.id.textsubtitle);
        textquestion = findViewById(R.id.textquestion);

        textmaintitle.setTypeface(typeface);
        textsubtitle.setTypeface(typeface);
        textquestion.setTypeface(typeface);
        textView.setTypeface(typeface);
        editText.setTypeface(typeface);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presscounter < maxpresscounter){
                    if (presscounter == 0){
                        editText.setText("");
                    }

                    editText.setText(editText.getText().toString() + key);
                    textView.setAnimation(animation);
                    textView.animate().alpha(0).setDuration(300);
                    presscounter++;

                    if (presscounter == maxpresscounter){
                        doValidate();
                    }
                }
            }
        });

        linearLayout.addView(textView);
    }

    private void doValidate() {
        presscounter =0;
        keys = shuffleArray(keys);

        if (editText.getText().toString().equals(textAnswer)){
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }else{
            editText.setText("");
        }
        linearLayout.removeAllViews();

        for (String key :keys){
            addview(linearLayout,key,editText);
        }

    }

    private String[] shuffleArray(String[] keys) {
        String temp;
        Random random = new Random();
        int index;
        for (int i=1; i<keys.length-1;i++){
            index = random.nextInt(i+1);
            temp = keys[index];
            keys[index] = keys[i];
            keys[i] = temp;
        }
        return keys;
    }
}
