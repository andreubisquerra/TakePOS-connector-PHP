<?php
$arg1 = "Multi Return";
exec("java -jar scale.jar $arg1",$output);

echo preg_replace("/[^0-9.]/", "", $output[0]);;



?>