package com.android.IPTV;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayingVideo extends Activity implements OnCompletionListener, OnErrorListener {

	private VideoView videoView;
	private MediaController mediaController;
	private ProgressDialog dialog;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.play_video);
		
		/*Configuration attente vidéo*/
		 dialog = new ProgressDialog(this);
		dialog.setCancelable(false);
		dialog.setMessage("Loading Video");
		dialog.show();
        
		String URL = this.getIntent().getExtras().getString("URL");
		int positionSpinner = this.getIntent().getExtras().getInt("position");

		videoView = (VideoView) findViewById(R.id.videoPlayer);
		videoView.setOnCompletionListener(this);
		videoView.setOnErrorListener(this);
		 mediaController = new MediaController(this);
		mediaController.setMediaPlayer(videoView);
		if (positionSpinner == 0) // Web TV HTTP
			videoView.setVideoPath(URL);
		else
			videoView.setVideoURI(Uri.parse(URL)); // RTSP Live streaming

		videoView.setMediaController(mediaController);
		videoView.requestFocus();
		
		/* Démarrage de la vidéo */
		LoadingVideo lv = new LoadingVideo();
		lv.execute((Void) null);
		

	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		
		Intent i = new Intent(this, MinetTV.class);
		startActivity(i);
		finish();
	}

	@Override
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		Toast.makeText(
				this,
				"ERROR: cannot read any video via this link",
				Toast.LENGTH_SHORT).show();
		onCompletion(arg0);
		return false;
	}
	
	private class LoadingVideo extends AsyncTask<Void, Void, Void> {


		@Override
		protected Void doInBackground(Void... params) {
			videoView.start();
			mediaController.show();
			return null;
		}

		public void onPostExecute(Void param) {
			dialog.cancel();

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
