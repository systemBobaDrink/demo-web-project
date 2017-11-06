<?php
include "connection.php";
$query="SELECT * FROM users";
$rs=$dbhandle->query($query);

while($row=$rs->fetch_assoc()){
    $data[]=$row;
    
    
}

print json_encode($data);

?>