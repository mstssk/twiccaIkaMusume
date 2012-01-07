package jp.mstssk.twiccaplugins.ika_musume;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.utils.URLEncodedUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ViewGroup;

public class AggressionActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public void onStart() {
		super.onStart();

		Intent intent = getIntent();
		String tweet = intent.getStringExtra(Intent.EXTRA_TEXT);

		try {
			tweet = URLEncoder.encode(tweet, "utf-8");
			AggressionTask task = new AggressionTask(this,
			        (ViewGroup) findViewById(R.id.layoutarea));
			task.execute("http://ika.koneta.org/api?text=" + tweet);
//			Log.i("mstssk", "http://ika.koneta.org/api?text=" + tweet);
		} catch (UnsupportedEncodingException e) {
//			Log.e("mstssk", e.getLocalizedMessage());
			finish();
		}

	}
}