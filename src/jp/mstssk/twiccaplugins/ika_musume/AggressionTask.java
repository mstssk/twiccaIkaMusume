package jp.mstssk.twiccaplugins.ika_musume;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AggressionTask extends AsyncTask<String, Integer, TextView> {

	private Activity activity;
	private ViewGroup resultArea;

	public AggressionTask(Activity act, ViewGroup view) {
		activity = act;
		resultArea = view;
	}

	@Override
	protected TextView doInBackground(String... params) {

		String url = (String) params[0];
		String source = null;

		try {
			source = new String(getByteArrayFromURL(url), "UTF-8");
		} catch (Exception e) {
//			Log.e("mstssk", e.getLocalizedMessage(), e);
		}

		if (source == null || source.length() <= 0) {
			return null;
		}

		TextView textView = new TextView(activity);
		textView.setText(source);
		return textView;

	}

	@Override
	protected void onPostExecute(TextView result) {
		if (result != null) {
			resultArea.removeAllViews();
			resultArea.addView(result);
		} else {
			Toast.makeText(activity, "侵略失敗でゲソ", Toast.LENGTH_SHORT).show();
			activity.finish();
		}
	}

	private byte[] getByteArrayFromURL(String strUrl) {
		byte[] byteArray = new byte[1024];
		byte[] result = null;
		HttpURLConnection con = null;
		InputStream in = null;
		ByteArrayOutputStream out = null;
		int size = 0;
		try {
			URL url = new URL(strUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			in = con.getInputStream();

			out = new ByteArrayOutputStream();
			while ((size = in.read(byteArray)) != -1) {
				out.write(byteArray, 0, size);
			}
			result = out.toByteArray();
		} catch (Exception e) {
//			Log.e("mstssk", e.getLocalizedMessage(), e);
		} finally {
			try {
				if (con != null)
					con.disconnect();
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (Exception e) {
//				Log.e("mstssk", e.getLocalizedMessage(), e);
			}
		}
		return result;
	}

}
