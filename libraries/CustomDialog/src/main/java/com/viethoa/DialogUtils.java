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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DialogUtils extends DialogFragment {

    private String titlePositiveButton;
    private String titleNegativeButton;
    private View customView;
    public String dialog_title;
    private Context context;
    private String message;

    private static boolean isCenter = false;
    private static boolean isSlideUp;
    private Number titleColor;
    private Number divideColor;

    private AlertDialog.Builder builder;
    private DialogListener callBack;

    /**
     * Dialog Callback
     */
    public static abstract class DialogListener {
        public abstract void onPositiveButton();

        public abstract void onNegativeButton();
    }

    /**
     * Setup dialog
     */
    public DialogUtils(Context context, View customView, String negativeButton, String positiveButton, String title, DialogListener callBack) {
        this.context = context;
        this.callBack = callBack;
        this.dialog_title = title;
        this.customView = customView;
        this.titlePositiveButton = positiveButton;
        this.titleNegativeButton = negativeButton;

        initialiseDialog();
        initialiseCustom();
    }

    public DialogUtils(Context context, String title, String message, String positive, String negative, DialogListener callBack) {
        this.context = context;
        this.dialog_title = title;
        this.message = message;
        this.callBack = callBack;
        this.titleNegativeButton = negative;
        this.titlePositiveButton = positive;

        initialiseDialog();
        initialiseCustom();
    }

    public DialogUtils(Context context, String title, String message, DialogListener callBack) {
        this.context = context;
        this.dialog_title = title;
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
     * Event for user custom dialog
     */
    public void setCenterTitle(boolean isCenter) {
        this.isCenter = isCenter;
    }

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
    public static Dialog createSimpleDialog(Context activity, View customView, boolean cancelable) {
        if (activity == null || customView == null) {
            Log.d("DialogUtil", "activity or layout is null");
            return null;
        }

        Dialog dialog = new Dialog(activity, R.style.SimpleDialog);
        dialog.setContentView(customView);
        dialog.setCancelable(cancelable);

        return dialog;
    }




    /**
     * New Simple dialog message
     */
    public static Dialog createCustomDialog(Context activity, String title, View customView, String cancelButton,
                                            String doneButton, boolean cancelable, final DialogListener listener) {
        return createDialog(activity, title, null, customView, cancelButton, doneButton, cancelable, listener);
    }

    public static Dialog createDialogMessage(Context activity, String title, String message, String cancelButton,
                                             String doneButton, boolean cancelable, final DialogListener listener) {
        return createDialog(activity, title, message, null, cancelButton, doneButton, cancelable, listener);
    }

    private static Dialog createDialog(Context activity, String title, String message, View customView,
                                             String cancelButton, String doneButton, boolean cancelable,
                                             final DialogListener listener) {
        if (activity == null)
            return null;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myCustomView = inflater.inflate(R.layout.dialog_message_layout, null);
        TextView tvTitle = (TextView) myCustomView.findViewById(R.id.tv_title);
        TextView tvMessage = (TextView) myCustomView.findViewById(R.id.tv_message);
        Button btnNegative = (Button) myCustomView.findViewById(R.id.btn_negative);
        Button btnPositive = (Button) myCustomView.findViewById(R.id.btn_positive);
        ViewGroup dialogContent = (ViewGroup) myCustomView.findViewById(R.id.dialog_content);

        final Dialog dialog = DialogUtils.createSimpleDialog(activity, myCustomView, cancelable);

        //Define my dialog width
        int width = DeviceUtils.getDeviceScreenWidth(activity) - 100;
        ViewGroup.LayoutParams params = myCustomView.getLayoutParams();
        params.width = width;
        myCustomView.setLayoutParams(params);

        //Title
        if (title != null && !title.equals("")) {
            tvTitle.setText(title);
        }

        //Message
        boolean isDialogMessage = (message != null && !message.equals(""));
        tvMessage.setText(isDialogMessage ? message : "");
        tvMessage.setVisibility(isDialogMessage ? View.VISIBLE : View.GONE);

        //Custom View
        if (customView != null && !isDialogMessage) {
            dialogContent.addView(customView);
        }

        //Negative button
        if (cancelButton != null && !cancelButton.equals("")) {
            btnNegative.setText(cancelButton);
            btnNegative.setVisibility(View.VISIBLE);
            btnNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (listener != null) {
                        listener.onNegativeButton();
                    }
                }
            });
        }
        //Positive button
        if (doneButton != null && !doneButton.equals("")) {
            btnPositive.setText(doneButton);
            btnPositive.setVisibility(View.VISIBLE);
            btnPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (listener != null) {
                        listener.onPositiveButton();
                    }
                }
            });
        }

        return dialog;
    }




    /**
     * dummy menu dialog :D
     */
    public static Dialog createMenuDialog(Activity activity, View customView, int X, int Y) {
        if (activity == null || customView == null) {
            Log.d("DialogUtil", "activity or layout is null");
            return null;
        }

        Dialog dummyMenuDialog = new Dialog(activity, R.style.app_setting_dialog);
        dummyMenuDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = dummyMenuDialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wmlp.x = X;
        wmlp.y = Y;

        dummyMenuDialog.setContentView(customView);
        dummyMenuDialog.setCancelable(true);

        return dummyMenuDialog;
    }


    /**
     * Setup Custom view Dialog
     */
    private AlertDialog.Builder CustomViewDialog() {
        if (builder == null) {
            Log.e("DialogUtil", "context is null");
            return null;
        }

        //Optional custom view
        if (customView != null) {
            builder.setView(customView);
        } else if (customView != null && message != null) {
            Log.e("DialogUtil", "message has no effect when using custom dialogView");
        }
        //Optional message
        else if (message != null && message.length() > 0) {
            builder.setMessage(message);
        }

        //Optional title
        if (dialog_title != null && dialog_title.length() > 0)
            builder.setTitle(dialog_title);

        //Optional positive button
        if (callBack != null && titlePositiveButton != null && titlePositiveButton.length() > 0)
            builder.setPositiveButton(titlePositiveButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callBack != null)
                        callBack.onPositiveButton();
                }
            });

        //Optional negative button
        if (callBack != null && titleNegativeButton != null && titleNegativeButton.length() > 0)
            builder.setNegativeButton(titleNegativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callBack != null)
                        callBack.onNegativeButton();
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
     * Custom animation show
     */
    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);

        //if (isSlideUp == true) {
        //    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlideUp;
        //} else {
        //    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlideDown;
        //}
    }

    /**
     * Custom dialog show
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
                if (isCenter) {
                    tv.setGravity(Gravity.CENTER);
                }
            }
        }
    }


}
