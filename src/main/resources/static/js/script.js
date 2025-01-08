console.log("this is script file");

const toggleSidebar = () => {

if($('.sidebar').is(":visible")){
	//true
	//band garaune 

$(".sidebar").css("display","none");
$(".content").css("margin-left","0%");

}else{
	//false
	//show garaune
    $(".sidebar").css("display","block");
$(".content").css("margin-left","20%");



}

};