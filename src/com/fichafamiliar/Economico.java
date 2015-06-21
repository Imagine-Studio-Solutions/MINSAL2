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
import android.util.Log;
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

public class Economico extends Activity {
	//private Integrantes classIntegrante= new Integrantes();
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	public Common_function objFunctionGeneral;
	
	String action="";
	int idfamilia=0;
	int idintegrante=0;
	int edad=0;
	
	public String 
	TrabajoRemunerado,
	Ocupacion,
	AyudaEconomicaFam, 
	ProMemoriaHistorica;
	String numerosIntegranteExtranjero;
	String numerosRecibeApoyoGobierno;
	
	String numerocorrelativo="";
	
	int id_sp;					// id usuario tablet
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	
	String 		id_TrabajoRemunerado,
				id_Ocupacion,
				id_AyudaEconomicaFam, 
				id_ProMemoriaHistorica;
	
	Spinner 	SpnTrabajoRemunerado,
				SpnOcupacion,
				SpnAyudaEconomicaFam,
				SpnProMemoriaHistorica;
	
	//Integrante en el extranjero
	CheckBox 	CheckBoxPadre,
				CheckBoxMadre,
				CheckBoxHijo_a,
				CheckBoxEsposo_a,
				CheckBoxOtrosIE,
				CheckBoxNingunIE;
	
	String  	Padre,
				Madre,
				Hijo_a,
				Esposo_a,
				OtrosIE,
				NingunIE;
	
	//Recibe apoyo del gobierno
	CheckBox 	CheckBoxsubsidio_gas,
				CheckBoxsubsidio_energia_elec,
				CheckBoxbonos_comunidades_rurales,
				CheckBoxbonos_comunidades_urbanas,
				CheckBoxpension_basica_universal,
				CheckBoxotro_tipo_apoyo,
				CheckBoxningun_tipo_apoyo;
	
	String  	subsidio_gas,
	 			subsidio_energia_elec,
	 			bonos_comunidades_rurales,
	 			bonos_comunidades_urbanas,
	 			pension_basica_universal,
	 			otro_tipo_apoyo,
	 			ningun_tipo_apoyo;
	TextView edadTextView;
	
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_economico);
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
		
		SpnTrabajoRemunerado= (Spinner) findViewById(R.id.SpnTieneTrabajoRemunerado);
		SpnOcupacion= (Spinner) findViewById(R.id.SpnOcupacion);
		SpnAyudaEconomicaFam= (Spinner) findViewById(R.id.SpnAyudaEconomicaFamiliar);
		SpnProMemoriaHistorica = (Spinner) findViewById(R.id.SpnProMemoriaHistorica);
		edadTextView=(TextView) findViewById(R.id.econo);
		
		//Intengrante en el extranjero
		CheckBoxPadre=(CheckBox) findViewById(R.id.Checkpadre);
		CheckBoxMadre=(CheckBox) findViewById(R.id.Checkmadre);
		CheckBoxHijo_a=(CheckBox) findViewById(R.id.Checkhijo_a);
		CheckBoxEsposo_a=(CheckBox) findViewById(R.id.Checkesposo_a);
		CheckBoxOtrosIE=(CheckBox) findViewById(R.id.Checkotros_extranjero);
		CheckBoxNingunIE=(CheckBox) findViewById(R.id.Checkninguno_extranjero);
		
		//Recibe apoyo del gobierno
		CheckBoxsubsidio_gas=(CheckBox) findViewById(R.id.Checksubsidiogas);
		CheckBoxsubsidio_energia_elec=(CheckBox) findViewById(R.id.Checksubsidio_ener_elec);
		CheckBoxbonos_comunidades_rurales=(CheckBox) findViewById(R.id.Checkbonos_comunidades_rurales);
		CheckBoxbonos_comunidades_urbanas=(CheckBox) findViewById(R.id.Checkbonos_comunidades_urbanas);
		CheckBoxpension_basica_universal=(CheckBox) findViewById(R.id.Checkpension_basica_universal);
		CheckBoxotro_tipo_apoyo=(CheckBox) findViewById(R.id.Checkotro_tipo_apoyo);
		CheckBoxningun_tipo_apoyo=(CheckBox) findViewById(R.id.Checkningunapoyo);
		 
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
		this.objFunctionGeneral = new Common_function();
		String fechaNac=objGestionDB.getFechaNacIntegrnate(idintegrante,this.contexto);
		edad=objFunctionGeneral.getEdad(fechaNac);
		//edadTextView.setText(" "+fechaNac+"   la edad es: "+edad);
		
		numerocorrelativo=objGestionDB.getNumeroCorrelativoIntegrante(idintegrante,this.contexto);
		Ocupacion=objGestionDB.getValorIntegranteSelecionado(idintegrante,69,this.contexto);
		
		//RSAsyncEconomico leerAsync = new RSAsyncEconomico(this);
		//leerAsync.execute();
		//leerAsync.Toastsd("algo");
		CargarCheckBox();	
		CargarSpinner();
	
		SpnTrabajoRemunerado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_TrabajoRemunerado = (((SpinnerObjectString)SpnTrabajoRemunerado.getSelectedItem()).getCodigo());
				cargarOcupacion(id_TrabajoRemunerado);
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnOcupacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Ocupacion = (((SpinnerObjectString)SpnOcupacion.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnAyudaEconomicaFam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_AyudaEconomicaFam = (((SpinnerObjectString)SpnAyudaEconomicaFam.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnProMemoriaHistorica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ProMemoriaHistorica = (((SpinnerObjectString)SpnProMemoriaHistorica.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
	}
	//---------------------------------------------------
	
	private void CargarCheckBox() {
		cargarCheckIntegranteExtranjero();
		cargarCheckRecibeApoyoGobierno(); 
	}
	private void cargarCheckIntegranteExtranjero() {
	    numerosIntegranteExtranjero = objGestionDB.getValorIntegranteSelecionado(idintegrante,115,this.contexto);
	    String[] numerosComoArray = numerosIntegranteExtranjero.split(",");
	    
	    for (int i = 0; i < numerosComoArray.length; i++) {
	    	 if(numerosComoArray[i].equals("1")){CheckBoxPadre.setChecked(true);}
		     if(numerosComoArray[i].equals("2")){CheckBoxMadre.setChecked(true);}
		     if(numerosComoArray[i].equals("3")){CheckBoxHijo_a.setChecked(true);}
		     if(numerosComoArray[i].equals("4")){CheckBoxEsposo_a.setChecked(true);}
		     if(numerosComoArray[i].equals("5")){CheckBoxOtrosIE.setChecked(true);}
		     if(numerosComoArray[i].equals("6")){CheckBoxNingunIE.setChecked(true);}
	    	}
}
private void cargarCheckRecibeApoyoGobierno() {
	    numerosRecibeApoyoGobierno = objGestionDB.getValorIntegranteSelecionado(idintegrante,84,this.contexto);
	    String[] numerosComoArray = numerosRecibeApoyoGobierno.split(",");
	    for (int i = 0; i < numerosComoArray.length; i++) {
	    	 
	    	 if(numerosComoArray[i].equals("1")){CheckBoxsubsidio_gas.setChecked(true);}
		     if(numerosComoArray[i].equals("2")){CheckBoxsubsidio_energia_elec.setChecked(true);}
		     if(numerosComoArray[i].equals("3")){CheckBoxbonos_comunidades_rurales.setChecked(true);}
		     if(numerosComoArray[i].equals("4")){CheckBoxbonos_comunidades_urbanas.setChecked(true);}
		     if(numerosComoArray[i].equals("5")){CheckBoxpension_basica_universal.setChecked(true);}
		     if(numerosComoArray[i].equals("6")){CheckBoxotro_tipo_apoyo.setChecked(true);}
		     if(numerosComoArray[i].equals("7")){CheckBoxningun_tipo_apoyo.setChecked(true);}
	    	}

}
	
	public void CargarSpinner() {	//descriptor
		//recuperar edad
		
		int
		TrabajoRemunerado_pos = 0,
		Ocupacion_pos=0,
		AyudaEconomicaFam_pos = 0, 
		ProMemoriaHistorica_pos = 0;
		
		ArrayAdapter<SpinnerObjectString> dataAdapterTrabajoRemunerado;
		//ArrayAdapter<SpinnerObjectString> dataAdapterOcupacion;
		ArrayAdapter<SpinnerObjectString> dataAdapterAyudaEconomicaFam;
		ArrayAdapter<SpinnerObjectString> dataAdapterProMemoriaHistorica;
		
		List<SpinnerObjectString> lablesTrabajoRemunerado = objGestionDB.getTrabajoRemunerado(76,edad,this.contexto);
		
		List<SpinnerObjectString> lablesAyudaEconomicaFam = objGestionDB.getCatalogoDescriptor(78,this.contexto);
		List<SpinnerObjectString> lablesProMemoriaHistorica = objGestionDB.getCatalogoDescriptor(48,this.contexto);
		
		dataAdapterTrabajoRemunerado = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTrabajoRemunerado);
	
		dataAdapterAyudaEconomicaFam = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesAyudaEconomicaFam);
		dataAdapterProMemoriaHistorica = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesProMemoriaHistorica);
	 
		//averiguar la posicion en la lista

		//if(action.equals("Edit")){
		//recupero el valor que tienen las variable cuando se creo la ficha
		TrabajoRemunerado=objGestionDB.getValorIntegranteSelecionado(idintegrante,112,this.contexto);
		
		for(int i=0; i<lablesTrabajoRemunerado.size(); i++){
			if(edad<14){
				TrabajoRemunerado_pos=5;
				SpnTrabajoRemunerado.setClickable(false);
			}else
			 {
				if(lablesTrabajoRemunerado.get(i).getCodigo().equals(TrabajoRemunerado)){
					TrabajoRemunerado_pos=i;
		       }
			}      
          }
		//}
		//if(action.equals("Edit")){
			//recupero el valor que tienen las variable cuando se creo la ficha
		
		
		//}
		//if(action.equals("Edit")){
			//recupero el valor que tienen las variable cuando se creo la ficha
		AyudaEconomicaFam=objGestionDB.getValorIntegranteSelecionado(idintegrante,116,this.contexto);
		for(int i=0; i<lablesAyudaEconomicaFam.size(); i++){
			if(lablesAyudaEconomicaFam.get(i).getCodigo().equals(AyudaEconomicaFam)){
				AyudaEconomicaFam_pos=i;
	        }       
          }
		//}
		//if(action.equals("Edit")){
			//recupero el valor que tienen las variable cuando se creo la ficha
			ProMemoriaHistorica=objGestionDB.getValorIntegranteSelecionado(idintegrante,90,this.contexto);
		
		for(int i=0; i<lablesProMemoriaHistorica.size(); i++){
			if(lablesProMemoriaHistorica.get(i).getCodigo().equals(ProMemoriaHistorica)){
				ProMemoriaHistorica_pos=i;
	        }       
          }
		//}
		dataAdapterTrabajoRemunerado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpnTrabajoRemunerado.setAdapter(dataAdapterTrabajoRemunerado);
		
		
		
		dataAdapterAyudaEconomicaFam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpnAyudaEconomicaFam.setAdapter(dataAdapterAyudaEconomicaFam);
		
		dataAdapterProMemoriaHistorica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpnProMemoriaHistorica.setAdapter(dataAdapterProMemoriaHistorica);
		 
		SpnTrabajoRemunerado.setSelection(TrabajoRemunerado_pos);
		
		SpnAyudaEconomicaFam.setSelection(AyudaEconomicaFam_pos);
		SpnProMemoriaHistorica.setSelection(ProMemoriaHistorica_pos);
	}
	
	
	public void EG(){
		String fechaactual=objGestionDB.fechaActual();
		//actualizo integrantes en el extranjero
    	getCheckIntegranteEnExtranjero();
    	String IntegranteEnExtranjero=Padre+Madre+Hijo_a+Esposo_a+OtrosIE+NingunIE;
    	IntegranteEnExtranjero=IntegranteEnExtranjero.substring(1, IntegranteEnExtranjero.length());
    	
    	
    	getCheckAyudaGobierno();
    	String AyudaGobierno=subsidio_gas+subsidio_energia_elec+bonos_comunidades_rurales+bonos_comunidades_urbanas+pension_basica_universal+otro_tipo_apoyo+ningun_tipo_apoyo;
    	AyudaGobierno=AyudaGobierno.substring(1, AyudaGobierno.length());
    	
		 
		if(TrabajoRemunerado.equals("") || TrabajoRemunerado==null){
			//insert
			objGestionDB.insertIntegranteVariable(112, fechaactual, id_TrabajoRemunerado, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto); 
	    	
		}else{
			//updatepension_basica_universal
			objGestionDB.updateIntegranteVariable(idintegrante, 112, id_TrabajoRemunerado, this.contexto);
		}if(Ocupacion.equals("") || Ocupacion==null){
			//insert
			objGestionDB.insertIntegranteVariable(69, fechaactual, id_Ocupacion, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto); 
	    	
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 69, id_Ocupacion, this.contexto);
		}
		if(AyudaEconomicaFam.equals("") || AyudaEconomicaFam==null){
			//insert
			objGestionDB.insertIntegranteVariable(116, fechaactual, id_AyudaEconomicaFam, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto); 
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 116, id_AyudaEconomicaFam, this.contexto);
		}if(ProMemoriaHistorica.equals("") || ProMemoriaHistorica==null){
			//insert
			objGestionDB.insertIntegranteVariable(90, fechaactual, id_ProMemoriaHistorica, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto); 
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 90, id_ProMemoriaHistorica, this.contexto);
		}
		if(numerosIntegranteExtranjero.equals("") || numerosIntegranteExtranjero==null){
			//insert
			objGestionDB.insertIntegranteVariable(115, fechaactual, IntegranteEnExtranjero, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto); 
	    	
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 115, IntegranteEnExtranjero, this.contexto);
		}
		if(numerosRecibeApoyoGobierno.equals("") || numerosRecibeApoyoGobierno==null){
			//insert
			objGestionDB.insertIntegranteVariable(84, fechaactual, AyudaGobierno, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto); 
		}else{
			//update
			objGestionDB.updateIntegranteVariable(idintegrante, 84, AyudaGobierno, this.contexto);
		}
		
	}
	private void getCheckIntegranteEnExtranjero() {	
		if(CheckBoxPadre.isChecked()){Padre=",1";}else{Padre="";}
		if(CheckBoxMadre.isChecked()){Madre=",2";}else{Madre="";}
		if(CheckBoxHijo_a.isChecked()){Hijo_a=",3";}else{Hijo_a="";}
		if(CheckBoxEsposo_a.isChecked()){Esposo_a=",4";}else{Esposo_a="";}
		if(CheckBoxOtrosIE.isChecked()){OtrosIE=",5";}else{OtrosIE="";}
		if(CheckBoxNingunIE.isChecked()){NingunIE=",6";}else{NingunIE="";}	
	}
 
	private void getCheckAyudaGobierno() {		  
		if(CheckBoxsubsidio_gas.isChecked()){subsidio_gas=",1";}else{subsidio_gas="";}
		if(CheckBoxsubsidio_energia_elec.isChecked()){subsidio_energia_elec=",2";}else{subsidio_energia_elec="";}
		if(CheckBoxbonos_comunidades_rurales.isChecked()){bonos_comunidades_rurales=",3";}else{bonos_comunidades_rurales="";}
		if(CheckBoxbonos_comunidades_urbanas.isChecked()){bonos_comunidades_urbanas=",4";}else{bonos_comunidades_urbanas="";}
		if(CheckBoxpension_basica_universal.isChecked()){pension_basica_universal=",5";}else{pension_basica_universal="";}
		if(CheckBoxotro_tipo_apoyo.isChecked()){otro_tipo_apoyo=",6";}else{otro_tipo_apoyo="";}	
		if(CheckBoxningun_tipo_apoyo.isChecked()){ningun_tipo_apoyo=",7";}else{ningun_tipo_apoyo="";}
	}
	
	public int	valida_integrante_extranjero(){
		if(CheckBoxPadre.isChecked() || CheckBoxMadre.isChecked() || CheckBoxHijo_a.isChecked() || CheckBoxEsposo_a.isChecked() || CheckBoxOtrosIE.isChecked() || CheckBoxNingunIE.isChecked()){
			return 1;	
		}else{
			return 0;
		}
		
	}
	public void click_ninguno_extranjero(View view) {
		if(CheckBoxNingunIE.isChecked()){//desactivar todos
			CheckBoxPadre.setChecked(false);
			CheckBoxMadre.setChecked(false);
			CheckBoxHijo_a.setChecked(false);
			CheckBoxEsposo_a.setChecked(false);
			CheckBoxOtrosIE.setChecked(false);
		}
	}
	public void click_padre(View view) {
		if(CheckBoxPadre.isChecked()){
			CheckBoxNingunIE.setChecked(false);
		}  
	}
	public void click_madre(View view) {
		if(CheckBoxMadre.isChecked()){
			CheckBoxNingunIE.setChecked(false);
		}  
	}
	public void click_hijo_a(View view) {
		if(CheckBoxHijo_a.isChecked()){
			CheckBoxNingunIE.setChecked(false);
		}  
	}
	public void click_esposo_a(View view) {
		if(CheckBoxEsposo_a.isChecked()){
			CheckBoxNingunIE.setChecked(false);
		}  
	}
	public void click_otros_extranjero(View view) {
		if(CheckBoxOtrosIE.isChecked()){
			CheckBoxNingunIE.setChecked(false);
		}  
	}
 
	public int	valida_recibe_ayuda_del_gobierno(){
			if(CheckBoxsubsidio_gas.isChecked() || CheckBoxsubsidio_energia_elec.isChecked() || CheckBoxbonos_comunidades_rurales.isChecked() || CheckBoxbonos_comunidades_urbanas.isChecked() || CheckBoxpension_basica_universal.isChecked() || CheckBoxotro_tipo_apoyo.isChecked() || CheckBoxningun_tipo_apoyo.isChecked()){
				return 1;	
			}else{
				return 0;
			}
			
		}
	 
	public void click_ningunapoyo(View view) {
		if(CheckBoxningun_tipo_apoyo.isChecked()){//desactivar todos
			CheckBoxsubsidio_gas.setChecked(false);
			CheckBoxsubsidio_energia_elec.setChecked(false);
			CheckBoxbonos_comunidades_rurales.setChecked(false);
			CheckBoxbonos_comunidades_urbanas.setChecked(false);
			CheckBoxpension_basica_universal.setChecked(false);
			CheckBoxotro_tipo_apoyo.setChecked(false);
			
		}
	}
	public void click_subsidiogas(View view) {
		if(numerocorrelativo.equals("01")||numerocorrelativo.equals("1")){
			if(CheckBoxsubsidio_gas.isChecked()){
				CheckBoxningun_tipo_apoyo.setChecked(false);
			} 
		}else{
			if(CheckBoxsubsidio_gas.isChecked()){
				CheckBoxsubsidio_gas.setChecked(false);
				Toast T_recibe_ayuda_del_gobierno=Toast.makeText(this, "Esta opción es solo para jefes de hogar", Toast.LENGTH_SHORT);
				T_recibe_ayuda_del_gobierno.setGravity(Gravity.CENTER, 0, 0);
				T_recibe_ayuda_del_gobierno.show();
			}
		}
		 
	}
	public void click_subsidio_ener_elec(View view) {
		if(numerocorrelativo.equals("01")||numerocorrelativo.equals("1")){
			if(CheckBoxsubsidio_energia_elec.isChecked()){
				CheckBoxningun_tipo_apoyo.setChecked(false);
			} 
		}else{
			if(CheckBoxsubsidio_energia_elec.isChecked()){
				CheckBoxsubsidio_energia_elec.setChecked(false);
				Toast T_recibe_ayuda_del_gobierno=Toast.makeText(this, "Esta opción es solo para jefes de hogar", Toast.LENGTH_SHORT);
				T_recibe_ayuda_del_gobierno.setGravity(Gravity.CENTER, 0, 0);
				T_recibe_ayuda_del_gobierno.show();
			}
		}	  
	}
	public void click_bonos_comunidades_rurales(View view) {
		if(numerocorrelativo.equals("01")||numerocorrelativo.equals("1")){
			if(CheckBoxbonos_comunidades_rurales.isChecked()){
				CheckBoxningun_tipo_apoyo.setChecked(false);
			} 
		}else{
			if(CheckBoxbonos_comunidades_rurales.isChecked()){
				CheckBoxbonos_comunidades_rurales.setChecked(false);
				Toast T_recibe_ayuda_del_gobierno=Toast.makeText(this, "Esta opción es solo para jefes de hogar", Toast.LENGTH_SHORT);
				T_recibe_ayuda_del_gobierno.setGravity(Gravity.CENTER, 0, 0);
				T_recibe_ayuda_del_gobierno.show();
			}
		}	
	}
	public void click_bonos_comunidades_urbanas(View view) {
		if(numerocorrelativo.equals("01")||numerocorrelativo.equals("1")){
			if(CheckBoxbonos_comunidades_urbanas.isChecked()){
				CheckBoxningun_tipo_apoyo.setChecked(false);
			} 
		}else{
			if(CheckBoxbonos_comunidades_urbanas.isChecked()){
				CheckBoxbonos_comunidades_urbanas.setChecked(false);
				Toast T_recibe_ayuda_del_gobierno=Toast.makeText(this, "Esta opción es solo para jefes de hogar", Toast.LENGTH_SHORT);
				T_recibe_ayuda_del_gobierno.setGravity(Gravity.CENTER, 0, 0);
				T_recibe_ayuda_del_gobierno.show();
			}
		}  
	}
	public void click_pension_basica_universal(View view) {
		if(CheckBoxpension_basica_universal.isChecked()){
			CheckBoxningun_tipo_apoyo.setChecked(false);
		}  
	}
	public void click_otro_tipo_apoyo(View view) {
		if(CheckBoxotro_tipo_apoyo.isChecked()){
			CheckBoxningun_tipo_apoyo.setChecked(false);
		}  
	}
	public void click_guardar_economico(View view) {
 	if(id_TrabajoRemunerado.equals("99")){
			SpnTrabajoRemunerado.requestFocusFromTouch();
			Toast T_TrabajoRemunerado=Toast.makeText(this, "Seleccione si tiene trabajo remunerado", Toast.LENGTH_LONG);
			T_TrabajoRemunerado.setGravity(Gravity.CENTER, 0, 0);
			T_TrabajoRemunerado.show();
		}else if(id_Ocupacion.equals("99")){
			SpnOcupacion.requestFocusFromTouch();
			Toast T_Ocupacion=Toast.makeText(this, "Seleccione la ocupación", Toast.LENGTH_LONG);
			T_Ocupacion.setGravity(Gravity.CENTER, 0, 0);
			T_Ocupacion.show();
		}else if(valida_integrante_extranjero()==0){
			Toast T_integrante_extranjero=Toast.makeText(this, "Marque al menos una opción de integrantes en el extranjero", Toast.LENGTH_LONG);
			T_integrante_extranjero.setGravity(Gravity.CENTER, 0, 0);
			T_integrante_extranjero.show();
		}else if(valida_recibe_ayuda_del_gobierno()==0){
			Toast T_recibe_ayuda_del_gobierno=Toast.makeText(this, "Marque al menos una opción de ayuda del gobierno ", Toast.LENGTH_LONG);
			T_recibe_ayuda_del_gobierno.setGravity(Gravity.CENTER, 0, 0);
			T_recibe_ayuda_del_gobierno.show();
		}else if(id_AyudaEconomicaFam.equals("99")){
			SpnAyudaEconomicaFam.requestFocusFromTouch();
			Toast T_AyudaEconomicaFam=Toast.makeText(this, "Seleccione la ayuda economica familiar", Toast.LENGTH_LONG);
			T_AyudaEconomicaFam.setGravity(Gravity.CENTER, 0, 0);
			T_AyudaEconomicaFam.show();
		}else if(id_ProMemoriaHistorica.equals("99")){
			SpnProMemoriaHistorica.requestFocusFromTouch();
			Toast T_ProMemoriaHistorica=Toast.makeText(this, "Seleccione la ayuda economica familiar", Toast.LENGTH_LONG);
			T_ProMemoriaHistorica.setGravity(Gravity.CENTER, 0, 0);
			T_ProMemoriaHistorica.show();
		}else{
			/*GuardarEditarEconomico GuardarEditar = new GuardarEditarEconomico(this);
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
	public void cargarOcupacion(String id_TrabajoRemunerado){
		List<SpinnerObjectString> lablesOcupacion = objGestionDB.getOcupacion(45,edad,id_TrabajoRemunerado,this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapterOcupacion= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_list_item_1, lablesOcupacion);
		SpnOcupacion.setAdapter(dataAdapterOcupacion);
		SpnOcupacion.setClickable(true);
		
		for(int i=0; i<lablesOcupacion.size(); i++){
			if(edad<14){
				SpnOcupacion.setSelection(4);
				SpnOcupacion.setClickable(false);
			}else
			{
				if(id_TrabajoRemunerado.equals("0")){
					SpnOcupacion.setSelection(4);
					SpnOcupacion.setClickable(false);
					//todos
				}else{
					if(lablesOcupacion.get(i).getCodigo().equals(Ocupacion)){
						SpnOcupacion.setSelection(i);
					}	
				}		
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
 
	public class RSAsyncEconomico extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para los spinner
		
		//posiciones en las listas
		//para los spinner
		
		
		public RSAsyncEconomico(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			/*if (action.equals("Edit")){
				CargarCheckBox();	
	    	}*/
			
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

		public void Toastsd(String texto) {
			 
			}
		

	}
	public class GuardarEditarEconomico extends AsyncTask<String, Void, String> {  
		Context contexto;
	    protected String doInBackground(String... params) {
			    		/*if(action.equals("New")){
			    			guardarEconomico();
			    		}else{
			    			actualizarEconomico();
			    		}*/
	    	
	    		return null;
	    	}
	    public GuardarEditarEconomico(Context contexto){	
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
    	public void guardarEconomico(){
	    	//String fechaactual=objGestionDB.fechaActual();
	    	
	    	//guardo integrantes en el extranjero
	    	/*getCheckIntegranteEnExtranjero();
	    	String IntegranteEnExtranjero=Padre+Madre+Hijo_a+Esposo_a+OtrosIE+NingunIE;
	    	IntegranteEnExtranjero=IntegranteEnExtranjero.substring(1, IntegranteEnExtranjero.length());*/
	    	//objGestionDB.insertIntegranteVariable(115, fechaactual, IntegranteEnExtranjero, idintegrante, correlativo_tablet, id_estasib_user_sp); 
	    	
	    	//guardo ayuda del gobierno
	    	/*getCheckAyudaGobierno();
	    	String AyudaGobierno=subsidio_gas+subsidio_energia_elec+bonos_comunidades_rurales+bonos_comunidades_urbanas+otro_tipo_apoyo+ningun_tipo_apoyo;
	    	AyudaGobierno=AyudaGobierno.substring(1, AyudaGobierno.length());*/
	    	//objGestionDB.insertIntegranteVariable(84, fechaactual, AyudaGobierno, idintegrante, correlativo_tablet, id_estasib_user_sp); 
	    	
	    	//guardo todos los spinner
	    	//objGestionDB.insertIntegranteVariable(112, fechaactual, id_TrabajoRemunerado, idintegrante, correlativo_tablet, id_estasib_user_sp); 
	    	//objGestionDB.insertIntegranteVariable(69, fechaactual, id_Ocupacion, idintegrante, correlativo_tablet, id_estasib_user_sp); 
	    	//objGestionDB.insertIntegranteVariable(116, fechaactual, id_AyudaEconomicaFam, idintegrante, correlativo_tablet, id_estasib_user_sp); 
	    	//objGestionDB.insertIntegranteVariable(90, fechaactual, id_ProMemoriaHistorica, idintegrante, correlativo_tablet, id_estasib_user_sp); 
	    	
 
	    }
    	public void actualizarEconomico(){
    		//actualizo integrantes en el extranjero
	    /*	getCheckIntegranteEnExtranjero();
	    	String IntegranteEnExtranjero=Padre+Madre+Hijo_a+Esposo_a+OtrosIE+NingunIE;
	    	IntegranteEnExtranjero=IntegranteEnExtranjero.substring(1, IntegranteEnExtranjero.length());
	    	*/
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 115, IntegranteEnExtranjero);
    		
    		
    		//actualizo ayuda del gobierno
	    /*	getCheckAyudaGobierno();
	    	String AyudaGobierno=subsidio_gas+subsidio_energia_elec+bonos_comunidades_rurales+bonos_comunidades_urbanas+otro_tipo_apoyo+ningun_tipo_apoyo;
	    	AyudaGobierno=AyudaGobierno.substring(1, AyudaGobierno.length());
	    	*/
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 84, AyudaGobierno);
	    	
	    	//actualizo los spinnner
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 112, id_TrabajoRemunerado);
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 69, id_Ocupacion);
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 116, id_AyudaEconomicaFam);
	    	//objGestionDB.updateIntegranteVariable(idintegrante, 90, id_ProMemoriaHistorica);
    	}
    

    	
	}	
}
