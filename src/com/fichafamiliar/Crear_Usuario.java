package com.fichafamiliar;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.fichafamiliar.CambioPassword.EditarPasswordUsuario;
import com.fichafamiliar.R.drawable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;

public class Crear_Usuario extends Activity {
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB; // New GestionDB(this);//creo el objeto de lLa clase que gestiona la DB
	public Spinner Spntipousuario, Spnregion, Spnsibasi, Spnestablecimiento;
	public EditText codigousuarionuevo, nombreusuarionuevo, claveusuarionuevo, confirmacion_clave, correlativo_tablet;
	public RadioButton rdactivo, rdinactivo;
	public TextView titulo_accion_usuario, titulo_clave_usuario, titulo_confirmacion_clave_usuario;
	public ImageButton boton, boton2;
	//public LinearLayout linearlayout_clave_usuario, linearlayout_confirmacion_clave_usuario;
	public String action="";
	public String codigo_usuario;
	public String nombre_usuario;
	public String clave;
	public String confirmacionclave;
	//public String tablet;
	public int tablet;
	public int activo; 
	public int id_usuario_tablet;
	private ProgressDialog progressDialog;
	
	
	int id_tipo_usuario, id_region, id_sibasi, id_establecimiento;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear__usuario);
		
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try 
		{
			db2.createDataBase();*/
			// db2.openDataBase();
		//	this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la clase que gestiona la DB
		/*} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

		codigousuarionuevo= (EditText) findViewById(R.id.codigousuarionuevo);
		nombreusuarionuevo= (EditText) findViewById(R.id.nombreusuarionuevo);
		claveusuarionuevo= (EditText) findViewById(R.id.claveusuarionuevo);
		confirmacion_clave= (EditText) findViewById(R.id.confirmacion_clave);
		correlativo_tablet= (EditText) findViewById(R.id.correlativo_tablet);
		rdactivo= (RadioButton) findViewById(R.id.rdactivo);
		rdinactivo= (RadioButton) findViewById(R.id.rdinactivo);
		Spntipousuario = (Spinner) findViewById(R.id.spn_tipo_usuario);
		Spnregion = (Spinner) findViewById(R.id.spn_region);
		Spnsibasi = (Spinner) findViewById(R.id.spn_sibasi);
		Spnestablecimiento= (Spinner) findViewById(R.id.spn_establecimiento);
		titulo_accion_usuario = (TextView) findViewById(R.id.titulo_accion_usuario);
		titulo_clave_usuario= (TextView) findViewById(R.id.titulo_clave_usuario);
		titulo_confirmacion_clave_usuario= (TextView) findViewById(R.id.titulo_confirmacion_clave_usuario);
		boton= (ImageButton) findViewById(R.id.imgbutton1);
		boton2= (ImageButton) findViewById(R.id.imgbutton2);
		//linearlayout_clave_usuario= (LinearLayout) findViewById(R.id.linearlayout_clave_usuario);
		//linearlayout_confirmacion_clave_usuario= (LinearLayout) findViewById(R.id.linearlayout_confirmacion_clave_usuario);
		boton2.setVisibility(View.GONE);
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "New" o "Edit" o "Delete" 
		if(action.equals("Edit")||action.equals("Delete"))
		{
			claveusuarionuevo.setFocusable(false);                      
			confirmacion_clave.setFocusable(false);
			
			if(action.equals("Edit"))
			{
				titulo_accion_usuario.setText("Editar un Usuario");
				claveusuarionuevo.setEnabled(false);
				confirmacion_clave.setEnabled(false);
				boton.setImageResource(R.drawable.edit);
				boton2.setVisibility(View.VISIBLE);
			}
			else
			{
				titulo_accion_usuario.setText("Eliminar un Usuario");
				claveusuarionuevo.setEnabled(false);
				confirmacion_clave.setEnabled(false);
				codigousuarionuevo.setEnabled(false);
				nombreusuarionuevo.setEnabled(false);
				correlativo_tablet.setEnabled(false);
				rdactivo.setEnabled(false);
				rdinactivo.setEnabled(false);
				Spntipousuario.setEnabled(false);
				Spnregion.setEnabled(false);
				Spnsibasi.setEnabled(false);
				Spnestablecimiento.setEnabled(false);
				boton.setImageResource(R.drawable.delete);
				
				
			}
			id_usuario_tablet= bundle.getInt("id_usuario_tablet");//viene de la activity anterior
			
			objGestionDB.getUsuarioInfoEdit(id_usuario_tablet,this.contexto);
			
			codigousuarionuevo.setText(objGestionDB.codigousuario);
			nombreusuarionuevo.setText(objGestionDB.nombreusuario);
			claveusuarionuevo.setText(objGestionDB.claveusuario);
			confirmacion_clave.setText(objGestionDB.claveusuario);
			correlativo_tablet.setText(String.valueOf(objGestionDB.correlativo_tablet));
			
			if(objGestionDB.estaactivo==1)
			{
				rdactivo.setChecked(true);
			}
			else
			{
				rdinactivo.setChecked(true);
			}
		}
		
		loadSpinnerDataSpntipousuario();
		loadSpinnerDataSpnRegion();
		
		Spntipousuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {		
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
				id_tipo_usuario = (((SpinnerObject)Spntipousuario.getSelectedItem()).getId()); 				
			}
			
			public void onNothingSelected(AdapterView<?> arg0){
				// TODO Auto-generated method stub
			}
		});
		
		Spnregion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {		
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
				id_region = (((SpinnerObject)Spnregion.getSelectedItem()).getId()); 
				loadSpinnerDataSpnsibasi(id_region);
			}
			
			public void onNothingSelected(AdapterView<?> arg0){
				// TODO Auto-generated method stub
			}
		});
		
		Spnsibasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {		
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
				id_sibasi = (((SpinnerObject)Spnsibasi.getSelectedItem()).getId()); 
				loadSpinnerDataSpnestablecimiento(id_sibasi);
			}
			
			public void onNothingSelected(AdapterView<?> arg0){
				// TODO Auto-generated method stub
			}
		});
		
		Spnestablecimiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {		
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
				id_establecimiento = (((SpinnerObject)Spnestablecimiento.getSelectedItem()).getId()); 				
			}
			
			public void onNothingSelected(AdapterView<?> arg0){
				// TODO Auto-generated method stub
			}
		});
		
		
		
		
		
	}


	
	private void loadSpinnerDataSpntipousuario()
	{
		List<SpinnerObject> lables = objGestionDB.getTipoUsuario(this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spntipousuario.setAdapter(dataAdapter);
		
		if(action.equals("Edit")||action.equals("Delete")){
			 int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getId()==objGestionDB.codigo_perfil){
					pos=i;
		        }       
	          }
			Spntipousuario.setSelection(pos);
		} 
		
	}
	
	private void loadSpinnerDataSpnRegion()
	{
		List<SpinnerObject> lables = objGestionDB.getRegion(this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spnregion.setAdapter(dataAdapter);
		
		if(action.equals("Edit")||action.equals("Delete")){
			 int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getId()==objGestionDB.id_region){
					pos=i;
		        }       
	          }
			Spnregion.setSelection(pos);
		}
	}
	
	private void loadSpinnerDataSpnsibasi(int id_region){
		List<SpinnerObject> lables = objGestionDB.getSiBasiXRegion(id_region,this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spnsibasi.setAdapter(dataAdapter);
		if(action.equals("Edit")||action.equals("Delete")){
			 int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getId()==objGestionDB.id_sibasi){
					pos=i;
		        }       
	          }
			Spnsibasi.setSelection(pos);
		}
	}
	
	private void loadSpinnerDataSpnestablecimiento(int id_sibasi){
		List<SpinnerObject> lables = objGestionDB.getEstablecimientoXSiBasi(id_sibasi,this.contexto);
		ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this, android.R.layout.simple_spinner_item, lables);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spnestablecimiento.setAdapter(dataAdapter);
		if(action.equals("Edit")||action.equals("Delete")){
			 int pos=0;
			for(int i=0; i<lables.size(); i++){
				if(lables.get(i).getId()==objGestionDB.id_estasib){
					pos=i;
		        }       
	          }
			Spnestablecimiento.setSelection(pos);
		}
	}
	
	
	public void mi_metodo()
	{
		GuardarEditarUsuario GuardarEditar = new GuardarEditarUsuario(this);
		GuardarEditar.execute();
		 
		ToastExito("Se ha eliminado el Usuario");
		Intent i = new Intent(contexto, Mantenimiento_Usuario.class);
		//i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
		startActivity(i);
	}
	
	
	public void new_usuario(View v){
		
		if(action.equals("Delete"))
		{
			
			
			
			//Mostrar un mensaje de confirmación antes de realizar el test 
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setMessage("¿Desea eliminar el Usuario?");
			alertDialog.setTitle("Eliminar Usuario");
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
		    	  Intent i = new Intent(contexto, Mantenimiento_Usuario.class);
		    	  //i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
		    	  finish();
		    	  startActivity(i);
		      }
		    });
		    alertDialog.show();     
			
			
			
			
			
			
			
			
			
			
			
			 
		}
		else if(action.equals("Edit"))
		{
			codigo_usuario = codigousuarionuevo.getText().toString();
			nombre_usuario= nombreusuarionuevo.getText().toString();
			//clave = claveusuarionuevo.getText().toString();
			//confirmacionclave = confirmacion_clave.getText().toString();
			
					
			
			
			
			if(rdactivo.isChecked()==true)
			{
				activo=1;
			}
			else
			{
				activo=0;
			}
			
			
			int banderaespaciosenblanco=0, c=1;
			for (int i=0; i<codigo_usuario.length();i++)
			{
				if(codigo_usuario.substring(i,c).equals(" "))
				{
					banderaespaciosenblanco++;
				}
				c++;
			}
			
			if(banderaespaciosenblanco>0)
			{
				Toast toastespacioblanco = Toast.makeText(getApplicationContext(), "El Código del Usuario no debe poseer espacios en blanco", Toast.LENGTH_SHORT);
				
				toastespacioblanco.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
				toastespacioblanco.setGravity(Gravity.BOTTOM, 0, 0);
		 
		        toastespacioblanco.show();
		        codigousuarionuevo.requestFocusFromTouch();
			}
			else
			{
				if(codigo_usuario.equals(""))
				{
					Toast toast1 = Toast.makeText(getApplicationContext(), "Debe digitar el Código del Usuario", Toast.LENGTH_SHORT);
					
					toast1.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
					toast1.setGravity(Gravity.BOTTOM, 0, 0);
			 
			        toast1.show();
			        codigousuarionuevo.requestFocusFromTouch();
				}
				else
				{
					if(codigo_usuario.length()<6)
					{
						Toast toastlargousuario = Toast.makeText(getApplicationContext(), "El Código del Usuario debe poseer almenos 6 caracteres", Toast.LENGTH_SHORT);
						
						toastlargousuario.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
						toastlargousuario.setGravity(Gravity.BOTTOM, 0, 0);
				 
				        toastlargousuario.show();
				        codigousuarionuevo.requestFocusFromTouch();
					}
					else
					{
						if(objGestionDB.getIfUsuarioExistEdit(codigo_usuario, id_usuario_tablet,this.contexto)==1)
						{
							Toast toastusuarioyaexiste = Toast.makeText(getApplicationContext(), "Ya Existe un Usuario con el codigo "+codigo_usuario+". Debe digitar otro.", Toast.LENGTH_SHORT);
							
							toastusuarioyaexiste.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
							toastusuarioyaexiste.setGravity(Gravity.BOTTOM, 0, 0);
					 
							toastusuarioyaexiste.show();
							codigousuarionuevo.requestFocusFromTouch();
						}
						else
						{
							if(id_tipo_usuario==0)
							{
								Toast toasttipousuario = Toast.makeText(getApplicationContext(), "Debe Seleccionar el Perfil del Usuario", Toast.LENGTH_SHORT);
								
								toasttipousuario.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
								toasttipousuario.setGravity(Gravity.BOTTOM, 0, 0);
						 
								toasttipousuario.show();
								Spntipousuario.requestFocusFromTouch();
							}
							else
							{
								if(nombre_usuario.equals(""))
								{
									Toast toastnombre_usuario = Toast.makeText(getApplicationContext(), "Debe digitar el Nombre del Usuario", Toast.LENGTH_SHORT);
									
									toastnombre_usuario.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
									toastnombre_usuario.setGravity(Gravity.BOTTOM, 0, 0);
							 
							        toastnombre_usuario.show();
							        nombreusuarionuevo.requestFocusFromTouch();
								}
								else
								{																																		
									if(id_region==0)
									{
										Toast toastregion = Toast.makeText(getApplicationContext(), "Debe Seleccionar la Región a la que pertenecera el Usuario", Toast.LENGTH_SHORT);
										
										toastregion.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
										toastregion.setGravity(Gravity.BOTTOM, 0, 0);
								 
										toastregion.show();
										Spnregion.requestFocusFromTouch();
									}
									else
									{
										if(id_sibasi==0)
										{
											Toast toastsibasi = Toast.makeText(getApplicationContext(), "Debe Seleccionar el SIBASI a la que pertenecera el Usuario", Toast.LENGTH_SHORT);
											
											toastsibasi.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
											toastsibasi.setGravity(Gravity.BOTTOM, 0, 0);
									 
											toastsibasi.show();
											Spnsibasi.requestFocusFromTouch();
										}
										else
										{
											if(id_establecimiento==0)
											{
												Toast toastestablecimiento = Toast.makeText(getApplicationContext(), "Debe Seleccionar el Establecimiento a la que pertenecera el Usuario", Toast.LENGTH_SHORT);
												
												toastestablecimiento.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
												toastestablecimiento.setGravity(Gravity.BOTTOM, 0, 0);
										 
												toastestablecimiento.show();
												Spnestablecimiento.requestFocusFromTouch();
											}
											else
											{
												if(correlativo_tablet.getText().toString().equals(""))
												{
													Toast toastcorrelativotablet = Toast.makeText(getApplicationContext(), "Debe Digitar el Correlativo de la Tablet asignada a el Usuario", Toast.LENGTH_SHORT);
													
													toastcorrelativotablet.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
													toastcorrelativotablet.setGravity(Gravity.BOTTOM, 0, 0);
											 
													toastcorrelativotablet.show();
													correlativo_tablet.requestFocusFromTouch();
												}
												else
												{		
													tablet = Integer.parseInt(correlativo_tablet.getText().toString());
													//instaciar la clase asincrona que guarda y edita
													//correr el doInbackground de la clase asincrona
													GuardarEditarUsuario GuardarEditar = new GuardarEditarUsuario(this);
													GuardarEditar.execute();
													 
													 
													 ToastExito("Se ha editado la información");													 
													 Intent i = new Intent(this, Mantenimiento_Usuario.class);
													 finish();
													 startActivity(i);
												}
											}
										}
									}
												
											
										
									
								}
							}
						}
					}
				}
			}
		}
		else
		{
			codigo_usuario = codigousuarionuevo.getText().toString();
			nombre_usuario= nombreusuarionuevo.getText().toString();
			clave = claveusuarionuevo.getText().toString();
			confirmacionclave = confirmacion_clave.getText().toString();
			
			action="New";		
			
			
			
			if(rdactivo.isChecked()==true)
			{
				activo=1;
			}
			else
			{
				activo=0;
			}
			
			int banderaespaciosenblanco=0, c=1;
			for (int i=0; i<codigo_usuario.length();i++)
			{
				if(codigo_usuario.substring(i,c).equals(" "))
				{
					banderaespaciosenblanco++;
				}
				c++;
			}
			
			if(banderaespaciosenblanco>0)
			{
				Toast toastespacioblanco = Toast.makeText(getApplicationContext(), "El Código del Usuario no debe poseer espacios en blanco", Toast.LENGTH_SHORT);
				
				toastespacioblanco.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
				toastespacioblanco.setGravity(Gravity.BOTTOM, 0, 0);
		 
		        toastespacioblanco.show();
		        codigousuarionuevo.requestFocusFromTouch();
			}
			else
			{
				if(codigo_usuario.equals(""))
				{
					Toast toast1 = Toast.makeText(getApplicationContext(), "Debe digitar el Código del Usuario", Toast.LENGTH_SHORT);
					
					toast1.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
					toast1.setGravity(Gravity.BOTTOM, 0, 0);
			 
			        toast1.show();
			        codigousuarionuevo.requestFocusFromTouch();
				}
				else
				{
					if(codigo_usuario.length()<6)
					{
						Toast toastlargousuario = Toast.makeText(getApplicationContext(), "El Código del Usuario debe poseer almenos 6 caracteres", Toast.LENGTH_SHORT);
						
						toastlargousuario.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
						toastlargousuario.setGravity(Gravity.BOTTOM, 0, 0);
				 
				        toastlargousuario.show();
				        codigousuarionuevo.requestFocusFromTouch();
					}
					else
					{
						if(objGestionDB.getIfUsuarioExist(codigo_usuario,this.contexto)==1)
						{
							Toast toastusuarioyaexiste = Toast.makeText(getApplicationContext(), "Ya Existe un Usuario con el codigo "+codigo_usuario+". Debe digitar otro.", Toast.LENGTH_SHORT);
							
							toastusuarioyaexiste.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
							toastusuarioyaexiste.setGravity(Gravity.BOTTOM, 0, 0);
					 
							toastusuarioyaexiste.show();
							codigousuarionuevo.requestFocusFromTouch();
						}
						else
						{
							if(id_tipo_usuario==0)
							{
								Toast toasttipousuario = Toast.makeText(getApplicationContext(), "Debe Seleccionar el Perfil del Usuario", Toast.LENGTH_SHORT);
								
								toasttipousuario.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
								toasttipousuario.setGravity(Gravity.BOTTOM, 0, 0);
						 
								toasttipousuario.show();
								Spntipousuario.requestFocusFromTouch();
							}
							else
							{
								if(nombre_usuario.equals(""))
								{
									Toast toastnombre_usuario = Toast.makeText(getApplicationContext(), "Debe digitar el Nombre del Usuario", Toast.LENGTH_SHORT);
									
									toastnombre_usuario.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
									toastnombre_usuario.setGravity(Gravity.BOTTOM, 0, 0);
							 
							        toastnombre_usuario.show();
							        nombreusuarionuevo.requestFocusFromTouch();
								}
								else
								{
									if(clave.equals(""))
									{
										Toast toastclave = Toast.makeText(getApplicationContext(), "Debe digitar la Contraseña del Usuario", Toast.LENGTH_SHORT);
										
										toastclave.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
										toastclave.setGravity(Gravity.BOTTOM, 0, 0);
								 
								        toastclave.show();
								        claveusuarionuevo.requestFocusFromTouch();
									}
									else
									{
										if(confirmacionclave.equals(""))
										{
											Toast toastconfirmacionclave = Toast.makeText(getApplicationContext(), "Debe Confirmar su contraseña", Toast.LENGTH_SHORT);
											
											toastconfirmacionclave.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
											toastconfirmacionclave.setGravity(Gravity.BOTTOM, 0, 0);
									 
									        toastconfirmacionclave.show();
									        confirmacion_clave.requestFocusFromTouch();
										}
										else
										{
								
											if(clave.equals(confirmacionclave))
											{
												if(clave.length()<6)
												{
													Toast toastlength= Toast.makeText(getApplicationContext(), "La Contraseña es demasiado corta", Toast.LENGTH_SHORT);
													
													toastlength.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
													toastlength.setGravity(Gravity.BOTTOM, 0, 0);
											 
													toastlength.show();
													claveusuarionuevo.requestFocusFromTouch();
												}
												else
												{
													if(id_region==0)
													{
														Toast toastregion = Toast.makeText(getApplicationContext(), "Debe Seleccionar la Región a la que pertenecera el Usuario", Toast.LENGTH_SHORT);
														
														toastregion.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
														toastregion.setGravity(Gravity.BOTTOM, 0, 0);
												 
														toastregion.show();
														Spnregion.requestFocusFromTouch();
													}
													else
													{
														if(id_sibasi==0)
														{
															Toast toastsibasi = Toast.makeText(getApplicationContext(), "Debe Seleccionar el SIBASI a la que pertenecera el Usuario", Toast.LENGTH_SHORT);
															
															toastsibasi.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
															toastsibasi.setGravity(Gravity.BOTTOM, 0, 0);
													 
															toastsibasi.show();
															Spnsibasi.requestFocusFromTouch();
														}
														else
														{
															if(id_establecimiento==0)
															{
																Toast toastestablecimiento = Toast.makeText(getApplicationContext(), "Debe Seleccionar el Establecimiento a la que pertenecera el Usuario", Toast.LENGTH_SHORT);
																
																toastestablecimiento.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
																toastestablecimiento.setGravity(Gravity.BOTTOM, 0, 0);
														 
																toastestablecimiento.show();
																Spnestablecimiento.requestFocusFromTouch();
															}
															else
															{
																if(correlativo_tablet.getText().toString().equals(""))
																{
																	Toast toastcorrelativotablet = Toast.makeText(getApplicationContext(), "Debe Digitar el Correlativo de la Tablet asignada a el Usuario", Toast.LENGTH_SHORT);
																	
																	toastcorrelativotablet.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
																	toastcorrelativotablet.setGravity(Gravity.BOTTOM, 0, 0);
															 
																	toastcorrelativotablet.show();
																	correlativo_tablet.requestFocusFromTouch();
																}
																else
																{
																	tablet = Integer.parseInt(correlativo_tablet.getText().toString());
																	
																	//instaciar la clase asincrona que guarda y edita
																	//correr el doInbackground de la clase asincrona
																	GuardarEditarUsuario GuardarEditar = new GuardarEditarUsuario(this);
																	GuardarEditar.execute();
																	 
																	 
																	ToastExito("Se ha guardado el Usuario");
																				 
																	Intent i = new Intent(contexto, Mantenimiento_Usuario.class);
																	//i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
																	finish();
																	startActivity(i);
																	 
																}
															}
														}
													}
												}
											}	
											else
											{
												Toast toastcompararcadenas = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
												
												toastcompararcadenas.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
												toastcompararcadenas.setGravity(Gravity.BOTTOM, 0, 0);
										 
										        toastcompararcadenas.show();
										        confirmacion_clave.requestFocusFromTouch();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	
	PasswordUsuarioReset ReiniciarPass = new PasswordUsuarioReset(this);
	public void reset_pass(View v)
	{
		//Mostrar un mensaje de confirmación antes de realizar el test 
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setMessage("¿Desea Colocar el Password por defecto para este Usuario?");
		alertDialog.setTitle("Resetear el Password");
		alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
		alertDialog.setCancelable(false);
		alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{					
				
				ReiniciarPass.execute();
				 
				 
				ToastExito("Se ha cambiado la contraseña Usuario");
							 
				Intent i = new Intent(contexto, Mantenimiento_Usuario.class);
				//i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
				finish();
				startActivity(i);													
			}
		});
		alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
		{
	      public void onClick(DialogInterface dialog, int which)
	      {
	    	  Intent i = new Intent(Crear_Usuario.this, Crear_Usuario.class);
	    	  i.putExtra("id_usuario_tablet", id_usuario_tablet);
	  		  i.putExtra("action", "Edit");
	  		startActivity(i);
	      }
	    });
	    alertDialog.show();   
	}
	
	
	public class GuardarEditarUsuario extends AsyncTask<String, Void, String> {  	
    	Context contexto;
		    protected String doInBackground(String... params) {
				    		if(action.equals("New")){
				    		//llamo el metodo que guarda
				    			guardarUsuario();
				    		}else if(action.equals("Delete"))
				    		{
				    			
				    			eliminarUsuario();
				    		}
				    		else
				    		{
				    		//llamo el metodo que edita	}
				    			actualizarUsuario();
				    		}
		    		return null;
		    	}
		    public GuardarEditarUsuario(Context contexto){	
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
	    	
		    public void guardarUsuario(){
		    	//String fechaactual=objGestionDB.fechaActual();
		    	String clave_con_md5;
		    	clave_con_md5=md5(clave);
		    	objGestionDB.insertUsuario(codigo_usuario, id_tipo_usuario, nombre_usuario, clave_con_md5, activo, id_region, id_sibasi, id_establecimiento, tablet,this.contexto);
				
				
		    }
		    public void eliminarUsuario()
		    {
		    	objGestionDB.deleteUsuario(id_usuario_tablet,this.contexto);
		    }
		    public void actualizarUsuario()
		    {
		    	objGestionDB.updateUsuario(codigo_usuario, id_tipo_usuario, nombre_usuario, activo, id_region, id_sibasi, id_establecimiento, tablet, id_usuario_tablet,this.contexto);		  
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
	
	public String md5(String s) 
	{ 
		MessageDigest digest; 
		try 
		{ 
			digest = MessageDigest.getInstance("MD5"); 
			digest.update(s.getBytes(),0,s.length()); 
			String hash = new BigInteger(1, digest.digest()).toString(16);  
			return hash; 
		} catch (NoSuchAlgorithmException e) 
		{ 
			e.printStackTrace(); 
		} 
		return "error"; 
	}
	
	public class PasswordUsuarioReset extends AsyncTask<String, Void, String> {  	
    	Context contexto;
    	
    	
		    protected String doInBackground(String... params) {
				    		
				    		//llamo el metodo que edita	
				    			ResetearPasswordUsuario();
				    		
		    		return null;
		    	}
		    public PasswordUsuarioReset(Context contexto){	
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
	            
	            	
        			progressDialog = ProgressDialog.show(contexto,"","Actualizando los datos...",true);
        		    
    		}
	    	
		    
		    public void ResetearPasswordUsuario()
		    {
		    	String clave_con_md5;
		    	clave_con_md5=md5("123456");
		    	objGestionDB.updatePassUsuario(clave_con_md5, id_usuario_tablet,this.contexto);		  
		    }
	}
	
	
	
	
}




