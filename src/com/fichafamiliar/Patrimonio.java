package com.fichafamiliar;


import java.io.IOException;
import java.util.List;

import com.fichafamiliar.Caracteristicas_vivienda.RSAsyncCaracteristicasVivienda;
import com.fichafamiliar.Ficha_familia_ativity_01.GuardarEditarFicha;

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
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Patrimonio extends Activity {
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
	VehiculoHogar,NumHabita,NumNoDormitorios;
	String 		id_VehiculoHogar;
	String numerosBienesFamilia;
	String numerosBienesHogar;
	
	String 		CultivoAgriPropio="",
				AvesCorral,
				GanadoVacuno="",
				GanadoPorcino="",
				NegocioPropio="",
				Ninguno1="",
	
				Radio="",
				EquipoSonido="",
				Televisor="",
				Refrigeradora="",
				Lavadora="",
				Computadora="",
				Ninguno2="";
	
	EditText	EditTextNumHabitaciones,
				EditTextNoHabitacionesDormitorios;
	
	CheckBox 	CheckBoxCultivoAgriPropio,
				CheckBoxAvesCorral,
				CheckBoxGanadoVacuno,
				CheckBoxGanadoPorcino,
				CheckBoxNegocioPropio,
				CheckBoxNinguno1,
				
				CheckBoxRadio,
				CheckBoxEquipoSonido,
				CheckBoxTelevisor,
				CheckBoxRefrigeradora,
				CheckBoxLavadora,
				CheckBoxComputadora,
				CheckBoxNinguno2;
	
	Spinner 	SpnVehiculoHogar;
	
	public String 	Num_Habitaciones,Num_NoDormitorios;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patrimonio);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New" 
		 
		
		
		EditTextNumHabitaciones = (EditText) findViewById(R.id.NumHabitaciones);
		EditTextNoHabitacionesDormitorios = (EditText) findViewById(R.id.NoHabitacionesDormitorios);
		
		
		CheckBoxCultivoAgriPropio = (CheckBox) findViewById(R.id.CheckcultivoAgricolaPropio);
		CheckBoxAvesCorral	=	(CheckBox) findViewById(R.id.Checkavescorral);
		CheckBoxGanadoVacuno = (CheckBox) findViewById(R.id.Checkganado_vacuno);
		CheckBoxGanadoPorcino = (CheckBox) findViewById(R.id.Checkganado_porcino);
		CheckBoxNegocioPropio = (CheckBox) findViewById(R.id.Checknegocio_propio);
		CheckBoxNinguno1 = (CheckBox) findViewById(R.id.Checkninguno1);
		
		CheckBoxRadio = (CheckBox) findViewById(R.id.Checkradio);
		CheckBoxEquipoSonido = (CheckBox) findViewById(R.id.Checkequipo_sonido);
		CheckBoxTelevisor = (CheckBox) findViewById(R.id.Checktelevisor);
		CheckBoxRefrigeradora = (CheckBox) findViewById(R.id.Checkrefrigeradora);
		CheckBoxLavadora = (CheckBox) findViewById(R.id.Checklavadora);
		CheckBoxComputadora = (CheckBox) findViewById(R.id.Checkcomputadora);
		CheckBoxNinguno2 = (CheckBox) findViewById(R.id.Checkninguno2);
		
		SpnVehiculoHogar = (Spinner) findViewById(R.id.SpnVehiculoHogar);
		
		this.objGestionDB = new GestionDB();// creo el objeto de la DB
		/*
		BaseDeDatos db2 = new BaseDeDatos(this);
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
		RSAsyncPatrimonio leerAsync = new RSAsyncPatrimonio(this);
		leerAsync.execute();
		
		SpnVehiculoHogar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_VehiculoHogar = (((SpinnerObjectString)SpnVehiculoHogar.getSelectedItem()).getCodigo());
				//NumHabitaciones.setText(""+id_TenenciaVivienda);
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		
	}
	
public int	valida_bienes_familia(){
		if(CheckBoxCultivoAgriPropio.isChecked() || CheckBoxAvesCorral.isChecked() || CheckBoxGanadoVacuno.isChecked() || CheckBoxGanadoPorcino.isChecked() || CheckBoxNegocioPropio.isChecked() || CheckBoxNinguno1.isChecked()){
			return 1;	
		}else{
			return 0;
		}
		
	}
public int	valida_bienes_hogar(){
		if(CheckBoxRadio.isChecked() || CheckBoxEquipoSonido.isChecked() || CheckBoxTelevisor.isChecked() || CheckBoxRefrigeradora.isChecked() || CheckBoxLavadora.isChecked() || CheckBoxComputadora.isChecked() || CheckBoxNinguno2.isChecked()){
			return 1;	
		}else{
			return 0;
		}
}
public void click_CultivoAgricolaPropio(View view) {
	if(CheckBoxCultivoAgriPropio.isChecked()){
		CheckBoxNinguno1.setChecked(false);
	}
}
public void click_AvesCorral(View view) {
	if(CheckBoxAvesCorral.isChecked()){
		CheckBoxNinguno1.setChecked(false);
	}  
}
public void clic_GanadoVacuno(View view) {
	if(CheckBoxGanadoVacuno.isChecked()){
		CheckBoxNinguno1.setChecked(false);
	} 
}
public void click_GanadoPorcino(View view) {
	if(CheckBoxGanadoPorcino.isChecked()){
		CheckBoxNinguno1.setChecked(false);
	}
}
public void click_NegocioPropio(View view) {
	if(CheckBoxNegocioPropio.isChecked()){
		CheckBoxNinguno1.setChecked(false);
	}
}
public void click_ninguno1(View view) {
	if(CheckBoxNinguno1.isChecked()){//desactivar todos
		CheckBoxCultivoAgriPropio.setChecked(false);
		CheckBoxAvesCorral.setChecked(false);
		CheckBoxGanadoVacuno.setChecked(false);
		CheckBoxGanadoPorcino.setChecked(false);
		CheckBoxNegocioPropio.setChecked(false);
	}
}
public void click_Radio(View view) {
	if(CheckBoxRadio.isChecked()){ 
		CheckBoxNinguno2.setChecked(false);
	}
}
public void click_EquipoSonido(View view) {
	if(CheckBoxEquipoSonido.isChecked()){ 
		CheckBoxNinguno2.setChecked(false);
	}
}
public void click_Televisor(View view) {
	if(CheckBoxTelevisor.isChecked()){ 
		CheckBoxNinguno2.setChecked(false);
	}
}
public void click_Refrigeradora(View view) {
	if(CheckBoxRefrigeradora.isChecked()){ 
		CheckBoxNinguno2.setChecked(false);
	}
}
public void click_Computadora(View view) {
	if(CheckBoxComputadora.isChecked()){ 
		CheckBoxNinguno2.setChecked(false);
	}
}
public void click_lavadora(View view) {
	if(CheckBoxLavadora.isChecked()){ 
		CheckBoxNinguno2.setChecked(false);
	}
}
public void click_ninguno2(View view) {
	if(CheckBoxNinguno2.isChecked()){//desactivar todos
		CheckBoxRadio.setChecked(false);
		CheckBoxEquipoSonido.setChecked(false);
		CheckBoxTelevisor.setChecked(false);
		CheckBoxRefrigeradora.setChecked(false);
		CheckBoxLavadora.setChecked(false);
		CheckBoxComputadora.setChecked(false);
	}
}


	public void click_patrimonio(View view) {
		Num_Habitaciones = EditTextNumHabitaciones.getText().toString();
		Num_NoDormitorios = EditTextNoHabitacionesDormitorios.getText().toString();
		
		if(Num_Habitaciones.equals("")){
			EditTextNumHabitaciones.requestFocusFromTouch();
			Toast N_Habitaciones=Toast.makeText(this, "Digite el número de habitaciones", Toast.LENGTH_LONG);
			N_Habitaciones.setGravity(Gravity.CENTER, 0, 0);
			N_Habitaciones.show();
		}else if(Num_NoDormitorios.equals("")){
			EditTextNoHabitacionesDormitorios.requestFocusFromTouch();
			Toast N_NoHabitacionesDormitorios=Toast.makeText(this, "Digite el número de espacios que no son dormitorios, utilizados para dormir", Toast.LENGTH_LONG);
			N_NoHabitacionesDormitorios.setGravity(Gravity.CENTER, 0, 0);
			N_NoHabitacionesDormitorios.show();
		}else if(valida_bienes_familia()==0){
			Toast vehiculo_hogar=Toast.makeText(this, "Marque al menos una opción de los bienes de la familia", Toast.LENGTH_LONG);
			vehiculo_hogar.setGravity(Gravity.CENTER, 0, 0);
			vehiculo_hogar.show();
		}else if(valida_bienes_hogar()==0){
			Toast bienes_hogar=Toast.makeText(this, "Marque al menos una opción de los bienes del hogar", Toast.LENGTH_LONG);
			bienes_hogar.setGravity(Gravity.CENTER, 0, 0);
			bienes_hogar.show();
		}else if(id_VehiculoHogar.equals("99")){
			SpnVehiculoHogar.requestFocusFromTouch();
			Toast vehiculo_hogar=Toast.makeText(this, "Seleccione si tiene vehiculo para uso del hogar", Toast.LENGTH_LONG);
			vehiculo_hogar.setGravity(Gravity.CENTER, 0, 0);
			vehiculo_hogar.show();
		}else{
			//instaciar la clase asincrona que guarda y edita
			//correr el doInbackground de la clase asincrona
			GuardarEditarPatrimonio GuardarEditar = new GuardarEditarPatrimonio(this);
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


public class RSAsyncPatrimonio extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		
		//posiciones en las listas
		public int 	
				VehiculoHogar_pos;
		ArrayAdapter<SpinnerObjectString> dataAdapterVehiculoHogar;
		
		public RSAsyncPatrimonio(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			/*if (action.equals("Edit")){
				CargarEditText();
				CargarCheckBox();	
	    	}*/
			CargarEditText();
			CargarCheckBox();
	    	CargarSpinner();
	    return null;
	    }
		@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    		dataAdapterVehiculoHogar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnVehiculoHogar.setAdapter(dataAdapterVehiculoHogar);
    		
    		SpnVehiculoHogar.setSelection(this.VehiculoHogar_pos);
    		/*if (action.equals("Edit")){*/
    			EditTextNumHabitaciones.setText(NumHabita);
				EditTextNoHabitacionesDormitorios.setText(NumNoDormitorios);
    		/*}*/
    		progressDialog.dismiss();
		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
    		// NumHabitaciones.setText("dsfasdf");
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }
		public void CargarSpinner() {	//descriptor
			
			
				List<SpinnerObjectString> lablesVehiculoHogar = objGestionDB.getCatalogoDescriptor(30,this.contexto);
				dataAdapterVehiculoHogar = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesVehiculoHogar);
				//averiguar la posicion en la lista
			//	if(action.equals("Edit")){
					//recupero el valor que tienen las variable cuando se creo la ficha
					VehiculoHogar=objGestionDB.getValorVariableSelecionado(idfamilia,67,this.contexto);
				
				
				for(int i=0; i<lablesVehiculoHogar.size(); i++){
					if(lablesVehiculoHogar.get(i).getCodigo().equals(VehiculoHogar)){
						VehiculoHogar_pos=i;
			        }       
		          }
				//}
				}
		private void CargarCheckBox() {
			cargarCheckBienesFamilia();
			cargarCheckBienesHogar();
		}
		private void CargarEditText() {
			NumHabita=objGestionDB.getValorVariableSelecionado(idfamilia,57,this.contexto);
			NumNoDormitorios=objGestionDB.getValorVariableSelecionado(idfamilia,95,this.contexto);
		}
		public void cargarCheckBienesFamilia() {
		    numerosBienesFamilia = objGestionDB.getValorVariableSelecionado(idfamilia,96,this.contexto);
		    String[] numerosComoArray = numerosBienesFamilia.split(",");
		    for (int i = 0; i < numerosComoArray.length; i++) {
			     if(numerosComoArray[i].equals("1")){CheckBoxCultivoAgriPropio.setChecked(true);}
			     if(numerosComoArray[i].equals("2")){ CheckBoxAvesCorral.setChecked(true);}
			     if(numerosComoArray[i].equals("3")){CheckBoxGanadoVacuno.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){CheckBoxGanadoPorcino.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){CheckBoxNegocioPropio.setChecked(true);}
			     if(numerosComoArray[i].equals("6")){CheckBoxNinguno1.setChecked(true);}
		    	}
		    }
		public void cargarCheckBienesHogar() {
		    numerosBienesHogar = objGestionDB.getValorVariableSelecionado(idfamilia,97,this.contexto);
		    String[] numerosComoArray = numerosBienesHogar.split(",");
		    for (int i = 0; i < numerosComoArray.length; i++) {
			     if(numerosComoArray[i].equals("1")){CheckBoxRadio.setChecked(true);}
			     if(numerosComoArray[i].equals("2")){CheckBoxEquipoSonido.setChecked(true);}
			     if(numerosComoArray[i].equals("3")){ CheckBoxTelevisor.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){CheckBoxRefrigeradora.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){CheckBoxLavadora.setChecked(true);}
			     if(numerosComoArray[i].equals("6")){CheckBoxComputadora.setChecked(true);}
			     if(numerosComoArray[i].equals("7")){CheckBoxNinguno2.setChecked(true);}
		    	}
		    }
	}
public class GuardarEditarPatrimonio extends AsyncTask<String, Void, String> {  	
	Context contexto;
	    protected String doInBackground(String... params) {
			    		/*if(action.equals("New")){
			    			guardarPatrimonio();
			    		}else{
			    			actualizarPatrimonio();
			    		}*/
	    	 EG();
	    		return null;
	    	}
	    public GuardarEditarPatrimonio(Context contexto){	
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
	    public void guardarPatrimonio(){
	    	/*String fechaactual=objGestionDB.fechaActual();
	    	getCheckbienesFamilia();
	    	getCheckbienesHogar();
	    	
	    	String bienesFamilia=CultivoAgriPropio+AvesCorral+GanadoVacuno+GanadoPorcino+NegocioPropio+Ninguno1;
	    	String bienesHogar=Radio+EquipoSonido+Televisor+Refrigeradora+Lavadora+Computadora+Ninguno2;
	    	bienesFamilia=bienesFamilia.substring(1, bienesFamilia.length());
	    	bienesHogar=bienesHogar.substring(1, bienesHogar.length());
	    	*/
	    	//objGestionDB.insertFamiliaVariable(idfamilia, 57, fechaactual, Num_Habitaciones,correlativo_tablet,id_estasib_user_sp);
	    	//objGestionDB.insertFamiliaVariable(idfamilia, 95, fechaactual, Num_NoDormitorios,correlativo_tablet,id_estasib_user_sp);
	    	//objGestionDB.insertFamiliaVariable(idfamilia, 96, fechaactual, bienesFamilia,correlativo_tablet,id_estasib_user_sp);
	    	//objGestionDB.insertFamiliaVariable(idfamilia, 97, fechaactual, bienesHogar,correlativo_tablet,id_estasib_user_sp);
	    	//objGestionDB.insertFamiliaVariable(idfamilia, 67, fechaactual, id_VehiculoHogar,correlativo_tablet,id_estasib_user_sp);
			
	    }
	    public void actualizarPatrimonio(){
	    	/*getCheckbienesFamilia();
	    	getCheckbienesHogar();
	    	
	    	String bienesFamilia=CultivoAgriPropio+AvesCorral+GanadoVacuno+GanadoPorcino+NegocioPropio+Ninguno1;
	    	String bienesHogar=Radio+EquipoSonido+Televisor+Refrigeradora+Lavadora+Computadora+Ninguno2;
	    	bienesFamilia=bienesFamilia.substring(1, bienesFamilia.length());
	    	bienesHogar=bienesHogar.substring(1, bienesHogar.length());
	    	*/
	    	//objGestionDB.updateFamiliaVariable(idfamilia, 57, Num_Habitaciones);
			//objGestionDB.updateFamiliaVariable(idfamilia, 95, Num_NoDormitorios);
			//objGestionDB.updateFamiliaVariable(idfamilia, 96, bienesFamilia);
			//objGestionDB.updateFamiliaVariable(idfamilia, 97, bienesHogar);
			//objGestionDB.updateFamiliaVariable(idfamilia, 67, id_VehiculoHogar);
	    }
	    public void EG(){
	    	String fechaactual=objGestionDB.fechaActual();
	    	getCheckbienesFamilia();
	    	getCheckbienesHogar();
	    	
	    	String bienesFamilia=CultivoAgriPropio+AvesCorral+GanadoVacuno+GanadoPorcino+NegocioPropio+Ninguno1;
	    	String bienesHogar=Radio+EquipoSonido+Televisor+Refrigeradora+Lavadora+Computadora+Ninguno2;
	    	bienesFamilia=bienesFamilia.substring(1, bienesFamilia.length());
	    	bienesHogar=bienesHogar.substring(1, bienesHogar.length());
	    	 
	    	if(VehiculoHogar.equals("") || VehiculoHogar==null){
	    		//insert
	    		objGestionDB.insertFamiliaVariable(idfamilia, 67, fechaactual, id_VehiculoHogar,correlativo_tablet,id_estasib_user_sp,this.contexto);
	    	}else{
	    		//update
	    		objGestionDB.updateFamiliaVariable(idfamilia, 67, id_VehiculoHogar,this.contexto);
	    	}
	    	if(NumHabita.equals("") || NumHabita==null){
	    		//insert
	    		objGestionDB.insertFamiliaVariable(idfamilia, 57, fechaactual, Num_Habitaciones,correlativo_tablet,id_estasib_user_sp,this.contexto);
	    	}else{
	    		//update
	    		objGestionDB.updateFamiliaVariable(idfamilia, 57, Num_Habitaciones,this.contexto);
	    	}
	    	if(NumNoDormitorios.equals("") || NumNoDormitorios==null){
	    		//inser
	    		objGestionDB.insertFamiliaVariable(idfamilia, 95, fechaactual, Num_NoDormitorios,correlativo_tablet,id_estasib_user_sp,this.contexto);
	    	}else{
	    		//update
	    		objGestionDB.updateFamiliaVariable(idfamilia, 95, Num_NoDormitorios,this.contexto);
	    	}if(numerosBienesFamilia.equals("") || numerosBienesFamilia==null){
	    		//insert
	    		objGestionDB.insertFamiliaVariable(idfamilia, 96, fechaactual, bienesFamilia,correlativo_tablet,id_estasib_user_sp,this.contexto);
	    	}else{
	    		//update
	    		objGestionDB.updateFamiliaVariable(idfamilia, 96, bienesFamilia,this.contexto);
	    	}
	    	if(numerosBienesHogar.equals("") || numerosBienesHogar==null){
	    		//insert
	    		objGestionDB.insertFamiliaVariable(idfamilia, 97, fechaactual, bienesHogar,correlativo_tablet,id_estasib_user_sp,this.contexto);
	    	}else{
	    		//update
	    		objGestionDB.updateFamiliaVariable(idfamilia, 97, bienesHogar,this.contexto);
	    	}
	    }
		 private void getCheckbienesHogar() { 
		    	if(CheckBoxRadio.isChecked()){Radio=",1";}else{Radio="";}
		    	if(CheckBoxEquipoSonido.isChecked()){EquipoSonido=",2";}else{EquipoSonido="";}
		    	if(CheckBoxTelevisor.isChecked()){Televisor=",3";}else{Televisor="";}
		    	if(CheckBoxRefrigeradora.isChecked()){Refrigeradora=",4";}else{Refrigeradora="";}
		    	if(CheckBoxLavadora.isChecked()){Lavadora=",5";}else{Lavadora="";}
		    	if(CheckBoxComputadora.isChecked()){Computadora=",6";}else{Computadora=""; }
		    	if(CheckBoxNinguno2.isChecked()){Ninguno2=",7";}else{Ninguno2="";}
			}
			private void getCheckbienesFamilia() {
				if(CheckBoxCultivoAgriPropio.isChecked()){CultivoAgriPropio=",1";}else{CultivoAgriPropio="";}
				if(CheckBoxAvesCorral.isChecked()){AvesCorral=",2";}else{AvesCorral="";}
				if(CheckBoxGanadoVacuno.isChecked()){GanadoVacuno=",3";}else{GanadoVacuno="";}
				if(CheckBoxGanadoPorcino.isChecked()){GanadoPorcino=",4";}else{GanadoPorcino="";}
				if(CheckBoxNegocioPropio.isChecked()){NegocioPropio=",5";}else{NegocioPropio="";}
				if(CheckBoxNinguno1.isChecked()){Ninguno1=",6";}else{Ninguno1="";}
			}
	}
}

