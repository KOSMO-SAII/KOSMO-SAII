console.log("실행");
for(var i =0; i<$('li').length;i++){
console.log(i)
$('li').click(function(event){
    console.log("클릭");
    console.log(event.target);
})
}
