/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// illegalChar won't let us input names with spaces in between
var illegalchar = /\W/;
var validChar = /^[A-Za-z0-9 ._]*[A-Za-z0-9][A-Za-z0-9 ._]*$/;
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

var shipping = 0;
var quantity = 1;
var price;
var salestax = 0;
var appliedtax=0;
var totalamt = 0;

function div_show() 
{
    setProductInfo();
    document.getElementById('mainpopup').style.display = "block";
    document.body.style.overflow = 'hidden';
}
//Function to Hide Popup
function div_hide()
{
    document.getElementById('mainpopup').style.display = "none";
    document.body.style.overflow = 'scroll';
}

function getAddress()
{
    var addr = document.getElementById('addr1').value + " " + document.getElementById('city').value + " "
    + document.getElementById('state').value + " " + document.getElementById('zip').value;
    document.getElementById('address').value=addr;
}

function setProductInfo()
{
    product = document.getElementById('products').value;
    price = document.getElementById('price').value;
    changetotal();
}

function getSalesTaxByZIP()
{
    var zip = document.getElementById('zip').value;
    console.log(zip);
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) 
        {
            salestax = xmlhttp.responseText;
            console.log("Sales Tax is: " + salestax);
            changeSalesTax();
        }
        else
        {
            console.log('Readystate: ' + xmlhttp.readyState + " | Status: " + xmlhttp.status);
        }
    };
    xmlhttp.open("GET","/PHP/SalesTax.php?q=" + zip,true);
    xmlhttp.send();
    
}

function checkName()
{
    var name = document.getElementById('name').value;
    var namerr = document.getElementById('nameerr');
    if(name.length === 0)
        namerr.innerHTML = "*Name cannot be empty";
    /* So this means name cannot contain whitespace?? */
    else if(!validChar.test(name))
        namerr.innerHTML = "*Contains Invalid Characters";
    else
    {
        namerr.innerHTML = '';
        validname=true;
    }
    verifyinput();
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
    verifyinput();
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
        crediterr.innerHTML = '*Invalid Credit Card Number';
    else
    {
        validcc = true;
        crediterr.innerHTML = '';
    }
    verifyinput();
}

function checkCSC()
{
    var csc = document.getElementById('CSC').value;
    var crediterr = document.getElementById('crediterr');
    if(csc === '')
        crediterr.innerHTML = '*CSC cannot be empty';
    else if(!validnum.test(csc))
        crediterr.innerHTML = '*Only Numbers allowed';
    else if(csc.length !== 3)
        crediterr.innerHTML = '*Incomplete';
    else
    {
        validcsc = true;
        crediterr.innerHTML = '';
    }
    verifyinput();
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
        getAddress();
    }
    verifyinput();
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
        getAddress();
    }
    verifyinput();
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
        getAddress();
    }
    verifyinput();
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
        getAddress();
    }
    verifyinput();
}

function checkZIP()
{
    var zip = document.getElementById('zip').value;
    var addrerr = document.getElementById('addrerr');
    
    if(zip ==='')
        addrerr.innerHTML = '*ZIP cannot be empty';
    else if(!validnum.test(zip))
        addrerr.innerHTML = '*ZIP must contain numbers';
    else if(zip.length !== 5)
        addrerr.innerHTML = '*ZIP must be 5 numbers long';
    else
    {
        validzip = true;
        addrerr.innerHTML ='';
        getAddress();
    }
    verifyinput();
}

function checkCountry()
{
    var country = document.getElementById('country').value;
    var countryerr = document.getElementById('countryerr');
    
    if(country === '')
        countryerr.innerHTML = '*Cannot Be Empty';
    else if(!validChar.test(country))
        countryerr.innerHTML = '*Contains illegal characters';
    else
    {
        validcountry = true;
        countryerr.innerHTML = '';
    }
    verifyinput();
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
    changeShipping();
    verifyinput();
}

function verifyinput()
{
    console.log("name "+validname);
    console.log("email "+validemail);
    console.log("cc "+validcc);
    console.log("csc "+validcsc);
    console.log("addr1 "+validaddr1);
    console.log("addr2"+validaddr2);
    console.log("city "+validcity);
    console.log("state "+validstate);
    console.log("zip "+validzip);
    console.log("country "+validcountry);
    console.log("valid "+validship+"\n");
    if(validname && validemail && validcc && validcsc && validaddr1 && validcity 
            && validstate && validzip && validcountry && validship && validaddr2)
    {
        document.getElementById('submitpurchase').disabled = false;
    }
}

function changetotal()
{
    totalamt = parseInt(document.getElementById('totalamt').value) + shipping;
    document.getElementById("totalamt").value = totalamt;
    document.getElementById('totalprice').innerHTML = "$"+totalamt;
}

function changeShipping()
{
    var ship;
    if(shippingmethod==='overnight')
    {
        shipping = 20;
        ship = "$20.00";
    }
    else if(shippingmethod==='2day')
    {
        shipping = 10;
        ship = "$10.00";
    }
    else if(shippingmethod==='1 Week')
    {
        shipping = 4;
        ship = "$4.00";
    }
    document.getElementById('shipping').innerHTML = ship;
    changetotal();
}

function changeSalesTax()
{
    var amtadd = price * quantity * Number(salestax);
    console.log(amtadd);
    appliedtax = amtadd;
    document.getElementById('salestax').innerHTML = '$'+appliedtax;
    changetotal();
}

function changeQuantity()
{
    quantity = document.getElementById('quantity').value;
    changeSalesTax();
}

function sendemail()
{
    var email = document.getElementById('email').value;
    var product = "insert product name here"; //PLACEHOLDER
    var name = document.getElementById('name').value;
    var addr1 = document.getElementById('addr1').value;
    var addr2Input = document.getElementById('addr2').value;
    var addr2 = addr2Input;
    var city = document.getElementById('city').value;
    var state = document.getElementById('state').value;
    var zip = document.getElementById('zip').value;
    
    if(addr2Input === "")
        addr2 = "";
    
    var creditcard = document.getElementById('creditcard').value.substring(12);
    var yourMessage = "Order for "+product
                        +"\n"
                        +"\nShipped to:"
                        +"\n" + name
                        +"\n" + addr1
                        + addr2
                        +"\n" + city + ", " + state + " " + zip
                        +"\n"
                        +"\n Credit Card: xxxxxxxx"+creditcard
                        +"\n"+"\n"
                        +"Thank you for shopping at Antreaders Bookstore!";
    var subject = "[Antreaders Bookstore] Order Confirmation for "+product;
    
    document.location.href = "mailto:"+email+"?subject="
        + encodeURIComponent(subject)
        + "&body=" + encodeURIComponent(yourMessage);
}