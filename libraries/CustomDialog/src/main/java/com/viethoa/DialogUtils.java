package com.viethoa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DialogUtils extends DialogFragment {

    private String titlePositiveButton;
    private String titleNegativeButton;
    private Number layoutResourceId;
    private String title;
    private Context context;
    private String message;

    private static boolean isSlideUp;
    private Number titleColor;
    private Number divideColor;

    private AlertDialog.Builder builder;
    private DialogListener callBack;

    /**
     * Setup dialog
     */
    public DialogUtils(Context context, int layoutResourceId, String negativeButton, String positiveButton, String title, DialogListener callBack) {
        this.context = context;
        this.callBack = callBack;
        this.title = title;
        this.titlePositiveButton = positiveButton;
        this.layoutResourceId = layoutResourceId;
        this.titleNegativeButton = negativeButton;

        initialiseDialog();
        initialiseCustom();
    }

    public DialogUtils(Context context, String title, String message, String positive, String negative, DialogListener callBack) {
        this.context = context;
        this.title = title;
        this.message = message;
        this.callBack = callBack;
        this.titleNegativeButton = negative;
        this.titlePositiveButton = positive;

        initialiseDialog();
        initialiseCustom();
    }

    public DialogUtils(Context context, String title, String message, DialogListener callBack) {
        this.context = context;
        this.title = title;
        this.message = message;
        this.callBack = callBack;
        this.titleNegativeButton = "Cancel";
        this.titlePositiveButton = "Ok";

        initialiseDialog();
        initialiseCustom();
    }

    protected void initialiseDialog() {
        if (context == null) {
            Log.e("DialogUtil", "context is null");
            return;
        }

        this.builder = new AlertDialog.Builder(context);
        this.builder.setCancelable(false);
    }

    protected void initialiseCustom() {
        //Default is slide up
        this.isSlideUp = true;

        //Default divide color
        this.divideColor = null;

        //Default title color
        this.titleColor = null;
    }

    /**
     *  Event for user custom dialog
     */
    public void setSlideUp() {
        this.isSlideUp = true;
    }

    public void setSlideDown() {
        this.isSlideUp = false;
    }

    public void setCancelable(boolean bool) {
        this.builder.setCancelable(bool);
    }

    public void setDivideColor(int color) {
        this.divideColor = color;
    }

    public void setTitleColor(int color) {
        this.titleColor = color;
    }

    /**
     * Simple dialog
     */
    public static Dialog createSimpleDialog(Activity activity, View customView, boolean cancelable, boolean isSlideUp) {
        if (activity == null || customView == null) {
            Log.d("DialogUtil", "activity or layout is null");
            return null;
        }

        Dialog dialog = new Dialog(activity, R.style.SimpleDialog);
        dialog.setContentView(customView);
        dialog.setCancelable(cancelable);

        if (isSlideUp == true) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlideUp;
        } else {
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlideDown;
        }

        return dialog;
    }

    /**
     * Custom view Dialog
     */
    private AlertDialog.Builder CustomViewDialog() {
        if (builder == null) {
            Log.e("DialogUtil", "context is null");
            return null;
        }

        //Optional custom view
        if (layoutResourceId != null) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View dialogCustomView = inflater.inflate(layoutResourceId.intValue(), null);

            if (dialogCustomView != null)
                builder.setView(dialogCustomView);
        } else if (layoutResourceId != null && message != null) {
            Log.e("DialogUtil", "message has no effect when using custom dialogView");
        }
        //Optional message
        else if (message != null && message.length() > 0) {
            builder.setMessage(message);
        }

        //Optional title
        if (title != null && title.length() > 0)
            builder.setTitle(title);

        //Optional message
        if (message != null && message.length() > 0)
            builder.setTitle(message);

        //Optional positive button
        if (callBack != null && titlePositiveButton != null && titlePositiveButton.length() > 0)
            builder.setPositiveButton(titlePositiveButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callBack != null)
                        callBack.onPositiveButton(dialogInterface);
                }
            });

        //Optional negative button
        if (callBack != null && titleNegativeButton != null && titleNegativeButton.length() > 0)
            builder.setNegativeButton(titleNegativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callBack != null)
                        callBack.onNegativeButton(dialogInterface);
                }
            });

        return builder;
    }

    /**
     * Create custom dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.builder = CustomViewDialog();
        return builder.create();
    }

    /**
     *  Custom animation show
     */
    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);

        if (isSlideUp == true) {
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlideUp;
        } else {
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlideDown;
        }
    }

    /**
     *  Custom dialog show
     */
    @Override
    public void onResume() {
        super.onResume();

        if (divideColor != null) {
            Dialog dialog = getDialog();
            int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider = dialog.findViewById(dividerId);

            if (divider != null) {
                divider.setBackgroundColor(getResources().getColor(divideColor.intValue()));
            }
        }

        if (titleColor != null) {
            Dialog dialog = getDialog();
            int textViewId = dialog.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
            TextView tv = (TextView) dialog.findViewById(textViewId);

            if (tv != null) {
                tv.setTextColor(getResources().getColor(titleColor.intValue()));
            }
        }
    }

    /**
     * Callback interface
     */
    public static abstract class DialogListener {
        public abstract void onPositiveButton(DialogInterface dialogInterface);

        public abstract void onNegativeButton(DialogInterface dialogInterface);
    }
}
