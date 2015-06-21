package db_gestion;

 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tables_structure.Bitacora;

import com.fichafamiliar.R;
import com.fichafamiliar.R.string;
import com.fichafamiliar.SpinnerObject;
import com.fichafamiliar.SpinnerObjectString;

import General_functions.Common_function;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GestionDB {
	
	
	// variable para gestionar usuarios
	public int idusuario_tablet = 0;
	public String codigousuario ="";
	public int codigo_perfil;
	public String password_db ="";
	public int id_estasib_user=0;
	public String nombreusuario="";
	public int id_sibasi=0;
	public int id_region=0;

	public int correlativo_tablet=0;
	
	public int estaactivo;
	
	public String nombreJefaFamilia="";
	public String direccionFamilia="";
	public Integer idfamilia=0;
	public Integer idfamiliaBuscar=0;
	public String query1="";
	 
	public int codigo_departamento=0;
	public int codigo_municipio=0;
	public String codigo_area="";
	public int codigo_canton=0;
	public int codigo_colonia=0;
	public String codigo_zona="";
	public String codigo_sit_vivienda="";
	public int numerovivienda=0;
	public int numerofamilia=0; 
	public String fechaintroduccion="";
	public String codigo_religion=""; 
	public String codigo_tipofamilia="";
	public Integer  tipo_riesgofamiliar=0;
	public String codigo_puebloindigena="";
	public String s_latitud="";
	public String s_longitud=""; 
	//-----------Ultimas---------------------------------------------------------------
		public String numerocorrelativo="";
		public String fechanacimiento="";
		public String sexo="";
		public String dui="";
		public int nacionalidad=0;
		public String primerapellido="";
		public String segundoapellido="";
		public String apellidocasada="";
		public String primernombre="";
		public String segundonombre="";
		public String tercernombre="";
		public String nombre_madre="";
		public String nombre_padre="";
		public String nombre_responsable="";
		public String tienedocumento="";
		//---------------------------------------------------------------------------------
	
	public String valor_variable_familia="";
	public String valor_variable_integrante="";
	public String IntegranteNombreApellidoConcatenado, IntegranteFechaNac;
	
	
	private static final String PREFRENCES_NAME = "sesiones_SharedPreferences";
	public SQLiteDatabase conexOpen;// contiene la conexion a la base de datos
	private  Context ccontexto;// el contexto de la aplicacion para
									// nosotros sera siempre el de la
									// MainActivity
									// el constructor de la clase
	public void conectarDB(Context contexto) {
		 
		BaseDeDatos objBaseDeDatos = new BaseDeDatos(ccontexto);
		objBaseDeDatos.openDataBase();
		this.conexOpen = objBaseDeDatos.myDataBase;// guardo la conexion a la base de datos
		/*try {
			db2.createDataBase();// se crea la base de datos, solo se crea si no existe.
			db2.openDataBase();	 // abro la conexion a la base de datos
			this.conexOpen = db2.myDataBase;// guardo la conexion a la base de datos
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public void desConectarDB() {
		if (this.conexOpen != null)
			this.conexOpen.close(); 
	}
	// ---------------mantenimiento de las tablas--------------------
	// este metodo recibe el nombre de la tabla y un objeto con los datos que va
	// a insertar
	public void insertReg(String table, ContentValues newValues,Context contexto) {
		conectarDB(contexto);
		conexOpen.insert(table, null, newValues); // ejecuto el insert
		desConectarDB();
		
	}
	public void updateReg(String table, ContentValues newValues, String clausawhere,Context contexto){
		conectarDB(contexto);
		conexOpen.update(table, newValues, clausawhere, null);
		desConectarDB(); 
	}
	public void updateFamiliaVariable(int idfamilia, int idvariable, int valor,Context contexto){
		ContentValues newValues = new ContentValues();
		newValues.put("valor", valor);
		
		conectarDB(contexto);
		conexOpen.update("familia_variable", newValues, "id_familia_tablet="+idfamilia+" AND id_variable="+idvariable, null); 
		desConectarDB(); 
	}
	public void updateFamiliaVariable(int idfamilia, int idvariable, String valor, Context contexto){
		ContentValues newValues = new ContentValues();
		newValues.put("valor", valor);
		
		conectarDB(contexto);
		conexOpen.update("familia_variable", newValues, "id_familia_tablet="+idfamilia+" AND id_variable="+idvariable, null); 
		desConectarDB(); 
	}
	
	//insertar en la tabla famili_variable
	public void insertFamiliaVariable(int id_familia_tablet, int idvariable, String fecha, int valor,int correlativo_tablet,int id_estasib,Context contexto){
			ContentValues newValues = new ContentValues(); 
			newValues.put("id_familia_tablet", id_familia_tablet);
			newValues.put("id_variable", idvariable);
			newValues.put("fecha", fecha);
			newValues.put("valor", valor);
			newValues.put("correlativo_tablet", correlativo_tablet);
			newValues.put("id_estasib", id_estasib);
			
			conectarDB(contexto);
			conexOpen.insert("familia_variable", null, newValues);
			desConectarDB();
			 
	}
	//sobre escribo el metodo, ahora el valor es String
	public void insertFamiliaVariable(int id_familia_tablet, int idvariable, String fecha, String valor,int correlativo_tablet,int id_estasib,Context contexto){
			ContentValues newValues = new ContentValues();
			newValues.put("id_familia_tablet", id_familia_tablet);
			newValues.put("id_variable", idvariable);
			newValues.put("fecha", fecha);
			newValues.put("valor", valor);
			newValues.put("correlativo_tablet", correlativo_tablet);
			newValues.put("id_estasib", id_estasib);
			
			conectarDB(contexto);
			conexOpen.insert("familia_variable", null, newValues);
			desConectarDB(); 
		
	}
	//insertar en la tabla integrante_variable
	public void insertIntegranteVariable(int id_variable,String fecha,int valor,int idintegrante_tablet, int correlativo_tablet, int id_estasib, Context contexto){
			ContentValues newValues = new ContentValues(); 
			newValues.put("id_variable", id_variable);
			newValues.put("fecha", fecha); 
			newValues.put("valor", valor);
			newValues.put("idintegrante_tablet", idintegrante_tablet);
			newValues.put("correlativo_tablet", correlativo_tablet);
			newValues.put("id_estasib", id_estasib);
			
			conectarDB(contexto);
			conexOpen.insert("integrante_variable", null, newValues);
			desConectarDB();
			 
		}
	//insertar en la tabla integrante_variable
	public void insertIntegranteVariable(int id_variable,String fecha,String valor,int idintegrante_tablet, int correlativo_tablet, int id_estasib, Context contexto){
			ContentValues newValues = new ContentValues(); 
			newValues.put("id_variable", id_variable);
			newValues.put("fecha", fecha); 
			newValues.put("valor", valor);
			newValues.put("idintegrante_tablet", idintegrante_tablet);
			newValues.put("correlativo_tablet", correlativo_tablet);
			newValues.put("id_estasib", id_estasib);
			
			conectarDB(contexto);
			conexOpen.insert("integrante_variable", null, newValues);
			desConectarDB();
		}
	public void updateIntegranteVariable(int idintegrante_tablet,int id_variable,int valor, Context contexto){
		ContentValues newValues = new ContentValues(); 
		newValues.put("valor", valor);
		
		conectarDB(contexto);
		conexOpen.update("integrante_variable", newValues, "idintegrante_tablet="+idintegrante_tablet+" AND id_variable="+id_variable, null); 
		desConectarDB();
	}

	public void updateIntegranteVariable(int idintegrante_tablet,int id_variable,String valor, Context contexto){
		ContentValues newValues = new ContentValues(); 
		newValues.put("valor", valor);
		
		conectarDB(contexto);
		conexOpen.update("integrante_variable", newValues, "idintegrante_tablet="+idintegrante_tablet+" AND id_variable="+id_variable, null); 
		desConectarDB();
	}
	
	public String fechaActual(){
		Calendar fecha = new GregorianCalendar();
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia =fecha.get(Calendar.DAY_OF_MONTH);
		String fechaactual=anio+"-"+(mes+1)+"-"+(dia);
		return fechaactual;
		//retorna un string con la fecha: aaaa-mm-dd
	}
 
	//recuperar ultimo id ingresado
	public int ultimoidInt(String tabla,Context contexto){
		int ultimoid=0;
		conectarDB(contexto);
		Cursor cQuery = conexOpen.rawQuery("Select idfamilia_tablet FROM "+ tabla +" order by idfamilia_tablet DESC LIMIT 1", null);
		if (cQuery.moveToFirst()) {// Recorremos el cursor hasta que no haya mas registros
				do { 
					ultimoid = cQuery.getInt(0);
				} while (cQuery.moveToNext());
			}
		desConectarDB();
		return ultimoid;
	}
	public int consultarUser(String user, String password,Context contexto) {
		int respuesta_db = 0;
		conectarDB(contexto);//concecto con la base de datos
		       Cursor c = conexOpen.rawQuery("SELECT idusuario_tablet," +
											" codigousuario," +
											" codigo_perfil," +
											" nombreusuario," +
											" claveusuario," +
											" estaactivo, " +
											" id_region," +
											" id_sibasi, " +
											" id_estasib, " +
											" correlativo_tablet " +
									" FROM 	usuario WHERE codigousuario='" + user + "'", null);
		if (c.moveToFirst()) {						//	Recorremos el cursor hasta que no haya mas registros.
			do {
				idusuario_tablet = c.getInt(0);		//	id del usuario
				codigousuario = c.getString(1);
				codigo_perfil = c.getInt(2);
				nombreusuario	= c.getString(3);
				password_db = c.getString(4);
				estaactivo = c.getInt(5);
				id_region= c.getInt(6);				//	region del usuario
				id_sibasi =c.getInt(7); 			//	sibasi del usuario
				id_estasib_user	= c.getInt(8);		//	establecimiento del usuario
				correlativo_tablet= c.getInt(9);	//	correlativo tablet del usuario
			} while (c.moveToNext());
		}
		if ((codigousuario.equals(""))) {			//	El usuario no existe
			respuesta_db = 0;
		} else {
			if (password_db.equals(password)) {		//	Guardo en variables de la
											   		//  clase los datos del usuario
				respuesta_db = 1;
			} else {
				respuesta_db = 2;
			}
		}
		desConectarDB();//desconecto la base de datos
		return respuesta_db;
	}
	public List<SpinnerObject> getAllNames(Context contexto) {
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		
		conectarDB(contexto);//conecto con la base de datos
		Cursor cursor = conexOpen.rawQuery("SELECT * from departamento", null);
		names.add(new SpinnerObject(0, "Seleccione"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		desConectarDB();//desconecto la base de datos
		return names;
	}

	public List<SpinnerObject> getDepto(Context contexto) {
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT departamentoid,departamento from departamento order by departamentoid", null);
		names.add(new SpinnerObject(0, "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		desConectarDB();
		return names;	
	}

	public List<SpinnerObject> getMunicipioXDepto(int id_depto,Context contexto) {
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery(
				"SELECT idmunicipio,nombremunicipio from municipios where dpto_id='"+ id_depto + "' order by nombremunicipio", null);
		names.add(new SpinnerObject(0, "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getArea(Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT codigoarea,nombrearea from area",null);
		names.add(new SpinnerObjectString("0", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}

	public List<SpinnerObject> getCantonBarrioColonia(String id_area,int id_municipio, Context contexto) {
		conectarDB(contexto);
		
		String query = "";
		if (id_area.equals("U")){
			query = "SELECT idcolonia,nombrecolonia FROM colonias WHERE id_municipio='"+ id_municipio + "' order by nombrecolonia";
		} else {
			query = "SELECT idcanton,canton FROM cantones WHERE id_municipio='"+ id_municipio + "' order by canton";
		}
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		Cursor cursor = conexOpen.rawQuery(query, null);
		names.add(new SpinnerObject(0, "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}

	public List<SpinnerObjectString> getZona(Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT codigozona from zona",
				null);
		names.add(new SpinnerObjectString("0", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(0)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}

	public List<SpinnerObjectString> getSituacionVivienda(Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery(
				"SELECT codigosituacion,descripcion from situacionvivienda WHERE codigosituacion IN('01','02','06')", null);
		names.add(new SpinnerObjectString("0", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}

	public List<SpinnerObjectString> getReligionFamilia(Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT codigoreligion,nombrereligion from religion", null);
		names.add(new SpinnerObjectString("0", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}

	public List<SpinnerObjectString> getPuebloIndigenaFamilia(Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery(
				"SELECT codigoetnia,nombreetnia from puebloindigena", null);
		names.add(new SpinnerObjectString("0", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}

	public List<SpinnerObjectString> getTipoFamilia(Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT codigotipofamilia,nombretipofamilia from tipofamilia", null);
		names.add(new SpinnerObjectString("0", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}

	// Activity Ficha_familia_activity_01
	// ***********************************************************************************
	public List<SpinnerObject> miembrosFamilia(int idfamilia, Context contexto) {
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen
				.rawQuery("SELECT idintegrante_tablet, primernombre, segundonombre, tercernombre, primerapellido, segundoapellido, numerocorrelativo FROM integrante WHERE idfamilia_tablet="+idfamilia+" ORDER BY numerocorrelativo",null);
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), "       "+cursor.getString(6)+"                    "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	
	public List<SpinnerObject> busquedaFamilia(int numfamilia, Context contexto) {
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen
				.rawQuery("SELECT idfamilia_tablet,direccion FROM familia  WHERE numerofamilia='"+numfamilia+"' ORDER BY direccion",null);
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), "               "+cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	
	public List<String>detalleFamilias(Context contexto) {
		List<String> datos = new ArrayList<String>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT username,password FROM usuarios",null);
		if (cursor.moveToFirst()) {
			do {
				//datos.add(cursor.getString(cursor.getColumnIndex("_id")));
				datos.add(cursor.getString(0));
				datos.add(cursor.getString(1));
				//datos.add(cursor.getString(cursor.getColumnIndex("nombreusuario")));
				Toast.makeText(contexto,cursor.getString(0),Toast.LENGTH_LONG).show();
				Toast.makeText(contexto,cursor.getString(1),Toast.LENGTH_LONG).show();
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return datos;
	}
public List<SpinnerObjectString> getSexo(Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT abreviatura, nombre FROM sexo", null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}

	public List<SpinnerObject> getNacionalidad(Context contexto) {
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT * FROM nacionalidad", null);
		names.add(new SpinnerObject(0, "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getCatalogoDescriptor(int id_descriptor, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by descripcion",null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getEstadoNutricional(int id_descriptor, int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		String query = "";
		
		conectarDB(contexto);
		
		if (edad<2){
		query = "SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor in (44,45,46)  order by descripcion";
		
		}
		if (edad>=2 && edad<5){
			query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor in (337,338,339,340)  order by descripcion";
		}
		if (edad>=5){
			query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=164  order by descripcion";
		}
		
		Cursor cursor = conexOpen.rawQuery(query,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
 
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		
		desConectarDB();
		return names;
	}
	
	/*public List<SpinnerObjectString> getUltimoGradoAprobado(int id_descriptor, String leer_escribir) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		String query = "";
		
		if (leer_escribir.equals("0")){
			query = 
		}else {
			query = "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by descripcion";
		}
		
		Cursor cursor = conexOpen.rawQuery(,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		return names;
	}*/
	public List<SpinnerObjectString> getTrabajoRemunerado(int id_descriptor,int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		String query = "";
		if (edad > 14)
		{
	
		query= "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" AND idvalor <> 449 order by descripcion"; 
		}
		else
		{
		query= "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by descripcion"; 
				
		}
		
		Cursor cursor = conexOpen.rawQuery(query ,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getOcupacion(int id_descriptor,int edad,String id_TrabajoRemunerado, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		String query = "";
		if (edad > 14)
		{
			if(id_TrabajoRemunerado.equals("0")){
				query= "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by descripcion"; 
				//todos incluyendo no aplica, me interesa que en la lista este "no aplica"
			}else{
				query= "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" AND idvalor <> 307 order by descripcion"; 
				//no aplica
			}
		}
		else
		{
			query= "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by descripcion"; 
			//todos incluyendo no aplica, me interesa que en la lista este "no aplica"	
		}
		
		Cursor cursor = conexOpen.rawQuery(query ,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	
	
	public List<SpinnerObjectString> getLeerEscribir(int id_descriptor,int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		String query = "";
		//query= "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" AND idvalor <> 155 order by descripcion"; 
		if (edad > 5)
		{
	
		query= "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" AND idvalor <> 155 order by descripcion"; 
		}
		else
		{
			query= "SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by descripcion"; 
				
		}
		
		Cursor cursor = conexOpen.rawQuery(query ,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	} 
	public List<SpinnerObjectString> getCatalogoUltimoGrado(int id_descriptor,String id_LeerEscribir, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		String query= "SELECT abreviatura,descripcion,idvalor FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by idvalor"; 
		if (id_LeerEscribir.equals("0"))
			{	
				query= "SELECT abreviatura,descripcion,idvalor FROM valordescriptor WHERE idvalor IN(488,487,489,490,491,503,504,505,506,501) order by idvalor"; 
			}
		if (id_LeerEscribir.equals("1"))
			{
				query= "SELECT abreviatura,descripcion,idvalor FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by idvalor"; 		
			}
		if(id_LeerEscribir.equals("NA")){
				query= "SELECT abreviatura,descripcion,idvalor FROM valordescriptor WHERE idvalor IN(488,487,489,503,504,505,506,501) order by idvalor"; 
			}
	 
		Cursor cursor = conexOpen.rawQuery(query ,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getCatalogoMetodoPlanifica(int id_descriptor,String sexo, int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		String query="";
		if(sexo.equals("F")){
			 //metodos de planificacion familiar de mujeres
			if(edad>=10){
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor IN(274,276,277,278,279,280) order by descripcion";
			}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=275 order by descripcion";
			}
			 
		}else{
			if(edad>=10){
				//metodos de planificacion familiar de hombres
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor IN(273,276,277) order by descripcion";
			}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=275 order by descripcion";
			}
			 
		}
		Cursor cursor = conexOpen.rawQuery(query,null);
		
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	} 
	public List<SpinnerObjectString> getCatalogoTomaCitologia(int id_descriptor,String sexo,int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		String query="";
		if(sexo.equals("F")){
			 // si, no
			if(edad>=10){
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor IN(153,154) order by descripcion";
			}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=155 order by descripcion";
			}
		}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=155 order by descripcion";
			}
		Cursor cursor = conexOpen.rawQuery(query,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getCatalogoFormaTrabajoInfantil(String id_Menor14Trabaja, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		String query="";
		
		conectarDB(contexto);
		if(id_Menor14Trabaja.equals("NA") || id_Menor14Trabaja.equals("0")){
			query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=328 order by descripcion";
		}else{
			query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor IN(327,341,342,343,345,346,542) order by descripcion";
		} 
		Cursor cursor = conexOpen.rawQuery(query,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getCatalogoMenor14Trabaja(int id_descriptor,int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		String query="";
		 
		
		conectarDB(contexto);
			if(edad<14){
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor IN(153,154) order by descripcion";
			}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=155 order by descripcion";
			}
		 
		Cursor cursor = conexOpen.rawQuery(query,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getCatalogoMenores18BajoCuidadoDe(int id_descriptor,int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		String query="";
		
		conectarDB(contexto);
			if(edad<18){
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor IN(283,284,309) order by descripcion";
			}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=282 order by descripcion";
			}
		 
		Cursor cursor = conexOpen.rawQuery(query,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}	
	
	public List<SpinnerObjectString> getEmbarazadaActualmente(int id_descriptor,String sexo,int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		String query="";
		
		conectarDB(contexto);
		if(sexo.equals("F")){
			 // si, no
			if(edad>=10){
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor IN(153,154) order by descripcion";
			}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=155 order by descripcion";
			}	 
		}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=155 order by descripcion";
			}
		Cursor cursor = conexOpen.rawQuery(query,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getCatalogoExamenMamas(int id_descriptor,String sexo,int edad, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		String query="";
		
		conectarDB(contexto);
		if(sexo.equals("F")){
			 // si, no
			if(edad>=10){
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor IN(153,154) order by descripcion";
			}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=155 order by descripcion";
			}	 
		}else{
				//no aplica
				query="SELECT abreviatura,descripcion FROM valordescriptor WHERE idvalor=155 order by descripcion";
			}
		Cursor cursor = conexOpen.rawQuery(query,null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObjectString> getCatalogoDescriptorDispensarizacion(int id_descriptor, Context contexto) {
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT abreviatura,descripcion,abreviatura FROM valordescriptor WHERE id_descriptor="+id_descriptor+" order by abreviatura",null);
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(2)+"  "+cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	
	public String getNameEstablecimiento(int id_estasib_user_sp, Context contexto){
		String nameEstablecimiento="";
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT estasib FROM estasib WHERE idestasib="+id_estasib_user_sp,null);
		if (cursor.moveToFirst()) {
			do {
				nameEstablecimiento = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return nameEstablecimiento;
		
	}
	public String getNumeroCorrelativoIntegrante(int id_integrante, Context contexto){
		String numero_correlativo="";//saber si es jefe de familia
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT numerocorrelativo FROM integrante WHERE idintegrante_tablet="+id_integrante,null);
		if (cursor.moveToFirst()) {
			do {
				numero_correlativo = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return numero_correlativo;
		
	}
	public String getSexoIntegrante(int id_integrante, Context contexto){
		conectarDB(contexto);
		
		Cursor cursor = conexOpen.rawQuery("SELECT sexo FROM integrante WHERE idintegrante_tablet="+id_integrante,null);
		if (cursor.moveToFirst()) {
			do {
				sexo = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return sexo;
		
	}
	public String getNameCodigoEcosf(int id_estasib_user_sp, Context contexto){
		String nameCodigoEcosf="";
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT codigoucsf FROM estasib WHERE idestasib="+id_estasib_user_sp,null);
		if (cursor.moveToFirst()) {
			do {
				nameCodigoEcosf = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return nameCodigoEcosf;
		
	}
	public String getCanton(int idfamilia, Context contexto){
		String colonia_canton="";
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT  cantones.canton " +
												 " FROM  familia " +
												 " INNER JOIN cantones  ON familia.codigo_canton=cantones.idcanton " +
												 " WHERE idfamilia_tablet="+idfamilia,null);
		if (cursor.moveToFirst()) {
			do {
				colonia_canton = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return colonia_canton;
		
	}
	public String getColonia(int idfamilia, Context contexto){
		String colonia_canton="";
		
		conectarDB(contexto);
		Cursor cursor = conexOpen.rawQuery("SELECT  colonias.nombrecolonia" +
												 " FROM  familia " +
												 " INNER JOIN colonias  ON familia.codigo_colonia=colonias.idcolonia " +
												 " WHERE idfamilia_tablet="+idfamilia,null);
		if (cursor.moveToFirst()) {
			do {
				colonia_canton = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return colonia_canton;
		
	}
	//buscar jefes de familia
	public ArrayList<SpinnerObject> getJefesFamilias(String Pnombre,String Snombre,String Papellido,String Sapellido,int idestasib, Context contexto) {
		conectarDB(contexto);
		
		String complementoPNombre=" ";
		String complementoSNombre=" ";
		String complementoPApellido=" ";
		String complementoSApellido=" ";
		String colonia_canton="";
		if(Pnombre.equals("")){
			//no pasa nada
		}else{
			complementoPNombre=" integrante.primernombre    LIKE '%"+Pnombre+"%' AND ";
		}
		
		if(Snombre.equals("")){
			//no pasa nada
		}else {
			complementoSNombre=" integrante.segundonombre   LIKE '%"+Snombre+"%' AND ";
		}
		
		if(Papellido.equals("")){
			//no pasa nada
		}else{
			complementoPApellido=" integrante.primerapellido LIKE '%"+Papellido+"%' AND ";
		}
		
		if(Sapellido.equals("")){
			//no pasa nada
		}else{
			complementoSApellido=" integrante.segundoapellido LIKE '%"+Sapellido+"%' AND ";	
		}
		
		ArrayList<SpinnerObject> names = new ArrayList<SpinnerObject>();
		/*Log.i("query","SELECT " +
				" integrante._id as idintegrante,"+
				" integrante.primernombre,"+
				" integrante.segundonombre,"+
				" integrante.primerapellido,"+
				" integrante.segundoapellido"+
		  " FROM  integrante" +
				" INNER JOIN integrante_familia  ON integrante_familia.id_integrante=integrante._id " +
				" INNER JOIN familia  ON familia._id=integrante_familia.id_familia " +
		  " WHERE "+complementoPNombre+
		  			complementoSNombre+
				    complementoPApellido+
				    complementoSApellido+
		  		" numero_correlativo='01' AND familia.id_estasib="+idestasib);*/
		
		Cursor cursor = conexOpen
			  .rawQuery("SELECT " +
								" integrante.idintegrante_tablet as idintegrante,"+
								" integrante.primernombre,"+
								" integrante.segundonombre,"+
								" integrante.primerapellido,"+
								" integrante.segundoapellido,"+
								" municipios.nombremunicipio," +
								" area.nombrearea,"+
								" area.codigoarea,"+
								" familia.codigo_zona,"+
								" familia.numerovivienda,"+
								" familia.numerofamilia,"+
								" familia.idfamilia_tablet," +
								" familia.direccion"+
						  " FROM  integrante" +
								" INNER JOIN familia  ON familia.idfamilia_tablet=integrante.idfamilia_tablet"+
								" INNER JOIN municipios  ON familia.codigo_municipio=municipios.idmunicipio"+
								" INNER JOIN area  ON familia.codigo_area=area.codigoarea"+
						  " WHERE "+complementoPNombre+
						  			complementoSNombre+
								    complementoPApellido+
								    complementoSApellido+
						  		" (numerocorrelativo='01' OR numerocorrelativo='01') AND familia.id_estasib="+idestasib+
						  		"  ORDER by integrante.primernombre, integrante.segundonombre, integrante.primerapellido, integrante.segundoapellido",null);
		if (cursor.moveToFirst()) {
			do {
				if(cursor.getString(7).equals("R"))
				{
					colonia_canton=getCanton(cursor.getInt(11),contexto);
				}else{
					colonia_canton=getColonia(cursor.getInt(11),contexto);
				}
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4),
						cursor.getString(5)+"-"+cursor.getString(6)+"-"+colonia_canton+"-"+cursor.getString(8)+"-"+cursor.getString(9)+"-"+cursor.getString(10),
						cursor.getString(12)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	//buscar cantidad jefes de familia
		public int getCountJefesFamilias(String Pnombre,String Snombre,String Papellido,String Sapellido,int idestasib, Context contexto) {
			conectarDB(contexto);
			
			String complementoPNombre=" ";
			String complementoSNombre=" ";
			String complementoPApellido=" ";
			String complementoSApellido=" ";
			String colonia_canton="";
			int cantidadReg=0;
			if(Pnombre.equals("")){
				//no pasa nada
			}else{
				complementoPNombre=" integrante.primernombre    LIKE '%"+Pnombre+"%' AND ";
			}
			
			if(Snombre.equals("")){
				//no pasa nada
			}else {
				complementoSNombre=" integrante.segundonombre   LIKE '%"+Snombre+"%' AND ";
			}
			
			if(Papellido.equals("")){
				//no pasa nada
			}else{
				complementoPApellido=" integrante.primerapellido LIKE '%"+Papellido+"%' AND ";
			}
			
			if(Sapellido.equals("")){
				//no pasa nada
			}else{
				complementoSApellido=" integrante.segundoapellido LIKE '%"+Sapellido+"%' AND ";	
			}
		 
			Cursor cursor = conexOpen
				  .rawQuery("SELECT  count(*) as cantidad FROM  integrante" +
									" INNER JOIN familia  ON familia.idfamilia_tablet=integrante.idfamilia_tablet"+
									" INNER JOIN municipios  ON familia.codigo_municipio=municipios.idmunicipio"+
									" INNER JOIN area  ON familia.codigo_area=area.codigoarea"+
							  " WHERE "+complementoPNombre+
							  			complementoSNombre+
									    complementoPApellido+
									    complementoSApellido+
							  		" (numerocorrelativo='01' OR numerocorrelativo='01') AND familia.id_estasib="+idestasib+
							  		"  ORDER by integrante.primernombre, integrante.segundonombre, integrante.primerapellido, integrante.segundoapellido",null);
			if (cursor.moveToFirst()) {
				do {
					cantidadReg=cursor.getInt(0);
				} while (cursor.moveToNext());
			}
			
			desConectarDB();
			return cantidadReg;
		}
	public List<SpinnerObject> getFamiliaJefe(int idfamilia, Context contexto) {
		conectarDB(contexto);
		
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
			Cursor cursor = conexOpen
			  .rawQuery(" SELECT idintegrante_tablet,  " +
			  					" (primernombre   ||' '|| segundonombre  ||' '||  primerapellido ||' '||   segundoapellido) AS nombre " +
			  					" FROM  integrante" +
			  					" INNER JOIN familia ON familia.idfamilia_tablet = integrante.idfamilia_tablet " +
			  					" WHERE familia.idfamilia_tablet= "+idfamilia+ " AND integrante.estado=1 ORDER BY numerocorrelativo ASC", null);
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	public List<SpinnerObject> getFamiliaFallecidos(int idfamilia, Context contexto) {
		conectarDB(contexto);
		
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		String flag="";
			Cursor cursor = conexOpen
			  .rawQuery(" SELECT idintegrante_tablet,  " +
			  					" (primernombre   ||' '|| segundonombre  ||' '||  primerapellido ||' '||   segundoapellido) AS nombre " +
			  					" FROM  integrante" +
			  					" INNER JOIN familia ON familia.idfamilia_tablet = integrante.idfamilia_tablet " +
			  					" WHERE familia.idfamilia_tablet= "+idfamilia+ " AND integrante.estado=2  ORDER BY numerocorrelativo ASC", null);
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
				flag=cursor.getString(1);
				if(flag.equals("")){
					names.add(new SpinnerObject(0, "No hay fallecidos en esta familia"));
				}
			} while (cursor.moveToNext());
			
		}
		
		desConectarDB();
		return names;
	}
//saber a que familia pertenece una persona
	public void  getIdFamilia(int idMiembroFamilia, Context contexto){
		conectarDB(contexto);
		
		Cursor c = conexOpen.rawQuery("	SELECT familia.idfamilia_tablet, " +
										" direccion " +
									" FROM integrante  " +
									" INNER JOIN familia  ON familia.idfamilia_tablet=integrante.idfamilia_tablet " +
									" WHERE idintegrante_tablet="+idMiembroFamilia, null);
		if (c.moveToFirst()) {	  
			do {
				this.idfamilia= c.getInt(0);
				this.direccionFamilia=c.getString(1);
			} while (c.moveToNext());
		}
		
		desConectarDB();
		//Log.i("direccionfamilia",this.direccioFamilia);
	}
//Obtiene id del departamento, id del municipio de un usuario
public void  getDeptoMunicipioUser(int idUser, Context contexto){
	conectarDB(contexto);
	
			Cursor c = conexOpen.rawQuery("SELECT departamentoid,idmunicipio FROM departamento " +
											" INNER JOIN municipios  ON departamento.departamentoid=municipios.dpto_id " +
											" INNER JOIN estasib     ON municipios.idmunicipio=estasib.id_mun " +
											" INNER JOIN usuario     ON estasib.idestasib=id_estasib " +
											" WHERE idusuario_tablet="+idUser, null);
			if (c.moveToFirst()) {	  
				do {
					this.codigo_departamento= c.getInt(0);
					this.codigo_municipio=c.getInt(1);
					
				} while (c.moveToNext());
			}
			
		desConectarDB();	
			//Log.i("direccionfamilia",this.direccioFamilia);
		}
	public void  getIdFamilia_direccion(int depto, int municipio, String area, int ctn_bar_col, String zona, String num_vivienda, String  num_familia, Context contexto){
		conectarDB(contexto);
		
		String complemento="";
		if (area.equals("U")){
			complemento = "codigo_colonia";
		} else {
			complemento = "codigo_canton";
		}//Log.i("busqueda",q);
		Cursor c = conexOpen.rawQuery("SELECT idfamilia_tablet, " +
											" direccion " +
										" FROM familia " +
										" WHERE codigo_departamento="+depto+ 
										" AND   codigo_municipio="+municipio+
										" AND   codigo_area='"+area+"'"+
										" AND   "+complemento+"="+ctn_bar_col+
										" AND   codigo_zona='"+zona+"'"+
										" AND   numerovivienda="+num_vivienda+
										" AND   numerofamilia="+num_familia, null);
		if (c.moveToFirst()) {	  
			do {
				this.idfamilia= c.getInt(0);
				this.idfamiliaBuscar= c.getInt(0);
				this.direccionFamilia=c.getString(1);
			} while (c.moveToNext());
		}
	desConectarDB();	
	}
 
public int existeNumExpediente(int depto, int municipio, String area, int ctn_bar_col, String zona, String num_vivienda, String  num_familia, Context contexto) {
	conectarDB(contexto);
	
	String complemento="";
				if (area.equals("U")){
					complemento = "codigo_colonia";
				} else {
					complemento = "codigo_canton";
				} 
					int exixte=0;
					int cuenta=0;
					String query_="SELECT COUNT(idfamilia_tablet) as cuenta "+
							" FROM familia " +
							" WHERE codigo_departamento="+depto+ 
							" AND   codigo_municipio="+municipio+
							" AND   codigo_area='"+area+"'"+
							" AND   "+complemento+"="+ctn_bar_col+
							" AND   codigo_zona='"+zona+"'"+
							" AND   numerovivienda="+num_vivienda+
							" AND   numerofamilia="+num_familia;
							Cursor cursor = conexOpen.rawQuery(query_, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				exixte=1;
			}
		desConectarDB();	
		return exixte;
		
	}	
public void getCaracteristicasViviendaInfoEdit(int idfamilia, Context contexto){
	conectarDB(contexto);
	
			String queryGetInfoFam="SELECT " +
					   " codigo_departamento, " +
					   " codigo_municipio," +
					   " codigo_area," +
					   " codigo_canton," +
					   " codigo_colonia," +
					   " codigo_zona," +
					   " numerovivienda," +
					   " numerofamilia," +
					   " fechaintroduccion," +
					   " direccion," +
					   " codigo_sit_vivienda," +
					   " codigo_religion," +
					   " codigo_puebloindigena," +
					   " codigo_tipofamilia," +
					   " tipo_riesgofamiliar" +
				" FROM   familia" +
				" WHERE idfamilia_tablet="+idfamilia;
			Cursor cursor = conexOpen.rawQuery(queryGetInfoFam, null);
			if (cursor.moveToFirst()) {	  
				do {
						this.codigo_departamento=cursor.getInt(0);
						this.codigo_municipio=cursor.getInt(1);
						this.codigo_area=cursor.getString(2);
						this.codigo_canton=cursor.getInt(3);
						this.codigo_colonia=cursor.getInt(4);
						this.codigo_zona=cursor.getString(5);
						this.numerovivienda=cursor.getInt(6);
						this.numerofamilia=cursor.getInt(7);
						this.fechaintroduccion=cursor.getString(8);
						this.direccionFamilia=cursor.getString(9);
						this.codigo_sit_vivienda=cursor.getString(10);
						this.codigo_religion=cursor.getString(11);
						this.codigo_puebloindigena=cursor.getString(12);
						this.codigo_tipofamilia=cursor.getString(13);
						this.tipo_riesgofamiliar=cursor.getInt(14);
						//this.s_latitud=cursor.getString(15);
						//this.s_longitud=cursor.getString(16);
					} while (cursor.moveToNext());
			}
			desConectarDB();
}
 
public void getNombre_Edad_Integrante(int idintegrante, Context contexto){
	conectarDB(contexto);
	
	String pn,sn,tn,pa,sa,ac,fechaNac,S_sexo;
	String queryGetPassUsuario="SELECT primernombre, segundonombre,tercernombre,primerapellido,segundoapellido,apellidocasada,fechanacimiento,sexo FROM integrante where idintegrante_tablet="+idintegrante ;
	Cursor cursor = conexOpen.rawQuery(queryGetPassUsuario, null);
	if (cursor.moveToFirst()) {	  
		do {
			if(cursor.getString(0) == null){
				pn="";
			}else{
				pn=cursor.getString(0)+" ";
			}
			if(cursor.getString(1) == null){
				sn="";
			}else{
				sn=cursor.getString(1)+" ";
			}
			if(cursor.getString(2) == null){
				tn="";
			}else{
				tn=cursor.getString(2)+" ";
			}
			if(cursor.getString(3) == null){
				pa="";
			}else{
				pa=cursor.getString(3)+" ";
			}
			if(cursor.getString(4) == null){
				sa="";
			}else{
				sa=cursor.getString(4)+" ";
			}
			if(cursor.getString(5) == null){
				ac="";
			}else{
				ac=cursor.getString(5)+" ";
			}
			if(cursor.getString(6) == null){
				fechaNac="";
			}else{
				fechaNac=cursor.getString(6);
			}
			if(cursor.getString(7) == null){
				S_sexo="";
			}else{
				S_sexo=cursor.getString(7);
			}
			
			this.IntegranteNombreApellidoConcatenado=pn+sn+tn+pa+sa+ac;
			this.IntegranteFechaNac=fechaNac;
			this.sexo=S_sexo;
		} while (cursor.moveToNext());
	}
	desConectarDB();
 }	
public String  getFechaNacIntegrnate(int idintegrante, Context contexto){
	conectarDB(contexto);
	
	String fechaNac="";
	String qfechaNac="SELECT fechanacimiento FROM integrante where idintegrante_tablet="+idintegrante ;
	Cursor cursor = conexOpen.rawQuery(qfechaNac, null);
	if (cursor.moveToFirst()) {	  
		do {
			fechaNac=cursor.getString(0);
		} while (cursor.moveToNext());
	}
	
	desConectarDB();
	return fechaNac;
 }	
	
public void getFamiliaInfoEdit(int idfamilia, Context contexto){
	conectarDB(contexto);
	
	String queryGetInfoFam="SELECT " +
								   " codigo_departamento, " +
								   " codigo_municipio," +
								   " codigo_area," +
								   " codigo_canton," +
								   " codigo_colonia," +
								   " codigo_zona," +
								   " numerovivienda," +
								   " numerofamilia," +
								   " fechaintroduccion," +
								   " direccion," +
								   " codigo_sit_vivienda," +
								   " codigo_religion," +
								   " codigo_puebloindigena," +
								   " codigo_tipofamilia," +
								   " tipo_riesgofamiliar," +
								   " latitud_vivienda," +
								   " longitud_vivienda " +
							" FROM   familia" +
							" WHERE idfamilia_tablet="+idfamilia;
	Cursor cursor = conexOpen.rawQuery(queryGetInfoFam, null);
	if (cursor.moveToFirst()) {	  
		do {
			this.codigo_departamento=cursor.getInt(0);
			this.codigo_municipio=cursor.getInt(1);
			this.codigo_area=cursor.getString(2);
			this.codigo_canton=cursor.getInt(3);
			this.codigo_colonia=cursor.getInt(4);
			this.codigo_zona=cursor.getString(5);
			this.numerovivienda=cursor.getInt(6);
			this.numerofamilia=cursor.getInt(7);
			this.fechaintroduccion=cursor.getString(8);
			this.direccionFamilia=cursor.getString(9);
			this.codigo_sit_vivienda=cursor.getString(10);
			this.codigo_religion=cursor.getString(11);
			this.codigo_puebloindigena=cursor.getString(12);
			this.codigo_tipofamilia=cursor.getString(13);
			this.tipo_riesgofamiliar=cursor.getInt(14);
			this.s_latitud=cursor.getString(15);
			this.s_longitud=cursor.getString(16);
		} while (cursor.moveToNext());
	}
	
	desConectarDB();
 }
	public String getValorVariableSelecionado(int idfamilia,int idvariable, Context contexto){
		conectarDB(contexto);
		
		String valor="";
		String query_fam_variable_valor="SELECT idfamiliavariable_tablet," +
											"   id_variable," +
											"   valor " +
											" FROM 	familia_variable " +
											" WHERE id_familia_tablet="+idfamilia+
											" AND id_variable="+idvariable;
		Cursor cursor = conexOpen.rawQuery(query_fam_variable_valor, null);
			if (cursor.moveToFirst()) {	  
				do {
					valor=cursor.getString(2);
				} while (cursor.moveToNext());
			}
		
		desConectarDB();	
		return valor;
		//return "3";
	}
	public String getValorIntegranteSelecionado(int idintegrante,int idvariable, Context contexto){
		conectarDB(contexto);
		
		String valor="";
		/*Log.i("query:  ", "SELECT idintegrantevariable_tablet," +
				"   id_variable," +
				"   valor " +
				" FROM 	integrante_variable " +
				" WHERE idintegrante_tablet="+idintegrante+" and id_variable="+idvariable);*/
		String query_fam_variable_valor="SELECT idintegrantevariable_tablet," +
											"   id_variable," +
											"   valor " +
											" FROM 	integrante_variable " +
											" WHERE idintegrante_tablet="+idintegrante+" and id_variable="+idvariable;
		Cursor cursor = conexOpen.rawQuery(query_fam_variable_valor, null);
		if (cursor.moveToFirst()) {	  
			do {
				 
					valor=cursor.getString(2);	
					//Log.i("valor: ", valor+" variable: "+idvariable);
				
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return valor;
	}
	public int vivenda_on_off(int idfamilia, Context contexto) {
		conectarDB(contexto);
		
		int cuenta=0; 
		int vivienda_icon=0;
		String query_on_off="SELECT COUNT(idfamiliavariable_tablet) as cuenta " +
									" FROM familia_variable " +
									" WHERE id_familia_tablet=" +idfamilia+""+
									" AND id_variable=83";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				vivienda_icon=1;
			}
			
		desConectarDB();	
		return vivienda_icon;
	}
	public int variables_completas(String variables,int idfamilia,int cantidad_variables,Context contexto) {
		conectarDB(contexto);
		
		int cuenta=0; 
		String query_on_off=" SELECT COUNT(idfamiliavariable_tablet) AS cuenta" +
								" FROM familia_variable " +
								" WHERE id_variable " +
								" IN ("+variables+") AND id_familia_tablet="+idfamilia;
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
								do {
										cuenta=cursor.getInt(0);
									}
								while (cursor.moveToNext());
							}
			desConectarDB();				
			if(cantidad_variables==cuenta){
				return 1;
			}else{
				return 0;
				}	
			
	}
	public int variables_completas_integrante(String variables,int idintegrante,int cantidad_variables, Context contexto) {
		conectarDB(contexto);
		int cuenta=0; 
		String query_on_off=" SELECT COUNT(idintegrantevariable_tablet) AS cuenta" +
								" FROM integrante_variable " +
								" WHERE id_variable " +
								" IN ("+variables+") AND idintegrante_tablet="+idintegrante;
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
								do {
										cuenta=cursor.getInt(0);
										//Log.i("cuenta", ""+cuenta);
									}
								while (cursor.moveToNext());
							}
			desConectarDB();				
			if(cantidad_variables==cuenta){
				return 1;
			}else{
				return 0;
				}	
			}
	public int patrimonio_on_off(int idfamilia, Context contexto) {
		conectarDB(contexto);
		int cuenta=0;
		int patrimonio_icon=0;
		String query_on_off="SELECT COUNT(idfamiliavariable_tablet) as cuenta " +
									" FROM familia_variable " +
									" WHERE id_familia_tablet=" +idfamilia+""+
									" AND id_variable=57";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				patrimonio_icon=1;
			}
		desConectarDB();
		return patrimonio_icon;
		
	} 
	public int amenazas_on_off(int idfamilia, Context contexto) {
		conectarDB(contexto);
				
		int amenazas_icon=0;
		int cuenta=0;
		String query_on_off="SELECT COUNT(idfamiliavariable_tablet) as cuenta " +
									" FROM familia_variable " +
									" WHERE id_familia_tablet=" +idfamilia+""+
									" AND id_variable=98";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				amenazas_icon=1;
			}
		desConectarDB();
		return amenazas_icon;
		
	}
 
	public int servicios_basicos_on_off(int idfamilia, Context contexto) {
		conectarDB(contexto);
		
		int servicios_icon=0;
		int cuenta=0;
		String query_on_off="SELECT COUNT(idfamiliavariable_tablet) as cuenta " +
									" FROM familia_variable " +
									" WHERE id_familia_tablet=" +idfamilia+""+
									" AND id_variable=100";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				servicios_icon=1;
			}
		desConectarDB();	
		return servicios_icon;
		
	}
	public int desechos_on_off(int idfamilia, Context contexto) {
		conectarDB(contexto);
		
		int desechos_icon=0;
		int cuenta=0;
		String query_on_off="SELECT COUNT(idfamiliavariable_tablet) as cuenta " +
									" FROM familia_variable " +
									" WHERE id_familia_tablet=" +idfamilia+""+
									" AND id_variable=38";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				desechos_icon=1;
			}
			desConectarDB();
			return desechos_icon;
	}
	public int vectores_on_off(int idfamilia, Context contexto) {
		conectarDB(contexto);
		
		int vectores_icon=0;
		int cuenta=0;
		String query_on_off="SELECT COUNT(idfamiliavariable_tablet) as cuenta " +
									" FROM familia_variable " +
									" WHERE id_familia_tablet=" +idfamilia+""+
									" AND id_variable=107";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				vectores_icon=1;
			}
		desConectarDB();	
		return vectores_icon;
		
	}
	public int mascotas_on_off(int idfamilia, Context contexto) {
		conectarDB(contexto);
		
		int mascotas_icon=0;
		int cuenta=0;
		String query_on_off="SELECT COUNT(idfamiliavariable_tablet) as cuenta " +
									" FROM familia_variable " +
									" WHERE id_familia_tablet=" +idfamilia+""+
									" AND id_variable=43";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				mascotas_icon=1;
			}
		desConectarDB();	
		return mascotas_icon;
		
	}public int riesgoFamilia_on_off(int idfamilia, Context contexto) {
		conectarDB(contexto);
		
		int mascotas_icon=0;
		int cuenta=0;
		String query_on_off="SELECT COUNT(idfamiliavariable_tablet) as cuenta " +
									" FROM familia_variable " +
									" WHERE id_familia_tablet=" +idfamilia+""+
									" AND id_variable=50";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				mascotas_icon=1;
			}
		desConectarDB();	
		return mascotas_icon;
		
	}
	//ICONOS INTEGRANTE
	public int educacion_on_off(int idintegrante, Context contexto) {
		conectarDB(contexto);
		
		int cuenta=0;
		int educacion_icon=0;
		String query_on_off="SELECT COUNT(idintegrantevariable_tablet) as cuenta " +
							" FROM integrante_variable " +
							" WHERE idintegrante_tablet="+idintegrante+ "" +
						    " AND id_variable=3";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				educacion_icon=1;
			}
		desConectarDB();	
		return educacion_icon;
	}
	public int economico_on_off(int idintegrante, Context contexto) {
		conectarDB(contexto);
		
		int cuenta=0;
		int economico_icon=0;
		
		
		String query_on_off="SELECT COUNT(idintegrantevariable_tablet) as cuenta " +
							" FROM integrante_variable " +
							" WHERE idintegrante_tablet="+idintegrante+ "" +
						    " AND id_variable=112";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				economico_icon=1;
			}
			
		desConectarDB();	
		return economico_icon;
	}
	public int salud_on_off(int idintegrante, Context contexto) {
		conectarDB(contexto);
		
		int cuenta=0;
		int salud_icon=0;
		String query_on_off="SELECT COUNT(idintegrantevariable_tablet) as cuenta " +
							" FROM integrante_variable " +
							" WHERE idintegrante_tablet="+idintegrante+ "" +
						    " AND id_variable=86";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				salud_icon=1;
			}
		desConectarDB();	
		return salud_icon;
	}
	public int habitos_on_off(int idintegrante, Context contexto) {
		conectarDB(contexto);
		
		int cuenta=0;
		int habitos_icon=0;
		String query_on_off="SELECT COUNT(idintegrantevariable_tablet) as cuenta " +
							" FROM integrante_variable " +
							" WHERE idintegrante_tablet="+idintegrante+ "" +
						    " AND id_variable=11";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				habitos_icon=1;
			}
		desConectarDB();	
		return habitos_icon;
	}
	public int salud_sexual_on_off(int idintegrante, Context contexto) {
		conectarDB(contexto);
		
		int cuenta=0;
		int salud_sexual_icon=0;
		String query_on_off="SELECT COUNT(idintegrantevariable_tablet) as cuenta " +
							" FROM integrante_variable " +
							" WHERE idintegrante_tablet="+idintegrante+ "" +
						    " AND id_variable=87";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				salud_sexual_icon=1;
			}
			
		desConectarDB();	
		return salud_sexual_icon;
	}
	public int otras_variables_on_off(int idintegrante, Context contexto) {
		conectarDB(contexto);
		
		int cuenta=0;
		int otras_variables_icon=0;
		String query_on_off="SELECT COUNT(idintegrantevariable_tablet) as cuenta " +
							" FROM integrante_variable " +
							" WHERE idintegrante_tablet="+idintegrante+ "" +
						    " AND id_variable=78";
							Cursor cursor = conexOpen.rawQuery(query_on_off, null);
							if (cursor.moveToFirst()) {	  
							do {
								cuenta=cursor.getInt(0);
							} while (cursor.moveToNext());
							}
			if(cuenta>0){
				otras_variables_icon=1;
			}
		
		desConectarDB();
		return otras_variables_icon;
	}
	//---------------------------------------MIS FUNCIONES--------------------------------------------
	public List<SpinnerObject> getTipoUsuario(Context contexto) {
		conectarDB(contexto);
		
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		Cursor cursor = conexOpen.rawQuery("SELECT idperfilusuario, nombreperfil FROM perfilusuario where idperfilusuario=8 or idperfilusuario=9", null);
		names.add(new SpinnerObject(0, "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		desConectarDB();
		return names;
	}
	
	public List<SpinnerObject> getRegion(Context contexto) {
		conectarDB(contexto);
		
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		Cursor cursor = conexOpen.rawQuery("SELECT idregion, nombre FROM region", null);
		names.add(new SpinnerObject(0, "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	
	public List<SpinnerObject> getSiBasiXRegion(int id_region, Context contexto) {
		conectarDB(contexto);
		
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		Cursor cursor = conexOpen.rawQuery(
				"SELECT idsibasi,nomsibasi from sibasis where id_region='"+ id_region + "' order by nomsibasi", null);
		names.add(new SpinnerObject(0, "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	
	public List<SpinnerObject> getEstablecimientoXSiBasi(int id_sibasi, Context contexto){
		conectarDB(contexto);
		
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		Cursor cursor = conexOpen.rawQuery(
				"SELECT idestasib,estasib from estasib where sibasi='"+ id_sibasi + "' order by estasib", null);
		names.add(new SpinnerObject(0, "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	
	
	public void insertUsuario(String codigo_usuariobd, int id_tipo_usuariobd, String nombre_usuariobd, String clavebd, int activobd, int id_regionbd, int id_sibasibd, int id_establecimientobd, int tabletbd, Context contexto){
		conectarDB(contexto);
		
		ContentValues newValues = new ContentValues(); 
		newValues.put("codigousuario", codigo_usuariobd);
		newValues.put("codigo_perfil", id_tipo_usuariobd);
		newValues.put("nombreusuario", nombre_usuariobd);
		newValues.put("claveusuario", clavebd);
		newValues.put("estaactivo", activobd);
		newValues.put("id_region", id_regionbd);
		newValues.put("id_sibasi", id_sibasibd);
		newValues.put("id_estasib", id_establecimientobd);
		newValues.put("correlativo_tablet", tabletbd);		
		conexOpen.insert("usuario", null, newValues);
		
		desConectarDB();
	}
	
	public void updateUsuario(String codigo_usuariobd, int id_tipo_usuariobd, String nombre_usuariobd, int activobd, int id_regionbd, int id_sibasibd, int id_establecimientobd, int tabletbd, int id_usuario_tablet, Context contexto){
		conectarDB(contexto);
		
		ContentValues newValues = new ContentValues(); 
		newValues.put("codigousuario", codigo_usuariobd);
		newValues.put("codigo_perfil", id_tipo_usuariobd);
		newValues.put("nombreusuario", nombre_usuariobd);		
		newValues.put("estaactivo", activobd);
		newValues.put("id_region", id_regionbd);
		newValues.put("id_sibasi", id_sibasibd);
		newValues.put("id_estasib", id_establecimientobd);
		newValues.put("correlativo_tablet", tabletbd);		
		conexOpen.update("usuario", newValues, "idusuario_tablet="+id_usuario_tablet, null);
		
		desConectarDB();
		
	}
	
	public void deleteUsuario(int id_usuario_tablet, Context contexto)
	{	conectarDB(contexto);
		conexOpen.delete("usuario","idusuario_tablet="+id_usuario_tablet, null);
		desConectarDB();
	}	
	
	public String claveusuario;
	public int id_estasib;
	public void getUsuarioInfoEdit(int id_usuario_tablet, Context contexto){
		conectarDB(contexto);
		
		String queryGetInfoUsuario="SELECT " +
									   " codigousuario, " +
									   " codigo_perfil," +
									   " nombreusuario," +
									   " claveusuario," +
									   " estaactivo," +
									   " id_region," +
									   " id_sibasi," +
									   " id_estasib," +
									   " correlativo_tablet" +									   
								" FROM   usuario" +
								" WHERE idusuario_tablet="+id_usuario_tablet;
		//Log.i("queryusuario",queryGetInfoUsuario);
		Cursor cursor = conexOpen.rawQuery(queryGetInfoUsuario, null);
		if (cursor.moveToFirst()) {	  
			do {
				this.codigousuario=cursor.getString(0);
				this.codigo_perfil=cursor.getInt(1);
				this.nombreusuario=cursor.getString(2);
				this.claveusuario=cursor.getString(3);
				this.estaactivo=cursor.getInt(4);
				this.id_region=cursor.getInt(5);
				this.id_sibasi=cursor.getInt(6);
				this.id_estasib=cursor.getInt(7);
				this.correlativo_tablet=cursor.getInt(8);				
			} while (cursor.moveToNext());
		}
		desConectarDB();
	 }
	public List<SpinnerObject> getUsuarios(Context contexto) {
		conectarDB(contexto);
		
		List<SpinnerObject> names = new ArrayList<SpinnerObject>();
		
		Cursor cursor = conexOpen
			  .rawQuery("SELECT * from usuario where codigo_perfil<>7",null);
 
		if (cursor.moveToFirst()) 
		{
			do 
			{
				names.add(new SpinnerObject(cursor.getInt(0), cursor.getString(4)));
	 
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
	}
	
	
	//mis funciones 2---------------------------------------------------------------------------------------------------------------------
	public void getUsuarioInfoEditPassword(int id_usuario_tablet, Context contexto){
		conectarDB(contexto);
		
		String queryGetPassUsuario="SELECT " +
									   " claveusuario" +										   									  
								" FROM   usuario" +
								" WHERE idusuario_tablet="+id_usuario_tablet;
		//Log.i("querypassusuario",queryGetPassUsuario);
		Cursor cursor = conexOpen.rawQuery(queryGetPassUsuario, null);
		if (cursor.moveToFirst()) {	  
			do {
				this.claveusuario=cursor.getString(0);									
			} while (cursor.moveToNext());
		}
		desConectarDB();
	 }
	
	public void updatePassUsuario(String clave, int id_usuario_tablet, Context contexto){
		conectarDB(contexto);
		
		ContentValues newValues = new ContentValues(); 
		newValues.put("claveusuario", clave);					
		conexOpen.update("usuario", newValues, "idusuario_tablet="+id_usuario_tablet, null);
		
		desConectarDB();
	}
	
	public int getIfUsuarioExist(String codigousuario, Context contexto){
			conectarDB(contexto);
		
			int cuenta=0;
			int numero_usuarios=0;
			String queryGetUsuario="SELECT COUNT(codigousuario) as cuenta FROM usuario  WHERE codigousuario='"+codigousuario+"'";
			//Log.i("querypassusuario",queryGetUsuario);
			Cursor cursor = conexOpen.rawQuery(queryGetUsuario, null);
			if (cursor.moveToFirst()) {	  
				do {
					cuenta=cursor.getInt(0);								
				} while (cursor.moveToNext());
			}
			
			if(cuenta>0){
				numero_usuarios=1;
			}
			
			desConectarDB();
			return numero_usuarios;				
	 }
	
	public int getIfUsuarioExistEdit(String codigousuario, int id_usuario_tablet, Context contexto){
		conectarDB(contexto);
		
		int cuenta=0;
		int numero_usuarios=0;
		String queryGetUsuario="SELECT COUNT(codigousuario) as cuenta FROM usuario  WHERE codigousuario='"+codigousuario+"' and idusuario_tablet<>"+id_usuario_tablet+"";
		Log.i("querypassusuario",queryGetUsuario);
		Cursor cursor = conexOpen.rawQuery(queryGetUsuario, null);
		if (cursor.moveToFirst()) {	  
			do {
				cuenta=cursor.getInt(0);								
			} while (cursor.moveToNext());
		}
		
		if(cuenta>0){
			numero_usuarios=1;
		}
		
		desConectarDB();
		return numero_usuarios;				
 }

 public List<SpinnerObjectString> getCatalogoDescriptorSinJefe(int id_descriptor, Context contexto) {
	 	conectarDB(contexto);
	 
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();		
		Cursor cursor = conexOpen.rawQuery("SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" and abreviatura<>'1' order by descripcion",null);		
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
}
 public List<SpinnerObjectString> getCatalogoDescriptorSinNoAplica(int id_descriptor, Context contexto){
	 	conectarDB(contexto);
	 
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();		
		Cursor cursor = conexOpen.rawQuery("SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" and abreviatura<>'0' order by descripcion",null);		
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
} 
 
 public List<SpinnerObjectString> getCatalogoDescriptorSinNada(int id_descriptor, Context contexto){
	 	conectarDB(contexto);
	 
		List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();		
		Cursor cursor = conexOpen.rawQuery("SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" and abreviatura='99' order by descripcion",null);
		Log.i("querypassusuario","SELECT abreviatura,descripcion FROM valordescriptor WHERE id_descriptor="+id_descriptor+" and abreviatura='99' order by descripcion");
		names.add(new SpinnerObjectString("99", "--Seleccione--"));
		if (cursor.moveToFirst()) {
			do {
				names.add(new SpinnerObjectString(cursor.getString(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
		return names;
}
 
 public void insertIntegrante(String numerocorrelativo, String primernombre, String segundonombre, String tercernombre, String primerapellido, String segundoapellido, String apellidocasada, String tienedocumento, String dui, int nacionalidad, String sexo, String fechanacimiento, String nombre_madre, String nombre_padre, String nombre_responsable, int idfamilia_tablet, int id_estasib, int correlativo_tablet, Context contexto){
	 	conectarDB(contexto);
	 
		ContentValues newValues = new ContentValues(); 
		newValues.put("numerocorrelativo", numerocorrelativo);
		newValues.put("primernombre", primernombre);
		newValues.put("segundonombre", segundonombre);
		newValues.put("tercernombre", tercernombre);				
		newValues.put("primerapellido", primerapellido);
		newValues.put("segundoapellido", segundoapellido);
		newValues.put("apellidocasada", apellidocasada);
		newValues.put("tiene_documento", tienedocumento);
		newValues.put("dui", dui);
		newValues.put("nacionalidad", nacionalidad);
		newValues.put("sexo", sexo);
		newValues.put("fechanacimiento", fechanacimiento);
		newValues.put("nombre_madre", nombre_madre);
		newValues.put("nombre_padre", nombre_padre);
		newValues.put("nombre_responsable", nombre_responsable);
		newValues.put("idfamilia_tablet", idfamilia_tablet);
		newValues.put("id_estasib", id_estasib);
		newValues.put("correlativo_tablet", correlativo_tablet);
		newValues.put("estado", 1);
		
		conexOpen.insert("integrante", null, newValues);
		desConectarDB();		
	}
	
	public void updateIntegrante(String numerocorrelativo, String primernombre, String segundonombre, String tercernombre, String primerapellido, String segundoapellido, String apellidocasada, String tienedocumento, String dui, int nacionalidad, String sexo, String fechanacimiento, String nombre_madre, String nombre_padre, String nombre_responsable, int idfamilia_tablet, int id_estasib, int correlativo_tablet, int id_integrante, Context contexto){
		conectarDB(contexto);
		
		ContentValues newValues = new ContentValues(); 
		newValues.put("numerocorrelativo", numerocorrelativo);
		newValues.put("primernombre", primernombre);
		newValues.put("segundonombre", segundonombre);
		newValues.put("tercernombre", tercernombre);				
		newValues.put("primerapellido", primerapellido);
		newValues.put("segundoapellido", segundoapellido);
		newValues.put("apellidocasada", apellidocasada);
		newValues.put("tiene_documento", tienedocumento);
		newValues.put("dui", dui);
		newValues.put("nacionalidad", nacionalidad);
		newValues.put("sexo", sexo);
		newValues.put("fechanacimiento", fechanacimiento);
		newValues.put("nombre_madre", nombre_madre);
		newValues.put("nombre_padre", nombre_padre);
		newValues.put("nombre_responsable", nombre_responsable);
		newValues.put("idfamilia_tablet", idfamilia_tablet);
		newValues.put("id_estasib", id_estasib);
		newValues.put("correlativo_tablet", correlativo_tablet);
		
		conexOpen.update("integrante", newValues, "idintegrante_tablet="+id_integrante, null);
		desConectarDB();
	}
	
	public void deleteIntegrante(int id_integrante, Context contexto)
	{	
		conectarDB(contexto);
		conexOpen.delete("integrante","idintegrante_tablet="+id_integrante, null);
		desConectarDB();
	} 
	
	
	//recuperar ultimo id ingresado
	public int ultimoidIntegranteInt(Context contexto){
		conectarDB(contexto);
		
		int ultimoid=0;
		Cursor cQuery = conexOpen.rawQuery("Select idintegrante_tablet FROM integrante order by idintegrante_tablet DESC LIMIT 1", null);
		if (cQuery.moveToFirst()) {// Recorremos el cursor hasta que no haya mas registros
				do { 
					ultimoid = cQuery.getInt(0);
				} while (cQuery.moveToNext());
			}	
		desConectarDB();
		return ultimoid;
	}
	//----ultimo--------------------------------------------------------------------------------------------------------------------------------
    public int getCorrelativoIntegrante(int id_familia_tablet, Context contexto){
    	conectarDB(contexto);
	
        int cuenta2=0;
        int numero_usuarios2=0;
        List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
        String queryGetUsuario="SELECT COUNT(numerocorrelativo) as cuenta FROM integrante  WHERE idfamilia_tablet='"+id_familia_tablet+"'";
       
        
        Cursor cursor = conexOpen.rawQuery(queryGetUsuario, null);
        if (cursor.moveToFirst()) {     
            do {
                cuenta2=cursor.getInt(0);                               
            } while (cursor.moveToNext());
        }
       
        if(cuenta2>0){
            numero_usuarios2=cuenta2;
        }
        numero_usuarios2=numero_usuarios2+1;
        
    	desConectarDB();
        return numero_usuarios2;
 }
    
 public int SiCorrelativoIntegranteYaExiste(int id_familia_tablet, String cadena_correlativo, Context contexto){
	 	conectarDB(contexto);
	 
        int cuenta=0;
        int numero_usuarios=0;
        
        String queryGetUsuario="SELECT COUNT(idintegrante_tablet) as cuenta FROM integrante  WHERE idfamilia_tablet="+id_familia_tablet+" and numerocorrelativo='"+ cadena_correlativo +"'";
        //Log.i("querypassusuario",queryGetUsuario);
        Cursor cursor = conexOpen.rawQuery(queryGetUsuario, null);
        if (cursor.moveToFirst()) {     
            do {
                cuenta=cursor.getInt(0);                               
            } while (cursor.moveToNext());
        }
        //Log.i("cuenta",String.valueOf(cuenta));
        
        if(cuenta>0){
            numero_usuarios=1;
        }
        
        desConectarDB();
        return numero_usuarios;               
 } 
 
 
 public void getIntegranteInfoEdit(int id_integrante, Context contexto){
	 	conectarDB(contexto);
	 
		String queryGetInfoUsuario="SELECT " +						
									   " numerocorrelativo, " +
									   " fechanacimiento," +
									   " sexo," +
									   " dui," +
									   " nacionalidad," +
									   " primerapellido," +
									   " segundoapellido," +
									   " apellidocasada," +
									   " primernombre," +
									   " segundonombre," +
									   " tercernombre," +
									   " nombre_madre," +
									   " nombre_padre," +
									   " nombre_responsable," +
									   " tiene_documento" +
								" FROM   integrante" +
								" WHERE idintegrante_tablet="+id_integrante;
		//Log.i("queryusuario",queryGetInfoUsuario);
		Cursor cursor = conexOpen.rawQuery(queryGetInfoUsuario, null);
		if (cursor.moveToFirst()) {	  
			do {
				this.numerocorrelativo=cursor.getString(0);
				this.fechanacimiento=cursor.getString(1);
				this.sexo=cursor.getString(2);
				this.dui=cursor.getString(3);
				this.nacionalidad=cursor.getInt(4);
				this.primerapellido=cursor.getString(5);
				this.segundoapellido=cursor.getString(6);
				this.apellidocasada=cursor.getString(7);
				this.primernombre=cursor.getString(8);
				this.segundonombre=cursor.getString(9);
				this.tercernombre=cursor.getString(10);
				this.nombre_madre=cursor.getString(11);
				this.nombre_padre=cursor.getString(12);
				this.nombre_responsable=cursor.getString(13);
				this.tienedocumento=cursor.getString(14);						
			} while (cursor.moveToNext());
		}
		
		desConectarDB();
	 }
 
 
 public void deleteIntegranteVariable(int id_integrante, Context contexto){
	 conectarDB(contexto);
	 conexOpen.delete("integrante_variable","idintegrante_tablet="+id_integrante, null);
	 desConectarDB();
 }
 
 public int getSiHayJefe(int id_familia_tablet, Context contexto){
	 conectarDB(contexto);
		
     int cuenta2=0;
     int numero_usuarios2=0;
     List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
     String queryGetUsuario="SELECT COUNT(numerocorrelativo) as cuenta FROM integrante  WHERE idfamilia_tablet='"+id_familia_tablet+"' and numerocorrelativo='01' and estado='1'";
    
     
     Cursor cursor = conexOpen.rawQuery(queryGetUsuario, null);
     if (cursor.moveToFirst()) {     
         do {
             cuenta2=cursor.getInt(0);                               
         } while (cursor.moveToNext());
     }
    
     /*if(cuenta2>0)
     {
         numero_usuarios2=cuenta2;
     }*/
     desConectarDB();
     return cuenta2;             
 }
 
 public int getSiHayJefeEdit(int id_familia_tablet, int id_integrante, Context contexto){
	 conectarDB(contexto);
		
     int cuenta2=0;
     int numero_usuarios2=0;
     List<SpinnerObjectString> names = new ArrayList<SpinnerObjectString>();
     String queryGetUsuario="SELECT COUNT(numerocorrelativo) as cuenta FROM integrante  WHERE idfamilia_tablet='"+id_familia_tablet+"' and numerocorrelativo='01' and estado='1' and idintegrante_tablet<>"+id_integrante+"";
    
     
     Cursor cursor = conexOpen.rawQuery(queryGetUsuario, null);
     if (cursor.moveToFirst()) {     
         do {
             cuenta2=cursor.getInt(0);                               
         } while (cursor.moveToNext());
     }
    
     /*if(cuenta2>0)
     {
         numero_usuarios2=cuenta2;
     }*/
     desConectarDB();
     return cuenta2;             
 }
  public int getSiEstaVivo(int id_integrante, Context contexto){ 			
	  conectarDB(contexto);
	  
	  int estavivo=1;
	  String queryEstadoIntegrante="SELECT " +
								   " estado" +										   									  
							" FROM   integrante" +
							" WHERE idintegrante_tablet="+id_integrante;
	//Log.i("querypassusuario",queryGetPassUsuario);
	Cursor cursor = conexOpen.rawQuery(queryEstadoIntegrante, null);
	if (cursor.moveToFirst()) {	  
		do {
			estavivo=cursor.getInt(0);									
		} while (cursor.moveToNext());
	}
	
	desConectarDB();
	return estavivo;
 }
 public void CambiarEstadoVivoMuertoIntegrante(int id_integrante, Context contexto){ 			
	 	conectarDB(contexto);
	 	
	 	int estadoacambiar = getSiEstaVivo(id_integrante, contexto);
	 	//Log.i("estadoacambiar1",String.valueOf(estadoacambiar));
	 	if(estadoacambiar==1)
	 	{
	 		estadoacambiar=2;
	 	}
	 	else{
    	 	if(estadoacambiar==2)
    	 	{
    	 		estadoacambiar=1;
    	 	}
	 	}
	 	//Log.i("estadoacambiar2",String.valueOf(estadoacambiar));
			ContentValues newValues = new ContentValues(); 
			newValues.put("estado", estadoacambiar); 				
			
			conexOpen.update("integrante", newValues, "idintegrante_tablet="+id_integrante, null); 
			desConectarDB();
 }
 //---------------------Sam------25/10/14----------------
 public void cambiarAViviendaDeshabitada(int idfamilia,int idsuarioMod,Context contexto){
	 //conectarDB(contexto);
	 
	 String fechaactual= fechaActual();
	 ContentValues updateValues = new ContentValues();
	 updateValues.put("nombredomicilio","");
	 updateValues.put("numerotelefono","");
	 updateValues.put("tipo_riesgofamiliar","");
	 updateValues.put("nombre_otrareligion","");
	 updateValues.put("idusuario_mod",idsuarioMod);
	 updateValues.put("fecha_hora_mod",fechaactual);
	 
	 updateReg("familia", updateValues,  "idfamilia_tablet="+idfamilia, contexto);
	 deleteMiembrosFam(idfamilia,contexto);
	 deleteVariablesFam(idfamilia,contexto);
	 
	 //desConectarDB();
 }
 public void deleteMiembrosFam(int idfamilia, Context contexto) {
	 conectarDB(contexto);
	 conexOpen.delete("integrante","idfamilia_tablet="+idfamilia, null); 
	 desConectarDB();
	}
 public void deleteVariablesFam(int idfamilia, Context contexto) {
	 conectarDB(contexto);
	 conexOpen.delete("familia_variable","id_familia_tablet="+idfamilia, null); 
	 desConectarDB();
	}
 public void deleteFamilia(int idfamilia, Context contexto) {
	 conectarDB(contexto);
	 conexOpen.delete("familia","idfamilia_tablet="+idfamilia, null);
	 desConectarDB();
	}
 
 public void deleteIntegrante_VariableFam(int idfamilia, Context contexto) {
		conectarDB(contexto); 
		Cursor cursor = conexOpen.rawQuery("SELECT idintegrante_tablet FROM integrante WHERE idfamilia_tablet="+idfamilia, null);
		 
		if (cursor.moveToFirst()) {
			do {
				conexOpen.delete("integrante_variable","idintegrante_tablet="+cursor.getInt(0), null); 
			} while (cursor.moveToNext());
		}
		desConectarDB(); 
	}
public int existenDatosSincronizar(Context contexto){
	 conectarDB(contexto);
	
	 int pendientesSincronizar=0;
	 int cuenta=0;
	 String queryCuentaSinSincronizar="SELECT COUNT(*) as cuenta FROM bitacora WHERE sincronizado=0";	 
	 Cursor cursor = conexOpen.rawQuery(queryCuentaSinSincronizar, null);
	 if (cursor.moveToFirst()) {
			do {
				cuenta=cursor.getInt(0);		 
			} while (cursor.moveToNext());
		}
	 if (cuenta>0){
		 pendientesSincronizar=1;
	 }else{
		 pendientesSincronizar=0;
	 }
	 
	 desConectarDB();
	 return  pendientesSincronizar;
 }
public int cantidadDatosSincronizar(Context contexto){
	 conectarDB(contexto);
	
	 int pendientesSincronizar=0;
	 int cuenta=0;
	 String queryCuentaSinSincronizar="SELECT COUNT(*) as cuenta FROM bitacora WHERE sincronizado=0";	 
	 Cursor cursor = conexOpen.rawQuery(queryCuentaSinSincronizar, null);
	 if (cursor.moveToFirst()) {
			do {
				cuenta=cursor.getInt(0);		 
			} while (cursor.moveToNext());
		}
 
	 desConectarDB();
	 return  cuenta;
}

public void get_datosSincronizar(Context contexto){
	conectarDB(contexto);
	
	Common_function objCommon_function = new Common_function();
	 
	 String qRegSinSincronizar="SELECT id,fechaaccion, horaaccion, codigo_usuario, instruccion, descripcion, tipo_accion  FROM bitacora WHERE sincronizado=0";
	 Cursor cursor = conexOpen.rawQuery(qRegSinSincronizar, null);
	 ContentValues newValues = new ContentValues();
	 newValues.put("sincronizado", 1);
	
	 int[] ids_enviados = new int[cursor.getCount()];
	 int i = 0;
	 int tamanio=ids_enviados.length;
	 if (cursor.moveToFirst()) {
		
		 while(cursor.isAfterLast() == false){
			 ids_enviados[i]=cursor.getInt(0);
			  objCommon_function.set_datosSincronizar(contexto,
						cursor.getInt(0), 
						cursor.getString(1), 
						cursor.getString(2), 
						cursor.getString(3), 
						cursor.getString(4), 
						cursor.getString(5),
						cursor.getString(6));
			 	//updateReg("bitacora", newValues, "id="+cursor.getInt(0),contexto); 
			    //conexOpen.update("bitacora", newValues, "id="+cursor.getInt(0), null);
			 	Log.i("id enviado", ""+cursor.getInt(0));
			 	i++;
			 cursor.moveToNext();
		 }		// int x=0;
		/*	do {
				objCommon_function.set_datosSincronizar(contexto,
														cursor.getInt(0), 
														cursor.getString(1), 
														cursor.getString(2), 
														cursor.getString(3), 
														cursor.getString(4), 
														cursor.getString(5),
														cursor.getString(6));
				  //updateReg("bitacora", newValues, "id="+cursor.getInt(0),contexto); 
				  Log.i("id enviado", ""+cursor.getInt(0));
			}while (cursor.moveToNext());*/
		}
	 //cursor.close();
	 updateEnviados(ids_enviados,contexto);
	 desConectarDB();
 }
public void updateEnviados(int[] arrayEnviados, Context contexto){
	for (int i = 0 ; i < arrayEnviados.length ; i++) {
		conectarDB(contexto);
		conexOpen.execSQL("UPDATE bitacora SET sincronizado=1  WHERE id="+arrayEnviados[i]); 
		desConectarDB();
    }
}
public void updateSincronizados(Object id,Context contexto){
 
		conectarDB(contexto);
		conexOpen.execSQL("UPDATE bitacora SET sincronizado=1  WHERE id="+id); 
		desConectarDB();
    
}

public void actualizarEnviados(JSONArray jsonArray, Context contexto){
	//Obtenemos el arreglo de datos
			JSONArray allDatosSincronizados;
			
			try {
				allDatosSincronizados=jsonArray;	
				Bitacora bitacora = new Bitacora();		
					//Recorremos todos los objetos de json
					for (int i=0; i<allDatosSincronizados.length() ; i++) {
					 
						
						 //bitacora = obtenerBitacora(allDatosSincronizados.getJSONObject(i));
						 Log.i("loquetraeelArray", "recorriendo los datos"+allDatosSincronizados.get(i));
						 updateSincronizados(allDatosSincronizados.get(i),contexto);
					}
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
}
public Bitacora obtenerBitacora(JSONObject json){
	Bitacora bitacora = new Bitacora();
	try {
		bitacora.setId(json.getInt("Id"));
		/*bitacora.setFechaAccion(json.getString("FechaAccion"));
		bitacora.setHoraAccion(json.getString("HoraAccion"));
		bitacora.setCodigoUsuario(json.getString("CodigoUsuario"));
		bitacora.setInstruccion(json.getString("Instruccion"));
		bitacora.setDescripcion(json.getString("descripcion"));
		bitacora.setTipoAccion(json.getString("TipoAccion"));
		bitacora.setSincronizado(json.getInt("Sincronizado"));*/		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Log.d("ErrorBitacora", "Error creando la actividad desde el JSON"+e.getMessage());
	}
	
	return bitacora;
}

//llenar tabla bitacora con registros de prueba
	public void insertBitacora(Context contexto){
			conectarDB(contexto);
		
			ContentValues newValues = new ContentValues(); 
			newValues.put("fechaaccion", "aqui va la fecha'");
			newValues.put("horaaccion", "aqui va la hora");
			newValues.put("codigo_usuario", "aqui el codigo de usuario");
			newValues.put("instruccion", "aqui la instruccion SQL");
			newValues.put("descripcion", "aqui va la descripcion");
			newValues.put("tipo_accion", "aqui el tipo de accion");
			newValues.put("sincronizado", "0");
			
			conexOpen.insert("bitacora", null, newValues);	
			desConectarDB();
	} 
//-----------------------------------------------------------------------------------
	
	public ArrayList<Bitacora> obtenerBitacoraSync(Context contexto){
		conectarDB(contexto);
		
		ArrayList<Bitacora> listaBitacora = new ArrayList<Bitacora>();
		Cursor cursor = conexOpen.rawQuery("SELECT id,fechaaccion, horaaccion, codigo_usuario, instruccion, descripcion, tipo_accion  FROM bitacora WHERE sincronizado=0 limit 1", null);
		
		if(cursor.moveToFirst()){
			while(cursor.isAfterLast() == false){
				Bitacora bitacora = new Bitacora();
				
				bitacora.setId(cursor.getInt(0));
				bitacora.setFechaAccion(cursor.getString(1));
				bitacora.setHoraAccion(cursor.getString(2));
				bitacora.setCodigoUsuario(cursor.getString(3));
				bitacora.setInstruccion(cursor.getString(4));
				bitacora.setDescripcion(cursor.getString(5));
				bitacora.setTipoAccion(cursor.getString(6));
				
				listaBitacora.add(bitacora);
				cursor.moveToNext();
			}
			
		}
		desConectarDB();
		return listaBitacora;
	}	
}//termina la clase