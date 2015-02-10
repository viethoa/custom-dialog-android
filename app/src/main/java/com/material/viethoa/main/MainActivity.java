package com.material.viethoa.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.viethoa.DialogUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //--------------------------------------------------------------------------------
        // Alert dialog
        Button btnDialogUp = (Button) findViewById(R.id.btnDialogUp);
        btnDialogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnUpClick();
            }
        });

        Button btnDialogDown = (Button) findViewById(R.id.btnDialogDown);
        btnDialogDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnDownClick();
            }
        });

        //--------------------------------------------------------------------------------
        // Dialog
        Button btnSimpleDialogUp = (Button) findViewById(R.id.btnSimpleDialogUp);
        btnSimpleDialogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogUp();
            }
        });

        Button btnSimpleDialogDown = (Button) findViewById(R.id.btnSimpleDialogDown);
        btnSimpleDialogDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogDown();
            }
        });
    }


    //--------------------------------------------------------------------------------
    //  Event
    //--------------------------------------------------------------------------------

    protected void SimpleDialogUp() {
        View customView = LayoutInflater.from(this).inflate(R.layout.dialog_custom_layout, null);

        Dialog simpleDialog = DialogUtils.createSimpleDialog(this, customView, true, true);
        if (simpleDialog != null && !simpleDialog.isShowing()) {
            simpleDialog.show();
        }
    }

    protected void SimpleDialogDown() {
        View customView = LayoutInflater.from(this).inflate(R.layout.dialog_custom_layout, null);

        Dialog simpleDialog = DialogUtils.createSimpleDialog(this, customView, true, false);
        if (simpleDialog != null && !simpleDialog.isShowing()) {
            simpleDialog.show();
        }
    }

    protected void BtnUpClick() {
        DialogUtils dialogUtils = new DialogUtils(this, R.layout.alert_dialog_custom_layout, null, null, null, null);
        dialogUtils.setDivideColor(R.color.app_primary_color);
        dialogUtils.setTitleColor(R.color.app_primary_color);

        if (dialogUtils != null) {
            dialogUtils.show(getFragmentManager(), null);
        }
    }

    protected void BtnDownClick() {
        DialogUtils dialogUtils = new DialogUtils(this, getString(R.string.dialog_title), getString(R.string.dialog_message),
                new DialogUtils.DialogListener() {
            @Override
            public void onPositiveButton(DialogInterface dialogInterface) {
                //Todo something you need
            }

            @Override
            public void onNegativeButton(DialogInterface dialogInterface) {
                //Todo something you need
            }
        });

        dialogUtils.setSlideDown();
        dialogUtils.setDivideColor(R.color.app_primary_color);
        dialogUtils.setTitleColor(R.color.app_primary_color);

        if (dialogUtils != null) {
            dialogUtils.show(getFragmentManager(), null);
        }
    }



}
