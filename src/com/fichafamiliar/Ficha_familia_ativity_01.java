package com.fichafamiliar;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ficha_familia_ativity_01 extends Activity{
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB; // = new GestionDB(this);//creo el objeto de
									// la clase que gestiona la DB
	public RSAsync leerAsync;
	
	private ProgressDialog progressDialog;
	 
	public  String CantidadHabitaciones="";
	
	public String 	id_TenenciaVivienda,id_MaterialParedes,id_MaterialPiso,id_MaterialTecho,id_ExpoRiesgoAmbiental,
			id_LeniaCarbonEstopaparacocinar,id_LuzElectrica, id_Telefono,id_AbastecimientoAgua, 
			id_TratamientoAguaConsumoHumano,id_TieneLetrina, id_TipoLetrina, id_ManejoAguasGrises,
			id_ManejoAguasNegras, id_ManejoBasura,id_Zancudos,id_Moscas,id_ChinchesPicudas,id_Cucarachas,id_Roedores,
			id_CultivoAgricolaPropio, id_AvesCorral, id_GanadoVacuno,id_GanadoPorcino, id_NegocioPropio, 
			id_Bono, id_VehiculoAutomotor;
	
	public String 	Num_Habitaciones,
			Num_perros,
			Num_PerrosVacunados,
			Num_Gatos,
			Num_GatosVacunados,
			Num_OtrasMascotas;
	
	public EditText    
			NumHabitaciones,Numperros,NumPerrosVacunados,
			NumGatos,NumGatosVacunados,NumOtrasMascotas;
	
		
	public Spinner SpnTenenciaVivienda,SpnMaterialParedes,
			SpnMaterialPiso, SpnMaterialTecho, SpnExpoRiesgoAmbiental,
			SpnLeniaCarbonEstopaparacocinar, SpnLuzElectrica, SpnTelefono,
			SpnAbastecimientoAgua, SpnTratamientoAguaConsumoHumano,
			SpnTieneLetrina, SpnTipoLetrina, SpnManejoAguasGrises,
			SpnManejoAguasNegras, SpnManejoBasura,SpnZancudos,SpnMoscas,
			SpnChinchesPicudas,SpnCucarachas,SpnRoedores,
			SpnCultivoAgricolaPropio, SpnAvesCorral, SpnGanadoVacuno,
			SpnGanadoPorcino, SpnNegocioPropio, SpnBono, SpnVehiculoAutomotor;
	public int id_familia;
	public String action="";
	//public String variable_familia_valor="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ficha_familia_ativity_01);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		Bundle bundle = getIntent().getExtras();
		this.id_familia= bundle.getInt("idfamilia");//viene de la activity anterior*/
		this.action=bundle.getString("action"); 
		 
		
		
		//TextEdit
		//NumHabitaciones =(EditText)findViewById(R.id.txt_NumHabitaciones);
		//Numperros = (EditText) findViewById(R.id.txt_Numperros);
		 
		//NumPerrosVacunados = (EditText) findViewById(R.id.txt_NumPerrosVacunados);
		//NumGatos = (EditText) findViewById(R.id.txt_NumGatos);
		//NumGatosVacunados = (EditText) findViewById(R.id.txt_NumGatosVacunados);
		//NumOtrasMascotas = (EditText) findViewById(R.id.txt_NumOtrasMascotas);
		
		SpnTenenciaVivienda = (Spinner) findViewById(R.id.SpnTenenciaVivienda);
		// SpnNumHabitaciones = (Spinner) findViewById(R.id.SpnNumHabitaciones);
		SpnMaterialParedes = (Spinner) findViewById(R.id.SpnMaterialParedes);
		SpnMaterialPiso = (Spinner) findViewById(R.id.SpnMaterialPiso);
		SpnMaterialTecho = (Spinner) findViewById(R.id.SpnMaterialTecho);
		//SpnExpoRiesgoAmbiental = (Spinner) findViewById(R.id.SpnExpoRiesgoAmbiental);
		//SpnLeniaCarbonEstopaparacocinar = (Spinner) findViewById(R.id.SpnLeniaCarbonEstopaparacocinar);
		//SpnLuzElectrica = (Spinner) findViewById(R.id.SpnLuzElectrica);
		//SpnTelefono = (Spinner) findViewById(R.id.SpnTelefono);
		//SpnAbastecimientoAgua = (Spinner) findViewById(R.id.SpnAbastecimientoAgua);
		//SpnTratamientoAguaConsumoHumano = (Spinner) findViewById(R.id.SpnTratamientoAguaConsumoHumano);
		//SpnTieneLetrina = (Spinner) findViewById(R.id.SpnTieneLetrina);
		//SpnTipoLetrina = (Spinner) findViewById(R.id.SpnTipoLetrina);
		//SpnManejoAguasGrises = (Spinner) findViewById(R.id.SpnManejoAguasGrises);
		/*SpnManejoAguasNegras = (Spinner) findViewById(R.id.SpnManejoAguasNegras);
		SpnManejoBasura = (Spinner) findViewById(R.id.SpnManejoBasura);
		
		SpnZancudos = (Spinner) findViewById(R.id.SpnZancudos);
		SpnMoscas = (Spinner) findViewById(R.id.SpnMoscas);
		SpnChinchesPicudas = (Spinner) findViewById(R.id.SpnChinchesPicudas);
		SpnCucarachas = (Spinner) findViewById(R.id.SpnCucarachas); 
		SpnRoedores = (Spinner) findViewById(R.id.SpnRoedores);
		
		SpnCultivoAgricolaPropio = (Spinner) findViewById(R.id.SpnCultivoAgricolaPropio);
		SpnAvesCorral = (Spinner) findViewById(R.id.SpnAvesCorral);
		SpnGanadoVacuno = (Spinner) findViewById(R.id.SpnGanadoVacuno);
		SpnGanadoPorcino = (Spinner) findViewById(R.id.SpnGanadoPorcino);
		SpnNegocioPropio = (Spinner) findViewById(R.id.SpnNegocioPropio);
		SpnBono = (Spinner) findViewById(R.id.SpnBono);
		SpnVehiculoAutomotor = (Spinner) findViewById(R.id.SpnVehiculoAutomotor);
*/
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();
			*/// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la
			/*										// clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		RSAsync leerAsync = new RSAsync(this);
		leerAsync.execute();
		SpnTenenciaVivienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_TenenciaVivienda = (((SpinnerObjectString)SpnTenenciaVivienda.getSelectedItem()).getCodigo());
				//NumHabitaciones.setText(""+id_TenenciaVivienda);
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnMaterialParedes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_MaterialParedes = (((SpinnerObjectString)SpnMaterialParedes.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnMaterialPiso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_MaterialPiso = (((SpinnerObjectString)SpnMaterialPiso.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnMaterialTecho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_MaterialTecho = (((SpinnerObjectString)SpnMaterialTecho.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnExpoRiesgoAmbiental.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ExpoRiesgoAmbiental = (((SpinnerObjectString)SpnExpoRiesgoAmbiental.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnLeniaCarbonEstopaparacocinar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_LeniaCarbonEstopaparacocinar = (((SpinnerObjectString)SpnLeniaCarbonEstopaparacocinar.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnLuzElectrica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_LuzElectrica = (((SpinnerObjectString)SpnLuzElectrica.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnTelefono.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Telefono = (((SpinnerObjectString)SpnTelefono.getSelectedItem()).getCodigo());
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
		SpnTratamientoAguaConsumoHumano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_TratamientoAguaConsumoHumano = (((SpinnerObjectString)SpnTratamientoAguaConsumoHumano.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnTieneLetrina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_TieneLetrina = (((SpinnerObjectString)SpnTieneLetrina.getSelectedItem()).getCodigo());
				/*if(id_TieneLetrina.equals("0")){
					SpnTieneLetrina.setClickable(false);
					id_TipoLetrina="NA";
					
				}else{
					SpnTipoLetrina.setClickable(true);
				}*/
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnTipoLetrina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_TipoLetrina = (((SpinnerObjectString)SpnTipoLetrina.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnManejoAguasGrises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ManejoAguasGrises = (((SpinnerObjectString)SpnManejoAguasGrises.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnManejoAguasNegras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ManejoAguasNegras = (((SpinnerObjectString)SpnManejoAguasNegras.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnManejoBasura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ManejoBasura = (((SpinnerObjectString)SpnManejoBasura.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnZancudos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Zancudos = (((SpinnerObjectString)SpnZancudos.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnMoscas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Moscas = (((SpinnerObjectString)SpnMoscas.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnChinchesPicudas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_ChinchesPicudas = (((SpinnerObjectString)SpnChinchesPicudas.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnCucarachas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Cucarachas = (((SpinnerObjectString)SpnCucarachas.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnRoedores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Roedores = (((SpinnerObjectString)SpnRoedores.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnCultivoAgricolaPropio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_CultivoAgricolaPropio = (((SpinnerObjectString)SpnCultivoAgricolaPropio.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnAvesCorral.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_AvesCorral = (((SpinnerObjectString)SpnAvesCorral.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnGanadoVacuno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_GanadoVacuno = (((SpinnerObjectString)SpnGanadoVacuno.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnGanadoPorcino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_GanadoPorcino = (((SpinnerObjectString)SpnGanadoPorcino.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnNegocioPropio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_NegocioPropio = (((SpinnerObjectString)SpnNegocioPropio.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnNegocioPropio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_NegocioPropio = (((SpinnerObjectString)SpnNegocioPropio.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnBono.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_Bono = (((SpinnerObjectString)SpnBono.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		SpnVehiculoAutomotor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
				id_VehiculoAutomotor = (((SpinnerObjectString)SpnVehiculoAutomotor.getSelectedItem()).getCodigo());
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});		
		Numperros.setOnFocusChangeListener(new OnFocusChangeListener() {
		    public void onFocusChange(View v, boolean gainFocus) {
		     //onFocus
		     if (gainFocus) {
		    	 
		    	 NumPerrosVacunados.setText("");
		     }
		     //onBlur
		     else {
			    	 Num_perros = Numperros.getText().toString();
			    	 if(Num_perros.equals(""))
			    	 {
			    		 NumPerrosVacunados.setText(""); 
			    	 }else{
				    		 int IntNumPerros=Integer.parseInt(Num_perros); 
				    		 if(IntNumPerros==0){
					    		 NumPerrosVacunados.setText(""+0);
					    	 }
			    	 }
		    	 }		    	 
		     }
		   });
		
		NumGatos.setOnFocusChangeListener(new OnFocusChangeListener() {
		    public void onFocusChange(View v, boolean gainFocus) {
		     //onFocus
		     if (gainFocus) {
		    	 NumGatosVacunados.setText("");
		     }
		     //onBlur
		     else {
			    	 Num_Gatos = NumGatos.getText().toString();
			    	 if(Num_Gatos.equals(""))
			    	 {
			    		 NumGatosVacunados.setText(""); 
			    	 }else{
				    		 int IntNumGatos=Integer.parseInt(Num_Gatos); 
				    		 if(IntNumGatos==0){
				    			 NumGatosVacunados.setText(""+0);
					    	 }
			    	 }
		    	 }		    	 
		     }
		   });
		}
 

	public void new_ficha_2(View view) {
		Num_Habitaciones = NumHabitaciones.getText().toString();
		Num_perros = Numperros.getText().toString();
		Num_PerrosVacunados = NumPerrosVacunados.getText().toString();
		Num_Gatos = NumGatos.getText().toString();
		Num_GatosVacunados = NumGatosVacunados.getText().toString();
		Num_OtrasMascotas = NumOtrasMascotas.getText().toString();

		if(id_TenenciaVivienda.equals("99")){
			SpnTenenciaVivienda.requestFocusFromTouch();
			Toast tenencia_vivienda=Toast.makeText(this, "Seleccione el tipo de tenenencia de la vivienda", Toast.LENGTH_LONG);
			tenencia_vivienda.setGravity(Gravity.CENTER, 0, 0);
			tenencia_vivienda.show();
		}else if(Num_Habitaciones.equals("")){
			NumHabitaciones.requestFocusFromTouch();
			Toast numHabitaciones=Toast.makeText(this, "Digite el número de habitaciones", Toast.LENGTH_LONG);
			numHabitaciones.setGravity(Gravity.CENTER, 0, 0);
			numHabitaciones.show();
		}else if(id_MaterialParedes.equals("99")){
			SpnMaterialParedes.requestFocusFromTouch();
			Toast material_paredes=Toast.makeText(this, "Seleccione el material de las paredes", Toast.LENGTH_LONG);
			material_paredes.setGravity(Gravity.CENTER, 0, 0);
			material_paredes.show();
		}else if(id_MaterialPiso.equals("99")){
			SpnMaterialPiso.requestFocusFromTouch();
			Toast material_piso=Toast.makeText(this, "Seleccione el material del piso", Toast.LENGTH_LONG);
			material_piso.setGravity(Gravity.CENTER, 0, 0);
			material_piso.show();
		}else if(id_MaterialTecho.equals("99")){
			SpnMaterialTecho.requestFocusFromTouch();
			Toast material_techo=Toast.makeText(this, "Seleccione el material del techo", Toast.LENGTH_LONG);
			material_techo.setGravity(Gravity.CENTER, 0, 0);
			material_techo.show();
		}else if(id_ExpoRiesgoAmbiental.equals("99")){
			SpnExpoRiesgoAmbiental.requestFocusFromTouch();
			Toast riesgo_ambiental=Toast.makeText(this, "Seleccione el riesgo ambiental", Toast.LENGTH_LONG);
			riesgo_ambiental.setGravity(Gravity.CENTER, 0, 0);
			riesgo_ambiental.show();
		}else if(id_LeniaCarbonEstopaparacocinar.equals("99")){
			SpnLeniaCarbonEstopaparacocinar.requestFocusFromTouch();
			Toast lenia_carbon_estopa_cocinar=Toast.makeText(this, "Seleccione si utiliza leña, carbon o estopa para cocinar", Toast.LENGTH_LONG);
			lenia_carbon_estopa_cocinar.setGravity(Gravity.CENTER, 0, 0);
			lenia_carbon_estopa_cocinar.show();
		}else if(id_LuzElectrica.equals("99")){
			SpnLuzElectrica.requestFocusFromTouch();
			Toast luz_electrica=Toast.makeText(this, "Seleccione si cuenta con luz electrica", Toast.LENGTH_LONG);
			luz_electrica.setGravity(Gravity.CENTER, 0, 0);
			luz_electrica.show();
		}else if(id_Telefono.equals("99")){
			SpnTelefono.requestFocusFromTouch();
			Toast telefono=Toast.makeText(this, "Seleccione si posee telefono", Toast.LENGTH_LONG);
			telefono.setGravity(Gravity.CENTER, 0, 0);
			telefono.show();
		}else if(id_AbastecimientoAgua.equals("99")){
			SpnAbastecimientoAgua.requestFocusFromTouch();
			Toast abastecimiento_agua=Toast.makeText(this, "Seleccione el tipo de abastecimiento de agua", Toast.LENGTH_LONG);
			abastecimiento_agua.setGravity(Gravity.CENTER, 0, 0);
			abastecimiento_agua.show();
		}else if(id_TratamientoAguaConsumoHumano.equals("99")){
			SpnTratamientoAguaConsumoHumano.requestFocusFromTouch();
			Toast tratamiento_agua_consumo_humano=Toast.makeText(this, "Seleccione el tipo de tratamiento de agua para consumo humano", Toast.LENGTH_LONG);
			tratamiento_agua_consumo_humano.setGravity(Gravity.CENTER, 0, 0);
			tratamiento_agua_consumo_humano.show();
		}else if(id_TieneLetrina.equals("99")){
			SpnTieneLetrina.requestFocusFromTouch();
			Toast tiene_letrina=Toast.makeText(this, "Seleccione si tiene letrina", Toast.LENGTH_LONG);
			tiene_letrina.setGravity(Gravity.CENTER, 0, 0);
			tiene_letrina.show();
		}else if(id_TipoLetrina.equals("99")){
			SpnTipoLetrina.requestFocusFromTouch();
			Toast tipo_letrina=Toast.makeText(this, "Seleccione el tipo de letrina", Toast.LENGTH_LONG);
			tipo_letrina.setGravity(Gravity.CENTER, 0, 0);
			tipo_letrina.show();
		}else if(id_ManejoAguasGrises.equals("99")){
			SpnManejoAguasGrises.requestFocusFromTouch();
			Toast manejo_aguas_grices=Toast.makeText(this, "Seleccione el manejo de aguas grices", Toast.LENGTH_LONG);
			manejo_aguas_grices.setGravity(Gravity.CENTER, 0, 0);
			manejo_aguas_grices.show();
		}else if(id_ManejoAguasNegras.equals("99")){
			SpnManejoAguasNegras.requestFocusFromTouch();
			Toast manejo_aguas_negras=Toast.makeText(this, "Seleccione el manejo de aguas negras", Toast.LENGTH_LONG);
			manejo_aguas_negras.setGravity(Gravity.CENTER, 0, 0);
			manejo_aguas_negras.show();
		}else if(id_ManejoBasura.equals("99")){
			SpnManejoBasura.requestFocusFromTouch();
			Toast manejo_basura=Toast.makeText(this, "Seleccione el manejo de la basura", Toast.LENGTH_LONG);
			manejo_basura.setGravity(Gravity.CENTER, 0, 0);
			manejo_basura.show();
		}else if(id_Zancudos.equals("99")){
			SpnZancudos.requestFocusFromTouch();
			Toast zancudos=Toast.makeText(this, "Seleccione si hay zancudos", Toast.LENGTH_LONG);
			zancudos.setGravity(Gravity.CENTER, 0, 0);
			zancudos.show();
		}else if(id_Moscas.equals("99")){
			SpnMoscas.requestFocusFromTouch();
			Toast moscas=Toast.makeText(this, "Seleccione si hay moscas", Toast.LENGTH_LONG);
			moscas.setGravity(Gravity.CENTER, 0, 0);
			moscas.show();
			}else if(id_ChinchesPicudas.equals("99")){
			SpnChinchesPicudas.requestFocusFromTouch();
			Toast chinches_picudas=Toast.makeText(this, "Seleccione si hay chinches  picudas", Toast.LENGTH_LONG);
			chinches_picudas.setGravity(Gravity.CENTER, 0, 0);
			chinches_picudas.show();
		}else if(id_Cucarachas.equals("99")){
			SpnCucarachas.requestFocusFromTouch();
			Toast cucarachas=Toast.makeText(this, "Seleccione si hay chinches  cucarachas", Toast.LENGTH_LONG);
			cucarachas.setGravity(Gravity.CENTER, 0, 0);
			cucarachas.show();
		}else if(id_Roedores.equals("99")){
			SpnRoedores.requestFocusFromTouch();
			Toast roedores=Toast.makeText(this, "Seleccione si hay roedores", Toast.LENGTH_LONG);
			roedores.setGravity(Gravity.CENTER, 0, 0);
			roedores.show();
		}else if(Num_perros.equals("")){
			Numperros.requestFocusFromTouch();
			Toast N_perros=Toast.makeText(this, "Digite el número de perros", Toast.LENGTH_LONG);
			N_perros.setGravity(Gravity.CENTER, 0, 0);
			N_perros.show();
		}else if(Num_PerrosVacunados.equals("")){
			NumPerrosVacunados.requestFocusFromTouch();
			Toast N_PerrosVacunados=Toast.makeText(this, "Digite el número de perros vacunados", Toast.LENGTH_LONG);
			N_PerrosVacunados.setGravity(Gravity.CENTER, 0, 0);
			N_PerrosVacunados.show();
		}else if(Num_Gatos.equals("")){
			NumGatos.requestFocusFromTouch();
			Toast N_Gatos=Toast.makeText(this, "Digite el número de fatos ", Toast.LENGTH_LONG);
			N_Gatos.setGravity(Gravity.CENTER, 0, 0);
			N_Gatos.show();
		}else if(Num_GatosVacunados.equals("")){
			NumGatosVacunados.requestFocusFromTouch ();
			Toast N_GatosVacunados=Toast.makeText(this, "Digite el número de gatos vacunados", Toast.LENGTH_LONG);
			N_GatosVacunados.setGravity(Gravity.CENTER, 0, 0);
			N_GatosVacunados.show();
		}else if(Num_OtrasMascotas.equals("")){
			NumOtrasMascotas.requestFocusFromTouch();
			Toast N_OtrasMascotas=Toast.makeText(this, "Digite el número de otras mascotas", Toast.LENGTH_LONG);
			N_OtrasMascotas.setGravity(Gravity.CENTER, 0, 0);
			N_OtrasMascotas.show();
		}else if(id_CultivoAgricolaPropio.equals("99")){
			SpnCultivoAgricolaPropio.requestFocusFromTouch ();
			Toast cultivo_agricola_propio=Toast.makeText(this, "Seleccione si tiene cultivo agricola propio", Toast.LENGTH_LONG);
			cultivo_agricola_propio.setGravity(Gravity.CENTER, 0, 0);
			cultivo_agricola_propio.show();
		}else if(id_AvesCorral.equals("99")){
			SpnAvesCorral.requestFocusFromTouch();
			Toast aves_corral=Toast.makeText(this, "Seleccione si tiene aves de corral", Toast.LENGTH_LONG);
			aves_corral.setGravity(Gravity.CENTER, 0, 0);
			aves_corral.show();
		}else if(id_GanadoVacuno.equals("99")){
			SpnGanadoVacuno.requestFocusFromTouch();
			Toast ganado_vacuno=Toast.makeText(this, "Seleccione si tienen ganado vacuno", Toast.LENGTH_LONG);
			ganado_vacuno.setGravity(Gravity.CENTER, 0, 0);
			ganado_vacuno.show();
		}else if(id_GanadoPorcino.equals("99")){
			SpnGanadoPorcino.requestFocusFromTouch();
			Toast ganado_porcino=Toast.makeText(this, "Seleccione si tiene ganado porcino", Toast.LENGTH_LONG);
			ganado_porcino.setGravity(Gravity.CENTER, 0, 0);
			ganado_porcino.show();
		}else if(id_NegocioPropio.equals("99")){
			SpnNegocioPropio.requestFocusFromTouch();
			Toast ganado_porcino=Toast.makeText(this, "Seleccione si tiene negocio propio", Toast.LENGTH_LONG);
			ganado_porcino.setGravity(Gravity.CENTER, 0, 0);
			ganado_porcino.show();
		}else if(id_Bono.equals("99")){
			SpnBono.requestFocusFromTouch ();
			Toast bono=Toast.makeText(this, "Seleccione el tipo de bono que recibe", Toast.LENGTH_LONG);
			bono.setGravity(Gravity.CENTER, 0, 0);
			bono.show();
		}else if(id_VehiculoAutomotor.equals("99")){
			SpnVehiculoAutomotor.requestFocusFromTouch();
			Toast vehiculo_automotor=Toast.makeText(this, "Seleccione si posee vehiculo a utomotor", Toast.LENGTH_LONG);
			vehiculo_automotor.setGravity(Gravity.CENTER, 0, 0);
			vehiculo_automotor.show();
		}else{
			//instaciar la clase asincrona que guarda y edita
			//correr el doInbackground de la clase asincrona
			GuardarEditarFicha GuardarEditar = new GuardarEditarFicha(this);
			GuardarEditar.execute();
			 
			 if(action.equals("New")){
				 ToastExito("Se ha guardado la nueva ficha");
								 
				 Intent i = new Intent(contexto, Miembros_familia.class);
				 i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
				// Log.i("idfamilia2", ""+id_familia);
				 startActivity(i);
			 }else{
				 ToastExito("Se ha editado la información");
				 Intent i = new Intent(this, Ver_detalle_ficha.class);
				 i.putExtra("busquedaPor",3);		//porque ha hecho el edit       busquedaPor=3
				 i.putExtra("idfamilia",id_familia);	//pasar los datos que se necesitaran en la siguiente activity
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
	
	
	public class RSAsync extends AsyncTask<String, Void, String> {
		Context contexto;
		//valores en la base de datos para cada variable(cuando se creo la ficha) 
	    String TenenciaVivienda_valor,
	    	   MaterialParedes_valor,
	    	   MaterialPiso_valor,
	    	   MaterialTecho_valor,
	    	   ExpoRiesgoAmbiental_valor,
	    	   LeniaCarbonEstopaparacocinar_valor,
	    	   LuzElectrica_valor,
	    	   Telefono_valor,
	    	   AbastecimientoAgua_valor,
	    	   TratamientoAguaConsumoHumano_valor,
	    	   TieneLetrina_valor,
	    	   TipoLetrina_valor,
	    	   ManejoAguasGrises_valor,
	    	   ManejoAguasNegras_valor,
	    	   ManejoBasura_valor,
	    	   Zancudos_valor,
	    	   Moscas_valor,
	    	   ChinchesPicudas_valor,
	    	   Cucarachas_valor,
	    	   Roedores_valor,
	    	   CultivoAgricolaPropio_valor,
	    	   AvesCorral_valor,
	    	   GanadoVacuno_valor,
	    	   GanadoPorcino_valor,
	    	   NegocioPropio_valor,
	    	   Bono_valor,
	    	   VehiculoAutomotor_valor;
	    
	    // para setear la posicion de la lista en los spinner
	    int    TenenciaVivienda_pos=0,
	    	   MaterialParedes_pos=0,
		 	   MaterialPiso_pos=0,
		 	   MaterialTecho_pos=0,
		 	   ExpoRiesgoAmbiental_pos=0,
		 	   LeniaCarbonEstopaparacocinar_pos=0,
		 	   LuzElectrica_pos=0,
		 	   Telefono_pos=0,
		 	   AbastecimientoAgua_pos=0,
		 	   TratamientoAguaConsumoHumano_pos=0,
		 	   TieneLetrina_pos=0,
		 	   TipoLetrina_pos=0,
		 	   ManejoAguasGrises_pos=0,
		 	   ManejoAguasNegras_pos=0,
		 	   ManejoBasura_pos=0,
		 	   Zancudos_pos=0,
		 	   Moscas_pos=0,
		 	   ChinchesPicudas_pos=0,
		 	   Cucarachas_pos=0,
		 	   Roedores_pos=0,
		 	   CultivoAgricolaPropio_pos=0,
		 	   AvesCorral_pos=0,
		 	   GanadoVacuno_pos=0,
		 	   GanadoPorcino_pos=0,
		 	   NegocioPropio_pos=0,
		 	   Bono_pos=0,
		 	   VehiculoAutomotor_pos=0; 
	    
		//cuando se llenan estas variables se las pasare a los EditText
		String perros,
			   perrosvac,
			   numcats,
			   numcatsvac,
			   otrasmasc,
			   numhab;
 	 
				ArrayAdapter<SpinnerObjectString> dataAdapterTenenciaVivienda;
				ArrayAdapter<SpinnerObjectString> dataAdapterMaterialParedes;
				ArrayAdapter<SpinnerObjectString> dataAdapterMaterialPiso;
				ArrayAdapter<SpinnerObjectString> dataAdapterMaterialTecho;
				ArrayAdapter<SpinnerObjectString> dataAdapterExpoRiesgoAmbiental;
				ArrayAdapter<SpinnerObjectString> dataAdapterLeniaCarbonEstopaparacocinar;
				ArrayAdapter<SpinnerObjectString> dataAdapterLuzElectrica;
				ArrayAdapter<SpinnerObjectString> dataAdapterTelefono;
				ArrayAdapter<SpinnerObjectString> dataAdapterAbastecimientoAgua;
				ArrayAdapter<SpinnerObjectString> dataAdapterTratamientoAguaConsumoHumano;
				ArrayAdapter<SpinnerObjectString> dataAdapterTieneLetrina;
				ArrayAdapter<SpinnerObjectString> dataAdapterTipoLetrina;
				ArrayAdapter<SpinnerObjectString> dataAdapterManejoAguasGrises;
				ArrayAdapter<SpinnerObjectString> dataAdapterManejoAguasNegras;
				ArrayAdapter<SpinnerObjectString> dataAdapterManejoBasura;
				ArrayAdapter<SpinnerObjectString> dataAdapterZancudos;
				ArrayAdapter<SpinnerObjectString> dataAdapterMoscas;
				ArrayAdapter<SpinnerObjectString> dataAdapterChinchesPicudas;
				ArrayAdapter<SpinnerObjectString> dataAdapterCucarachas;
				ArrayAdapter<SpinnerObjectString> dataAdapterRoedores;
				ArrayAdapter<SpinnerObjectString> dataAdapterCultivoAgricolaPropio;
				ArrayAdapter<SpinnerObjectString> dataAdapterAvesCorral;
				ArrayAdapter<SpinnerObjectString> dataAdapterGanadoVacuno;
				ArrayAdapter<SpinnerObjectString> dataAdapterGanadoPorcino;
				ArrayAdapter<SpinnerObjectString> dataAdapterNegocioPropio;
				ArrayAdapter<SpinnerObjectString> dataAdapterBono;
				ArrayAdapter<SpinnerObjectString> dataAdapterVehiculoAutomotor;	
		
		public RSAsync(Context contexto){	
		this.contexto = contexto;
		}
    protected String doInBackground(String... params) {
    	if (action.equals("Edit")){
    		CargarEditText();	
    	}
    	
    	CargarSpinner();
    return null;
    	}
    	@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    		dataAdapterTenenciaVivienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterMaterialParedes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterMaterialPiso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterMaterialTecho.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterExpoRiesgoAmbiental.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterLeniaCarbonEstopaparacocinar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterLuzElectrica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterTelefono.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterAbastecimientoAgua.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterTratamientoAguaConsumoHumano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterTieneLetrina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterTipoLetrina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterManejoAguasGrises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterManejoAguasNegras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterManejoBasura.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterZancudos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterMoscas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterChinchesPicudas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterCucarachas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterRoedores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterCultivoAgricolaPropio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterAvesCorral.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterGanadoVacuno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterGanadoPorcino.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterNegocioPropio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterBono.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		dataAdapterVehiculoAutomotor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
    			
    		/*result.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		sp.setAdapter(result);
    		sp.setSelection(this.pos);*/
    		SpnTenenciaVivienda.setAdapter(dataAdapterTenenciaVivienda);
    		SpnTenenciaVivienda.setSelection(this.TenenciaVivienda_pos);
    		
    		SpnMaterialParedes.setAdapter(dataAdapterMaterialParedes);
    		SpnMaterialParedes.setSelection(this.MaterialParedes_pos);
    		
    		SpnMaterialPiso.setAdapter(dataAdapterMaterialPiso);
    		SpnMaterialPiso.setSelection(this.MaterialPiso_pos);
    		
    		SpnMaterialTecho.setAdapter(dataAdapterMaterialTecho);
    		SpnMaterialTecho.setSelection(this.MaterialTecho_pos);
    		
    		SpnExpoRiesgoAmbiental.setAdapter(dataAdapterExpoRiesgoAmbiental);
    		SpnExpoRiesgoAmbiental.setSelection(this.ExpoRiesgoAmbiental_pos);
    		
    		SpnLeniaCarbonEstopaparacocinar.setAdapter(dataAdapterLeniaCarbonEstopaparacocinar);
    		SpnLeniaCarbonEstopaparacocinar.setSelection(this.LeniaCarbonEstopaparacocinar_pos);
    		
    		SpnLuzElectrica.setAdapter(dataAdapterLuzElectrica);
    		SpnLuzElectrica.setSelection(this.LuzElectrica_pos);
    		
    		SpnTelefono.setAdapter(dataAdapterTelefono);
    		SpnTelefono.setSelection(this.Telefono_pos);
    		
    		SpnAbastecimientoAgua.setAdapter(dataAdapterAbastecimientoAgua);
    		SpnAbastecimientoAgua.setSelection(this.AbastecimientoAgua_pos);
    		
    		SpnTratamientoAguaConsumoHumano.setAdapter(dataAdapterTratamientoAguaConsumoHumano);
    		SpnTratamientoAguaConsumoHumano.setSelection(this.TratamientoAguaConsumoHumano_pos);
    		
    		SpnTieneLetrina.setAdapter(dataAdapterTieneLetrina);
    		SpnTieneLetrina.setSelection(this.TieneLetrina_pos);
    		
    		SpnTipoLetrina.setAdapter(dataAdapterTipoLetrina);
    		SpnTipoLetrina.setSelection(this.TipoLetrina_pos);
    		
    		SpnManejoAguasGrises.setAdapter(dataAdapterManejoAguasGrises);
    		SpnManejoAguasGrises.setSelection(this.ManejoAguasGrises_pos);
    		
    		SpnManejoAguasNegras.setAdapter(dataAdapterManejoAguasNegras);
    		SpnManejoAguasNegras.setSelection(this.ManejoAguasNegras_pos);
    		
    		SpnManejoBasura.setAdapter(dataAdapterManejoBasura);
    		SpnManejoBasura.setSelection(this.ManejoBasura_pos);
    		
    		SpnZancudos.setAdapter(dataAdapterZancudos);
    		SpnZancudos.setSelection(this.Zancudos_pos);
    		
    		SpnMoscas.setAdapter(dataAdapterMoscas);
    		SpnMoscas.setSelection(this.Moscas_pos);
    		
    		SpnChinchesPicudas.setAdapter(dataAdapterChinchesPicudas);
    		SpnChinchesPicudas.setSelection(this.ChinchesPicudas_pos);
    		
    		SpnCucarachas.setAdapter(dataAdapterCucarachas);
    		SpnCucarachas.setSelection(this.Cucarachas_pos);
    		
    		SpnRoedores.setAdapter(dataAdapterRoedores);
    		SpnRoedores.setSelection(this.Roedores_pos);
    		
    		SpnCultivoAgricolaPropio.setAdapter(dataAdapterCultivoAgricolaPropio);
    		SpnCultivoAgricolaPropio.setSelection(this.CultivoAgricolaPropio_pos);
    		
    		SpnAvesCorral.setAdapter(dataAdapterAvesCorral);
    		SpnAvesCorral.setSelection(this.AvesCorral_pos);
    		
    		SpnGanadoVacuno.setAdapter(dataAdapterGanadoVacuno);
    		SpnGanadoVacuno.setSelection(this.GanadoVacuno_pos);
    		
    		SpnGanadoPorcino.setAdapter(dataAdapterGanadoPorcino);
    		SpnGanadoPorcino.setSelection(this.GanadoPorcino_pos);
    		
    		SpnNegocioPropio.setAdapter(dataAdapterNegocioPropio);
    		SpnNegocioPropio.setSelection(this.NegocioPropio_pos);
    		
    		SpnBono.setAdapter(dataAdapterBono);
    		SpnBono.setSelection(this.Bono_pos);
    		
    		SpnVehiculoAutomotor.setAdapter(dataAdapterVehiculoAutomotor);
    		SpnVehiculoAutomotor.setSelection(this.VehiculoAutomotor_pos);
    		
    		
    		if (action.equals("Edit")){
    			Numperros.setText(perros);
        		NumPerrosVacunados.setText(perrosvac);
    			NumGatos.setText(numcats);
    			NumGatosVacunados.setText(numcatsvac);
    			NumOtrasMascotas.setText(otrasmasc);
    			NumHabitaciones.setText(numhab);	
    		}
    		
			
			
    	    progressDialog.dismiss();
    	}
    	@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
    		//NumHabitaciones.setText("dsfasdf");
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Por favor espere mientras se cargan los datos...",true);
        }
    	public void CargarEditText(){
    		 perros=objGestionDB.getValorVariableSelecionado(id_familia,43,this.contexto);
    		 perrosvac=objGestionDB.getValorVariableSelecionado(id_familia,44,this.contexto);
    		 numcats=objGestionDB.getValorVariableSelecionado(id_familia,45,this.contexto);
    		 numcatsvac=objGestionDB.getValorVariableSelecionado(id_familia,46,this.contexto);
    		 numhab=objGestionDB.getValorVariableSelecionado(id_familia,57,this.contexto);
    		 otrasmasc=objGestionDB.getValorVariableSelecionado(id_familia,62,this.contexto);
    	} 
    	public void CargarSpinner() {																	  //descriptor
    			List<SpinnerObjectString> lablesTeneciaVivienda 			 = objGestionDB.getCatalogoDescriptor(13,this.contexto);
    			List<SpinnerObjectString> lablesMaterialParedes 			 = objGestionDB.getCatalogoDescriptor(14,this.contexto);
    			List<SpinnerObjectString> lablesMaterialPiso    			 = objGestionDB.getCatalogoDescriptor(15,this.contexto);
    			List<SpinnerObjectString> lablesMaterialTecho   			 = objGestionDB.getCatalogoDescriptor(16,this.contexto);
    			List<SpinnerObjectString> lablesExpoRiesgoAmbiental	 = objGestionDB.getCatalogoDescriptor(17,this.contexto);
    			List<SpinnerObjectString> lablesLeniaCarbonEstopaparacocinar = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesLuzElectrica 			     = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesTelefono			 		 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesAbastecimientoAgua 			 = objGestionDB.getCatalogoDescriptor(34,this.contexto);
    			List<SpinnerObjectString> lablesTratamientoAguaConsumoHumano = objGestionDB.getCatalogoDescriptor(20,this.contexto);
    			List<SpinnerObjectString> lablesTieneLetrina 				 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesTipoLetrina 				 = objGestionDB.getCatalogoDescriptor(21,this.contexto);
    			List<SpinnerObjectString> lablesManejoAguasGrises 			 = objGestionDB.getCatalogoDescriptor(22,this.contexto);
    			List<SpinnerObjectString> lablesManejoAguasNegras 			 = objGestionDB.getCatalogoDescriptor(23,this.contexto);
    			List<SpinnerObjectString> lablesManejoBasura 				 = objGestionDB.getCatalogoDescriptor(24,this.contexto);
    			List<SpinnerObjectString> lablesZancudos 					 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesMoscas  					 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesChinchesPicudas 			 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesCucarachas					 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesRoedores 					 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesCultivoAgricolaPropio 		 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesAvesCorral 					 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesGanadoVacuno 				 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesGanadoPorcino 				 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesNegocioPropio  				 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			List<SpinnerObjectString> lablesBono 						 = objGestionDB.getCatalogoDescriptor(31,this.contexto);
    			List<SpinnerObjectString> lablesVehiculoAutomotor 			 = objGestionDB.getCatalogoDescriptor(30,this.contexto);
    			
    			dataAdapterTenenciaVivienda				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTeneciaVivienda);
    			dataAdapterMaterialParedes				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMaterialParedes);
    			dataAdapterMaterialPiso					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMaterialPiso);
    			dataAdapterMaterialTecho				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMaterialTecho);
    			dataAdapterExpoRiesgoAmbiental			= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesExpoRiesgoAmbiental);
    			dataAdapterLeniaCarbonEstopaparacocinar	= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesLeniaCarbonEstopaparacocinar);
    			dataAdapterLuzElectrica					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesLuzElectrica);
    			dataAdapterTelefono						= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTelefono);
    			dataAdapterAbastecimientoAgua			= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesAbastecimientoAgua);
    			dataAdapterTratamientoAguaConsumoHumano	= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTratamientoAguaConsumoHumano);
    			dataAdapterTieneLetrina					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTieneLetrina);
    			dataAdapterTipoLetrina					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesTipoLetrina);
    			dataAdapterManejoAguasGrises			= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesManejoAguasGrises);
    			dataAdapterManejoAguasNegras			= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesManejoAguasNegras);
    			dataAdapterManejoBasura					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesManejoBasura);
    			dataAdapterZancudos						= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesZancudos);
    			dataAdapterMoscas						= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesMoscas);
    			dataAdapterChinchesPicudas				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesChinchesPicudas);
    			dataAdapterCucarachas					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesCucarachas);
    			dataAdapterRoedores						= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesRoedores);
    			dataAdapterCultivoAgricolaPropio		= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesCultivoAgricolaPropio);
    			dataAdapterAvesCorral					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesAvesCorral);
    			dataAdapterGanadoVacuno					= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesGanadoVacuno);
    			dataAdapterGanadoPorcino				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesGanadoPorcino);
    			dataAdapterNegocioPropio				= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesNegocioPropio);
    			dataAdapterBono							= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesBono);
    			dataAdapterVehiculoAutomotor			= new ArrayAdapter<SpinnerObjectString>(contexto, android.R.layout.simple_spinner_item, lablesVehiculoAutomotor);
    	
    			if(action.equals("Edit")){
    				//recupero el valor que tienen las variable cuando se creo la ficha
    				TenenciaVivienda_valor=objGestionDB.getValorVariableSelecionado(id_familia,24,this.contexto);
    				MaterialParedes_valor=objGestionDB.getValorVariableSelecionado(id_familia,25,this.contexto);
    				MaterialPiso_valor=objGestionDB.getValorVariableSelecionado(id_familia,26,this.contexto);
    				MaterialTecho_valor=objGestionDB.getValorVariableSelecionado(id_familia,27,this.contexto);
    				ExpoRiesgoAmbiental_valor=objGestionDB.getValorVariableSelecionado(id_familia,28,this.contexto);
    				LeniaCarbonEstopaparacocinar_valor=objGestionDB.getValorVariableSelecionado(id_familia,58,this.contexto);
    				LuzElectrica_valor=objGestionDB.getValorVariableSelecionado(id_familia,31,this.contexto);
    				Telefono_valor=objGestionDB.getValorVariableSelecionado(id_familia,32,this.contexto);
    				AbastecimientoAgua_valor=objGestionDB.getValorVariableSelecionado(id_familia,33,this.contexto);
    				TratamientoAguaConsumoHumano_valor=objGestionDB.getValorVariableSelecionado(id_familia,34,this.contexto);
    				TieneLetrina_valor=objGestionDB.getValorVariableSelecionado(id_familia,35,this.contexto);
    				TipoLetrina_valor=objGestionDB.getValorVariableSelecionado(id_familia,36,this.contexto);
    				ManejoAguasGrises_valor=objGestionDB.getValorVariableSelecionado(id_familia,38,this.contexto);
    		    	ManejoAguasNegras_valor=objGestionDB.getValorVariableSelecionado(id_familia,39,this.contexto);
    		    	ManejoBasura_valor=objGestionDB.getValorVariableSelecionado(id_familia,40,this.contexto);
    				Zancudos_valor=objGestionDB.getValorVariableSelecionado(id_familia,60,this.contexto);
    				Moscas_valor=objGestionDB.getValorVariableSelecionado(id_familia,56,this.contexto);
    				ChinchesPicudas_valor=objGestionDB.getValorVariableSelecionado(id_familia,41,this.contexto);
    				Cucarachas_valor=objGestionDB.getValorVariableSelecionado(id_familia,61,this.contexto);
    				Roedores_valor=objGestionDB.getValorVariableSelecionado(id_familia,42,this.contexto);
    				CultivoAgricolaPropio_valor=objGestionDB.getValorVariableSelecionado(id_familia,63,this.contexto);
    				AvesCorral_valor=objGestionDB.getValorVariableSelecionado(id_familia,47,this.contexto);
    				GanadoVacuno_valor=objGestionDB.getValorVariableSelecionado(id_familia,48,this.contexto);
    				GanadoPorcino_valor=objGestionDB.getValorVariableSelecionado(id_familia,65,this.contexto);
    				NegocioPropio_valor=objGestionDB.getValorVariableSelecionado(id_familia,66,this.contexto);
    				Bono_valor=objGestionDB.getValorVariableSelecionado(id_familia,49,this.contexto);
    				VehiculoAutomotor_valor=objGestionDB.getValorVariableSelecionado(id_familia,67,this.contexto);
    				
    				//averiguar la posicion en la lista
    				for(int i=0; i<lablesTeneciaVivienda.size(); i++){
    					if(lablesTeneciaVivienda.get(i).getCodigo().equals(TenenciaVivienda_valor)){
    						TenenciaVivienda_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesMaterialParedes.size(); i++){
    					if(lablesMaterialParedes.get(i).getCodigo().equals(MaterialParedes_valor)){
    						MaterialParedes_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesMaterialPiso.size(); i++){
    					if(lablesMaterialPiso.get(i).getCodigo().equals(MaterialPiso_valor)){
    						MaterialPiso_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesMaterialTecho.size(); i++){
    					if(lablesMaterialTecho.get(i).getCodigo().equals(MaterialTecho_valor)){
    						MaterialTecho_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesExpoRiesgoAmbiental.size(); i++){
    					if(lablesExpoRiesgoAmbiental.get(i).getCodigo().equals(ExpoRiesgoAmbiental_valor)){
    						ExpoRiesgoAmbiental_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesLeniaCarbonEstopaparacocinar.size(); i++){
    					if(lablesLeniaCarbonEstopaparacocinar.get(i).getCodigo().equals(LeniaCarbonEstopaparacocinar_valor)){
    						LeniaCarbonEstopaparacocinar_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesLuzElectrica.size(); i++){
    					if(lablesLuzElectrica.get(i).getCodigo().equals(LuzElectrica_valor)){
    						LuzElectrica_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesTelefono.size(); i++){
    					if(lablesTelefono.get(i).getCodigo().equals(Telefono_valor)){
    						Telefono_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesAbastecimientoAgua.size(); i++){
    					if(lablesAbastecimientoAgua.get(i).getCodigo().equals(AbastecimientoAgua_valor)){
    						AbastecimientoAgua_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesTratamientoAguaConsumoHumano.size(); i++){
    					if(lablesTratamientoAguaConsumoHumano.get(i).getCodigo().equals(TratamientoAguaConsumoHumano_valor)){
    						TratamientoAguaConsumoHumano_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesTieneLetrina.size(); i++){
    					if(lablesTieneLetrina.get(i).getCodigo().equals(TieneLetrina_valor)){
    						TieneLetrina_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesTipoLetrina.size(); i++){
    					if(lablesTipoLetrina.get(i).getCodigo().equals(TipoLetrina_valor)){
    						TipoLetrina_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesManejoAguasGrises.size(); i++){
    					if(lablesManejoAguasGrises.get(i).getCodigo().equals(ManejoAguasGrises_valor)){
    						ManejoAguasGrises_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesManejoAguasNegras.size(); i++){
    					if(lablesManejoAguasNegras.get(i).getCodigo().equals(ManejoAguasNegras_valor)){
    						ManejoAguasNegras_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesManejoBasura.size(); i++){
    					if(lablesManejoBasura.get(i).getCodigo().equals(ManejoBasura_valor)){
    						ManejoBasura_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesZancudos.size(); i++){
    					if(lablesZancudos.get(i).getCodigo().equals(Zancudos_valor)){
    						Zancudos_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesMoscas.size(); i++){
    					if(lablesMoscas.get(i).getCodigo().equals(Moscas_valor)){
    						Moscas_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesChinchesPicudas.size(); i++){
    					if(lablesChinchesPicudas.get(i).getCodigo().equals(ChinchesPicudas_valor)){
    						ChinchesPicudas_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesCucarachas.size(); i++){
    					if(lablesCucarachas.get(i).getCodigo().equals(Cucarachas_valor)){
    						Cucarachas_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesRoedores.size(); i++){
    					if(lablesRoedores.get(i).getCodigo().equals(Roedores_valor)){
    						Roedores_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesCultivoAgricolaPropio.size(); i++){
    					if(lablesCultivoAgricolaPropio.get(i).getCodigo().equals(CultivoAgricolaPropio_valor)){
    						CultivoAgricolaPropio_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesAvesCorral.size(); i++){
    					if(lablesAvesCorral.get(i).getCodigo().equals(AvesCorral_valor)){
    						AvesCorral_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesGanadoVacuno.size(); i++){
    					if(lablesGanadoVacuno.get(i).getCodigo().equals(GanadoVacuno_valor)){
    						GanadoVacuno_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesGanadoPorcino.size(); i++){
    					if(lablesGanadoPorcino.get(i).getCodigo().equals(GanadoPorcino_valor)){
    						GanadoPorcino_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesNegocioPropio.size(); i++){
    					if(lablesNegocioPropio.get(i).getCodigo().equals(NegocioPropio_valor)){
    						NegocioPropio_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesBono.size(); i++){
    					if(lablesBono.get(i).getCodigo().equals(Bono_valor)){
    						Bono_pos=i;
    			        }       
    		          }
    				for(int i=0; i<lablesVehiculoAutomotor.size(); i++){
    					if(lablesVehiculoAutomotor.get(i).getCodigo().equals(VehiculoAutomotor_valor)){
    						VehiculoAutomotor_pos=i;
    			        }       
    		          }
    			}//if(action.equals("Edit"))
    		}//CargarSpinner()
    }//termina la clase RSAsync
    public class GuardarEditarFicha extends AsyncTask<String, Void, String> {  	
    	Context contexto;
		    protected String doInBackground(String... params) {
				    		if(action.equals("New")){
				    		//llamo el metodo que guarda
				    			guardarFicha();
				    		}else{
				    		//llamo el metodo que edita	}
				    			actualizarFicha();
				    		}
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
				
				//uso el campo idvariable y no el _id, de la tabla  "variable"
				/*objGestionDB.insertFamiliaVariable(id_familia, 24, fechaactual, id_TenenciaVivienda);
				objGestionDB.insertFamiliaVariable(id_familia, 25, fechaactual, id_MaterialParedes);
				objGestionDB.insertFamiliaVariable(id_familia, 26, fechaactual, id_MaterialPiso);
				objGestionDB.insertFamiliaVariable(id_familia, 27, fechaactual, id_MaterialTecho);
				objGestionDB.insertFamiliaVariable(id_familia, 28, fechaactual, id_ExpoRiesgoAmbiental);
				objGestionDB.insertFamiliaVariable(id_familia, 58, fechaactual, id_LeniaCarbonEstopaparacocinar);
				objGestionDB.insertFamiliaVariable(id_familia, 31, fechaactual, id_LuzElectrica);	
				
				objGestionDB.insertFamiliaVariable(id_familia, 32, fechaactual, id_Telefono);
				objGestionDB.insertFamiliaVariable(id_familia, 33, fechaactual, id_AbastecimientoAgua);
				
				objGestionDB.insertFamiliaVariable(id_familia, 34, fechaactual, id_TratamientoAguaConsumoHumano);
				objGestionDB.insertFamiliaVariable(id_familia, 35, fechaactual, id_TieneLetrina);
				
				objGestionDB.insertFamiliaVariable(id_familia, 36, fechaactual, id_TipoLetrina);
				objGestionDB.insertFamiliaVariable(id_familia, 38, fechaactual, id_ManejoAguasGrises);
				objGestionDB.insertFamiliaVariable(id_familia, 39, fechaactual, id_ManejoAguasNegras);
				
				objGestionDB.insertFamiliaVariable(id_familia, 40, fechaactual, id_ManejoBasura);
				objGestionDB.insertFamiliaVariable(id_familia, 60, fechaactual, id_Zancudos);
				objGestionDB.insertFamiliaVariable(id_familia, 56, fechaactual, id_Moscas);
				objGestionDB.insertFamiliaVariable(id_familia, 41, fechaactual, id_ChinchesPicudas);
				objGestionDB.insertFamiliaVariable(id_familia, 61, fechaactual, id_Cucarachas);
				objGestionDB.insertFamiliaVariable(id_familia, 42, fechaactual, id_Roedores);
				objGestionDB.insertFamiliaVariable(id_familia, 43, fechaactual, Num_perros);
				objGestionDB.insertFamiliaVariable(id_familia, 44, fechaactual, Num_PerrosVacunados);
				objGestionDB.insertFamiliaVariable(id_familia, 45, fechaactual, Num_Gatos);
				objGestionDB.insertFamiliaVariable(id_familia, 46, fechaactual, Num_GatosVacunados);
				objGestionDB.insertFamiliaVariable(id_familia, 62, fechaactual, Num_OtrasMascotas);
				objGestionDB.insertFamiliaVariable(id_familia, 63, fechaactual, id_CultivoAgricolaPropio);
				objGestionDB.insertFamiliaVariable(id_familia, 47, fechaactual, id_AvesCorral);
				objGestionDB.insertFamiliaVariable(id_familia, 48, fechaactual, id_GanadoVacuno);
				objGestionDB.insertFamiliaVariable(id_familia, 65, fechaactual, id_GanadoPorcino);
				objGestionDB.insertFamiliaVariable(id_familia, 66, fechaactual, id_NegocioPropio);
				objGestionDB.insertFamiliaVariable(id_familia, 49, fechaactual, id_Bono);
				objGestionDB.insertFamiliaVariable(id_familia, 67, fechaactual, id_VehiculoAutomotor);
				objGestionDB.insertFamiliaVariable(id_familia, 57, fechaactual, Num_Habitaciones);*/
		
				
		    }
		    public void actualizarFicha(){
		    	objGestionDB.updateFamiliaVariable(id_familia, 24, id_TenenciaVivienda,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 25, id_MaterialParedes,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 26, id_MaterialPiso,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 27, id_MaterialTecho,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 28, id_ExpoRiesgoAmbiental,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 58, id_LeniaCarbonEstopaparacocinar,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 31, id_LuzElectrica,this.contexto);	
				
				objGestionDB.updateFamiliaVariable(id_familia, 32, id_Telefono,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 33, id_AbastecimientoAgua,this.contexto);				
				objGestionDB.updateFamiliaVariable(id_familia, 34, id_TratamientoAguaConsumoHumano,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 35, id_TieneLetrina,this.contexto);
				
				objGestionDB.updateFamiliaVariable(id_familia, 36, id_TipoLetrina,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 38, id_ManejoAguasGrises,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 39, id_ManejoAguasNegras,this.contexto);
				
				objGestionDB.updateFamiliaVariable(id_familia, 40, id_ManejoBasura,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 60, id_Zancudos,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 56, id_Moscas,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 41, id_ChinchesPicudas,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 61, id_Cucarachas,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 42, id_Roedores,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 43, Num_perros,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 44, Num_PerrosVacunados,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 45, Num_Gatos,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 46, Num_GatosVacunados,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 62, Num_OtrasMascotas,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 63, id_CultivoAgricolaPropio,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 47, id_AvesCorral,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 48, id_GanadoVacuno,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 65, id_GanadoPorcino,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 66, id_NegocioPropio,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 49, id_Bono,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 67, id_VehiculoAutomotor,this.contexto);
				objGestionDB.updateFamiliaVariable(id_familia, 57, Num_Habitaciones,this.contexto);
		    }
    }
	
}//termina la clase de la activity
