<?php

$servername = "localhost"; 
$username = "root"; 
$password = "";
$database = "user";

$conn = new mysqli($servername, $username, $password, $database);

$username = $_POST['username'];
$email = $_POST['email'];

if ($conn->connect_error) {
    die("Kết nối đến cơ sở dữ liệu thất bại: " . $conn->connect_error);
}

$sql = "INSERT INTO users (username, email) VALUES ('$username', '$email')";

if ($conn->query($sql) === TRUE) {
    echo "Dữ liệu đã được chèn thành công";
} else {
    echo "Lỗi: " . $sql . "<br>" . $conn->error;
}


$conn->close();
?>
