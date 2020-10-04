<?php
header('Access-Control-Allow-Origin: *');
$data=base64_decode($_POST["invoice"]);
$file = fopen("invoice.txt", "w") or die("Unable to open file!");
fwrite($file, $data);
fclose($file);