package io.github.importre.android.instrumenttestdemo.activity;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.spoon.Spoon;

import io.github.importre.android.instrumenttestdemo.R;

public class SubExampleActivityTest extends ActivityInstrumentationTestCase2<SubExampleActivity> {

    private SubExampleActivity activity;

    public SubExampleActivityTest() {
        super(SubExampleActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setIntentData();
        activity = getActivity();
    }

    private void setIntentData() {
        Bundle data = new Bundle();
        data.putInt(SubExampleActivity.NUMBER, 1);
        Intent intent = new Intent();
        intent.putExtras(data);
        setActivityIntent(intent);
    }

    public void testShouldNotBeNull() {
        {
            Button button = (Button) activity.findViewById(R.id.btn_ok);
            assertNotNull(button);
        }

        {
            Button button = (Button) activity.findViewById(R.id.btn_cancel);
            assertNotNull(button);
        }

        {
            View view = activity.findViewById(R.id.text_result);
            assertNotNull(view);
        }
    }

    public void testShouldFinishWhenClickingOkButton() {
        Instrumentation inst = getInstrumentation();
        Instrumentation.ActivityMonitor monitor = inst.addMonitor(
                SubExampleActivity.class.getName(), null, true);

        Button button = (Button) activity.findViewById(R.id.btn_ok);
        TouchUtils.clickView(this, button);
        assertEquals(0, monitor.getHits());

        inst.removeMonitor(monitor);
    }

    public void testShouldFinishWhenClickingCancelButton() {
        Instrumentation inst = getInstrumentation();
        Instrumentation.ActivityMonitor monitor = inst.addMonitor(
                SubExampleActivity.class.getName(), null, true);

        Button button = (Button) activity.findViewById(R.id.btn_cancel);
        TouchUtils.clickView(this, button);
        assertEquals(0, monitor.getHits());

        inst.removeMonitor(monitor);
    }

    public void testOddNumber() {
        Resources resources = activity.getResources();
        TextView result = (TextView) activity.findViewById(R.id.text_result);
        assertEquals(resources.getString(R.string.odd_number), result.getText().toString());

        Spoon.screenshot(activity, "odd_number");
    }
}
