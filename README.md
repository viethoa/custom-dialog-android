CustomDialog
-----
CustomDialog is an Open Source Android library that allows developers to easily create dialog.
Feel free to use it all you want in your Android apps provided that you cite this project and include the license in your app.
Support Sdk Version 14 to lastest.

Feature
-----
- Auto dismiss dialog in Negative or Positive button clicked.
- Simple to set cancelable.
- Set `null` for optional that you don't to use this.

![](https://github.com/viethoa/image-repositories/blob/master/custom_dialog.gif "DynamicListView")

How to use
-----
    repositories {
        maven { url 'https://oss.sonatype.org/content/groups/public' }
    }

    dependencies {
        compile 'com.github.viethoa:customdialogs:3.0.0'
    }

    **Or**:

    Download this project and import library in there.

1. Create simple dialog message
-----
    protected void SimpleDialogMessage() {
        String title = "Simple Dialog Message";
        String message = "Here is your message can display !";
        String negativeButton = "Cancel";
        String positiveButton = "Ok";
        Dialog myDialog = DialogUtils.createDialogMessage(this, title, message,
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

2. Create Custom View Dialog within Negative and Positive button
-----
	protected void DialogCustomView() {
        String title = "Simple Dialog Message";
        String negativeButton = "Cancel";
        String positiveButton = "Ok";

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.dialog_item_layout, null);

        Dialog myDialog = DialogUtils.createCustomDialog(this, title, customView,
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

3. Create Custom View Dialog without Negative and Positive button
-----
    Dialog simpleDialog = DialogUtils.createSimpleDialog(this, customView, true, false);
    if (simpleDialog != null && !simpleDialog.isShowing()) {
        simpleDialog.show();
    }

Note: 
- You can change PositiveButton's Text and NegativeButton's Text by to like:

    `DialogUtils.createCustomDialog(this, title, customView, negativeText, positiveText, false, new DialogUtils.DialogListener()`

- If you only need a PositiveButton or NegativeButton you just make like:

    `DialogUtils.createCustomDialog(this, title, customView, null, positiveButton, false, new DialogUtils.DialogListener()`

    or

    `DialogUtils.createCustomDialog(this, title, customView, negativeButton, null, false, new DialogUtils.DialogListener()`

License
-------

    Copyright 2015 Khuat Viet Hoa

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

