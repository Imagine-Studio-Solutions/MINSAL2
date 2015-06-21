package com.fichafamiliar;

import java.io.IOException;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mascotas extends Activity {
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
	public String 	NPerros,NGatos,NOtrasMascotas;
	
	EditText	 NumPerros,NumGatos,NumOtrasMascotas;
	
	public String 	Num_Perros,Num_Gatos,Num_OtrasMascotas;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		setContentView(R.layout.activity_macotas);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New" 
		NumPerros = (EditText) findViewById(R.id.Numperros);
		NumGatos = (EditText) findViewById(R.id.NumGatos);
		NumOtrasMascotas = (EditText) findViewById(R.id.NumOtrasMascotas);
		
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
		RSAsyncMascotas leerAsync = new RSAsyncMascotas(this);
		leerAsync.execute();
	}
	public void click_mascotas(View view) {
		Num_Perros = NumPerros.getText().toString();
		Num_Gatos = NumGatos.getText().toString();
		Num_OtrasMascotas = NumOtrasMascotas.getText().toString();
		if(Num_Perros.equals("")){
			NumPerros.requestFocusFromTouch();
			Toast T_NumPerros=Toast.makeText(this, "Digite el número de perros", Toast.LENGTH_LONG);
			T_NumPerros.setGravity(Gravity.CENTER, 0, 0);
			T_NumPerros.show();
		}else if(Num_Gatos.equals("")){
			NumGatos.requestFocusFromTouch();
			Toast T_NumGatos=Toast.makeText(this, "Digite el número de gatos", Toast.LENGTH_LONG);
			T_NumGatos.setGravity(Gravity.CENTER, 0, 0);
			T_NumGatos.show();
		}else if(Num_OtrasMascotas.equals("")){
			NumOtrasMascotas.requestFocusFromTouch();
			Toast T_NumOtrasMascotas=Toast.makeText(this, "Digite el número de otras mascotas", Toast.LENGTH_LONG);
			T_NumOtrasMascotas.setGravity(Gravity.CENTER, 0, 0);
			T_NumOtrasMascotas.show();
		}else{
			GuardarEditarMascotas GuardarEditar = new GuardarEditarMascotas(this);
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
	 
	public class RSAsyncMascotas extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		
		public RSAsyncMascotas(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			/*if (action.equals("Edit")){
				CargarEditText();	
	    	}*/
			CargarEditText();
			return null;
		}
		@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    		 
    		/*if (action.equals("Edit")){*/
    			NumPerros.setText(NPerros);
    			NumGatos.setText(NGatos);
    			NumOtrasMascotas.setText(NOtrasMascotas);
    		//}
    		progressDialog.dismiss();
		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
    		// NumHabitaciones.setText("dsfasdf");
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }
		private void CargarEditText() {
			NPerros=objGestionDB.getValorVariableSelecionado(idfamilia, 43,this.contexto);
			NGatos=objGestionDB.getValorVariableSelecionado(idfamilia,45,this.contexto);
			NOtrasMascotas=objGestionDB.getValorVariableSelecionado(idfamilia,62,this.contexto);
		}
	}
	public class GuardarEditarMascotas extends AsyncTask<String, Void, String> {  	
		Context contexto;
		    protected String doInBackground(String... params) {
				    		/*if(action.equals("New")){
				    			guardarMascotas();
				    		}else{
				    			actualizarMascotas();
				    		}*/
		    	EG();
		    		return null;
		    	}
		    public GuardarEditarMascotas(Context contexto){	
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
	}
	public void guardarMascotas(){
    	//String fechaactual=objGestionDB.fechaActual();
    	//objGestionDB.insertFamiliaVariable(idfamilia, 43, fechaactual, Num_Perros,correlativo_tablet,id_estasib_user_sp);
    	//objGestionDB.insertFamiliaVariable(idfamilia, 45, fechaactual, Num_Gatos,correlativo_tablet,id_estasib_user_sp);
    	//objGestionDB.insertFamiliaVariable(idfamilia, 62, fechaactual, Num_OtrasMascotas,correlativo_tablet,id_estasib_user_sp);		
    }
	public void actualizarMascotas(){
    	//objGestionDB.updateFamiliaVariable(idfamilia, 43, Num_Perros);
		//objGestionDB.updateFamiliaVariable(idfamilia, 45, Num_Gatos);
		//objGestionDB.updateFamiliaVariable(idfamilia, 62, Num_OtrasMascotas);
    }
	public void EG(){
		String fechaactual=objGestionDB.fechaActual();
		/*
		 	public String 	,,;
		 * */
		if(NPerros.equals("") || NPerros==null){
			//insert
			objGestionDB.insertFamiliaVariable(idfamilia, 43, fechaactual, Num_Perros,correlativo_tablet,id_estasib_user_sp,this.contexto);
		}else{
			//update
			objGestionDB.updateFamiliaVariable(idfamilia, 43, Num_Perros,this.contexto);
		}
		if(NGatos.equals("") || NGatos==null){
			//insert
			objGestionDB.insertFamiliaVariable(idfamilia, 45, fechaactual, Num_Gatos,correlativo_tablet,id_estasib_user_sp,this.contexto);
		}else{
			//update
			objGestionDB.updateFamiliaVariable(idfamilia, 45, Num_Gatos,this.contexto);
		}
		if(NOtrasMascotas.equals("") || NOtrasMascotas==null){
			//insert
			objGestionDB.insertFamiliaVariable(idfamilia, 62, fechaactual, Num_OtrasMascotas,correlativo_tablet,id_estasib_user_sp,this.contexto);	
		}else{
			//update
			objGestionDB.updateFamiliaVariable(idfamilia, 62, Num_OtrasMascotas,this.contexto);
		}
	}
}
