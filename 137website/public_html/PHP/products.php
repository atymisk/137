<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/CSS/styleCSS.css">
    <title>Products</title>
</head>
<body>
    
    
    <nav id="navbar">
        <a href="/index.html" id="title">Antreader Bookstore</a>
        <a href="/PHP/products.php" class="menu">Products</a>
        <a href="/about.html" class="menu">About</a>
    </nav>

    <h1 id="products_title">Products</h1>
   
    

    <table id="products" align="center">
        <tr>
            <td class="product_info">I  mage</td>
            <td class="product_info">Author(s)</td>
            <td class="product_info">Book Title</td>
            <td class="product_info">Genre</td>
            <td class="product_info">Year</td>
            <td class="product_info">Price</td>
        </tr>
        <?php

            ini_set('display_errors', 'on');

            require_once "PDODBinfo.php";

            $sql = "SELECT * FROM products";

            foreach($conn->query($sql) as $row)
            {
                echo '<tr style="cursor:pointer" onclick="document.location.href=\'/PHP/one_product.php?title='.$row['title'].'\';">';
                echo '<td height=300 width=250 align=center class="product_info"> <img src="';
                echo $row['image_link'];
                echo '" alt="Picture will be coming soon" height=200 width=150></td>';
                
                echo '<td class="product_info">'.$row['author'].'</td>';
                
                echo '<td class="product_info">';
                echo '<a href="/PHP/one_product.php?title='.$row['title'].'" class="product_info">';
                echo $row['title'].'</a></td>';
                
                echo '<td class="product_info">'.$row['genre'].'</td>';
                
                echo '<td class="product_info">'.$row['published_date'].'</td>';
                
                echo '<td class="product_info">$'.$row['price'].'</td></tr>';
                
            }

            $conn = null;

        ?>
        </table>
 <!--  
    <table id="products" align="center">
    <tr>
        <th class="product_info">Image</th>
        <th class="product_info">Author(s)</th>
        <th class="product_info">Book Title</th>
        <th class="product_info">Genre</th>
        <th class="product_info">Year</th>
        <th class="product_info">Price</th>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t3.gstatic.com/images?q=tbn:ANd9GcTe1gsaJjcCE3fcffXfF3h1PRGedlt-1vpZQjb4IYxZ0-1awjl2" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">John Steinbeck</td>
        <td class="product_info"><a href="grapesofwrath_jsteinbeck.html" class="product_info">The Grapes of Wrath</a></td>
        <td class="product_info">Novel</td>
        <td class="product_info">1939</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t3.gstatic.com/images?q=tbn:ANd9GcRpRWpbEn9Wzq3wMUZeH7baG3Y7bdNkSvgKTwwq2pw6gyNJ3Pvv" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">Bram Stoker</td>
        <td class="product_info"><a href="dracula_bstoker.html" class="product_info">Dracula</a></td>
        <td class="product_info">Horror</td>
        <td class="product_info">1897</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t3.gstatic.com/images?q=tbn:ANd9GcSlNuy17PZRDUjL0nB4J1UFBfXNZDesutLFTfZ0ePk4ScAlQzUy" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">John Kennedy Toole</td>
        <td class="product_info"><a href="aconfederacy_jktoole.html" class="product_info">A Confederacy of Dunces</a></td>
        <td class="product_info">Comedy</td>
        <td class="product_info">1980</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t2.gstatic.com/images?q=tbn:ANd9GcTwDM_w81UvVhA3q_NDprmAw3cBvAJwcJ3bMI_G3LPDwlO2HjTv" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">Orson Scott Card</td>
        <td class="product_info"><a href="endersgame_oscott.html" class="product_info">Ender's Game</a></td>
        <td class="product_info">Fiction</td>
        <td class="product_info">1985</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t0.gstatic.com/images?q=tbn:ANd9GcTWicfieKL624hpiEQNo1JvLy9trxJnUZyR1K2Q_24ZOPu-87pJ" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">Rachel Carson</td>
        <td class="product_info"><a href="silentspring_rcarson.html" class="product_info">Silent Spring</a></td>
        <td class="product_info">Non-fiction</td>
        <td class="product_info">1962</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t0.gstatic.com/images?q=tbn:ANd9GcSJC7FDq-z6NlSNw-45Ra_v3wDYqU3BPHdxEQyTVPzC99vj9D6R" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">William Shakespeare</td>
        <td class="product_info"><a href="hamlet_wshakespeare.html" class="product_info">Hamlet</a></td>
        <td class="product_info">Drama</td>
        <td class="product_info">1603</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t3.gstatic.com/images?q=tbn:ANd9GcQiB4biJnG6OL2KaGaIXmonyE9YErmW3_NvL-iL1KcqKFVd0-j_" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">Jane Austen</td>
        <td class="product_info"><a href="prideandprejudice_jausten.html" class="product_info">Pride and Prejudice</a></td>
        <td class="product_info">Romance</td>
        <td class="product_info">1813</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t1.gstatic.com/images?q=tbn:ANd9GcQd3o9-cpsy1hYwoXH44-JRvj15A8K025wuFLTE0xmdQz6gkoii" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">Theodore Dreiser</td>
        <td class="product_info"><a href="anamericantragedy_tdreiser.html" class="product_info">An American Tragedy</a></td>
        <td class="product_info">Crime Fiction</td>
        <td class="product_info">1925</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t2.gstatic.com/images?q=tbn:ANd9GcT7YKDu4Qm1SZ7Q1AC6g7U-zTK42cHZAAMBAzup9ZpWiFh8CWuz" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">Jonathan Swift</td>
        <td class="product_info"><a href="gulliverstravels_jswift.html" class="product_info">Gulliver's Travels</a></td>
        <td class="product_info">Satire</td>
        <td class="product_info">1726</td>
        <td class="product_info">$20.00</td>
    </tr>
    <tr>
        <td height=300 width = 250 align=center class="product_info"><img src = "http://t2.gstatic.com/images?q=tbn:ANd9GcTimGHtA_9r4uggerMT9qC2pcMFj6CRaivRkk-Cdg8YIg3Jqq3J" alt='Picture will be coming soon' height=200 width = 150></td>
        <td class="product_info">James Joyce</td>
        <td class="product_info"><a href="dubliners_jjoyce.html" class="product_info">Dubliners</a></td>
        <td class="product_info">Short story</td>
        <td class="product_info">1914</td>
        <td class="product_info">$20.00</td>
    </tr>
    </table>
-->
</body>
</html>

