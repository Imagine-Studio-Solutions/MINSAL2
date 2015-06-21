package com.fichafamiliar;

import java.io.IOException;
import java.util.List;

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

public class ServiciosBasicos extends Activity {
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
	public String	Alumbrado,
	AbastecimientoAgua,
	AguaConsumoHumano,
	TieneSanitario,
	TipoSanitario,
	ColaboradorHogar;
	String numerosMedioComunicacion;
	//para los spinner
	String 		id_Alumbrado="",
				id_AbastecimientoAgua="",
				id_AguaConsumoHumano="",
				id_TieneSanitario="",
				id_TipoSanitario="",
				id_ColaboradorHogar="";
	//para los checkbox
	String 		TelFijo="",
				TelCelular="",
				Internet="",
				Cable="",
				NoTiene="";
	
	CheckBox 	CheckBoxTelFijo,
				CheckBoxTelCelular,
				CheckBoxInternet,
				CheckBoxCable,
				CheckBoxNoTiene;
	
	Spinner 	SpnAlumbrado,
				SpnAbastecimientoAgua,
				SpnAguaConsumoHumano,
				SpnTieneSanitario,
				SpnTipoSanitario,
				SpnColaboradorHogar;
	
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		setContentView(R.layout.activity_servicios_basicos);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New" 
		
		CheckBoxTelFijo 		= (CheckBox) findViewById(R.id.Checktelefono_fijo);
		CheckBoxTelCelular 		= (CheckBox) findViewById(R.id.Checktelefono_celular);
		CheckBoxInternet 		= (CheckBox) findViewById(R.id.Checkinternet);
		CheckBoxCable 			= (CheckBox) findViewById(R.id.Checkcable);
		CheckBoxNoTiene 		= (CheckBox) findViewById(R.id.Checkno_tiene);	
		
		SpnAlumbrado 			= (Spinner) findViewById(R.id.SpnAlumbradoPrincipalmente);
		SpnAbastecimientoAgua 	= (Spinner) findViewById(R.id.SpnAbastecimiento_agua);
		SpnAguaConsumoHumano 	= (Spinner) findViewById(R.id.SpnTratamiento_agua_consumo_humano);
		SpnTieneSanitario 		= (Spinner) findViewById(R.id.Spntiene_servicio_sanitario);
		SpnTipoSanitario 		= (Spinner) findViewById(R.id.Spntipo_servicio_sanitario);
		SpnColaboradorHogar 	= (Spinner) findViewById(R.id.SpnColaborador_hogar);
		
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
		
		RSAsyncServiciosBasicos leerAsync = new RSAsyncServiciosBasicos(this);
		leerAsync.execute();
 

		
		SpnAlumbrado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Alumbrado = (((SpinnerObjectString)SpnAlumbrado.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnAbastecimientoAgua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_AbastecimientoAgua = (((SpinnerObjectString)SpnAbastecimientoAgua.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnAguaConsumoHumano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_AguaConsumoHumano = (((SpinnerObjectString)SpnAguaConsumoHumano.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnTieneSanitario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_TieneSanitario = (((SpinnerObjectString)SpnTieneSanitario.getSelectedItem()).getCodigo());
				if(id_TieneSanitario.equals("3")){
					 
					SpnTipoSanitario.setSelection(6);
					//falta desactivar el spinner del tipo de sanitario
				}else{
					if(id_TipoSanitario.equals("NA")){
						SpnTipoSanitario.setSelection(0); 
					}
				}
			}	
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnTipoSanitario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_TipoSanitario = (((SpinnerObjectString)SpnTipoSanitario.getSelectedItem()).getCodigo());
				if(id_TipoSanitario.equals("NA")){
					SpnTieneSanitario.setSelection(1);
				}else{
					if(id_TieneSanitario.equals("3")){
						SpnTieneSanitario.setSelection(0); 
					}
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnColaboradorHogar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ColaboradorHogar = (((SpinnerObjectString)SpnColaboradorHogar.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
public int	valida_medio_comunicacion(){
		if(CheckBoxTelFijo.isChecked() || CheckBoxTelCelular.isChecked() || CheckBoxInternet.isChecked() || CheckBoxCable.isChecked() || CheckBoxNoTiene.isChecked()){
			return 1;	
		}else{
			return 0;
		}
	}
public void click_TelFijo(View view) {
	if(CheckBoxTelFijo.isChecked()){
		CheckBoxNoTiene.setChecked(false);
	}  
}
public void clic_TelCelular(View view) {
	if(CheckBoxTelCelular.isChecked()){
		CheckBoxNoTiene.setChecked(false);
	} 
}
public void click_Internet(View view) {
	if(CheckBoxInternet.isChecked()){
		CheckBoxNoTiene.setChecked(false);
	}
}
public void click_Cable(View view) {
	if(CheckBoxCable.isChecked()){
		CheckBoxNoTiene.setChecked(false);
	}
}
public void click_NoTiene(View view) {
	if(CheckBoxNoTiene.isChecked()){
		CheckBoxTelFijo.setChecked(false);
		CheckBoxTelCelular.setChecked(false);
		CheckBoxInternet.setChecked(false);
		CheckBoxCable.setChecked(false);
	}
}
public void click_servicios_basicos(View view) {

	 if(id_Alumbrado.equals("99")){
		SpnAlumbrado.requestFocusFromTouch();
		Toast T_alumbrado=Toast.makeText(this, "Seleccione el alumbrado utilizado principalmente", Toast.LENGTH_LONG);
		T_alumbrado.setGravity(Gravity.CENTER, 0, 0);
		T_alumbrado.show();
	}else if(valida_medio_comunicacion()==0){
		Toast T_medio_comunicacion=Toast.makeText(this, "Marque al menos una opción para los medios de comunicación", Toast.LENGTH_LONG);
		T_medio_comunicacion.setGravity(Gravity.CENTER, 0, 0);
		T_medio_comunicacion.show();
	}else if(id_AbastecimientoAgua.equals("99")){
		SpnAbastecimientoAgua.requestFocusFromTouch();
		Toast T_abastecimiento=Toast.makeText(this, "Seleccione el abastecimiento de agua", Toast.LENGTH_LONG);
		T_abastecimiento.setGravity(Gravity.CENTER, 0, 0);
		T_abastecimiento.show();
	}else if(id_AguaConsumoHumano.equals("99")){
		SpnAguaConsumoHumano.requestFocusFromTouch();
		Toast T_agua_consumo_humano=Toast.makeText(this, "Seleccione el tratamiento de agua para consumo humano", Toast.LENGTH_LONG);
		T_agua_consumo_humano.setGravity(Gravity.CENTER, 0, 0);
		T_agua_consumo_humano.show();
	}else if(id_TieneSanitario.equals("99")){
		SpnTieneSanitario.requestFocusFromTouch();
		Toast T_tiene_sanitario=Toast.makeText(this, "Seleccione si tiene sanitario", Toast.LENGTH_LONG);
		T_tiene_sanitario.setGravity(Gravity.CENTER, 0, 0);
		T_tiene_sanitario.show();
	}else if(id_TipoSanitario.equals("99")){
		SpnTipoSanitario.requestFocusFromTouch();
		Toast T_tipo_sanitario=Toast.makeText(this, "Seleccione el tipo de sanitario", Toast.LENGTH_LONG);
		T_tipo_sanitario.setGravity(Gravity.CENTER, 0, 0);
		T_tipo_sanitario.show();
	}else if(id_ColaboradorHogar.equals("99")){
		SpnColaboradorHogar.requestFocusFromTouch();
		Toast T_colaborador=Toast.makeText(this, "Seleccione si tiene colaborador para servicios del hogar", Toast.LENGTH_LONG);
		T_colaborador.setGravity(Gravity.CENTER, 0, 0);
		T_colaborador.show();
	}else{
		GuardarEditarServiciosBasicos GuardarEditar = new GuardarEditarServiciosBasicos(this);
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
			 i.putExtra("busquedaPor",3);		//porque ha hecho el edit busquedaPor=3
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
	
	public class RSAsyncServiciosBasicos extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos
		//para guardar el valor del spn que trae de la base de datos
		
		//posiciones en las listas
		public int	Alumbrado_pos,
					AbastecimientoAgua_pos,
					AguaConsumoHumano_pos,
					TieneSanitario_pos,
					TipoSanitario_pos,
					ColaboradorHogar_pos;
		ArrayAdapter<SpinnerObjectString> dataAdapterAlumbrado;
		ArrayAdapter<SpinnerObjectString> dataAdapterAbastecimientoAgua;
		ArrayAdapter<SpinnerObjectString> dataAdapterAguaConsumoHumano;
		ArrayAdapter<SpinnerObjectString> dataAdapterTieneSanitario;
		ArrayAdapter<SpinnerObjectString> dataAdapterTipoSanitario;
		ArrayAdapter<SpinnerObjectString> dataAdapterColaboradorHogar;
		public RSAsyncServiciosBasicos(Context contexto){	
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
    	
    		dataAdapterAlumbrado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnAlumbrado.setAdapter(dataAdapterAlumbrado);
    		SpnAlumbrado.setSelection(this.Alumbrado_pos);
    		
    		dataAdapterAbastecimientoAgua.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnAbastecimientoAgua.setAdapter(dataAdapterAbastecimientoAgua);
    		SpnAbastecimientoAgua.setSelection(this.AbastecimientoAgua_pos);
    		
    		dataAdapterAguaConsumoHumano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnAguaConsumoHumano.setAdapter(dataAdapterAguaConsumoHumano);
    		SpnAguaConsumoHumano.setSelection(this.AguaConsumoHumano_pos);
    		
    		dataAdapterTieneSanitario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnTieneSanitario.setAdapter(dataAdapterTieneSanitario);
    		SpnTieneSanitario.setSelection(this.TieneSanitario_pos);
    		
    		dataAdapterTipoSanitario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnTipoSanitario.setAdapter(dataAdapterTipoSanitario);
    		SpnTipoSanitario.setSelection(this.TipoSanitario_pos);
    		
    		dataAdapterColaboradorHogar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		SpnColaboradorHogar.setAdapter(dataAdapterColaboradorHogar);
    		SpnColaboradorHogar.setSelection(this.ColaboradorHogar_pos);
    		
    		
    		progressDialog.dismiss();
		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }
		public void CargarSpinner() {//descriptor
			List<SpinnerObjectString> lablesAlumbrado = objGestionDB.getCatalogoDescriptor(63,this.contexto);
			List<SpinnerObjectString> lablesAbastecimientoAgua = objGestionDB.getCatalogoDescriptor(65,this.contexto);
			List<SpinnerObjectString> lablesAguaConsumoHumano = objGestionDB.getCatalogoDescriptor(46,this.contexto);
			List<SpinnerObjectString> lablesTieneSanitario = objGestionDB.getCatalogoDescriptor(66,this.contexto);
			List<SpinnerObjectString> lablesTipoSanitario = objGestionDB.getCatalogoDescriptor(67,this.contexto);
			List<SpinnerObjectString> lablesColaboradorHogar = objGestionDB.getCatalogoDescriptor(30,this.contexto);
			
			dataAdapterAlumbrado = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesAlumbrado);
			dataAdapterAbastecimientoAgua = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesAbastecimientoAgua);
			dataAdapterAguaConsumoHumano = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesAguaConsumoHumano);
			dataAdapterTieneSanitario = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTieneSanitario);
			dataAdapterTipoSanitario = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTipoSanitario);
			dataAdapterColaboradorHogar = new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesColaboradorHogar);
		 
			//averiguar la posicion en la lista
		//	if(action.equals("Edit")){
				//recupero el valor que tienen las variable cuando se creo la ficha
				Alumbrado=objGestionDB.getValorVariableSelecionado(idfamilia,100,this.contexto);
				AbastecimientoAgua=objGestionDB.getValorVariableSelecionado(idfamilia,102,this.contexto);
				AguaConsumoHumano=objGestionDB.getValorVariableSelecionado(idfamilia,55,this.contexto);
				TieneSanitario=objGestionDB.getValorVariableSelecionado(idfamilia,103,this.contexto);
				TipoSanitario=objGestionDB.getValorVariableSelecionado(idfamilia,104,this.contexto);
				ColaboradorHogar=objGestionDB.getValorVariableSelecionado(idfamilia,105,this.contexto);
				
				for(int i=0; i<lablesAlumbrado.size(); i++){
					if(lablesAlumbrado.get(i).getCodigo().equals(Alumbrado)){
						Alumbrado_pos=i;
			        }       
		          }
				for(int i=0; i<lablesAbastecimientoAgua.size(); i++){
					if(lablesAbastecimientoAgua.get(i).getCodigo().equals(AbastecimientoAgua)){
						AbastecimientoAgua_pos=i;
			        }       
		          }
				for(int i=0; i<lablesAguaConsumoHumano.size(); i++){
					if(lablesAguaConsumoHumano.get(i).getCodigo().equals(AguaConsumoHumano)){
						AguaConsumoHumano_pos=i;
			        }       
		          }
				for(int i=0; i<lablesTieneSanitario.size(); i++){
					if(lablesTieneSanitario.get(i).getCodigo().equals(TieneSanitario)){
						TieneSanitario_pos=i;
			        }       
		          }
				for(int i=0; i<lablesTipoSanitario.size(); i++){
					if(lablesTipoSanitario.get(i).getCodigo().equals(TipoSanitario)){
						TipoSanitario_pos=i;
			        }       
		          }
				for(int i=0; i<lablesColaboradorHogar.size(); i++){
					if(lablesColaboradorHogar.get(i).getCodigo().equals(ColaboradorHogar)){
						ColaboradorHogar_pos=i;
			        }       
		          }
			//}	
		}
		//
		private void CargarCheckBox() {
			cargarCheckMedioComunicacion();
		}
		private void cargarCheckMedioComunicacion() {
			
		    numerosMedioComunicacion = objGestionDB.getValorVariableSelecionado(idfamilia,101,this.contexto);
		    String[] numerosComoArray = numerosMedioComunicacion.split(",");
		    for (int i = 0; i < numerosComoArray.length; i++) {
			     if(numerosComoArray[i].equals("1")){ CheckBoxTelFijo.setChecked(true);}
			     if(numerosComoArray[i].equals("2")){CheckBoxTelCelular.setChecked(true);}
			     if(numerosComoArray[i].equals("3")){CheckBoxInternet.setChecked(true);}
			     if(numerosComoArray[i].equals("4")){CheckBoxCable.setChecked(true);}
			     if(numerosComoArray[i].equals("5")){CheckBoxNoTiene.setChecked(true);}
		    	}
			}
	}
public class GuardarEditarServiciosBasicos extends AsyncTask<String, Void, String> {  
					Context contexto;
					 protected String doInBackground(String... params) {
					    		/*if(action.equals("New")){
					    			guardarServiciosBasicos();
					    		}else{
					    			actualizarServiciosBasicos();
					    		}*/
						 EG();
						return null;
							}
						public GuardarEditarServiciosBasicos(Context contexto){	
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
						public void guardarServiciosBasicos(){
					    	/*String fechaactual=objGestionDB.fechaActual();
					    	getCheckMedioComunicacion();
						    	
					    	String mediosComunicacion=TelFijo+TelCelular+Internet+Cable+NoTiene;
					    	mediosComunicacion=mediosComunicacion.substring(1, mediosComunicacion.length());
					    	*/
					    	//Log.i("medios",mediosComunicacion);
					    	//objGestionDB.insertFamiliaVariable(idfamilia, 100, fechaactual, id_Alumbrado,correlativo_tablet,id_estasib_user_sp);
					    	//objGestionDB.insertFamiliaVariable(idfamilia, 101, fechaactual, mediosComunicacion,correlativo_tablet,id_estasib_user_sp);
					    	//objGestionDB.insertFamiliaVariable(idfamilia, 102, fechaactual, id_AbastecimientoAgua,correlativo_tablet,id_estasib_user_sp);
					    	//objGestionDB.insertFamiliaVariable(idfamilia, 55,  fechaactual, id_AguaConsumoHumano,correlativo_tablet,id_estasib_user_sp);
					    	//objGestionDB.insertFamiliaVariable(idfamilia, 103, fechaactual, id_TieneSanitario,correlativo_tablet,id_estasib_user_sp);
					    	//objGestionDB.insertFamiliaVariable(idfamilia, 104, fechaactual, id_TipoSanitario,correlativo_tablet,id_estasib_user_sp);
					    	//objGestionDB.insertFamiliaVariable(idfamilia, 105, fechaactual, id_ColaboradorHogar,correlativo_tablet,id_estasib_user_sp);
					    }
						public void actualizarServiciosBasicos(){
							/*getCheckMedioComunicacion();
					    	
							String mediosComunicacion=TelFijo+TelCelular+Internet+Cable+NoTiene;
					    	mediosComunicacion=mediosComunicacion.substring(1, mediosComunicacion.length());
					    	*/
					    	//objGestionDB.updateFamiliaVariable(idfamilia, 100, id_Alumbrado);
							//objGestionDB.updateFamiliaVariable(idfamilia, 101, mediosComunicacion);
							//objGestionDB.updateFamiliaVariable(idfamilia, 102, id_AbastecimientoAgua);
							//objGestionDB.updateFamiliaVariable(idfamilia, 55, id_AguaConsumoHumano);
							//objGestionDB.updateFamiliaVariable(idfamilia, 103, id_TieneSanitario);
							//objGestionDB.updateFamiliaVariable(idfamilia, 104, id_TipoSanitario);
							//objGestionDB.updateFamiliaVariable(idfamilia, 105, id_ColaboradorHogar);
					    }
						public void EG(){
							String fechaactual=objGestionDB.fechaActual();
							getCheckMedioComunicacion();
					    	
							String mediosComunicacion=TelFijo+TelCelular+Internet+Cable+NoTiene;
					    	mediosComunicacion=mediosComunicacion.substring(1, mediosComunicacion.length());
							if(Alumbrado.equals("") || Alumbrado==null){
								//insert
								objGestionDB.insertFamiliaVariable(idfamilia, 100, fechaactual, id_Alumbrado,correlativo_tablet,id_estasib_user_sp,this.contexto);
							}else{
								//update
								objGestionDB.updateFamiliaVariable(idfamilia, 100, id_Alumbrado,this.contexto);
							}
							if(AbastecimientoAgua.equals("") || AbastecimientoAgua==null){
								//insert
								objGestionDB.insertFamiliaVariable(idfamilia, 102, fechaactual, id_AbastecimientoAgua,correlativo_tablet,id_estasib_user_sp,this.contexto);
						    	
							}else{
								//update
								objGestionDB.updateFamiliaVariable(idfamilia, 102, id_AbastecimientoAgua,this.contexto);
							}
							if(AguaConsumoHumano.equals("") || AguaConsumoHumano==null){
								//insert
								objGestionDB.insertFamiliaVariable(idfamilia, 55,  fechaactual, id_AguaConsumoHumano,correlativo_tablet,id_estasib_user_sp,this.contexto);
						    	
							}else{
								//update
								objGestionDB.updateFamiliaVariable(idfamilia, 55, id_AguaConsumoHumano,this.contexto);
							}
							if(TieneSanitario.equals("") || TieneSanitario==null){
								//insert
								objGestionDB.insertFamiliaVariable(idfamilia, 103, fechaactual, id_TieneSanitario,correlativo_tablet,id_estasib_user_sp,this.contexto);
							}else{
								//update
								objGestionDB.updateFamiliaVariable(idfamilia, 103, id_TieneSanitario,this.contexto);
							}
							if(TipoSanitario.equals("") || TipoSanitario==null){
								//insert
								objGestionDB.insertFamiliaVariable(idfamilia, 104, fechaactual, id_TipoSanitario,correlativo_tablet,id_estasib_user_sp,this.contexto);
						    	
							}else{
								//update
								objGestionDB.updateFamiliaVariable(idfamilia, 104, id_TipoSanitario,this.contexto);
							}
							if(ColaboradorHogar.equals("") || ColaboradorHogar==null){
								//insert
								objGestionDB.insertFamiliaVariable(idfamilia, 105, fechaactual, id_ColaboradorHogar,correlativo_tablet,id_estasib_user_sp,this.contexto);
							}else{
								//update
								objGestionDB.updateFamiliaVariable(idfamilia, 105, id_ColaboradorHogar,this.contexto);
							}if(numerosMedioComunicacion.equals("") || numerosMedioComunicacion==null){
								//insert
								objGestionDB.insertFamiliaVariable(idfamilia, 101, fechaactual, mediosComunicacion,correlativo_tablet,id_estasib_user_sp,this.contexto);
						    	
							}else{
								//update
								objGestionDB.updateFamiliaVariable(idfamilia, 101, mediosComunicacion,this.contexto);
							}
						}
						
						
						private void getCheckMedioComunicacion() {
							
							if(CheckBoxTelFijo.isChecked()){TelFijo=",1";}else{TelFijo="";}
							if(CheckBoxTelCelular.isChecked()){TelCelular=",2";}else{TelCelular="";}
							if(CheckBoxInternet.isChecked()){Internet=",3";}else{Internet="";}
							if(CheckBoxCable.isChecked()){Cable=",4";}else{Cable="";}
							if(CheckBoxNoTiene.isChecked()){NoTiene=",5";}else{NoTiene="";} 
						}
	}	

}
