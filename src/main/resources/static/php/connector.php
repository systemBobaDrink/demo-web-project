<?php 

define('DB_SERVER', 'mydatabase.ckoxrzfooypv.us-east-2.rds.amazonaws.com');
define('DB_USERNAME', 'systembobadrink');
define('DB_PASSWORD', 'databasepassword');
define('DB_DATABASE', 'users');

//define('DB_SERVER', 'localhost');
//define('DB_USERNAME', 'root');
//define('DB_PASSWORD', 'Systembobadrink321');
//define('DB_DATABASE', 'users');


// $dbhandle=new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_DATABASE) or die("Unable to Connect DB");
$connection = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD);

// $connection = mysql_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD) or die(mysql_error());
$database = mysql_select_db($connection, DB_DATABASE) or die(mysql_error());


$data = mysqli_query($connection, "SELECT * FROM Users");



?>
