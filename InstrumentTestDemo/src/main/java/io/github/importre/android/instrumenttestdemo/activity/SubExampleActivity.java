package io.github.importre.android.instrumenttestdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.github.importre.android.instrumenttestdemo.R;

public class SubExampleActivity extends Activity implements View.OnClickListener {

    public static final String NUMBER = "NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_example);
        initControllers();
        setWhatNumber();
    }

    private void initControllers() {
        Button ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);
        Button cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(this);
    }

    private void setWhatNumber() {
        TextView result = (TextView) findViewById(R.id.text_result);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        Bundle data = intent.getExtras();
        if (data == null) {
            return;
        }

        int id;
        int number = data.getInt(SubExampleActivity.NUMBER, 0);

        if (number % 2 == 1) {
            id = R.string.odd_number;
        } else {
            id = R.string.even_number;
        }
        result.setText(id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAsCancelled();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_ok:
                finishAsOk();
                break;
            case R.id.btn_cancel:
                finishAsCancelled();
                break;
            default:
                // do nothing
                break;
        }
    }

    private void finishAsOk() {
        setResult(Activity.RESULT_OK);
        finishActivity(ExampleActivity.REQUEST_RESULT);
        finish();
    }

    private void finishAsCancelled() {
        setResult(Activity.RESULT_CANCELED);
        finishActivity(ExampleActivity.REQUEST_RESULT);
        finish();
    }
}
