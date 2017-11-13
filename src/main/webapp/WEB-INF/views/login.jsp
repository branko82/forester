<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
	<title>Login</title>
	<style type="text/css">
        .button {
          width:80px;
        }
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="shortcut icon" href="/forester/resources/images/forester.png"></link>
</head>
<body style="margin: 0px; padding: 0px; font-family: 'Trebuchet MS', verdana; background-color: #d2d8c7">
	<div style="width: 400px; margin: 200px auto 0 auto; border-style: solid; border-width: 1px; background-color:#ffffff">
		<h1>Prijava</h1>
		<form name='loginForm' action="" method='POST'>
			<table>
				<tr>
					<td>Korisnicko ime:</td>
					<td><input type='text' name='user' value=''></td>
				</tr>
				<tr>
					<td>Lozinka:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td><input name="submit" type="submit" value="Prijavi se" class="button" /></td>
					<td><font color="red">${login_error}</font></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>