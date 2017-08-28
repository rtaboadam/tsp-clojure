(defproject cctsp "0.1.0-SNAPSHOT"
  :description "TSP problem with Simulated Annealing"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
  	[org.clojure/clojure "1.8.0"]
  	[corpus-enormous "0.1.4"]
  	[seesaw "1.4.2" :exclusions [org.clojure/clojure]]
    [org.clojure/java.jdbc "0.3.5"]
    [org.xerial/sqlite-jdbc "3.7.2"]
    [net.mikera/core.matrix "0.60.3"]
  ]
  :plugins [[lein-gorilla "0.4.0"]]
  :main ^:skip-aot cctsp.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
