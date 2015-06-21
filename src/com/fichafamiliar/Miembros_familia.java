package com.fichafamiliar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Miembros_familia extends Activity {

	ListView listaMiembrosFamilia;
	List<SpinnerObject> lista = new ArrayList<SpinnerObject>();
	SpinnerObject ObjSpinnerObject;
	TextView prueba;
	
	private int id_familia;
	
TextView texto,usuario_menu;
	
	//variables para sp
	int id_sp;
	String user_sp;
	String pass_sp;
	int id_estasib_user_sp;
	String nombreusuario;
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.miembros_familia);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		 
		//usuario_menu = (TextView)findViewById(R.id.text_usuario_menu);
		
		 id_sp = preferencias.getInt("id_sp", 0);
		 user_sp = preferencias.getString("user_sp", "");
		 pass_sp = preferencias.getString("pass_sp", "");
		 id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0);
		 nombreusuario=preferencias.getString("nombreusuario_sp", "");
		
		//usuario_menu.setText(" "+nombreusuario);
		// texto.setText("Estas variables estan en sesion sharedPreferences:  id_sp: "+id_sp+" user_sp: "+user_sp+" pass_sp: "+pass_sp);
		
		Bundle bundle = getIntent().getExtras();
		this.id_familia= bundle.getInt("idfamilia");//viene de la activity anterior*/
		//this.id_familia=714;
 
	 
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

		listaMiembrosFamilia = (ListView) findViewById(R.id.listaMiembrosFamilia);
		prueba = (TextView) findViewById(R.id.txtTitleMienbros);

		loadMiembrosFamilia(this.id_familia);
		// clic en elemento de la lista de miembros de la familia*/

		listaMiembrosFamilia.setTextFilterEnabled(true);
		listaMiembrosFamilia.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				/*
				 * AlertDialog.Builder adb = new
				 * AlertDialog.Builder(Miembros_familia.this);
				 * adb.setTitle("ListView OnClick");
				 * adb.setMessage("Selected Item is = "+
				 * listaMiembrosFamilia.getItemAtPosition(position));
				 * adb.setPositiveButton("Ok", null); adb.show();
				 */
				/*
				 * int databaseId=(((SpinnerObject)listaMiembrosFamilia.getItemAtPosition(position)).getId()); 
				 * prueba.setText("eligio:" + databaseId);
				 */
				Intent i = new Intent(Miembros_familia.this,Edit_delete_miembro_fam.class);
				startActivity(i);
			}
		});
	}
	private void loadMiembrosFamilia(int idfamilia) {
				lista = objGestionDB.miembrosFamilia(idfamilia,this.contexto);
				ArrayAdapter<SpinnerObject> adap = new ArrayAdapter<SpinnerObject>(getApplicationContext(), R.layout.simplerow, lista);
				listaMiembrosFamilia.setAdapter(adap);
	}
 
	public void add_new_miembro_familia(View view) {
		// llamar metodo que consulta que verifica que la vivienda no esta deshabitada
		// llamar la activity que contienen el formulario para ingresar un
		// miembro de la familia
		// llevar el id de la familia
		Intent i = new Intent(this, Add_new_miembro_familia.class);
		i.putExtra("idfamilia", this.id_familia);
		startActivity(i);
	}
	public void retur_home_click(View view) {
		Intent i = new Intent(this, Menu_Activity.class);
		/*i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
		i.putExtra("var_user",nombreusuario);*/
		startActivity(i);
	}
}
