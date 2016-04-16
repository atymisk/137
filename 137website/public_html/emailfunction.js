/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var illegalchar = /\W/;
var validChar = /^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$/;
var validnum = /^\d+$/;

var validname = false;
var validemail = false;
var validcc = false;
var validcsc = false;
var validaddr1 = false;
var validaddr2 = true;
var validcity = false;
var validstate = false;
var validzip = false;
var validcountry = false;
var validship = false;

var shippingmethod;
var product;
var quantity;
var price;

function checkName()
{
    var name = document.getElementById('name').value;
    var namerr = document.getElementById('nameerr');
    if(name.length === 0)
        namerr.innerHTML = "*Name cannot be empty";
    else if(illegalchar.test(name))
        namerr.innerHTML = "*Contains Invalid Characters";
    else
    {
        namerr.innerHTML = '';
        validname=true;
    }
}

function checkEmail()
{
    var email = document.getElementById('email').value;
    var emailerr = document.getElementById('emailerr');
    
    var emailFilter = /^[^@]+@[^@.]+\.[^@]*\w\w$/ ;
    var illegalChars= /[\(\)\<\>\,\;\:\\\"\[\]]/ ;
    
    if(email.length === 0)
        emailerr.innerHTML = "*Email cannot be empty";
    else if(email.match(illegalChars))
        emailerr.innerHTML = '*Contains Invalid Characters';
    else if(!emailFilter.test(email.replace(/^\s+|\s+$/, '')))
        emailerr.innerHTML = '*Please enter a valid email';
    else
    {
        validemail=true;
        emailerr.innerHTML = '';
    }
}

function checkCreditCard()
{
    var cc = document.getElementById('creditcard').value;
    var crediterr = document.getElementById('crediterr');
    
    if(cc === '')
        crediterr.innerHTML = "*Field cannot be empty";
    else if(!validnum.test(cc))
        crediterr.innerHTML = '*Only Numbers allowed';
    else if(cc.length !== 16)
        crediterr.innerHTML = '*Incomplete';
    else
    {
        validcc = true;
        crediterr.innerHTML = '';
    }
}

function checkCSC()
{
    var csc = document.getElementById('CSC').value;
    var crediterr = document.getElementById('crediterr');
    if(csc === '')
        crediterr.innerHTML = '*Field cannot be empty';
    else if(!validnum.test(csc))
        crediterr.innerHTML = '*Only Numbers allowed';
    else if(csc.length !== 3)
        crediterr.innerHTML = '*Incomplete';
    else
    {
        validcsc = true;
        crediterr.innerHTML = '';
    }
}

function checkShippingAddress1()
{
    var shipping1 = document.getElementById('addr1').value;
    var errship1 = document.getElementById('shippingerr1');
    
    if(shipping1 === '')
        errship1.innerHTML = '*Cannot be empty';
    else if(!validChar.test(shipping1))
        errship1.innerHTML = '*Contains illegal characters';
    else
    {
        errship1.innerHTML = '';
        validaddr1 = true;
    }
}

function checkShippingAddress2()
{
    var shipping2 = document.getElementById('addr2').value;
    var errship2 = document.getElementById('shippingerr2');
    
    if(shipping2!==''&&!validChar.test(shipping2))
    {
        errship2.innerHTML = '*Contains illegal characters';
        validaddr2 = false;
    }
    else
    {
        validaddr2 = true;
        errship2.innerHTML = '';
    }
}

function checkCity()
{
    var city = document.getElementById('city').value;
    var cityerr = document.getElementById('addrerr');
    
    if(city === '')
        cityerr.innerHTML = '*City cannot be empty';
    else if(!validChar.test(city))
        cityerr.innerHTML = '*City contains illegal characters';
    else
    {
        validcity = true;
        cityerr.innerHTML = '';
    }
}

function checkState()
{
    var state = document.getElementById('state').value;
    var addrerr = document.getElementById('addrerr');

    if(state === '')
        addrerr.innerHTML = '*Select a State';
    else
    {
        validstate=true;
        addrerr.innerHTML = '';
    }
}

function checkZIP()
{
    var zip = document.getElementById('zip').value;
    var addrerr = document.getElementById('addrerr');
    
    if(zip ==='')
        addrerr.innerHTML = '*ZIP cannot be empty';
    else if(!validnum.test(zip))
        addrerr.innerHTML = '*ZIP must contain numbers'
    else if(zip.length !== 5)
        addrerr.innerHTML = '*ZIP must be 5 numbers long'
    else
    {
        validzip = true;
        addrerr.innerHTML ='';
    }
}

function checkCountry()
{
    var country = document.getElementById('country').value;
    var countryerr = document.getElementById('countryerr');
    
    if(country === '')
        countryerr.innerHTML = '*Cannot Be Empty'
    else if(!validChar.test(country))
        countryerr.innerHTML = '*Contains illegal characters';
    else
    {
        validcountry = true;
        countryerr.innerHTML = '';
    }
}

function checkShippingOptions()
{
    var shippingoptions = document.getElementsByName('shipping');
    var shiperr = document.getElementById('shipmethoderr');
    for (var i = 0, length = shippingoptions.length; i < length; i++) 
    {
        if (shippingoptions[i].checked) 
        {
            shippingmethod = shippingoptions[i].value;
            validship = true;
            break;
        }
    }
    if(!validship)
        shiperr.innerHTML = '*Please Select a Shipping Method';
    else
        shiperr.innerHTML = '';
}

function verifyinput()
{       
    checkName();
    checkEmail();
    checkCreditCard();
    checkCSC();
    checkShippingAddress1();
    checkShippingAddress2();
    checkCity();
    checkState();
    checkZIP();
    checkCountry();
    checkShippingOptions();
    if(validname && validemail && validcc && validcsc && validaddr1 && validcity 
            && validstate && validzip && validcountry && validship && validaddr2)
        sendemail();
}

function sendemail()
{
<<<<<<< HEAD
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
=======
    var email = document.getElementById('email').value;
    var yourMessage = "";
    var subject = "Order Confirmation";

>>>>>>> origin/master
    document.location.href = "mailto:"+email+"?subject="
        + encodeURIComponent(subject)
        + "&body=" + encodeURIComponent(yourMessage);
}