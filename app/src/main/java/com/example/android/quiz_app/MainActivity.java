package com.example.android.quiz_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    RelativeLayout holder;
    TextView customToast;
    int score = 0;

    String givenAns2,
            givenAns1,
            givenAns6,
            givenAns3,
            givenAns4,
            givenAns5,
            givenAns7,
            givenAns8 = "",
            givenAns9 = "";

    RadioGroup ans2, ans3, ans4, ans5, ans7;
    RadioButton ans21, ans22, ans31, ans32, ans33, ans41, ans42, ans51, ans52, ans71, ans72;
    EditText ans1, ans6, userName;
    CheckBox ans81, ans82, ans83, ans84, ans91, ans92, ans93;
    Button resetButton, submitButton, ratingButton, aboutButton;
    RatingBar ratingBar;
    LinearLayout rateBlock;

    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //The name of the user
        userName = findViewById(R.id.name);
        name = userName.getText().toString();

        //The radioGroups in the xml file
        ans2 = findViewById(R.id.radioGroupe2);
        ans3 = findViewById(R.id.radioGroupe3);
        ans4 = findViewById(R.id.radioGroupe4);
        ans5 = findViewById(R.id.radioGroupe5);
        ans7 = findViewById(R.id.radioGroupe7);

        /*
         * The possible answers for the questions
         * */

        //answers to question 1
        ans1 = findViewById(R.id.correctLetter);

        //answers to question 2
        ans21 = findViewById(R.id.strawberries);
        ans22 = findViewById(R.id.lemons);


        //answers to question 3
        ans31 = findViewById(R.id.pencils90);
        ans32 = findViewById(R.id.pencils900);
        ans33 = findViewById(R.id.pencils9000);


        //answers to question 4
        ans41 = findViewById(R.id.boil);
        ans42 = findViewById(R.id.freeze);


        //answers to question 5
        ans51 = findViewById(R.id.trueMpemba);
        ans52 = findViewById(R.id.falseMpemba);

        //answers to question 6
        ans6 = findViewById(R.id.liter);

        //answers to question 7
        ans71 = findViewById(R.id.trueWater);
        ans72 = findViewById(R.id.falseWater);

        //answers to question 8
        ans81 = findViewById(R.id.uranium);
        ans82 = findViewById(R.id.gold);
        ans83 = findViewById(R.id.mercury);
        ans84 = findViewById(R.id.copper);


        //answers to question 9
        ans91 = findViewById(R.id.diamond);
        ans92 = findViewById(R.id.plastic);
        ans93 = findViewById(R.id.graphite);

        //The reset button
        resetButton = findViewById(R.id.reset);
        resetButton.setVisibility(View.INVISIBLE);
        //The submit button
        submitButton = findViewById(R.id.submit);

        //The textView that holds the link
        TextView linkTextView = (TextView) findViewById(R.id.link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        //The rating bar
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        //The rating button
        ratingButton = (Button) findViewById(R.id.rateButton);

        //The rate block
        rateBlock = findViewById(R.id.rate_block);
        rateBlock.setVisibility(View.INVISIBLE);
    }
    /*
    * Make the layout that holds the rating elements visible when the button is clicked
    *
    * */
    public void rateUs(View view) {
        rateBlock.setVisibility(View.VISIBLE);
    }
    /*
    * Show the information about the app in a alert dialog
    *
    * */
    public void about(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.about));

        builder.setMessage(getString(R.string.aboutUs));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            return;
            }
        });


        builder.create().show();
    }

/*
* Send an email that holds the rating of the user
*
* */
    public void rating(View view) {
        String star = String.valueOf(ratingBar.getRating()),
                nameOfUser = userName.getText().toString(),
                subject = getString(R.string.subject),
                rate = getString(R.string.userRating, star, nameOfUser);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, rate);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        rateBlock.setVisibility(View.INVISIBLE);

        Toast toastRate = Toast.makeText(getApplicationContext(), getString(R.string.msgAfterRating), Toast.LENGTH_SHORT);
        toastRate.show();


    }
/*
*
* Submit the results of the user
* */
    public void submit(View view) {

        name = userName.getText().toString();
        //Creating a Custom toast to show the correct answers

        holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.custom_toast, (RelativeLayout) findViewById(R.id.rell));
        customToast = (TextView) holder.findViewById(R.id.toast_text_view);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.yourScore, name));

        /*
        *
        *
        // In case the user missed a question, show this toast !
        * */
        //get the values of the radio buttons and checkboxes
        int a2 = ans2.getCheckedRadioButtonId(),
                a3 = ans3.getCheckedRadioButtonId(),
                a4 = ans4.getCheckedRadioButtonId(),
                a5 = ans5.getCheckedRadioButtonId(),
                a7 = ans7.getCheckedRadioButtonId();
        Boolean a81 = ans81.isChecked(),
                a82 = ans82.isChecked(),
                a83 = ans83.isChecked(),
                a84 = ans84.isChecked(),
                a91 = ans91.isChecked(),
                a92 = ans92.isChecked(),
                a93 = ans93.isChecked();
        String a1 = ans1.getText().toString(), a6 = ans6.getText().toString();

        if (a2 == -1 ||
                a3 == -1 ||
                a4 == -1 ||
                a5 == -1 ||
                a7 == -1 ||
                (!a81 && !a82 && !a83 && !a84) || (!a91 && !a92 && !a93) ||
                a1.equals("") || a6.equals("")) {

            Toast tForgotSomething = Toast.makeText(getApplicationContext(), getString(R.string.forgotAnAnswer), Toast.LENGTH_SHORT);
            tForgotSomething.show();
            return;
        }

        //if the user forgets to input his name
        if (name.equals("")) {

            Toast tForgotName = Toast.makeText(getApplicationContext(), getString(R.string.forgotName), Toast.LENGTH_SHORT);
            tForgotName.show();
            return;
        }

        //define the message that will be shown in the dialog
        String finalResult = String.valueOf(score());
        finalResult += "\n\n" + results();
        builder.setMessage(finalResult);

        builder.setPositiveButton(getString(R.string.correctAns), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //show the correct answers
                customToast.setText(rightAnswers());
                customToast.setTextSize(16);
                Toast t = new Toast(getApplicationContext());
                t.setGravity(Gravity.CENTER, 0, 0);
                t.setDuration(Toast.LENGTH_LONG);
                t.setView(holder);
                t.show();

                //make the reset visible
                resetButton.setVisibility(View.VISIBLE);


            }
        }).setNegativeButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //try again


                Toast Try = Toast.makeText(getApplicationContext(), getString(R.string.tryAgainMsg), Toast.LENGTH_SHORT);
                Try.show();

                tryAgain();
                //Make the submit button VISIBLE
                submitButton.setVisibility(View.VISIBLE);

            }
        });


        builder.create().show();

        //Make the submit button invisible
        submitButton.setVisibility(View.INVISIBLE);

    }

    /*
     *
     * Reset Button
     *
     * */
    public void reset(View view) {
        tryAgain();
        submitButton.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
    }

    /*
     *
     * The correct answers to show in a toast
     * */
    public String rightAnswers() {
        //Correct Answers of the questions
        String answers = "1." + getString(R.string.answer1);
        answers += "\n2." + getString(R.string.answer2);
        answers += "\n3." + getString(R.string.answer3);
        answers += "\n4." + getString(R.string.answer4);
        answers += "\n5." + getString(R.string.answer5);
        answers += "\n6." + getString(R.string.answer6);
        answers += "\n7." + getString(R.string.answer7);
        answers += "\n8." + getString(R.string.answer8);
        answers += "\n9." + getString(R.string.answer9);

        return answers;
    }

    /*
     *
     * Reset all the answers and try again
     * */
    public void tryAgain() {
        //Reset question 1
        ans1.setText("");
        //Reset question 6
        ans6.setText("");
        //Reset question 2
        ans2.clearCheck();
        //Reset question 3
        ans3.clearCheck();
        //Reset question 4
        ans4.clearCheck();
        //Reset question 5
        ans5.clearCheck();
        //Reset question 7
        ans7.clearCheck();
        //Reset question 8
        ans81.setChecked(false);
        ans82.setChecked(false);
        ans83.setChecked(false);
        ans84.setChecked(false);
        //Reset question 8
        ans91.setChecked(false);
        ans92.setChecked(false);
        ans93.setChecked(false);

        //Reset the score
        score = 0;

        //Reset the given answers
        givenAns1 = "";
        givenAns2 = "";
        givenAns3 = "";
        givenAns4 = "";
        givenAns5 = "";
        givenAns6 = "";
        givenAns7 = "";
        givenAns8 = "";
        givenAns9 = "";

        //Clear the input of the name
        userName.setText("");

    }


    //    /*
//     *
//     * The answers given by the user
//     * */
    public String results() {

        //the answer given to question 1:
        givenAns1 = ans1.getText().toString();

        //the answer given to question 2 :
        if (ans21.isChecked()) {
            givenAns2 = ans21.getText().toString();
        } else if (ans22.isChecked()) {
            givenAns2 = ans22.getText().toString();
        }

        //the answer given to question 3 :
        if (ans31.isChecked()) {
            givenAns3 = ans31.getText().toString();
        } else if (ans32.isChecked()) {
            givenAns3 = ans32.getText().toString();
        } else if (ans33.isChecked()) {
            givenAns3 = ans33.getText().toString();
        }

        //the answer given to question 4 :
        if (ans41.isChecked()) {
            givenAns4 = ans41.getText().toString();
        } else if (ans42.isChecked()) {
            givenAns4 = ans42.getText().toString();
        }


        //the answer given to question 5 :
        if (ans51.isChecked()) {
            givenAns5 = ans51.getText().toString();
        } else if (ans52.isChecked()) {
            givenAns5 = ans52.getText().toString();
        }

        //the answer to question 6 :
        givenAns6 = ans6.getText().toString();

        //the answer given to question 7:
        if (ans71.isChecked()) {
            givenAns7 = ans71.getText().toString();
        } else if (ans72.isChecked()) {
            givenAns7 = ans72.getText().toString();
        }

        //the answer given to question 8 :
        if (ans81.isChecked()) {
            givenAns8 = ans81.getText().toString() + ", ";
        }
        if (ans82.isChecked()) {
            givenAns8 += ans82.getText().toString() + ", ";
        }
        if (ans83.isChecked()) {
            givenAns8 += ans83.getText().toString() + ", ";
        }
        if (ans84.isChecked()) {
            givenAns8 += ans84.getText().toString();
        }

        //the answer given to question 9:
        if (ans91.isChecked()) {
            givenAns9 = ans91.getText().toString() + ", ";
        }
        if (ans92.isChecked()) {
            givenAns9 += ans92.getText().toString() + ", ";
        }
        if (ans93.isChecked()) {
            givenAns9 += ans93.getText().toString();
        }
        String resultMsg;

        resultMsg = getString(R.string.message) + "\n";
        resultMsg += "1. " + givenAns1 + "\n";
        resultMsg += "2. " + givenAns2 + "\n";
        resultMsg += "3. " + givenAns3 + "\n";
        resultMsg += "4. " + givenAns4 + "\n";
        resultMsg += "5. " + givenAns5 + "\n";
        resultMsg += "6. " + givenAns6 + "\n";
        resultMsg += "7. " + givenAns7 + "\n";
        resultMsg += "8. " + givenAns8 + "\n";
        resultMsg += "9. " + givenAns9;

        return resultMsg;

    }


    /*
     * Calculate the score of the user
     * */
    public int score() {
            /*
//     * The correct answers of the quiz
//     *
//     * */

        if (ans1.getText().toString().equals("j") || ans1.getText().toString().equals("J")) {
            score += 1;
        }
        if (ans22.isChecked()) {
            score += 1;
        }
        if (ans33.isChecked()) {
            score += 1;
        }
        if (ans41.isChecked()) {
            score += 1;
        }
        if (ans51.isChecked()) {
            score += 1;
        }
        if (ans6.getText().toString().equals("<")) {
            score += 1;
        }
        if (ans71.isChecked()) {
            score += 1;
        }
        if (ans82.isChecked() && ans84.isChecked()) {
            score += 1;
        }
        if (ans91.isChecked() && ans93.isChecked()) {
            score += 1;
        }

        return score;
    }



}
