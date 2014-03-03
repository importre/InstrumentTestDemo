package io.github.importre.android.instrumenttestdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import io.github.importre.android.instrumenttestdemo.R;

public class ExampleActivity extends Activity implements View.OnClickListener {

    public static final int REQUEST_RESULT = 0;
    private static final String KEY_COUNT = "COUNT";

    private TextView mCountTextView;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        initControllers();
        initCountTextView(savedInstanceState);
    }

    private void initControllers() {
        ImageButton plusButton = (ImageButton) findViewById(R.id.btn_plus);
        plusButton.setOnClickListener(this);
        Button showActivityButton = (Button) findViewById(R.id.btn_intent);
        showActivityButton.setOnClickListener(this);

        mCountTextView = (TextView) findViewById(R.id.text_plus);
        mResultTextView = (TextView) findViewById(R.id.text_result);
    }

    private void initCountTextView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String count = savedInstanceState.getString(ExampleActivity.KEY_COUNT, "");
            mCountTextView.setText(count);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ExampleActivity.KEY_COUNT, mCountTextView.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_plus:
                increaseCount();
                break;
            case R.id.btn_intent:
                showSubExampleActivity();
                break;
            default:
                // do nothing
                break;
        }
    }

    private void increaseCount() {
        String s = mCountTextView.getText().toString();
        int count = 1;
        if (!TextUtils.isEmpty(s)) {
            count = Integer.parseInt(s) + 1;
        }
        mCountTextView.setText(count + "");
    }

    private void showSubExampleActivity() {
        Intent intent = new Intent(this, SubExampleActivity.class);
        Bundle data = new Bundle();
        int number = 0;
        String str = mCountTextView.getText().toString();
        if (!TextUtils.isEmpty(str)) {
            number = Integer.parseInt(str);
        }
        data.putInt(SubExampleActivity.NUMBER, number);
        intent.putExtras(data);
        startActivityForResult(intent, ExampleActivity.REQUEST_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ExampleActivity.REQUEST_RESULT:
                setResultTextView(resultCode);
                break;
            default:
                // do nothing
                break;
        }
    }

    private void setResultTextView(int resultCode) {
        switch (resultCode) {
            case Activity.RESULT_OK:
                mResultTextView.setText(android.R.string.ok);
                break;
            case Activity.RESULT_CANCELED:
                mResultTextView.setText(android.R.string.cancel);
                break;
            default:
                mResultTextView.setText(null);
                break;
        }
    }
}
