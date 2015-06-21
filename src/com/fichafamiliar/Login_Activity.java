package com.fichafamiliar;

import db_gestion.*;

import java.io.IOException;
import java.math.BigInteger;
import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends Activity {
	private EditText username, password;
	Spinner spinner;
	public  TextView md5s;
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;

	public GestionDB objGestionDB; // = new GestionDB(this);//creo el objeto de
									// la clase que gestiona la DB

	@Override
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		getWindow (). getDecorView (). setBackgroundColor ( Color.WHITE );
		username = (EditText) findViewById(R.id.edit_username);// tomo el valor del edittext username
		password = (EditText) findViewById(R.id.edit_password);// tomo el valor del edittext password
		md5s = (TextView) findViewById(R.id.textView1);
		username.setText("");
		password.setText("");
		BaseDeDatos objBaseDeDatos = new BaseDeDatos(this);
		try {
			objBaseDeDatos.createDataBase();
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		// para el spinner
		// loadSpinnerData();//Metodo que carga el datos al spinner
		// capturo el evento selected del spinner

		/*
		 * spinner.setOnItemSelectedListener(new
		 * AdapterView.OnItemSelectedListener() { //cuando se ha seleccionado un
		 * item del spinner public void onItemSelected(AdapterView<?>
		 * parent,View view, int pos, long id) { int databaseId
		 * =(((SpinnerObject)spinner.getSelectedItem()).getId());//recupero el
		 * id //username.setText("Id seleccionado: "+databaseId); } public void
		 * onNothingSelected(AdapterView<?> arg0) { // TODO Auto-generated
		 * method stub } });
		 */
	}


	// inserto registros 
	
	/*public void boton_insert(View view) { 
	  ContentValues newValues = new
	  ContentValues(); 
	  newValues.put("username", "carlos");
	  newValues.put("password","ramos123"); 
	  objGestionDB.insertReg("usuarios",newValues); 
	  }*/
	 
	// click del boton
public void click_buton_login(View view){
		String s_username = username.getText().toString().trim();			
		String s_password = password.getText().toString().trim();
		s_password= md5(s_password);
		
		int respuesta = 0;
		if (s_username.equals("")){
			Toast.makeText(this, "Digite el usuario ", Toast.LENGTH_LONG).show();
		} else if (s_password.equals("")){
			Toast.makeText(this, "Digite el password", Toast.LENGTH_LONG).show();
		} else {
			respuesta = objGestionDB.consultarUser(s_username, s_password,contexto);// llamo el  metodo que consulta  a la db
			if (respuesta == 0){
				Toast.makeText(this, "El usuario no existe", Toast.LENGTH_LONG).show();
			} else if (respuesta == 2){
				Toast.makeText(this, "La password es incorrecta",Toast.LENGTH_LONG).show();
			} else if (respuesta == 1){
				//Todo esta bien llamo el metodo que guarda las variables en shared preferences
				sesiones_SP();
				//Toast.makeText(this,"Todo bien lazar la Acttivity que tiene el menu", Toast.LENGTH_LONG).show();
				finish();
				Intent i = new Intent(this, Menu_Activity.class);
				i.putExtra("var_user",s_username);
				
				startActivity(i);				
			} else {
				Toast.makeText(	this,"Ha ocurrido un error no se puedo consultar la informacion",Toast.LENGTH_LONG).show();
			}
		}
	}
public void sesiones_SP() {
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		Editor editor = preferencias.edit();
		editor.putInt("id_sp", objGestionDB.idusuario_tablet);// id usuario tablet
		editor.putString("user_sp", objGestionDB.codigousuario);// usuario
		editor.putString("pass_sp", objGestionDB.password_db);// contraseña
		editor.putInt("id_estasib_user_sp", objGestionDB.id_estasib_user);// id establecimiento
		editor.putString("nombreusuario_sp", objGestionDB.nombreusuario);//nombre usuario
		editor.putInt("id_sibasi_sp", objGestionDB.id_sibasi);//id sibasi
		editor.putInt("correlativo_tablet", objGestionDB.correlativo_tablet);//correlativo tablet
		editor.putInt("codigo_perfil", objGestionDB.codigo_perfil);//codigo perfil del usuario
		editor.commit();
	}
public String md5(String s) 
{
    MessageDigest digest;
    try 
    {
        digest = MessageDigest.getInstance("MD5");
        digest.update(s.getBytes(),0,s.length());
        String hash = new BigInteger(1, digest.digest()).toString(16);
       // Log.i("md5",hash);
        return hash; 
    } 
    catch (NoSuchAlgorithmException e) 
    {
        e.printStackTrace();
    }
	return "error";
    
}
}
