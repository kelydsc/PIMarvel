package br.com.digitalhouse.digital.pimarvel.event.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.digitalhouse.digital.pimarvel.event.view.EventFragment;

public class BootReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) ||
        intent.getAction().equals(Intent.ACTION_LOCKED_BOOT_COMPLETED)) {
    Intent startIntent = new Intent(context, EventFragment.class);
    startIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(startIntent);
        }
    }
}
