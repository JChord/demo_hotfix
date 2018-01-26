package com.doslin.hotfix;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by doslin on 2018/1/26.
 */

public class WaitToFix {
    public void testMethod(Context context) {
        throw new IllegalArgumentException();
        //Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
    }


}
