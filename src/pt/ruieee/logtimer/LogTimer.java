/*
 * Copyright (C) 2014 Rui Santos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pt.ruieee.logtimer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LogTimer {
	
	private static final String TAG = "LogTimer";
	
	Context context;
	SharedPreferences timerPrefs;
	static LogTimer timer;
	
	private LogTimer(Context context){
		this.context = context;
		timerPrefs = context.getSharedPreferences("timer_prefs",Activity.MODE_PRIVATE);
	}
	
	public void startTimer(){
		startTimer("default_timer");
	}
	
	public void stopTimer(){
		stopTimer("default_timer");
	}
	
	public void checkPoint(){
		checkPoint("default_timer");
	}
	
	public void startTimer(String specificTimer){
		Log.d(TAG," >>>> Starting "+specificTimer+" timer!");
		
		long now = System.currentTimeMillis();
		timerPrefs.edit().putLong(specificTimer,now)
							.putLong(specificTimer+"_total",now).commit();
	}
	
	public void stopTimer(String specificTimer){
		stopTimer(specificTimer,"");
	}
	
	public void stopTimer(String specificTimer,String message){
		long now = System.currentTimeMillis();
		Log.d(TAG," >>>> Timer "+specificTimer+" finished - "+message+" : "+(now-timerPrefs.getLong(specificTimer,0))+"ms"+" ("+(now-timerPrefs.getLong(specificTimer+"_total",0))+"ms total)");
		
		timerPrefs.edit().remove(specificTimer)
			.remove(specificTimer+"_total")
			.remove(specificTimer+"_checkpoint")
			.commit();
	}
	
	public void checkPoint(String specificTimer){
		checkPoint(specificTimer,"");
	}
	
	public void checkPoint(String specificTimer,String message){
		long now = System.currentTimeMillis();
		Log.d(TAG," >> "+specificTimer+" cp #"+(timerPrefs.getInt(specificTimer+"_checkpoint",0)+1)+" "+(now-timerPrefs.getLong(specificTimer,0))+"ms ("+(now-timerPrefs.getLong(specificTimer+"_total",0))+"ms total) "+message);
		
		timerPrefs.edit().putLong(specificTimer,now).commit();
		timerPrefs.edit().putInt(specificTimer+"_checkpoint",(timerPrefs.getInt(specificTimer+"_checkpoint",0)+1)).commit();
	}
	
	public static LogTimer getInstance(Context context){
		return timer==null?new LogTimer(context):timer;
	}
	

}
