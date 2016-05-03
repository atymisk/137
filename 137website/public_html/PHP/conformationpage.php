<?php

    ini_set('display_errors', 'on');

    require_once "PDODBinfo.php";

    $stmt = $conn->query("SELECT name, email, address, shipping, products FROM customer");
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
    echo "</table>\n";
        
?>