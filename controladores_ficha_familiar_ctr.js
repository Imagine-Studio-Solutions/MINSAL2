document.observe('dom:loaded' , bindForm);

function bindForm(){
	if($F('codigo_sit_vivienda')!='01')
		deshabilitar_ficha();
} // bindForm

/**************************************************************
Máscara de entrada. Script creado por Tunait! (21/12/2004)
Si quieres usar este script en tu sitio eres libre de hacerlo con la condición de que permanezcan intactas estas líneas, osea, los créditos.
No autorizo a distribuír el código en sitios de script sin previa autorización
Si quieres distribuírlo, por favor, contacta conmigo.
Ver condiciones de uso en http://javascript.tunait.com/
tunait@yahoo.com 
****************************************************************/

var bandera;
var guardar_salud;
bandera=0;
guardar_salud=0;

var patron_dui = new Array(8,1);
var patron_fecha = new Array(2,2,4);

function mascara(d,sep,pat,nums){
if(d.valant != d.value){
	val = d.value
	largo = val.length
	val = val.split(sep)
	val2 = ''
	for(r=0;r<val.length;r++){
		val2 += val[r]	
	}
	if(nums){
		for(z=0;z<val2.length;z++){
			if(isNaN(val2.charAt(z))){
				letra = new RegExp(val2.charAt(z),"g")
				val2 = val2.replace(letra,"")
			}
		}
	}
	val = ''
	val3 = new Array()
	for(s=0; s<pat.length; s++){
		val3[s] = val2.substring(0,pat[s])
		val2 = val2.substr(pat[s])
	}
	for(q=0;q<val3.length; q++){
		if(q ==0){
			val = val3[q]
		}
		else{
			if(val3[q] != ""){
				val += sep + val3[q]
				}
		}
	}
	d.value = val
	d.valant = val
	}
}	  
function elegir_ecos(){
	if($F('ecos')=='-1'){
		alert("seleccione una USCF/E");
		return;
	}
	else{
		if($F('anio')=='-1'){
			alert("seleccione un año de levantamiento de Ficha Familiar");
			return;
		}
		else
			$('selecos_frm').submit();
	}
}  

function init() {
	shortcut.add("Ctrl+i", function() {
		agregar_familiar();
	});
	shortcut.add("Ctrl+e", function() {
		agregar_est_salud();
	});
}
window.onload=init;

function deshabilitar_ficha(){
	if($F('codigo_sit_vivienda')!='01'){
		$('tabla_var_vivienda').hide();
		$('datos_vivienda').hide();
		$('numerofamilia').value='000';
	}
	else{
		$('tabla_var_vivienda').show();
		$('datos_vivienda').show();
		$('numerofamilia').value='';
	}
	
	
}

function nombre_otrarel(){
	
	if ($F('codigo_religion') == '06') {
		$('nombre_otrareligion').show();
	}
	else 
		$('nombre_otrareligion').hide();
}

function con_letrina(){
	
	if($('valor[21]').options[$('valor[21]').selectedIndex].text=='1-Si'){
		$('auxiliar[22]').selectedIndex=0;
		$('auxiliar[24]').selectedIndex=0;
		$('auxiliar[22]').disabled=false;
		$('auxiliar[24]').disabled=false;
		$('auxiliar[22]').focus();
	}
	else{
		$('auxiliar[22]').selectedIndex=7;
		$('auxiliar[22]').disabled=true;
		$('auxiliar[24]').selectedIndex=3;
		$('auxiliar[24]').disabled=true;
		$('valor[22]').value=$F('auxiliar[22]');
		$('valor[24]').value=$F('auxiliar[24]');
		$('valor[23]').focus();
	}
}

function poner_valor_oculto(){
	$('valor[22]').value=$F('auxiliar[22]');
	$('valor[24]').value=$F('auxiliar[24]');
		
}

function perros_vacunados(){
	if ($F('valor_input[31]')=='0') {
		$('valor_input[32]').value ='0';
		$('valor_input[33]').focus();
	}
	else {
		$('valor_input[32]').focus();
		$('valor_input[32]').value =null;
	}
}

function gatos_vacunados(){
	if ($F('valor_input[33]')==0) {
		$('valor_input[34]').value ='0';
		$('valor_input[35]').focus();
	}
	else {
		$('valor_input[34]').focus();
		$('valor_input[34]').value =null;
	}
}

function buscar_municipio(){
	
	parametros='codigo_departamento='+$F('codigo_departamento')+
				'&accion=buscar_municipio';
	
	var myAjax = new Ajax.Updater(
		'codigo_municipio',
		'/controladores/ficha_familiar_ctr2.php',
		{
			method:'post',
			parameters: parametros	,
			evalScripts:true,
			onFailure:error	
		}
	);
}

function domicilio(){
	if($F('codigo_municipio')=='-1'){
		alert('Debe seleccionar un departamento y un municipio');
	}
	else{
		parametros='id_municipio='+$F('codigo_municipio')+'&area='+$F('codigo_area')+
				'&accion=mostrar_domicilios';
	
		var myAjax = new Ajax.Updater(
			'codigo_domicilio',
			'/controladores/ficha_familiar_ctr2.php',
			{
				method:'post',
				parameters: parametros	,
				evalScripts:true,
				onFailure:error	
			}
		);
	}
}

function limpiar_domicilio(){
	$('codigo_area').value='-1'
	$('codigo_domicilio').value='-1'
	$('codigo_zona').value='-1'
}

function agregar_familiar(){
	
	if($F('codigo_sit_vivienda')=='01'){
		$('protector_menu').show();
		$('capa_agregar_familiar').show();
		var myAjax = new Ajax.Updater(
			'capa_agregar_familiar',
			'/controladores/ficha_familiar_ctr2.php',
			{
				method:'post',
				parameters:	'idfamilia='+$F('llave')+'&accion=agregar_familiar',
				evalScripts:true,
				onFailure:error	
			}
		);
	}
	else{
		alert('No puede agregar integrantes a una vivienda No Habitada');
	}
	
}
function calcular_edad(){
	fecha=$F('fechanacimiento');
	fecha_llenado=$F('fechallenado');
	
	var regex = /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;
	if(!regex.test(fecha)){
		alert("Ingrese una fecha con formato dd/mm/aaaa");
		$('fechanacimiento').value='';
		$('edad').innerHTML='';
		return;
	} 
	var d = new Date(fecha.replace(regex, '$2/$1/$3'));
	if(!((parseInt(RegExp.$2, 10) == (1+d.getMonth()) ) && (parseInt(RegExp.$1, 10) == d.getDate()) && (parseInt(RegExp.$3, 10) == d.getFullYear()))){
		alert('Fecha inválida');
		$('fechanacimiento').value='';
		$('fechanacimiento').focus();
		return;
	}
		
	if(!regex.test(fecha_llenado)){
		alert("Ingrese una fecha de llenado de Ficha Familiar con formato dd/mm/aaaa");
		$('fechallenado').value='';
		return;
	} 
	var d = new Date(fecha_llenado.replace(regex, '$2/$1/$3'));
	if(!((parseInt(RegExp.$2, 10) == (1+d.getMonth()) ) && (parseInt(RegExp.$1, 10) == d.getDate()) && (parseInt(RegExp.$3, 10) == d.getFullYear()))){
		alert('Fecha de llenado de Ficha Familiar inválida');
		$('fechallenado').value='';
		return;
	}
	
	//Que la edad no sea negativa
	if(mayor($F('fechanacimiento'),$F('fechallenado'))){
		alert('La fecha de nacimiento deber ser menor a la fecha de llenado');
		$('fechanacimiento').focus();
		return;
	}
	
	fecha_nacido=$F('fechanacimiento').split('/');
	
	if(parseInt(fecha_nacido[2])<=parseInt(1900)){
		alert('El año de nacimiento no puede se menor de 1900');
		$('fechanacimiento').value='';
		$('fechanacimiento').focus();
		return;
	}
	
	parametros = 'fechanac=' + $F('fechanacimiento') +'&fechallenado=' +
				$F('fechallenado') +'&accion=calcular_edad';
	
	var myAjax = new Ajax.Request('/controladores/ficha_familiar_ctr2.php', {
		onSuccess: verificando_edad,
		onFailure: error,
		parameters: parametros,
		method: 'post',
	});

}

function verificando_edad(resp){
	respuesta=resp.responseText;
	if (respuesta == 'error') {
		alert('Fecha de nacimiento con año inválido');
		$('fechanacimiento').value = '';
	}
	else {
		datos = eval(resp.responseText);
		
		//$('probando').update(edad[0]);
		$('edad').innerHTML = datos[0];
		$('anios').value = datos[1];
		edad = parseInt(datos[1]);
		//Si es el jefe de familia
		if ($F('numerocorrelativo') == '01') {
			$('valor[49]').selectedIndex = 1;
			$('valor[49]').disabled = true;
		}
		if(($F('modo_integrante')!='actualizar_generales') && ($F('modo_integrante')!='actualizar_todo')){
			$('valor[50]').selectedIndex = 0;
			$('valor[51]').selectedIndex = 0;
			$('valor[52]').selectedIndex = 0;
			$('valor[53]').selectedIndex = 0;
			$('valor[54]').selectedIndex = 0;
			$('valor[55]').selectedIndex = 0;
		}
		
		if (edad < 3) {
			$('valor[51]').selectedIndex = 3;
			$('valor[51]').disabled = true;
			$('valor[52]').selectedIndex = 2;
			$('valor[52]').disabled = true;
			$('valor[49]').focus();
		}
		else{
			if (($F('modo_integrante') != 'actualizar_generales') && ($F('modo_integrante') != 'actualizar_todo')) {
				$('valor[51]').selectedIndex = 0;
				$('valor[52]').selectedIndex = 0;
			}
			$('valor[51]').disabled = false;
			$('valor[52]').disabled = false;
			$('valor[49]').focus();
		}
		if (edad < 14) {
			$('valor[53]').disabled = true;
			$('valor[54]').disabled = true;
			$('valor[53]').selectedIndex = 4;
			$('valor[54]').selectedIndex = 1;
			$('valor[49]').focus();
		}
		else{
			if (($F('modo_integrante') != 'actualizar_generales') && ($F('modo_integrante') != 'actualizar_todo')) {
				$('valor[53]').selectedIndex = 0;
				$('valor[54]').selectedIndex = 0;
				$('valor[51]').selectedIndex = 0;
				$('valor[52]').selectedIndex = 0;
			}
			$('valor[53]').disabled = false;
			$('valor[54]').disabled = false;
			$('valor[51]').disabled = false;
			$('valor[52]').disabled = false;
		}
	}
	sexo_y_edad($F('numerocorrelativo'));
}


function guardar_familiar(otro_miembro,metodo){
	var vars = new Array;
	var faltan;
	var j;
	
	edad=$F('anios');
	sexo=$F('sexo');
	
	if (bandera == 0) {
		bandera = 1;
		
		$('metodo').value = metodo;
		$('otro_miembro').value = otro_miembro;
		
		nombre = $F('nombrepropio');
		apellido = $F('apellidos');
		regexp = /^[a-zA-ZñÑ\s]{3,}$/;
		
		if (!regexp.test(nombre) || !regexp.test(apellido)) {
			alert("El nombre del familiar debe estar completo sin abreviaturas y sólo puede contener letras sin signos de puntuación");
			if(metodo=='nuevo'){
				$('fam_otro').disabled = false;
				$('fam_salir').disabled = false;
			}
			else{
				$('fam_no_otro').disabled = false;
			}
			bandera = 0;
			return;
		}
		
		if (($F('sexo')) == '-1') {
			alert("No ha ingresado el sexo");
			if(metodo=='nuevo'){
				$('fam_otro').disabled = false;
				$('fam_salir').disabled = false;
			}
			else{
				$('fam_no_otro').disabled = false;
			}
			bandera = 0;
			return;
		}
		
		if (($F('fechanacimiento').length) == 0) {
			alert("No ha ingresado la fecha de nacimiento");
			if(metodo=='nuevo'){
				$('fam_otro').disabled = false;
				$('fam_salir').disabled = false;
			}
			else{
				$('fam_no_otro').disabled = false;
			}
			bandera = 0;
			$('fechanacimiento').focus();
			return;
		}
		$$('.var_int').each(function(nodo, i){
			if (nodo.value == -1 || nodo.value=='') {
				faltan = 'true';
			}
			else{
				vars[i] = nodo.value
			}
			j=i;
		})
		
		//Por si es una modificación que agregue el estado de salud
		if ($F('modo_integrante') == 'actualizar_todo') {
			$$('.var_multiple').each(function(nodo){
			
				if (nodo.value == -1) {
					faltan = 'true';
				}
				else {
					j++;
					vars[j] = $F(nodo.id);
				}
			})
			numero_variables = 25;
			
		}
		else {
			numero_variables = 7;
		}
		
		respondidas=vars.length;
		
		//Que no existan preguntas sin responder
		if (faltan == 'true' || respondidas<numero_variables) {
			alert("Existe/n pregunta/s sin responder, favor revise que todas las preguntas tengan respuesta");
			if(metodo=='nuevo'){
				$('fam_otro').disabled = false;
				$('fam_salir').disabled = false;
			}
			else{
				$('fam_no_otro').disabled = false;
			}
			bandera = 0;
			return;
		}
		
		if ($F('modo_integrante') == 'actualizar_todo') {
			//Para validar el valor nutricional pregunta 60
			//menores de 2 años valores 2,3 4 del select
			/*if (edad < 2) {
				if ($('valor[60]').selectedIndex == 1 || $('valor[60]').selectedIndex == 5 || $('valor[60]').selectedIndex == 6 || $('valor[60]').selectedIndex == 7 || $('valor[60]').selectedIndex == 8 || $('valor[60]').selectedIndex == 9) {
					alert("El valor selecionado en estado nutricional no es permitido para la edad del integrante ");
					if(metodo=='nuevo'){
						$('fam_otro').disabled = false;
						$('fam_salir').disabled = false;
					}
					else{
						$('fam_no_otro').disabled = false;
					}
					bandera = 0;
					$('valor[60]').focus();
					return;
				}
			}
			//menores de 5 años valores 1,5,8 y 9 del select
			else if(edad<5){
				if ($('valor[60]').selectedIndex == 2 || $('valor[60]').selectedIndex == 3 || $('valor[60]').selectedIndex == 4 || $('valor[60]').selectedIndex == 6 || $('valor[60]').selectedIndex == 7) {
					alert("El valor selecionado en estado nutricional no es permitido para la edad del integrante ");
					if(metodo=='nuevo'){
						$('fam_otro').disabled = false;
						$('fam_salir').disabled = false;
					}
					else{
						$('fam_no_otro').disabled = false;
					}
					bandera = 0;
					$('valor[60]').focus();
					return;
				}
			}	*/
			
			//Para validad el método de planificación pregunta 69
			//hombres opciones 1,6,7 y 9 del select
			/*if (edad>9){
				if (sexo=='M') {
					alert('hombre')
					if ($('valor[69]').selectedIndex == 2 || $('valor[69]').selectedIndex == 3 || $('valor[69]').selectedIndex == 4 || $('valor[69]').selectedIndex == 5 || $('valor[69]').selectedIndex == 8) {
						alert("El valor selecionado en método de planficación no es permitido para el sexo del integrante ");
						if(metodo=='nuevo'){
							$('fam_otro').disabled = false;
							$('fam_salir').disabled = false;
						}
						else{
							$('fam_no_otro').disabled = false;
						}
						bandera = 0;
						$('valor[69]').focus();
						return;
					}
				}
				//mujeres todas las opciones menos 6
				else{
					if ($('valor[69]').selectedIndex == 6) {
						alert("El valor selecionado en método de planficación no es permitido para el sexo del integrante ");
						if(metodo=='nuevo'){
							$('fam_otro').disabled = false;
							$('fam_salir').disabled = false;
						}
						else{
							$('fam_no_otro').disabled = false;
						}
						bandera = 0;
						$('valor[69]').focus();
						return;
					}
				}	
			}*/
		}
		//que el # de dui tenga todos sus digitos
	/*	if ($F('dui')) {
			cant_digitos = $F('dui').length;
			if (cant_digitos < 10) {
				alert('Ingrese un número de DUI completo');
				if(metodo=='nuevo'){
					$('fam_otro').disabled = false;
					$('fam_salir').disabled = false;
				}
				else{
					$('fam_no_otro').disabled = false;
				}
				bandera = 0;
				return;
			}
		}*/
		//que el jefe de familia sea mayor de 15 años
		if ($('valor[49]').selectedIndex == '1' || $('valor[49]').selectedIndex == '4' || $('valor[49]').selectedIndex == '5') {
			if (parseInt($F('anios')) < 14) {
				alert('Para el tipo de parentesco la persona debe ser mayor de 14 años');
				if(metodo=='nuevo'){
					$('fam_otro').disabled = false;
					$('fam_salir').disabled = false;
				}
				else{
					$('fam_no_otro').disabled = false;
				}
				bandera = 0;
				return;
			}
		}
		
		/*******Poner condición para yerno o nuera PENDIENTE!!!!!!!!!!!!!!!!!!!!!!!******/
	/*	if ($('valor[49]').selectedIndex == '7') {
			if (parseInt($F('anios')) < 12) {
				alert('El yerno/nuera debe ser mayor de 11 años');
				if(metodo=='nuevo'){
					$('fam_otro').disabled = false;
					$('fam_salir').disabled = false;
				}
				else{
					$('fam_no_otro').disabled = false;
				}
				bandera = 0;
				return;
			}
		}*/
		
		
		
		//Que la edad no sea negativa
		if(mayor($F('fechanacimiento'),$F('fechallenado'))){
			alert('La fecha de nacimiento deber ser menor a la fecha de llenado');
			if(metodo=='nuevo'){
				$('fam_otro').disabled = false;
				$('fam_salir').disabled = false;
			}
			else{
				$('fam_no_otro').disabled = false;
			}
			bandera = 0;
			return;
		}
		
		//Que no haya otro jefe de familia
		if($('numerocorrelativo').value!='01' && ($('valor[49]').options[$('valor[49]').selectedIndex].text == '1-Jefa(e)')){
			alert('Ya tiene un integrante como Jefe de Familia');
			if(metodo=='nuevo'){
				$('fam_otro').disabled = false;
				$('fam_salir').disabled = false;
			}
			else{
				$('fam_no_otro').disabled = false;
			}
			bandera = 0;
			return;
		}
		
		//Que no tenga un grado de estudio alto si no puede leer
		if ($('valor[51]').options[$('valor[51]').selectedIndex].text == '0-No') {
			grado_estudio = $F('valor[52]');
			grado_estudio = grado_estudio.split('-');
			if (grado_estudio[1] != 'NA') {
				if (parseInt(grado_estudio[1]) > parseInt(2)) {
					alert('El grado de estudio es inválido, para una persona analfabeta');
					if (metodo == 'nuevo') {
						$('fam_otro').disabled = false;
						$('fam_salir').disabled = false;
					}
					else {
						$('fam_no_otro').disabled = false;
					}
					bandera = 0;
					return;
				}
			}
		}
		else if ($('valor[51]').options[$('valor[51]').selectedIndex].text == '1-Si') {
			if ($('valor[52]').options[$('valor[52]').selectedIndex].text == 'NA-No aplica') {
				alert('El grado de estudio es inválido, para una persona que sabe leer');
				if (metodo == 'nuevo') {
					$('fam_otro').disabled = false;
					$('fam_salir').disabled = false;
				}
				else {
					$('fam_no_otro').disabled = false;
				}
				bandera = 0;
				return;
			}
		}		
		$('botones').hide();
		
		parametros = "variables=" + vars.toJSON() + '&id_familia=' + $F('llave') +
		'&numerocorrelativo=' +	$F('numerocorrelativo') +
		'&fechanacimiento=' +$F('fechanacimiento') +
		'&sexo=' +	$F('sexo') +
		'&nombrepropio=' +$F('nombrepropio') +
		'&apellidos=' +	$F('apellidos') +
		'&fechaoriginal=' +	$F('fecha_original') +
		'&fecha=' +	$F('fechallenado') +
		'&metodo=' +metodo +
		'&nacionalidad=' +$F('nacionalidad') +
		'&accion=guardar_familiar';
		
		new Ajax.Request("/controladores/ficha_familiar_ctr2.php", {
			onSuccess: linea_familiar,
			onCreate:function() {
						    $('transparente').show();
						  },
			onFailure: error,
			parameters: parametros,
			method: 'post'
		});
	}
	else{
		alert('Ya dio click una vez sobre el botón guardar, favor esperar a que se guarde el integrante');
	}
   
}

function linea_familiar(resp){
		var fila;
		var fila_salud;
		resultado=resp.responseText;
		//$('probando').update(resultado);
		if (resultado != 'ok') {
			datos_guardado = eval(resultado);
			if (datos_guardado[0] == 'ok') {
				/**********************FILA PARA DATOS GENERALES*******************************/
				fila = '<TD align=center>' + $F('numerocorrelativo') + '</TD>' +
				'<TD>' +
				$F('nombrepropio') +
				'</TD>' +
				'<TD>' +
				$F('apellidos') +
				'</TD>' +
				'<TD>' +
				$('nacionalidad').options[$('nacionalidad').selectedIndex].text +
				'</TD>' +
				'<TD id="sexo-' +
				$F('numerocorrelativo') +
				'">' +
				$('sexo').options[$('sexo').selectedIndex].text +
				'</TD>' +
				'<TD style="text-align:right">' +
				$F('fechanacimiento') +
				'</TD>' +
				'<TD>' +
				$('edad').innerHTML +
				'<input type="hidden" name="anios" id="anios-' +
				$F('numerocorrelativo') +
				'" value="' +
				$F('anios') +
				'">' +
				'</TD>' +
				'<TD align=center>' +
				$('valor[49]').options[$('valor[49]').selectedIndex].text +
				'</TD>' +
				'<TD style="text-align:center">' +
				$('valor[50]').options[$('valor[50]').selectedIndex].text +
				'</TD>' +
				'<TD style="text-align:center">' +
				$('valor[51]').options[$('valor[51]').selectedIndex].text +
				'</TD>' +
				'<TD style="text-align:center" >' +
				$('valor[52]').options[$('valor[52]').selectedIndex].text +
				'</TD>' +
				'<TD style="text-align:center" >' +
				$('valor[53]').options[$('valor[53]').selectedIndex].text +
				'</TD>' +
				'<TD style="text-align:center" >' +
				$('valor[54]').options[$('valor[54]').selectedIndex].text +
				'</TD>' +
				'<TD style="text-align:center" >' +
				$('valor[55]').options[$('valor[55]').selectedIndex].text +
				'</TD>' +
				"<TD align=center><img src='/imagenes/modificar.png' onclick=\"modificar_familiar('" +
				$F('numerocorrelativo') +
				"','generales')\">" +
				"<img src='/imagenes/borrar.png' onclick=\"confirmar_borrar('" +
				$F('numerocorrelativo') +
				"')\"></TD>";
				
				/**********************FILA PARA DATOS DE SITUACIÓN DE SALUD*******************************/
				if ($F('modo_integrante') == 'actualizar_todo') {
					fila_salud = '<TD align=center>' + $F('numerocorrelativo') + '</TD>' +
					'<TD align=left>' +
					$('valor[56]').options[$('valor[56]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center">' +
					$('valor[57]').options[$('valor[57]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center">' +
					datos_guardado[1] +
					'</TD>' +
					'<TD style="text-align:center" >' +
					$('valor[59]').options[$('valor[59]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center" >' +
					$('valor[60]').options[$('valor[60]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center" >' +
					$('valor[61]').options[$('valor[61]').selectedIndex].text +
					'</TD>' +
					'<TD align=left>' +
					$('valor[62]').options[$('valor[62]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center">' +
					$('valor[63]').options[$('valor[63]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center">' +
					$('valor[64]').options[$('valor[64]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center" >' +
					$('valor[65]').options[$('valor[65]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:left" >' +
					$('valor[66]').options[$('valor[66]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center" >' +
					$('valor[67]').options[$('valor[67]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center" >' +
					$('valor[68]').options[$('valor[68]').selectedIndex].text +
					'</TD>' +
					'<TD align=center>' +
					$('valor[69]').options[$('valor[69]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center">' +
					$('valor[70]').options[$('valor[70]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center">' +
					$('valor[71]').options[$('valor[71]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center" >' +
					$('valor[72]').options[$('valor[72]').selectedIndex].text +
					'</TD>' +
					'<TD style="text-align:center" >' +
					$('valor[73]').options[$('valor[73]').selectedIndex].text +
					'</TD>';
				}
				
				if ($F('metodo') == 'nuevo') {
					new Insertion.Bottom('detalle_integrante', '<TR class="integrante" id="integrante-' + $F('numerocorrelativo') + '">' + fila + '</TR>');
					if ($F('otro_miembro') == 'true') {
						$('transparente').hide();
						bandera = 0;
						$('capa_agregar_familiar').update('.');
						agregar_familiar();
					}
					else {
						$('transparente').hide();
						$('capa_agregar_familiar').update('.');
						$('capa_agregar_familiar').hide();
						bandera = 0;
					}
				}
				else {
					$('transparente').hide();
					$('integrante-' + $F('numerocorrelativo')).innerHTML = fila;

					if ($F('modo_integrante') == 'actualizar_todo') 
						$('salud-' + $F('numerocorrelativo')).innerHTML = fila_salud;

					$('capa_agregar_familiar').update('.');
					$('capa_agregar_familiar').hide();
					bandera = 0;
				}
				
			}
			else {
				$('botones').show();
				alert("No se pudo guardar el integrante de familia, favor volver a intentarlo");
				$('transparente').hide();
				
				if (metodo == 'nuevo') {
					$('fam_otro').disabled = false;
					$('fam_salir').disabled = false;
				}
				else {
					$('fam_no_otro').disabled = false;
				}
				bandera = 0;
			}
		}
}

function agregar_est_salud(){
	//contamos cuantos estados de salud ya han sido agregados para el nuevo correlativo
	familiar=$$('.integrante');
	familiares=familiar.length;
	
	if(familiares>0){
		$('capa_agregar_familiar').show();
		var myAjax = new Ajax.Updater(
			'capa_agregar_familiar',
			'/controladores/ficha_familiar_ctr2.php',
			{
				method:'post',
				parameters:	'id_familia='+$F('llave')+'&accion=agregar_estsalud',
				evalScripts:true,
				onFailure:error	
			}
		);
	}
	else
		alert('Debe ingresar al menos un integrante de la familia');
	
}

function poner_estado_salud(correlativo){
	var edad;
	//edad y sexo del estado de salud a agregar
	edad = $F('anios');
	sexo=$F('sexo');
	
	if ($F('modo_integrante') == 'actualizar_todo') {
		Form.disable('agregar_estsalud_frm');
		$('numerocorrelativo').disabled = false;
		$('valor[56]').disabled = false;
		$('valor[58]').disabled = false;
		$('valor[73]').disabled = false;
		$('valor[72]').disabled = false;
		
		if (edad < 18) {
			$('valor[71]').disabled = false;
			if (edad > 9) {
				$('valor[61]').disabled = false;
				$('valor[62]').disabled = false;
				$('valor[69]').disabled = false;
			}
			if (sexo == 'F' && edad > 9) {
				$('valor[64]').disabled = false;
				$('valor[65]').disabled = false;
				$('valor[66]').disabled = false;
				$('valor[67]').disabled = false;
				$('valor[68]').disabled = false;
			}
			if (edad > 5 && edad < 14) {
				$('valor[70]').disabled = false;
			}
			if (edad < 5) {
				$('valor[60]').disabled = false;
			}
		}
		else {
			$('valor[61]').disabled = false;
			$('valor[62]').disabled = false;
			$('valor[69]').disabled = false;
			
			if (sexo == 'F') {
				$('valor[64]').disabled = false;
				$('valor[65]').disabled = false;
				//if (edad <= 60) {
					$('valor[66]').disabled = false;
					$('valor[67]').disabled = false;
				//}*/
				$('valor[68]').disabled = false;
			}
		}
		valor_nutricion = $F('nutricion_aux').split('-');
		valor_planificacion = $F('planifica_aux').split('-');
		estado_nutricional(edad, valor_nutricion[1]);
		metodo_planificacion(edad, sexo, valor_planificacion[1]);
	}
	/*if (edad < 3) {
        $('valor[51]').disabled = true;
        $('valor[52]').disabled = true;
    }*/
    if (edad < 14) {
        $('valor[53]').disabled = true;
        $('valor[54]').disabled = true;
    }
}	


function sexo_y_edad(correlativo){
	//edad y sexo del estado de salud a agregar
	
	if($F('modo_integrante')!='actualizar_todo' && $F('modo_integrante')!='ingresar_salud'){
		return;
	}
	
	if ($F('modo_integrante') == 'actualizar_todo') {
		edad = $F('anios');
		sexo=$F('sexo');
	}
	else {
		edad = $F('anios-' + correlativo);
		edad=parseInt(edad);
		sexo=$('sexo-'+correlativo).innerHTML;
		sexo=sexo.split('-');
		sexo=sexo[1];
	}
	//alert(edad);
	seleccionado=$F('numerocorrelativo');
	
	Form.reset('agregar_estsalud_frm');
	Form.disable('agregar_estsalud_frm');
	$('numerocorrelativo').disabled=false;
	$('numerocorrelativo').value=seleccionado;
	$('valor[56]').disabled=false;
	$('valor[58]').disabled=false;
	$('valor[73]').disabled=false;
	$('valor[72]').disabled=false;
	$('valor[61]').disabled = false;
	$('valor[62]').disabled =false;
	$('valor[63]').disabled =false;
	
	if (edad < 18) {
		$('valor[71]').disabled = false;
		$('valor[71]').selectedIndex = 0;
		if(edad > 9){
			$('valor[62]').disabled = false;
		}
		else{
			$('valor[63]').selectedIndex = 4;
			//planificacion
			$('valor[69]').selectedIndex = 9;
		}
		if(sexo=='F' && edad >9){
			$('valor[64]').disabled = false;
			$('valor[65]').disabled = false;
			$('valor[66]').disabled = false;
			$('valor[68]').disabled = false;
			$('valor[67]').disabled = false;
			$('valor[64]').selectedIndex = 0;
			$('valor[65]').selectedIndex = 0;
			$('valor[66]').selectedIndex = 0;
			$('valor[68]').selectedIndex = 0;
			$('valor[67]').selectedIndex = 0;
		}
		else{
			$('valor[64]').selectedIndex = 3;
			$('valor[65]').selectedIndex = 3;
			$('valor[66]').selectedIndex = 3;
			$('valor[67]').selectedIndex = 3;
			$('valor[68]').selectedIndex = 4;
		}
		if(edad>4 && edad <14){
			$('valor[70]').disabled = false;
		}
		else{
			$('valor[70]').selectedIndex = 3;
		}
		
	}
	else{
		$('valor[71]').selectedIndex = 4;
		$('valor[70]').selectedIndex = 3;
		
		if(sexo=='F'){
			$('valor[64]').disabled = false;
			$('valor[65]').disabled = false;
			$('valor[68]').disabled = false;
			$('valor[64]').selectedIndex = 0;
			$('valor[65]').selectedIndex = 0;
			$('valor[68]').selectedIndex = 0;
			if (edad > 60) {
				$('valor[67]').selectedIndex = 3;
				$('valor[66]').selectedIndex = 3;
			}
			else {
				$('valor[67]').selectedIndex = 0;
				$('valor[67]').disabled = false;	
				$('valor[66]').selectedIndex = 0;
				$('valor[66]').disabled = false;
			}
			
		}
		else{
			$('valor[64]').selectedIndex = 3;
			$('valor[65]').selectedIndex = 3;
			$('valor[66]').selectedIndex = 3;
			$('valor[67]').selectedIndex = 3;
			$('valor[68]').selectedIndex = 4;
		}
	}
	
	//Para el estado nutricional
	if(edad>5){
		$('valor[60]').selectedIndex = 6;
	}
	else{
		$('valor[60]').disabled =false;
	}
	if (edad > 9) {
		//planificacion habilitar
		$('valor[69]').disabled = false;
	}
	
	if ($F('modo_integrante') == 'actualizar_todo') {
		estado_nutricional(edad, 'xx');
		metodo_planificacion(edad, sexo, 'xx');
	}
}

function estado_nutricional(edad,valor_nutri_guardado){
	parametros='edad='+edad+'&accion=estado_nutricional'+'&valor_guardado='+valor_nutri_guardado;
	var myAjax = new Ajax.Updater(
		'estado_nutricional',
		'/controladores/ficha_familiar_ctr2.php',
		{
			method:'post',
			parameters:	parametros,
			evalScripts:true,
			onFailure:error	
		}
	);
}

function metodo_planificacion(edad,sexo,valor_planif_guardado){	
	parametros='sexo='+sexo+'&edad='+edad+'&accion=met_planificacion'+'&valor_guardado='+valor_planif_guardado;
	var myAjax = new Ajax.Updater(
		'metodo_planificacion',
		'/controladores/ficha_familiar_ctr2.php',
		{
			method:'post',
			parameters:	parametros,
			evalScripts:true,
			onFailure:error	
		}
	);
}

function tiene_discapacidad(){
	if($('valor[56]').options[$('valor[56]').selectedIndex].text!='NA-No tiene discapacidad'){
		$('valor[57]').selectedIndex=0;
		$('valor[57]').disabled=false;
		$('valor[57]').focus();
	}
	else{
		$('valor[57]').selectedIndex=6;
		$('valor[57]').disabled=true;
	}
		
}

function esta_embarazada(){
	if($('valor[41]').options[$('valor[41]').selectedIndex].text=='1-Si'){
		$('valor[42]').selectedIndex=0;
		$('valor[42]').disabled=false;
		$('valor[42]').focus();
	}
	else{
		$('valor[42]').selectedIndex=5;
		$('valor[42]').disabled=false;
	}
}

function no_hay_partos(){
	
	if($('valor[43]').options[$('valor[43]').selectedIndex].text=='NA-No aplica'){
		$('valor[46]').selectedIndex=3;
		$('valor[46]').disabled=false;
	}
	else{
		$('valor[46]').selectedIndex=0;
		$('valor[46]').disabled=false;
	}
}

function alfabeta(){
	
	if($('valor[51]').options[$('valor[51]').selectedIndex].text=='0-No'){
		$('valor[52]').selectedIndex=1;
		$('valor[52]').disabled=false;
	}
	else if($('valor[51]').options[$('valor[51]').selectedIndex].text=='NA-No aplica'){
		$('valor[52]').selectedIndex=2;
		$('valor[52]').disabled=true;
	}
	else{
		$('valor[52]').selectedIndex=0;
		$('valor[52]').disabled=false;
	}
}

function ocupacion(){	
	if($('valor[53]').options[$('valor[53]').selectedIndex].text=='NA-No aplica' || $('valor[53]').options[$('valor[53]').selectedIndex].text=='0-Desempleado'){
		$('valor[54]').selectedIndex=1;
		$('valor[54]').disabled=true;
	}
	else{
		$('valor[54]').selectedIndex=0;
		$('valor[54]').disabled=false;
	}
}

function toma_med(){	
	if($('valor[58]').selectedIndex==1){
		$('valor[59]').selectedIndex=3;
		$('valor[59]').disabled=true;
		$('valor[60]').focus();
	}
	else{
		$('valor[59]').selectedIndex=0;
		$('valor[59]').disabled=false;
		$('valor[59]').focus();
	}
}

function toma_med(){	
	if($('valor[58]').selectedIndex==1){
		$('valor[59]').selectedIndex=3;
		$('valor[59]').disabled=true;
	}
	else{
		$('valor[59]').selectedIndex=0;
		$('valor[59]').disabled=false;
	}
}

function consumo_bebidas(){
	
	if($('valor[62]').selectedIndex==1 || $('valor[62]').selectedIndex==6){
		$('valor[63]').selectedIndex=4;
		$('valor[63]').disabled=true;
	}
	else{
		$('valor[63]').selectedIndex=0;
		$('valor[63]').disabled=false;
		$('valor[63]').focus();
	}
}

function cantidad_bebida(){
	
	if($('valor[63]').selectedIndex==4 && $('valor[62]').selectedIndex!=6){
		alert('No puede seleccionar No aplica cuando existe Frecuencia de consumo de bebidas embriagantes');
		$('valor[63]').selectedIndex=0;
		$('valor[63]').focus();
	}
}

function guardar_est_salud(otro_miembro,metodo){	
	var vars = new Array;
	var faltan;
	var j,pase_enfermedad;
	
	edad = $F('anios-' + $F('numerocorrelativo'));
	edad=parseInt(edad);
	sexo=$('sexo-'+$F('numerocorrelativo')).innerHTML;
	sexo=sexo.split('-');
	sexo=sexo[1];
	
	if (guardar_salud == 0) {
		guardar_salud = 1;
		
		$('metodo').value = metodo;
		$('otro_miembro').value = otro_miembro;
		
		if ($F('numerocorrelativo') == '-1') {
			alert("Seleccione un correlativo para el integrante de familia");
			if(metodo=='nuevo'){
				$('salud_otro').disabled = false;
				$('salud_salir').disabled = false;
			}
			else{
				$('salud_no_otro').disabled = false;
			}
			guardar_salud = 0;
			return;
		}
		
		$$('.var_int').each(function(nodo,i){
			if (nodo.value == -1 || nodo.value=='') {
				faltan = 'true';
			}
			else {
				vars[i] = nodo.value
			}
			j=i;
		})
		
		//Por si es una modificación que agregue el estado de salud
		$$('.var_multiple').each(function(nodo){
				if (nodo.value == -1) {
					faltan = 'true';
				}
				else {
					j++;
					enf_selec=$F(nodo.id);
					for(k=0;k<enf_selec.length;k++){
						if(enf_selec[k]=='81-0'){
							if (enf_selec.length>1){
								pase_enfermedad='no';
							}
							else
								pase_enfermedad='si';
						}
					}
					vars[j] = $F(nodo.id);
				}
		})
		if(pase_enfermedad=='no'){
			alert('No puede seleccionar una Enfermedad y también la opción No Presenta Enfermedad');
			if(metodo=='nuevo'){
				$('salud_otro').disabled = false;
				$('salud_salir').disabled = false;
			}
			else{
				$('salud_no_otro').disabled = false;
			}
			guardar_salud = 0;
			return;
		}
		
		respondidas=vars.length;
		
		//Que no existan preguntas sin responder
		if (faltan == 'true' || respondidas<18) {
			alert("Existe/n pregunta/s sin responder, favor revise que todas las preguntas tengan respuesta");
			if(metodo=='nuevo'){
				$('salud_otro').disabled = false;
				$('salud_salir').disabled = false;
			}
			else{
				$('salud_no_otro').disabled = false;
			}
			guardar_salud = 0;
			return;
		}
		
		//Para validar el valor nutricional pregunta 60
		//menores de 2 años valores 2,3 5 del select
		if (edad <=2) {
			if ($('valor[60]').selectedIndex == 1 || $('valor[60]').selectedIndex == 4 || $('valor[60]').selectedIndex == 6 || $('valor[60]').selectedIndex == 7 || $('valor[60]').selectedIndex == 8 || $('valor[60]').selectedIndex == 9) {
				alert("El valor selecionado en estado nutricional no es permitido para la edad del integrante ");
				if(metodo=='nuevo'){
					$('salud_otro').disabled = false;
					$('salud_salir').disabled = false;
				}
				else{
					$('salud_no_otro').disabled = false;
				}
				guardar_salud = 0;
				$('valor[60]').focus();
				return;
			}
		}
		//menores de 5 años valores 1,4,8 y 9 del select
		else if(edad<5){
			if ($('valor[60]').selectedIndex == 2 || $('valor[60]').selectedIndex == 3 || $('valor[60]').selectedIndex == 5 ||
			 $('valor[60]').selectedIndex == 6 || $('valor[60]').selectedIndex == 7) {
				alert("El valor selecionado en estado nutricional no es permitido para la edad del integrante ");
				if(metodo=='nuevo'){
					$('salud_otro').disabled = false;
					$('salud_salir').disabled = false;
				}
				else{
					$('salud_no_otro').disabled = false;
				}
				guardar_salud = 0;
				$('valor[60]').focus();
				return;
			}
		}	
		
		//Para validad el método de planificación pregunta 69
		//hombres opciones 1,6,7 y 9 del select
		if (edad>9){
			if (sexo=='M') {
				if ($('valor[69]').selectedIndex == 2 || $('valor[69]').selectedIndex == 3 || $('valor[69]').selectedIndex == 4 || $('valor[69]').selectedIndex == 5 || $('valor[69]').selectedIndex == 8) {
					alert("El valor selecionado en método de planificación no es permitido para el sexo del integrante ");
					if(metodo=='nuevo'){
						$('salud_otro').disabled = false;
						$('salud_salir').disabled = false;
					}
					else{
						$('salud_no_otro').disabled = false;
					}
					guardar_salud = 0;
					$('valor[69]').focus();
					return;
				}
			}
			//mujeres todas las opciones menos 6
			else{
				if ($('valor[69]').selectedIndex == 6) {
					alert("El valor selecionado en método de planificación no es permitido para el sexo del integrante ");
					if(metodo=='nuevo'){
						$('salud_otro').disabled = false;
						$('salud_salir').disabled = false;
					}
					else{
						$('salud_no_otro').disabled = false;
					}
					guardar_salud = 0;
					$('valor[69]').focus();
					return;
				}
			}	
		}
		
		parametros = "variables=" + vars.toJSON() + '&id_familia=' + $F('llave') +
		'&numerocorrelativo=' +
		$F('numerocorrelativo') +
		'&fecha=' +
		$F('fechallenado') +
		'&fechaoriginal=' +
		$F('fecha_original') +
		'&metodo=' +
		metodo +
		'&accion=guardar_estsalud';
		
		$('botones').hide();
		
		new Ajax.Request("/controladores/ficha_familiar_ctr2.php", {
			onSuccess: linea_salud,
			onCreate: function(){
				$('transparente').show();
			},
			onFailure: error,
			parameters: parametros,
			method: 'post'
		});
	}
	else{
			alert('Ya dio click una vez sobre el botón guardar, favor esperar a que se guarde el estado de salud');
	}
}

function probando_resp(resp){
	otro=resp.responseText;
    $('probando').update(otro);
		
}

function linea_salud(resp){
		resultado_salud=eval(resp.responseText);
		//$('aux').update(resultado_salud);
		if (resultado_salud[0] == 'ok') {
			/**********************FILA PARA SITUACION DE SALUD*******************************/
			fila_salud = '<TD align=center>' + $F('numerocorrelativo') + '</TD>' +
			'<TD align=center>' +
			$('valor[56]').options[$('valor[56]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center">' +
			$('valor[57]').options[$('valor[57]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center">' +
			resultado_salud[1] +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[59]').options[$('valor[59]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[60]').options[$('valor[60]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[61]').options[$('valor[61]').selectedIndex].text +
			'</TD>' +
			'<TD align=center>' +
			$('valor[62]').options[$('valor[62]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center">' +
			$('valor[63]').options[$('valor[63]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center">' +
			$('valor[64]').options[$('valor[64]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[65]').options[$('valor[65]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[66]').options[$('valor[66]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[67]').options[$('valor[67]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[68]').options[$('valor[68]').selectedIndex].text +
			'</TD>' +
			'<TD align=center>' +
			$('valor[69]').options[$('valor[69]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center">' +
			$('valor[70]').options[$('valor[70]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center">' +
			$('valor[71]').options[$('valor[71]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[72]').options[$('valor[72]').selectedIndex].text +
			'</TD>' +
			'<TD style="text-align:center" >' +
			$('valor[73]').options[$('valor[73]').selectedIndex].text +
			'</TD>';
			
			if($F('metodo')=='nuevo'){
				new Insertion.Bottom('salud_integrante', '<TR class="salud" id="salud-' + $F('numerocorrelativo') + '">' + fila_salud + '</TR>');
				if ($F('otro_miembro')== 'true'){
					guardar_salud=0;
					$('transparente').hide();
					agregar_est_salud();
				}					
				else {
					guardar_salud=0;
					$('transparente').hide();
					$('capa_agregar_familiar').update('.');
					$('capa_agregar_familiar').hide();
				}
			}
			else{
				guardar_salud=0;
				$('transparente').hide();
				$('salud-'+$F('numerocorrelativo')).innerHTML = fila_salud;
				$('capa_agregar_familiar').update('.');
				$('capa_agregar_familiar').hide();
			}
				
		}
		else{
			$('transparente').hide();
			$('botones').show();
			alert("No se pudo guardar el estado de salud, favor volver a intentarlo");
			if(metodo=='nuevo'){
				$('salud_otro').disabled = false;
				$('salud_salir').disabled = false;
			}
			else{
				$('salud_no_otro').disabled = false;
			}
			guardar_salud = 0;
		}
		if(metodo=='nuevo'){
			$('salud_otro').disabled = false;
			$('salud_salir').disabled = false;
		}
		else{
			$('salud_no_otro').disabled = false;
		}
	
}
function modificar_familiar(correlativo,seccion){
	$('capa_agregar_familiar').show();
	
	parametros='id_familia='+$F('llave')+'&fecha='+$F('fechallenado')+
				'&correlativo_integrante='+correlativo+'&seccion='+seccion+'&accion=modificar_familiar';
	
	var myAjax = new Ajax.Updater(
		'capa_agregar_familiar',
		'/controladores/ficha_familiar_ctr2.php',
		{
			method:'post',
			parameters:	parametros,
			evalScripts:true,
			onFailure:error	
		}
	);
	
}

function confirmar_borrar(correlativo){
	
	if (confirm('¿Borrar el integrante de familia?'))
		borrar(correlativo);
	
}

function borrar(correlativo){
	parametros='id_familia='+$F('llave')+
				'&correlativo_integrante='+correlativo+
				'&accion=borrar_familiar';
	
	new Ajax.Request("/controladores/ficha_familiar_ctr2.php", {
            onSuccess: borrar_linea,
            onFailure: error,
            parameters: parametros,
            method: 'post'
    });
	
}

function borrar_linea(resp){
	var integrantes;
	integrantes=0;
	corr=resp.responseText;
	
	//$('aux').update(corr);
	if (corr != 'error'){
		
		$('integrante-'+corr).remove();
		if($('salud-'+corr))
			$('salud-'+corr).remove();
			
		//Contando cuantos integrantes quedan
		$$('.integrante').each(function(nodo){
			integrantes++;
		})
		if(integrantes==0){
			$('tipo_riesgofamiliar').value='-1';
			$('riesgo_de_familia').innerHTML = 'NO ASIGNADO';
		}
		
	}else
		alert("No se ha podido eliminar el integrante de familia");
}

function verificar_numero(){
	var domicilio;
	var result = valid.validate();	
	if ($F('codigo_sit_vivienda') == '01') {
		if ($F('numerofamilia') == 0) {
			alert('El número de familia no puede se cero para una vivienda habitada');
			return;
		}
	}
	
	if(!$('codigo_municipio')){
		alert('No se ha seleccionado un municipio');
		return;
	}
	
	if(!$('codigo_domicilio')){
		alert('No se ha seleccionado un Cantón o Colonia');
		return;
	}
	
	if (result == true) {
		if ($F('codigo_area') == 'R') {
			domicilio = '&codigo_canton=' + $F('codigo_domicilio') + '&codigo_colonia=no';
		}
		else {
			domicilio = '&codigo_canton=no' + '&codigo_colonia=' + $F('codigo_domicilio');
		}
		
		parametros = 'codigo_departamento=' + $F('codigo_departamento') + '&codigo_municipio=' + $F('codigo_municipio') +
		'&codigo_area=' +
		$F('codigo_area') +
		'&accion=verificar_numero' +
		domicilio +
		'&codigo_zona=' +
		$F('codigo_zona') +
		'&numerovivienda=' +
		$F('numerovivienda') +
		'&numerofamilia=' +
		$F('numerofamilia');
		new Ajax.Request("/controladores/ficha_familiar_ctr2.php", {
			onSuccess: ya_existe,
			onFailure: error,
			parameters: parametros,
			method: 'post'
		});
	}
}

function ya_existe(resp){
	datos=eval(resp.responseText);
	longitud=datos.length;
	datos=datos[0];
	//$('aux').update(datos.estasib);	
	if(longitud==0){
		$('ficha_familiar_frm').submit();
	}
	else{
		alert("Ya existe este número de Expediente Familiar en la "+datos.estasib+" ingresada por el digitador "+datos.nombreusuario);
		return false;
	}
	
}

function cambiar_correlativo(nuevo_corr,celda,familia,id){
	var ya_existe_corr;
	ya_existe_corr='false';	
	numero=(celda.parentNode.id).split('-');
	corr_orig=numero[1];
	
	$$('.integrante').each(function(nodo){
		correlativo=nodo.id;
		datos=correlativo.split('-');
		corr_existente=datos[1];
		
		if(nuevo_corr==corr_existente){
			ya_existe_corr='true';
		}
	})
	if(ya_existe_corr=='true'){
		$(id).value=corr_orig;
		alert('Ya existe un integrante con este correlativo, favor modificar ese familiar antes de realizar esta operación');
		return;
	}
	parametros= 'nuevo_corr='+nuevo_corr+'&corr_orig='+corr_orig+'&id_familia='+familia+'&accion=cambiar_correlativo';
	
	new Ajax.Request("/controladores/ficha_familiar_ctr2.php", {
			onSuccess: actualizar_corr,
			onFailure: error,
			parameters: parametros,
			method: 'post'
	});
	
}

function actualizar_corr(resp){
	resp=resp.responseText;
	if(resp!='error'){
		datos=resp.split('-');
		original=datos[0];
		nuevo=datos[1];
		$('integrante-'+original).id='integrante-'+nuevo;
		alert('Correlativo de Integrante Modificado');
	}
	else{
		alert('Ocurrió un error al modificar el correlativo')
	}
	
}

function guardar_completa(){
	var integrantes,estados_salud;
	integrantes=0;
	estados_salud=0;
	
	if ($F('codigo_sit_vivienda') == '01') {
		if($F('numerofamilia')==0){
		 	alert('El número de familia no puede se cero para una vivienda habitada');
			return;
		 }
		
		$$('.integrante').each(function(nodo){
			integrantes++;
		})
		
		$$('.salud').each(function(nodo){
			estados_salud++;
		})
		if (integrantes == 0 ) {
			alert('No ha agregado ningún integrante de familia')
			return;
		//parametros= Form.serialize('ficha_familiar_frm')+'&accion=guardar_completa';
		}
		if (integrantes != estados_salud) {
			alert('No ha agregado estado de salud a un integrante de familia')
			return;
		//parametros= Form.serialize('ficha_familiar_frm')+'&accion=guardar_completa';
		}
		if ($F('tipo_riesgofamiliar') == '-1') {
			alert('No ha asignado un valor al Riesgo Familiar');
			return;
		}
		else {
			parametros = Form.serialize('ficha_familiar_frm') + '&riesgo=' + $F('tipo_riesgofamiliar') + '&accion=guardar_completa';
		}
	}
	else{
		parametros = Form.serialize('ficha_familiar_frm') + '&accion=guardar_completa';
	}
	
	var result = valid.validate();
	
	if(result==true){
		var myAjax = new Ajax.Updater(
			'mensaje_duplicado',
			'/controladores/ficha_familiar_ctr2.php',
			{
				method:'post', 
				parameters:	parametros,
				evalScripts:true,
				onFailure:error	
			}
		);
	}
}

function actualizar_riesgo(){
	var integrantes;
	integrantes=0;
	
	$$('.integrante').each(function(nodo){
		integrantes++;
	})
	
	if (integrantes != 0) {
		if ($F('tipo_riesgofamiliar') != '-1') {
			if ($F('tipo_riesgofamiliar') == 1) {
				$('riesgo_de_familia').innerHTML = '1 - RIESGO ALTO';
			}
			else 
				if ($F('tipo_riesgofamiliar') == 2) {
					$('riesgo_de_familia').innerHTML = '2 - RIESGO MEDIO';
				}
				else {
					$('riesgo_de_familia').innerHTML = '3 - RIESGO BAJO';
				}
		}
		else 
			$('riesgo_de_familia').innerHTML = 'NO ASIGNADO';
	}
	else {
		$('tipo_riesgofamiliar').value='-1';
		$('riesgo_de_familia').innerHTML = 'NO ASIGNADO';
		alert('Debe ingresar al menos un integrante de la familia para asignar el Tipo de Riesgo Familiar');
	}
}

function error(){
	alert ('Ocurrió un error al consultar el servidor, comuníquese con el administrador.');
	$('transparente').hide();
}
