(ns cctsp.database-test
  (:require [clojure.test :refer :all]
            [cctsp.database :refer :all]
            [clojure.java.jdbc :as sql]))

(deftest a-test
  (testing "It should be return 15"
    (let [result (-> (sql/query db "SELECT 3*5 as result") first :result)]
      (is (= result 15)))))

(deftest b-test
  (testing "Return de id of Coacalco city 1046"
    (let [city_id (-> (sql/query db "SELECT * FROM cities WHERE name LIKE 'coacalco'") first :id)]
      (is (= city_id 1046)))))

(deftest c-test
  (testing "It should return the length of the cities [1098]"
    (let [cities (-> (sql/query db "SELECT id FROM cities"))
          cities_len (count cities)]
          (is (= cities_len 1098)))))

(deftest d-test
  (testing "It should be return 121"
    (is (= (count (get_neighbors 1 db)) 121))))

(deftest e-test
  (testing "It should be true"
    (let [connection (-> (sql/query db "SELECT * FROM connections WHERE id_city_1 = 619 AND id_city_2 = 847") first)]
      (is (= (connection :distance) (distance 619 847))))))

(deftest e1-test
  (testing "It should be true"
    (let [connection (-> (sql/query db "SELECT * FROM connections WHERE id_city_1 = 622 AND id_city_2 = 733") first)]
      (is (= (connection :distance) (distance 622 733))))))

(deftest e2-test
  (testing "It should be true"
    (let [connection (-> (sql/query db "SELECT * FROM connections WHERE id_city_1 = 851 AND id_city_2 = 993") first)]
      (is (= (connection :distance) (distance 851 993))))))