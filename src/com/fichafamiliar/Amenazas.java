package com.fichafamiliar;

import java.io.IOException;
import java.util.List;

import com.fichafamiliar.Patrimonio.GuardarEditarPatrimonio;
import com.fichafamiliar.Patrimonio.RSAsyncPatrimonio;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Amenazas extends Activity {
	//private Ver_detalle_ficha classVerDetalleFicha= new Ver_detalle_ficha();
	
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	String action="";
	int idfamilia;
	int id_sp;					// id usuario tablet
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	public String 	
	CombustibleCocinar;
	String numerosRiesgoAmbiental;
	String 		id_CombustibleCocinar;
	
	String 		SinRiesgo="",
				Deslaves="",
				Inundaciones="",
				Desechos="",
				Erupcion="",
				OtrosRiesgos="";
	
	CheckBox 	CheckBoxSinRiesgo,
				CheckBoxDeslaves,
				CheckBoxInundaciones,
				CheckBoxDesechos,
				CheckBoxErupcion,
				CheckBoxOtrosRiesgos;
	
	Spinner 	SpnCombustibleCocinar;
	
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amenazas);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New" 
		
		CheckBoxSinRiesgo = (CheckBox) findViewById(R.id.Checksinriesgo);
		CheckBoxDeslaves	=	(CheckBox) findViewById(R.id.Checkdeslaves);
		CheckBoxInundaciones = (CheckBox) findViewById(R.id.Checkinundaciones);
		CheckBoxDesechos = (CheckBox) findViewById(R.id.Checkdesechos);
		CheckBoxErupcion = (CheckBox) findViewById(R.id.Checkerupcion);
		CheckBoxOtrosRiesgos = (CheckBox) findViewById(R.id.Checkotros_riesgos);
		
		SpnCombustibleCocinar = (Spinner) findViewById(R.id.SpnCombustibleCocinar);
		
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
		this.idfamilia= bundle.getInt("idfamilia");
		RSAsyncAmenazas leerAsync = new RSAsyncAmenazas(this);
		leerAsync.execute();
		
		SpnCombustibleCocinar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_CombustibleCocinar = (((SpinnerObjectString)SpnCombustibleCocinar.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});	
	}  
public int	valida_riesgo_ambiental(){
		if(CheckBoxSinRiesgo.isChecked() || CheckBoxDeslaves.isChecked() || CheckBoxInundaciones.isChecked() || CheckBoxDesechos.isChecked() || CheckBoxErupcion.isChecked() || CheckBoxOtrosRiesgos.isChecked()){
			return 1;	
		}else{
			return 0;
		}
		
	}
public void click_SinRiesgo(View view) {
	if(CheckBoxSinRiesgo.isChecked()){//desactivar todos
		CheckBoxDeslaves.setChecked(false);
		CheckBoxInundaciones.setChecked(false);
		CheckBoxDesechos.setChecked(false);
		CheckBoxErupcion.setChecked(false);
		CheckBoxOtrosRiesgos.setChecked(false);
	}
}
public void click_Deslaves(View view) {
	if(CheckBoxDeslaves.isChecked()){
		CheckBoxSinRiesgo.setChecked(false);
	}  
}
public void clic_Inundaciones(View view) {
	if(CheckBoxInundaciones.isChecked()){
		CheckBoxSinRiesgo.setChecked(false);
	} 
}
public void click_Desechos(View view) {
	if(CheckBoxDesechos.isChecked()){
		CheckBoxSinRiesgo.setChecked(false);
	}
}
public void click_Erupcion(View view) {
	if(CheckBoxErupcion.isChecked()){
		CheckBoxSinRiesgo.setChecked(false);
	}
}
public void click_OtrosRiesgos(View view) {
	if(CheckBoxOtrosRiesgos.isChecked()){
		CheckBoxSinRiesgo.setChecked(false);
	}
}

public void click_amenazas(View view) {
	if(valida_riesgo_ambiental()==0){
		Toast riesgo=Toast.makeText(this, "Marque al menos una opción de riesgo ambiental", Toast.LENGTH_LONG);
		riesgo.setGravity(Gravity.CENTER, 0, 0);
		riesgo.show();
	}else if(id_CombustibleCocinar.equals("99")){
		SpnCombustibleCocinar.requestFocusFromTouch();
		Toast combustible=Toast.makeText(this, "Seleccione el tipo de combustible que utiliza para cocinar", Toast.LENGTH_LONG);
		combustible.setGravity(Gravity.CENTER, 0, 0);
		combustible.show();
	}else{
		GuardarEditarAmenazas GuardarEditar = new GuardarEditarAmenazas(this);
		GuardarEditar.execute();
		 
		 if(action.equals("New")){
			 ToastExito("Se ha guardado la información");			 
			 Intent i = new Intent(contexto, Ver_detalle_ficha.class);
			 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
			 i.putExtra("busquedaPor",3);
			 // Log.i("idfamilia2", ""+id_familia);
			// classVerDetalleFicha.ActivityVerDetalleFicha.finish();
			 finish();
			 startActivity(i);
		 }else{
			 ToastExito("Se ha editado la información");
			 Intent i = new Intent(this, Ver_detalle_ficha.class);
			 i.putExtra("busquedaPor",3);		//porque ha hecho el edit       busquedaPor=3
			 i.putExtra("idfamilia",idfamilia);	//pasar los datos que se necesitaran en la siguiente activity
			// classVerDetalleFicha.ActivityVerDetalleFicha.finish();
			 finish();
			 startActivity(i);
		 }
	}		
}
public void ToastExito(String texto) {
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
	
public class RSAsyncAmenazas extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para guardar el valor del spn que trae de la base de datos
		//posiciones en las listas
		public int 	
				CombustibleCocinar_pos;
		ArrayAdapter<SpinnerObjectString> dataAdapterCombustibleCocinar;
		public RSAsyncAmenazas(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			/*if (action.equals("Edit")){
				CargarCheckBox();	
	    	}*/
			CargarCheckBox();	
	    	CargarSpinner();
	    return null;
	    }
		@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    		dataAdapterCombustibleCocinar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnCombustibleCocinar.setAdapter(dataAdapterCombustibleCocinar);
    		
    		SpnCombustibleCocinar.setSelection(this.CombustibleCocinar_pos);
    		progressDialog.dismiss();
		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }
		public void CargarSpinner() {	//descriptor
			List<SpinnerObjectString> lablesCombustibleCocinar = objGestionDB.getCatalogoDescriptor(62,this.contexto);
			dataAdapterCombustibleCocinar = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesCombustibleCocinar);
			//averiguar la posicion en la lista
			//if(action.equals("Edit")){
				//recupero el valor que tienen las variable cuando se creo la ficha
				CombustibleCocinar=objGestionDB.getValorVariableSelecionado(idfamilia,99,this.contexto);
			
			
			for(int i=0; i<lablesCombustibleCocinar.size(); i++){
				if(lablesCombustibleCocinar.get(i).getCodigo().equals(CombustibleCocinar)){
					CombustibleCocinar_pos=i;
		        }       
	          }
			//}
			}
		private void CargarCheckBox() {
			cargarCheckRiesgoAmbiental();
		}
		private void cargarCheckRiesgoAmbiental() {
		    numerosRiesgoAmbiental = objGestionDB.getValorVariableSelecionado(idfamilia,98,this.contexto);
		    String[] numerosComoArray = numerosRiesgoAmbiental.split(",");
		    for (int i = 0; i < numerosComoArray.length; i++) {
			     if(numerosComoArray[i].equals("0")){CheckBoxSinRiesgo.setChecked(true);}
			     if(numerosComoArray[i].equals("1")){ CheckBoxDeslaves.setChecked(true);}
			     if(numerosComoArray[i].equals("2")){CheckBoxInundaciones.setChecked(true);}
			     if(numerosComoArray[i].equals("3")){CheckBoxDesechos.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){CheckBoxErupcion.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){CheckBoxOtrosRiesgos.setChecked(true);}
		    	}
			}
		
	}
	public class GuardarEditarAmenazas extends AsyncTask<String, Void, String> {  
		Context contexto;
	    protected String doInBackground(String... params) {
			    		/*if(action.equals("New")){
			    			guardarAmenazas();
			    		}else{
			    			actualizarAmenazas();
			    		}*/
	    	EG();
	    		return null;
	    	}
	    public GuardarEditarAmenazas(Context contexto){	
				this.contexto = contexto;
			}
    	@Override
    	protected void onPostExecute(String result) {
    			progressDialog.dismiss();
    		}
    	@Override
        protected void onPreExecute() {
	            super.onPreExecute();
	            if(action.equals("New")){
	            	progressDialog = ProgressDialog.show(contexto,"","Guardando los datos...",true);	
	        		}else{
	        			progressDialog = ProgressDialog.show(contexto,"","Actualizando los datos...",true);
	        		}    
    		}
    	public void guardarAmenazas(){
	    	/*String fechaactual=objGestionDB.fechaActual();
	    	getCheckriesgosAmbientales();
		    	
	    	String riesgosAmbientales=SinRiesgo+Deslaves+Inundaciones+Desechos+Erupcion+OtrosRiesgos;
	    	riesgosAmbientales=riesgosAmbientales.substring(1, riesgosAmbientales.length());
	    	
	    	objGestionDB.insertFamiliaVariable(idfamilia, 98, fechaactual, riesgosAmbientales,correlativo_tablet,id_estasib_user_sp);*/
	    	//objGestionDB.insertFamiliaVariable(idfamilia, 99, fechaactual, id_CombustibleCocinar,correlativo_tablet,id_estasib_user_sp);
	    }
    	public void actualizarAmenazas(){
    		//getCheckriesgosAmbientales();
	    	
    		/*String riesgosAmbientales=SinRiesgo+Deslaves+Inundaciones+Desechos+Erupcion+OtrosRiesgos;
	    	riesgosAmbientales=riesgosAmbientales.substring(1, riesgosAmbientales.length());
	    	*/
	    	//objGestionDB.updateFamiliaVariable(idfamilia, 98, riesgosAmbientales);
			//objGestionDB.updateFamiliaVariable(idfamilia, 99, id_CombustibleCocinar);
	    }
    	public void EG(){
			String fechaactual=objGestionDB.fechaActual();
			getCheckriesgosAmbientales();
	    	
    		String riesgosAmbientales=SinRiesgo+Deslaves+Inundaciones+Desechos+Erupcion+OtrosRiesgos;
	    	riesgosAmbientales=riesgosAmbientales.substring(1, riesgosAmbientales.length()); 
			if(CombustibleCocinar.equals("") || CombustibleCocinar==null){
				//insert
				objGestionDB.insertFamiliaVariable(idfamilia, 99, fechaactual, id_CombustibleCocinar,correlativo_tablet,id_estasib_user_sp,this.contexto);
			}else{
				//update
				objGestionDB.updateFamiliaVariable(idfamilia, 99, id_CombustibleCocinar, this.contexto);
			}
			if(numerosRiesgoAmbiental.equals("") || numerosRiesgoAmbiental==null){
				//insert
				objGestionDB.insertFamiliaVariable(idfamilia, 98, fechaactual, riesgosAmbientales,correlativo_tablet,id_estasib_user_sp,this.contexto);
			}else{
				//update
				objGestionDB.updateFamiliaVariable(idfamilia, 98, riesgosAmbientales,this.contexto);
			}
			
			}
    	
    	private void getCheckriesgosAmbientales() {
			if(CheckBoxSinRiesgo.isChecked()){SinRiesgo=",0";}else{SinRiesgo="";}
			if(CheckBoxDeslaves.isChecked()){Deslaves=",1";}else{Deslaves="";}
			if(CheckBoxInundaciones.isChecked()){Inundaciones=",2";}else{Inundaciones="";}
			if(CheckBoxDesechos.isChecked()){Desechos=",3";}else{Desechos="";}
			if(CheckBoxErupcion.isChecked()){Erupcion=",4";}else{Erupcion="";}
			if(CheckBoxOtrosRiesgos.isChecked()){OtrosRiesgos=",5";}else{OtrosRiesgos="";}
		}

	}	
}
