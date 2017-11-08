package com.star.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    private TextView mQuestionTextView;
    private Button mNextButton;
    private Button mPrevButton;

    private ImageButton mPrevImageButton;
    private ImageButton mNextImageButton;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        });

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(v -> checkAnswer(true));

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(v -> checkAnswer(false));

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        });

        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + mQuestionBank.length - 1) % mQuestionBank.length;
            updateQuestion();
        });

        mNextImageButton = findViewById(R.id.next_image_button);
        mNextImageButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        });

        mPrevImageButton = findViewById(R.id.prev_image_button);
        mPrevImageButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + mQuestionBank.length - 1) % mQuestionBank.length;
            updateQuestion();
        });

        updateQuestion();
    }

    private void updateQuestion() {
        int questionResId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(questionResId);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = (userPressedTrue == answerIsTrue) ?
                R.string.correct_toast : R.string.incorrect_toast;

        Toast.makeText(QuizActivity.this, messageResId,
                Toast.LENGTH_SHORT).show();
    }
}
