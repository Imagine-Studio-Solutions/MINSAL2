package com.fichafamiliar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Ver_Acciones_Usuario extends Activity {
	int idusuario_tablet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver__acciones__usuario);
		
		//getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		Bundle bundle = getIntent().getExtras();
		//idusuario_tablet= bundle.getString("nombreurl");
		idusuario_tablet= bundle.getInt("idusuario_tablet");
		
	}
 
	 
	public void editar_usuario(View v)
	{
		
		Intent i = new Intent(Ver_Acciones_Usuario.this, Crear_Usuario.class);
		i.putExtra("id_usuario_tablet", idusuario_tablet);
		i.putExtra("action", "Edit");
		finish();
		startActivity(i);
	}
	public void eliminar_usuario(View v)
	{
		
		Intent i = new Intent(Ver_Acciones_Usuario.this, Crear_Usuario.class);
		i.putExtra("id_usuario_tablet", idusuario_tablet);
		i.putExtra("action", "Delete");
		finish();
		startActivity(i);
	}
}
