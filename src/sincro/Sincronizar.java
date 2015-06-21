package sincro;

 



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import General_functions.JSONTransmitter;
import android.content.Context;
import android.util.Log;
import db_gestion.GestionDB;
import tables_structure.Bitacora;


public class Sincronizar {
	public  static String TAG = "soncronizar";
	public GestionDB objGestionDB;
	private static AQuery miAquery;
	String url = "http://siff.salud.gob.sv/AndroidPrueba/Login/conlabase.php";
	
	public void enviarBitacora(Context contexto){
		ArrayList<Bitacora> bitacoras = new ArrayList<Bitacora>();
		this.objGestionDB = new GestionDB();
		
		bitacoras =  objGestionDB.obtenerBitacoraSync(contexto);
		
		JSONArray jsonArrayServer = new JSONArray();
		
		for (Bitacora bitacora : bitacoras) {
			try {
				//creamos los objetos json
				Gson gson = new Gson();
				String jsonString = gson.toJson(bitacora, Bitacora.class);
				jsonArrayServer.put(new JSONObject(jsonString));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d(TAG, "No se pudo crear el json");
			}
		}
		JSONTransmitter transmitter = new JSONTransmitter(contexto);
        transmitter.sinc(contexto,jsonArrayServer);
        
        
	/*	Map<String, Object> params = new HashMap<String, Object>();
	    params.put("jsonData", jsonArrayServer.toString());
		
	   AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
			@Override
			public void callback(String url, JSONObject json,AjaxStatus status) {
				if(json!=null){
					try {
						if(json.getBoolean("exito")){
							//Llamamos la tarea asincrona para actualizar
							//las actividades
							Log.d(TAG, "Llamamos la tarea asincrona para actualizar las actividades");
							//new ActualizarActividades(mContext,mOnSidecosSyncListener, json, enviando).execute(); 
						}else{
							Log.d(TAG, "Error en la base de datos: "+json.toString());
							//Notificamos que la operacion fue fallia
							//mOnSidecosSyncListener.onActividadSincronizada(false);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.d(TAG, "Error en el json de la actividad: "+e.getMessage());
						//Notificamos que la operacion fue fallia
						//mOnSidecosSyncListener.onActividadSincronizada(false);
					}
				}else{
					Log.d(TAG, "El json de las actividades venia vacio");
					//Notificamos que la operacion fue fallia
					//mOnSidecosSyncListener.onActividadSincronizada(false);
				}
			}
		};
		ajaxCallback.url(url).type(JSONObject.class).params(params);
		miAquery.ajax(ajaxCallback);
		*/
	}
	
}
