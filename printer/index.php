<?php
header('Access-Control-Allow-Origin: *');
$data=base64_decode($_POST["invoice"]);
$file = fopen("invoice.txt", "w") or die("Unable to open file!");
fwrite($file, $data);
fclose($file);
$printer = file_get_contents('../printer1.ini', true);
exec('java -jar Print.jar "'.$printer.'"',$output);
print_r( $output);
echo 'java -jar Print.jar "'.$printer.'"';