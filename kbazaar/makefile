build: clean
	./gradlew build

setup:
	pre-commit install --hook-type pre-commit --config ../.pre-commit-config.yaml
	pre-commit install --hook-type pre-push --config ../.pre-push-config.yaml

clean:
	./gradlew clean

run:
	./gradlew bootRun

test:
	./gradlew clean && ./gradlew test

it-test:
	./gradlew clean && ./gradlew it-test

format:
	./gradlew spotlessApply

format-check:
	./gradlew spotlessCheck

sonar:
	./gradlew sonar

health:
	curl -X GET http://localhost:8080/api/v1/health

swagger:
	open http://localhost:8080/swagger-ui/index.html

docker-build:
	docker build -t kbazaar .

docker-run:
	docker run -p 8080:8080 kbazaar
