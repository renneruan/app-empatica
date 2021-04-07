package com.empatica.sample.test;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class ServerData extends AsyncTask<String,Integer,Integer> {

    public MainActivity activity;

    public ServerData(MainActivity a)
    {
        this.activity = a;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected Integer doInBackground(String... params) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8222);
            Log.d("TcpServer", "Esperando dados...");
            //activity.updateLabel(activity.statusReceivingLabel, "Esperando dados...");
            //Toast.makeText(ServerData.this, "TcpServer: " + ss.getInetAddress(), Toast.LENGTH_SHORT).show();
            boolean done = false;
            while (!done) {
                try {
                    Socket s = ss.accept();
                    Log.i("TcpServer", "Recebendo dados...");
                    //activity.updateLabel(activity.statusReceivingLabel, "Recebendo dados...");
                    //Toast.makeText(MainActivity.this, "TcpServer: " + "Receiving", Toast.LENGTH_SHORT).show();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    //receive a message
                    final String incomingMsg = in.readLine();
                    Log.i("TcpServer", incomingMsg);
                    //activity.updateLabel(activity.receivedLabel, incomingMsg);
                    /*PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(s.getOutputStream())), true);
                    out.println("REC: " + incomingMsg);
                    out.flush();*/
                    Log.d("TcpClient", "REC: " + incomingMsg);
                    if (incomingMsg.equals("GET_EDA")) {
                        activity.startClientData();
                        activity.updateLabel(activity.statusSendingLabel, "TESTE");
                    } else if (incomingMsg.equals("STOP_EDA")) {
                        activity.stopClientData();
                    } else if (incomingMsg.equals("STOP_GAME")) {
                        done = true;
                    }
                    s.close();
                    //Toast.makeText(MainActivity.this, "TcpServer: " + in.readLine(), Toast.LENGTH_SHORT).show();
                } catch (IOException ioe) {
                    Log.i("TcpServer", "Conexão falhou");
                    Log.i("TcpServer", ioe.getMessage());
                    //activity.updateLabel(activity.statusReceivingLabel, "Conexão falhou");
                }
            }
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