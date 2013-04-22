package com.example.appwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class HelloAppWidgetService extends Service {

	//適当なアクション名.
	private static final String ACTION_BTNCLICK = "com.example.widget.ACTION_BTNCLICK";
	@Override
	/**
	 * AppWidgetへの更新処理を行う
	 */
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		//AppWidgetを管理するためのクラス
		AppWidgetManager manager = AppWidgetManager.getInstance(this);

		//AppWidgetのTextViewを更新するためのオブジェクトの宣言
		RemoteViews view = new RemoteViews(getPackageName(), R.layout.main);
		
		//ボタンを押されたときのアクション名であるならば
		if (ACTION_BTNCLICK.equals(intent.getAction())) {
			btnClicked(view);
		}
		
		//ボタンを押された時に呼び出されるアクション名を設定する
		Intent newintent = new Intent();
		newintent.setAction(ACTION_BTNCLICK);
		//AppWidget上のボタンが押されたことを検知するためのPendingIntentを作成
		PendingIntent pending = PendingIntent.getService(this, 0, newintent, 0);
		view.setOnClickPendingIntent(R.id.button1, pending);
		//AppWidgetManaerを使用してAppWidgetを更新する
		ComponentName widget = new ComponentName(this, HelloAppWidget.class);
		manager.updateAppWidget(widget, view);		
   }
	/**
	* ボタンが押されたときの処理
	* @param view
	*/
	public void btnClicked(RemoteViews view){
		view.setTextViewText(R.id.textView1, "Hello, Android!!");
	}

	@Override
	/**
	* 今回は使用しない
	*/
	public IBinder onBind(Intent arg0) {
		return null;
	 }

}