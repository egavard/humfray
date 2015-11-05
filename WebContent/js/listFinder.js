function getXHRRequest(){
    if (window.XMLHttpRequest){
        return new XMLHttpRequest();
    }
    if (window.ActiveXObject){
        var names = [
            "Msxml2.XMLHTTP.6.0",
            "Msxml2.XMLHTTP.3.0",
            "Msxml2.XMLHTTP",
            "Microsoft.XMLHTTP"
        ];
        for(var i in names){
            try{ return new ActiveXObject(names[i]); }
            catch(e){}
        }
    }
    return null; // unsupported
}



function doAjaxRequestForNextQuestion(questionID,selectedElement){
	$(selectedElement).parent().parent().nextAll().remove();
	var objAjax = getXHRRequest();
	if(objAjax != null){
		objAjax.open("GET",racine+"/handleQuestion?idQuestion="+questionID+"&currentAnswer="+$(selectedElement).find("option:selected").val()+"");
		objAjax.send();
		objAjax.onreadystatechange=function(){
			if(objAjax.readyState == 4 && objAjax.status==200){
				//We got an answer from server.
				$("#alertFallenServer").hide();
				$(".question").last().append(objAjax.responseText);
			}else{
				$("#alertFallenServer").show();
			}
		};
	}else{
		$("#alertXmlRequest").show();
	}
}