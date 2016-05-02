<?php
$q = $_GET['q'];
require_once 'PDODBinfo.php';

$taxrate = $conn->query('SELECT tax_rate FROM tax_rates WHERE zipcode = ' . $q)->fetch();
echo($taxrate['tax_rate']);
$conn = null;
?>