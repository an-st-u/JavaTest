function сlearDefault(){
var elem = document.getElementById("nod_1").value;
if (elem == "Вводить нужно здесь...") {
document.getElementById("nod_1").value="";
}
}

function sendText(){
var elem = document.getElementById("nod_1").value;
var sendtext = new XMLHttpRequest();
sendtext.open('POST','/JS/1.html',true);
//sendtext.setRequestHeader('Content-Type', 'application/json');
//sendtext.setRequestHeader('Content-Type', 'text/html');
sendtext.send("elem="+elem);
sendtext.onreadystatechange = function(e) {
if(this.readyState ==4 && this.status == 200) {
document.getElementById("nod_1").value = sendtext.responseText;
}
}
}

function сlear(){
document.getElementById("nod_1").value="";
}
