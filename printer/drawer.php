<?php
header('Access-Control-Allow-Origin: *');
$nprinter=1;
echo "Printer number: ".$nprinter."<br>";
$file1 = 'drawer.txt';
$file2 = "invoice".$nprinter.".txt";
if (!copy($file1, $file2)) {
    echo "Error with copy $file2\n";
}
$printer = file_get_contents('../printer'.$nprinter.'.ini', true);
exec('java -jar Print.jar "'.$printer.'" '.$nprinter,$output);
print_r( $output);
echo 'java -jar Print.jar "'.$printer.'" '.$nprinter;