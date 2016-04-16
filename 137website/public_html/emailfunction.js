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
    /*var product = "insert product name here";
    var name = document.getElementById('name');
    var creditcardnum = document.getElementById('creditcardnum').substring(12);*/
    var yourMessage = "Order"/*"Order for "+product
                        +"\n Name: "+name
                        +"\n Credit Card Number: xxxxxx"+creditcardnum
                        +"\n"+"\n"
                        +"Thank you for shopping at 33 Bookstore!"*/;
    var subject = "Order Confirmation for "/*+product*/;
    document.location.href = "mailto:"+email+"?subject="
        + encodeURIComponent(subject)
        + "&body=" + encodeURIComponent(yourMessage);
}