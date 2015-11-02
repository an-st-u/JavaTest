function add() {
	
	var suda = document.getElementById('tablitsa')
	var kinoteatr = document.getElementById('nod_1').value;
	var tr = document.createElement('tr');
	tr.id = 'dorem';
	tr.innerHTML = '<td>'+kinoteatr+'</td><td>ул. Толмачева, 12</td><td><a href="http://www.kinosalut.ru/">Киносалют</a></td>';	
	suda.appendChild(tr);
}

function _remove() {
	var suda = document.getElementById('tablitsa')
	var tr = document.getElementById('dorem')
	suda.removeChild(tr);
}

function сlearDefault(){
	var elem = document.getElementById("nod_1").value;
	if ((elem == "Вводить нужно здесь...") || (elem == "Введите в правильной форме, пожалуйста...") ) {
	document.getElementById("nod_1").value="";
	}
}
