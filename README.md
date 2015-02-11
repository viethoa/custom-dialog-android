CustomDialog
-----
CustomDialog is an Open Source Android library that allows developers to easily create dialog with custom animation show.
Feel free to use it all you want in your Android apps provided that you cite this project and include the license in your app.
Support Sdk Version 14 to nearly.

Feature
-----
- dialogUtils.dismiss() to dismiss this.
- dialogUtils.setCancelable() to set cancelable.
- dialogUtils.setSlideDown() to change animate Show is slide down.
- Set `null` for optional that you don't to use this.

How to use
-----
    repositories {
        maven { url 'https://oss.sonatype.org/content/groups/public' }
    }

    dependencies {
        compile 'com.github.viethoa:customdialogs:1.2.1-SNAPSHOT'
    }

    **Or**:

    Download this project and import library in there.

1. Create normal dialog (to easily custom title divide color)
-----
    <image normal dialog>

    DialogUtils dialogUtils = new DialogUtils(this, title, message, new DialogUtils.DialogListener() {

        @Override
        public void onPositiveButton(DialogInterface dialogInterface) {
            //Todo something you need
        }

        @Override
        public void onNegativeButton(DialogInterface dialogInterface) {
            //Todo something you need
        }
    });

    dialogUtils.setDivideColor(color);
    dialogUtils.setTitleColor(color);

    if (dialogUtils != null) {
        dialogUtils.show(getFragmentManager(), null);
    }

Note: 
- You can change PositiveButton's Text and NegativeButton's Text by to like:

    `DialogUtils dialogUtils = new DialogUtils(this, title, message, positiveText, neagtiveText, new DialogUtils.DialogListener()`

- If you only need a PositiveButton or NegativeButton you just make like:

    `DialogUtils dialogUtils = new DialogUtils(this, title, message, positiveText, null, new DialogUtils.DialogListener()`

    or

    `DialogUtils dialogUtils = new DialogUtils(this, title, message, null, negativeText, new DialogUtils.DialogListener()`

2. Create Custom View Dialog (This is AlertDialog Control)
-----
    <image normal dialog>

    DialogUtils dialogUtils = new DialogUtils(this, R.layout.custom_layout, null, null, null, null);
    if (dialogUtils != null) {
        dialogUtils.show(getFragmentManager(), null);
    }

3. Create simple dialog (This is Dialog Control)
-----

    Dialog simpleDialog = DialogUtils.createSimpleDialog(this, customView, true, false);
    if (simpleDialog != null && !simpleDialog.isShowing()) {
        simpleDialog.show();
    }




