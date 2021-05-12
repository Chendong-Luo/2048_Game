window.onload=()=>{
    gentable();
}
window.onkeydown=(e)=>{
    if(e.key == 'ArrowRight') moveRight(1);
    else if(e.key == 'ArrowLeft') moveLeft(1);
    else if(e.key == 'ArrowDown') moveDown(1);
    else if(e.key == 'ArrowUp') moveUp(1);
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
    if(rand != null){
        rand.idle = 0;
        rand.item.innerText = 2;
    }
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
    if(IdleItems.length == 0) return null;
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

function addItem(srcitem, destitem){
    destitem.item.innerText = srcitem.item.innerText * 2;
    srcitem.item.innerText = 0;
    srcitem.idle = 1;
}

function moveRight(flag){
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
                    if(tmp.item.innerText == i.item.innerText){
                        addItem(i, tmp);
                    }
                    break;
                }
                if(tmp.idle == 0){
                    if(tmp.item.innerText == i.item.innerText){
                        addItem(i, tmp);
                    }
                    break;
                }
                if(tmp.idle == 1 && tmp.right.idle == 1){
                    tmp = tmp.right;
                    continue;
                }
                if(tmp.idle == 1 && tmp.right.idle == 0){
                    if(tmp.right.item.innerText == i.item.innerText){
                        addItem(i, tmp.right);
                    }else{
                        moveItem(i, tmp);
                    }
                    break;
                }
            }
        }
    });
    generateRandom();
    if(flag > 0){
        moveRight(flag - 1);
    }else{
        return;
    }

}
function moveLeft(flag){
    fetchValue();
    using = getUsingItems();
    using.map((i)=>{
        if(i.left != null){
            var tmp = i.left;
            while(1){
                if(tmp.left == null && tmp.idle == 1){
                    moveItem(i, tmp);
                    break;
                }
                if(tmp.left == null && tmp.idle == 0){
                    if(tmp.item.innerText == i.item.innerText){
                        addItem(i, tmp);
                    }
                    break;
                }
                if(tmp.idle == 0){
                    if(tmp.item.innerText == i.item.innerText){
                        addItem(i, tmp);
                    }
                    break;
                }
                if(tmp.idle == 1 && tmp.left.idle == 1){
                    tmp = tmp.left;
                    continue;
                }
                if(tmp.idle == 1 && tmp.left.idle == 0){
                    if(tmp.left.item.innerText == i.item.innerText){
                        addItem(i, tmp.left);
                    }else{
                        moveItem(i, tmp);
                    }
                    break;
                }
            }
        }
    });
    generateRandom();
    if(flag > 0){
        moveLeft(flag - 1);
    }else{
        return;
    }

}
function moveDown(flag){
    fetchValue();
    using = getUsingItems();
    using.map((i)=>{
        if(i.down != null){
            var tmp = i.down;
            while(1){
                if(tmp.down == null && tmp.idle == 1){
                    moveItem(i, tmp);
                    break;
                }
                if(tmp.down == null && tmp.idle == 0){
                    if(tmp.item.innerText == i.item.innerText){
                        addItem(i, tmp);
                    }
                    break;
                }
                if(tmp.idle == 0){
                    if(tmp.item.innerText == i.item.innerText){
                        addItem(i, tmp);
                    }
                    break;
                }
                if(tmp.idle == 1 && tmp.down.idle == 1){
                    tmp = tmp.down;
                    continue;
                }
                if(tmp.idle == 1 && tmp.down.idle == 0){
                    if(tmp.down.item.innerText == i.item.innerText){
                        addItem(i, tmp.down);
                    }else{
                        moveItem(i, tmp);
                    }
                    break;
                }
            }
        }
    });
    generateRandom();
    if(flag > 0){
        moveDown(flag -1);
    }else{
        return;
    }

}
function moveUp(flag){
    fetchValue();
    using = getUsingItems();
    using.map((i)=>{
        if(i.up != null){
            var tmp = i.up;
            while(1){
                if(tmp.up == null && tmp.idle == 1){
                    moveItem(i, tmp);
                    break;
                }
                if(tmp.up == null && tmp.idle == 0){
                    if(tmp.item.innerText == i.item.innerText){
                        addItem(i, tmp);
                    }
                    break;
                }
                if(tmp.idle == 0){
                    if(tmp.item.innerText == i.item.innerText){
                        addItem(i, tmp);
                    }
                    break;
                }
                if(tmp.idle == 1 && tmp.up.idle == 1){
                    tmp = tmp.up;
                    continue;
                }
                if(tmp.idle == 1 && tmp.up.idle == 0){
                    if(tmp.up.item.innerText == i.item.innerText){
                        addItem(i, tmp.up);
                    }else{
                        moveItem(i, tmp);
                    }
                    break;
                }
            }
        }
    });
    generateRandom();
    if(flag > 0){
        moveUp(flag - 1);
    }else{
        return;
    }

}
