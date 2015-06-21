package com.fichafamiliar;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RiesgoFamiliar extends Activity {
	//private Ver_detalle_ficha classVerDetalleFicha= new Ver_detalle_ficha();
	
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB; // = new GestionDB(this);//creo el objeto de la clase que gestiona la DB
	private ImageButton  IBGuardar;
	private Calendar cal;
	private int day;
	private int month;
	private int year;
	int idfamilia;
	String direccionFam="";
	String establecimiento_name;
	String codigo_ecosf_name;
		
	String action="";
	private int id_estasib_user_sp;
	 
	private String 	 
					id_riesgo_familiar;

	Spinner  	sp_riesgo_familiar;


	int id_estasib,correlativo_tablet;

	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riesgo_familiar);
		
 
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		correlativo_tablet = preferencias.getInt("correlativo_tablet", 0);
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0);
		
		 
		IBGuardar= (ImageButton) findViewById(R.id.IBGuardarRiesgo);
		
		//Spinner		 
		sp_riesgo_familiar = (Spinner) findViewById(R.id.sp_riesgo_familiar);
		
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

		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New" 
		//si viene de editar, llamar  al metodo que recupera la informacion de la ficha
		this.idfamilia= bundle.getInt("idfamilia");
		objGestionDB.getFamiliaInfoEdit(idfamilia,this.contexto);
		loadCatalogoDescriptor(sp_riesgo_familiar,28);	
		
		sp_riesgo_familiar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_riesgo_familiar = (((SpinnerObjectString)sp_riesgo_familiar.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		 
	}
	private void loadCatalogoDescriptor(Spinner spinnerCargar, int id_descriptor) {
		List<SpinnerObjectString> lables = objGestionDB.getCatalogoDescriptor(id_descriptor,this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCargar.setAdapter(dataAdapter);
		if(action.equals("Edit")){
			int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getCodigo().equals(Integer.toString(objGestionDB.tipo_riesgofamiliar))){
					pos=i;
		        }       
	        }
			spinnerCargar.setSelection(pos);
		}
		
	}


	public void click_riesgo_familiar(View view){
		  if(id_riesgo_familiar.equals("99")){	
			sp_riesgo_familiar.requestFocusFromTouch();
			Toast riesgo_familia=Toast.makeText(this, "Seleccione el riesgo de la familia", Toast.LENGTH_LONG);
			riesgo_familia.setGravity(Gravity.CENTER, 0, 0);
			riesgo_familia.show();
		}else {
			//creando un nueva familia
			//llamar metodo que guarda en la DB y que retorne el id que se creara de la tupla insertada
			ContentValues newValues = new ContentValues(); 
			 
			 
			newValues.put("tipo_riesgofamiliar",id_riesgo_familiar);
			
			Calendar fecha = new GregorianCalendar();
			int anio = fecha.get(Calendar.YEAR);
			 
			newValues.put("anio",anio);

				objGestionDB.updateReg("familia",newValues, "idfamilia_tablet="+this.idfamilia,this.contexto);
				
				ToastUpdate("Se ha actualizado el registro");
				Intent i = new Intent(this, Ver_detalle_ficha.class);
				i.putExtra("busquedaPor",3);		//porque ha hecho el edit       busquedaPor=3
				i.putExtra("idfamilia",idfamilia);	//pasar los datos que se necesitaran en la siguiente activity
				//classVerDetalleFicha.ActivityVerDetalleFicha.finish();
				finish();
				startActivity(i);
			}	 
	}
	public void ToastUpdate(String texto) {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toastupdate,(ViewGroup) findViewById(R.id.Linear_Update_Ficha)); //"inflamos" nuestro layout
		TextView text = (TextView) layout.findViewById(R.id.text_Update_Ficha);
		 
		text.setText(texto); //texto a mostrar y asignado al textView de nuestro layout
		Toast toast = new Toast(getApplicationContext()); //Instanciamos un objeto Toast
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0); //lo situamos centrado verticalmente en la pantalla
		toast.setDuration(Toast.LENGTH_LONG); //duracion del toast
		toast.setView(layout); //asignamos nuestro layout personalizado al objeto Toast
		toast.show(); //mostramos el Toast en pantalla
		}
}
