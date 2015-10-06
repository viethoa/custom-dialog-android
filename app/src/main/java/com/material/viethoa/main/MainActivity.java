package com.material.viethoa.main;

import android.app.Activity;
import android.app.Dialog;
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

        Button btnDialogMessage = (Button) findViewById(R.id.btn_dialog_message);
        btnDialogMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogMessage();
            }
        });

        Button btnCustomDialog = (Button) findViewById(R.id.btn_custom_dialog);
        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustomView();
            }
        });
    }


    //--------------------------------------------------------------------------------
    //  Event
    //--------------------------------------------------------------------------------

    protected void SimpleDialogMessage() {
        String title = "Simple Dialog Message";
        String message = "Here is your message can display !";
        String negativeButton = "Cancel";
        String positiveButton = "Ok";
        final Dialog myDialog = DialogUtils.createDialogMessage(this, title, message,
                negativeButton, positiveButton, false, new DialogUtils.DialogListener() {
                    @Override
                    public void onPositiveButton() {

                    }

                    @Override
                    public void onNegativeButton() {

                    }
                });

        if (myDialog != null && !myDialog.isShowing()) {
            myDialog.show();
        }
    }

    protected void DialogCustomView() {
        String title = "Simple Dialog Message";
        String negativeButton = "Cancel";
        String positiveButton = "Ok";

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.dialog_item_layout, null);

        final Dialog myDialog = DialogUtils.createCustomDialog(this, title, customView,
                negativeButton, positiveButton, false, new DialogUtils.DialogListener() {
                    @Override
                    public void onPositiveButton() {

                    }

                    @Override
                    public void onNegativeButton() {

                    }
                });

        if (myDialog != null && !myDialog.isShowing()) {
            myDialog.show();
        }
    }



}
