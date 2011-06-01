package com.android.IPTV;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.IPTV.channelUtils.ChannelLinks;

public class MinetTV extends Activity implements OnClickListener {

	private String urlTv;
	private ImageButton tf1Button;
	private ImageButton france2Button;
	private ImageButton france3Button;
	private ImageButton france4Button;
	private ImageButton france5Button;
	private ImageButton m6Button;
	private ImageButton arteButton;
	private ImageButton direct8Button;
	private ImageButton w9Button;
	private ImageButton tmcButton;
	private ImageButton nt1Button;
	private ImageButton nrj12Button;
	private ImageButton lcpButton;
	private ImageButton bfmButton;
	private ImageButton iteleButton;
	private AlertDialog.Builder adb;
	private ProgressDialog dialog;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.minet_tv);
			
		/*Configuration attente connexion wifi*/
		dialog = new ProgressDialog(this);
		dialog.setCancelable(false);
		dialog.setMessage("Connecting Wifi");
		dialog.show();
				
		/* Connexion Wifi */
		ConnectionManagement cm = new ConnectionManagement();
		cm.execute((Void) null);

		/* Définition des boutons de la télévision */
		tf1Button = (ImageButton) findViewById(R.id.tf1);
		france2Button = (ImageButton) findViewById(R.id.france2);
		france3Button = (ImageButton) findViewById(R.id.france3);
		france4Button = (ImageButton) findViewById(R.id.france4);
		france5Button = (ImageButton) findViewById(R.id.france5);
		m6Button = (ImageButton) findViewById(R.id.m6);
		arteButton = (ImageButton) findViewById(R.id.arte);
		direct8Button = (ImageButton) findViewById(R.id.direct8);
		w9Button = (ImageButton) findViewById(R.id.w9);
		tmcButton = (ImageButton) findViewById(R.id.tmc);
		nt1Button = (ImageButton) findViewById(R.id.nt1);
		nrj12Button = (ImageButton) findViewById(R.id.nrj12);
		lcpButton = (ImageButton) findViewById(R.id.lcp);
		bfmButton = (ImageButton) findViewById(R.id.bfm);
		iteleButton = (ImageButton) findViewById(R.id.itele);

		/* Connection des boutons au Listener */
		tf1Button.setOnClickListener(this);
		france2Button.setOnClickListener(this);
		france3Button.setOnClickListener(this);
		france4Button.setOnClickListener(this);
		france5Button.setOnClickListener(this);
		m6Button.setOnClickListener(this);
		arteButton.setOnClickListener(this);
		direct8Button.setOnClickListener(this);
		w9Button.setOnClickListener(this);
		tmcButton.setOnClickListener(this);
		nt1Button.setOnClickListener(this);
		nrj12Button.setOnClickListener(this);
		lcpButton.setOnClickListener(this);
		bfmButton.setOnClickListener(this);
		iteleButton.setOnClickListener(this);
		

		/* Configuration du menu du serveur */

		LayoutInflater factory = LayoutInflater.from(this);
		final View alertDialogView = factory.inflate(R.layout.alertdialogperso,
				null);
		adb = new AlertDialog.Builder(this);
		adb.setView(alertDialogView);
		adb.setTitle("IPTV Server Address");
		adb.setIcon(android.R.drawable.ic_menu_info_details);

		// On affecte un bouton "OK" à notre AlertDialog et on lui affecte un
		// évènement
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				EditText et = (EditText) alertDialogView
						.findViewById(R.id.ServerEditText);
				ChannelLinks.serverAddr = et.getText().toString();
				if (ChannelLinks.serverAddr == "")
					ChannelLinks.serverAddr = "0.0.0.0";
		
			}
		});

		// On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un
		// évènement
		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Nothing to do when cancelling
				
				dialog.cancel();
			}
		});

		
	}

	@Override
	public void onClick(View arg0) {

		ImageButton button_clicked = (ImageButton) arg0;
		switch (button_clicked.getId()) {
		case R.id.tf1:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.tf1;
			break;
		case R.id.france2:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.france2;
			break;
		case R.id.france3:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.france3;
			break;
		case R.id.france4:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.france4;
			break;
		case R.id.france5:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.france5;
			break;
		case R.id.m6:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.m6;
			break;
		case R.id.arte:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.arte;
			break;
		case R.id.direct8:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.direct8;
			break;
		case R.id.w9:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.w9;
			break;
		case R.id.nt1:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.nt1;
			break;
		case R.id.tmc:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.tmc;
			break;
		case R.id.nrj12:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.nrj12;
			break;
		case R.id.lcp:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.lcp;
			break;
		case R.id.bfm:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.bfm;
			break;
		case R.id.itele:
			urlTv = ChannelLinks.serverAddr + ChannelLinks.itele;
			break;
		}

		Intent i = new Intent(this, PlayingVideo.class);
		Bundle objetbunble = new Bundle();
		objetbunble.putString("URL", urlTv);
		objetbunble.putInt("position", 1);
		i.putExtras(objetbunble);
		startActivity(i);

	}

	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.server_menu, menu);
		// menu.getItem(0).getSubMenu().setHeaderIcon(R.drawable.option_white);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		
		adb.show();
		return true;

	}

	private class ConnectionManagement extends AsyncTask<Void, Void, Void> {

		public void startWifi() {
			WifiManager wM = (WifiManager) getSystemService(WIFI_SERVICE);
			wM.setWifiEnabled(true);

		}

		@Override
		protected Void doInBackground(Void... params) {

			WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
			if (wm.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
				startWifi(); // Démarrer le wifi dans le cas où il est désactivé

				for (int i = 0; i < 99; i++) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}

			return null;
		}

		public void onPostExecute(Void param) {
			dialog.cancel();

		}
	}
}
