<?php

$servername = "localhost"; 
$username = "root"; 
$password = "";
$database = "user"; 

$conn = new mysqli($servername, $username, $password, $database);

if ($conn->connect_error) {
    die("Kết nối đến cơ sở dữ liệu thất bại: " . $conn->connect_error);
}

$sql = "SELECT * FROM users";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $data = array();
    while($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
    $json_data = json_encode($data);

    echo $json_data;
} else {
    echo "Không có dữ liệu trong bảng users";
}

$conn->close();
?>
