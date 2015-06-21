package com.fichafamiliar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Jefes_familia extends Activity {
	
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	int id_estasib_user_sp;
	String 	s_primernombre,
			s_segundonombre,
			s_primerapellido,
			s_segundoapellido;
	
	ListView listaJefes;

 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jefes_familia);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		
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
		
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0);//recupero el id del establecimiento
		
		Bundle bundle = getIntent().getExtras();
		s_primernombre    = bundle.getString("p_nombre");
		s_segundonombre   = bundle.getString("s_nombre");
		s_primerapellido  = bundle.getString("p_apellido");
		s_segundoapellido = bundle.getString("s_apellido");	
		//Toast.makeText(this, "Pnombre: "+s_primernombre+" Snombre: "+s_segundonombre+ " Papellido:"+s_primerapellido+" Sapellido:"+s_segundoapellido+" Idestasib:"+id_estasib_user_sp, Toast.LENGTH_LONG).show();
		
		listaJefes = (ListView) findViewById(R.id.listaJefesFamilia);
		
		TextView prueba = (TextView) findViewById(R.id.txtTitleMienbros);
		loadJefes(s_primernombre,s_segundonombre,s_primerapellido,s_segundoapellido,id_estasib_user_sp);
		
		listaJefes.setTextFilterEnabled(true);
		listaJefes.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> elemento, View v, int position,long id) {
				int elegido = ((SpinnerObject) elemento.getItemAtPosition(position)).getId(); 
				Intent i = new Intent(Jefes_familia.this, Ver_detalle_ficha.class);
				i.putExtra("idJefeFamilia",elegido);
				i.putExtra("busquedaPor",1);//si la busqueda es por jefe de familia busquedaPor=1
											//si la busqueda es por numero de expediente familiar busquedaPor=2
				startActivity(i);
				//int idjefefamilia=elegido.getId();
				//nada(elegido);
				//nada(idjefefamilia);
			}
		});
	}
	/*private void nada(int idjefefamilia){
		Toast.makeText(	this,"ha hecho antes del itemselected: "+idjefefamilia,Toast.LENGTH_LONG).show();
	}*/
	private void loadJefes(String Pnombre,String Snombre,String Papellido,String Sapellido,int Idestasib) {
		/*List<SpinnerObject> lista =  objGestionDB.getJefesFamilias(Pnombre, Snombre, Papellido, Sapellido,Idestasib);
		ArrayAdapter<SpinnerObject> adap = new ArrayAdapter<SpinnerObject>(this, R.layout.simplerow, lista);
		adap.setDropDownViewResource(R.layout.simplerow);
		listaJefes.setAdapter(adap);
		setListViewHeightBasedOnChildren(listaJefes);*/
		
		ArrayList<SpinnerObject> itemsCompra = objGestionDB.getJefesFamilias(Pnombre, Snombre, Papellido, Sapellido,Idestasib,this.contexto);
	    ItemCompraAdapter adapter = new ItemCompraAdapter(this, itemsCompra);
	    listaJefes.setAdapter(adapter);
	    setListViewHeightBasedOnChildren(listaJefes);
		
	}
	
	/*private ArrayList<ItemCompra> obtenerItems() {
    	ArrayList<ItemCompra> items = new ArrayList<ItemCompra>();
    	items.add(new ItemCompra(1, "Patatas", "Tuberculo"));
    	items.add(new ItemCompra(2, "Naranja", "Fruta"));
    	items.add(new ItemCompra(1, "Lechuga", "Verdura"));
    	return items;
    }*/

public static void setListViewHeightBasedOnChildren(ListView listView) 
	{
	    ListAdapter listAdapter = listView.getAdapter();
	    if (listAdapter == null) {
	        return;
	    }
	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        view = listAdapter.getView(i, view, listView);
	        if (i == 0) {
	            view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));
	        }
	        view.measure(0,0);
	        totalHeight += view.getMeasuredHeight();
	    }
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	    listView.requestLayout();
	}
	
/*	public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter(); 
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        
        }
       
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }*/
public void retur_home(View view) {
	Intent i = new Intent(this, Menu_Activity.class);
	/*i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
	i.putExtra("var_user",nombreusuario);*/
	startActivity(i);
}
}
