package com.example.pizza;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;

public class AsyncGetJson extends AsyncTask<Uri.Builder, Void, String> {

    private Activity mainActivity;

    public AsyncGetJson(Activity activity) {

        // 呼び出し元のアクティビティ
        this.mainActivity = activity;
    }

    // このメソッドは必ずオーバーライドする必要があるよ
    // ここが非同期で処理される部分みたいたぶん。
    @Override
    protected String doInBackground(Uri.Builder... builder) {
        // httpリクエスト投げる処理を書く。
        // ちなみに私はHttpClientを使って書きましたー
        String a = Json.getJson(builder[0].build().toString()).toString();
        return a;
    }


    // このメソッドは非同期処理の終わった後に呼び出されます
    @Override
    protected void onPostExecute(String result) {

    }
}