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
    
    echo "ok";
}

?>
