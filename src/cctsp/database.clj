;; Universidad Nacional Autónoma de México
;; Facultad de Ciencias
;; Heuristicas de Optimización Combinatoria
;;
;; Funciones para el manejo de la base de datos

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

(def MAX_CITIES (cities_length db))

(defn get_neighbors
  "Función que regresa los vecinos de una ciudad i.e ciudades adyacentes
    Parametros
      :city la ciudad en id
      :db la base de datos
  "
  [city db]
  (vec (sql/query db ["SELECT id_city_2 ,distance
                      FROM connections WHERE id_city_1 = ?"
                      (str city)])))

(defn serializable?
  "Función que dice si algo es serializable
    Parametros
      :v el objeto a evaluar"
  [v]
  (instance? java.io.Serializable v))

(defn vect_neighbors 
  [city]
  (let [vector_ (-> (repeat MAX_CITIES 0) vec transient)
        cities (get_neighbors city db)]
        (persistent! (reduce (fn [v x] (assoc! v (x :id_city_2) (x :distance))) 
                             vector_ 
                             cities))))


(def matrix
  "Matriz de adyacencias de las ciudades"
  (let [connections (sql/query db "SELECT * FROM connections")
        cities_length (count (sql/query db "SELECT * FROM cities"))
        init_matrix (->> (repeat (+ cities_length 1) 0) vec
                         (repeat (+ cities_length 1)) vec
                         transient)]
        (persistent!
          (reduce
            (fn [v x] 
              "Simplemente buscamos la posicion de las ciudades en la matrix
              y escribimos su distancia en ella"
              (let [c1 (x :id_city_1)
                    c2 (x :id_city_2)
                    distance (x :distance)]
                    (assoc! v c1 (assoc (v c1) c2 distance))))
            init_matrix connections))))

(defn distance [c1 c2]
  ((matrix c1) c2))