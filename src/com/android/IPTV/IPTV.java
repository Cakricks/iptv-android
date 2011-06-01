package com.android.IPTV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class IPTV extends Activity implements View.OnClickListener,
		View.OnKeyListener, AdapterView.OnItemSelectedListener {

	private String URL = "";
	private EditText editUrl;
	private Spinner spin;
	private int positionSpinner;
	
	private Button submitButton;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		// Bouton lecture vidéo
		
		submitButton = (Button) findViewById(R.id.buttonSubmit);
		submitButton.setOnClickListener(this);
		
		// Zone de saisie + écoute évènement clavier
		editUrl = (EditText) findViewById(R.id.urlEdit);
		editUrl.setSingleLine(true);
		editUrl.setOnKeyListener(this);

		// La liste des modes (scénarios) du spinner
		String[] scenarios = { "Web TV (HTTP)", "Live RTSP Streaming" };
		
		spin = (Spinner) findViewById(R.id.spinner_scenario);
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, scenarios);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);

	}

	public void onClick(View view) {

		URL = editUrl.getText().toString();
		if (URL.equals(""))
			Toast.makeText(this, "Please enter a correct video link !",
					Toast.LENGTH_SHORT).show();
		else {
			// création de l'activité lecture vidéo
			Intent i = new Intent(this, PlayingVideo.class);

			Bundle objetbunble = new Bundle();
			objetbunble.putString("URL", URL);
			objetbunble.putInt("position", positionSpinner);
			i.putExtras(objetbunble);

			startActivity(i);
		}

	}

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {

		if (position == 0) {
			positionSpinner = 0;

		} else {
			positionSpinner = 1;

		}

	}

	public void onNothingSelected(AdapterView<?> parent) {
		spin.setSelection(0);
		positionSpinner = 0;
	}

	public boolean onKey(View view, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_ENTER) {
			onClick(view);
			return true;
		} else
			return false;
	}

	
}
