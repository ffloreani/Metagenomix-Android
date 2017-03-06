package com.metagenomix.android.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;

import com.metagenomix.android.MetagenomixApplication;
import com.metagenomix.android.activities.ConversionActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public static void copyDirOrFileFromAssetManager(String assetDir, String destinationDir) throws IOException {
        File localStorageDir = MetagenomixApplication.getAppContext().getDir(destinationDir, Context.MODE_PRIVATE);

        AssetManager assetManager = MetagenomixApplication.getAppContext().getAssets();
        String[] files = assetManager.list(assetDir);

        for (String file : files) {
            if(!file.endsWith(".txt")) continue;
            File destFile = new File(localStorageDir, file);
            copyAssetFile(file, destFile);
        }
    }

    private static void copyAssetFile(String assetFilePath, File destinationFile) throws IOException {
        InputStream in = MetagenomixApplication.getAppContext().getAssets().open(assetFilePath);
        OutputStream out = new FileOutputStream(destinationFile);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0)
            out.write(buf, 0, len);
        in.close();
        out.close();
    }
}
