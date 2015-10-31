function sendText(){
var elem = document.getElementById("nod_1").value;
alert(elem);
}

function сlearDefault(){
var elem = document.getElementById("nod_1").value;
if (elem == "Вводить нужно здесь...") {
document.getElementById("nod_1").value="";
}
}

function сlear(){
document.getElementById("nod_1").value="";
}
