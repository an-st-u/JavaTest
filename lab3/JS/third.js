function addRow() {
	
	freezing(1);
	var suda = document.getElementById('tablitsa');
	
	var row = suda.insertRow(-1);
	addCellInput(row,"","text"); // строка, тип, текст
	addCellInput(row,"","text");
	addCellInput(row,"","text");
	addCell(row,"Save","input","submit");


	var Confirm = row.childNodes[3].childNodes[0];
	Confirm.classList.add("del_btn");
    	Confirm.onclick = function() {
		confirm(this.parentNode.parentNode);
		freezing(0);
	}

}

function addCellInput(row, in_text, type_text,id) {

	var cell = row.insertCell(-1);
	var elem = document.createElement("input");
	elem.classList.add("vvod");
	elem.size = 25;
	elem.type = type_text || null;
	elem.value = in_text || "";
	elem.innerHTML = in_text || "";
	cell.appendChild(elem); 

}


function addCell(row, in_text, tag, type_text) {

	var cell = row.insertCell(-1);
	if (tag) {
		var elem = document.createElement(tag);
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

	sendText(baza);


	for (var i=0;i<3;i++) {
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

function EditRow(row,baza) {
	
	freezing(1);	
	for (var i=1;i>=0;i--) {
		var Confirm = row.childNodes[3].childNodes[i];
		Confirm.parentNode.removeChild(Confirm);
	}

	
	for (var i=3;i>=0;i--) {
		
		var Confirm = row.childNodes[i];
		row.removeChild(Confirm);
	}

	for (var i=0;i<3;i++) {
		addCellInput(row,baza[i],"text");
	}

	addCell(row,"Save","input","submit");


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
		_remove();
		}

	}

}

function sendText(baza){
	var sendtext = new XMLHttpRequest();
	sendtext.open('POST','/sendText',true);
	sendtext.send("#"+baza[0]+"#"+baza[1]+"#"+baza[2]);
	sendtext.onreadystatechange = function() {
	if(this.readyState ==4 && this.status == 200) {
		}
	}
}
