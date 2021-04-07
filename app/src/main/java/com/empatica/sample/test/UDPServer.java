package com.empatica.sample;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPServer extends AsyncTask<String,Integer,Integer> {

    public MainActivity activity;

    public UDPServer(MainActivity a)
    {
        this.activity = a;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected Integer doInBackground(String... params) {
        try {
            String messageStr = "Hello Android!";
            int server_port = 7777;
            DatagramSocket s = new DatagramSocket();
            InetAddress local = InetAddress.getByName(activity.ipUnity);
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);
            Log.i("UDP", activity.ipUnity);
            Log.i("UDP", messageStr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer numero){
    }

    private void showToast(final String text)
    {
        /*runOnUiThread(new Runnable()
        {
            public void run()
            {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }
        });*/
    }
}