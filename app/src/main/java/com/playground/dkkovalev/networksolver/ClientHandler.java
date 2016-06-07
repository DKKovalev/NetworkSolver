package com.playground.dkkovalev.networksolver;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by DKKovalev on 31.05.2016.
 */
public class ClientHandler extends AsyncTask<Void, Void, String> {

    private String address;
    private int port;
    private String request = "Error";

    private String tag;

    private NetworkCallback networkCallback;

    private double a, b, c;

    public ClientHandler(String address, int port, String tag, double a, double b, double c) {
        this.address = address;
        this.port = port;
        this.tag = tag;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public ClientHandler(String address, int port, String tag, double a, double b) {
        this.address = address;
        this.port = port;
        this.tag = tag;
        this.a = a;
        this.b = b;
    }

    @Override
    protected String doInBackground(Void... params) {
        Socket socket = null;
        BufferedReader in;
        PrintWriter printWriter;

        String response = "Error";
        try {
            socket = new Socket(address, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            request = tag + "," + a + "," + b + "," + c;


            printWriter.println(request);
            response = in.readLine();

        } catch (IOException e) {

        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        networkCallback.getServerResponse(response);
    }

    public void setNetworkCallback(NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }
}