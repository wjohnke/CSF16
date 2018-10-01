var toggleVal=false;
            
function launchCode() {
    /*
    Function to load html/css into preview pane
    */
    var htmltxt=document.getElementById("htmlText").value;
    var csstxt=document.getElementById("cssText").value;

    var field=document.getElementById("previewPane");
    if(toggleVal==false){
        field.innerHTML=htmltxt + csstxt;
    }
    else{
        field.innerHTML=htmltxt;
    }
}
function toggle() {
    /*
    Function toggles the css boolean and then calls launchCode()
    */
    toggleVal=!toggleVal;
    launchCode();
}

function clearBtn() {
    document.getElementById("previewPane").innerHTML="";
    document.getElementById("cssText").value="";
    document.getElementById("htmlText").value="";
}