build:
	mvn clean package dependency:copy-dependencies

docker-build:
	docker build -t task-manger-spring:latest .

docker-run:
	docker run -p 9000:8080 task-manger-spring:latest

docker-test:
	curl "http://localhost:9000/2015-03-31/functions/function/invocations" -d '{}'
