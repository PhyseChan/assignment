package com.example.assignment.weiget;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment.R;


public class CommonRefusedDialog {

    public static void showRefusedDialog(final Activity activity, String title, String message, final boolean isFinish) {
        if (activity.isFinishing()) {
            return;
        }

        final Dialog dialog = new Dialog(activity, R.style.tips_dialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_common_permission_refused, null);
        TextView mTitle = view.findViewById(R.id.alert_title);
        TextView mMessage = view.findViewById(R.id.alert_bigmessage);
        if (TextUtils.isEmpty(title)) {
            return;
        }

        if (TextUtils.isEmpty(message)) {
            return;
        }
        mTitle.setText(title);
        mMessage.setText(message);
        Button button = view.findViewById(R.id.alert_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (isFinish) {
                    activity.finish();
                }
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }
}