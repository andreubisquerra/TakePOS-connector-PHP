<?php
header('Access-Control-Allow-Origin: *');
$arg1 = "Multi Return";
exec("java -jar scale.jar $arg1",$output);

if (empty($output)){
	exec("taskkill /F /IM java.exe ");
	exec("java -jar scale.jar $arg1",$output);
}

echo preg_replace("/[^0-9.]/", "", $output[0]);;
?>