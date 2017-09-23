(defproject cctsp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [debugger "0.2.0"]
                 [random-seed "1.0.0"]]
  :main ^:skip-aot cctsp.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
