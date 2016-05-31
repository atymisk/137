<?php
$servername = "sylvester-mccoy-v3.ics.uci.edu";
$dbname = "inf124grp03";
$username = "inf124grp03";
$password = "S&Etah4G";

try
{
    $conn = new PDO('mysql:host=sylvester-mccoy-v3.ics.uci.edu;dbname=inf124grp03',$username,$password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(PDOException $e)
{
    echo "Error: " . $e->getMessage();
}
?>