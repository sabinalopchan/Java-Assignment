function funSearch(){
    var search_str=jQuery('#search_str').val();
    if (search_str!=''){
        window.location.href='/search/'+search_str;
    }
}
