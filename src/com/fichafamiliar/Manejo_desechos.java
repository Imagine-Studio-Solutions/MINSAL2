package com.fichafamiliar;

import java.io.IOException;
import java.util.List;

import com.fichafamiliar.ServiciosBasicos.GuardarEditarServiciosBasicos;
import com.fichafamiliar.ServiciosBasicos.RSAsyncServiciosBasicos;

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

public class Manejo_desechos extends Activity {
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
	
	//para los spinner
		String 		id_AguasGrices="",
					id_AguasNegras="",
					id_ManejoBasura="";
	public String	    AguasGrices="",
						AguasNegras="",
						ManejoBasura="";
		
		Spinner SpnAguasGrices,
				SpnAguasNegras,
				SpnManejoBasura;
	private ProgressDialog progressDialog;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		setContentView(R.layout.activity_manejo_desechos);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New"
		

		SpnAguasGrices 			= (Spinner) findViewById(R.id.SpnManejo_aguas_grises);
		SpnAguasNegras 	= (Spinner) findViewById(R.id.SpnManejo_aguas_negras);
		SpnManejoBasura 	= (Spinner) findViewById(R.id.SpnManejo_basura);
		
		
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
		}
		*/
		this.idfamilia= bundle.getInt("idfamilia");
		
		RSAsyncManejoDesechos leerAsync = new RSAsyncManejoDesechos(this);
		leerAsync.execute();
		
		SpnAguasGrices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_AguasGrices = (((SpinnerObjectString)SpnAguasGrices.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});SpnAguasNegras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_AguasNegras = (((SpinnerObjectString)SpnAguasNegras.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});SpnManejoBasura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ManejoBasura = (((SpinnerObjectString)SpnManejoBasura.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	public void click_manejo_desechos(View view) {
  
		 if(id_AguasGrices.equals("99")){
			 SpnAguasGrices.requestFocusFromTouch();
				Toast T_AguasGrices=Toast.makeText(this, "Seleccione el alumbrado utilizado principalmente", Toast.LENGTH_LONG);
				T_AguasGrices.setGravity(Gravity.CENTER, 0, 0);
				T_AguasGrices.show();
			}
		 if(id_AguasNegras.equals("99")){
			 SpnAguasNegras.requestFocusFromTouch();
				Toast T_AguasNegras=Toast.makeText(this, "Seleccione el alumbrado utilizado principalmente", Toast.LENGTH_LONG);
				T_AguasNegras.setGravity(Gravity.CENTER, 0, 0);
				T_AguasNegras.show();
			}
		 if(id_ManejoBasura.equals("99")){
			 SpnManejoBasura.requestFocusFromTouch();
				Toast T_ManejoBasura=Toast.makeText(this, "Seleccione el alumbrado utilizado principalmente", Toast.LENGTH_LONG);
				T_ManejoBasura.setGravity(Gravity.CENTER, 0, 0);
				T_ManejoBasura.show();
			}else{
				GuardarEditarManejoDesechos GuardarEditar = new GuardarEditarManejoDesechos(this);
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
					 //classVerDetalleFicha.ActivityVerDetalleFicha.finish();
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
 
	public class RSAsyncManejoDesechos extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para guardar el valor del spn que trae de la base de datos
		
		//posiciones en las listas
		public int	AguasGrices_pos,
					AguasNegras_pos,
					ManejoBasura_pos;
		ArrayAdapter<SpinnerObjectString> dataAdapterAguasGrices;
		ArrayAdapter<SpinnerObjectString> dataAdapterAguasNegras;
		ArrayAdapter<SpinnerObjectString> dataAdapterManejoBasura;
		public RSAsyncManejoDesechos(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			if (action.equals("Edit")){
				//CargarCheckBox();	
	    	}
	    	CargarSpinner();
	    return null;
	    }
		@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    	 
    		dataAdapterAguasGrices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnAguasGrices.setAdapter(dataAdapterAguasGrices);
    		SpnAguasGrices.setSelection(this.AguasGrices_pos);
    		
    		dataAdapterAguasNegras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnAguasNegras.setAdapter(dataAdapterAguasNegras);
    		SpnAguasNegras.setSelection(this.AguasNegras_pos);
    		
    		dataAdapterManejoBasura.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnManejoBasura.setAdapter(dataAdapterManejoBasura);
    		SpnManejoBasura.setSelection(this.ManejoBasura_pos);
    		
    		progressDialog.dismiss();
		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }
		public void CargarSpinner() {//descriptor
			List<SpinnerObjectString> lablesAguasGrices = objGestionDB.getCatalogoDescriptor(22,this.contexto);
			List<SpinnerObjectString> lablesAguasNegras = objGestionDB.getCatalogoDescriptor(23,this.contexto);
			List<SpinnerObjectString> lablesManejoBasura = objGestionDB.getCatalogoDescriptor(69,this.contexto);
			
			dataAdapterAguasGrices = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesAguasGrices);
			dataAdapterAguasNegras = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesAguasNegras);
			dataAdapterManejoBasura = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesManejoBasura);
			//if(action.equals("Edit")){
				//recupero el valor que tienen las variable cuando se creo la ficha
				AguasGrices=objGestionDB.getValorVariableSelecionado(idfamilia,38,this.contexto);
				AguasNegras=objGestionDB.getValorVariableSelecionado(idfamilia,39,this.contexto);
				ManejoBasura=objGestionDB.getValorVariableSelecionado(idfamilia,106,this.contexto);
				
				for(int i=0; i<lablesAguasGrices.size(); i++){
					if(lablesAguasGrices.get(i).getCodigo().equals(AguasGrices)){
						AguasGrices_pos=i;
			        }       
		          }
				for(int i=0; i<lablesAguasNegras.size(); i++){
					if(lablesAguasNegras.get(i).getCodigo().equals(AguasNegras)){
						AguasNegras_pos=i;
			        }       
		          }
				for(int i=0; i<lablesManejoBasura.size(); i++){
					if(lablesManejoBasura.get(i).getCodigo().equals(ManejoBasura)){
						ManejoBasura_pos=i;
			        }       
		          }
				
				//}
			}
	}
	public class GuardarEditarManejoDesechos extends AsyncTask<String, Void, String> {  
		Context contexto;
		 protected String doInBackground(String... params) {
		    		/*if(action.equals("New")){
		    			guardarManejoDesechos();
		    		}else{
		    			actualizarManejoDesechos();
		    		}*/
			 EG();
			return null;
				}
			public GuardarEditarManejoDesechos(Context contexto){	
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
			public void guardarManejoDesechos(){
		    	String fechaactual=objGestionDB.fechaActual();
		    	//objGestionDB.insertFamiliaVariable(idfamilia, 38, fechaactual, id_AguasGrices,correlativo_tablet,id_estasib_user_sp);
		    	//objGestionDB.insertFamiliaVariable(idfamilia, 39, fechaactual, id_AguasNegras,correlativo_tablet,id_estasib_user_sp);
		    	//objGestionDB.insertFamiliaVariable(idfamilia, 106, fechaactual, id_ManejoBasura,correlativo_tablet,id_estasib_user_sp);
		    }
			public void actualizarManejoDesechos(){
		    	//objGestionDB.updateFamiliaVariable(idfamilia, 38, id_AguasGrices);
				//objGestionDB.updateFamiliaVariable(idfamilia, 39, id_AguasNegras);
				//objGestionDB.updateFamiliaVariable(idfamilia, 106, id_ManejoBasura);
		    }
			public void EG(){
				String fechaactual=objGestionDB.fechaActual();
				
				if(AguasGrices.equals("") || AguasGrices==null){
					//insert
					objGestionDB.insertFamiliaVariable(idfamilia, 38, fechaactual, id_AguasGrices,correlativo_tablet,id_estasib_user_sp,this.contexto);
				}else{
					//update
					objGestionDB.updateFamiliaVariable(idfamilia, 38, id_AguasGrices,this.contexto);
				}
				
				if(AguasNegras.equals("") || AguasNegras==null){
					//insert
					objGestionDB.insertFamiliaVariable(idfamilia, 39, fechaactual, id_AguasNegras,correlativo_tablet,id_estasib_user_sp,this.contexto);
				}else{
					//update
					objGestionDB.updateFamiliaVariable(idfamilia, 39, id_AguasNegras,this.contexto);
				}
				
				if(ManejoBasura.equals("") || ManejoBasura==null){
					//insert
					objGestionDB.insertFamiliaVariable(idfamilia, 106, fechaactual, id_ManejoBasura,correlativo_tablet,id_estasib_user_sp,this.contexto);
					
				}else{
					//update
					objGestionDB.updateFamiliaVariable(idfamilia, 106, id_ManejoBasura,this.contexto);
					
				}
			} 
			
	}
}
