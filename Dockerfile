# Setup
FROM openjdk:8-jre-alpine
RUN addgroup --system appuser && adduser -S -s /bin/false -G appuser appuser
WORKDIR /app

# Copy necessary files
COPY app/build/install/app/ ./

# Apply user group
RUN chown -R appuser:appuser .
USER appuser

# Prepare for run
WORKDIR /app/bin
CMD ["./app"]
EXPOSE 8080
