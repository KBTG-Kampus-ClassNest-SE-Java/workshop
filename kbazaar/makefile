# install dependencies gradle
install:
	./gradlew build
	./gradlew install

run:
	./gradlew bootRun

health:
	curl -X GET http://localhost:8080/api/v1/health

docker-build:
	docker build -t kbazaar .

docker-run:
	docker run -p 8080:8080 kbazaar