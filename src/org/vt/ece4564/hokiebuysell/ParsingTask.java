package org.vt.ece4564.hokiebuysell;

import java.util.concurrent.ArrayBlockingQueue;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;


public class ParsingTask extends AsyncTask<Void, Void, Void> {

	ArrayBlockingQueue<String> q_;
	Activity activity_;
	private static final String TAG = "XBOX";
	String HTMLcode_ = "";
	String profileString = "";
	boolean doesExist = true;

	public ParsingTask(ArrayBlockingQueue<String> queue, Activity act) {
		q_ = queue;
		activity_ = act;
	}

	@Override
	protected Void doInBackground(Void... params) {
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {

			
	}
}
