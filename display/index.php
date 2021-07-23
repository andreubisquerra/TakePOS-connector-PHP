<?php
header('Access-Control-Allow-Origin: *');
$text = $_GET["text"];
$display = file_get_contents('../display.ini', true);
exec("java -jar CustomerDisplay.jar $display \"$text\"",$output);
echo "java -jar CustomerDisplay.jar $display $text";


echo $output[0];
?>