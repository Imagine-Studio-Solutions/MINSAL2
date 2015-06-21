package com.fichafamiliar;

import java.io.IOException;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Integrantes_fallecidos extends Activity {
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	ListView listaMiembrosFallecidos;
	int idfamilia=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.integrantes_fallecidos);
		//getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();
			*/// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la
			/*										// clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		listaMiembrosFallecidos = (ListView) findViewById(R.id.listaMiembrosFallecidos);
		Bundle bundle = getIntent().getExtras();
		idfamilia    = bundle.getInt("idfamilia");
		
		loadMiembrosFallecidos(idfamilia);
		listaMiembrosFallecidos.setTextFilterEnabled(true);
		listaMiembrosFallecidos.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> elemento, View v, int position,long id) {
					int elegido = ((SpinnerObject) elemento.getItemAtPosition(position)).getId(); 					
					Intent i = new Intent(Integrantes_fallecidos.this, Edit_delete_miembro_fam.class);
					i.putExtra("idmiembrofam",elegido);
					i.putExtra("idfamilia",idfamilia);
					i.putExtra("busquedaPor",3);
					startActivity(i); 
				}
			});
	}
	
	private void loadMiembrosFallecidos(int idfamilia) {
		List<SpinnerObject> lista =  objGestionDB.getFamiliaFallecidos(idfamilia,this.contexto);
		ArrayAdapter<SpinnerObject> adap = new ArrayAdapter<SpinnerObject>(this, R.layout.simplerow, lista);
		//listaMiem.setBackgroundColor(Color.RED); 
		adap.setDropDownViewResource(R.layout.simplerow);
		listaMiembrosFallecidos.setAdapter(adap);
		/*for(int i=0; i<lista.size(); i++){
			//int  idinte=lista.get(i).getId();
			//llamar a la funcion que averigua si esta vivo o muerto
			//si esta vivo
			//listaMiem.getItemAtPosition(i).setBackgroundColor(Color.BLUE);
			//listaMiem.getChildAt(i).setBackgroundColor(Color.RED);
			 
			listaMiem.getChildAt(i).setBackgroundColor(Color.CYAN);
			//si no esta vivo, no pasa nada
	       
	}*/
		setListViewHeightBasedOnChildren(listaMiembrosFallecidos);
		
		}
	public static void setListViewHeightBasedOnChildren(ListView listView) {
	    ListAdapter listAdapter = listView.getAdapter(); 
	    if (listAdapter == null) {
	        // pre-condition
	        return;
	    }
	
	    int totalHeight = 0;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        View listItem = listAdapter.getView(i, null, listView);
	        listItem.measure(0, 0);
	        totalHeight += listItem.getMeasuredHeight();
	    }
	
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	    listView.requestLayout();
	}
	
}
