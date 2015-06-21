package com.fichafamiliar;

import java.io.IOException;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class Edit_delete_miembro_fam extends Activity {
	int busquedaPor;
	int idfamilia;
	int idmiembrofam;
	int siEstaVivo;
	String comovaahacer;
	ImageButton muerto,variables_integrante,delete_integrante;
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB; // New GestionDB(this);//creo el objeto de lLa clase que gestiona la DB
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_delete_miembro_fam);
		//getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		muerto = (ImageButton) findViewById(R.id.edit_miembro_familia3);
		variables_integrante = (ImageButton) findViewById(R.id.variables_integrante);
		delete_integrante = (ImageButton) findViewById(R.id.delete_integrante);
		
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();*/
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la clase que gestiona la DB
		/*} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		Bundle bundle 	= getIntent().getExtras();
		busquedaPor    	= bundle.getInt("busquedaPor");
		idfamilia    	= bundle.getInt("idfamilia");
		idmiembrofam	= bundle.getInt("idmiembrofam");
		
		
		siEstaVivo=objGestionDB.getSiEstaVivo(idmiembrofam,this.contexto);
		
		if(siEstaVivo==1)
		{
			muerto.setImageResource(R.drawable.logodefuncicioninactivo);
		}
		if(siEstaVivo==2)
		{
			muerto.setImageResource(R.drawable.logodefuncicion);
			variables_integrante.setVisibility(View.INVISIBLE);
			delete_integrante.setVisibility(View.GONE);
		}
	}
 
	public void load_view_edit_miembro_fam(View v){
		 			Intent i = new Intent(Edit_delete_miembro_fam.this, Add_new_miembro_familia.class);
					i.putExtra("idmiembrofam",idmiembrofam);
					i.putExtra("idfamilia",idfamilia);
					i.putExtra("busquedaPor",3);
					i.putExtra("action", "Edit");
					startActivity(i);	
	}
	public void load_view_del_miembro_fam(View v)
	{
			Intent i = new Intent(Edit_delete_miembro_fam.this, Add_new_miembro_familia.class);
		i.putExtra("idmiembrofam",idmiembrofam);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("busquedaPor",3);
		i.putExtra("action", "Delete");
		startActivity(i);	
	}
	public void load_chibolografo(View v)
	{
			Intent i = new Intent(Edit_delete_miembro_fam.this, Integrantes.class);
		i.putExtra("idintegrante",idmiembrofam);
		i.putExtra("idfamilia",idfamilia);
		//i.putExtra("busquedaPor",3);
		//i.putExtra("action", "Delete");
		startActivity(i);	
	}	
	public void load_defuncion(View v)
	{
		
		
		if(siEstaVivo==1)
		{
			comovaahacer="Vivo a Difunto";
		}
		
		if(siEstaVivo==2)
		{
			comovaahacer="Difunto a Vivo";
		}
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setMessage("¿Desea Cambiar el Estado de este Integrante de "+ comovaahacer +"?");
		alertDialog.setTitle("Cambiar el Estado del Integrante de "+ comovaahacer);
		alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
		alertDialog.setCancelable(false);
		alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{					
				objGestionDB.CambiarEstadoVivoMuertoIntegrante(idmiembrofam,Edit_delete_miembro_fam.this);
				
				  Intent i = new Intent(contexto, Ver_detalle_ficha.class);
		    	  i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
		  		  i.putExtra("busquedaPor",3);//pasar los datos que se necesitaran en la siguiente activity
		  		  //i.putExtra("idmiembrofam",idmiembrofam);//pasar los datos que se necesitaran en la siguiente activity
		    	  
		  		  
		  		
		  		  
		    	  finish();
		    	  startActivity(i);
				
			}
	   });
	   alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
	   {
	      public void onClick(DialogInterface dialog, int which)
	      {
	    	  Intent i = new Intent(contexto, Edit_delete_miembro_fam.class);
	    	  i.putExtra("idfamilia",idfamilia);//pasar los datos que se necesitaran en la siguiente activity
	  		  i.putExtra("busquedaPor",3);//pasar los datos que se necesitaran en la siguiente activity
	  		  i.putExtra("idmiembrofam",idmiembrofam);//pasar los datos que se necesitaran en la siguiente activity
	    	  
	  		  
	  		
	  		  
	    	  finish();
	    	  startActivity(i);
	      }
	    });
	    alertDialog.show();
		
		
		
		
		/*
			Intent i = new Intent(Edit_delete_miembro_fam.this, Add_new_miembro_familia.class);
		i.putExtra("idmiembrofam",idmiembrofam);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("busquedaPor",3);
		i.putExtra("action", "Delete");
		startActivity(i);*/	
	}	
}
