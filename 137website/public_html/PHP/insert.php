<?php
require_once "PDODBinfo.php";

//check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

//put received data into an array
$data = array('id' => $_POST['id'] ,
    'name' => $_POST['name'], 
    'creditcard' => $_POST['creditcard'],
    'email' => $_POST['email'],
    'address' => $_POST['address'],
    'shipping' => $_POST['shipping'],
    'products' => $_POST['products']);

if( isset($_POST['id']) 
    && isset($_POST['name']) 
    && isset($_POST['creditcard']) 
    && isset($_POST['email']) 
    && isset($_POST['address']) 
    && isset($_POST['shipping']) 
    && isset($_POST['products'])    )
{
    $sql = "INSERT INTO customer (id, name, creditcard, email, address, shipping, products)"
            . "VALUES (:id, :name, :creditcard, :email, :address, :shipping, :products)";
    
    $stmt = $pdo->prepare($sql);
    $stmt->execute($data);  
    
    echo "ok";
}

?>
