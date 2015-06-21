package com.fichafamiliar;

import java.io.IOException;
import java.util.List;

import com.fichafamiliar.Ficha_familia_ativity_01.GuardarEditarFicha;
import com.fichafamiliar.Ficha_familia_ativity_01.RSAsync;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Caracteristicas_vivienda extends Activity {
	//private Ver_detalle_ficha classVerDetalleFicha= new Ver_detalle_ficha();
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	String action="";
	int idfamilia;
	int id_sp;// id usuario tablet
	int id_estasib_user_sp; // id establecimiento
	String  nombreusuario; //nombre usuario
	int  id_sibasi;//id sibasi
	int correlativo_tablet;//correlativo tablet
	public String 	
	TipoVivienda,
	TipoTenenciaVivienda,
	MaterialParedes,
	MaterialPiso,
	MaterialTecho;
	public String 	
			idTipoVivienda,
			idTipoTenenciaVivienda,
			idMaterialParedes,
			idMaterialPiso,
			idMaterialTecho;
	public Spinner 
			SpnTipoVivienda,
			SpnTipoTenenciaVivienda,
			SpnMaterialParedes, 
			SpnMaterialPiso, 
			SpnMaterialTecho;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_caracteristicas_vivienda);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		
		 id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		 id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		 nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		 id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		 correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New" 
		this.action=bundle.getString("action"); 
		
		SpnTipoVivienda = (Spinner) findViewById(R.id.SpnTipoVivienda);
		SpnTipoTenenciaVivienda = (Spinner) findViewById(R.id.SpnTenenciaVivienda);
		SpnMaterialParedes = (Spinner) findViewById(R.id.SpnMaterialParedes);
		SpnMaterialPiso = (Spinner) findViewById(R.id.SpnMaterialPiso);
		SpnMaterialTecho = (Spinner) findViewById(R.id.SpnMaterialTecho);
		
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();*/
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la
		/*											// clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		this.idfamilia= bundle.getInt("idfamilia");
		RSAsyncCaracteristicasVivienda leerAsync = new RSAsyncCaracteristicasVivienda(this);
		leerAsync.execute();
			//si viene de editar, llamar  al metodo que recupera la informacion de la vivienda
			
			
			//objGestionDB.getCaracteristicasViviendaInfoEdit(idfamilia);
 

		SpnTipoVivienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				idTipoVivienda = (((SpinnerObjectString)SpnTipoVivienda.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnTipoTenenciaVivienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				idTipoTenenciaVivienda = (((SpinnerObjectString)SpnTipoTenenciaVivienda.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnMaterialParedes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				idMaterialParedes = (((SpinnerObjectString)SpnMaterialParedes.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnMaterialPiso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				idMaterialPiso = (((SpinnerObjectString)SpnMaterialPiso.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnMaterialTecho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				idMaterialTecho = (((SpinnerObjectString)SpnMaterialTecho.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
 
	public void clic_caracteristicas_vivienda(View view) {
		if(idTipoVivienda.equals("99")){
			SpnTipoVivienda.requestFocusFromTouch();
			Toast tipoVivienda=Toast.makeText(this, "Seleccione el tipo de vivienda", Toast.LENGTH_LONG);
			tipoVivienda.setGravity(Gravity.CENTER, 0, 0);
			tipoVivienda.show();
		}
		else if(idTipoTenenciaVivienda.equals("99")){
			SpnTipoTenenciaVivienda.requestFocusFromTouch();
			Toast tenencia_vivienda=Toast.makeText(this, "Seleccione el tipo de tenenencia de la vivienda", Toast.LENGTH_LONG);
			tenencia_vivienda.setGravity(Gravity.CENTER, 0, 0);
			tenencia_vivienda.show();
		}else if(idMaterialParedes.equals("99")){
			SpnMaterialParedes.requestFocusFromTouch();
			Toast material_paredes=Toast.makeText(this, "Seleccione el material de las paredes", Toast.LENGTH_LONG);
			material_paredes.setGravity(Gravity.CENTER, 0, 0);
			material_paredes.show();
		}else if(idMaterialPiso.equals("99")){
			SpnMaterialPiso.requestFocusFromTouch();
			Toast material_piso=Toast.makeText(this, "Seleccione el material del piso", Toast.LENGTH_LONG);
			material_piso.setGravity(Gravity.CENTER, 0, 0);
			material_piso.show();
		}else if(idMaterialTecho.equals("99")){
			SpnMaterialTecho.requestFocusFromTouch();
			Toast material_techo=Toast.makeText(this, "Seleccione el material del techo", Toast.LENGTH_LONG);
			material_techo.setGravity(Gravity.CENTER, 0, 0);
			material_techo.show();
		}else{
			GuardarEditarFicha GuardarEditar = new GuardarEditarFicha(this);
			GuardarEditar.execute();
			if(action.equals("New")){
				 ToastExito("Se han guardado la información");			 
				 Intent i = new Intent(contexto, Ver_detalle_ficha.class);
				 i.putExtra("busquedaPor",3);
				 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
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
	
	
	
	
	public class RSAsyncCaracteristicasVivienda extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		
		//posiciones en las listas
		public int 	
				TipoVivienda_pos,
				TipoTenenciaVivienda_pos,
				MaterialParedes_pos,
				MaterialPiso_pos,
				MaterialTecho_pos;
		
		ArrayAdapter<SpinnerObjectString> dataAdapterTipoVivienda;
		ArrayAdapter<SpinnerObjectString> dataAdapterTenenciaVivienda;
		ArrayAdapter<SpinnerObjectString> dataAdapterMaterialParedes;
		ArrayAdapter<SpinnerObjectString> dataAdapterMaterialPiso;
		ArrayAdapter<SpinnerObjectString> dataAdapterMaterialTecho;
		
	
		

		public RSAsyncCaracteristicasVivienda(Context contexto){	
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
    		dataAdapterTipoVivienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterTenenciaVivienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterMaterialParedes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterMaterialPiso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterMaterialTecho.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		
    		
    		SpnTipoVivienda.setAdapter(dataAdapterTipoVivienda);
    		SpnTipoVivienda.setSelection(this.TipoVivienda_pos);
    		
    		SpnTipoTenenciaVivienda.setAdapter(dataAdapterTenenciaVivienda);
    		SpnTipoTenenciaVivienda.setSelection(this.TipoTenenciaVivienda_pos);
    		
    		SpnMaterialParedes.setAdapter(dataAdapterMaterialParedes);
    		SpnMaterialParedes.setSelection(this.MaterialParedes_pos);
    		
    		SpnMaterialPiso.setAdapter(dataAdapterMaterialPiso);
    		SpnMaterialPiso.setSelection(this.MaterialPiso_pos);
    		
    		SpnMaterialTecho.setAdapter(dataAdapterMaterialTecho);
    		SpnMaterialTecho.setSelection(this.MaterialTecho_pos);
    		progressDialog.dismiss();
    		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
    		//NumHabitaciones.setText("dsfasdf");
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }
		public void CargarSpinner() {																	  //descriptor
			
			List<SpinnerObjectString> lablesTipoVivienda 				 = objGestionDB.getCatalogoDescriptor(47,this.contexto);
			List<SpinnerObjectString> lablesTeneciaVivienda 			 = objGestionDB.getCatalogoDescriptor(54,this.contexto);
			List<SpinnerObjectString> lablesMaterialParedes 			 = objGestionDB.getCatalogoDescriptor(55,this.contexto);
			List<SpinnerObjectString> lablesMaterialPiso    			 = objGestionDB.getCatalogoDescriptor(56,this.contexto);
			List<SpinnerObjectString> lablesMaterialTecho   			 = objGestionDB.getCatalogoDescriptor(57,this.contexto);		
			
			
			dataAdapterTipoVivienda					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTipoVivienda);
			dataAdapterTenenciaVivienda				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTeneciaVivienda);
			dataAdapterMaterialParedes				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMaterialParedes);
			dataAdapterMaterialPiso					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMaterialPiso);
			dataAdapterMaterialTecho				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMaterialTecho);
		//	if(action.equals("Edit")){
				//recupero el valor que tienen las variable cuando se creo la ficha
				TipoVivienda=objGestionDB.getValorVariableSelecionado(idfamilia,83,this.contexto);
				TipoTenenciaVivienda=objGestionDB.getValorVariableSelecionado(idfamilia,91,this.contexto);
				MaterialParedes=objGestionDB.getValorVariableSelecionado(idfamilia,92,this.contexto);
				MaterialPiso=objGestionDB.getValorVariableSelecionado(idfamilia,93,this.contexto);
				MaterialTecho=objGestionDB.getValorVariableSelecionado(idfamilia,94,this.contexto);
				
				//averiguar la posicion en la lista
				for(int i=0; i<lablesTipoVivienda.size(); i++){
					if(lablesTipoVivienda.get(i).getCodigo().equals(TipoVivienda)){
						TipoVivienda_pos=i;
			        }       
		          }
				for(int i=0; i<lablesTeneciaVivienda.size(); i++){
					if(lablesTeneciaVivienda.get(i).getCodigo().equals(TipoTenenciaVivienda)){
						TipoTenenciaVivienda_pos=i;
			        }       
		          }
				for(int i=0; i<lablesMaterialParedes.size(); i++){
					if(lablesMaterialParedes.get(i).getCodigo().equals(MaterialParedes)){
						MaterialParedes_pos=i;
			        }       
		          }
				for(int i=0; i<lablesMaterialPiso.size(); i++){
					if(lablesMaterialPiso.get(i).getCodigo().equals(MaterialPiso)){
						MaterialPiso_pos=i;
			        }       
		          }
				for(int i=0; i<lablesMaterialTecho.size(); i++){
					if(lablesMaterialTecho.get(i).getCodigo().equals(MaterialTecho)){
						MaterialTecho_pos=i;
			        }       
		          }
				
				//}
			}
	}
    public class GuardarEditarFicha extends AsyncTask<String, Void, String> {  	
    	Context contexto;
		    protected String doInBackground(String... params) {
				    		/*if(action.equals("New")){
				    		//llamo el metodo que guarda
				    			guardarFicha();
				    		}else{
				    		//llamo el metodo que edita	}
				    			actualizarFicha();
				    		}*/
		    	EG();
		    		return null;
		    	}
		    public GuardarEditarFicha(Context contexto){	
					this.contexto = contexto;
				}
	    	@Override
	    	protected void onPostExecute(String result) {
	    			progressDialog.dismiss();
	    		}
	    	@Override
	        protected void onPreExecute() {
	            // TODO Auto-generated method stub
		            super.onPreExecute();
		            if(action.equals("New")){
		            	progressDialog = ProgressDialog.show(contexto,"","Guardando los datos...",true);	
		        		}else{
		        			progressDialog = ProgressDialog.show(contexto,"","Actualizando los datos...",true);
		        		}    
	    		}
		    public void guardarFicha(){
		    	String fechaactual=objGestionDB.fechaActual();
				//objGestionDB.insertFamiliaVariable(idfamilia, 83, fechaactual, idTipoVivienda,correlativo_tablet,id_estasib_user_sp);
				//objGestionDB.insertFamiliaVariable(idfamilia, 91, fechaactual, idTipoTenenciaVivienda,correlativo_tablet,id_estasib_user_sp);
				//objGestionDB.insertFamiliaVariable(idfamilia, 92, fechaactual, idMaterialParedes,correlativo_tablet,id_estasib_user_sp);
				//objGestionDB.insertFamiliaVariable(idfamilia, 93, fechaactual, idMaterialPiso,correlativo_tablet,id_estasib_user_sp);
				//objGestionDB.insertFamiliaVariable(idfamilia, 94, fechaactual, idMaterialTecho,correlativo_tablet,id_estasib_user_sp);	
		    }
		    public void actualizarFicha(){
		    	//objGestionDB.updateFamiliaVariable(idfamilia, 83, idTipoVivienda);
				//objGestionDB.updateFamiliaVariable(idfamilia, 91, idTipoTenenciaVivienda);
				//objGestionDB.updateFamiliaVariable(idfamilia, 92, idMaterialParedes);
				//objGestionDB.updateFamiliaVariable(idfamilia, 93, idMaterialPiso);
				//objGestionDB.updateFamiliaVariable(idfamilia, 94, idMaterialTecho);
		    }
		    public void EG(){
				String fechaactual=objGestionDB.fechaActual();
				if(TipoVivienda.equals("") || TipoVivienda==null){
					//insert
					objGestionDB.insertFamiliaVariable(idfamilia, 83, fechaactual, idTipoVivienda,correlativo_tablet,id_estasib_user_sp,this.contexto);
				}else{
					//update
					objGestionDB.updateFamiliaVariable(idfamilia, 83, idTipoVivienda,this.contexto);
				}
				if(TipoTenenciaVivienda.equals("") || TipoTenenciaVivienda==null){
					//insert
					objGestionDB.insertFamiliaVariable(idfamilia, 92, fechaactual, idMaterialParedes,correlativo_tablet,id_estasib_user_sp,this.contexto);
					//update
					objGestionDB.updateFamiliaVariable(idfamilia, 91, idTipoTenenciaVivienda,this.contexto);
				}
				if(MaterialParedes.equals("") || MaterialParedes==null){
					//insert
					objGestionDB.insertFamiliaVariable(idfamilia, 91, fechaactual, idTipoTenenciaVivienda,correlativo_tablet,id_estasib_user_sp,this.contexto);
				}else{
					//update
					objGestionDB.updateFamiliaVariable(idfamilia, 92, idMaterialParedes,this.contexto);
				}
				if(MaterialPiso.equals("") || MaterialPiso==null){
					//insert
					objGestionDB.insertFamiliaVariable(idfamilia, 93, fechaactual, idMaterialPiso,correlativo_tablet,id_estasib_user_sp,this.contexto);
				}else{
					//update
					objGestionDB.updateFamiliaVariable(idfamilia, 93, idMaterialPiso,this.contexto);
				}
				if(MaterialTecho.equals("") || MaterialTecho==null){
					//insert
					objGestionDB.insertFamiliaVariable(idfamilia, 94, fechaactual, idMaterialTecho,correlativo_tablet,id_estasib_user_sp,this.contexto);	
				}else{
					//update
					objGestionDB.updateFamiliaVariable(idfamilia, 94, idMaterialTecho,this.contexto);
				} 
				
		    }
    }
	
}
