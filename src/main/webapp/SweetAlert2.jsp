<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
<script>
window.onload=function() {
    swal.fire({
      title: '${title}',
      html: '${text}',
      icon: '${icon}',
      confirmButtonText: '확인'
    }).then((result) => {
    	location.href="${url}";
    });
  };
</script>

</body>
</html>