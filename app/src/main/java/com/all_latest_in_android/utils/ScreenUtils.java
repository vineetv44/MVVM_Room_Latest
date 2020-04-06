package com.all_latest_in_android.utils;


import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class ScreenUtils {
    Context context;

    public ScreenUtils(Context context) {
        this.context = context;
    }

    /**
     * Returns the current screen dimensions in device independent pixels (DIP) as a {@link Point} object where
     * {@link Point#x} is the screen width and {@link Point#y} is the screen height.
     *
     * @return The current screen dimensions in DIP.
     */
    public Point getScreenDimensionsInDIP() {
        Configuration configuration = context.getResources().getConfiguration();
        return new Point(configuration.screenWidthDp, configuration.screenHeightDp);

    }

    /**
     * @return [true] if the device is in landscape orientation, [false] otherwise.
     */
    public boolean isInLandscapeOrientation() {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * @return [true] if the device has a small screen, [false] otherwise.
     */
    public boolean hasSmallScreen() {
        return getScreenSize() == Configuration.SCREENLAYOUT_SIZE_SMALL;
    }

    /**
     * @return [true] if the device has a normal screen, [false] otherwise.
     */
    public boolean hasNormalScreen() {
        return getScreenSize() == Configuration.SCREENLAYOUT_SIZE_NORMAL;
    }

    /**
     * @return [true] if the device has a large screen, [false] otherwise.
     */
    public boolean hasLargeScreen() {
        return getScreenSize() == Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * @return [true] if the device has an extra large screen, [false] otherwise.
     */
    public boolean hasXLargeScreen() {
        return getScreenSize() == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * The size of the screen, one of 4 possible values:
     * <p>
     * <ul>
     * <li>http://developer.android.com/reference/android/content/res/Configuration.html#SCREENLAYOUT_SIZE_SMALL</li>
     * <li>http://developer.android.com/reference/android/content/res/Configuration.html#SCREENLAYOUT_SIZE_NORMAL</li>
     * <li>http://developer.android.com/reference/android/content/res/Configuration.html#SCREENLAYOUT_SIZE_LARGE</li>
     * <li>http://developer.android.com/reference/android/content/res/Configuration.html#SCREENLAYOUT_SIZE_XLARGE</li>
     * </ul>
     * <p>
     * See http://developer.android.com/reference/android/content/res/Configuration.html#screenLayout for more details.
     *
     * @return The size of the screen
     */
    public int getScreenSize() {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
    }

    /**
     * The width of the screen
     *
     * @return The width of the screen
     */
    public int getScreenWidth() {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        return dm.widthPixels;
    }

    /**
     * The height of the screen
     *
     * @return The height of the screen
     */
    public int getScreenHeight() {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        return dm.heightPixels;
    }

    /**
     * The height of status bar
     *
     * @return The height of the status bar
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * Converts the given device independent pixels (DIP) value into the corresponding pixels
     * value for the current screen.
     *
     * @param dip The DIP value to convert
     * @return The pixels value for the current screen of the given DIP value.
     */
    public int convertDIPToPixels(int dip) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    /**
     * Converts the given device independent pixels (DIP) value into the corresponding pixels
     * value for the current screen.
     *
     * @param dip The DIP value to convert
     * @return The pixels value for the current screen of the given DIP value.
     */
    public int convertDIPToPixels(float dip) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    /**
     * Converts the given pixels value into the corresponding device independent pixels (DIP)
     * value for the current screen.
     *
     * @param pixels The pixels value to convert
     * @return The DIP value for the current screen of the given pixels value.
     */
    public float convertPixelsToDIP(int pixels) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, displayMetrics);
    }
}