<!DOCTYPE html>
<html>
    <head>
        
        <?php
        
            ini_set('display_errors', 'on');

            require_once "PDODBinfo.php";

            $title = $_GET['title'];

            $sql = "SELECT * FROM products WHERE title='".$title."'";

            $row = $conn->query($sql)->fetch();
            
            echo "<title>".$row['title']." by ".$row['author']."</title>";
        
        ?>
        
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" type="text/css" href="/CSS/popupCSS.css">
        <link rel="stylesheet" type="text/css" href="/CSS/productCSS.css">
        <link rel="stylesheet" type="text/css" href="/CSS/styleCSS.css">
        <script type="text/javascript" src='/javascript/emailfunction.js'></script>
    </head>
    <body>
        
        <nav id="navbar">
            <a href="index.html" id="title">Antreader Bookstore</a>
            <a href="PHP/products.php" class="menu">Products</a>
            <a href="about.html" class="menu">About</a>
        </nav>
        <h1 class='about'>Big Empty Files</h1><br>

        <?php

            echo "<h1 class='about'>".$row['title']." by ".$row['author']."</h1>";
            echo '<img class="inlinepic" src="'.$row['image_link'].'" alt="'.$row['title'].' cover"/>';
            echo "<h2 class='about'>Year Published</h2>";
            echo "<p class='about'>".$row['published_date']."</p>";
            echo "<h2 class='about'>Genre</h2>";
            echo "<p class='about'>".$row['genre']."</p>";
            echo "<h2 class='about'>Synopsis</h2>";
            echo "<p class='about'>".$row['synopsis']."</p>";
            echo "<h2 class='about'>Price</h2>";
            echo "<p class='about'>$".$row['price']."</p>";
        
            echo "<div id='mainpopup'>";
            echo "<div id='popupform'>";
            echo '<form action="/PHP/insert.php" id="form" method="post" name="form" onsubmit="return verifyinput();">';
            echo "<input type='hidden' name='products' id='products' value='".$row['title']." by " . $row['author']."'>";
            echo "<input type='hidden' name='price' id='price' value='".$row['price']."'>";
        
        $conn=null;
        ?>            
                    <h2 style='text-align:center;'>Payment Information</h2>
                    <hr/>
                    <table>
                        <tr>
                            <td class="popup"><label for='name' class='textinpt'>Name</label></td>
                            <td class="popup">
                                <input id="name" name="name" placeholder="Example: Peter Anteater" type="text" onblur='checkName()' required><br/>
                            </td>
                            <td class="popup"><p id='nameerr' class='err'></p></td>
                        </tr>
                        <tr>
                            <td class="popup"><label for='email' class='textinpt'>Email Address</label></td>
                            <td class="popup">
                                <input id="email" name="email" 
                                       placeholder="Example: peteranteater@uci.edu" type="text" onblur='checkEmail()' required><br/>
                            </td>
                            <td class="popup"><p id='emailerr' class='err'></p></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td class="popup"><label for='creditcard' class='textinpt'>Credit Card Number</label></td>
                            <td class="popup">
                                <input id='creditcard' name='creditcard' 
                                    placeholder='Example: 1234 5678 1234 5678' type='text' onblur='checkCreditCard()' required/>
                            </td>
                            <td class="popup"><input id='CSC' name='CSC' placeholder="CSC" type='text' style='width:35%;' onblur='checkCSC()' required/></td>
                        </tr>
                        <tr><td class="popup"><p id='crediterr' class='err'></p></td></tr>
                    </table>
                    <table>
                        <h3 style='margin-left: 4%;'>Shipping Information</h3>
                        <tr>
                            <td class="popup"><label for='addr1' class='textinpt'>Shipping Address Line 1</label></td>
                            <td class="popup"><input id='addr1' name='addr1' placeholder='Example: 123 Main St.' type='text' onblur='checkShippingAddress1()' required/></td>
                            <td class="popup"><p id='shippingerr1' class='err'></p></td>
                        </tr>
                        <tr>
                            <td class="popup"><label for='addr2' class='textinpt'>Shipping Address Line 2</label></td>
                            <td class="popup"><input id='addr2' name='addr2' placeholder='optional' onblur='checkShippingAddress2()' type='text'/></td>
                            <td class="popup"><p id='shippingerr2' class='err'></p></td>
                        </tr>
                    </table>
                    <table style='margin-left: 3%;'>
                        <tr>
                            <td class="popup"><label for='city' class='textinpt'>City</label></td>
                            <td class="popup"><input id='city' name='city' placeholder='Example: Irvine' type='text' onblur='checkCity()' required/></td>
                            <td class="popup"><label for='state' class='textinpt'>State</label></td>
                            <td class="popup">
                                <select id='state' name='state' onblur='checkState()' required>
                                    <option value='' disabled selected>--</option>
                                    <option value='AL'>Al</option><option value='AK'>AK</option>
                                    <option value='AR'>AR</option><option value='AZ'>AZ</option><option value='CA'>CA</option>
                                    <option value='CO'>CO</option><option value='CT'>CT</option><option value='DE'>DE</option>
                                    <option value='FL'>FL</option><option value='GA'>GA</option><option value='HI'>HI</option>
                                    <option value='ID'>ID</option><option value='IL'>IL</option><option value='IN'>IN</option>
                                    <option value='IA'>IA</option><option value='KS'>KS</option><option value='KY'>KY</option>
                                    <option value='LA'>LA</option><option value='ME'>ME</option><option value='MD'>MD</option>
                                    <option value='MA'>MA</option><option value='MI'>MI</option><option value='MN'>MN</option>
                                    <option value='MS'>MS</option><option value='MO'>MO</option><option value='MT'>MT</option>
                                    <option value='NE'>NE</option><option value='NV'>NV</option><option value='NH'>NH</option>
                                    <option value='NJ'>NJ</option><option value='NM'>NM</option><option value='NY'>NY</option>
                                    <option value='NC'>NC</option><option value='ND'>ND</option><option value='OH'>OH</option>
                                    <option value='OK'>OK</option><option value='OR'>OR</option><option value='PA'>PA</option>
                                    <option value='RI'>RI</option><option value='SC'>SC</option><option value='SD'>SD</option>
                                    <option value='TN'>TN</option><option value='TX'>TX</option><option value='UT'>UT</option>
                                    <option value='VT'>VT</option><option value='VA'>VA</option><option value='WA'>WA</option>
                                    <option value='WV'>WV</option><option value='WI'>WI</option><option value='WY'>WY</option>
                                </select>
                            </td>
                            <td class="popup"><label for='zip'>ZIP</label></td>
                            <td class="popup"><input id='zip' name='zip' placeholder="ex: 12345" size='5' type='text' onblur='checkZIP()' required/></td>
                        </tr>
                        <tr><td><input type="hidden" name="address" id="address"></td></tr>
                    </table>
                        <p id='addrerr' class='err'></p>
                    <table style='margin-top: 0px;'>
                        <tr>
                            <td class="popup"><label for='country'>Country</label></td>
                            <td class="popup"><input id='country' name='country' type='text' placeholder="ex: United States of America" onblur='checkCountry()' required/></td>
                            <td class="popup"><p id='countryerr' class='err'></p></td>
                        </tr>
                    </table>
                    
                    <h3 style='margin-left: 4%;'>Shipping Options</h3>
                    <table>
                        <tr>
                            <td class="popup">
                                <input type="radio" name="shipping" id='overnight' onblur='checkShippingOptions()' value="overnight">
                                <label for='overnight'>Overnight Shipping</label>
                            </td>
                            <td class="popup"><p  class='err'id='shipmethoderr'></p></td>
                        </tr>
                        <tr>
                            <td class="popup">
                                <input type="radio" name="shipping" id='2days' onblur='checkShippingOptions()' value="2day">
                            <label for='2days'>2 Day Shipping</label>
                            </td>
                        </tr>
                        <tr>
                            <td class="popup">
                                <input type="radio" name="shipping" id='1week' onblur='checkShippingOptions()' value="1 Week">
                            <label for='1week'>1 week Shipping</label>
                            </td>
                            
                        </tr>
                    </table>
                    <hr>
                    <h3 class="infoheader">Total Amount</h3>
                    <table style="margin-left: 10%;">
                        <tr>
                            <td><i>Quantity: </i></td>
                            <td><p class="money"><input id="quantity" type="number" 
                                                        name="quantity" min="1" value="1" onchange="changeQuantity()"></p></td>
                        </tr>
                        <tr>
                            <td><i>Sales Tax: </i></td>
                            <td><p class="money" id="salestax">$0.00</p></td>
                        </tr>
                        <tr>
                            <td><i>Shipping: </i></td>
                            <td><p class="money" id="shipping">$0.00</p></td>
                        </tr>
                        <tr>
                            <td><i>Total: </i></td>
                            <td><p class="money" id="totalprice">$0.00</p></td>
                        </tr>
                    </table>
                    <hr>
                    <center>
                        <ul>
                            <li style='display:inline;'><input type='submit' onclick='verifyinput()' value="Submit Payment"/></li>
                            <li style='display:inline;'><input type='button' id="closeform" value='Cancel Order' onclick='div_hide()'/></li>
                        </ul>    
                    </center>
                </form>
            </div>
        </div>
            
        <center>
            <button type='button' id='popup' onclick='div_show()'>Buy Now!</button>
        </center>
    </body>
</html>