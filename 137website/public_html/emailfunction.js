/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function verifyinput()
{
    alert('test');
    sendemail();
}

function sendemail()
{
    var email = document.getElementById('email');
    var yourMessage = "";
    var subject = "Order Confirmation";
    document.location.href = "mailto:"+email+"?subject="
        + encodeURIComponent(subject)
        + "&body=" + encodeURIComponent(yourMessage);
}