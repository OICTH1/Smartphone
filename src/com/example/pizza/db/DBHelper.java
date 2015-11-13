package com.example.pizza.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	 /* データベース名 */
    private final static String DB_NAME = "PIZZA";
    /* データベースのバージョン */
    private final static int DB_VER = 2;
    /* コンテキスト */
    private Context mContext;

	public DBHelper(Context context) {
		 super(context, DB_NAME, null, DB_VER);
	     mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
            execSql(db,"sql/create");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 try {
	            execSql(db,"sql/drop");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        onCreate(db);
	}

	/**
     * 引数に指定したassetsフォルダ内のsqlを実行します。
     * @param db データベース
     * @param assetsDir assetsフォルダ内のフォルダのパス
     * @throws IOException
     */
    private void execSql(SQLiteDatabase db,String assetsDir) throws IOException {
        AssetManager as = mContext.getResources().getAssets();
        try {
            String files[] = as.list(assetsDir);
            for (int i = 0; i < files.length; i++) {
                String str = readFile(as.open(assetsDir + "/" + files[i]));
                for (String sql: str.split("/")){
                    db.execSQL(sql);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ファイルから文字列を読み込みます。
     * @param is
     * @return ファイルの文字列
     * @throws IOException
     */
    private String readFile(InputStream is) throws IOException{
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is,"SJIS"));

            StringBuilder sb = new StringBuilder();
            String str;
            while((str = br.readLine()) != null){
                sb.append(str +"\n");
            }
            return sb.toString();
        } finally {
            if (br != null) br.close();
        }
    }
}
