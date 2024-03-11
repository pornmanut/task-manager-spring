FROM public.ecr.aws/lambda/java:17

# Copy function code
COPY target/classes ${LAMBDA_TASK_ROOT}
COPY target/dependency/* ${LAMBDA_TASK_ROOT}/lib/


# Set the CMD to your handler (could also be done as a parameter override outside of the Dockerfile)
CMD [ "com.pornmanut.taskmanager.StreamLambdaHandler::handleRequest" ]
