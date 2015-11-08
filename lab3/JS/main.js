function сlearDefault(){
	var elem = document.getElementById("nod_1").value;
	if ((elem == "Вводить нужно здесь...") || (elem == "Введите в правильной форме, пожалуйста...") ) {
	document.getElementById("nod_1").value="";
	}
}



function сlear(){
	document.getElementById("nod_1").value="";
}



function sendText(){
	var elem = document.getElementById("nod_1").value;
	var sendtext = new XMLHttpRequest();
	sendtext.open('POST','/sendNOD',true);
	sendtext.send("elem="+elem);
	sendtext.onreadystatechange = function(e) {
	if(this.readyState ==4 && this.status == 200) {
		document.getElementById("nod_1").value = sendtext.responseText;
		}
	}
}
