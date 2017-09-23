(ns cctsp.core
  (:require [clojure.java.jdbc :as sql]
            [random-seed.core :refer :all])
  (:refer-clojure :exclude [rand rand-int rand-nth])
  (:gen-class))


(declare cost-function)
(declare gen-city)
(declare distance-memo)
(declare distance)

(defprotocol Point
	(distance-to [this city])
	(to-string [this]))

(defprotocol PointCollection
	(total-distance [this]))

(defrecord City [name latitude longitude]
	Point
	(distance-to [this city] (distance-memo this city))
	(to-string [this] (str (:name this) ":(" (:latitude this) "," (:longitude this))))

(defrecord Tour [cities]
  PointCollection
  (total-distance [this]
  	(let [cities (:cities this)] 
  		(loop [curr cities distances '()]
  			(if (empty? (rest curr))
  				(reduce + distances)
  				(recur (rest curr) (conj distances (distance-to (first curr) (second curr))))
  				)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

