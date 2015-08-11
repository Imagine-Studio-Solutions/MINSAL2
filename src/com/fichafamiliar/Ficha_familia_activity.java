package com.fichafamiliar;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;

public class Ficha_familia_activity extends Activity implements OnClickListener{
private Ver_detalle_ficha classVerDetalleFicha= new Ver_detalle_ficha();
	
public SQLiteDatabase conexOpen;
public Context contexto = this;
public GestionDB objGestionDB; // = new GestionDB(this);//creo el objeto de la clase que gestiona la DB
private ImageButton ib_fecha_llenado,IBEditar,IBGuardar,IBGet_geoposition;
private Calendar cal;
private int day;
private int month;
private int year;

String f_llenado,n_vivienda,n_familia,direc_vivienda ;
int idfamilia;
String direccionFam="";
String establecimiento_name;
String codigo_ecosf_name;
String action="";
	
private int id_depto, 
			id_municipio, 
			id_canton_barrio_colonia;
private String 	id_area,
				id_situacion_vivienda,
				id_zona,
				id_religion_familia,
				id_tipo_familia,
				id_pueblo_indigena_familia/*,
				id_riesgo_familiar*/;

Spinner sp_depto, 
		sp_municipio, 
		sp_area, 
		sp_canton_barrio_colonia, 
		sp_zona,
		sp_situacion_vivienda, 
		sp_religion_familia,
		sp_pueblo_indigena_familia, 
		sp_tipo_familia/*,
		sp_riesgo_familiar*/;

EditText nombre_establecimiento,
		 codigo_ecosf,
		 num_vivienda,
		 num_familia,
		 direcion_vivienda,
		 et_fecha_llenado;

TextView TextViewReligionFamilia,
		 TextViewPuebloIndigenaFamilia,
		 TextViewTipoFamilia;
String expedienteFamiliaDB="",expedienteFamiliaFormulario;

Double gpsLat, gpsLon;

int id_estasib;// id establecimiento
int id_sp;					// id usuario tablet
String  nombreusuario; 		// nombre usuario
int  id_sibasi;				// id sibasi
int correlativo_tablet;		// correlativo tablet
int cant_col;
private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ficha_familia_activity);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet = preferencias.getInt("correlativo_tablet", 0);//correlativo tablet
		
		
		TextViewReligionFamilia=(TextView) findViewById(R.id.TextViewReligionFamilia);
		TextViewPuebloIndigenaFamilia=(TextView) findViewById(R.id.TextViewPuebloIndigenaFamilia);
		TextViewTipoFamilia=(TextView) findViewById(R.id.TextViewTipoFamilia);
		
		ib_fecha_llenado = (ImageButton) findViewById(R.id.imageButton1);
		et_fecha_llenado = (EditText) findViewById(R.id.txt_fecha_llenado);
		ib_fecha_llenado.setOnClickListener(this); 
		et_fecha_llenado.setFocusable(false);
		//txt
		nombre_establecimiento = (EditText) findViewById(R.id.txt_nombre_establecimiento);
		nombre_establecimiento.setFocusable(false);
		
		codigo_ecosf = (EditText) findViewById(R.id.txt_codigo_ecosf);
		codigo_ecosf.setFocusable(false);
		
		num_vivienda = (EditText) findViewById(R.id.txt_num_vivienda);
		num_familia = (EditText) findViewById(R.id.txt_num_familia);
		direcion_vivienda=(EditText) findViewById(R.id.txt_direcion_vivienda);
		
		IBGuardar= (ImageButton) findViewById(R.id.IBGuardar);
		
		//Spinner
		sp_depto = (Spinner) findViewById(R.id.sp_depto);
		sp_municipio = (Spinner) findViewById(R.id.sp_municipio);
		sp_area = (Spinner) findViewById(R.id.sp_area);
		sp_canton_barrio_colonia = (Spinner) findViewById(R.id.sp_canton_barrio_colonia);
		sp_zona = (Spinner) findViewById(R.id.sp_zona);
		sp_situacion_vivienda = (Spinner) findViewById(R.id.sp_situacion_vivienda);
		sp_religion_familia = (Spinner) findViewById(R.id.sp_religion_familia);
		sp_pueblo_indigena_familia = (Spinner) findViewById(R.id.sp_pueblo_indigena_familia);
		sp_tipo_familia = (Spinner) findViewById(R.id.sp_tipo_familia);
		//sp_riesgo_familiar = (Spinner) findViewById(R.id.sp_riesgo_familiar);
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();*/
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la
				/*									// clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New" 
		gpsLat = bundle.getDouble("gpsLat");
		gpsLon = bundle.getDouble("gpsLon");
		
		establecimiento_name=objGestionDB.getNameEstablecimiento(id_estasib,this.contexto);
		nombre_establecimiento.setText(establecimiento_name);
		//seteo el TextEdit del código del Ecosf
		codigo_ecosf_name=objGestionDB.getNameCodigoEcosf(id_estasib,this.contexto);
		codigo_ecosf.setText(codigo_ecosf_name);
		
		if(action.equals("Edit")){
			//si viene de editar, llamar  al metodo que recupera la informacion de la ficha
			this.idfamilia= bundle.getInt("idfamilia");
			//this.direccionFam= bundle.getString("direccionFam");
			objGestionDB.getFamiliaInfoEdit(idfamilia,this.contexto);
			if(objGestionDB.codigo_area.equals("U")){
				cant_col=objGestionDB.codigo_colonia;
			}else{
				cant_col=objGestionDB.codigo_canton;
			}
			expedienteFamiliaDB=""+objGestionDB.codigo_departamento+objGestionDB.codigo_municipio+objGestionDB.codigo_area+cant_col+objGestionDB.codigo_zona+objGestionDB.numerovivienda+objGestionDB.numerofamilia;
			loadSpinnerDataSp_canton_barrio_colonia(objGestionDB.codigo_area,objGestionDB.codigo_municipio);
			num_vivienda.setText(""+objGestionDB.numerovivienda);
			num_familia.setText(""+objGestionDB.numerofamilia);
			et_fecha_llenado.setText(""+objGestionDB.fechaintroduccion);
			direcion_vivienda.setText(""+objGestionDB.direccionFamilia);
			// t_latitud.setText(""+objGestionDB.s_latitud);
			// t_longitud.setText(""+objGestionDB.s_longitud); 
		}else if(action.equals("New")){
			objGestionDB.getDeptoMunicipioUser(id_sp,this.contexto);			
		}
		loadSpinnerDataSp_depto();
		//loadSpinnerDataSp_area();
		loadSpinnerDataSp_zona();
		loadSpinnerDataSp_situacion_vivienda();
		loadSpinnerDataSp_religion_familia();
		loadSpinnerDataSp_pueblo_indigena_familia();
		loadSpinnerDataSp_tipo_familia();
		//loadCatalogoDescriptor(sp_riesgo_familiar,28);	
		
		
		sp_depto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			// cuando se ha seleccionado un item del spinner
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// guardo en variable el id del depto seleccionado
				id_depto = (((SpinnerObject)sp_depto.getSelectedItem()).getId());
				loadSpinnerDataSp_municipio(id_depto);// llamo al metodo que va a cargar los municipios
			}
			public void onNothingSelected(AdapterView<?> arg0){
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
				if(action.equals("New")){
					loadSpinnerDataSp_canton_barrio_colonia(id_area, id_municipio);
				}
				
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
		sp_situacion_vivienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_situacion_vivienda = (((SpinnerObjectString)sp_situacion_vivienda.getSelectedItem()).getCodigo());
				if(id_situacion_vivienda.equals("02")){
					//	ocultar elementos
					sp_religion_familia.setVisibility(View.INVISIBLE);
					sp_pueblo_indigena_familia.setVisibility(View.INVISIBLE);
					sp_tipo_familia.setVisibility(View.INVISIBLE);
					
					TextViewReligionFamilia.setVisibility(View.INVISIBLE);
					TextViewPuebloIndigenaFamilia.setVisibility(View.INVISIBLE);
					TextViewTipoFamilia.setVisibility(View.INVISIBLE);
					num_familia.setText("0");
					num_familia.setEnabled(false);
				}else{
					//	mostrar elementos
					num_familia.setEnabled(true);
					sp_religion_familia.setVisibility(View.VISIBLE);
					sp_pueblo_indigena_familia.setVisibility(View.VISIBLE);
					sp_tipo_familia.setVisibility(View.VISIBLE);
					
					TextViewReligionFamilia.setVisibility(View.VISIBLE);
					TextViewPuebloIndigenaFamilia.setVisibility(View.VISIBLE);
					TextViewTipoFamilia.setVisibility(View.VISIBLE);
					if(action.equals("Edit")){
						num_familia.setText(""+objGestionDB.numerofamilia);
						num_familia.requestFocusFromTouch(); 
					}else{
						n_familia = num_familia.getText().toString();
						if(n_familia.equals("")){
							num_familia.setText("");	
						}else{
							
						}
					
					}
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		sp_religion_familia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_religion_familia = (((SpinnerObjectString)sp_religion_familia.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		sp_pueblo_indigena_familia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_pueblo_indigena_familia = (((SpinnerObjectString)sp_pueblo_indigena_familia.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		sp_tipo_familia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_tipo_familia = (((SpinnerObjectString)sp_tipo_familia.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	/*	sp_riesgo_familiar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_riesgo_familiar = (((SpinnerObjectString)sp_riesgo_familiar.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	*/			
	}
	
	@SuppressWarnings("unused")
	
	
	
	
	
	
	private String curlyHair(){
		String location_she="";
		int day=0;
		int i_miss_you;
		
		while(day<3){
			location_she="Hotel_Hilton_Miami_AirPort";
			i_miss_you=+999999999;
			day=+1;
		}
		return "I_am_happy";
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
		if(action.equals("Edit")){
			int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getCodigo().equals(objGestionDB.codigo_area)){
					pos=i;
		        }       
	        }
			sp_area.setSelection(pos);
		}
	}

	private void loadSpinnerDataSp_canton_barrio_colonia(String id_area,int id_municipio) {
	 List<SpinnerObject> lables = objGestionDB.getCantonBarrioColonia(id_area, id_municipio,this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_canton_barrio_colonia.setAdapter(dataAdapter);
		int codigo;		
		if(action.equals("Edit")){
			if(objGestionDB.codigo_area.equals("U")){
				codigo=objGestionDB.codigo_colonia;
			}else{
				codigo=objGestionDB.codigo_canton;
			}
			int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getId()==codigo){
					pos=i;
		        }       
	        }
			sp_canton_barrio_colonia.setSelection(pos);
		}	
	}
	private void loadSpinnerDataSp_zona() {
		List<SpinnerObjectString> lables = objGestionDB.getZona(this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_zona.setAdapter(dataAdapter);
		if(action.equals("Edit")){
			int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getCodigo().equals(objGestionDB.codigo_zona)){
					pos=i;
		        }       
	        }
			sp_zona.setSelection(pos);
		}		
	}
	private void loadSpinnerDataSp_situacion_vivienda() {
		List<SpinnerObjectString> lables = objGestionDB.getSituacionVivienda(this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_situacion_vivienda.setAdapter(dataAdapter);
		if(action.equals("Edit")){
			int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getCodigo().equals(objGestionDB.codigo_sit_vivienda)){
					pos=i;
		        }       
	        }
			sp_situacion_vivienda.setSelection(pos);
		}
	}
	private void loadSpinnerDataSp_religion_familia() {
		List<SpinnerObjectString> lables = objGestionDB.getReligionFamilia(this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_religion_familia.setAdapter(dataAdapter);
		if(action.equals("Edit")){
			int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getCodigo().equals(objGestionDB.codigo_religion)){
					pos=i;
		        }       
	        }
			sp_religion_familia.setSelection(pos);
		}
	}
	private void loadSpinnerDataSp_pueblo_indigena_familia() {
		List<SpinnerObjectString> lables = objGestionDB.getPuebloIndigenaFamilia(this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_pueblo_indigena_familia.setAdapter(dataAdapter);
		if(action.equals("Edit")){
			int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getCodigo().equals(objGestionDB.codigo_puebloindigena)){
					pos=i;
		        }       
	        } 
			sp_pueblo_indigena_familia.setSelection(pos);
		}
	}
	private void loadSpinnerDataSp_tipo_familia() {
		List<SpinnerObjectString> lables = objGestionDB.getTipoFamilia(this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_tipo_familia.setAdapter(dataAdapter);
		if(action.equals("Edit")){
			int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getCodigo().equals(objGestionDB.codigo_tipofamilia)){
					pos=i;
		        }       
	        }
			sp_tipo_familia.setSelection(pos);
		}
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
	public void new_ficha_1(View view){
		String establecimiento = nombre_establecimiento.getText().toString();
		String cod_ecosf = codigo_ecosf.getText().toString();
		n_vivienda = num_vivienda.getText().toString();
		n_familia = num_familia.getText().toString();
		direc_vivienda = direcion_vivienda.getText().toString();
		f_llenado=et_fecha_llenado.getText().toString();	
				
		if(establecimiento.equals("")){
			Toast estab=Toast.makeText(this, "Digite el nombre del establecimiento", Toast.LENGTH_LONG);
			estab.setGravity(Gravity.CENTER, 0, 0);
			estab .show();
		}else if(cod_ecosf.equals("")){	
			codigo_ecosf.requestFocus();
			Toast co_ecosf=Toast.makeText(this, "Digite el código del ECOSF", Toast.LENGTH_LONG);
			co_ecosf.setGravity(Gravity.CENTER, 0, 0);
			co_ecosf.show();
		}else if(id_depto==0){
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
		}else if(n_vivienda.equals("")){
			num_vivienda.requestFocusFromTouch(); 
			Toast fam = Toast.makeText(this,"Digite el número de la vivienda", Toast.LENGTH_LONG);
			fam.setGravity(Gravity.CENTER, 0, 0);
			fam.show();
		}else if(n_familia.equals("")){
			num_familia.requestFocusFromTouch();
			Toast num_fam=Toast.makeText(this, "Digite el número de la familia", Toast.LENGTH_LONG);
			num_fam.setGravity(Gravity.CENTER, 0, 0);
			num_fam.show();
		}else if(f_llenado.equals("")){
			 ib_fecha_llenado.requestFocusFromTouch();
			Toast fe_llenado=Toast.makeText(this, "Seleccione la fecha de llenado", Toast.LENGTH_LONG);
			fe_llenado.setGravity(Gravity.CENTER, 0, 0);
			fe_llenado.show();
		}else if(direc_vivienda.equals("")){
			direcion_vivienda.requestFocusFromTouch();
			Toast di_vivienda=Toast.makeText(this, "Digite la dirección de la vivienda", Toast.LENGTH_LONG);
			di_vivienda.setGravity(Gravity.CENTER, 0, 0);
			di_vivienda.show();
		}else if(id_situacion_vivienda.equals("0")){
			sp_situacion_vivienda.requestFocusFromTouch();
			Toast sit_vivienta=Toast.makeText(this, "Seleccione la situación de la vivienda", Toast.LENGTH_LONG);
			sit_vivienta.setGravity(Gravity.CENTER, 0, 0);
			sit_vivienta.show();
		}else{
			if (id_situacion_vivienda.equals("02")){
				// setear a cero las demas variables
				id_religion_familia="";
				id_pueblo_indigena_familia="";
				id_tipo_familia="";
				if(action.equals("New")){
					int existe=objGestionDB.existeNumExpediente(id_depto,id_municipio,id_area,id_canton_barrio_colonia,id_zona,n_vivienda,n_familia,this.contexto);
					if(existe==0){
						validacion_completa();
					}else{
						showMyToast("Este número de expediente ya existe");
					}
				}else{
					validaExpedienteFamilia();
				}	
			}else{
				if(id_religion_familia.equals("0")){
					sp_religion_familia.requestFocusFromTouch();
					Toast relig_familia=Toast.makeText(this, "Seleccione la religión de la familia", Toast.LENGTH_LONG);
					relig_familia.setGravity(Gravity.CENTER, 0, 0);
					relig_familia.show();
				}else if(id_pueblo_indigena_familia.equals("0")){
					sp_pueblo_indigena_familia.requestFocusFromTouch();
					Toast pueblo_indi_fam=Toast.makeText(this, "Seleccione el pueblo indigena al que pertenece la familia", Toast.LENGTH_LONG);
					pueblo_indi_fam.setGravity(Gravity.CENTER, 0, 0);
					pueblo_indi_fam.show();
				}else if(id_tipo_familia.equals("0")){	
					sp_tipo_familia.requestFocusFromTouch();
					Toast tip_fam=Toast.makeText(this, "Seleccione el tipo de familia", Toast.LENGTH_LONG);
					tip_fam.setGravity(Gravity.CENTER, 0, 0);
					tip_fam.show();
				}else if(n_familia.equals("0")){	
					num_familia.requestFocus();
					Toast t_n_familia=Toast.makeText(this, "El número de una familia en vivienda habitada no puede ser cero", Toast.LENGTH_LONG);
					t_n_familia.setGravity(Gravity.CENTER, 0, 0);
					t_n_familia.show();
				}else {
					if(action.equals("New")){
						int existe=objGestionDB.existeNumExpediente(id_depto,id_municipio,id_area,id_canton_barrio_colonia,id_zona,n_vivienda,n_familia,this.contexto);
						if(existe==0){
							validacion_completa();
						}else{
							 //mostar toast con mensaje que ya existe ese numero de expediente
							showMyToast("Este número de expediente ya existe");
						}
					}else{
						//Editar
						validaExpedienteFamilia();
					}
				}
			}
		}
		/*else if(id_riesgo_familiar.equals("99")){	
			sp_riesgo_familiar.requestFocusFromTouch();
			Toast riesgo_familia=Toast.makeText(this, "Seleccione el riesgo de la familia", Toast.LENGTH_LONG);
			riesgo_familia.setGravity(Gravity.CENTER, 0, 0);
			riesgo_familia.show();
		}*/
		
		
	}
private void validaExpedienteFamilia() {
	expedienteFamiliaFormulario=""+id_depto+id_municipio+id_area+id_canton_barrio_colonia+id_zona+n_vivienda+n_familia;
	int existe=objGestionDB.existeNumExpediente(id_depto,id_municipio,id_area,id_canton_barrio_colonia,id_zona,n_vivienda,n_familia,this.contexto);
	if(existe==0){
		//no existe ese numero de expediente
		validacion_completa();
	}else{
		 //si existe ese numero de expediente
		if(expedienteFamiliaFormulario.equals(expedienteFamiliaDB)){
			validacion_completa();
		}else{
			//
			//Log.i("cademas2","expedienteFamiliaFormulario= "+expedienteFamiliaFormulario+" expedienteFamiliaDB= "+expedienteFamiliaDB );
			//Log.i("cademas","id_municipio: "+id_municipio+" codigo_municipio: "+objGestionDB.codigo_municipio);
			showMyToast("Este número de expediente ya existe");
		}
	}
	}
public void guardarEditarInfo(){
	//llama a funcion guardar
	//creando un nueva familia
	//llamar metodo que guarda en la DB y que retorne el id que se creara de la tupla insertada
	ContentValues newValues = new ContentValues(); 
	newValues.put("codigo_departamento", id_depto); 
	newValues.put("codigo_municipio",id_municipio);
	newValues.put("codigo_area",id_area);
				
	if(id_area.equals("U")){
		newValues.put("codigo_colonia",id_canton_barrio_colonia);
		newValues.put("codigo_canton","");
	}else if(id_area.equals("R")){
		newValues.put("codigo_canton",id_canton_barrio_colonia);
		newValues.put("codigo_colonia","");
	}
	newValues.put("codigo_sit_vivienda",id_situacion_vivienda); 
	newValues.put("codigo_zona",id_zona); 
	newValues.put("numerovivienda",n_vivienda); 
	newValues.put("numerofamilia",n_familia); 
	newValues.put("id_estasib",id_estasib);
	newValues.put("codigo_religion",id_religion_familia); 
	newValues.put("codigo_tipofamilia",id_tipo_familia); 
	newValues.put("codigo_puebloindigena",id_pueblo_indigena_familia);
//	newValues.put("tipo_riesgofamiliar",id_riesgo_familiar);
	
	
	Calendar fecha = new GregorianCalendar();
	int anio = fecha.get(Calendar.YEAR);
	
	newValues.put("fechaintroduccion",f_llenado);
	newValues.put("direccion",direc_vivienda);
	newValues.put("anio",anio);
     
	if(action.equals("New")){
		String fechaactual=objGestionDB.fechaActual();
		newValues.put("id_estasib",id_estasib);
		newValues.put("correlativo_tablet",correlativo_tablet);
		newValues.put("idusuario_ingreso",id_sp);
		newValues.put("fecha_hora_ingreso",fechaactual);
		
		objGestionDB.insertReg("familia",newValues,this.contexto);
		//obtener el ultimo id que se inserto
		this.idfamilia=objGestionDB.ultimoidInt("familia", this.contexto);
		//Log.i("idfamilia", ""+idfamilia);
		Toast.makeText(this.contexto, "("+gpsLat + "-"+gpsLon+")", Toast.LENGTH_SHORT);
		
		Intent i = new Intent(this, Ver_detalle_ficha.class);
		i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
		i.putExtra("busquedaPor",3);
		//i.putExtra("action", "New");
		//classVerDetalleFicha.ActivityVerDetalleFicha.finish();
		finish();
		startActivity(i);	
	}else if(action.equals("Edit")){
		String fechaactual=objGestionDB.fechaActual();
		newValues.put("idusuario_mod",id_sp);
		newValues.put("fecha_hora_mod",fechaactual);
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
public void validacion_completa(){
	if (action.equals("Edit")){
		if(id_situacion_vivienda.equals("02")){
			alertaSituacionVivienda();
		}else{
			guardarEditarInfo();
		} 
	}else{
		guardarEditarInfo();
	}
}	
public void alertaSituacionVivienda(){
	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder.setMessage("Eliminara los miembros y la demas información de la familia?")
    .setCancelable(false)
    .setPositiveButton("Aceptar",
            new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int id){
        guardarEditarInfo();
        objGestionDB.cambiarAViviendaDeshabitada(idfamilia,id_sp,Ficha_familia_activity.this);
        }
    });
    alertDialogBuilder.setNegativeButton("Cancelar",
            new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int id){
            dialog.cancel();
        }
    });
    AlertDialog alert = alertDialogBuilder.create();
    alert.show();
	

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

	@Override
	public void onClick(View v){			
		showDialog(0);
	}
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id){
		Calendar fechaActual = new GregorianCalendar();
		int anioActual = fechaActual.get(Calendar.YEAR);
		int mesoActual = fechaActual.get(Calendar.MONTH);
		int diaActual  = fechaActual.get(Calendar.DAY_OF_MONTH);
		
		return new DatePickerDialog(this, datePickerListener, anioActual, mesoActual, diaActual);
	}
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			//et_fecha_llenado.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
			et_fecha_llenado.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
		}
	};
	

	
	
	
}
