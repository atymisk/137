<?php
require_once "PDODBinfo.php";

//put received data into an array
$data = array(':name' => $_POST['name'], 
    ':creditcard' => $_POST['creditcard'],
    ':email' => $_POST['email'],
    ':address' => $_POST['address'],
    ':shipping' => $_POST['shipping'],
    ':products' => $_POST['products']);

if( isset($_POST['name']) 
    && isset($_POST['creditcard']) 
    && isset($_POST['email']) 
    && isset($_POST['address']) 
    && isset($_POST['shipping']) 
    && isset($_POST['products'])    )
{
    $sql = "INSERT INTO customer (name, creditcard, email, address, shipping, products)"
            . "VALUES (:name, :creditcard, :email, :address, :shipping, :products)";
    
    $stmt = $conn->prepare($sql);
    $stmt->execute($data);  
    
    //confirmation page
    /*$stmt = $conn->query("SELECT name, email, address, shipping, products FROM customer");*/
    echo '<table border="1">'."\n";
    //while ( $row = $stmt->fetch(PDO::FETCH_ASSOC) ) {
        echo "<tr><td>";
        echo($_POST['name']);
        echo("</td><td>");
        echo($_POST['email']);
        echo("</td><td>");
        echo($_POST['address']);
        echo("</td><td>");
        echo($_POST['shipping']);
        echo("</td><td>");
        echo($_POST['products']);
        echo("</td></tr>\n");
    //}
    echo "</table>\n";
    
    //echo "ok";
}

?>
