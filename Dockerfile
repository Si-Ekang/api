# Setup
FROM openjdk:8-jre-alpine
RUN addgroup --system appuser && adduser -S -s /bin/false -G appuser appuser
WORKDIR /app

# Copy necessary files
COPY build/install/api/ ./

# Apply user group
RUN chown -R appuser:appuser .
USER appuser

# Prepare for run
WORKDIR /app/bin
CMD ["./api"]
EXPOSE 8080
