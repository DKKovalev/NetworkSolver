package com.playground.dkkovalev.networksolver;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by DKKovalev on 31.05.2016.
 */
public class ClientHandler extends AsyncTask<Void, Void, Void> {

    private String address;
    private int port;
    private String request = "Error";
    private String response = "Error";
    private PrintWriter printWriter;

    private BufferedReader bufferedReader;

    private String tag;

    private int a, b, c;

    public ClientHandler(String address, int port, String tag, int a, int b, int c) {
        this.address = address;
        this.port = port;
        this.tag = tag;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public ClientHandler(String address, int port, String tag, int a, int b) {
        this.address = address;
        this.port = port;
        this.tag = tag;
        this.a = a;
        this.b = b;
    }

    @Override
    protected Void doInBackground(Void... params) {

        Socket socket = null;

        try {
            socket = new Socket(address, port);

            printWriter = new PrintWriter(socket.getOutputStream(), true);

            request = tag + "," + a + "," + b + "," + c;

            printWriter.write(request);

            /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];

            int bytesRead;

            InputStream inputStream = socket.getInputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }*/

            printWriter.flush();
            printWriter.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.i("gkgkg", response);
        super.onPostExecute(aVoid);
    }
}
