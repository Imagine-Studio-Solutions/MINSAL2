package com.fichafamiliar;


import java.io.IOException;
import java.util.List;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.InputFilter.LengthFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Busqueda_ficha extends Activity { 
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB; 
	
	private int id_depto, id_municipio, id_canton_barrio_colonia;
	private EditText num_vivienda,num_familia;
	private String id_area,id_zona;
	Spinner sp_depto, sp_municipio, sp_area, sp_canton_barrio_colonia, sp_zona;
	
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	int id_estasib;// id establecimiento
	int id_sp;					// id usuario tablet
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_busqueda_ficha);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet = preferencias.getInt("correlativo_tablet", 0);//correlativo tablet
		
		//getWindow().getDecorView().setBackgroundColor(Color.WHITE);
	   // getWindow().setLayout (LayoutParams.WRAP_CONTENT /* width */ , LayoutParams.WRAP_CONTENT /* height */);
	   //numero_familia = (EditText) findViewById(R.id.txt_numero_familia);// tomo el valor del edittext txt_numero_familia
		sp_depto = (Spinner) findViewById(R.id.sp_depto);
		sp_municipio = (Spinner) findViewById(R.id.sp_municipio);
		sp_area = (Spinner) findViewById(R.id.sp_area);
		sp_canton_barrio_colonia = (Spinner) findViewById(R.id.sp_canton_barrio_colonia);
		sp_zona = (Spinner) findViewById(R.id.sp_zona);
		num_vivienda = (EditText) findViewById(R.id.txt_num_vivienda);
		num_familia = (EditText) findViewById(R.id.txt_num_familia);
		
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();*/
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la
													// clase que gestiona la DB
		/*} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}*/
		 objGestionDB.getDeptoMunicipioUser(id_sp,this.contexto);
		
		loadSpinnerDataSp_depto();
		
		//loadSpinnerDataSp_canton_barrio_colonia();
		loadSpinnerDataSp_zona();
		/*seleccion spinner depto*/
		sp_depto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			// cuando se ha seleccionado un item del spinner
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// guardo en variable el id del depto seleccionado
				id_depto = (((SpinnerObject)sp_depto.getSelectedItem()).getId());
				// llamo al metodo que va a cargar los municipios
				loadSpinnerDataSp_municipio(id_depto);
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		sp_municipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			// cuando se ha seleccionado un item del spinner
			public void onItemSelected(AdapterView<?> parent,
					View view, int pos, long id) {
				// guardo en variable el id del municipio seleccionado
				id_municipio = (((SpinnerObject) sp_municipio.getSelectedItem()).getId());
				loadSpinnerDataSp_area();
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		sp_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			// cuando se ha seleccionado un item del spinner
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				// guardo en variable el id del area seleccionado
				id_area = (((SpinnerObjectString)sp_area.getSelectedItem()).getCodigo());
				//nombre_establecimiento.setText(id_area);
				//txt_codigo_ecosf
				  loadSpinnerDataSp_canton_barrio_colonia(id_area, id_municipio);
				// dependiendo el area elegida asi le paso el parametro al
				// metodo
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			
			}
		});
		sp_canton_barrio_colonia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_canton_barrio_colonia = (((SpinnerObject)sp_canton_barrio_colonia.getSelectedItem()).getId());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		sp_zona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_zona = (((SpinnerObjectString)sp_zona.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	private void loadSpinnerDataSp_depto() {
		List<SpinnerObject> lables = objGestionDB.getDepto(this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_depto.setAdapter(dataAdapter);
		int pos=0;
		for(int i=0; i<lables.size(); i++){
			if(lables.get(i).getId()==objGestionDB.codigo_departamento){
				pos=i;
	        }       
          }
		sp_depto.setSelection(pos);
		
		
	}
	private void loadSpinnerDataSp_municipio(int id_depto) {
		List<SpinnerObject> lables = objGestionDB.getMunicipioXDepto(id_depto,this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_municipio.setAdapter(dataAdapter);
		int pos=0;
		for(int i=0; i<lables.size(); i++){
			if(lables.get(i).getId()==objGestionDB.codigo_municipio){
				pos=i;
	        }       
		sp_municipio.setSelection(pos);
	}
	}
	private void loadSpinnerDataSp_area(){
		List<SpinnerObjectString> lables = objGestionDB.getArea(this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_area.setAdapter(dataAdapter);
	}
	private void loadSpinnerDataSp_canton_barrio_colonia(String id_area,int id_municipio) {
		List<SpinnerObject> lables = objGestionDB.getCantonBarrioColonia(id_area, id_municipio,this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_canton_barrio_colonia.setAdapter(dataAdapter);
	}
	private void loadSpinnerDataSp_zona() {
		List<SpinnerObjectString> lables = objGestionDB.getZona(this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_zona.setAdapter(dataAdapter);
	}
 
	public void result_busqueda_familia(View view) { 
		String vivienda = num_vivienda.getText().toString();
		String familia = num_familia.getText().toString();
		if(id_depto==0){
			sp_depto.requestFocusFromTouch ();
			Toast depto=Toast.makeText(this, "Seleccione el departamento", Toast.LENGTH_LONG);
			depto.setGravity(Gravity.CENTER, 0, 0);
			depto.show();			
		}else if(id_municipio==0){	 
			sp_municipio.requestFocusFromTouch();
			Toast municipio=Toast.makeText(this, "Seleccione el municipio", Toast.LENGTH_LONG);
			municipio.setGravity(Gravity.CENTER, 0, 0);
			municipio.show();
		}else if(id_area.equals("0")){
			sp_area.requestFocusFromTouch();
			Toast area=Toast.makeText(this, "Seleccione el area", Toast.LENGTH_LONG);
			area.setGravity(Gravity.CENTER, 0, 0);
			area.show();
		}else if(id_canton_barrio_colonia==0){
			sp_canton_barrio_colonia.requestFocusFromTouch ();
			Toast can_barr_col=Toast.makeText(this, "Seleccione el canton, barrio o colonia", Toast.LENGTH_LONG);
			can_barr_col.setGravity(Gravity.CENTER, 0, 0);
			can_barr_col.show();
		}else if(id_zona.equals("0")){
			sp_zona.requestFocusFromTouch();
			Toast zona=Toast.makeText(this, "Seleccione la zona ", Toast.LENGTH_LONG);
			zona.setGravity(Gravity.CENTER, 0, 0);
			zona.show();
		}else if(vivienda.equals("")){
			Toast Tvivienda=Toast.makeText(this, "Digite el número de vivienda", Toast.LENGTH_LONG);
			Tvivienda.setGravity(Gravity.CENTER, 0, 0);
			Tvivienda.show();
		}else if(familia.equals("")){
			Toast Tfamilia=Toast.makeText(this, "Digite el número de famila", Toast.LENGTH_LONG);
			Tfamilia.setGravity(Gravity.CENTER, 0, 0);
			 
			Tfamilia.show();
		}else{
			
			int existe=objGestionDB.existeNumExpediente(id_depto,id_municipio,id_area,id_canton_barrio_colonia,id_zona,vivienda,familia,this.contexto);
			if(existe==0){
				showMyToast("No existe este número de expediente");	
			}else{
				objGestionDB.getIdFamilia_direccion(id_depto,id_municipio,id_area,id_canton_barrio_colonia,id_zona,vivienda,familia,this.contexto);
				Intent i = new Intent(Busqueda_ficha.this, Ver_detalle_ficha.class);
				i.putExtra("busquedaPor",2);//si la busqueda es por jefe de familia busquedaPor=1
											//si la busqueda es por numero de expediente familiar busquedaPor=2
				i.putExtra("idfamilia",objGestionDB.idfamilia);
				i.putExtra("direccion",objGestionDB.direccionFamilia);
				startActivity(i);
			}	 
			 
		}
	}
	public void showMyToast(String texto) {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.mensajetoast,(ViewGroup) findViewById(R.id.toast_layout_root)); //"inflamos" nuestro layout
		TextView text = (TextView) layout.findViewById(R.id.text_toast);
		text.setText(texto); //texto a mostrar y asignado al textView de nuestro layout
		Toast toast = new Toast(getApplicationContext()); //Instanciamos un objeto Toast
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0); //lo situamos centrado verticalmente en la pantalla
		toast.setDuration(Toast.LENGTH_LONG); //duracion del toast
		toast.setView(layout); //asignamos nuestro layout personalizado al objeto Toast
		toast.show(); //mostramos el Toast en pantalla
		} 
}