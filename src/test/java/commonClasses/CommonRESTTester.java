/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonClasses;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by f3307530 on 2015/02/05.
 */
public class CommonRESTTester {

    private String getRestResponse(String url){
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String response = null;

        try {
            //HttpHost target = new HttpHost(url, port, protocol);
            HttpGet getCall = new HttpGet(url);
            HttpResponse httpResponse = httpclient.execute(getCall);
            HttpEntity entity = httpResponse.getEntity();

            byte[] buffer = new byte[1024];
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                try {
                    int bytesRead = 0;
                    String chunk = "";
                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        chunk = new String(buffer, 0, bytesRead);
                        //System.out.println(chunk);
                    }
                    response = chunk;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (RuntimeException runtimeException) {
                    getCall.abort();
                    runtimeException.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (Exception ignore) {
                    }
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return response;
    }
    /*
    public static void main(String args[]) {
        CommonRESTTester restTester = new CommonRESTTester();
        System.out.println(restTester.getRestResponse("http://gts-r3qarevproxy1.fnb.co.za/bncsspBndBonds?guid=20qqw0113135509669&date=20150113"));
    }
    */
}
