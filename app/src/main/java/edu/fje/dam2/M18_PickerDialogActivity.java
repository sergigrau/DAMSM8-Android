package edu.fje.dam2;

import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Classe que hereta de la classe Activity i que mostra com utilitzar un giny
 * per a seleccionar dates i hores. Utilitza les classes DatePickerDialog i
 * TimePickerDialog per a seleccionar les dates i les hores Utilitza un layout
 * XML per a definir la interfície d'una llista de la qual l'usuari pot
 * seleccionar un itemamb dos botons.
 *
 * @author sergi.grau@fje.edu
 * @version 1.0, 22/11/2012
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M18_PickerDialogActivity extends AppCompatActivity {

	DateFormat formatDataHora = DateFormat.getDateTimeInstance();
	TextView etiquetaDataHora;
	Calendar dataHora = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.m18_picker_dialog);
		etiquetaDataHora = (TextView) findViewById(R.id.dataHora);
		actualitzarEtiqueta();
	}

	public void seleccionarData(View v) {
		new DatePickerDialog(this, d, dataHora.get(Calendar.YEAR),
				dataHora.get(Calendar.MONTH),
				dataHora.get(Calendar.DAY_OF_MONTH)).show();
	}

	public void seleccionarHora(View v) {
		new TimePickerDialog(M18_PickerDialogActivity.this, t,
				dataHora.get(Calendar.HOUR_OF_DAY),
				dataHora.get(Calendar.MINUTE), true).show();
	}

	private void actualitzarEtiqueta() {
		etiquetaDataHora.setText(formatDataHora.format(dataHora.getTime()));
	}

	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dataHora.set(Calendar.YEAR, year);
			dataHora.set(Calendar.MONTH, monthOfYear);
			dataHora.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			actualitzarEtiqueta();
		}
	};
	TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			dataHora.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dataHora.set(Calendar.MINUTE, minute);
			actualitzarEtiqueta();
		}
	};
}