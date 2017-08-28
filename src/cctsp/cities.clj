; Universidad Nacional Autónoma de México
; Facultad de Ciencias
; Heuristicas de Optimización Combinatoria


(ns cctsp.cities
  (:gen-class))

(defn foo [a] 
	(println "Hello foo!!!") a)

(def simple_matrix [[1 0] [1 0]])

(defn feasible? [path matrix]
	(let [pairs (partition 2 1 path)] 
		(every? identity 
			(map 
				(fn [pair]
					(let [fst (nth pair 0) snd (nth pair 1)]
						(== ((matrix fst) snd) 1))) 
				pairs))))