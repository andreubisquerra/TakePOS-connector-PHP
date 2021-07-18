<?php
header('Access-Control-Allow-Origin: *');
$data=$_POST["invoice"];
echo $data;
if (empty($_GET["printer"])) $nprinter=1;
else $nprinter=$_GET["printer"];
echo "Printer number: ".$nprinter."<br>";
$file = fopen("invoice".$nprinter.".txt", "w") or die("Unable to open file!");
fwrite($file, $data);
fclose($file);
$printer = file_get_contents('../printer'.$nprinter.'.ini', true);
exec('java -jar PrintHtml.jar "'.$printer.'" '.$nprinter,$output);
print_r( $output);
echo 'java -jar Print.jar "'.$printer.'" '.$nprinter;
