<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload Files and Detect Language Request Page</title>
</head>
<body>

	<form method="POST" action="uploadFilesAndDetectLanguage" enctype="multipart/form-data">
	    Upload File: <input type="file" name="file"><br /> 
		File Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="Upload">  Upload file and detect language
	</form>
	
</body>
</html>