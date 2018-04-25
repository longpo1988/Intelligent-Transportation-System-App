package com.example.administrator.its_gs_mvp.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.administrator.its_gs_mvp.R;

public class LoadingDialog {

    public static Dialog dialog;

    public static void showDialog(Context context) {
        dialog = new Dialog(context, R.style.dialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading_progress);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public static void disDialog() {
        dialog.dismiss();
    }
}
