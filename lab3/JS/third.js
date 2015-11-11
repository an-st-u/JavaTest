getBaza();

function addRow() {
	
	freezing(1);
	var suda = document.getElementById('tablitsa');
	
	var row = suda.insertRow(-1);
	addCell(row,"","input","text","vvod_kino"); // строка, текст, тэг, тип, класс
	addCell(row,"","input","text","vvod_kino");
	addCell(row,"","input","text","vvod_kino");
	addCell(row,"Save","input","submit","del_btn");


	var Confirm = row.childNodes[3].childNodes[0];
    	Confirm.onclick = function() {
		confirm(this.parentNode.parentNode);
		freezing(0);
	}

}



function addCell(row, in_text, tag, type_text, uclass) {

	var cell = row.insertCell(-1);
	if (tag) {
		var elem = document.createElement(tag);
		elem.classList.add(uclass);
		elem.type = type_text || null;
		elem.value = in_text || "";
		elem.innerHTML = in_text || "";
		cell.appendChild(elem); 
	} else {
		cell.innerHTML = in_text || "";
	}
	
}


function _remove() {
		
	var suda = document.getElementById('tablitsa');
	delText(suda.rows.length-1);
	if (suda.rows.length!=1) {
		suda.deleteRow(-1);
	}
}

function сlearDefault(){
	
	var elem = document.getElementById("nod_1").value;
	if ((elem == "Вводить нужно здесь...") || (elem == "Введите в правильной форме, пожалуйста...") ) {
	document.getElementById("nod_1").value="";
	}
}

function removeIndex(row) {
	
	var suda = document.getElementById('tablitsa');
	delText(row.parentNode.parentNode);
	suda.deleteRow(row.parentNode.parentNode.rowIndex);
	

}

function confirm(row) {


	var baza = [];
	var temp = row.childNodes[3].childNodes[0];
	row.removeChild(temp.parentNode);
	
	for (var i=2;i>=0;i--) {
		
		var Confirm = row.childNodes[i].childNodes[0];
		baza[i]=Confirm.value;
		row.removeChild(Confirm.parentNode);
	}

	sendText(baza,row); //


	for (var i=0;i<3;i++) {
		addCell(row,baza[i]);
	}

	addCell(row,"Del","input","submit","del_btn");

	var delButton = row.childNodes[3].childNodes[0];
    	delButton.onclick = function() {
		removeIndex(this);
	}

	delButton = row.childNodes[3];
	var trc = document.createElement('input');
	delButton.appendChild(trc);
	trc.value = "Edit";
	trc.type = "submit";
	trc.classList.add("del_btn");
    	trc.onclick = function() {
		EditRow(this.parentNode.parentNode,baza);
	}
	

}

function EditRow(row,baza) {

	for (var i=2;i>=0;i--) {
		
		var Confirm = row.childNodes[i];
		baza[i]=Confirm.innerHTML;
	}

	freezing(1);	
	
	for (var i=3;i>=0;i--) {
		
		var Confirm = row.childNodes[i];
		row.removeChild(Confirm);
	}

	for (var i=0;i<3;i++) {
		addCell(row,baza[i],"input","text","vvod_kino");
	}

	addCell(row,"Save","input","submit","del_btn");


	var Confirm = row.childNodes[3].childNodes[0];
	Confirm.classList.add("del_btn");
    	Confirm.onclick = function() {
		confirm(this.parentNode.parentNode);
		freezing(0);
	}
}

function freezing(i) {
	
	var cold = document.getElementById('add');
	var cold2 = document.getElementById('remall');
	
	if (i==1) {	
		cold.value = "Wait...";
		cold.onclick = "";
		cold2.value = "Удаление пока невозможно";
		cold2.onclick = "";
	} else {
		cold.value = "Добавить";
		cold.onclick = function() {
		addRow();
		}
		cold2.value = "Убрать последнюю строку";
		cold2.onclick = function() {
		_remove(this);
		}

	}

}

function sendText(baza,row){
	var sendtext = new XMLHttpRequest();
	sendtext.open('POST','/sendText',true);
	sendtext.send("&row="+(row.rowIndex-1)+"#"+baza[0]+"#"+baza[1]+"#"+baza[2]);
	sendtext.onreadystatechange = function() {
	if(this.readyState ==4 && this.status == 200) {
		}
	}
}

function delText(row){
	var sendtext = new XMLHttpRequest();
	sendtext.open('POST','/delText',true);
	sendtext.send("&row="+(row.rowIndex-1));
	sendtext.onreadystatechange = function() {
	if(this.readyState ==4 && this.status == 200) {
		}
	}
}

function getBaza(){
	var sendtext = new XMLHttpRequest();
	sendtext.open('POST','/getBaza',true);
	sendtext.send();
	sendtext.onreadystatechange = function() {
	if(this.readyState ==4 && this.status == 200) {
			var answer = sendtext.responseText;
			fillBaza(answer); 
		}
	}
}


function fillBaza(text) {

	var baza = text.split("#");
	var suda = document.getElementById('tablitsa');	

	for (var rws=0;rws<baza.length-1;rws+=3) {

		var row = suda.insertRow(-1);

		for (var i=rws;i<rws+3;i++) {
			addCell(row,baza[i]);
		}

		addCell(row,"Del","input","submit");

		var delButton = row.childNodes[3].childNodes[0];
		delButton.classList.add("del_btn");
		delButton.onclick = function() {
			removeIndex(this);
		}

		delButton = row.childNodes[3];
		var trc = document.createElement('input');
		delButton.appendChild(trc);
		trc.value = "Edit";
		trc.type = "submit";
		trc.classList.add("del_btn");
		trc.onclick = function() {
			EditRow(this.parentNode.parentNode,baza);
		}

	}


}
