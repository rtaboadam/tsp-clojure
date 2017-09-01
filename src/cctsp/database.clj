(ns cctsp.database
  (:require [clojure.java.jdbc :as sql]
            [clojure.string :as str])
  (:gen-class))

(def db 
  "Conexion con la base de datos"
  { :classname "org.sqlite.JDBC"
    :subprotocol "sqlite"
    :subname "resources/world.db"
  })

(defn cities_length
  "Devuelve la cantidad de ciudad que hay en la base de datos
    Parametros
      :db La base de datos"
  [db]
  (-> (sql/query db "SELECT * FROM cities") count))

(defn get_neighbors
  "Función que regresa los vecinos de una ciudad i.e ciudades adyacentes
    Parametros
      :city la ciudad en id
      :db la base de datos
  " 
  [city db]
  (vec (sql/query db ["SELECT id_city_2 ,distance FROM connections WHERE id_city_1 = ?", (str city)])))

(defn serializable?
  "Función que dice si algo es serializable
    Parametros
      :v el objeto a evaluar"
  [v]
  (instance? java.io.Serializable v))

(defn make_matrix 
  "Función que crea una matrix de r x c
    Parametros:
      :r Renglones
      :c Columnads"
  [r c]
  (vec (repeat c (vec (repeat r 0)))))
