package com.all_latest_in_android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

public class KeyboardUtil implements ViewTreeObserver.OnGlobalLayoutListener {
    private View decorView;
    private View contentView;
    public KeyboardUtil(Activity act) {
        FrameLayout content = act.findViewById(android.R.id.content);
        this.decorView = act.getWindow().getDecorView();
        this.contentView = content.getChildAt(0);
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /**
     * Helper to hide the keyboard
     *
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        }
    }

    public void enable() {
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void disable() {
        decorView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    public static void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!view.hasFocus()) {
            view.requestFocus();
        }
        inputMethodManager.showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    @Override
    public void onGlobalLayout() {
        Rect r = new Rect();
        //r will be populated with the coordinates of your view that area still visible.
        decorView.getWindowVisibleDisplayFrame(r);
        //get screen height and calculate the difference with the usable area from the r
        int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
        int diff = height - r.bottom;
        //if it could be a keyboard add the padding to the view
        if (diff != 0) {
            // if the use-able screen height differs from the total screen height we assume that it shows a keyboard now
            //check if the padding is 0 (if yes set the padding for the keyboard)
            if (contentView.getPaddingBottom() != diff) {
                //set the padding of the contentView for the keyboard
                contentView.setPadding(0, 0, 0, diff);
            }
        } else {
            //check if the padding is != 0 (if yes reset the padding)
            if (contentView.getPaddingBottom() != 0) {
                //reset the padding of the contentView
                contentView.setPadding(0, 0, 0, 0);
            }
        }
    }
}