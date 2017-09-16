;; Universidad Nacional Autónoma de México
;; Facultad de Ciencias
;; Heuristicas de Optimización Combinatoria
;;
;; Definiciones acerca del algoritmo de Recocido simulado

(ns cctsp.anneling)

;; Definimos el protocolo solución 
;; (Un protocolo es una interfaz...bueno, parecido)

(defprotocol Solution
	"El recocido simulado trabaja sobre soluciones.
	Este ultimo se mueve a través del espacio de busqueda que
	las posibles soluciones de un problema genera"

	(neighbour [this] 
		"Una solucion debe ser capaz de generar un vecino a traves de si mismo")
	(neighbours [this]
		"Para generar un lista de vecionos")
	(feasible? [this]
		"Funcíon para garantizar que la solución es valida")
	(fitness [this]
		"Función que devuelve un promedio de que tan `buena` es la solución"))
