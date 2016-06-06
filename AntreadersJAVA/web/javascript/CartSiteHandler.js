/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function updateCart()
{
    document.forms['uptqty'].submit();
}

function checkCart()
{
    var rows = document.getElementById('carttable').getElementsByTagName('tr').length;
    if(rows !== 0){document.getElementById('submitCheckout').disabled = false;}
}