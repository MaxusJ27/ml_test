.PHONY: run-dev
run-dev: ## Start the Spring Boot application
		./mvnw spring-boot:run

.PHONY: run-build
run-build: ## Build the sam artifact
		sam build

.PHONY: run-deploy
run-deploy: ## Deploy the AWS Lambda function
		sam deploy --guided

.PHONY: help
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.DEFAULT_GOAL := help