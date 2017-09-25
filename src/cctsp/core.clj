(ns cctsp.core
  (:require [clojure.java.jdbc :as sql]
            [random-seed.core :refer :all])
  (:refer-clojure :exclude [rand rand-int rand-nth])
  (:gen-class))

(declare cost-function)
(declare gen-city)
(declare distance-memo)
(declare distance)
(declare swap)

(def db 
	{ :classname "org.sqlite.JDBC" 
	  :subprotocol "sqlite"
	  :subname "resources/world.db"})


(def cities_length (count (sql/query db "SELECT * FROM cities")))

(def cities_matrix
	(let [connections (sql/query db "SELECT * FROM connections")
		  cities_length (count (sql/query db "SELECT * FROM cities"))
		  init_matrix (->> (repeat (+ cities_length 1) 0) vec
		  				   (repeat (+ cities_length 1)) vec
		  				   transient)]
		  (persistent!
		  	(reduce
		  		(fn [v x]
		  			(let [c1 (x :id_city_1)
		  				  c2 (x :id_city_2)
		  				  distance (x :distance)]
		  				  (assoc! v c1 (assoc (v c1) c2 distance))))
		  		init_matrix connections))))

(defprotocol Point
	(distance-to [this city])
	(to-string [this]))

(defprotocol PointCollection
	(total-distance [this])
	(neighbour [this]))

(defrecord City [id name latitude longitude]
	Point
	(distance-to [this city] (distance-memo (:id this) (:id city)))
	(to-string [this] (str (:name this) ":(" (:latitude this) "," (:longitude this) ")")))

(defrecord Tour [cities]
  PointCollection
  (total-distance [this]
  	(let [cities (:cities this)] 
  		(loop [curr cities distances '()]
  			(if (empty? (rest curr))
  				(reduce + distances)
  				(recur (rest curr) (conj distances (distance-to (first curr) (second curr))))
  				))))
  (neighbour [this]
  	(swap this)))

(defn distance [c1 c2]
	((cities_matrix c1) c2))

(def distance-memo (memoize distance))

(defn swap [coll pos1 pos2]
	(let [item1 (nth coll pos1)
		  item2 (nth coll pos2)]
		  (assoc (assoc coll pos1 item2) pos2 item1)))

(defn gen-city [city-id]
	(let [city (-> (sql/query db ["SELECT * FROM cities WHERE id=?" city-id]) first)] 
		(City. (city :id) (city :name) (city :latitude) (city :longitude))))

(defn random-tour [number-of-cities]
	(Tour. (vec (map 
		gen-city 
		(take number-of-cities (repeatedly #(rand-int cities_length))))
	)))



; (defn calculate-lot [temp solution limit]
; 	(loop [c 0 r 0. s solution]
; 		(let [s' (neighbour s)
; 			  f_s (total-distance s)
; 			  f_s' (total-distance s')]
; 			  (if (>= (+ f_s temp) f_s'))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

