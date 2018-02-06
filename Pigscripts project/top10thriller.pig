REGISTER '/Users/varshav/NetBeansProjects/PigUDF/dist/PigUDF.jar'

movies = LOAD '/Users/varshav/Downloads/MovieLens/movies.dat' using PigStorage (':') AS (movie_id:int, dummy1, movie_name:chararray, dummy2, movie_genre);

movies_all = FOREACH movies GENERATE movie_id,movie_name,movies.MovieYear(movie_name) AS year, FLATTEN(STRSPLIT(movie_genre,'\\|',-1)) AS (genre1:chararray,genre2:chararray,genre3:chararray,genre4:chararray,genre5:chararray,genre6:chararray); 

C = FOREACH movies_all GENERATE movie_id,movie_name,year,genre1,genre2,genre3,genre4,genre5,genre6;

ratings = LOAD '/Users/varshav/Downloads/MovieLens/ratings.dat' using PigStorage (':') AS (user_id:int, dummy1, movie_id:int, dummy2, movie_rating:int, dummy3,timestamp:long);

thriller = FILTER C by $3 == 'Thriller' or $4== 'Thriller' or  $5 == 'Thriller' or $6=='Thriller' or $7=='Thriller' or $8=='Thriller';

joind = JOIN thriller by movie_id,ratings by movie_id;

fr = FOREACH joind generate movie_name,movie_rating,year;

gr = GROUP fr by movie_name;
V = FOREACH gr generate group as movie_name,AVG(fr.movie_rating);
ord = ORDER V by $1 desc;
top10 = limit ord 10;

STORE top10 INTO './top10Thriller';