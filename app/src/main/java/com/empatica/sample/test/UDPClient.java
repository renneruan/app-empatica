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

public class UDPClient extends AsyncTask<String,Integer,Integer> {

    public MainActivity activity;

    public UDPClient(MainActivity a)
    {
        this.activity = a;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected Integer doInBackground(String... params) {
        try {
            String text;
            int server_port = 8222;
            byte[] message = new byte[1500];
            DatagramPacket p = new DatagramPacket(message, message.length);
            DatagramSocket s = new DatagramSocket(server_port);
            s.receive(p);
            text = new String(message, 0, p.getLength());
            Log.d("Udp tutorial","message:" + text);
            s.close();
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