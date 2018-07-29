package com.luiztadeu.popularmovies.NetworkUtils;

import android.os.AsyncTask;

import com.luiztadeu.popularmovies.repository.IMoviesRepository;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ConnectionsUtils {

    private static final String COMMAND_PING = "/system/bin/ping -c 1 8.8.8.8";

    private ConnectionsUtils() {
    }

    public static void isConnected(IMoviesRepository.Consumer consumer) {
        new InternetCheck(consumer);
    }

    public static class InternetCheck extends AsyncTask<Void, Void, Boolean> {

        private IMoviesRepository.Consumer mConsumer;

        InternetCheck(IMoviesRepository.Consumer consumer) {
            mConsumer = consumer;
            execute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                Runtime runtime = Runtime.getRuntime();
                Process ipProcess = runtime.exec(COMMAND_PING);
                int exitValue = ipProcess.waitFor();
                return (exitValue == 0);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean internet) {
            if (internet)
                mConsumer.hasInternet();
            else {
                mConsumer.dontHasInternet();
            }
        }
    }
}
