package com.empatica.sample.test;

import android.os.AsyncTask;
import android.util.Log;

import com.empatica.sample.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientData extends AsyncTask<String,Integer,Integer> {

    public MainActivity activity;

    public ClientData(MainActivity a)
    {
        this.activity = a;
    }

    @Override
    protected void onPreExecute(){
        Log.d("TcpClient", "NEW CLIENT DATA");
    }

    @Override
    protected Integer doInBackground(String... params) {
        ServerSocket ss = null;
        try {
            Socket s = new Socket(activity.ipUnity, 8222);
            Log.d("TcpClient", "Pronto para enviar dados - " + params[0]);
            activity.updateLabel(activity.statusSendingLabel, "Pronto para enviar dados - " + params[0]);
            //Toast.makeText(ServerData.this, "TcpServer: " + ss.getInetAddress(), Toast.LENGTH_SHORT).show();
            boolean done = false;
            while (!done) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(s.getOutputStream())), true);
                    out.println("TESTE");
                    out.flush();
                    Log.d("TcpClient", "TESTE");
                    activity.updateLabel(activity.statusSendingLabel, "TESTE");
                    done = true;
                } catch (IOException ioe) {
                    done = true;
                }
            }
            s.close();
            Log.i("TcpServer", "Conexão fechada");
            //activity.updateLabel(activity.statusReceivingLabel, "Conexão fechada");
        } catch (InterruptedIOException e) {
            //if timeout occurs
            e.printStackTrace();
            Log.e("TcpServer", ""+e);
            //Toast.makeText(MainActivity.this, "TcpServer: " + e, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("TcpServer", ""+e);
            //Toast.makeText(MainActivity.this, "TcpServer: " + e, Toast.LENGTH_SHORT).show();
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("TcpServer", ""+e);
                    //Toast.makeText(MainActivity.this, "TcpServer: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer numero){
    }
}