package com.fichafamiliar;

import java.io.IOException;
import java.util.List;

import com.fichafamiliar.Habitos.GuardarEditarHabitos;
import com.fichafamiliar.Habitos.RSAsyncHabitos;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import General_functions.Common_function;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Salud_sexual extends Activity {
	//private Integrantes classIntegrante= new Integrantes();
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	String action="";
	int idfamilia=0;
	int idintegrante=0;
	int edad=0;
	String sexo="";
	int id_sp;					// id usuario tablet
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	//para los spinner
			String 		id_TomaCitologia,
						id_ExamenMamas,
						id_EmbarazadaActualmente,
						id_MetodoPlanifFam;

			Spinner 	SpnTomaCitologia,
						SpnExamenMamas,
						SpnEmbarazadaActualmente,
						SpnMetodoPlanifFam;
		
			
			public String	TomaCitologia="",
			ExamenMamas="",
			EmbarazadaActualmente="",
			MetodoPlanifFam="";		
			
			
		private ProgressDialog progressDialog;
		public Common_function objFunctionGeneral;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salud_sexual);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		 
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New"
		idfamilia= bundle.getInt("idfamilia"); 
		idintegrante= bundle.getInt("idintegrante"); 
		
		SpnTomaCitologia= (Spinner) findViewById(R.id.SpnTomaCitologia);
		SpnExamenMamas= (Spinner) findViewById(R.id.SpnExamenMamas);
		SpnEmbarazadaActualmente= (Spinner) findViewById(R.id.SpnEmbarazadaActualmente);
		SpnMetodoPlanifFam= (Spinner) findViewById(R.id.SpnMetodoPlanifFam);
		
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
		
		this.objFunctionGeneral = new Common_function();
		String fechaNac=objGestionDB.getFechaNacIntegrnate(idintegrante,this.contexto);
		edad=objFunctionGeneral.getEdad(fechaNac);
		
		sexo=objGestionDB.getSexoIntegrante(idintegrante,this.contexto);
		//ToastExito(sexo);
		
		/*RSAsyncSaludSexual leerAsync = new RSAsyncSaludSexual(this);
		leerAsync.execute();
		*/
		CargarSpinner();
		
		SpnTomaCitologia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_TomaCitologia = (((SpinnerObjectString)SpnTomaCitologia.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnExamenMamas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ExamenMamas = (((SpinnerObjectString)SpnExamenMamas.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnEmbarazadaActualmente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_EmbarazadaActualmente = (((SpinnerObjectString)SpnEmbarazadaActualmente.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnMetodoPlanifFam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_MetodoPlanifFam = (((SpinnerObjectString)SpnMetodoPlanifFam.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
}
	public void CargarSpinner() {//descriptor
		int TomaCitologia_pos = 0,
		ExamenMamas_pos = 0,
		EmbarazadaActualmente_pos = 0,
		MetodoPlanifFam_pos = 0;

		ArrayAdapter<SpinnerObjectString> dataAdapterTomaCitologia;
		ArrayAdapter<SpinnerObjectString> dataAdapterExamenMamas;
		ArrayAdapter<SpinnerObjectString> dataAdapterEmbarazadaActualmente;
		ArrayAdapter<SpinnerObjectString> dataAdapterMetodoPlanifFam; 
		
		List<SpinnerObjectString> lablesTomaCitologia = objGestionDB.getCatalogoTomaCitologia(29,sexo,edad,this.contexto);
		List<SpinnerObjectString> lablesExamenMamas = objGestionDB.getCatalogoExamenMamas(29,sexo,edad,this.contexto);
		List<SpinnerObjectString> lablesEmbarazadaActualmente = objGestionDB.getEmbarazadaActualmente(29,sexo,edad,this.contexto);
		List<SpinnerObjectString> lablesMetodoPlanifFam = objGestionDB.getCatalogoMetodoPlanifica(41,sexo,edad,this.contexto);
		
		dataAdapterTomaCitologia = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTomaCitologia);
		dataAdapterExamenMamas = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesExamenMamas);
		dataAdapterEmbarazadaActualmente = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesEmbarazadaActualmente);
		dataAdapterMetodoPlanifFam = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMetodoPlanifFam);
		
		//if(action.equals("Edit")){
			//recupero el valor que tienen las variable cuando se creo la ficha
			 
			TomaCitologia=objGestionDB.getValorIntegranteSelecionado(idintegrante,87,this.contexto);
			ExamenMamas =objGestionDB.getValorIntegranteSelecionado(idintegrante,74,this.contexto);
			EmbarazadaActualmente=objGestionDB.getValorIntegranteSelecionado(idintegrante,15,this.contexto);
			MetodoPlanifFam=objGestionDB.getValorIntegranteSelecionado(idintegrante,77,this.contexto);
			

			for(int i=0; i<lablesTomaCitologia.size(); i++){
				if(sexo.equals("F")){
					if(edad>=10){
						if(lablesTomaCitologia.get(i).getCodigo().equals(TomaCitologia)){
							TomaCitologia_pos=i;
				        }
					}else{
						TomaCitologia_pos=1;
						SpnTomaCitologia.setClickable(false);
					}
				}else{
					TomaCitologia_pos=1;
					SpnTomaCitologia.setClickable(false);
				}       
	          }
			for(int i=0; i<lablesExamenMamas.size(); i++){
				if(sexo.equals("F")){
					if(edad>=10){
						if(lablesExamenMamas.get(i).getCodigo().equals(ExamenMamas)){
							ExamenMamas_pos=i;
						}
			        }else{
			        	ExamenMamas_pos=1;
						SpnExamenMamas.setClickable(false);
			        }
				}else{
					ExamenMamas_pos=1;
					SpnExamenMamas.setClickable(false);
				}
	          }
			for(int i=0; i<lablesEmbarazadaActualmente.size(); i++){
				if(sexo.equals("F")){
					if(edad>=10){
						if(lablesEmbarazadaActualmente.get(i).getCodigo().equals(EmbarazadaActualmente)){
							EmbarazadaActualmente_pos=i;
						}
			        }else{
			        	EmbarazadaActualmente_pos=1;
						SpnEmbarazadaActualmente.setClickable(false);
			        }
				}else{
					EmbarazadaActualmente_pos=1;
					SpnEmbarazadaActualmente.setClickable(false);
				}	
	          }
			for(int i=0; i<lablesMetodoPlanifFam.size(); i++){
				if(edad>=10){
					if(lablesMetodoPlanifFam.get(i).getCodigo().equals(MetodoPlanifFam)){
						MetodoPlanifFam_pos=i;
			        } 
				}else{
					MetodoPlanifFam_pos=1;
					SpnMetodoPlanifFam.setClickable(false);
				}
				      
	          }
			
			dataAdapterTomaCitologia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnTomaCitologia.setAdapter(dataAdapterTomaCitologia);
    		SpnTomaCitologia.setSelection(TomaCitologia_pos);
    		
    		dataAdapterExamenMamas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnExamenMamas.setAdapter(dataAdapterExamenMamas);
    		SpnExamenMamas.setSelection(ExamenMamas_pos);
    		
    		dataAdapterEmbarazadaActualmente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnEmbarazadaActualmente.setAdapter(dataAdapterEmbarazadaActualmente);
    		SpnEmbarazadaActualmente.setSelection(EmbarazadaActualmente_pos);
    		
    		dataAdapterMetodoPlanifFam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnMetodoPlanifFam.setAdapter(dataAdapterMetodoPlanifFam);
    		SpnMetodoPlanifFam.setSelection(MetodoPlanifFam_pos);
		}
	public void  EG(){
		String fechaactual=objGestionDB.fechaActual();
	 
		if(TomaCitologia.equals("") || TomaCitologia==null){
			//insert
			objGestionDB.insertIntegranteVariable(87, fechaactual, id_TomaCitologia, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 87, id_TomaCitologia, this.contexto);
		}
		if(ExamenMamas.equals("") || ExamenMamas==null){
			//insert
			objGestionDB.insertIntegranteVariable(74, fechaactual, id_ExamenMamas, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 74, id_ExamenMamas, this.contexto);
		}
		if(EmbarazadaActualmente.equals("") || EmbarazadaActualmente==null){
			//insert
			objGestionDB.insertIntegranteVariable(15, fechaactual, id_EmbarazadaActualmente, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 15, id_EmbarazadaActualmente, this.contexto);
		}
		if(MetodoPlanifFam.equals("") || MetodoPlanifFam==null){
		   	//insert
			objGestionDB.insertIntegranteVariable(77, fechaactual, id_MetodoPlanifFam, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
			//Log.i("queryusuario","INSERT");
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 77, id_MetodoPlanifFam, this.contexto);
			//Log.i("queryusuario","update:   metodo= "+MetodoPlanifFam);
		}
}
	public void click_salud_sexual(View view) {
		 if(id_TomaCitologia.equals("99")){
			 SpnTomaCitologia.requestFocusFromTouch();
				Toast T_TomaCitologia=Toast.makeText(this, "Seleccione en la opción toma de citolgía", Toast.LENGTH_LONG);
				T_TomaCitologia.setGravity(Gravity.CENTER, 0, 0);
				T_TomaCitologia.show();
			}else if(id_ExamenMamas.equals("99")){
			 SpnExamenMamas.requestFocusFromTouch();
				Toast T_ExamenMamas=Toast.makeText(this, "Seleccione en la opción de examen manual de mamas", Toast.LENGTH_LONG);
				T_ExamenMamas.setGravity(Gravity.CENTER, 0, 0);
				T_ExamenMamas.show();
			}else if(id_EmbarazadaActualmente.equals("99")){
			 SpnEmbarazadaActualmente.requestFocusFromTouch();
				Toast T_EmbarazadaActualmente=Toast.makeText(this, "Seleccione si esta embarazada actualmente", Toast.LENGTH_LONG);
				T_EmbarazadaActualmente.setGravity(Gravity.CENTER, 0, 0);
				T_EmbarazadaActualmente.show();
			}else if(id_MetodoPlanifFam.equals("99")){
				SpnMetodoPlanifFam.requestFocusFromTouch();
					Toast T_MetodoPlanifFam=Toast.makeText(this, "Seleccione el metodo de planificación familiar", Toast.LENGTH_LONG);
					T_MetodoPlanifFam.setGravity(Gravity.CENTER, 0, 0);
					T_MetodoPlanifFam.show();
			}else{
				/*GuardarEditarSaludSexual GuardarEditar = new GuardarEditarSaludSexual(this);
				GuardarEditar.execute();
				 */
				EG();
				  if(action.equals("New")){
					 ToastExito("Se ha guardado la información");			 
					 Intent i = new Intent(contexto, Integrantes.class);
					 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
					 i.putExtra("idintegrante",idintegrante);
					// classIntegrante.ActivityIntegrantes.finish();
					 //finish();
					 startActivity(i);
				 }else if(action.equals("Edit")){
					 ToastExito("Se ha editado la información");
					 Intent i = new Intent(this, Integrantes.class);
					 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
					 i.putExtra("idintegrante",idintegrante);
					// classIntegrante.ActivityIntegrantes.finish();
					 //finish();
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

public class RSAsyncSaludSexual extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para guardar el valor del spn que trae de la base de datos 
		//posiciones en las listas
		 
		
		public RSAsyncSaludSexual(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			 
	    	//CargarSpinner();
	    return null;
	    }
		@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    		 
    		
    		
    		progressDialog.dismiss();
		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }

	}
public class GuardarEditarSaludSexual extends AsyncTask<String, Void, String> {  
	Context contexto;
	 protected String doInBackground(String... params) {
	    		/*if(action.equals("New")){
	    			guardarSaludSexual();
	    		}else{
	    			actualizarSaludSexual();
	    		}*/
		// EG();
		return null;
			}
		public GuardarEditarSaludSexual(Context contexto){	
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
		public void guardarSaludSexual(){
	    	String fechaactual=objGestionDB.fechaActual();
	    	 
	    	//objGestionDB.insertIntegranteVariable(87, fechaactual, id_TomaCitologia, idintegrante, correlativo_tablet, id_estasib_user_sp);
	    	//objGestionDB.insertIntegranteVariable(74, fechaactual, id_ExamenMamas, idintegrante, correlativo_tablet, id_estasib_user_sp);
	    	//objGestionDB.insertIntegranteVariable(15, fechaactual, id_EmbarazadaActualmente, idintegrante, correlativo_tablet, id_estasib_user_sp);
	    	//objGestionDB.insertIntegranteVariable(77, fechaactual, id_MetodoPlanifFam, idintegrante, correlativo_tablet, id_estasib_user_sp);
		}
		public void actualizarSaludSexual(){
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 87, id_TomaCitologia);
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 74, id_ExamenMamas);
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 15, id_EmbarazadaActualmente);
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 77, id_MetodoPlanifFam);
	    }

	}
}
