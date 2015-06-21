package com.fichafamiliar;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

//import com.fichafamiliar.Crear_Usuario.GuardarEditarUsuario;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

	//public class Add_new_miembro_familia extends Activity implements OnClickListener {
	public class Add_new_miembro_familia extends Activity implements OnClickListener{
	private Integrantes classIntegrante= new Integrantes();
		
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";	
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB; // New GestionDB(this);//creo el objeto de lLa clase que gestiona la DB
	private ProgressDialog progressDialog;
	
	public String S_segundo_nombre;
	public String S_tercer_nombre;
	public String S_segundo_apellido;
	public String S_apellido_casada;
	
	public int id_familia;
	String action;
	public int id_integrante;
	private int id_estasib_user_sp;
	private int correlativo_tablet;
	private int id_sibasi;
	private int id_usuario_tablet;
	String nombreusuario;
	
	
	public int diffYears=0;
	
	String Parentesko;
	String Insti_coti_bene;
	String estadfami;
	String asegben;
	
	private int day;
	private int month;
	private int year;
		
	int 		id_Nacionalidad;	
	public String		id_tienedocumento, id_estado_familiar, id_asegurado_beneficiario, id_Parentesto,id_Institucion_cotizante_beneficiario,id_Sabe_leer_escribir,
				id_Ultimo_grado_estudio, id_Tiene_trabajo_remunerado,
				id_Tipo_discapacidad, id_Causa_discapacidad, id_Toma_medicamentos,
				id_Estado_nutricional, id_Fuma,
				id_Frecuencia_consumo_bebida_embriagantes,
				id_Cantidad_bebida_embriagante_dia, id_Citologia_ultimo_anio,
				id_Examen_manual_mamas, id_Embarazada_actualmente,
				id_Ninos_menores_1_anio_condiciones,
				id_Utiliza_metodo_planificacion_familiar, id_Menor_14_trabaja,
				id_Menores_18_bajo_cuidado_de,id_Esquema_vacunacion,id_Sexo, 
				S_correlativo, S_primer_nombre, S_primer_apellido, S_numero_dui,
				bd_tiene_dui,
				id_;
	
	private Calendar cal;
	EditText 	correlativo, primer_nombre,segundo_nombre,tercer_nombre,
				primer_apellido,segundo_apellido,apellido_casada,
				numero_dui, fecha_nacimiento, nombre_madre, nombre_padre, nombre_responsable,
				edad_anios,edad_meses,edad_dias; 
	
	TextView  	edad_calculada;
	
	Spinner 	SpnTieneDocumento, SpnNacionalidad, SpnSexo,  SpnParentesto,
				SpnEstadoFamiliar, SpnAseguradoBeneficiario, SpnInstitucion_cotizante_beneficiario;
				
	
	
	
	public ImageButton ibutton_fecha_nacimiento, imgbuttonnewmiembro;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_miembro_familia);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		
		//BaseDeDatos db2 = new BaseDeDatos(this);
		//try {
			//db2.createDataBase();
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la clase que gestiona la DB
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		Bundle bundle = getIntent().getExtras();
		
		id_familia= bundle.getInt("idfamilia");//viene de la activity anterior
		action=bundle.getString("action");
		
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		
		 id_usuario_tablet = preferencias.getInt("id_sp", 0);// id usuario tablet
		 id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		 nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		 id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		 correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 
		//primer_nombre = (EditText) findViewById(R.id.etprimernombre);
		//primer_nombre.setText(String.valueOf(id_familia)+" "+action);	
		
								
		
		id_asegurado_beneficiario="";
		id_Sexo="";
		//EditText
		correlativo = (EditText) findViewById(R.id.etcorrelativo);
		//------------------------------------------------------------------------------------------------------------
		if(action.equals("New"))
		{
			correlativo.setText(String.valueOf(ObtenerCorrelativo(id_familia)));
			correlativo.setFocusable(false);
		}
		//----------------------------------------------------------------------------------------------------------------
		//correlativo.setText(String.valueOf(ObtenerCorrelativo()));
		
		
		primer_nombre = (EditText) findViewById(R.id.etprimernombre);
		segundo_nombre = (EditText) findViewById(R.id.etsegundonombre);
		tercer_nombre = (EditText) findViewById(R.id.ettercernombre);
		primer_apellido = (EditText) findViewById(R.id.etprimerapellido);
		segundo_apellido = (EditText) findViewById(R.id.etsegundoapellido);
		apellido_casada = (EditText) findViewById(R.id.etapellidocasada);
		numero_dui = (EditText) findViewById(R.id.etnumerodui);
		
		
		
		
		
		ibutton_fecha_nacimiento = (ImageButton) findViewById(R.id.imageFechaNac);
		fecha_nacimiento = (EditText) findViewById(R.id.txt_fecha_nacimiento);
		ibutton_fecha_nacimiento.setOnClickListener(this);
		fecha_nacimiento.setFocusable(false);
	  
		
		nombre_madre = (EditText) findViewById(R.id.etnombremadre);
		nombre_padre = (EditText) findViewById(R.id.etnombrepadre);
		nombre_responsable = (EditText) findViewById(R.id.etnombre_responsable);
		ibutton_fecha_nacimiento = (ImageButton) findViewById(R.id.imageFechaNac);
		//TextView
		edad_calculada=(TextView) findViewById(R.id.tvedadcalculada);								
		
		//Spinner
		SpnTieneDocumento = (Spinner) findViewById(R.id.SpnTieneDocumento);
		SpnNacionalidad = (Spinner) findViewById(R.id.SpnNacionalidad);
		SpnSexo = (Spinner) findViewById(R.id.SpnSexo);		
		SpnParentesto = (Spinner) findViewById(R.id.Spnparentesto);
		
		SpnEstadoFamiliar= (Spinner) findViewById(R.id.SpnEstadoFamiliar);
		SpnAseguradoBeneficiario= (Spinner) findViewById(R.id.SpnAseguradoBeneficiario);
		SpnInstitucion_cotizante_beneficiario = (Spinner) findViewById(R.id.SpnInstitucion_cotizante_beneficiario);
		
		imgbuttonnewmiembro = (ImageButton) findViewById(R.id.imgbuttonnewmiembro);
		
		
		if(action.equals("Edit")||action.equals("Delete"))
		{
			this.id_integrante=bundle.getInt("idmiembrofam");
			//primer_nombre.setText(String.valueOf(id_integrante));
			
			if(action.equals("Edit"))
			{
				imgbuttonnewmiembro.setImageResource(R.drawable.edit);
				//correlativo.setFocusable(true);
			}			
			else
			{
			
				correlativo.setFocusable(false);
				primer_nombre.setEnabled(false);
				segundo_nombre.setEnabled(false);
				tercer_nombre.setEnabled(false);
				primer_apellido.setEnabled(false);
				segundo_apellido.setEnabled(false);
				apellido_casada.setEnabled(false);
				SpnTieneDocumento.setEnabled(false);
				numero_dui.setEnabled(false);
				SpnNacionalidad.setEnabled(false);
				SpnSexo.setEnabled(false);
				ibutton_fecha_nacimiento.setEnabled(false);
				SpnParentesto.setEnabled(false);
				nombre_madre.setEnabled(false);
				nombre_padre.setEnabled(false);
				nombre_responsable.setEnabled(false);
				SpnEstadoFamiliar.setEnabled(false);
				SpnAseguradoBeneficiario.setEnabled(false);
				SpnInstitucion_cotizante_beneficiario.setEnabled(false);
				
				imgbuttonnewmiembro.setImageResource(R.drawable.delete);
			}
		
			objGestionDB.getIntegranteInfoEdit(id_integrante, this.contexto);
			
			
			correlativo.setText(objGestionDB.numerocorrelativo);
			primer_nombre.setText(objGestionDB.primernombre);
			segundo_nombre.setText(objGestionDB.segundonombre);
			tercer_nombre.setText(objGestionDB.tercernombre);
			primer_apellido.setText(objGestionDB.primerapellido);
			segundo_apellido.setText(objGestionDB.segundoapellido);
			apellido_casada.setText(objGestionDB.apellidocasada);
			numero_dui.setText(objGestionDB.dui);
			//segundo_apellido.setText(objGestionDB.dui);
			fecha_nacimiento.setText(objGestionDB.fechanacimiento);
			String [] separadas = objGestionDB.fechanacimiento.split("-");
			year=Integer.parseInt(separadas[0]);
			month=Integer.parseInt(separadas[1]);
			month=month-1;
			day=Integer.parseInt(separadas[2]);
			diffYears=calcularEdad(year, month, day);
			//int dia=day+1;
			//edad_calculada.setText(objGestionDB.fechanacimiento);
			edad_calculada.setText(calcularEdad(year, month, day) + " años");
			nombre_madre.setText(objGestionDB.nombre_madre);
			nombre_padre.setText(objGestionDB.nombre_padre);
			nombre_responsable.setText(objGestionDB.nombre_responsable);
			bd_tiene_dui=objGestionDB.tienedocumento;
			
			
		}
		
		loadSpinnerDataSpnNacionalidad();
		loadSpinnerDataSpnSexo();		
		loadCatalogoDescriptor(SpnParentesto,1); 
		if(action.equals("New"))
		{
			//if(ObtenerCorrelativo(id_familia)==1)
			if(ObtenerCorrelativo(id_familia).equals("01"))
			{
				SpnParentesto.setClickable(false);
				
				SpnParentesto.setSelection(5);
				
			}
		}
		/*
		else
		{
			if(objGestionDB.numerocorrelativo.equals("1"))
			{
				SpnParentesto.setClickable(false);
				
			}
			
		}*/
		loadCatalogoDescriptor(SpnInstitucion_cotizante_beneficiario,2);
		loadCatalogoDescriptor(SpnEstadoFamiliar,72);
		loadCatalogoDescriptor(SpnAseguradoBeneficiario, 73);
		loadCatalogoDescriptor(SpnTieneDocumento, 30);
		
		SpnSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Sexo = (((SpinnerObjectString)SpnSexo.getSelectedItem()).getCodigo());
				
				if(id_Sexo.equals("M") ||id_Sexo.equals("I") ){
					apellido_casada.setText("");
					apellido_casada.setFocusable(false);
					
				}else{
					if(apellido_casada.getText().toString().equals(""))
					{
					apellido_casada.setText("");
					}
					
					apellido_casada.setFocusableInTouchMode(true);
						
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		SpnNacionalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Nacionalidad = (((SpinnerObject)SpnNacionalidad.getSelectedItem()).getId());
				
				if(id_Nacionalidad==0||id_Nacionalidad==1)
				{
					
					//if((diffYears!=0)&&(diffYears>=18))
					//{
						SpnTieneDocumento.setClickable(true);
						
						if(id_tienedocumento.equals("t"))
						{						
							numero_dui.setFocusableInTouchMode(true);
						}
						else
						{
							numero_dui.setFocusableInTouchMode(false);
							
							
							numero_dui.setText("");
						}
					//}
					
					/*if(diffYears<18)
					{
						SpnTieneDocumento.setClickable(true);
						
						numero_dui.setFocusableInTouchMode(false);
						numero_dui.setText("");
					}*/
					
					
				}
				else
				{
					SpnTieneDocumento.setSelection(1);
					SpnTieneDocumento.setClickable(false);
					numero_dui.setFocusable(false);
					
					
					numero_dui.setText("");
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		SpnParentesto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Parentesto = (((SpinnerObjectString)SpnParentesto.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnInstitucion_cotizante_beneficiario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Institucion_cotizante_beneficiario = (((SpinnerObjectString)SpnInstitucion_cotizante_beneficiario.getSelectedItem()).getCodigo());
				
				if(id_Institucion_cotizante_beneficiario.equals("0"))
				{
					SpnAseguradoBeneficiario.setSelection(3);
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnTieneDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
		{
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) 
			{
			    
				id_tienedocumento= (((SpinnerObjectString)SpnTieneDocumento.getSelectedItem()).getCodigo());
				
				if(id_tienedocumento.equals("0"))
				{
					id_tienedocumento="f";
				}
				
				if(id_tienedocumento.equals("1"))
				{
					id_tienedocumento="t";
				}
				
				if(id_tienedocumento.equals("f")){
					 
					numero_dui.setText("");
					numero_dui.setFocusable(false);
					//falta desactivar el spinner del tipo de sanitario
				}
				else
				{
					
					/*if(diffYears>=18)
					{
						numero_dui.setFocusableInTouchMode(true);
					}
					else
					{*/
						
						/*if(edad_calculada.getText().toString().equals("EDAD CALCULADA"))
						{
							numero_dui.setFocusableInTouchMode(true);
						}
						else
						{*/
							/*if(diffYears<=18)
							{
								numero_dui.setFocusableInTouchMode(false);
								numero_dui.setText("");
							}
							else
							{*/
								//numero_dui.setText("");
								numero_dui.setFocusableInTouchMode(true);
							//}
						//}
						
					//}
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnEstadoFamiliar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_estado_familiar= (((SpinnerObjectString)SpnEstadoFamiliar.getSelectedItem()).getCodigo());
				
				if((id_estado_familiar.equals("6"))||(id_estado_familiar.equals("1")))
				{
					
					if(id_Sexo.equals("F"))
					{
						//apellido_casada.setText(id_Sexo+"-"+id_estado_familiar);
						apellido_casada.setText("");
						apellido_casada.setFocusable(false);
						
					}
					else
					{
						apellido_casada.setFocusable(false);
						apellido_casada.setText("");
					}
				}
				else if((id_estado_familiar.equals("99")))
				{
					
					if(id_Sexo.equals("99"))
					{
						apellido_casada.setFocusableInTouchMode(true);
						
					}
					else if(id_Sexo.equals("F"))
					{
						apellido_casada.setFocusableInTouchMode(true);
						
					}
					else
					{
						apellido_casada.setFocusable(false);
						apellido_casada.setText("");
					}
				}
				else
				{
					if(id_Sexo.equals("F"))
					{
						apellido_casada.setFocusableInTouchMode(true);
					}
					else
					{
						apellido_casada.setFocusable(false);
						apellido_casada.setText("");
					}
					
				}
				
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnAseguradoBeneficiario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_asegurado_beneficiario= (((SpinnerObjectString)SpnAseguradoBeneficiario.getSelectedItem()).getCodigo());
				
				if(id_asegurado_beneficiario.equals("0"))					
				{
					
					SpnInstitucion_cotizante_beneficiario.setClickable(false);
					SpnInstitucion_cotizante_beneficiario.setSelection(4);
					
				}
				else if(id_asegurado_beneficiario.equals("99"))
				{
					SpnInstitucion_cotizante_beneficiario.setClickable(false);
					SpnInstitucion_cotizante_beneficiario.setSelection(0);
				}
				else 
				{
					SpnInstitucion_cotizante_beneficiario.setClickable(true);
					
				}	
					
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		 
		
	}
	public void toastCorto(String texto){
		Toast toast=Toast.makeText(this, "el tamaño es:"+texto.length(), Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	private void loadSpinnerDataSpnSexo(){
		List<SpinnerObjectString> lables = objGestionDB.getSexo(this.contexto);
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpnSexo.setAdapter(dataAdapter);
		
		if(action.equals("Edit")||action.equals("Delete")){
			int pos=0;
			for(int i=0; i<lables.size(); i++)
			{
				if(lables.get(i).getCodigo().equals(objGestionDB.sexo))
				{
					pos=i;
		        }       
	        }
			SpnSexo.setSelection(pos);
		} 
	}
	private void loadSpinnerDataSpnNacionalidad(){
		List<SpinnerObject> lables = objGestionDB.getNacionalidad(this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpnNacionalidad.setAdapter(dataAdapter);
		
		if(action.equals("New"))
		{
			SpnNacionalidad.setSelection(1);
		}
		
		if(action.equals("Edit")||action.equals("Delete")){
			int pos=0;
			for(int i=0; i<lables.size(); i++)
			{
				if(lables.get(i).getId()==objGestionDB.nacionalidad)
				{
					pos=i;
		        }       
	        }
			SpnNacionalidad.setSelection(pos);
		}
	}
	private void loadCatalogoDescriptor(Spinner spinnerCargar, int id_descriptor){
		List<SpinnerObjectString> lables;
		
		if(id_descriptor==1)
		{
			if(action.equals("New"))
			{
				
				if(ObtenerCorrelativo(id_familia).equals("01"))
				{
					lables = objGestionDB.getCatalogoDescriptor(id_descriptor, this.contexto);
				}
				else
				{
					lables = objGestionDB.getCatalogoDescriptorSinJefe(id_descriptor, this.contexto);
				}
			}
			else
			{
				lables = objGestionDB.getCatalogoDescriptor(id_descriptor, this.contexto);
			}
		}
		else
		{
			lables = objGestionDB.getCatalogoDescriptor(id_descriptor, this.contexto);
		}
		
		ArrayAdapter<SpinnerObjectString> dataAdapter = new ArrayAdapter<SpinnerObjectString>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCargar.setAdapter(dataAdapter);
		
		 Parentesko=objGestionDB.getValorIntegranteSelecionado(id_integrante,1, this.contexto);
		 Insti_coti_bene=objGestionDB.getValorIntegranteSelecionado(id_integrante,2, this.contexto);
		 estadfami=objGestionDB.getValorIntegranteSelecionado(id_integrante,109, this.contexto);
		 asegben=objGestionDB.getValorIntegranteSelecionado(id_integrante,110, this.contexto);
		
		//segundo_apellido.setText("tiene_dui="+bd_tiene_dui);
		
		if(action.equals("Edit")||action.equals("Delete")){
			int pos=0;http://credibleconstructioninc.com/crediable_sample.jpg
			
			if(id_descriptor==1)
			{
				for(int i=0; i<lables.size(); i++)
				{
					
					
						if(lables.get(i).getCodigo().equals(Parentesko))
						{
							pos=i;
				        }
					
					
				}
				spinnerCargar.setSelection(pos);
			}
			if(id_descriptor==2)
			{
				for(int i=0; i<lables.size(); i++)
				{
					
					
						if(lables.get(i).getCodigo().equals(Insti_coti_bene))
						{
							pos=i;
				        }
					
					
				}
				spinnerCargar.setSelection(pos);
			}
			if(id_descriptor==72)
			{
				for(int i=0; i<lables.size(); i++)
				{
					
					
						if(lables.get(i).getCodigo().equals(estadfami))
						{
							pos=i;
				        }
					
					
				}
				spinnerCargar.setSelection(pos);
			}
			if(id_descriptor==73)
			{
				for(int i=0; i<lables.size(); i++)
				{
					
					
						if(lables.get(i).getCodigo().equals(asegben))
						{
							pos=i;
				        }
					
					
				}
				spinnerCargar.setSelection(pos);
			}
			if(id_descriptor==30)//Tiene o no tiene documento
			{
				for(int i=0; i<lables.size();i++)
				{
					String valorcito;
					if(lables.get(i).getCodigo().equals("0"))
					{
						valorcito="f";
					}
					else
					{
						valorcito="t";
					}
					
					
					if(valorcito.equals(bd_tiene_dui))
					{
						pos=i;
					}
				}
				spinnerCargar.setSelection(pos);
			}															
			
			
		}
	}
	
	public void add_miembro_familia(View view) {
		 
	if(action.equals("New"))
	{
		S_correlativo = correlativo.getText().toString();
		S_primer_nombre = primer_nombre.getText().toString();
		S_primer_apellido = primer_apellido.getText().toString();
		S_numero_dui= numero_dui.getText().toString();
		
		if(S_primer_nombre.equals("")){
			primer_nombre.requestFocusFromTouch();
			Toast t_primer_nombre=Toast.makeText(this, "Digite el Primer Nombre", Toast.LENGTH_LONG);
			t_primer_nombre.setGravity(Gravity.CENTER, 0, 0);
			t_primer_nombre.show();
		}
		else
		{
			if(S_primer_apellido.equals(""))
			{
				primer_apellido.requestFocusFromTouch();
				Toast t_primer_apellido=Toast.makeText(this, "Digite el Primer Apellido", Toast.LENGTH_LONG);
				t_primer_apellido.setGravity(Gravity.CENTER, 0, 0);
				t_primer_apellido.show();
			}
			else
			{
				if((id_tienedocumento.equals("f"))||(id_tienedocumento.equals("t")))
				{
					if(id_Nacionalidad==0)
					{
						SpnNacionalidad.requestFocusFromTouch();
						Toast t_nacionalidad=Toast.makeText(this, "Seleccione la Nacionalidad", Toast.LENGTH_LONG);
						t_nacionalidad.setGravity(Gravity.CENTER, 0, 0);
						t_nacionalidad.show();
					}
					else
					{
						if(id_Sexo.equals("99"))
						{
							SpnSexo.requestFocusFromTouch();
							Toast t_sexo=Toast.makeText(this, "Seleccione el Sexo", Toast.LENGTH_LONG);
							t_sexo.setGravity(Gravity.CENTER, 0, 0);
							t_sexo.show();
						}
						else
						{
							if(fecha_nacimiento.getText().toString().equals(""))
							{
								ibutton_fecha_nacimiento.requestFocusFromTouch();
								Toast t_fecha_nacimiento=Toast.makeText(this, "Seleccione la Fecha de Nacimiento", Toast.LENGTH_LONG);
								t_fecha_nacimiento.setGravity(Gravity.CENTER, 0, 0);
								t_fecha_nacimiento.show();
							}
							else
							{
								if(diffYears>=0)
								{
									if(id_Parentesto.equals("99"))
									{
										SpnParentesto.requestFocusFromTouch();
										Toast t_Parentesto=Toast.makeText(this, "Seleccione el Parentesco con el Jefe de Familia", Toast.LENGTH_LONG);
										t_Parentesto.setGravity(Gravity.CENTER, 0, 0);
										t_Parentesto.show();
									}
									else
									{
										if(id_estado_familiar.equals("99"))
										{
											SpnEstadoFamiliar.requestFocusFromTouch();
											Toast t_EstadoFamiliar=Toast.makeText(this, "Seleccione el Estado Familiar", Toast.LENGTH_LONG);
											t_EstadoFamiliar.setGravity(Gravity.CENTER, 0, 0);
											t_EstadoFamiliar.show();
										}
										else
										{
											if(id_asegurado_beneficiario.equals("99"))
											{
												SpnAseguradoBeneficiario.requestFocusFromTouch();
												Toast t_AseguradoBeneficiario=Toast.makeText(this, "Seleccione Si la Persona esta Asegura o es Beneficiaria", Toast.LENGTH_LONG);
												t_AseguradoBeneficiario.setGravity(Gravity.CENTER, 0, 0);
												t_AseguradoBeneficiario.show();
											}
											else
											{
												if(id_Institucion_cotizante_beneficiario.equals("99"))
												{
													SpnInstitucion_cotizante_beneficiario.requestFocusFromTouch();
													Toast t_Institucion_cotizante_beneficiario=Toast.makeText(this, "Seleccione la Institución de la que es cotizante o beneficiario", Toast.LENGTH_LONG);
													t_Institucion_cotizante_beneficiario.setGravity(Gravity.CENTER, 0, 0);
													t_Institucion_cotizante_beneficiario.show();
												}
												else
												{
													/*if(((id_tienedocumento.equals("t"))&&(numero_dui.getText().toString().equals("")))&& diffYears>=18)
													{
														numero_dui.requestFocusFromTouch();
														Toast t_numero_dui=Toast.makeText(this, "Digite el número de DUI", Toast.LENGTH_LONG);
														t_numero_dui.setGravity(Gravity.CENTER, 0, 0);
														t_numero_dui.show();
													}
													else
													{
														if(((id_tienedocumento.equals("t"))&&(numero_dui.getText().toString().length()<9))&& diffYears>=18){
															numero_dui.requestFocusFromTouch();
															Toast t_numero_dui=Toast.makeText(this, "El número de DUI debe contener 9 digitos", Toast.LENGTH_LONG);
															t_numero_dui.setGravity(Gravity.CENTER, 0, 0);
															t_numero_dui.show();
														}else{*/
															
															  //tablet = Integer.parseInt(correlativo_tablet.getText().toString());
															  //instaciar la clase asincrona que guarda y edita
															  //correr el doInbackground de la clase asincrona
															
															  S_segundo_nombre=segundo_nombre.getText().toString(); 
															  S_tercer_nombre=tercer_nombre.getText().toString(); 
															  S_primer_apellido=primer_apellido.getText().toString(); 
															  S_segundo_apellido=segundo_apellido.getText().toString(); 
															  S_apellido_casada=apellido_casada.getText().toString(); 
															  
															
															
															
															
															
															
															  GuardarEditarIntegrante GuardarEditar = new GuardarEditarIntegrante(this);
															  GuardarEditar.execute();
															 
															 
															  ToastExito("Se ha guardado la información");
																 
															  Intent i = new Intent(contexto, Integrantes.class);
															  i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
															  //i.putExtra("busquedaPor",3);//pasar los datos que se necesitaran en la siguiente activity
															  id_integrante=objGestionDB.ultimoidIntegranteInt(this.contexto);
															  
															 // ToastExito(String.valueOf("el id integrante que se envia "+id_integrante));
															  i.putExtra("idintegrante",id_integrante);//pasar los datos que se necesitaran en la siguiente activity
															  
															  finish();
															  //classIntegrante.ActivityIntegrantes.finish();
															  startActivity(i);
														
														//}
													//}
												}
											}
										}
									}
								}
								else
								{
									ibutton_fecha_nacimiento.requestFocusFromTouch();
									Toast t_sexo=Toast.makeText(this, "La Fecha de Nacimiento seleccionada no es Valida", Toast.LENGTH_LONG);
									t_sexo.setGravity(Gravity.CENTER, 0, 0);
									t_sexo.show();
								}
							}
							
						}
						
					}																									
				}
				else
				{
					SpnTieneDocumento.requestFocusFromTouch();
					Toast t_tiene_documento=Toast.makeText(this, "Seleccione Si Tiene o No Documento", Toast.LENGTH_LONG);
					t_tiene_documento.setGravity(Gravity.CENTER, 0, 0);
					t_tiene_documento.show();
				}
			}
			
		}
	}
	else if(action.equals("Edit"))
	{
		S_correlativo = correlativo.getText().toString();
		S_primer_nombre = primer_nombre.getText().toString();
		S_primer_apellido = primer_apellido.getText().toString();
		S_numero_dui= numero_dui.getText().toString();
		
		if(S_correlativo.equals(""))
		{
			correlativo.requestFocusFromTouch();
			Toast t_correlativo=Toast.makeText(this, "Digite el Correlativo", Toast.LENGTH_LONG);
			t_correlativo.setGravity(Gravity.CENTER, 0, 0);
			t_correlativo.show();
		}
		else
		{	
			if(S_primer_nombre.equals("")){
				primer_nombre.requestFocusFromTouch();
				Toast t_primer_nombre=Toast.makeText(this, "Digite el Primer Nombre", Toast.LENGTH_LONG);
				t_primer_nombre.setGravity(Gravity.CENTER, 0, 0);
				t_primer_nombre.show();
			}
			else
			{
				if(S_primer_apellido.equals(""))
				{
					primer_apellido.requestFocusFromTouch();
					Toast t_primer_apellido=Toast.makeText(this, "Digite el Primer Apellido", Toast.LENGTH_LONG);
					t_primer_apellido.setGravity(Gravity.CENTER, 0, 0);
					t_primer_apellido.show();
				}
				else
				{
					if((id_tienedocumento.equals("f"))||(id_tienedocumento.equals("t")))
					{
						if(id_Nacionalidad==0)
						{
							SpnNacionalidad.requestFocusFromTouch();
							Toast t_nacionalidad=Toast.makeText(this, "Seleccione la Nacionalidad", Toast.LENGTH_LONG);
							t_nacionalidad.setGravity(Gravity.CENTER, 0, 0);
							t_nacionalidad.show();
						}
						else
						{
							if(id_Sexo.equals("99"))
							{
								SpnSexo.requestFocusFromTouch();
								Toast t_sexo=Toast.makeText(this, "Seleccione el Sexo", Toast.LENGTH_LONG);
								t_sexo.setGravity(Gravity.CENTER, 0, 0);
								t_sexo.show();
							}
							else
							{
								if(fecha_nacimiento.getText().toString().equals(""))
								{
									ibutton_fecha_nacimiento.requestFocusFromTouch();
									Toast t_fecha_nacimiento=Toast.makeText(this, "Seleccione la Fecha de Nacimiento", Toast.LENGTH_LONG);
									t_fecha_nacimiento.setGravity(Gravity.CENTER, 0, 0);
									t_fecha_nacimiento.show();
								}
								else
								{
									if(diffYears>=0)
									{
										if(id_Parentesto.equals("99"))
										{
											SpnParentesto.requestFocusFromTouch();
											Toast t_Parentesto=Toast.makeText(this, "Seleccione el Parentesco con el Jefe de Familia", Toast.LENGTH_LONG);
											t_Parentesto.setGravity(Gravity.CENTER, 0, 0);
											t_Parentesto.show();
										}
										else
										{
											if(id_estado_familiar.equals("99"))
											{
												SpnEstadoFamiliar.requestFocusFromTouch();
												Toast t_EstadoFamiliar=Toast.makeText(this, "Seleccione el Estado Familiar", Toast.LENGTH_LONG);
												t_EstadoFamiliar.setGravity(Gravity.CENTER, 0, 0);
												t_EstadoFamiliar.show();
											}
											else
											{
												if(id_asegurado_beneficiario.equals("99"))
												{
													SpnAseguradoBeneficiario.requestFocusFromTouch();
													Toast t_AseguradoBeneficiario=Toast.makeText(this, "Seleccione Si la Persona esta Asegura o es Beneficiaria", Toast.LENGTH_LONG);
													t_AseguradoBeneficiario.setGravity(Gravity.CENTER, 0, 0);
													t_AseguradoBeneficiario.show();
												}
												else
												{
													if(id_Institucion_cotizante_beneficiario.equals("99"))
													{
														SpnInstitucion_cotizante_beneficiario.requestFocusFromTouch();
														Toast t_Institucion_cotizante_beneficiario=Toast.makeText(this, "Seleccione la Institución de la que es cotizante o beneficiario", Toast.LENGTH_LONG);
														t_Institucion_cotizante_beneficiario.setGravity(Gravity.CENTER, 0, 0);
														t_Institucion_cotizante_beneficiario.show();
													}
													else
													{
														/*if(((id_tienedocumento.equals("t"))&&(numero_dui.getText().toString().equals("")))&& diffYears>=18)
														{
															numero_dui.requestFocusFromTouch();
															Toast t_numero_dui=Toast.makeText(this, "Digite el número de DUI", Toast.LENGTH_LONG);
															t_numero_dui.setGravity(Gravity.CENTER, 0, 0);
															t_numero_dui.show();
														}
														else{
															if(((id_tienedocumento.equals("t"))&&(numero_dui.getText().toString().length()<9))&& diffYears>=18){
																numero_dui.requestFocusFromTouch();
																Toast t_numero_dui=Toast.makeText(this, "El número de DUI debe contener 9 digitos", Toast.LENGTH_LONG);
																t_numero_dui.setGravity(Gravity.CENTER, 0, 0);
																t_numero_dui.show();
															}
															else
															{*/
																
																if(((id_Parentesto.equals("1"))&&(Integer.parseInt(S_correlativo)!=1))||(!(id_Parentesto.equals("1"))&&(Integer.parseInt(S_correlativo)==1)))
																{
																	correlativo.requestFocusFromTouch();
																	Toast t_no_puede_ser=Toast.makeText(this, "No puede Digitar el Correlativo 1 a Un miembro de la Familia que no sea el Jefe", Toast.LENGTH_LONG);
																	t_no_puede_ser.setGravity(Gravity.CENTER, 0, 0);
																	t_no_puede_ser.show();
																}
																else
																{
																	if((objGestionDB.getSiHayJefeEdit(id_familia,id_integrante, this.contexto)>0)&&(Integer.parseInt(S_correlativo)==1))
																	{
																		correlativo.requestFocusFromTouch();
																		Toast t_ya_hay_jefe=Toast.makeText(this, "Esta Familia ya posee un Jefe de Familia con el Correlativo Número 1", Toast.LENGTH_LONG);
																		t_ya_hay_jefe.setGravity(Gravity.CENTER, 0, 0);
																		t_ya_hay_jefe.show();
																	}
																	else
																	{
																	  //tablet = Integer.parseInt(correlativo_tablet.getText().toString());
																	  //instaciar la clase asincrona que guarda y edita
																	  //correr el doInbackground de la clase asincrona
																	
																	  S_segundo_nombre=segundo_nombre.getText().toString(); 
																	  S_tercer_nombre=tercer_nombre.getText().toString(); 
																	  S_primer_apellido=primer_apellido.getText().toString(); 
																	  S_segundo_apellido=segundo_apellido.getText().toString(); 
																	  S_apellido_casada=apellido_casada.getText().toString(); 
																	  
																	
																	
																	
																	
																	  GuardarEditarIntegrante GuardarEditar = new GuardarEditarIntegrante(this);
																	  GuardarEditar.execute();
																	 
																	 
																	  ToastExito("Se ha guardado la información");
																		 
																	  Intent i = new Intent(contexto, Integrantes.class);
																	  i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
																	  //i.putExtra("busquedaPor",3);//pasar los datos que se necesitaran en la siguiente activity
																	  //id_integrante=objGestionDB.ultimoidIntegranteInt();
																	  i.putExtra("idintegrante",id_integrante);//pasar los datos que se necesitaran en la siguiente activity
																	  //ToastExito(String.valueOf(id_integrante));
																	  //classIntegrante.ActivityIntegrantes.finish();
																	  finish();
																	  startActivity(i);
																	}
																}
															
															//}
														//}
													}
												}
											}
										}
									}
									else
									{
										ibutton_fecha_nacimiento.requestFocusFromTouch();
										Toast t_sexo=Toast.makeText(this, "La Fecha de Nacimiento seleccionada no es Valida", Toast.LENGTH_LONG);
										t_sexo.setGravity(Gravity.CENTER, 0, 0);
										t_sexo.show();
									}
								}
								
							}
							
						}																									
					}
					else
					{
						SpnTieneDocumento.requestFocusFromTouch();
						Toast t_tiene_documento=Toast.makeText(this, "Seleccione Si Tiene o No Documento", Toast.LENGTH_LONG);
						t_tiene_documento.setGravity(Gravity.CENTER, 0, 0);
						t_tiene_documento.show();
					}
				}
				
			}
		}
	}
	else
	{
		//Mostrar un mensaje de confirmación antes de realizar el test 
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setMessage("¿Desea eliminar este Integrante?");
		alertDialog.setTitle("Eliminar Integrante");
		alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
		alertDialog.setCancelable(false);
		alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{					
					mi_metodo();
				
			}
	   });
	   alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
	   {
	      public void onClick(DialogInterface dialog, int which)
	      {
	    	  Intent i = new Intent(contexto, Ver_detalle_ficha.class);
	    	  i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
	  		  i.putExtra("busquedaPor",3);//pasar los datos que se necesitaran en la siguiente activity
	    	  
	    	  finish();
	    	  startActivity(i);
	      }
	    });
	    alertDialog.show();
	}
	
	}
		
	
	public void mi_metodo()
	{
		GuardarEditarIntegrante GuardarEditar = new GuardarEditarIntegrante(this);
		GuardarEditar.execute();
		 
		ToastExito("Se ha eliminado el Integrante");
		Intent i = new Intent(contexto, Ver_detalle_ficha.class);
		i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
		i.putExtra("busquedaPor",3);//pasar los datos que se necesitaran en la siguiente activity
		
		
		
		startActivity(i);
	}
	
	
	//public int ObtenerCorrelativo(int id_familia_seleccionada){
	public String ObtenerCorrelativo(int id_familia_seleccionada){
        int entero_correlativo;
        String cadena_correlativo="";
        
        if(objGestionDB.getSiHayJefe(id_familia_seleccionada, this.contexto)==0)
        {
        	entero_correlativo=1;
        	cadena_correlativo="01";
        }
        else
        {
	        entero_correlativo=objGestionDB.getCorrelativoIntegrante(id_familia_seleccionada, this.contexto);
	        
	        cadena_correlativo= String.valueOf(entero_correlativo);
	        
	        if(cadena_correlativo.equals("1"))
	        {
	        	cadena_correlativo="01";
	        }
	        else if(cadena_correlativo.equals("2"))
	        {
	        	cadena_correlativo="02";
	        }
	        else if(cadena_correlativo.equals("3"))
	        {
	        	cadena_correlativo="03";
	        }
	        else if(cadena_correlativo.equals("4"))
	        {
	        	cadena_correlativo="04";
	        }
	        else if(cadena_correlativo.equals("5"))
	        {
	        	cadena_correlativo="05";
	        }
	        else if(cadena_correlativo.equals("6"))
	        {
	        	cadena_correlativo="06";
	        }
	        else if(cadena_correlativo.equals("7"))
	        {
	        	cadena_correlativo="07";
	        }
	        else if(cadena_correlativo.equals("8"))
	        {
	        	cadena_correlativo="08";
	        }
	        else if(cadena_correlativo.equals("9"))
	        {
	        	cadena_correlativo="09";
	        }else
	        {
	        	cadena_correlativo=cadena_correlativo;
	        	//ToastExito("aqui ta "+cadena_correlativo);
	        }
	        
	        
	        int bandera=objGestionDB.SiCorrelativoIntegranteYaExiste(id_familia_seleccionada, cadena_correlativo, this.contexto);
	        
	        if(bandera==0)
	        {
	        	entero_correlativo=entero_correlativo;
	        }
	        else
	        {
	        	int i=0;
	        	do
	        	{
	        		i=i+1;
	        		entero_correlativo=entero_correlativo+i;
	        		
	        		cadena_correlativo= String.valueOf(entero_correlativo);
	        		if(cadena_correlativo.equals("1"))
	    	        {
	    	        	cadena_correlativo="01";
	    	        }
	    	        else if(cadena_correlativo.equals("2"))
	    	        {
	    	        	cadena_correlativo="02";
	    	        }
	    	        else if(cadena_correlativo.equals("3"))
	    	        {
	    	        	cadena_correlativo="03";
	    	        }
	    	        else if(cadena_correlativo.equals("4"))
	    	        {
	    	        	cadena_correlativo="04";
	    	        }
	    	        else if(cadena_correlativo.equals("5"))
	    	        {
	    	        	cadena_correlativo="05";
	    	        }
	    	        else if(cadena_correlativo.equals("6"))
	    	        {
	    	        	cadena_correlativo="06";
	    	        }
	    	        else if(cadena_correlativo.equals("7"))
	    	        {
	    	        	cadena_correlativo="07";
	    	        }
	    	        else if(cadena_correlativo.equals("8"))
	    	        {
	    	        	cadena_correlativo="08";
	    	        }
	    	        else if(cadena_correlativo.equals("9"))
	    	        {
	    	        	cadena_correlativo="09";
	    	        }else
	    	        {
	    	        	cadena_correlativo=cadena_correlativo;
	    	        }
	        		
	        		bandera=objGestionDB.SiCorrelativoIntegranteYaExiste(id_familia_seleccionada, cadena_correlativo, this.contexto);
	        	}
	        	while(bandera==1);
	        }
        }
        //return entero_correlativo;
        return cadena_correlativo;
    }
	
	
	
	
	
	public void onClick(View v){			
		showDialog(0);
	}
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id){
		Calendar fechaActual = new GregorianCalendar();
		int anioActual = fechaActual.get(Calendar.YEAR);
		int mesoActual = fechaActual.get(Calendar.MONTH);
		int diaActual  = fechaActual.get(Calendar.DAY_OF_MONTH);
		
		return new DatePickerDialog(this, datePickerListener, anioActual, mesoActual, diaActual);
	}
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			//et_fecha_llenado.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
			//fecha_nacimiento.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
			
			
			//fechaActual;
			Calendar fechaActual = new GregorianCalendar();
			int anioActual = fechaActual.get(Calendar.YEAR);
			int mesoActual = fechaActual.get(Calendar.MONTH);
			int diaActual  = fechaActual.get(Calendar.DAY_OF_MONTH);
			
			String fechita= selectedYear+"-"+(selectedMonth + 1)+"-"+selectedDay;
			fecha_nacimiento.setText(fechita);
			
			//GregorianCalendar fechaAnterior = new GregorianCalendar(selectedYear, (selectedMonth + 1), selectedDay); 
			//diffYears = (anioActual - selectedYear - 1) + (mesoActual == selectedMonth ? (diaActual >= selectedDay ? 1 : 0) : mesoActual >= selectedMonth ? 1 : 0);
			
			diffYears= calcularEdad(selectedYear, selectedMonth, selectedDay);
			
			if(diffYears<0)
			{
				//ibutton_fecha_nacimiento.setFocusableInTouchMode(true);
				Toast toastfechanovalida = Toast.makeText(getApplicationContext(), "La Fecha de Nacimiento digitada no es Valida", Toast.LENGTH_SHORT);
				
				toastfechanovalida.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
				toastfechanovalida.setGravity(Gravity.BOTTOM, 0, 0);
		 
				toastfechanovalida.show();
								
			}
			else
			{
				edad_calculada.setText(diffYears + " años"); 
				
				
				/*if(diffYears<18)
				{
					//SpnTieneDocumento.setClickable(false);
					//SpnTieneDocumento.setSelection(1);
					numero_dui.setText("");
					numero_dui.setFocusable(false);
				}
				else
				{*/
					if(id_Nacionalidad==0||id_Nacionalidad==1)
					{
						//SpnTieneDocumento.setSelection(0);
						
						if(id_tienedocumento.equals("t"))
						{						
							SpnTieneDocumento.setClickable(true);
							numero_dui.setFocusableInTouchMode(true);
						}
						else if(id_tienedocumento.equals("f"))
						{
							SpnTieneDocumento.setClickable(true);
							numero_dui.setFocusableInTouchMode(false);
						}
						else
						{
							numero_dui.setFocusableInTouchMode(true);
						}
					}
					else
					{
						SpnTieneDocumento.setSelection(1);
						SpnTieneDocumento.setClickable(false);
						numero_dui.setFocusable(false);
						numero_dui.setText("");
					}
					
					
					
				//}
			}
			//Date fechaNac= new SimpleDateFormat("dd-MM-yyyy").parse("");
		}
	};
	
	
	public int calcularEdad(int anioseleccionado, int messeleccionado, int diaseleccionado)
	{
		//fechaActual;
		Calendar fechaActual = new GregorianCalendar();
		int anioActual = fechaActual.get(Calendar.YEAR);
		int mesoActual = fechaActual.get(Calendar.MONTH);
		int diaActual  = fechaActual.get(Calendar.DAY_OF_MONTH);
		int diferenciaAnios;
			
		GregorianCalendar fechaAnterior = new GregorianCalendar(anioseleccionado, (messeleccionado + 1), diaseleccionado); 
		diferenciaAnios = (anioActual - anioseleccionado - 1) + (mesoActual == messeleccionado ? (diaActual >= diaseleccionado ? 1 : 0) : mesoActual >= messeleccionado ? 1 : 0);
		return diferenciaAnios;
	}
	
	
	
	public class GuardarEditarIntegrante extends AsyncTask<String, Void, String> {  	
    	Context contexto;
		    protected String doInBackground(String... params) {
				    		if(action.equals("New")){
				    		//llamo el metodo que guarda
				    			guardarIntegrante();
				    		}else if(action.equals("Delete"))
				    		{				    			
				    			eliminarIntegrante();
				    		}
				    		else
				    		{
				    		//llamo el metodo que edita	}
				    			actualizarIntegrante();
				    		}
		    		return null;
		    	}
		    public GuardarEditarIntegrante(Context contexto){	
					this.contexto = contexto;
				}
	    	@Override
	    	protected void onPostExecute(String result) {
	    			progressDialog.dismiss();
	    		}
	    	@Override
	        protected void onPreExecute() 
	    	{
	            // TODO Auto-generated method stub
	            super.onPreExecute();
	            if(action.equals("New"))
	            {
	            	progressDialog = ProgressDialog.show(contexto,"","Guardando los datos...",true);	
        		}
	            else
	            {	            	
        			progressDialog = ProgressDialog.show(contexto,"","Actualizando los datos...",true);
        		}    
    		}
	    	
		    public void guardarIntegrante(){
		    	
		    	//objGestionDB.insertIntegrante(String.valueOf(ObtenerCorrelativo(id_familia)), S_primer_nombre, S_segundo_nombre, S_tercer_nombre, S_primer_apellido, S_segundo_apellido, S_apellido_casada, id_tienedocumento, numero_dui.getText().toString(), id_Nacionalidad, id_Sexo, fecha_nacimiento.getText().toString(), nombre_madre.getText().toString(), nombre_padre.getText().toString(), nombre_responsable.getText().toString(), id_familia, id_estasib_user_sp, correlativo_tablet);
		    	objGestionDB.insertIntegrante(S_correlativo, S_primer_nombre, S_segundo_nombre, S_tercer_nombre, S_primer_apellido, S_segundo_apellido, S_apellido_casada, id_tienedocumento, numero_dui.getText().toString(), id_Nacionalidad, id_Sexo, fecha_nacimiento.getText().toString(), nombre_madre.getText().toString(), nombre_padre.getText().toString(), nombre_responsable.getText().toString(), id_familia, id_estasib_user_sp, correlativo_tablet, this.contexto);
		    	//int id_integrante_mia=objGestionDB.ultimoidIntegranteInt();
		    	id_integrante=objGestionDB.ultimoidIntegranteInt(this.contexto);
				//ToastExito("el id_integrante de la funcion guardar"+id_integrante);
				
				GE(id_integrante);
				//String fechaactual=objGestionDB.fechaActual();
				//objGestionDB.insertIntegranteVariable(1, fechaactual, id_Parentesto, id_integrante, correlativo_tablet, id_estasib_user_sp);
				//objGestionDB.insertIntegranteVariable(109, fechaactual, id_estado_familiar, id_integrante, correlativo_tablet, id_estasib_user_sp);
				//objGestionDB.insertIntegranteVariable(110, fechaactual, id_asegurado_beneficiario, id_integrante, correlativo_tablet, id_estasib_user_sp);
				//objGestionDB.insertIntegranteVariable(2, fechaactual, id_Institucion_cotizante_beneficiario, id_integrante, correlativo_tablet, id_estasib_user_sp);
				
				
		    }
		    public void eliminarIntegrante()
		    {
		    	objGestionDB.deleteIntegranteVariable(id_integrante, this.contexto);
		    	objGestionDB.deleteIntegrante(id_integrante, this.contexto);
		    }
		    public void actualizarIntegrante()
		    {
		    	objGestionDB.updateIntegrante(S_correlativo, S_primer_nombre, S_segundo_nombre, S_tercer_nombre, S_primer_apellido, S_segundo_apellido, S_apellido_casada, id_tienedocumento, numero_dui.getText().toString(), id_Nacionalidad, id_Sexo, fecha_nacimiento.getText().toString(), nombre_madre.getText().toString(), nombre_padre.getText().toString(), nombre_responsable.getText().toString(), id_familia, id_estasib_user_sp, correlativo_tablet, id_integrante, this.contexto);
		    	GE(id_integrante);
		    	//objGestionDB.updateIntegranteVariable(id_integrante,1,id_Parentesto);
		    	//objGestionDB.updateIntegranteVariable(id_integrante,109,id_estado_familiar);
				//objGestionDB.updateIntegranteVariable(id_integrante,110,id_asegurado_beneficiario);
				//objGestionDB.updateIntegranteVariable(id_integrante,2,id_Institucion_cotizante_beneficiario);						    			    	
		    	//objGestionDB.updateIntegrante(String.valueOf(ObtenerCorrelativo()), S_primer_nombre, S_segundo_nombre, S_tercer_nombre, S_primer_apellido, S_segundo_apellido, S_apellido_casada, id_tienedocumento, numero_dui.getText().toString(), id_Nacionalidad, id_Sexo, fecha_nacimiento.getText().toString(), nombre_madre.getText().toString(), nombre_padre.getText().toString(), nombre_responsable.getText().toString(), id_familia, id_estasib, correlativo_tablet, id_integrante);
		    }
		    public void GE(int id_integrante){
		    	String fechaactual=objGestionDB.fechaActual();
		    	 
		    	/*String ;
		    	String ;
		    	String ;
		    	String ;*/
		    	if(Parentesko.equals("") || Parentesko==null){
		    		//inser
		    		objGestionDB.insertIntegranteVariable(1, fechaactual, id_Parentesto, id_integrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
		    	}else{
		    		//update
		    		objGestionDB.updateIntegranteVariable(id_integrante,1,id_Parentesto, this.contexto);
		    	}
		    	if(Insti_coti_bene.equals("") || Insti_coti_bene==null){
					//insert
		    		objGestionDB.insertIntegranteVariable(2, fechaactual, id_Institucion_cotizante_beneficiario, id_integrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
					
				}else{
					//update
					objGestionDB.updateIntegranteVariable(id_integrante,2,id_Institucion_cotizante_beneficiario, this.contexto);	
				}
		    	if(estadfami.equals("") || estadfami==null){
					//insert
		    		objGestionDB.insertIntegranteVariable(109, fechaactual, id_estado_familiar, id_integrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
					
				}else{
					//update
					objGestionDB.updateIntegranteVariable(id_integrante,109,id_estado_familiar, this.contexto);
				}
		    	if(asegben.equals("") || asegben==null){
					//insert
		    		objGestionDB.insertIntegranteVariable(110, fechaactual, id_asegurado_beneficiario, id_integrante, correlativo_tablet, id_estasib_user_sp, this.contexto);
					
				}else{
					//update
					objGestionDB.updateIntegranteVariable(id_integrante,110,id_asegurado_beneficiario, this.contexto);
				}
		    }
	}
	
	
	
	
	public void ToastExito(String texto) 
	{
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
	
	
}