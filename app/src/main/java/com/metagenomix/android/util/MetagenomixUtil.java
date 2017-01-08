package com.metagenomix.android.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by filipfloreani on 14/12/2016.
 */

public class MetagenomixUtil {

    public static long getRandomLongWithLimit(long limit) {
        return ThreadLocalRandom.current().nextLong(limit);
    }

    public static int getRandomIntWithLimit(int limit) {
        return ThreadLocalRandom.current().nextInt(limit);
    }

    public static double getRandomDoubleWithLimit(double limit) {
        return ThreadLocalRandom.current().nextDouble(limit);
    }

    public static AlertDialog.Builder buildOkDialog(Context context) {
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(context);
        adBuilder.setTitle("Alert")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });

        return adBuilder;
    }
}
