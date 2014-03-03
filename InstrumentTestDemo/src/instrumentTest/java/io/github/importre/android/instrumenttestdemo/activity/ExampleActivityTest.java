package io.github.importre.android.instrumenttestdemo.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.spoon.Spoon;

import io.github.importre.android.instrumenttestdemo.R;

public class ExampleActivityTest extends ActivityInstrumentationTestCase2<ExampleActivity> {

    private ExampleActivity activity;

    public ExampleActivityTest() {
        super(ExampleActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        getInstrumentation().setInTouchMode(false);

        activity = getActivity();
        assertNotNull(activity);
    }

    public void testShouldNotBeNull() {
        TextView countTextView = (TextView) activity.findViewById(R.id.text_plus);
        assertNotNull(countTextView);

        ImageButton plusButton = (ImageButton) activity.findViewById(R.id.btn_plus);
        assertNotNull(plusButton);

        Button showActivityButton = (Button) activity.findViewById(R.id.btn_intent);
        assertNotNull(showActivityButton);

        TextView resultTextView = (TextView) activity.findViewById(R.id.text_result);
        assertNotNull(resultTextView);
    }

    public void testClickPlusButton() throws Exception {
        ImageButton plusButton = (ImageButton) activity.findViewById(R.id.btn_plus);
        TextView countTextView = (TextView) activity.findViewById(R.id.text_plus);

        int iter = 3;
        for (int i = 0; i < iter; i++) {
            TouchUtils.clickView(this, plusButton);
            Spoon.screenshot(activity, "click_plus_" + (i + 1));
        }

        int result = Integer.parseInt(countTextView.getText().toString());
        assertEquals(iter, result);
    }

    public void testShouldShowOkMessageIfResultIsOk() {
        Instrumentation inst = getInstrumentation();
        Instrumentation.ActivityResult activityResult =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        Instrumentation.ActivityMonitor monitor = inst.addMonitor(
                SubExampleActivity.class.getName(), activityResult, true);
        assertEquals(0, monitor.getHits());

        Button button = (Button) activity.findViewById(R.id.btn_intent);
        TouchUtils.clickView(this, button);
        assertEquals(1, monitor.getHits());

        TextView resultTextView = (TextView) activity.findViewById(R.id.text_result);
        Resources resources = activity.getResources();
        assertEquals(resources.getString(android.R.string.ok),
                resultTextView.getText().toString());

        inst.removeMonitor(monitor);
    }

    public void testShouldShowCancelMessageIfResultIsCancel() {
        Instrumentation inst = getInstrumentation();
        Instrumentation.ActivityResult activityResult =
                new Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null);

        Instrumentation.ActivityMonitor monitor = inst.addMonitor(
                SubExampleActivity.class.getName(), activityResult, true);

        Button button = (Button) activity.findViewById(R.id.btn_intent);
        TouchUtils.clickView(this, button);

        TextView resultTextView = (TextView) activity.findViewById(R.id.text_result);
        Resources resources = activity.getResources();
        assertEquals(resources.getString(android.R.string.cancel),
                resultTextView.getText().toString());

        inst.removeMonitor(monitor);
    }

    public void testShouldRestoreViewsWhenRecreating() {
        ImageButton plusButton = (ImageButton) activity.findViewById(R.id.btn_plus);
        TouchUtils.clickView(this, plusButton);

        CharSequence before;
        {
            TextView textView = (TextView) activity.findViewById(R.id.text_plus);
            before = textView.getText();
            Spoon.screenshot(activity, "initial_state");
            assertFalse(TextUtils.isEmpty(before));
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.recreate();
            }
        });

        CharSequence after;
        {
            TextView textView = (TextView) activity.findViewById(R.id.text_plus);
            after = textView.getText();
            Spoon.screenshot(activity, "recreated_state");
            assertFalse(TextUtils.isEmpty(after));
        }

        assertEquals(before, after);
    }
}
