function addRow() {
	
	var suda = document.getElementById('tablitsa');
	var name = document.getElementById('name').value;
	var address = document.getElementById('address').value;
	var site = document.getElementById('site').value;
	
	var row = suda.insertRow(-1);
	addCell(row,name); // строка, тип, текст
	addCell(row,address);
	addCell(row,site);
	addCell(row,"","input","submit");
	
	var delButton = row.childNodes[3].childNodes[0];
	delButton.classList.add("del_btn");
    	delButton.onclick = function() {
		removeIndex(this);
	}
}

function addCell(row, in_text, tag, value_text) {

	var cell = row.insertCell(-1);
	if (tag) {
		var elem = document.createElement(tag);
		elem.type = value_text || null;
		elem.value = in_text || "";
		elem.innerHTML = in_text || "";
		cell.appendChild(elem); }
	else {
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
