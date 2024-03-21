build: clean
	./gradlew build

clean:
	./gradlew clean

run:
	./gradlew bootRun

test:
	./gradlew test

format:
	./gradlew spotlessApply

format-check:
	./gradlew spotlessCheck

sonar:
	./gradlew sonarqube

health:
	curl -X GET http://localhost:8080/api/v1/health

swagger:
	open http://localhost:8080/swagger-ui/index.html

docker-build:
	docker build -t kbazaar .

docker-run:
	docker run -p 8080:8080 kbazaar