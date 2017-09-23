(ns cctsp.core
  (:require [clojure.java.jdbc :as sql]
            [random-seed.core :refer :all])
  (:refer-clojure :exclude [rand rand-int rand-nth])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
