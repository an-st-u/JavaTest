function add() {
	
	var suda = document.getElementById('tablitsa');
	var name = document.getElementById('name').value;
	var address = document.getElementById('address').value;
	var site = document.getElementById('site').value;
	var tr = document.createElement('tr');
	tr.innerHTML = '<td>'+name+'</td><td>'+address+'</td>' +
        '<td><a href="http://'+site+'">'+name+'</a></td>';
	suda.appendChild(tr);
}

function _remove() {
		
	var suda = document.getElementById('tablitsa');
	//alert(suda.lastElementChild.innerHTML);
	if (suda.lastElementChild!=suda.firstElementChild) {
	suda.removeChild(suda.lastElementChild);
	}
}

function сlearDefault(){
	
	var elem = document.getElementById("nod_1").value;
	if ((elem == "Вводить нужно здесь...") || (elem == "Введите в правильной форме, пожалуйста...") ) {
	document.getElementById("nod_1").value="";
	}
}
