package com.fichafamiliar;

import java.io.IOException;
import java.util.List;

import com.fichafamiliar.Economico.GuardarEditarEconomico;
import com.fichafamiliar.Economico.RSAsyncEconomico;

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
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Salud extends Activity {
	//private Integrantes classIntegrante= new Integrantes();
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	String action="";
	int idfamilia=0;
	int idintegrante=0;
	
	int id_sp;					// id usuario tablet
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	
	String 		id_MedicamentosEnfCronica;
	Spinner 	SpnMedicamentosEnfCronica;
	
	
	public String 	MedicamentosEnfCronica;
	String numerosTipoDiscapacidad;
	String numerosCausaDiscapacidad ;
	String numerosActividadesRequiereAsistencia;
	String numerosEnfermedadesCronicas;
	
	//Discapacidades
	CheckBox 	Checkintelectual,
				Checksensorial,
				Checkfisica,
				Checkmentalpsiquiatrica,
				Checkorganica,
				Checksindiscapacidad;
	
	String 		intelectual="",
				sensorial="",
				fisica="",
				mentalpsiquiatrica="",
				organica="",
				sindiscapacidad="";
	
	//Causa discapacidad
	CheckBox	Checkcongenita,
				Checkaccidente,
				Checkporminas,
				Checkotrosartefactos,
				Checksecuelaenfermedad,
				Checknoaplica;
	
	String		congenita="",
				acciedente="",
				porminas="",
				trosartefactos="",
				secuelaenfermedad="",
				noaplica="";
	
	//actividades que requiere asistencia
	CheckBox	Checkaseopersonal,
				Checknecesidadesfisiologicas,
				Checkcaminarcorrer,
				Checkasistirconsulta,
				Checkninguna,
				Checkotrasactividades;
	
	String		aseopersonal="",
				necesidadesfisiologicas="",
				caminarcorrer="",
				asistirconsulta="",
				ninguna="",
				otrasactividades="";
	
	//padece enfermedades cronicas
	CheckBox	Checknopresenta,
				Checkhipertencionarterial,
				Checkdiabetesmellitus,
				CheckrespiratoriasEPOC,
				Checkenfermedadrenalcronica,
				Checkepilepsia,
				Checkasmabronquial,
				Checkalguncancer,
				Checkaccidentecerebrovascular,
				Checkotracronica;
	
	String		nopresenta="",
				hipertencionarterial="",
				diabetesmellitus="",
				respiratoriasEPOC="",
				enfermedadrenalcronica="",
				epilepsia="",
				asmabronquial="",
				alguncancer="",
				accidentecerebrovascular="",
				otracronica="";
	
	private ProgressDialog progressDialog;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salud);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		 
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New" 
		//Log.i("ACTION EN SALUD:", action);
		
		idfamilia= bundle.getInt("idfamilia"); 
		idintegrante= bundle.getInt("idintegrante"); 
		
		SpnMedicamentosEnfCronica= (Spinner) findViewById(R.id.SpnMedicamentosEnfCronica);
		
		//Check Discapacidades
		Checkintelectual=(CheckBox) findViewById(R.id.Checkintelectual);
		Checksensorial=(CheckBox) findViewById(R.id.Checksensorial);
		Checkfisica=(CheckBox) findViewById(R.id.Checkfisica);
		Checkmentalpsiquiatrica=(CheckBox) findViewById(R.id.Checkmentalpsiquiatrica);
		Checkorganica=(CheckBox) findViewById(R.id.Checkorganica);
		Checksindiscapacidad=(CheckBox) findViewById(R.id.Checksindiscapacidad);
		
		//Check Causa discapacidad
		Checkcongenita=(CheckBox) findViewById(R.id.Checkcongenita);
		Checkaccidente=(CheckBox) findViewById(R.id.Checkaccidente);
		Checkporminas=(CheckBox) findViewById(R.id.Checkporminas);
		Checkotrosartefactos=(CheckBox) findViewById(R.id.Checkotrosartefactos);
		Checksecuelaenfermedad=(CheckBox) findViewById(R.id.Checksecuelaenfermedad);
		Checknoaplica=(CheckBox) findViewById(R.id.Checknoaplica);
		
		//Check actividades que requiere asistencia
		Checkaseopersonal=(CheckBox) findViewById(R.id.Checkaseopersonal);
		Checknecesidadesfisiologicas=(CheckBox) findViewById(R.id.Checknecesidadesfisiologicas);
		Checkcaminarcorrer=(CheckBox) findViewById(R.id.Checkcaminarcorrer);
		Checkasistirconsulta=(CheckBox) findViewById(R.id.Checkasistirconsulta);
		Checkninguna=(CheckBox) findViewById(R.id.Checkninguna);
		Checkotrasactividades=(CheckBox) findViewById(R.id.Checkotrasactividades);
		
		//Check padece enfermedades cronicas
		Checknopresenta=(CheckBox) findViewById(R.id.Checknopresenta);
		Checkhipertencionarterial=(CheckBox) findViewById(R.id.Checkhipertencionarterial);
		Checkdiabetesmellitus=(CheckBox) findViewById(R.id.Checkdiabetesmellitus);
		CheckrespiratoriasEPOC=(CheckBox) findViewById(R.id.CheckrespiratoriasEPOC);
		Checkenfermedadrenalcronica=(CheckBox) findViewById(R.id.Checkenfermedadrenalcronica);
		Checkepilepsia=(CheckBox) findViewById(R.id.Checkepilepsia);
		Checkasmabronquial=(CheckBox) findViewById(R.id.Checkasmabronquial);
		Checkalguncancer=(CheckBox) findViewById(R.id.Checkalguncancer);
		Checkaccidentecerebrovascular=(CheckBox) findViewById(R.id.Checkaccidentecerebrovascular);
		Checkotracronica=(CheckBox) findViewById(R.id.Checkotracronica);
		
		
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
		
		/*RSAsyncSalud leerAsync = new RSAsyncSalud(this);
		leerAsync.execute();*/
		CargarCheckBox();
		CargarSpinner();
		
		SpnMedicamentosEnfCronica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_MedicamentosEnfCronica = (((SpinnerObjectString)SpnMedicamentosEnfCronica.getSelectedItem()).getCodigo());
				if(id_MedicamentosEnfCronica.equals("NA")){
					Checknopresenta.setChecked(true);
					Checkhipertencionarterial.setChecked(false);
					Checkdiabetesmellitus.setChecked(false);
					CheckrespiratoriasEPOC.setChecked(false);
					Checkenfermedadrenalcronica.setChecked(false);
					Checkasmabronquial.setChecked(false);
					Checkalguncancer.setChecked(false);
					Checkaccidentecerebrovascular.setChecked(false);
					Checkotracronica.setChecked(false);
					Checkepilepsia.setChecked(false);
				}else{
					Checknopresenta.setChecked(false);
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
}//termina el oncreate
	private void CargarCheckBox() {
		cargarCheckTipoDiscapacidad();
		cargarCheckCausaDiscapacidad(); 
		cargarCheckActividadesRequiereAsistencia();
	    cargarCheckEnfermedadesCronicas();
	}
	
	public void CargarSpinner() {
		 int	MedicamentosEnfCronica_pos=0;
		 
		ArrayAdapter<SpinnerObjectString> dataAdapterMedicamentosEnfCronica;
		
		
		List<SpinnerObjectString> lablesMedicamentosEnfCronica = objGestionDB.getCatalogoDescriptor(29,this.contexto);
		dataAdapterMedicamentosEnfCronica = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMedicamentosEnfCronica);
		//averiguar la posicion en la lista
		//if(action.equals("Edit")){
			//recupero el valor que tienen las variable cuando se creo la ficha
			MedicamentosEnfCronica=objGestionDB.getValorIntegranteSelecionado(idintegrante,10,this.contexto);
		for(int i=0; i<lablesMedicamentosEnfCronica.size(); i++){
			if(lablesMedicamentosEnfCronica.get(i).getCodigo().equals(MedicamentosEnfCronica)){
				MedicamentosEnfCronica_pos=i;
	        }       
          }
		
		
		dataAdapterMedicamentosEnfCronica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpnMedicamentosEnfCronica.setAdapter(dataAdapterMedicamentosEnfCronica);
		
		SpnMedicamentosEnfCronica.setSelection(MedicamentosEnfCronica_pos);
		//}
	}
	private void cargarCheckTipoDiscapacidad() {
		  numerosTipoDiscapacidad = objGestionDB.getValorIntegranteSelecionado(idintegrante,86,this.contexto);
		 
		 String[] numerosComoArray = numerosTipoDiscapacidad.split(",");
		// Log.i("numerosTipoDiscapacidad:  ", numerosComoArray[0]);
		   for (int i = 0; i < numerosComoArray.length; i++) {
		    	 if(numerosComoArray[i].equals("1")){Checkintelectual.setChecked(true);}
			     if(numerosComoArray[i].equals("2")){Checksensorial.setChecked(true);}
			     if(numerosComoArray[i].equals("3")){Checkfisica.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){Checkmentalpsiquiatrica.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){Checkorganica.setChecked(true);}
			     if(numerosComoArray[i].equals("6")){Checksindiscapacidad.setChecked(true);}
		    	}
	}
	private void cargarCheckCausaDiscapacidad() {
		   numerosCausaDiscapacidad = objGestionDB.getValorIntegranteSelecionado(idintegrante,85,this.contexto);
		    String[] numerosComoArray = numerosCausaDiscapacidad.split(",");
		   for (int i = 0; i < numerosComoArray.length; i++) {
		    	 if(numerosComoArray[i].equals("1")){Checkcongenita.setChecked(true);}
			     if(numerosComoArray[i].equals("2")){Checkaccidente.setChecked(true);}
			     if(numerosComoArray[i].equals("3")){Checkporminas.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){Checkotrosartefactos.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){Checksecuelaenfermedad.setChecked(true);}
			     if(numerosComoArray[i].equals("NA")){Checknoaplica.setChecked(true);}
			    
		    	}
	}
	private void cargarCheckActividadesRequiereAsistencia() {
		  numerosActividadesRequiereAsistencia = objGestionDB.getValorIntegranteSelecionado(idintegrante,113,this.contexto);
		   String[] numerosComoArray = numerosActividadesRequiereAsistencia.split(",");
		   
		    for (int i = 0; i < numerosComoArray.length; i++) {
		    	 if(numerosComoArray[i].equals("1")){Checkaseopersonal.setChecked(true);}
			     if(numerosComoArray[i].equals("2")){Checknecesidadesfisiologicas.setChecked(true);}
			     if(numerosComoArray[i].equals("3")){Checkcaminarcorrer.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){Checkasistirconsulta.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){Checkninguna.setChecked(true);}
			     if(numerosComoArray[i].equals("6")){Checkotrasactividades.setChecked(true);}
			      //Log.i("posicion-valor:" , numerosComoArray[i]);
		    	} 
	}
	private void cargarCheckEnfermedadesCronicas() {
		 	numerosEnfermedadesCronicas = objGestionDB.getValorIntegranteSelecionado(idintegrante,81,this.contexto);
		 	String[] numerosComoArray = numerosEnfermedadesCronicas.split(",");
		 	
		  for (int i = 0; i < numerosComoArray.length; i++) {
		    	 if(numerosComoArray[i].equals("0")){Checknopresenta.setChecked(true);}
		    	 if(numerosComoArray[i].equals("1")){Checkhipertencionarterial.setChecked(true);}
		    	 
			     if(numerosComoArray[i].equals("2")){Checkdiabetesmellitus.setChecked(true);}
			     
			     if(numerosComoArray[i].equals("3")){CheckrespiratoriasEPOC.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){Checkasmabronquial.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){Checkalguncancer.setChecked(true);}
			     
			     if(numerosComoArray[i].equals("6")){Checkaccidentecerebrovascular.setChecked(true);}
			     if(numerosComoArray[i].equals("7")){Checkenfermedadrenalcronica.setChecked(true);}
			     if(numerosComoArray[i].equals("8")){Checkepilepsia.setChecked(true);}
			     if(numerosComoArray[i].equals("9")){Checkotracronica.setChecked(true);}
		    	}
		
	
	}
	private void getCheckTipoDiscapacidad() {
		if(Checkintelectual.isChecked()){intelectual=",1";}else{intelectual="";}
		if(Checksensorial.isChecked()){sensorial=",2";}else{sensorial="";}
		if(Checkfisica.isChecked()){fisica=",3";}else{fisica="";}
		if(Checkmentalpsiquiatrica.isChecked()){mentalpsiquiatrica=",4";}else{mentalpsiquiatrica="";}
		if(Checkorganica.isChecked()){organica=",5";}else{organica="";}
		if(Checksindiscapacidad.isChecked()){sindiscapacidad=",6";}else{sindiscapacidad="";}
	}
	private void getCheckCausaDiscapacidad() {
			
		if(Checkcongenita.isChecked()){congenita=",1";}else{congenita="";}
		if(Checkaccidente.isChecked()){acciedente=",2";}else{acciedente="";}
		if(Checkporminas.isChecked()){porminas=",3";}else{porminas="";}
		if(Checkotrosartefactos.isChecked()){trosartefactos=",4";}else{trosartefactos="";}
		if(Checksecuelaenfermedad.isChecked()){secuelaenfermedad=",5";}else{secuelaenfermedad="";}
		if(Checknoaplica.isChecked()){noaplica=",NA";}else{noaplica="";}
	}
	private void getCheckActividadesRequiereAsistencia() {
		if(Checkaseopersonal.isChecked()){aseopersonal=",1";}else{aseopersonal="";}
		if(Checknecesidadesfisiologicas.isChecked()){necesidadesfisiologicas=",2";}else{necesidadesfisiologicas="";}
		if(Checkcaminarcorrer.isChecked()){caminarcorrer=",3";}else{caminarcorrer="";}
		if(Checkasistirconsulta.isChecked()){asistirconsulta=",4";}else{asistirconsulta="";}
		if(Checkninguna.isChecked()){ninguna=",5";}else{ninguna="";}
		if(Checkotrasactividades.isChecked()){otrasactividades=",6";}else{otrasactividades="";}
	 }
	private void getCheckEnfermedadesCronicas() {
		if(Checknopresenta.isChecked()){nopresenta=",0";}else{nopresenta="";}
		if(Checkhipertencionarterial.isChecked()){hipertencionarterial=",1";}else{hipertencionarterial="";}
		if(Checkdiabetesmellitus.isChecked()){diabetesmellitus=",2";}else{diabetesmellitus="";}
		if(CheckrespiratoriasEPOC.isChecked()){respiratoriasEPOC=",3";}else{respiratoriasEPOC="";}
		if(Checkasmabronquial.isChecked()){asmabronquial=",4";}else{asmabronquial="";}
		if(Checkalguncancer.isChecked()){alguncancer=",5";}else{alguncancer="";}
		if(Checkaccidentecerebrovascular.isChecked()){accidentecerebrovascular=",6";}else{accidentecerebrovascular="";}
		if(Checkenfermedadrenalcronica.isChecked()){enfermedadrenalcronica=",7";}else{enfermedadrenalcronica="";}
		if(Checkepilepsia.isChecked()){epilepsia=",8";}else{epilepsia="";}
		if(Checkotracronica.isChecked()){otracronica=",9";}else{otracronica="";}
	 }
	
	public void EG(){
		String fechaactual=objGestionDB.fechaActual();
		
		numerosTipoDiscapacidad = objGestionDB.getValorIntegranteSelecionado(idintegrante,86,this.contexto);
		numerosCausaDiscapacidad = objGestionDB.getValorIntegranteSelecionado(idintegrante,85,this.contexto);
		numerosActividadesRequiereAsistencia = objGestionDB.getValorIntegranteSelecionado(idintegrante,113,this.contexto);
		numerosEnfermedadesCronicas = objGestionDB.getValorIntegranteSelecionado(idintegrante,81,this.contexto);
		
		
		getCheckTipoDiscapacidad();
    	String TipoDiscapacidad=intelectual+sensorial+fisica+mentalpsiquiatrica+organica+sindiscapacidad;
    	TipoDiscapacidad=TipoDiscapacidad.substring(1,TipoDiscapacidad.length());
    	
    	
    	getCheckCausaDiscapacidad();
    	String CausaDiscapacidad=congenita+acciedente+porminas+trosartefactos+secuelaenfermedad+noaplica;
    	CausaDiscapacidad=CausaDiscapacidad.substring(1,CausaDiscapacidad.length());
   	
       	
    	getCheckActividadesRequiereAsistencia();
    	String ActividadesRequiereAsistencia=aseopersonal+necesidadesfisiologicas+caminarcorrer+asistirconsulta+ninguna+otrasactividades;
    	ActividadesRequiereAsistencia=ActividadesRequiereAsistencia.substring(1,ActividadesRequiereAsistencia.length());
    	
    	getCheckEnfermedadesCronicas();
    	String EnfermedadesCronicas=nopresenta+hipertencionarterial+diabetesmellitus+respiratoriasEPOC+enfermedadrenalcronica+epilepsia+asmabronquial+alguncancer+accidentecerebrovascular+otracronica;
    	EnfermedadesCronicas=EnfermedadesCronicas.substring(1,EnfermedadesCronicas.length());
	 
		if(MedicamentosEnfCronica.equals("") || MedicamentosEnfCronica==null || MedicamentosEnfCronica.equals("99")){
					objGestionDB.insertIntegranteVariable(10, fechaactual, id_MedicamentosEnfCronica, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
					//Log.i("primer: ","Insert");
				}//insert
		else{
					objGestionDB.updateIntegranteVariable(idintegrante, 10, id_MedicamentosEnfCronica, this.contexto);
					//Log.i("primer: ","update");
				}//update
			
			
			if(numerosTipoDiscapacidad.equals("")){
				objGestionDB.insertIntegranteVariable(86,fechaactual,TipoDiscapacidad, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
				//insert
				//Log.i("segundo: ","Insert");
			}else{
					objGestionDB.updateIntegranteVariable(idintegrante, 86, TipoDiscapacidad, this.contexto);
					//Log.i("segundo: ","Update");
				 }//update
		 
		 if(numerosCausaDiscapacidad.equals("") || numerosCausaDiscapacidad==null || numerosCausaDiscapacidad.equals("99")){
				objGestionDB.insertIntegranteVariable(85,fechaactual,CausaDiscapacidad, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
				//Log.i("tercer: ","Insert");
				}//insert
			else{
				objGestionDB.updateIntegranteVariable(idintegrante, 85, CausaDiscapacidad, this.contexto);
				//Log.i("tercer: ","Update");
				}//update
		 	
			
		 	
			if(numerosActividadesRequiereAsistencia.equals("") || numerosActividadesRequiereAsistencia==null || numerosActividadesRequiereAsistencia.equals("99")){
				objGestionDB.insertIntegranteVariable(113,fechaactual,ActividadesRequiereAsistencia, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
				//Log.i("cuarto: ","Insert");
				}//insert
			else{
				objGestionDB.updateIntegranteVariable(idintegrante, 113, ActividadesRequiereAsistencia, this.contexto);
				//Log.i("cuarto: ","Update");
				}//update
		 
			
		 if(numerosEnfermedadesCronicas.equals("") || numerosEnfermedadesCronicas==null || numerosEnfermedadesCronicas.equals("99") ){//insert
				objGestionDB.insertIntegranteVariable(81,fechaactual,EnfermedadesCronicas, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
				//Log.i("quinto: ","Insert");
			}else{//update
				objGestionDB.updateIntegranteVariable(idintegrante, 81, EnfermedadesCronicas, this.contexto);
				//Log.i("quinto: ","Update");
				} 
		}
	
	//Discapacidades	
	public int	valida_discapacidades(){
		if(Checkintelectual.isChecked() 
				|| Checksensorial.isChecked() 
				|| Checkfisica.isChecked() 
				|| Checkmentalpsiquiatrica.isChecked() 
				|| Checkorganica.isChecked() 
				|| Checksindiscapacidad.isChecked()){
			return 1;	
		}else{
			return 0;
		}
	}
	public void click_sindiscapacidad(View view) {
		if(Checksindiscapacidad.isChecked()){//desactivar todos
			Checkintelectual.setChecked(false);
			Checksensorial.setChecked(false);
			Checkfisica.setChecked(false);
			Checkmentalpsiquiatrica.setChecked(false);
			Checkorganica.setChecked(false);
			
			//activo no aplica de las causas de discapacidad
			Checknoaplica.setChecked(true);
			Checkninguna.setChecked(true);
			
		
			//desactivo las causas de discapacidad
			Checkcongenita.setChecked(false);
			Checkaccidente.setChecked(false);
			Checkporminas.setChecked(false);
			Checkotrosartefactos.setChecked(false);
			Checksecuelaenfermedad.setChecked(false);
			
			//desactivo las actividades que requiere asistencia
			
			Checkaseopersonal.setChecked(false);
			Checknecesidadesfisiologicas.setChecked(false);
			Checkcaminarcorrer.setChecked(false);
			Checkasistirconsulta.setChecked(false);
			Checkotrasactividades.setChecked(false);
		}else{
			if(Checknoaplica.isChecked()){
				Checknoaplica.setChecked(false);
			}
			if(Checkninguna.isChecked()){
				Checkninguna.setChecked(false);
			}
		}
	}
	public void click_intelectual(View view) {
		if(Checkintelectual.isChecked()){
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
			Checkninguna.setChecked(false);
		}  
	}
	public void click_sensorial(View view) {
		if(Checksensorial.isChecked()){
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
			Checkninguna.setChecked(false);
		}  
	}
	public void click_fisica(View view) {
		if(Checkfisica.isChecked()){
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
		}  Checkninguna.setChecked(false);
	}
	public void click_mentalpsiquiatrica(View view) {
		if(Checkmentalpsiquiatrica.isChecked()){
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
			Checkninguna.setChecked(false);
		}  
	}
	public void click_organica(View view) {
		if(Checkorganica.isChecked()){
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
			Checkninguna.setChecked(false);
		}  
	}
	
	//valida causa discapacidades
	public int	valida_causa_discapacidades(){
		if(Checkcongenita.isChecked() 
				|| Checkaccidente.isChecked() 
				|| Checkporminas.isChecked() 
				|| Checkotrosartefactos.isChecked() 
				|| Checksecuelaenfermedad.isChecked() 
				|| Checknoaplica.isChecked()){
			return 1;	
		}else{
			return 0;
		}
	}
	public void click_noaplica(View view) {
		if(Checknoaplica.isChecked()){//desactivar todos
			Checkcongenita.setChecked(false);
			Checkaccidente.setChecked(false);
			Checkporminas.setChecked(false);
			Checkotrosartefactos.setChecked(false);
			Checksecuelaenfermedad.setChecked(false); 
			
			//activo sin discapacidad 
			Checksindiscapacidad.setChecked(true);
			//activo ninguna
			Checkninguna.setChecked(true);
			
			//desactivo las discapacidades
			Checkintelectual.setChecked(false);
			Checksensorial.setChecked(false);
			Checkfisica.setChecked(false); 
			Checkmentalpsiquiatrica.setChecked(false);
			Checkorganica.setChecked(false);

			Checkaseopersonal.setChecked(false);
			Checknecesidadesfisiologicas.setChecked(false);
			Checkcaminarcorrer.setChecked(false);
			Checkasistirconsulta.setChecked(false);
			Checkotrasactividades.setChecked(false);
			
		}else{
			if(Checksindiscapacidad.isChecked()){
				Checksindiscapacidad.setChecked(false); 	
			}
			if(Checkninguna.isChecked()){
				Checkninguna.setChecked(false);
				
			}
		}
			
	}
	public void click_congenita(View view) {
		if(Checkcongenita.isChecked()){
			Checknoaplica.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checkninguna.setChecked(false);
		}
	}
	public void click_accidentes_traumatismo(View view) {
		if(Checkaccidente.isChecked()){
			Checknoaplica.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checkninguna.setChecked(false);
		} 
	}
	public void click_porminas(View view) {
		if(Checkporminas.isChecked()){
			Checknoaplica.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checkninguna.setChecked(false);
		}
	}
	public void click_otrosartefactos(View view) {
		if(Checkotrosartefactos.isChecked()){
			Checknoaplica.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checkninguna.setChecked(false);
		}
	}
	public void click_secuelaenfermedad(View view) {
		if(Checksecuelaenfermedad.isChecked()){
			Checknoaplica.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checkninguna.setChecked(false);
		} 
	}
	//actividades que requiere asistencia
	public int	valida_actividades_requiere_asistencia(){
		if(Checkaseopersonal.isChecked() 
				|| Checknecesidadesfisiologicas.isChecked()
				|| Checkcaminarcorrer.isChecked() 
				|| Checkasistirconsulta.isChecked() 
				|| Checkninguna.isChecked() 
				|| Checkotrasactividades.isChecked()){
			return 1;	
		}else{
			return 0;
		}
 
	}
	public void click_ningunaactividad(View view) {
		if(Checkninguna.isChecked()){//desactivar todos
			Checkaseopersonal.setChecked(false);
			Checknecesidadesfisiologicas.setChecked(false);
			Checkcaminarcorrer.setChecked(false);
			Checkasistirconsulta.setChecked(false);
			Checkotrasactividades.setChecked(false);
			
			Checkintelectual.setChecked(false);
			Checksensorial.setChecked(false);
			Checkfisica.setChecked(false);
			Checkmentalpsiquiatrica.setChecked(false);
			Checkorganica.setChecked(false);
			
			Checkcongenita.setChecked(false);
			Checkaccidente.setChecked(false);
			Checkporminas.setChecked(false);
			Checkotrosartefactos.setChecked(false);
			Checksecuelaenfermedad.setChecked(false);
			
			Checksindiscapacidad.setChecked(true);
			Checknoaplica.setChecked(true);
		}else{
			if(Checksindiscapacidad.isChecked()){
				Checksindiscapacidad.setChecked(false); 	
			}
			if(Checknoaplica.isChecked()){
				Checknoaplica.setChecked(false);
			}
		}	
	}
	public void click_aseopersonal(View view) {
		if(Checkaseopersonal.isChecked()){
			Checkninguna.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
		}  
	}
	public void click_necesidadesfisiologicas(View view) {
		if(Checknecesidadesfisiologicas.isChecked()){
			Checkninguna.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
		}  
	}
	public void click_caminarcorrer(View view) {
		if(Checkcaminarcorrer.isChecked()){
			Checkninguna.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
		}  
	}
	public void click_asistirconsulta(View view) {
		if(Checkasistirconsulta.isChecked()){
			Checkninguna.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false);
		}  
	}
	public void click_otrasactividades(View view) {
		if(Checkotrasactividades.isChecked()){
			Checkninguna.setChecked(false);
			Checksindiscapacidad.setChecked(false);
			Checknoaplica.setChecked(false); 
		}  
	}
 
		//padece enfermedades cronicas			
		public int	valida_padece_enfermedades_cronicas(){
				   if( Checknopresenta.isChecked() 
					|| Checkhipertencionarterial.isChecked() 
					|| Checkdiabetesmellitus.isChecked() 
					|| CheckrespiratoriasEPOC.isChecked() 
					|| Checkenfermedadrenalcronica.isChecked() 
					|| Checkasmabronquial.isChecked()
					|| Checkalguncancer.isChecked() 
					|| Checkaccidentecerebrovascular.isChecked() 
					|| Checkotracronica.isChecked() 
					|| Checkepilepsia.isChecked())
			{
				return 1;	
			}else{
				return 0;
			}
		}
		public void click_nopresenta(View view) {
			if(Checknopresenta.isChecked()){//desactivar todos
				
				Checkhipertencionarterial.setChecked(false);
				Checkdiabetesmellitus.setChecked(false);
				CheckrespiratoriasEPOC.setChecked(false);
				Checkenfermedadrenalcronica.setChecked(false);
				Checkasmabronquial.setChecked(false);
				Checkalguncancer.setChecked(false);
				Checkaccidentecerebrovascular.setChecked(false);
				Checkotracronica.setChecked(false);
				Checkepilepsia.setChecked(false);
				
				
				
				SpnMedicamentosEnfCronica.setSelection(2);
			}
		}
		public void click_hipertencionarterial(View view) {
			if(Checkhipertencionarterial.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
		public void click_diabetesmellitus(View view) {
			if(Checkdiabetesmellitus.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
		public void click_respiratoriasepoc(View view) {
			if(CheckrespiratoriasEPOC.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
		public void click_enfermedadrenalcronica(View view) {
			if(Checkenfermedadrenalcronica.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
		public void click_alguncancer(View view) {
			if(Checkalguncancer.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
		public void click_epilepsia(View view) {
			if(Checkepilepsia.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
		public void click_asmabronquial(View view) {
			if(Checkasmabronquial.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
		public void click_accidente_cerebrovascular(View view) {
			if(Checkaccidentecerebrovascular.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
		public void click_otracronica(View view) {
			if(Checkotracronica.isChecked()){
				Checknopresenta.setChecked(false);
				SpnMedicamentosEnfCronica.setSelection(0);
			}  
		}
	
	public void click_GuardarEditarsalud(View view) {
		if(valida_discapacidades()==0){
			Toast T_discapacidades=Toast.makeText(this, "Marque al menos una opción de discapacidades", Toast.LENGTH_LONG);
			T_discapacidades.setGravity(Gravity.CENTER, 0, 0);
			T_discapacidades.show();
		}else if(valida_causa_discapacidades()==0){
			Toast T_causa_discapacidades=Toast.makeText(this, "Marque al menos una opción de las causas de discapacidades", Toast.LENGTH_LONG);
			T_causa_discapacidades.setGravity(Gravity.CENTER, 0, 0);
			T_causa_discapacidades.show();
		}else if(valida_actividades_requiere_asistencia()==0){
			Toast T_actividades_requiere_asistencia=Toast.makeText(this, "Marque al menos una opción de las actividades que requiere asistencia", Toast.LENGTH_LONG);
			T_actividades_requiere_asistencia.setGravity(Gravity.CENTER, 0, 0);
			T_actividades_requiere_asistencia.show();
		}else if(valida_padece_enfermedades_cronicas()==0){
			Toast T_enfermedades_cronicas=Toast.makeText(this, "Marque al menos una opción de las enfermedades crónicas", Toast.LENGTH_LONG);
			T_enfermedades_cronicas.setGravity(Gravity.CENTER, 0, 0);
			T_enfermedades_cronicas.show();
		}else if(id_MedicamentosEnfCronica.equals("99")){
			SpnMedicamentosEnfCronica.requestFocusFromTouch();
			Toast T_MedicamentosEnfCronica=Toast.makeText(this, "Seleccione si en la opción medicamentos", Toast.LENGTH_LONG);
			T_MedicamentosEnfCronica.setGravity(Gravity.CENTER, 0, 0);
			T_MedicamentosEnfCronica.show();
		}else{
			/*GuardarEditarSalud GuardarEditar = new GuardarEditarSalud(this);
			GuardarEditar.execute();*/
			EG(); 
			
			 if(action.equals("New")){
				 ToastExito("Se ha guardado la información");			 
				 Intent i = new Intent(contexto, Integrantes.class);
				 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
				 i.putExtra("idintegrante",idintegrante);
				 
				
				// classIntegrante.ActivityIntegrantes.finish();
				// finish();
				 startActivity(i);
			 }
			 if (action.equals("Edit")){
				ToastExito("Se ha editado la información");
				 Intent i = new Intent(this, Integrantes.class);
				 i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
				 i.putExtra("idintegrante",idintegrante);
				// classIntegrante.ActivityIntegrantes.finish();
			//	finish();
				 //ToastExito("idfamilia "+idfamilia+" idintegrante "+idintegrante);
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
		toast.setDuration(Toast.LENGTH_SHORT); //duracion del toast
		toast.setView(layout); //asignamos nuestro layout personalizado al objeto Toast
		toast.show(); //mostramos el Toast en pantalla
		}
	
	
public class RSAsyncSalud extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para los spinner
		 
		//posiciones en las listas
		//para los spinner
		
		public RSAsyncSalud(Context contexto){	
			this.contexto = contexto;
			}
		@Override
		protected String doInBackground(String... params) {
			/*if (action.equals("Edit")){
				CargarCheckBox();	
	    	}*/
			CargarCheckBox();
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
public class GuardarEditarSalud extends AsyncTask<String, Void, String> {  
	Context contexto;
    protected String doInBackground(String... params) {
		    		/*if(action.equals("New")){
		    			guardarSalud();
		    		}else{
		    			actualizarSalud();
		    		}*/
    	EG();
    		return null;
    	}
    public GuardarEditarSalud(Context contexto){	
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
	public void guardarSalud(){
		String fechaactual=objGestionDB.fechaActual();
		
		//guardo tipo de discapacidad
    	getCheckTipoDiscapacidad();
    	String TipoDiscapacidad=intelectual+sensorial+fisica+mentalpsiquiatrica+organica+sindiscapacidad;
    	TipoDiscapacidad=TipoDiscapacidad.substring(1,TipoDiscapacidad.length());
    	//objGestionDB.insertIntegranteVariable(86,fechaactual,TipoDiscapacidad, idintegrante, correlativo_tablet, id_estasib_user_sp); 
    	
    	//guardo causa de discapacidad
    	getCheckCausaDiscapacidad();
    	String CausaDiscapacidad=congenita+acciedente+porminas+trosartefactos+secuelaenfermedad+noaplica;
    	CausaDiscapacidad=CausaDiscapacidad.substring(1,CausaDiscapacidad.length());
    	//objGestionDB.insertIntegranteVariable(85,fechaactual,CausaDiscapacidad, idintegrante, correlativo_tablet, id_estasib_user_sp);
    	
    	//guardo actividades que requieren asistencia
    	getCheckActividadesRequiereAsistencia();
    	String ActividadesRequiereAsistencia=aseopersonal+necesidadesfisiologicas+caminarcorrer+asistirconsulta+ninguna+otrasactividades;
    	ActividadesRequiereAsistencia=ActividadesRequiereAsistencia.substring(1,ActividadesRequiereAsistencia.length());
    	//objGestionDB.insertIntegranteVariable(113,fechaactual,ActividadesRequiereAsistencia, idintegrante, correlativo_tablet, id_estasib_user_sp);
    	
    	//guardo enfermedades cronicas
    	getCheckEnfermedadesCronicas();
    	String EnfermedadesCronicas=nopresenta+hipertencionarterial+diabetesmellitus+respiratoriasEPOC+enfermedadrenalcronica+epilepsia+asmabronquial+alguncancer+accidentecerebrovascular+otracronica;
    	EnfermedadesCronicas=EnfermedadesCronicas.substring(1,EnfermedadesCronicas.length());
    	objGestionDB.insertIntegranteVariable(81,fechaactual,EnfermedadesCronicas, idintegrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
    	
    	
    	//guardo medicamentos para enfermedad cronica
    	//objGestionDB.insertIntegranteVariable(10, fechaactual, id_MedicamentosEnfCronica, idintegrante, correlativo_tablet, id_estasib_user_sp);    	
		}
	public void actualizarSalud(){
		//guardo tipo de discapacidad
    	getCheckTipoDiscapacidad();
    	String TipoDiscapacidad=intelectual+sensorial+fisica+mentalpsiquiatrica+organica+sindiscapacidad;
    	TipoDiscapacidad=TipoDiscapacidad.substring(1,TipoDiscapacidad.length());
    	//objGestionDB.updateIntegranteVariable(idintegrante, 86, TipoDiscapacidad);
    	
    	
    	//guardo causa de discapacidad
    	getCheckCausaDiscapacidad();
    	String CausaDiscapacidad=congenita+acciedente+porminas+trosartefactos+secuelaenfermedad+noaplica;
    	CausaDiscapacidad=CausaDiscapacidad.substring(1,CausaDiscapacidad.length());
    	//objGestionDB.updateIntegranteVariable(idintegrante, 85, CausaDiscapacidad);
    	
    	
    	
    	//guardo actividades que requieren asistencia
    	getCheckActividadesRequiereAsistencia();
    	String ActividadesRequiereAsistencia=aseopersonal+necesidadesfisiologicas+caminarcorrer+asistirconsulta+ninguna+otrasactividades;
    	ActividadesRequiereAsistencia=ActividadesRequiereAsistencia.substring(1,ActividadesRequiereAsistencia.length());
    	//objGestionDB.updateIntegranteVariable(idintegrante, 113, ActividadesRequiereAsistencia);
    	
    	
    	//guardo enfermedades cronicas
    	getCheckEnfermedadesCronicas();
    	String EnfermedadesCronicas=nopresenta+hipertencionarterial+diabetesmellitus+respiratoriasEPOC+enfermedadrenalcronica+epilepsia+asmabronquial+alguncancer+accidentecerebrovascular+otracronica;
    	EnfermedadesCronicas=EnfermedadesCronicas.substring(1,EnfermedadesCronicas.length());
    	//objGestionDB.updateIntegranteVariable(idintegrante, 81, EnfermedadesCronicas);
    	
    	//guardo medicamentos para enfermedad cronica
    	//objGestionDB.updateIntegranteVariable(idintegrante, 10, id_MedicamentosEnfCronica);
    	
	} 
	
	
	
}
