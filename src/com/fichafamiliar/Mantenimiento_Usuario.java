package com.fichafamiliar;

import java.io.IOException;
import java.util.List;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Mantenimiento_Usuario extends Activity {

	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	
	ListView listaUsuarios;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mantenimiento__usuario);
		
		//getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		
		this.objGestionDB = new GestionDB();// creo el objeto de la DB
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();
			// db2.openDataBase();
			this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB(this);// creo el objeto de la
													// clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		listaUsuarios= (ListView) findViewById(R.id.lista_Usuarios);
		
		loadUsuarios();
		
		listaUsuarios.setTextFilterEnabled(true);
		
		listaUsuarios.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> elemento, View v, int position,long id) {
				int elegido = ((SpinnerObject) elemento.getItemAtPosition(position)).getId();
				
				
				Intent i = new Intent(Mantenimiento_Usuario.this, Ver_Acciones_Usuario.class);
				i.putExtra("idusuario_tablet", elegido);
							
				//i.putExtra("busquedaPor",1);
				/*i.putExtra("idusuario_tablet", 1);
				i.putExtra("nombreusuario", 1);*/
				//si la busqueda es por jefe de familia busquedaPor=1
											//si la busqueda es por numero de expediente familiar busquedaPor=2
				finish();
				startActivity(i);
				//int idjefefamilia=elegido.getId();
				//nada(elegido);
				//nada(idjefefamilia);
			}
		});
		
	}
 
 
	
	private void loadUsuarios() 
	{
		List<SpinnerObject> lista =  objGestionDB.getUsuarios(this.contexto);
		ArrayAdapter<SpinnerObject> adap = new ArrayAdapter<SpinnerObject>(this, R.layout.simplerow, lista);
		adap.setDropDownViewResource(R.layout.simplerow);
		listaUsuarios.setAdapter(adap);
	}
	
	public void crear_usuario(View v)
	{
		Intent i = new Intent(Mantenimiento_Usuario.this, Crear_Usuario.class);
		i.putExtra("action", "New");
		startActivity(i);
	}
	
	public void volver_menu(View v)
	{
		Intent i = new Intent(Mantenimiento_Usuario.this, Menu_Activity.class);
		//i.putExtra("action", "New");
		finish();
		startActivity(i);
	}
}
