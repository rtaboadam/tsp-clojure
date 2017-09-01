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

(defn get_neighbors
  "Función que regresa los vecinos de una ciudad i.e ciudades adyacentes
    Parametros
      :city la ciudad en id
      :db la base de datos
  " 
  [city db]
  (sql/query db ["SELECT id_city_2 ,distance FROM connections WHERE id_city_1 = ?", (str city)]))

