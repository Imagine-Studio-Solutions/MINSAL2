package com.fichafamiliar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

public class Buscar_por extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_por);
		//getWindow().getDecorView().setBackgroundColor(Color.WHITE);
	}
 
	public void buscar_por_miembro(View view) {
		Intent i = new Intent(this, Buscar_miembro.class);
		//i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
		startActivity(i); 
	}
	public void buscar_por_expediente(View view) {
		Intent i = new Intent(this, Busqueda_ficha.class);
		//i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
		startActivity(i);
	}
}