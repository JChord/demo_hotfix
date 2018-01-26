package com.doslin.hotfix;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by doslin on 2018/1/25.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassLoader classLoader = getClassLoader();
        int i = 0;
        while (classLoader != null){
            Log.d(TAG, "ClassLoader " + (i++) + " : " + classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }

    public void test(View btn) {
        new WaitToFix().testMethod(this);
    }

    public void fix(View btn) {
        File filesDir = this.getDir("odex", Context.MODE_PRIVATE);
        String name = "out.dex";
        String filePath = new File(filesDir, name).getAbsolutePath();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        InputStream is = null;
        FileOutputStream os = null;
        try {
            Log.d(TAG, "fixBug: " + new File(Environment.getExternalStorageDirectory(), name).getAbsolutePath());
            is = new FileInputStream(new File(Environment.getExternalStorageDirectory(), name));
            os = new FileOutputStream(filePath);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            File f = new File(filePath);
            if (f.exists()) {
                Toast.makeText(this, "dex overwrite", Toast.LENGTH_SHORT).show();
            }
            FixManager.loadDex(this);
        } catch (IOException e) {
            Log.d(TAG, "fix error: " + e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                Log.d(TAG, "fix error: " + e.getMessage());
            }
        }
    }
}
