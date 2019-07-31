package br.com.digitalhouse.digital.pimarvel.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.transition.Explode;
import android.view.Window;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppUtil {

    //Verifica se tem conexão com internet
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (
                ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;

        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected() &&
                    (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                            || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
        }
        return false;
    }

    private static char[] HEXCHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String hexEncode(byte[] bytes) {
        char[] result = new char[bytes.length * 2];
        int b;
        for (int i = 0, j = 0; i < bytes.length; i++) {
            b = bytes[i] & 0xff;
            result[j++] = HEXCHARS[b >> 4];
            result[j++] = HEXCHARS[b & 0xf];
        }
        return new String(result);
    }

    /**
     * Cria um hash MD5 par enviarmos a API da marvel
     */
    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());

            return hexEncode(digest.digest());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void setAnimation(Activity activity) {
        activity.getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        activity.getWindow().setEnterTransition(new Explode());
    }

}