# ilivalidator-jobrunr

## Todo
- SQLite? Funktioniert.
- Können zwei identisch konfigurierte Container laufen? Oder sind dann beides Master, was zu Problemen führt? -> Funktioniert. Wie verhält sich das in einem Kubernetes-Cluster?
- Reicht Autowired Datasource? Ja.
- Schema-Support?

## Database storage
```
docker-compose build
docker-compose up -d edit-db
```

## Runtime tests

### Same image twice
```
docker run -p 8081:8080 -p 8000:8000 sogis/ilivalidator-jobrunr:latest
```
```
docker run -p 8082:8080 sogis/ilivalidator-jobrunr:latest
```
```
curl http://localhost:8081/run-job\?name\="foo"
curl http://localhost:8082/run-job\?name\="bar"
```

Scheint zu funktionieren. Es gibt nur einen Master (siehe Output beim Startup). Jobs werden auf einem freien Server prozessiert.