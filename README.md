# counter-api

use following to test functionality

curl http://localhost:8080/counter-api/search -X POST -d "searchText=ipsum,ert,mmg"  -H "ContentType: application/json"  -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw=="

curl http://localhost:8080/counter-api/top/5 -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw=="