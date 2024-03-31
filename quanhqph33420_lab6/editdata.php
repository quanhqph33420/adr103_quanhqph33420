<?php

$servername = "localhost";
$username = "root"; 
$password = "";
$database = "user";

$conn = new mysqli($servername, $username, $password, $database);

$id = $_POST['id'];
$newUsername = $_POST['new_username'];
$newEmail = $_POST['new_email'];

if ($conn->connect_error) {
    die("Kết nối đến cơ sở dữ liệu thất bại: " . $conn->connect_error);
}

$sql = "UPDATE users SET username='$newUsername', email='$newEmail' WHERE id=$id";

if ($conn->query($sql) === TRUE) {
    echo "Dữ liệu đã được sửa thành công";
} else {
    echo "Lỗi: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
