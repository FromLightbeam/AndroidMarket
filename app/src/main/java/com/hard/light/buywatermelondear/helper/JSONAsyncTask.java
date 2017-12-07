package com.hard.light.buywatermelondear.helper;


import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(String... urls) {
//        try {

        //------------------>>
//            DefaultHttp httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
//
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();
//
//
//            if (status == 200) {
//                HttpEntity entity = response.getEntity();
//                String data = EntityUtils.toString(entity);
//
//
//                JSONObject jsono = new JSONObject(data);
//
//                return true;


//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//
//            e.printStackTrace();
//        }
        return false;
    }

    protected void onPostExecute(Boolean result) {

    }
}