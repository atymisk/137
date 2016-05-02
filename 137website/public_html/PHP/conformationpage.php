<?php
$servername = "sylvester-mccoy-v3.ics.uci.edu";
$dbname = "inf124grp03";
$username = "inf124grp03";
$password = "S&Etah4G";

try
{
    $conn = new PDO('mysql:host=sylvester-mccoy-v3.ics.uci.edu;dbname=inf124grp03',$username,$password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    
    $stmt = $pdo->query("SELECT name, email, address, shipping, products FROM customer");
    echo '<table border="1">'."\n";
    while ( $row = $stmt->fetch(PDO::FETCH_ASSOC) ) {
        echo "<tr><td>";
        echo($row['name']);
        echo("</td><td>");
        echo($row['email']);
        echo("</td><td>");
        echo($row['address']);
        echo("</td><td>");
        echo($row['shipping']);
        echo("</td><td>");
        echo($row['products']);
        echo("</td></tr>\n");
    }
    echo "</table>\n";?>
        
}
catch(PDOException $e)
{
    echo "Error: " . $e->getMessage();
}
?>