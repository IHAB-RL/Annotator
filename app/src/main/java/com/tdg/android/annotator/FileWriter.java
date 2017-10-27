package com.tdg.android.annotator;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class FileWriter {

    private static String LOG = "FileWriter";
    private static String MAIN_FOLDER = "ANNOTATOR";
    private static String DATA_FOLDER = "annotations";

    public FileWriter() {}


    public static String getFolderPath() {
        File baseDirectory = Environment.getExternalStoragePublicDirectory(MAIN_FOLDER);
        if (!baseDirectory.exists()) {
            Log.e(LOG, "Directory does not exist -> create");
            baseDirectory.mkdir();
        }
        Log.e(LOG, "Path: "+baseDirectory.getAbsolutePath());
        return baseDirectory.getAbsolutePath();
    }


    public boolean saveToFile(Context context, String filename, String data) {

        String sFileName = filename;

        // Obtain working Directory
        File dir = new File(getFolderPath() + "/" + DATA_FOLDER + "/");
        // Address Basis File in working Directory
        File file = new File(dir, sFileName);

        // Make sure the path directory exists.
        if (!dir.exists()) {
            dir.mkdirs();
            if (BuildConfig.DEBUG) {
                Log.i(LOG, "Directory created: " + dir);
            }
        }

        String stringToSave = data;

        if (BuildConfig.DEBUG) {
            Log.i(LOG, "writing to File: " + file.getAbsolutePath());
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

            myOutWriter.append(stringToSave);

            myOutWriter.close();

            fOut.flush();
            fOut.close();

            new FileUpdater(context, file);

            if (BuildConfig.DEBUG) {
                Log.i(LOG, "Data successfully written.");
            }
            return true;
        } catch (IOException e) {

            if (BuildConfig.DEBUG) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }
        return false;
    }
}
