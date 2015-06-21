package com.fichafamiliar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Result_busqueda_familia extends Activity {
	
	private int num_familia;
	ListView listaFamilia;
	List<SpinnerObject> lista = new ArrayList<SpinnerObject>();
	SpinnerObject ObjSpinnerObject;
	
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_busqueda_familia);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		Bundle bundle = getIntent().getExtras();
		this.num_familia= bundle.getInt("num_familia");
		
		//TextView texto = (TextView) findViewById(R.id.txtTitleMienbros);
		//texto.setText("numero: "+this.num_familia);
		
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
		
		listaFamilia = (ListView) findViewById(R.id.listaFamilia);
		loadFamilia(this.num_familia);
		listaFamilia.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,long id) {
				Intent i = new Intent(Result_busqueda_familia.this,Ver_detalle_ficha.class);
				startActivity(i);
			}
		});
	}
	private void loadFamilia(int numero_familia) {
		lista = objGestionDB.busquedaFamilia(numero_familia,this.contexto);
		ArrayAdapter<SpinnerObject> adap = new ArrayAdapter<SpinnerObject>(
				getApplicationContext(), R.layout.simplerow, lista);
		listaFamilia.setAdapter(adap);
	}

	

}
