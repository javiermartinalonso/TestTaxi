/**
 * Definicion de valores para el componente datetimeentry de ace. Carga en
 * navegador via objeto JSON.
 */
ice.ace.locales['es'] = {
	closeText : 'Cerrar',
	prevText : 'Previo',
	nextText : 'Próximo',
	currentText : 'Corriente',
	monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
			'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
			'Diciembre' ],
	monthNamesShort : [ 'ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul', 'ago',
			'sep', 'oct', 'nov', 'dic' ],
	dayNames : [ 'domingo', 'lunes', 'martes', 'miércoles', 'jueves',
			'viernes', 'sábado' ],
	dayNamesShort : [ 'dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb' ],
	dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá' ],
	weekHeader : 'Sm',
	dateFormat : 'dd/mm/yy',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : ''
};




function modalwin(url){
	if(document.all&&window.print)
	{
		eval('window.showModelessDialog(url,"","help:1;resizable:1;scrollbars=1;dialogWidth:10000px;dialogHeight:10000px")');
	}
		
	else
	{
//		eval('window.open(url,"",resizable=1,scrollbars=1,left=20,top=20")');	
		eval('window.open(url,"test","scrollbars=yes,fullscreen=yes,resizable=yes")');
	}
	
//	
//	begin();
//	cuentaAtras(segundos);
};




//http://javascript.about.com/library/blmodal.htm
//function modalWin() {
//	if (window.showModalDialog) {
//		window.showModalDialog("xpopupex.htm","name", "dialogWidth:255px;dialogHeight:250px");
//	} else {
//		window.open('xpopupex.htm','name', 'height=255,width=250,toolbar=no,directories=no,status=no, continued from previous linemenubar=no,scrollbars=no,resizable=no ,modal=yes');
//	}
//}



//cuenta atras
function cuentaAtras(segundos,metodo)
{
	mi_metodo = metodo;
//	alert(segundos);
	var intSegundos = parseInt(segundos);	

	
//	alert("cuentaAtras");
	
	intSegundos=parseInt(intSegundos)-1;

	if(intSegundos>0)
	{
//		alert("segundos" + intSegundos);
		setTimeout("cuentaAtras(intSegundos, mi_metodo)",1000);
	}
	else
	{
//		alert("para_barra");
//		stop();
		void(metodo);
	}
}



//function begin()
//{
//	alert("begin");
////    this.progressInterval = 
//    	setInterval (cargar() , 1000);
//}
//
//function stop() 
//{
//	alert("stop");
////    clearInterval(this.progressInterval);
////    clientSideBar.setValue(0);
//}
//
//function cargar()
//{
//	alert("cargar");
//    //clientSideBar.setValue(clientSideBar.getValue() + 10);	
//	alert("cargar_barra");
//}

//
//
//<input type="button" value="Modo Modal" onclick="javascript:window.showModalDialog('http://norfipc.com/index.html');" />
//<input type="button" value="Modo Modeless" onclick="javascript:window.showModelessDialog('http://norfipc.com/index.html');" />


function prepareScr(){
	window.moveTo(0, 0);
	window.resizeTo(window.screen.width, window.screen.height-50);
}


 


//
//	var tecla;
//	ns4 = (document.layers)? true:false;
//	alert(ns4);
//	ie4 = (document.all)? true:false;
//	alert(ie4);
//	document.onkeydown = keyDown;
//	if (ns4) document.captureEvents(Event.KEYDOWN);
//	function keyDown(e) {
//		if (ns4) {
//			var nKey = e.which;
//			tecla=nKey;
//		}
//		if (ie4) {
//			var ieKey = event.keyCode;
//			tecla=ieKey;
//		}
//		alert('Código'+tecla);
//	}
//
//
////
document.onkeydown = function()
{ 
	if(window.event && window.event.keyCode == 116)         
	{ 
     	window.event.keyCode = 505; 
     	return false; 
   	}
//	if(window.event && window.event.keyCode == 505) 
//    { 
//		return false;    
//    } 
//	
//	tecla;
//	ns4 = (document.layers)? true:false;
//	alert(ns4);
//	ie4 = (document.all)? true:false;
//	alert(ie4);
//	document.onkeydown = keyDown;
//	if (ns4) document.captureEvents(Event.KEYDOWN);
};


//variables que determinan el total de horas, minutos y segundos para la cuenta atras
//intHora=0;
//intMinutos=0;
//intSegundos=30;


//
//var hora_restante = document.getElementById('formu_test:hora_restante');
//var minutos_restante = document.getElementById('formu_test:minutos_restante');
//var segundos_restante = document.getElementById('formu_test:segundos_restante');









//function $(id){
//	return document.getElementById(id);
//}
//
//function init()
//{
//
//	shortcut("Ctrl+F",function()
//	{
//		$('nombreFactura').focus(); 
//	});
//	shortcut("Ctrl+R",function()
//	{
//		$('nombreD').focus();
//	});
//	shortcut("Ctrl+D",function()
//	{
//		$('nombreC').focus();
//	});
//	shortcut("Ctrl+G",function()
//	{
//		guardarEncomienda();
//	});
//
//}
//
//
//function shortcut(shortcut,callback,opt) {
//	//Provide a set of default options
//	var default_options = {	'type':'keydown', 'propagate':false, 'target':document};
//	if(!opt) 
//		opt = default_options;
//	else {
//		for(var dfo in default_options) {
//			if(typeof opt[dfo] == 'undefined') opt[dfo] = default_options[dfo];
//		}
//	}
//	var ele = opt.target;
//	if(typeof opt.target == 'string') 
//		ele = document.getElementById(opt.target);
//	var ths = this;
//	//The function to be called at keypress
//	var func = function(e) {
//		e = e || window.event;
//		//Find Which key is pressed
//		if (e.keyCode) code = e.keyCode;
//		else if (e.which) code = e.which;
//		var character = String.fromCharCode(code).toLowerCase();
//		var keys = shortcut.toLowerCase().split('+');
//		//Key Pressed - counts the number of valid keypresses - if it is same as the number of keys, the shortcut function is invoked
//		var kp = 0;
//		//Work around for stupid Shift key bug created by using lowercase - as a result the shift+num combination was broken
//		var shift_nums = {'1':'!','2':'@','3':'#','4':'$','5':'%','6':'^',' 7':'&','8':'*','9':')','0':')','-':'_','=':'+',';':':',':':'<','.':'>','/':'?','\\':'|'};
//		//Special Keys - and their codes
//		var special_keys = {
//				'esc':27,
//				'escape':27,
//				'tab':9,
//				'space':32,
//				'return':13,
//				'enter':13,
//				'backspace':8,
//				'scrolllock':145,
//				'scroll_lock':145,
//				'scroll':145,
//				'capslock':20,
//				'caps_lock':20,
//				'caps':20,
//				'numlock':144,
//				'num_lock':144,
//				'num':144,
//				'pause':19,
//				'break':19,
//				'insert':45,
//				'home':36,
//				'delete':46,
//				'end':35,
//				'pageup':33,
//				'page_up':33,
//				'pu':33,
//				'pagedown':34,
//				'page_down':34,
//				'pd':34,
//				'left':37,
//				'up':38,
//				'right':39,
//				'down':40,
//				'f1':112,
//				'f2':113,
//				'f3':114,
//				'f4':115,
//				'f5':116,
//				'f6':117,
//				'f7':118,
//				'f8':119,
//				'f9':120,
//				'f10':121,
//				'f11':122,
//				'f12':123
//		};
//		for(var i=0; k=keys[i],i<keys.length; i++) {
//			//Modifiers
//			if(k == 'ctrl' || k == 'control') {
//				if(e.ctrlKey) kp++;
//			} else if(k == 'shift') {
//				if(e.shiftKey) kp++;
//			} else if(k == 'alt') {
//				if(e.altKey) kp++;
//			} else if(k.length > 1) { //If it is a special key
//				if(special_keys[k] == code) kp++;
//			} else { //The special keys did not match
//				if(character == k) kp++;
//				else {
//					if(shift_nums[character] && e.shiftKey) { //Stupid Shift key bug created by using lowercase
//						character = shift_nums[character];
//						if(character == k) kp++;
//					}
//				}
//			}
//		}
//		if(kp == keys.length) {
//			callback(e);
//			if(!opt['propagate']) { //Stop the event
//				//e.cancelBubble is supported by IE - this will kill the bubbling process.
//				e.cancelBubble = true;
//				e.returnValue = false;
//				//e.stopPropagation works only in Firefox.
//				if (e.stopPropagation) {
//					e.stopPropagation();
//					e.preventDefault();
//				}
//				return false;
//			}
//		}
//	};
//
//	//Attach the function with the event
//	if(ele.addEventListener) ele.addEventListener(opt['type'], func, false);
//	else if(ele.attachEvent) ele.attachEvent('on'+opt['type'], func);
//	else ele['on'+opt['type']] = func;
//}



function cerrar_pagina(){ 
	window.close();
	} 


var preguntaActualizada;
var preguntaActual;



function preguntasRefresh()
{
	if (!preguntaActualizada)
	{
		str_contestadas = document.getElementById('formu_test:pregunta_contestadas').value;
		contestadas = parseInt(str_contestadas);		
		contestadas = contestadas +1;
		
		if(contestadas<10)
		{
			str_contestadas = "0"+contestadas;
		}
		else
		{
			str_contestadas = contestadas;
		}		
		document.getElementById('formu_test:pregunta_contestadas').value =  str_contestadas;
		
		
		str_pendientes = document.getElementById('formu_test:pregunta_pendientes').value;
		pendientes = parseInt(str_pendientes);
		pendientes = pendientes - 1;
		
		if(pendientes<10)
		{
			str_pendientes = "0"+pendientes;
		}
		else
		{
			str_pendientes = pendientes;
		}
		
		document.getElementById('formu_test:pregunta_pendientes').value = str_pendientes;
		preguntaActualizada = true;
	}
}

function activarActualizarPreguntas()
{
	preguntaActualizada = false;
}



function preguntaActualIncrement()
{
		str_actual = document.getElementById('formu_test:pregunta_actual').value;
		actual = parseInt(str_actual);		
		actual = actual +1;
		
		if(actual<10)
		{
			str_actual = "0"+actual;
		}
		else
		{
			str_actual = actual;
		}		
		document.getElementById('formu_test:pregunta_actual').value = str_actual;
}


function preguntaActualDecrement()
{
		str_actual = document.getElementById('formu_test:pregunta_actual').value;
		actual = parseInt(str_actual);		
		actual = actual -1;
		
		if(actual<10)
		{
			str_actual = "0"+actual;
		}
		else
		{
			str_actual = actual;
		}		
		document.getElementById('formu_test:pregunta_actual').value = str_actual;
}



//cuenta atras
function countUpRefresh(Hour, Min, Seg)
{
//	alert(Min);
	var intHora = parseInt(Hour);
	var intMinutos = parseInt(Min);
	var intSegundos = parseInt(Seg);	
	
//alert(intSegundos);
//	countUp(intHora+"", intMinutos+"", intSegundos+"");
	document.getElementById('formu_test:hora_duracion').value = Hour;
	document.getElementById('formu_test:minutos_duracion').value = Min;
	document.getElementById('formu_test:segundos_duracion').value = Seg;
}



//cuenta atras
function countDownRefresh(Hour, Min, Seg)
{
//	alert(Min);
	var intHora = parseInt(Hour);
	var intMinutos = parseInt(Min);
	var intSegundos = parseInt(Seg);	
//	
//	
//	countDown(intHora+"", intMinutos+"", intSegundos+"");
	document.getElementById('formu_test:hora_restante').value = Hour;
	document.getElementById('formu_test:minutos_restante').value = Min;
	document.getElementById('formu_test:segundos_restante').value = Seg;
}



//cuenta atras
function countDown(Hour, Min, Seg)
{
//	alert(Min);
	var str_horas = 00;
	var str_minutos = 00;
	var str_segundos = 00;	
	var intHora = parseInt(Hour);
	var intMinutos = parseInt(Min);
	var intSegundos = parseInt(Seg);	
	
	
//	alert("countDown");
	
	intSegundos=parseInt(intSegundos)-1;
	if(intSegundos<0)
	{
		intSegundos=parseInt(59);
		intMinutos=parseInt(intMinutos)-1;
	}
	
	str_segundos = intSegundos.toString();
	if((intSegundos<10)&&(str_segundos.length<2))
	{
		str_segundos = 0+str_segundos;
	}
	

	//segundos_restante.value=str_segundos;
	document.getElementById('formu_test:segundos_restante').value=str_segundos;
	

	if(intMinutos<0)
	{
		intMinutos=parseInt(59);
		intHora=parseInt(intHora)-1;
	}


	str_minutos = intMinutos.toString();
	if((intMinutos<10)&&(str_minutos.length<2))
	{
		str_minutos = 0+str_minutos;
	}

	//minutos_restante.value=str_minutos;
	document.getElementById('formu_test:minutos_restante').value=str_minutos;
	
	str_horas = intHora.toString();

	if((intHora<10)&&(str_horas.length<2))
	{
		str_horas = 0+str_horas;
	}

	if (intHora>=0)
	{
		document.getElementById('formu_test:hora_restante').value=str_horas;	
	}


	if(intHora<0)
	{
		//final
		//alert(document.getElementById("formu_test:response"));
//		str_segundos.value=00;
//		str_minutos.value=00;
//		str_horas.value=00;
		window.scrollTo(0,0);
		document.getElementById("formu_test:enviar_oculto").click();
		primeraVez = true;
	}else{
		setTimeout("countDown(document.getElementById('formu_test:hora_restante').value, document.getElementById('formu_test:minutos_restante').value, document.getElementById('formu_test:segundos_restante').value)",1000);
	}
}




//function cambioPagina(pagina) {
//
////alert("cambioPagina");
//
////		$('#panel_paginas').attr('src', pagina);
//
//
//
//}





//var hora_duracion = document.getElementById("formu_test:hora_duracion");
//var minutos_duracion = document.getElementById("formu_test:minutos_duracion");
//var segundos_duracion = document.getElementById("formu_test:segundos_duracion");

//cronometro incremental
function countUp(Hora, Minutos, Segundos)
{
	
//	alert(Segundos);
	var str_horas = 00;
	var str_minutos = 00;
	var str_segundos = 00;	
	var intHora = parseInt(Hora);
	var intMinutos = parseInt(Minutos);
	var intSegundos = parseInt(Segundos);	

//	alert(Hora);
//	alert(Minutos);
//	alert(Segundos);

	intSegundos=parseInt(intSegundos)+1;
	if(intSegundos>59)
	{
		intSegundos=parseInt(0);
		intMinutos=parseInt(intMinutos)+1;
		str_minutos=0+intMinutos;
	}
	
	str_segundos = intSegundos.toString();
	if((intSegundos<10)&&(str_segundos.length<2))
	{
		str_segundos = 0+str_segundos;
	}
	
//	alert("dos");
//	alert(document.getElementById('formu_test:segundos_duracion').value);
//	segundos_duracion.value=str_segundos;
	document.getElementById('formu_test:segundos_duracion').value=str_segundos;
	
	if(intMinutos>59)
	{
		intMinutos=parseInt(0);
		intHora=parseInt(intHora)+1;
	}


	str_minutos = intMinutos.toString();
	if((intMinutos<10)&&(str_minutos.length<2))
	{
		str_minutos = 0+str_minutos;
	}

//	minutos_duracion.value=str_minutos;
	document.getElementById('formu_test:minutos_duracion').value=str_minutos;

	
	str_horas = intHora.toString();

	if((intHora<10)&&(str_horas.length<2))
	{
		str_horas = 0+str_horas;
	}
	
//	hora_duracion.value=str_horas;
	document.getElementById('formu_test:hora_duracion').value=str_horas;
//	alert(str_horas);
	if(intHora>23)
	{
		intHora=parseInt(0);
		intHora=0+intHora;
	}else{
				
		setTimeout("countUp(document.getElementById('formu_test:hora_duracion').value, document.getElementById('formu_test:minutos_duracion').value, document.getElementById('formu_test:segundos_duracion').value)",1000);
	}
}

