package com.fichafamiliar;

import java.io.IOException;
import java.util.List;
 
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

public class Habitos extends Activity {
	//private Integrantes classIntegrante= new Integrantes();
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	public Common_function objFunctionGeneral;
	
	int edad = 0;
	String action="";
	int idfamilia=0;
	int idintegrante=0;
	int id_sp;					// id usuario tablet
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	//para los spinner
			String 		id_EstadoNutricional="",
						id_Fuma="",
						id_ConsumeBebidasEmbriagantes="";

			Spinner SpnEstadoNutricional,
					SpnFuma,
					SpnConsumeBebidasEmbriagantes;
			
			
			
			
			public String	EstadoNutricional="",
					Fuma="",
					ConsumeBebidasEmbriagantes;		
		private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_habitos);
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
		
		 
		SpnEstadoNutricional= (Spinner) findViewById(R.id.SpnEstadoNutricional);
		SpnFuma= (Spinner) findViewById(R.id.SpnFuma);
		SpnConsumeBebidasEmbriagantes= (Spinner) findViewById(R.id.SpnBebidasEmbriagantes);
		
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
		this.objFunctionGeneral = new Common_function();
		String fechaNac=objGestionDB.getFechaNacIntegrnate(idintegrante,this.contexto);
		edad=objFunctionGeneral.getEdad(fechaNac);
		
		/*RSAsyncHabitos leerAsync = new RSAsyncHabitos(this);
		leerAsync.execute();
		*/
		
		CargarSpinner();
		SpnEstadoNutricional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_EstadoNutricional = (((SpinnerObjectString)SpnEstadoNutricional.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnFuma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Fuma = (((SpinnerObjectString)SpnFuma.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnConsumeBebidasEmbriagantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ConsumeBebidasEmbriagantes = (((SpinnerObjectString)SpnConsumeBebidasEmbriagantes.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
	}
	public void click_Habitos(View view) {
		 
		 if(id_EstadoNutricional.equals("99")){
			 SpnEstadoNutricional.requestFocusFromTouch();
				Toast T_EstadoNutricional=Toast.makeText(this, "Seleccione el estado nutricional", Toast.LENGTH_LONG);
				T_EstadoNutricional.setGravity(Gravity.CENTER, 0, 0);
				T_EstadoNutricional.show();
			}
		 else if(id_Fuma.equals("99")){
			 SpnFuma.requestFocusFromTouch();
				Toast T_Fuma=Toast.makeText(this, "Seleccione si fuma", Toast.LENGTH_LONG);
				T_Fuma.setGravity(Gravity.CENTER, 0, 0);
				T_Fuma.show();
			}
		 else if(id_ConsumeBebidasEmbriagantes.equals("99")){
			 SpnConsumeBebidasEmbriagantes.requestFocusFromTouch();
				Toast T_ConsumeBebidasEmbriagantes=Toast.makeText(this, "Seleccione si consume bebidas alcoholicas", Toast.LENGTH_LONG);
				T_ConsumeBebidasEmbriagantes.setGravity(Gravity.CENTER, 0, 0);
				T_ConsumeBebidasEmbriagantes.show();
			}else{
				/*GuardarEditarHabitos GuardarEditar = new GuardarEditarHabitos(this);
				GuardarEditar.execute();
				 */
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
	
	public void  EG(){
		String fechaactual=objGestionDB.fechaActual();
		 
		if(EstadoNutricional.equals("") || EstadoNutricional==null){
			//insert
			objGestionDB.insertIntegranteVariable(11, fechaactual, id_EstadoNutricional, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 11, id_EstadoNutricional, this.contexto);
		}
		if(Fuma.equals("") || Fuma==null){
			//insert
			objGestionDB.insertIntegranteVariable(119, fechaactual, id_Fuma, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 119, id_Fuma, this.contexto);
		}
		if(ConsumeBebidasEmbriagantes.equals("") || ConsumeBebidasEmbriagantes==null){
			//insert
			objGestionDB.insertIntegranteVariable(118, fechaactual, id_ConsumeBebidasEmbriagantes, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 118, id_ConsumeBebidasEmbriagantes, this.contexto);
		}
	}
	public void CargarSpinner() {//descriptor
		int	EstadoNutricional_pos = 0,
		Fuma_pos = 0,
		ConsumeBebidasEmbriagantes_pos = 0;
		ArrayAdapter<SpinnerObjectString> dataAdapterEstadoNutricional;
		ArrayAdapter<SpinnerObjectString> dataAdapterFuma;
		ArrayAdapter<SpinnerObjectString> dataAdapterConsumeBebidasEmbriagantes;
		
					
		List<SpinnerObjectString> lablesEstadoNutricional = objGestionDB.getEstadoNutricional(7,edad,this.contexto);
		List<SpinnerObjectString> lablesFuma = objGestionDB.getCatalogoDescriptor(30,this.contexto);
		List<SpinnerObjectString> lablesConsumeBebidasEmbriagantes = objGestionDB.getCatalogoDescriptor(30,this.contexto);
		
		dataAdapterEstadoNutricional = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesEstadoNutricional);
		dataAdapterFuma = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesFuma);
		dataAdapterConsumeBebidasEmbriagantes = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesConsumeBebidasEmbriagantes);
		//if(action.equals("Edit")){
			//recupero el valor que tienen las variable cuando se creo la ficha
			EstadoNutricional=objGestionDB.getValorIntegranteSelecionado(idintegrante,11,this.contexto);
			Fuma =objGestionDB.getValorIntegranteSelecionado(idintegrante,119,this.contexto);
			ConsumeBebidasEmbriagantes=objGestionDB.getValorIntegranteSelecionado(idintegrante,118,this.contexto);

			for(int i=0; i<lablesEstadoNutricional.size(); i++){
				if (edad>=5){
					EstadoNutricional_pos=1;
					SpnEstadoNutricional.setClickable(false);
					}else{
						if(lablesEstadoNutricional.get(i).getCodigo().equals(EstadoNutricional)){
							EstadoNutricional_pos=i;
				        }
					}
				
				       
	          }
			for(int i=0; i<lablesFuma.size(); i++){
				if(lablesFuma.get(i).getCodigo().equals(Fuma)){
					Fuma_pos=i;
		        }       
	          }
			for(int i=0; i<lablesConsumeBebidasEmbriagantes.size(); i++){
				if(lablesConsumeBebidasEmbriagantes.get(i).getCodigo().equals(ConsumeBebidasEmbriagantes)){
					ConsumeBebidasEmbriagantes_pos=i;
		        }       
	          }
			
			//}
			
			dataAdapterEstadoNutricional.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnEstadoNutricional.setAdapter(dataAdapterEstadoNutricional);
    		SpnEstadoNutricional.setSelection(EstadoNutricional_pos);
    		
    		dataAdapterFuma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnFuma.setAdapter(dataAdapterFuma);
    		SpnFuma.setSelection(Fuma_pos);
    		
    		dataAdapterConsumeBebidasEmbriagantes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnConsumeBebidasEmbriagantes.setAdapter(dataAdapterConsumeBebidasEmbriagantes);
    		SpnConsumeBebidasEmbriagantes.setSelection(ConsumeBebidasEmbriagantes_pos);	
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
 
public class RSAsyncHabitos extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para guardar el valor del spn que trae de la base de datos
		 
		
		//posiciones en las listas
		/*public int	EstadoNutricional_pos,
					Fuma_pos,
					ConsumeBebidasEmbriagantes_pos;
		ArrayAdapter<SpinnerObjectString> dataAdapterEstadoNutricional;
		ArrayAdapter<SpinnerObjectString> dataAdapterFuma;
		ArrayAdapter<SpinnerObjectString> dataAdapterConsumeBebidasEmbriagantes;
		*/
		public RSAsyncHabitos(Context contexto){	
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
    		/* 
    		dataAdapterEstadoNutricional.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnEstadoNutricional.setAdapter(dataAdapterEstadoNutricional);
    		SpnEstadoNutricional.setSelection(this.EstadoNutricional_pos);
    		
    		dataAdapterFuma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnFuma.setAdapter(dataAdapterFuma);
    		SpnFuma.setSelection(this.Fuma_pos);
    		
    		dataAdapterConsumeBebidasEmbriagantes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnConsumeBebidasEmbriagantes.setAdapter(dataAdapterConsumeBebidasEmbriagantes);
    		SpnConsumeBebidasEmbriagantes.setSelection(this.ConsumeBebidasEmbriagantes_pos);
    		*/
    		progressDialog.dismiss();
		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }

	}
	public class GuardarEditarHabitos extends AsyncTask<String, Void, String> {  
		Context contexto;
		 protected String doInBackground(String... params) {
		    		/*if(action.equals("New")){
		    			guardarHabitos();
		    		}else{
		    			actualizarHabitos();
		    		}*/
			// EG();
			return null;
				}
			public GuardarEditarHabitos(Context contexto){	
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
			public void guardarHabitos(){
		    	String fechaactual=objGestionDB.fechaActual();
		    	//objGestionDB.insertIntegranteVariable(11, fechaactual, id_EstadoNutricional, idintegrante, correlativo_tablet, id_estasib_user_sp);
		    	//objGestionDB.insertIntegranteVariable(119, fechaactual, id_Fuma, idintegrante, correlativo_tablet, id_estasib_user_sp);
		    	objGestionDB.insertIntegranteVariable(118, fechaactual, id_ConsumeBebidasEmbriagantes, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		    	}
			public void actualizarHabitos(){
		    	//objGestionDB.updateIntegranteVariable(idintegrante, 11, id_EstadoNutricional);
		    	//objGestionDB.updateIntegranteVariable(idintegrante, 119, id_Fuma);
		    	//objGestionDB.updateIntegranteVariable(idintegrante, 118, id_ConsumeBebidasEmbriagantes);
		    }
		
	}
}
