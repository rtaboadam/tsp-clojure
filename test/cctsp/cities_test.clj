(ns cctsp.cities-test
  (:require [clojure.test :refer :all]
    [cctsp.cities :refer :all]))

(def matrix [
	[1 1 1 0 0 0]
	[1 1 1 0 0 0]
	[1 1 1 1 1 1]
	[0 0 1 1 0 0]
	[0 0 1 0 1 0]
	[0 0 1 0 0 1]])

(deftest a-test
	(testing "It should be true"
   (is (= (feasible? [3 2 4] matrix) true))))

(deftest b-test
	(testing "It should be false"
		(is (= (feasible? [0 1 2 5 4] matrix) false))))
