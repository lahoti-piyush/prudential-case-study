# prudential-case-study

to build project run

	mvn clean install -Dmaven.test.skip=true
	
above command will generate jar file in target folder.

goto target folder

	cd target


In order to start web application on port 8080

	java -jar  weather-0.0.1-SNAPSHOT.jar


output is C:\weather (default) if you want to override output path then  

	java -jar weather-0.0.1-SNAPSHOT.jar  --output.path="C:\\asd\\qwe"

after application started. you can upload file

	URL:-
		http://localhost:8080/upload
	HEADER:-
		content-type:multipart/form-data
	BODY:-
		file:<file> 
	
	
You can use Advance Rest client chrome plugin to test.

This will generate file at Output path and return weather info in response also. 

Thanks
