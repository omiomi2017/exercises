# exercises
Small size projects to exercise

NEO - NearEarthObjects explore the Nasa api for near earth objects
for example https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-10&detailed=false&api_key=DEMO_KEY

To compile, use mvn compile
then mvn exec:java

The application will open port 9000 for the web server.

Get api key at https://api.nasa.gov/index.html#apply-for-an-api-key

Request data.

Note on tests:
a. some tests that download data are disabled not to exceed DEMO_KEY, can enable them in NEOClientDownloadImplTest 
look for variable runningTestsThatDownloadData and set it to true;

The API provides two types of resources feed - which has a list of NEO(Near Earth Object) 
-each NEO would have one approach
and a detailed description per NEO - from there we can get orbital information as well as all close approaches.

The application caches downloaded resources so requesting the same data more than once will not result in additional downloads.
NAsa api limits date range requests to 7 days, there's a layer to request data intervals of arbitrary size by chunking.

