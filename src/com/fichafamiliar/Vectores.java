package com.fichafamiliar;

import java.io.IOException;

import com.fichafamiliar.Amenazas.GuardarEditarAmenazas;
import com.fichafamiliar.Amenazas.RSAsyncAmenazas;

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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Vectores extends Activity {
	//private Ver_detalle_ficha classVerDetalleFicha= new Ver_detalle_ficha();
	
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	String action="";
	int idfamilia;
	int id_sp;					// id usuario tabletcontext
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	String numerosVectores;
	String 		zancudos="",
				moscas="",
				roedores="",
				chincesPicudas="",
				cucarachas="",
				otrosVectores,
				noHayVectores="";

	CheckBox 	CheckBoxzancudos,
				CheckBoxmoscas,
				CheckBoxroedores,
				CheckBoxchincesPicudas,
				CheckBoxcucarachas,
				CheckBoxotrosVectores,
				CheckBoxnoHayVectores;
	
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		setContentView(R.layout.activity_vectores);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New"
		
 
		CheckBoxzancudos		= (CheckBox) findViewById(R.id.CheckZancudos);
		CheckBoxmoscas			= (CheckBox) findViewById(R.id.CheckMoscas);
		CheckBoxroedores		= (CheckBox) findViewById(R.id.CheckRoedores);
		CheckBoxchincesPicudas	= (CheckBox) findViewById(R.id.CheckChinches_picudas);
		CheckBoxcucarachas		= (CheckBox) findViewById(R.id.CheckCucarachas);
		CheckBoxotrosVectores	= (CheckBox) findViewById(R.id.CheckOtrosVectores);
		CheckBoxnoHayVectores		= (CheckBox) findViewById(R.id.Checknohayvectores);
		
		
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
		this.idfamilia= bundle.getInt("idfamilia");
		RSAsyncAmenazas leerAsync = new RSAsyncAmenazas(this);
		leerAsync.execute();
	}
	public int	valida_vectores(){
		if(CheckBoxzancudos.isChecked() || CheckBoxmoscas.isChecked() || CheckBoxroedores.isChecked() || CheckBoxchincesPicudas.isChecked() || CheckBoxcucarachas.isChecked() || CheckBoxotrosVectores.isChecked() || CheckBoxnoHayVectores.isChecked()){
			return 1;	
		}else{
			return 0;
		}
		
	}
	public void click_NoHayVectores(View view) {
		if(CheckBoxnoHayVectores.isChecked()){//desactivar todos
			CheckBoxzancudos.setChecked(false);
			CheckBoxmoscas.setChecked(false);
			CheckBoxroedores.setChecked(false);
			CheckBoxchincesPicudas.setChecked(false);
			CheckBoxcucarachas.setChecked(false);
			CheckBoxotrosVectores.setChecked(false);
		}
	}
	public void click_zancudos(View view) {
		if(CheckBoxzancudos.isChecked()){
			CheckBoxnoHayVectores.setChecked(false);
		}  
	}
	public void click_moscas(View view) {
		if(CheckBoxmoscas.isChecked()){
			CheckBoxnoHayVectores.setChecked(false);
		}  
	}
	public void click_roedores(View view) {
		if(CheckBoxroedores.isChecked()){
			CheckBoxnoHayVectores.setChecked(false);
		}  
	} 
	public void click_chincesPicudas(View view) {
		if(CheckBoxchincesPicudas.isChecked()){
			CheckBoxnoHayVectores.setChecked(false);
		}  
	}
	public void click_cucarachas(View view) {
		if(CheckBoxcucarachas.isChecked()){
			CheckBoxnoHayVectores.setChecked(false);
		}  
	}
	public void click_otrosVectores(View view) {
		if(CheckBoxotrosVectores.isChecked()){
			CheckBoxnoHayVectores.setChecked(false);
		}  
	}
	public void click_vectores(View view) {
		if(valida_vectores()==0){
			Toast T_vectores=Toast.makeText(this, "Marque al menos una opción de presencia de vectores", Toast.LENGTH_LONG);
			T_vectores.setGravity(Gravity.CENTER, 0, 0);
			T_vectores.show();
		}else{
			GuardarEditarVectores GuardarEditar = new GuardarEditarVectores(this);
			GuardarEditar.execute();
			 
			 if(action.equals("New")){
				 ToastExito("Se ha guardado la información");			 
				 Intent i = new Intent(contexto, Ver_detalle_ficha.class);
				 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
				 i.putExtra("busquedaPor",3);
				 // Log.i("idfamilia2", ""+id_familia);
				 //classVerDetalleFicha.ActivityVerDetalleFicha.finish();
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
	 
	public class RSAsyncAmenazas extends AsyncTask<String, Void, String> {
		Context contexto;
		public RSAsyncAmenazas(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			/*if (action.equals("Edit")){
				CargarCheckBox();	
	    	}*/
			CargarCheckBox();
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
		private void CargarCheckBox() {
			cargarCheckVectores();
		}
		private void cargarCheckVectores() {
		    numerosVectores = objGestionDB.getValorVariableSelecionado(idfamilia,107,this.contexto);
		    String[] numerosComoArray = numerosVectores.split(",");
		    for (int i = 0; i < numerosComoArray.length; i++) {
			     if(numerosComoArray[i].equals("1")){CheckBoxzancudos.setChecked(true);}
			     if(numerosComoArray[i].equals("2")){CheckBoxmoscas.setChecked(true);}
			     if(numerosComoArray[i].equals("3")){CheckBoxchincesPicudas.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){CheckBoxcucarachas.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){CheckBoxroedores.setChecked(true);}
			     if(numerosComoArray[i].equals("6")){CheckBoxotrosVectores.setChecked(true);}
			     if(numerosComoArray[i].equals("7")){CheckBoxnoHayVectores.setChecked(true);}
		    	}
			}
	}
	public class GuardarEditarVectores extends AsyncTask<String, Void, String> {  
		Context contexto;
	    protected String doInBackground(String... params) {
			    		/*if(action.equals("New")){
			    			guardarVectores();
			    		}else{
			    			actualizarVectores();
			    		}*/
	    	EG();
	    		return null;
	    	}
	    public GuardarEditarVectores(Context contexto){	
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
    	public void guardarVectores(){
	    	/*String fechaactual=objGestionDB.fechaActual();
	    	getCheckvectores();
	    	String vectores=zancudos+moscas+chincesPicudas+cucarachas+roedores+otrosVectores+noHayVectores;
	    	vectores=vectores.substring(1, vectores.length());

	    	objGestionDB.insertFamiliaVariable(idfamilia, 107, fechaactual, vectores,correlativo_tablet,id_estasib_user_sp);*/
	    }
    	public void actualizarVectores(){
    		/*getCheckvectores();
    		String vectores=zancudos+moscas+chincesPicudas+cucarachas+roedores+otrosVectores+noHayVectores;
	    	vectores=vectores.substring(1, vectores.length());*/
			//objGestionDB.updateFamiliaVariable(idfamilia, 107, vectores);
	    }
    	public void EG(){
			String fechaactual=objGestionDB.fechaActual();
			getCheckvectores();
    		String vectores=zancudos+moscas+chincesPicudas+cucarachas+roedores+otrosVectores+noHayVectores;
	    	vectores=vectores.substring(1, vectores.length());
	    	
			if(numerosVectores.equals("") || numerosVectores==null){
				//insert
				objGestionDB.insertFamiliaVariable(idfamilia, 107, fechaactual, vectores,correlativo_tablet,id_estasib_user_sp,this.contexto);
			}else{
				//update
				objGestionDB.updateFamiliaVariable(idfamilia, 107, vectores,this.contexto);
			}
			
    	}
    	
    	
    	private void getCheckvectores() {
			if(CheckBoxzancudos.isChecked()){zancudos=",1";}else{zancudos="";}
			if(CheckBoxmoscas.isChecked()){moscas=",2";}else{moscas="";}
			if(CheckBoxchincesPicudas.isChecked()){chincesPicudas=",3";}else{chincesPicudas="";}
			if(CheckBoxcucarachas.isChecked()){cucarachas=",4";}else{cucarachas="";}
			if(CheckBoxroedores.isChecked()){roedores=",5";}else{roedores="";}
			if(CheckBoxotrosVectores.isChecked()){otrosVectores=",6";}else{otrosVectores="";}
			if(CheckBoxnoHayVectores.isChecked()){noHayVectores=",7";}else{noHayVectores="";}
		}
    	
	}
}
