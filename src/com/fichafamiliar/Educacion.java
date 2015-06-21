package com.fichafamiliar;

import java.io.IOException;
import java.util.List;

import com.fichafamiliar.Manejo_desechos.GuardarEditarManejoDesechos;
import com.fichafamiliar.Manejo_desechos.RSAsyncManejoDesechos;

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

public class Educacion extends Activity {
	//private Integrantes classIntegrante= new Integrantes();
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	public Common_function objFunctionGeneral;
	String action="";
	int idfamilia=0;
	int idintegrante=0;
	int id_sp;					// id usuario tablet
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	int edad=0;
	
	public String	LeerEscribir,
	EstudiaActualmente,
	UltimoGrado="";
	//para los spinner
		String 		id_LeerEscribir="",
					id_EstudiaActualmente="",
					id_UltimoGrado="";

		Spinner SpnLeerEscribir,
				SpnEstudiaActualmente,
				SpnUltimoGrado;
	private ProgressDialog progressDialog;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_educacion);
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
		
		SpnLeerEscribir 		= (Spinner) findViewById(R.id.SpnLeerEscribir);
		SpnEstudiaActualmente 	= (Spinner) findViewById(R.id.SpnEstudiaActualmente);
		SpnUltimoGrado 			= (Spinner) findViewById(R.id.SpnUltimoGrado);
		
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();*/
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la
			/*										// clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.objFunctionGeneral = new Common_function();
		String fechaNac=objGestionDB.getFechaNacIntegrnate(idintegrante,this.contexto);
		edad=objFunctionGeneral.getEdad(fechaNac);
		
		this.UltimoGrado=objGestionDB.getValorIntegranteSelecionado(idintegrante,120,this.contexto);
	
		/*RSAsyncEducacion leerAsync = new RSAsyncEducacion(this);
		leerAsync.execute(); 
		*/ 
		CargarSpinner(); 
		SpnLeerEscribir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_LeerEscribir = (((SpinnerObjectString)SpnLeerEscribir.getSelectedItem()).getCodigo());
				cargarSpnUltimoGrado(id_LeerEscribir);
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnEstudiaActualmente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_EstudiaActualmente = (((SpinnerObjectString)SpnEstudiaActualmente.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnUltimoGrado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_UltimoGrado = (((SpinnerObjectString)SpnUltimoGrado.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
	}
	public void EG(){
		String fechaactual=objGestionDB.fechaActual();
		 
		if(LeerEscribir.equals("") || LeerEscribir==null){
			//insert
			objGestionDB.insertIntegranteVariable(3, fechaactual, id_LeerEscribir, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 3, id_LeerEscribir, this.contexto);
		}
		if(EstudiaActualmente.equals("") || EstudiaActualmente==null){
			//insert
			objGestionDB.insertIntegranteVariable(111, fechaactual, id_EstudiaActualmente, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 111, id_EstudiaActualmente, this.contexto);
		}
		if(UltimoGrado.equals("") || UltimoGrado==null){
			//insert
			objGestionDB.insertIntegranteVariable(120, fechaactual, id_UltimoGrado, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 120, id_UltimoGrado, this.contexto);
		}
		
	}
	
	public void CargarSpinner() {//descriptor
		
		int	LeerEscribir_pos = 0,
		EstudiaActualmente_pos = 0,
		UltimoGrado_pos;
		ArrayAdapter<SpinnerObjectString> dataAdapterLeerEscribir;
		ArrayAdapter<SpinnerObjectString> dataAdapterEstudiaActualmente;
		ArrayAdapter<SpinnerObjectString> dataAdapterUltimoGrado;
		
		
		
		List<SpinnerObjectString> lablesLeerEscribir = objGestionDB.getLeerEscribir(29,edad,this.contexto);
		List<SpinnerObjectString> lablesEstudiaActualmente = objGestionDB.getCatalogoDescriptor(74,this.contexto);
		
		
		dataAdapterLeerEscribir = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesLeerEscribir);
		dataAdapterEstudiaActualmente = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesEstudiaActualmente);
		
		//if(action.equals("Edit")){
			//recupero el valor que tienen las variable cuando se creo la ficha
			LeerEscribir=objGestionDB.getValorIntegranteSelecionado(idintegrante,3,this.contexto);
			EstudiaActualmente=objGestionDB.getValorIntegranteSelecionado(idintegrante,111,this.contexto);
			/*
			for(int i=0; i<lablesLeerEscribir.size(); i++){
				if(lablesLeerEscribir.get(i).getCodigo().equals(LeerEscribir)){
					LeerEscribir_pos=i;
		        } 
			}*/
			 for(int i=0; i<lablesLeerEscribir.size(); i++){

				if(edad<5){
					LeerEscribir_pos=2;
					SpnLeerEscribir.setClickable(false);}
				else
				{
					if(lablesLeerEscribir.get(i).getCodigo().equals(LeerEscribir)){
						LeerEscribir_pos=i;
			        } 
				}  
	          }
			for(int i=0; i<lablesEstudiaActualmente.size(); i++){
				if(lablesEstudiaActualmente.get(i).getCodigo().equals(EstudiaActualmente)){
					EstudiaActualmente_pos=i;
		        }       
	          }
			
			//}
			
			dataAdapterLeerEscribir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnLeerEscribir.setAdapter(dataAdapterLeerEscribir);
    		SpnLeerEscribir.setSelection(LeerEscribir_pos);
    		
    		dataAdapterEstudiaActualmente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnEstudiaActualmente.setAdapter(dataAdapterEstudiaActualmente);
    		SpnEstudiaActualmente.setSelection(EstudiaActualmente_pos);	
			
		}
	public void click_Educacion(View view) {
		 
		 if(id_LeerEscribir.equals("99")){
			 SpnLeerEscribir.requestFocusFromTouch();
				Toast T_LeerEscribir=Toast.makeText(this, "Seleccione si sabe leer y escribir", Toast.LENGTH_LONG);
				T_LeerEscribir.setGravity(Gravity.CENTER, 0, 0);
				T_LeerEscribir.show();
			}
		 else if(id_EstudiaActualmente.equals("99")){
			 SpnEstudiaActualmente.requestFocusFromTouch();
				Toast T_EstudiaActualmente=Toast.makeText(this, "Seleccione si estudia actualmente", Toast.LENGTH_LONG);
				T_EstudiaActualmente.setGravity(Gravity.CENTER, 0, 0);
				T_EstudiaActualmente.show();
			}
		 else if(id_UltimoGrado.equals("99")){
			 SpnUltimoGrado.requestFocusFromTouch();
				Toast T_UltimoGrado=Toast.makeText(this, "Seleccione el ultimo grado de estudio", Toast.LENGTH_LONG);
				T_UltimoGrado.setGravity(Gravity.CENTER, 0, 0);
				T_UltimoGrado.show();
			}else{
				/*GuardarEditarEducacion GuardarEditar = new GuardarEditarEducacion(this);
				GuardarEditar.execute();*/
				EG();
				  if(action.equals("New")){
					 ToastExito("Se ha guardado la información");			 
					 Intent i = new Intent(contexto, Integrantes.class);
					 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
					 i.putExtra("idintegrante",idintegrante);
					 //classIntegrante.ActivityIntegrantes.finish();
					 finish();
					 startActivity(i);
				 }else{
					 ToastExito("Se ha editado la información");
					 Intent i = new Intent(this, Integrantes.class);
					 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
					 i.putExtra("idintegrante",idintegrante);
					 //classIntegrante.ActivityIntegrantes.finish();
					 finish();
					 startActivity(i);
				 }
			}
	}
	public void cargarSpnUltimoGrado(String id_LeerEscribir){
		List<SpinnerObjectString> lablesUltimoGrado = objGestionDB.getCatalogoUltimoGrado(75,id_LeerEscribir,this.contexto);//50  29
		//List<SpinnerObjectString> lablesUltimoGrado = objGestionDB.getCatalogoDescriptor(75);
		ArrayAdapter<SpinnerObjectString> dataAdapterUltimoGrado= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_list_item_1, lablesUltimoGrado);
		//dataAdapterUltimoGrado = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesUltimoGrado);
		SpnUltimoGrado.setAdapter(dataAdapterUltimoGrado);
			for(int i=0; i<lablesUltimoGrado.size(); i++){
				if(lablesUltimoGrado.get(i).getCodigo().equals(this.UltimoGrado)){
					SpnUltimoGrado.setSelection(i);
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
			toast.setDuration(Toast.LENGTH_SHORT); //duracion del toast
			toast.setView(layout); //asignamos nuestro layout personalizado al objeto Toast
			toast.show(); //mostramos el Toast en pantalla
			}
 
	public class RSAsyncEducacion extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para guardar el valor del spn que trae de la base de datos
		
		//posiciones en las listas
		
		
		public RSAsyncEducacion(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			 
	    	CargarSpinner();
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
	public class GuardarEditarEducacion extends AsyncTask<String, Void, String> {  
		Context contexto;
		 protected String doInBackground(String... params) {
		    		/*if(action.equals("New")){
		    			guardarEducacion();
		    		}else{
		    			actualizarEducacion();
		    		}*/
			 EG();
			return null;
				}
			public GuardarEditarEducacion(Context contexto){	
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
			public void guardarEducacion(){
		    	//String fechaactual=objGestionDB.fechaActual();
		    	//objGestionDB.insertIntegranteVariable(3, fechaactual, id_LeerEscribir, idintegrante, correlativo_tablet, id_estasib_user_sp);
		    	//objGestionDB.insertIntegranteVariable(111, fechaactual, id_EstudiaActualmente, idintegrante, correlativo_tablet, id_estasib_user_sp);
		    	//objGestionDB.insertIntegranteVariable(120, fechaactual, id_UltimoGrado, idintegrante, correlativo_tablet, id_estasib_user_sp);
		    	}
			public void actualizarEducacion(){
		    	//objGestionDB.updateIntegranteVariable(idintegrante, 3, id_LeerEscribir);
		    	//objGestionDB.updateIntegranteVariable(idintegrante, 111, id_EstudiaActualmente);
		    	//objGestionDB.updateIntegranteVariable(idintegrante, 120, id_UltimoGrado);
		    }
		
	}
}
