package br.com.chfmr.gcm;

import android.app.Activity;
import android.content.ComponentName;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.content.Intent;
import android.content.Context;

/**
 * Created by carlosrodrigues on 4/18/15.
 */
public class GcmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){

        ComponentName comp = new ComponentName(
                context.getPackageName(),
                GcmService.class.getName()
        );

        startWakefulService(context, intent.setComponent(comp));
        setResultCode(Activity.RESULT_OK);
    }
}
