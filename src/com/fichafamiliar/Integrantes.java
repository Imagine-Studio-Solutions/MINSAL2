package com.fichafamiliar;

import java.io.IOException;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Integrantes extends Activity {
	//public static Activity ActivityIntegrantes;
	
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	 
	int idintegrante;
	int idfamilia;
	
	int id_sp;
	String user_sp;
	String pass_sp;
	int id_estasib_user_sp;
	String nombreusuario;
	int id_sibasi;
	int correlativo_tablet;
	

	TextView FechaNacIntegrante,NombreIntegrante;
	private ImageButton 
			ImageDatosGenerales,ImageEducacion,ImageEconomia, ImageSalud,ImageHabitos,ImageSaludSexual, ImageOtrasVariables;
	
	private String 
			actionDatosGenerales="Edit", actionEducacion, actionEconomia, actionSalud,actionHabitos,
			actionSaludSexual, actionOtrasVariables;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_integrantes);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		//ActivityIntegrantes=this;
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();
			*/
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la
			/*										// clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		
		 id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		 id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		 nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		 id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		 correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 
		 
		 FechaNacIntegrante = (TextView) findViewById(R.id.FechaNacIntegrante);
		 NombreIntegrante = (TextView) findViewById(R.id.NombreIntegrante);
			
		 ImageDatosGenerales= (ImageButton) findViewById(R.id.imgButonDatosGenerales);
		 ImageEducacion= (ImageButton) findViewById(R.id.imgButonEducacion);
		 ImageEconomia= (ImageButton) findViewById(R.id.imgButonEconomico);
		 ImageSalud= (ImageButton) findViewById(R.id.imgButonSalud);
		 ImageHabitos= (ImageButton) findViewById(R.id.imgButonHabitos);
		 ImageSaludSexual= (ImageButton) findViewById(R.id.imgButonSaludSexual);
		 ImageOtrasVariables= (ImageButton) findViewById(R.id.imgButonOtrasVariables);
		 
		 Bundle bundle = getIntent().getExtras();
		 idfamilia   = bundle.getInt("idfamilia");
		 idintegrante =  bundle.getInt("idintegrante");
		 
		 objGestionDB.getNombre_Edad_Integrante(idintegrante,this.contexto); 
		 
		 NombreIntegrante.setText(objGestionDB.IntegranteNombreApellidoConcatenado);
		 FechaNacIntegrante.setText(objGestionDB.IntegranteFechaNac);
		 iconos_integrante_on_off(idintegrante);
		 
		  
	}
	/*@Override
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
		Toast.makeText(getApplicationContext(), "Para regresar haga clik en el boton Menu",      
		Toast.LENGTH_LONG).show();
	}*/
	private void iconos_integrante_on_off(int idintegrante) {
		
		if(objGestionDB.variables_completas_integrante("3,111,120", idintegrante, 3,this.contexto)==1){
			ImageEducacion.setImageResource(R.drawable.educcion_on);
			
			actionEducacion="Edit";		
		}else{
			ImageEducacion.setImageResource(R.drawable.educcion_off);
			actionEducacion="New";
		}
		if(objGestionDB.variables_completas_integrante("112,69,115,116,84,90", idintegrante, 6,this.contexto)==1){
			ImageEconomia.setImageResource(R.drawable.economia_on);
			actionEconomia="Edit";		
		}else{
			ImageEconomia.setImageResource(R.drawable.economia_off);
			actionEconomia="New";
		}
		
		if(objGestionDB.variables_completas_integrante("86,85,113,81,10", idintegrante, 5,this.contexto)==1){
			ImageSalud.setImageResource(R.drawable.salud_on);
			actionSalud="Edit";		
		}else{
			ImageSalud.setImageResource(R.drawable.salud_off);
			actionSalud="New";
		}
		
		if(objGestionDB.variables_completas_integrante("11,119,118", idintegrante, 3,this.contexto)==1){
			ImageHabitos.setImageResource(R.drawable.habitos_on);
			actionHabitos="Edit";		
		}else{
			ImageHabitos.setImageResource(R.drawable.habitos_off);
			actionHabitos="New";
		}
		
		if(objGestionDB.variables_completas_integrante("87,74,15,77", idintegrante, 4,this.contexto)==1){
			ImageSaludSexual.setImageResource(R.drawable.salud_sexual_on);
			actionSaludSexual="Edit";		
		}else{
			ImageSaludSexual.setImageResource(R.drawable.salud_sexual_off);
			actionSaludSexual="New";
		}
		
		if(objGestionDB.variables_completas_integrante("78,88,79,80,114,23", idintegrante, 6,this.contexto)==1){
			ImageOtrasVariables.setImageResource(R.drawable.otras_variables_on);
			actionOtrasVariables="Edit";		
		}else{
			ImageOtrasVariables.setImageResource(R.drawable.otras_variables_off);
			actionOtrasVariables="New";
		}
		
	}

	public void click_datosGenerales(View view) {
		Intent i = new Intent(this, Add_new_miembro_familia.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("idmiembrofam",idintegrante);
		i.putExtra("action", actionDatosGenerales);
		//finish();
		startActivity(i);
	}
	public void click_educacion(View view) {
		Intent i = new Intent(this, Educacion.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("idintegrante",idintegrante);
		i.putExtra("action", actionEducacion);
		//finish();
		startActivity(i);
	}
	public void click_economico(View view) {
		Intent i = new Intent(this, Economico.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("idintegrante",idintegrante);
		i.putExtra("action", actionEconomia);
		//finish();
		startActivity(i);
	}
	public void click_salud(View view) {
		//Log.i("idfamilia,idintegrante,action", "idfamilia:  "+idfamilia+" idntegrante: "+idintegrante+" actionSalud: "+actionSalud);
		Intent i = new Intent(this, Salud.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("idintegrante",idintegrante);
		i.putExtra("action", actionSalud);
		//finish();
		startActivity(i);
	}
	public void click_habitos(View view) {
		Intent i = new Intent(this, Habitos.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("idintegrante",idintegrante);
		i.putExtra("action", actionHabitos);
		//finish();
		startActivity(i);
	}
	public void click_saludSexual(View view) {
		Intent i = new Intent(this, Salud_sexual.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("idintegrante",idintegrante);
		i.putExtra("action", actionSaludSexual);
		//finish();
		startActivity(i);
	}
	public void click_otrasVariables(View view) {
		Intent i = new Intent(this, Otras_variables.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("idintegrante",idintegrante);
		i.putExtra("action", actionOtrasVariables);
		//finish();
		startActivity(i);
	}
	public void retur_familia_click(View view) {
	Intent i = new Intent(this, Ver_detalle_ficha.class);
	i.putExtra("idfamilia",idfamilia);
	//i.putExtra("idintegrante",idintegrante);
	i.putExtra("busquedaPor", 3);
	//finish();
	startActivity(i);
}
	
	 
}
