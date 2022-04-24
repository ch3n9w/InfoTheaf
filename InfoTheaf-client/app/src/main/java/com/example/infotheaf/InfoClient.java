package com.example.infotheaf;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.StringEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;


public class InfoClient {
    private String url;
    private boolean httpres;
    public InfoClient(String url) {
        this.url = url;
        httpres = false;
    }

    public boolean sendInfo(String data) throws Exception{
        new Thread() {
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setHeader("Content-Type", "application/json");
                    httpPost.setEntity(new StringEntity(data));

                    HttpResponse httpResponse =  httpClient.execute(httpPost);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity2 = httpResponse.getEntity();
                        String detail = EntityUtils.toString(entity2, "utf-8");
                        httpres = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return httpres;
    }

}
