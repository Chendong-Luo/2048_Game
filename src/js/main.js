window.onload=()=>{
    gentable();
}
window.onkeydown=(e)=>{
    if(e.key == 'ArrowRight') moveRight();
    else if(e.key == 'ArrowLeft') moveLeft();
    else if(e.key == 'ArrowDown') moveDown();
    else if(e.key == 'ArrowUp') moveUp();
    console.log(e.key);
}
table = new Array();
tableValue = null;
function gentable() {
    main = document.getElementById("main");
    columns = document.createElement('div');
    columns.setAttribute('class', 'cavcol');
    main.appendChild(columns);
    for(var i=0; i<4; i++){
        tmprow = new Array();
        row = document.createElement('div');
        row.setAttribute('class', 'cavrow');
        columns.appendChild(row);
        for(var j=0; j<4; j++){
            item = document.createElement('div');
            item.setAttribute('class', 'cavitem');
            item.innerText = (i+1)*(j+1);
            row.appendChild(item);
            tmprow.push(item)
        }
        table.push(tmprow);
    }
}

function fetchValue(){
    tableValue = new Array();
    table.forEach((row)=>{
        tmprow = new Array();
        row.forEach((item)=>{
            tmprow.push(Number(item.innerText));
        })
        tableValue.push(tmprow);
    })
    console.log(tableValue);
}

function moveRight(){
    fetchValue();
}
function moveLeft(){

}
function moveDown(){

}
function moveUp(){

}
