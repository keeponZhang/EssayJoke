(function JsAddJavascriptInterface_(){

    if (typeof(window.jsInterface)!='undefined') {    
        console.log('window.jsInterface_js_interface_name is exist!!');} 
    else {
        window.jsInterface = {        
            onButtonClick:function(arg0) {
                    alert("aaaa");
                return prompt('MyApp:'+JSON.stringify({obj:'jsInterface',func:'onButtonClick',args:[arg0]}));
            },
            
            onImageClick:function(arg0,arg1,arg2) {
             alert("bbb");
                prompt('MyApp:'+JSON.stringify({obj:'jsInterface',func:'onImageClick',args:[arg0,arg1,arg2]}));
            },
        };
    }
}
)
