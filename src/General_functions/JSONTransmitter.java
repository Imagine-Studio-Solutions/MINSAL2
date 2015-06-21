package General_functions;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONObject;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
 
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
 
public class JSONTransmitter  {
	public Context contexto;
	int id;
	String url = "http://siff.salud.gob.sv/AndroidPrueba/Login/conlabase.php";
	private ProgressDialog progressDialog;
	public SQLiteDatabase conexOpen;
	
	public GestionDB objGestionDB;
	
	 public JSONTransmitter(Context contexto/*,int id*/){	
			//this.contexto = contexto;
			//this.id=id;
			
			this.objGestionDB = new GestionDB();// creo el objeto de la DB
			//BaseDeDatos db2 = new BaseDeDatos(this.context);
			/*try {
				db2.createDataBase();
				// db2.openDataBase();
				this.conexOpen = db2.myDataBase;
				this.objGestionDB = new GestionDB(this.context);// creo el objeto de la
														// clase que gestiona la DB
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			} 
    public void sinc(Context contexto,JSONArray... data) {
    	
    	
    	JSONArray json = data[0];
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 700000);
        
        JSONObject jsonResponse = null;
        
        HttpPost post = new HttpPost(url);
        try {
            StringEntity se = new StringEntity("jsonData="+json.toString());
            
            post.addHeader("content-type", "application/x-www-form-urlencoded");
            post.setEntity(se);
 
            HttpResponse response;
            response = client.execute(post);
            
            String resFromServer = org.apache.http.util.EntityUtils.toString(response.getEntity());
            jsonResponse=new JSONObject(resFromServer);
            
           // Log.d("ResPuesta", jsonResponse.toString());
              
              JSONArray  jsonArray= jsonResponse.getJSONArray("datos");
              //String  jsonString= jsonResponse.getString("mensaje");
              //boolean  jsonBoolean= jsonResponse.getBoolean("exito");
              
              //if(jsonBoolean){
            	  objGestionDB.actualizarEnviados(jsonArray,contexto); 
              //}
             
              
              
            /*String resultadoSincro=jsonResponse.getString("resultado");
        	if(resultadoSincro.equals("1")){
        		ContentValues newValues = new ContentValues();
        		newValues.put("sincronizado", 1);
        		objGestionDB.updateReg("bitacora", newValues, "id="+this.id+"",this.contexto);
        		
        	}*/
           //Log.i("Response from server", ""+jsonResponse.getString("resultado"));
        } catch (Exception e) {
        	Log.i("errorFicha", "algo inesperado paso");
        	e.printStackTrace();
        	}
      //  return jsonResponse;
    }
   /* @Override
    protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		progressDialog.dismiss();
	}
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,"","Enviando datos al servidor...",true);
    }*/
 
}