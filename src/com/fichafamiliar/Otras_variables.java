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

public class Otras_variables extends Activity {
	//private Integrantes classIntegrante= new Integrantes();
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	String action="";
	int edad=0;
	int idfamilia=0;
	int idintegrante=0;
	int id_sp;					// id usuario tablet
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	//para los spinner
			String 		id_Menor14Trabaja,
						id_FormaTrabajoInfantil,
						id_Menores18BajoCuidadoDe,
						id_EsquemaVacunacion,
						id_CuandoEnfermaAcudeA,
						id_EvaluacionDispensarizacion;

			Spinner 	SpnMenor14Trabaja,
						SpnFormaTrabajoInfantil,
						SpnMenores18BajoCuidadoDe,
						SpnEsquemaVacunacion,
						SpnCuandoEnfermaAcudeA,
						SpnEvaluacionDispensarizacion;
		
			public String	Menor14Trabaja,
			FormaTrabajoInfantil,
			Menores18BajoCuidadoDe,
			EsquemaVacunacion,
			CuandoEnfermaAcudeA,
			EvaluacionDispensarizacion;	
			
		private ProgressDialog progressDialog;
		public Common_function objFunctionGeneral;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_otras_variables);
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
		
		SpnMenor14Trabaja= (Spinner) findViewById(R.id.SpnMenor14Trabaja);
		SpnFormaTrabajoInfantil= (Spinner) findViewById(R.id.SpnFormaTrabajoInfantil);
		SpnMenores18BajoCuidadoDe= (Spinner) findViewById(R.id.SpnMenores18BajoCuidadoDe);
		SpnEsquemaVacunacion= (Spinner) findViewById(R.id.SpnEsquemaVacunacion);
		SpnCuandoEnfermaAcudeA= (Spinner) findViewById(R.id.SpnCuandoEnfermaAcudeA);
		SpnEvaluacionDispensarizacion = (Spinner) findViewById(R.id.SpnEvaluacionDispensarizacion);
		
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
		
		
		/*RSAsyncOtrasVariables leerAsync = new RSAsyncOtrasVariables(this);
		leerAsync.execute();
		 */
		CargarSpinner();
		SpnMenor14Trabaja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Menor14Trabaja = (((SpinnerObjectString)SpnMenor14Trabaja.getSelectedItem()).getCodigo());
				cargarSpnFormaTrabajoInfantil(id_Menor14Trabaja);
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		SpnFormaTrabajoInfantil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_FormaTrabajoInfantil = (((SpinnerObjectString)SpnFormaTrabajoInfantil.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		SpnMenores18BajoCuidadoDe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Menores18BajoCuidadoDe = (((SpinnerObjectString)SpnMenores18BajoCuidadoDe.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnEsquemaVacunacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_EsquemaVacunacion = (((SpinnerObjectString)SpnEsquemaVacunacion.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnCuandoEnfermaAcudeA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_CuandoEnfermaAcudeA = (((SpinnerObjectString)SpnCuandoEnfermaAcudeA.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnEvaluacionDispensarizacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_EvaluacionDispensarizacion = (((SpinnerObjectString)SpnEvaluacionDispensarizacion.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	public void EG(){
		String fechaactual=objGestionDB.fechaActual();
		 
		if(Menor14Trabaja.equals("") || Menor14Trabaja==null){
			//insert
			objGestionDB.insertIntegranteVariable(78,  fechaactual, id_Menor14Trabaja, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
	    	
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 78,  id_Menor14Trabaja, this.contexto);
		}
		if(FormaTrabajoInfantil.equals("") || FormaTrabajoInfantil==null){
			//insert
			objGestionDB.insertIntegranteVariable(88,  fechaactual, id_FormaTrabajoInfantil, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
	    	
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 88,  id_FormaTrabajoInfantil, this.contexto);
		}
		if(Menores18BajoCuidadoDe.equals("") || Menores18BajoCuidadoDe==null){
			//insert
			objGestionDB.insertIntegranteVariable(79,  fechaactual, id_Menores18BajoCuidadoDe, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
	    	
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 79,  id_Menores18BajoCuidadoDe, this.contexto);
		}
		if(EsquemaVacunacion.equals("") || EsquemaVacunacion==null){
			//insert
			objGestionDB.insertIntegranteVariable(80,  fechaactual, id_EsquemaVacunacion, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
	    	
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 80,  id_EsquemaVacunacion, this.contexto);
		}
		if(CuandoEnfermaAcudeA.equals("") || CuandoEnfermaAcudeA==null){
			//insert
			objGestionDB.insertIntegranteVariable(114, fechaactual, id_CuandoEnfermaAcudeA, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
	    	
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 114, id_CuandoEnfermaAcudeA, this.contexto);
		}
		if(EvaluacionDispensarizacion.equals("") || EvaluacionDispensarizacion==null){
			//insert
			objGestionDB.insertIntegranteVariable(23,  fechaactual, id_EvaluacionDispensarizacion, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
	    	
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 23,  id_EvaluacionDispensarizacion, this.contexto);
		}
}
	public void CargarSpinner() {//descriptor
		int		Menor14Trabaja_pos = 0,
		
		Menores18BajoCuidadoDe_pos = 0,
		EsquemaVacunacion_pos = 0,
		CuandoEnfermaAcudeA_pos = 0,
		EvaluacionDispensarizacion_pos = 0;

		ArrayAdapter<SpinnerObjectString> dataAdapterMenor14Trabaja;
		
		ArrayAdapter<SpinnerObjectString> dataAdapterMenores18BajoCuidadoDe;
		ArrayAdapter<SpinnerObjectString> dataAdapterEsquemaVacunacion;
		ArrayAdapter<SpinnerObjectString> dataAdapterCuandoEnfermaAcudeA;
		ArrayAdapter<SpinnerObjectString> dataAdapterEvaluacionDispensarizacion;
		
		
		List<SpinnerObjectString> lablesMenor14Trabaja = objGestionDB.getCatalogoMenor14Trabaja(29,edad,this.contexto);
	
		List<SpinnerObjectString> lablesMenores18BajoCuidadoDe = objGestionDB.getCatalogoMenores18BajoCuidadoDe(42,edad,this.contexto);
		List<SpinnerObjectString> lablesEsquemaVacunacion = objGestionDB.getCatalogoDescriptor(43,this.contexto);
		List<SpinnerObjectString> lablesCuandoEnfermaAcudeA = objGestionDB.getCatalogoDescriptor(81,this.contexto);
		List<SpinnerObjectString> lablesEvaluacionDispensarizacion = objGestionDB.getCatalogoDescriptorDispensarizacion(12,this.contexto);
		
		dataAdapterMenor14Trabaja = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMenor14Trabaja);
		
		dataAdapterMenores18BajoCuidadoDe = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMenores18BajoCuidadoDe);
		dataAdapterEsquemaVacunacion = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesEsquemaVacunacion);
		dataAdapterCuandoEnfermaAcudeA = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesCuandoEnfermaAcudeA);
		dataAdapterEvaluacionDispensarizacion = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesEvaluacionDispensarizacion);

		
		//if(action.equals("Edit")){
			
			 		Menor14Trabaja=objGestionDB.getValorIntegranteSelecionado(idintegrante,78,this.contexto);
					FormaTrabajoInfantil=objGestionDB.getValorIntegranteSelecionado(idintegrante,88,this.contexto);
					Menores18BajoCuidadoDe=objGestionDB.getValorIntegranteSelecionado(idintegrante,79,this.contexto);
					EsquemaVacunacion=objGestionDB.getValorIntegranteSelecionado(idintegrante,80,this.contexto);
					CuandoEnfermaAcudeA=objGestionDB.getValorIntegranteSelecionado(idintegrante,114,this.contexto);
					EvaluacionDispensarizacion=objGestionDB.getValorIntegranteSelecionado(idintegrante,23,this.contexto);
			
			for(int i=0; i<lablesMenor14Trabaja.size(); i++){
				if(edad<14){
					if(lablesMenor14Trabaja.get(i).getCodigo().equals(Menor14Trabaja)){
						Menor14Trabaja_pos=i;
			        }
				}else{
					Menor14Trabaja_pos=1;
					SpnMenor14Trabaja.setClickable(false);
				}
				       
	          }
			
			for(int i=0; i<lablesMenores18BajoCuidadoDe.size(); i++){
				if(edad<18){
					if(lablesMenores18BajoCuidadoDe.get(i).getCodigo().equals(Menores18BajoCuidadoDe)){
						Menores18BajoCuidadoDe_pos=i;
			        }
				}else{
					Menores18BajoCuidadoDe_pos=1;
					SpnMenores18BajoCuidadoDe.setClickable(false);
				}       
	          }
			for(int i=0; i<lablesEsquemaVacunacion.size(); i++){
				if(lablesEsquemaVacunacion.get(i).getCodigo().equals(EsquemaVacunacion)){
					EsquemaVacunacion_pos=i;
		        }       
	          }
			for(int i=0; i<lablesCuandoEnfermaAcudeA.size(); i++){
				if(lablesCuandoEnfermaAcudeA.get(i).getCodigo().equals(CuandoEnfermaAcudeA)){
					CuandoEnfermaAcudeA_pos=i;
		        }       
	          }
			for(int i=0; i<lablesEvaluacionDispensarizacion.size(); i++){
				if(lablesEvaluacionDispensarizacion.get(i).getCodigo().equals(EvaluacionDispensarizacion)){
					EvaluacionDispensarizacion_pos=i;
		        }       
	          }
			
			//}
			dataAdapterMenor14Trabaja.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnMenor14Trabaja.setAdapter(dataAdapterMenor14Trabaja);
    		SpnMenor14Trabaja.setSelection(Menor14Trabaja_pos);
    		
    		
    		
    		dataAdapterMenores18BajoCuidadoDe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnMenores18BajoCuidadoDe.setAdapter(dataAdapterMenores18BajoCuidadoDe);
    		SpnMenores18BajoCuidadoDe.setSelection(Menores18BajoCuidadoDe_pos);
    		
    		dataAdapterEsquemaVacunacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnEsquemaVacunacion.setAdapter(dataAdapterEsquemaVacunacion);
    		SpnEsquemaVacunacion.setSelection(EsquemaVacunacion_pos);
    		
    		dataAdapterCuandoEnfermaAcudeA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnCuandoEnfermaAcudeA.setAdapter(dataAdapterCuandoEnfermaAcudeA);
    		SpnCuandoEnfermaAcudeA.setSelection(CuandoEnfermaAcudeA_pos);
    		
    		dataAdapterEvaluacionDispensarizacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnEvaluacionDispensarizacion.setAdapter(dataAdapterEvaluacionDispensarizacion);
    		SpnEvaluacionDispensarizacion.setSelection(EvaluacionDispensarizacion_pos);
		}
	public void cargarSpnFormaTrabajoInfantil(String id_Menor14Trabaja){
		List<SpinnerObjectString> lablesFormaTrabajoInfantil = objGestionDB.getCatalogoFormaTrabajoInfantil(id_Menor14Trabaja,this.contexto);//50  29
		ArrayAdapter<SpinnerObjectString> dataAdapterFormaTrabajoInfantil= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_list_item_1, lablesFormaTrabajoInfantil);
		SpnFormaTrabajoInfantil.setAdapter(dataAdapterFormaTrabajoInfantil);
		SpnFormaTrabajoInfantil.setClickable(true);
		for(int i=0; i<lablesFormaTrabajoInfantil.size(); i++){
			if(id_Menor14Trabaja.equals("NA") || id_Menor14Trabaja.equals("0")){
				SpnFormaTrabajoInfantil.setSelection(1);
				SpnFormaTrabajoInfantil.setClickable(false);
				
			}else{
				if(lablesFormaTrabajoInfantil.get(i).getCodigo().equals(FormaTrabajoInfantil)){
					SpnFormaTrabajoInfantil.setSelection(i);
		        }
			}
          }
		
	}
	
	/*
	 private void loadSpinnerDataSp_depto() {
		List<SpinnerObject> lables = objGestionDB.getDepto();
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_depto.setAdapter(dataAdapter);
		if(action.equals("Edit")){
			 int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getId()==objGestionDB.codigo_departamento){
					pos=i;
		        }       
	          }
			sp_depto.setSelection(pos);
		} 
	}
	 */
	
	
	public void click_otras_variables(View view) {
		 
		 if(id_Menor14Trabaja.equals("99")){
			 SpnMenor14Trabaja.requestFocusFromTouch();
				Toast T_EstadoNutricional=Toast.makeText(this, "Seleccione la opción para menores de 14 años", Toast.LENGTH_LONG);
				T_EstadoNutricional.setGravity(Gravity.CENTER, 0, 0);
				T_EstadoNutricional.show();
			}
		 else if(id_FormaTrabajoInfantil.equals("99")){
			 SpnFormaTrabajoInfantil.requestFocusFromTouch();
				Toast T_Menor14Trabaja=Toast.makeText(this, "Seleccione la opción Forma de trabajo infantil", Toast.LENGTH_LONG);
				T_Menor14Trabaja.setGravity(Gravity.CENTER, 0, 0);
				T_Menor14Trabaja.show();
			}
		 else if(id_Menores18BajoCuidadoDe.equals("99")){
			 SpnMenores18BajoCuidadoDe.requestFocusFromTouch();
				Toast T_Menores18BajoCuidadoDe=Toast.makeText(this, "Seleccione bajo cuidado de quien quedan los menores de 18 años", Toast.LENGTH_LONG);
				T_Menores18BajoCuidadoDe.setGravity(Gravity.CENTER, 0, 0);
				T_Menores18BajoCuidadoDe.show();
			}
		 else if(id_EsquemaVacunacion.equals("99")){
			 SpnEsquemaVacunacion.requestFocusFromTouch();
				Toast T_EsquemaVacunacion=Toast.makeText(this, "Seleccione en la opción de esquema de vacunación", Toast.LENGTH_LONG);
				T_EsquemaVacunacion.setGravity(Gravity.CENTER, 0, 0);
				T_EsquemaVacunacion.show();
			}
		 else if(id_CuandoEnfermaAcudeA.equals("99")){
			 SpnCuandoEnfermaAcudeA.requestFocusFromTouch();
				Toast T_CuandoEnfermaAcudeA=Toast.makeText(this, "Seleccione, cuando se enferma acude a", Toast.LENGTH_LONG);
				T_CuandoEnfermaAcudeA.setGravity(Gravity.CENTER, 0, 0);
				T_CuandoEnfermaAcudeA.show();
			}
		 else if(id_EvaluacionDispensarizacion.equals("99")){
			 SpnEvaluacionDispensarizacion.requestFocusFromTouch();
				Toast T_EvaluacionDispensarizacion=Toast.makeText(this, "Seleccione en la opción de evaluación de la dispenzarización", Toast.LENGTH_LONG);
				T_EvaluacionDispensarizacion.setGravity(Gravity.CENTER, 0, 0);
				T_EvaluacionDispensarizacion.show();
			}else{
				/*GuardarOtrasVariables GuardarEditar = new GuardarOtrasVariables(this);
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
	 
	public class RSAsyncOtrasVariables extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para guardar el valor del spn que trae de la base de datos
		 
		
		//posiciones en las listas
		/*public int		Menor14Trabaja_pos,
						
						Menores18BajoCuidadoDe_pos,
						EsquemaVacunacion_pos,
						CuandoEnfermaAcudeA_pos,
						EvaluacionDispensarizacion_pos;
		
		ArrayAdapter<SpinnerObjectString> dataAdapterMenor14Trabaja;
		
		ArrayAdapter<SpinnerObjectString> dataAdapterMenores18BajoCuidadoDe;
		ArrayAdapter<SpinnerObjectString> dataAdapterEsquemaVacunacion;
		ArrayAdapter<SpinnerObjectString> dataAdapterCuandoEnfermaAcudeA;
		ArrayAdapter<SpinnerObjectString> dataAdapterEvaluacionDispensarizacion;
		*/
		public RSAsyncOtrasVariables(Context contexto){	
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
    		 
    		/*dataAdapterMenor14Trabaja.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnMenor14Trabaja.setAdapter(dataAdapterMenor14Trabaja);
    		SpnMenor14Trabaja.setSelection(this.Menor14Trabaja_pos);
    		
    		
    		
    		dataAdapterMenores18BajoCuidadoDe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnMenores18BajoCuidadoDe.setAdapter(dataAdapterMenores18BajoCuidadoDe);
    		SpnMenores18BajoCuidadoDe.setSelection(this.Menores18BajoCuidadoDe_pos);
    		
    		dataAdapterEsquemaVacunacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnEsquemaVacunacion.setAdapter(dataAdapterEsquemaVacunacion);
    		SpnEsquemaVacunacion.setSelection(this.EsquemaVacunacion_pos);
    		
    		dataAdapterCuandoEnfermaAcudeA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnCuandoEnfermaAcudeA.setAdapter(dataAdapterCuandoEnfermaAcudeA);
    		SpnCuandoEnfermaAcudeA.setSelection(this.CuandoEnfermaAcudeA_pos);
    		
    		dataAdapterEvaluacionDispensarizacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnEvaluacionDispensarizacion.setAdapter(dataAdapterEvaluacionDispensarizacion);
    		SpnEvaluacionDispensarizacion.setSelection(this.EvaluacionDispensarizacion_pos);
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
	public class GuardarOtrasVariables extends AsyncTask<String, Void, String> {  
		Context contexto;
		 protected String doInBackground(String... params) {
		    		/*if(action.equals("New")){
		    			guardarOtrasVariables();
		    		}else{
		    			actualizarOtrasVariables();
		    		}*/
			 //EG();
			return null;
				}
			public GuardarOtrasVariables(Context contexto){	
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
			public void guardarOtrasVariables(){
		    	String fechaactual=objGestionDB.fechaActual();
		    	}
			}
			public void actualizarOtrasVariables(){
		    	
		    }
		
}
