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
tableItem = new Array();
tableValue = null;

class Item{
    constructor(){
        this.item = null;
        this.right = null;
        this.left = null;
        this.up = null;
        this.left = null;
        this.idle = 1;
    }
}

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
            index = i*4 + j;
            item.innerText = 0;

            itemobj = new Item();
            itemobj.item = item;
            if(j>0){
                tableItem[index-1].right = itemobj;
                itemobj.left = tableItem[index-1];
            }
            if(i>0){
                itemobj.up = tableItem[index-4];
                tableItem[index-4].down = itemobj;
            }
            tableItem.push(itemobj);

            row.appendChild(item);
            tmprow.push(item)
        }
        table.push(tmprow);
    }
    generateRandom();
}

function generateRandom(){
    var rand = randomChooseIdle();
    rand.idle = 0;
    rand.item.innerText = 2;
}

function getUsingItems(){
    UsingItems = new Array();
    tableItem.map((i)=>{
        if(i.idle == 0) UsingItems.push(i);
    });
    return UsingItems;
}

function randomChooseIdle(){
    IdleItems = new Array();
    tableItem.map((i)=>{
        if(i.idle == 1) IdleItems.push(i);
    });
    return IdleItems[Math.floor(Math.random() * IdleItems.length)];
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
    console.log(tableItem);
    console.log(randomChooseIdle());
}

function moveItem(srcitem, destitem){
    destitem.item.innerText = srcitem.item.innerText;
    srcitem.item.innerText = 0;
    destitem.idle = 0;
    srcitem.idle = 1;
}

function moveRight(){
    fetchValue();
    using = getUsingItems();
    using.map((i)=>{
        if(i.right != null){
            var tmp = i.right;
            while(1){
                if(tmp.right == null && tmp.idle == 1){
                    moveItem(i, tmp);
                    break;
                }
                if(tmp.right == null && tmp.idle == 0){
                    break;
                }
                if(tmp.idle == 0){
                    break;
                }
                if(tmp.idle == 1 && tmp.right.idle == 1){
                    tmp = tmp.right;
                }
                if(tmp.idle == 1 && tmp.right.idle == 0){
                    moveItem(i, tmp);
                    break;
                }
            }
        }
    });
    generateRandom();

}
function moveLeft(){

}
function moveDown(){

}
function moveUp(){

}
